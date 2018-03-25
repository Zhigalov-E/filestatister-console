package com.myapp.console.models;

public class LineStatisticModel {
	private int lengthOfLongestWord;
	private int lengthOfShortestWord;
	private double averageLengthOfWords;
	private int lineLength;

	public int getLengthOfLongestWord() {
		return lengthOfLongestWord;
	}

	public void setLengthOfLongestWord(int lengthOfLongestWord) {
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

	public int getLineLength() {
		return lineLength;
	}

	public void setLineLength(int lineLength) {
		this.lineLength = lineLength;
	}
}
