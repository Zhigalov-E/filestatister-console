package com.myapp.console.services;

import com.myapp.console.logic.FileStatistic;
import com.myapp.console.models.FileModel;
import com.myapp.console.models.FileStatisticModel;
import com.myapp.console.models.LineStatisticModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FileStatisticService {
	private final FileStatistic fileStatistic = new FileStatistic();
	private final LineStatisticService lineStaticticService = new LineStatisticService();

	public FileModel aggregateFileStatistic(File file) {
		FileModel fileModel = new FileModel();
		fileModel.setName(file.getName());
		fileModel.setPath(file.getAbsolutePath());
		fileModel.setLinesStatistic(getLinesStatistic(file));

		if (!fileModel.getLinesStatistic().isEmpty()) {
			fileModel.setFileStatistic(aggregateFileStatistic(fileModel.getLinesStatistic()));
		} else {
			fileModel.setFileStatistic(new FileStatisticModel());
		}
		return fileModel;
	}

	private List<LineStatisticModel> getLinesStatistic(File file) {
		List<LineStatisticModel> lineStaticticModels = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)))) {
			String strLine;
			while ((strLine = bufferedReader.readLine()) != null)   {
				lineStaticticModels.add(lineStaticticService.getStatistic(strLine));
			}
		} catch (FileNotFoundException e) {
			System.err.println("FileStatisticService: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("FileStatisticService: " + e.getMessage());
		}
		return lineStaticticModels;
	}

	private FileStatisticModel aggregateFileStatistic(Collection<LineStatisticModel> lineStaticticModels) {
		FileStatisticModel fileStatisticModel = new FileStatisticModel();
		fileStatisticModel.setLengthOfLongetWord(fileStatistic.getLengthLongestWordInFile(
				lineStaticticModels.stream()
				.map(LineStatisticModel::getLengthOfLongestWord)
				.collect(Collectors.toList()))
		);
		fileStatisticModel.setLengthOfShortestWord(fileStatistic.getLengthShortestWordInFile(
				lineStaticticModels.stream()
						.map(LineStatisticModel::getLengthOfShortestWord)
						.collect(Collectors.toList()))
		);
		fileStatisticModel.setAverageLengthOfWords(fileStatistic.getAverageWordLengthInFile(
				lineStaticticModels.stream()
						.map(LineStatisticModel::getAverageLengthOfWords)
						.collect(Collectors.toList()))
		);
		fileStatisticModel.setLengthLines(fileStatistic.getLineLengthInFile(
				lineStaticticModels.stream()
						.map(LineStatisticModel::getLineLength)
						.collect(Collectors.toList()))
		);
		return fileStatisticModel;
	}
}
