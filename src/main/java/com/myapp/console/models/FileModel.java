package com.myapp.console.models;

import java.util.Collection;

public class FileModel {
	private String name;
	private String path;
	private FileStatisticModel fileStatistic;
	private Collection<LineStatisticModel> linesStatistic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FileStatisticModel getFileStatistic() {
		return fileStatistic;
	}

	public void setFileStatistic(FileStatisticModel fileStatistic) {
		this.fileStatistic = fileStatistic;
	}

	public Collection<LineStatisticModel> getLinesStatistic() {
		return linesStatistic;
	}

	public void setLinesStatistic(Collection<LineStatisticModel> linesStatistic) {
		this.linesStatistic = linesStatistic;
	}
}
