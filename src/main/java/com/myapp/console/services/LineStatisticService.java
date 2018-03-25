package com.myapp.console.services;

import com.myapp.console.logic.LineStatistic;
import com.myapp.console.models.LineStatisticModel;

public class LineStatisticService {
	private final LineStatistic lineStatistic = new LineStatistic();

	public LineStatisticModel getStatistic(String line) {
		LineStatisticModel lineStatictic = new LineStatisticModel();
		lineStatictic.setLengthOfLongestWord(lineStatistic.getLengthLongestWordInLine(line));
		lineStatictic.setLengthOfShortestWord(lineStatistic.getLengthShortestWordInLine(line));
		lineStatictic.setLineLength(lineStatistic.getLengthOfLine(line));
		lineStatictic.setAverageLengthOfWords(lineStatistic.getAverageWordLengthInLine(line));
		return lineStatictic;
	}
}
