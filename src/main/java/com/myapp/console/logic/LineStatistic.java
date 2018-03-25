package com.myapp.console.logic;

import java.util.Arrays;
import java.util.Comparator;

public class LineStatistic {
	private static final String WORD_SEPARATOR = " ";

	public int getLengthLongestWordInLine(String line) {
		return Arrays.stream(line.split(WORD_SEPARATOR)).max(Comparator.comparingInt(String::length)).orElse("").length();
	}

	public int getLengthShortestWordInLine(String line) {
		return Arrays.stream(line.split(WORD_SEPARATOR)).min(Comparator.comparingInt(String::length)).orElse("").length();
	}

	public int getLengthOfLine(String line) {
		return line.length();
	}

	public double getAverageWordLengthInLine(String line) {
		return Arrays.stream(line.split(WORD_SEPARATOR)).mapToInt(String::length).average().orElse(0.0);
	}
}
