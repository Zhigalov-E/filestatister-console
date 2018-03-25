package com.myapp.console.models;

public class FileStatisticModel {
	private int lengthOfLongestWord;
	private int lengthOfShortestWord;
	private double averageLengthOfWords;
	private int lengthLines;

	public int getLengthOfLongestWord() {
		return lengthOfLongestWord;
	}

	public void setLengthOfLongetWord(int lengthOfLongestWord) {
		this.lengthOfLongestWord = lengthOfLongestWord;
	}

	public int getLengthOfShortestWord() {
		return lengthOfShortestWord;
	}

	public void setLengthOfShortestWord(int lengthOfShortestWord) {
		this.lengthOfShortestWord = lengthOfShortestWord;
	}

	public double getAverageLengthOfWords() {
		return averageLengthOfWords;
	}

	public void setAverageLengthOfWords(double averageLengthOfWords) {
		this.averageLengthOfWords = averageLengthOfWords;
	}

	public int getLengthLines() {
		return lengthLines;
	}

	public void setLengthLines(int lengthLines) {
		this.lengthLines = lengthLines;
	}
}
