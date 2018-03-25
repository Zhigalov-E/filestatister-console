package com.myapp.console.logic;

import java.util.Collection;

public class FileStatistic {

	public int getLengthLongestWordInFile(Collection<Integer> longestWords) {
		return longestWords.parallelStream().mapToInt(v -> v).max().orElse(0);
	}

	public int getLengthShortestWordInFile(Collection<Integer> shotestWords) {
		return shotestWords.parallelStream().mapToInt(v -> v).min().orElse(0);
	}

	public int getLineLengthInFile(Collection<Integer> lineLength) {
		return lineLength.parallelStream().mapToInt(v -> v).sum();
	}

	public double getAverageWordLengthInFile(Collection<Double> avarageLength) {
		return avarageLength.parallelStream().mapToDouble(d -> d).average().orElse(0);
	}
}
