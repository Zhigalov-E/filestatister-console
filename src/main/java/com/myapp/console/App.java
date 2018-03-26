package com.myapp.console;

import com.beust.jcommander.JCommander;
import com.myapp.console.db.FileStatisticRepository;
import com.myapp.console.services.FileStatisticService;
import com.myapp.console.util.Args;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	private static final String TXT_EXT = "TXT";
	private static ExecutorService service = Executors.newCachedThreadPool();


	public static void main(String[] args) {
		Args argv = new Args();
		JCommander.newBuilder()
				.addObject(argv)
				.build()
				.parse(args);
		if (argv.isHelp()) {
			System.out.println(getHelpMessage());
			return;
		}

		if (argv.isDir()) {
			checkExistsAndProcessFolder(argv.getPath(), argv.isRecursive());
		} else {
			processFiles(argv.getPath());
		}
		service.shutdown();
	}

	private static void checkExistsAndProcessFolder(Collection<String> dirs, boolean isRecursive) {
		for (String dir : dirs) {
			File file = new File(dir);
			if (isExistsDirectory(file)) {
				if (isRecursive) {
					processFileFolderRecurcive(file);
				} else {
					processFileFolder(file);
				}
			}
		}
	}

	private static void processFileFolderRecurcive(final File file) {
		for (final File fileEntry : file.listFiles()) {
			if (fileEntry.isDirectory()) {
				processFileFolderRecurcive(fileEntry);
			} else {
				if (isShouldFileProcess(fileEntry)) {
					processFileInThread(fileEntry);
				}
			}
		}
	}

	private static void processFileFolder(final File dir) {
		for (final File fileEntry : dir.listFiles()) {
			if (isShouldFileProcess(fileEntry)) {
				processFileInThread(fileEntry);
			}
		}
	}

	private static void processFiles(Collection<String> files) {
		for (String fileStr : files) {
			File file = new File(fileStr);
			if (isShouldFileProcess(file)) {
				processFileInThread(file);
			}
		}
	}

	private static void processFile(File file) {
		FileStatisticService fileStatisticService = new FileStatisticService();
		FileStatisticRepository.saveFile(fileStatisticService.aggregateFileStatistic(file));
		System.out.println("processed file: "+ file.getPath());
	}

	private static void processFileInThread(File file) {
		CompletableFuture.runAsync(() -> {
					processFile(file);
				}, service).exceptionally(e -> errorHandle(e));
	}

	private static boolean isExistsDirectory(File file) {
		return file.exists() && file.isDirectory();
	}

	private static boolean isShouldFileProcess(File file) {
		return file.exists() && file.isFile() && file.canRead() && checkTxtFileExtension(file);
	}

	private static boolean checkTxtFileExtension(File file) {
		return getFileExtension(file).equalsIgnoreCase(TXT_EXT);
	}

	private static String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

	private static String getHelpMessage() {
		return "usage: file-stat [--directory] [--recursive] [--help]\n" +
				"					[--path[=<path>]]\n"+
				"	 -p --path		<file search path of directories >\n" +
				"                  	 A , separated list of directories\n"+
				"    -r --recursive find files in directory with recursion\n" +
				"    -d --directory find files in directory\n" +
				"    -h --help      print this help message";
	}

	private static Void errorHandle(Throwable  e){
		if (e != null) {
			System.out.println("error occured. " + e.getMessage());
		}
		return null;
	}
}
