package com.myapp.console;

import com.myapp.console.db.FileStatisticRepository;
import com.myapp.console.services.FileStatisticService;

import java.io.*;

public class App {

	public static final String TXT_EXT = "TXT";

	public static void main(String[] args) {
		FileStatisticService fileStatisticService = new FileStatisticService();
		for (String arg : args) {
			File file = new File(arg);
			if (isShouldFileProcess(file)) {
				FileStatisticRepository.saveFile(fileStatisticService.aggregateFileStatistic(file));
			}
		}
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
}
