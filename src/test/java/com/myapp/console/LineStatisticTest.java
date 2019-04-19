package com.myapp.console;

import com.myapp.console.logic.LineStatistic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineStatisticTest {
	public static final String LINE = "These methods should be grouped by functionality rather than by scope or " +
			"accessibility. For example, a private class method can be in between two public instance methods. The " +
			"goal is to make reading and understanding the code easier.";

	private LineStatistic lineStatistic;

	@Test
	public void getLengthLongestWordInLineTest() {
		LineStatistic lineStatistic = new LineStatistic();
		int result = lineStatistic.getLengthLongestWordInLine(LINE);
		assertEquals("Should be 14", 14, result);
	}



	@Test
	public void getLengthLongestWordInEmptyLineTest() {
		LineStatistic lineStatistic = new LineStatistic();
		int result = lineStatistic.getLengthLongestWordInLine(" ");
		assertEquals("Should be 0", 0, result);
	}

	@Test
	public void getLengthShortestWordInLineTest() {
		LineStatistic lineStatistic = new LineStatistic();
		int result = lineStatistic.getLengthShortestWordInLine(LINE);
		assertEquals("Should be 1", 1, result);
	}

	@Test
	public void getLengthShortestWordInEmptyLineTest() {
		LineStatistic lineStatistic = new LineStatistic();
		int result = lineStatistic.getLengthShortestWordInLine(" ");
		assertEquals("Should be 0", 0, result);
	}

	@Test
	public void getLengthOfLineTest() {
		LineStatistic lineStatistic = new LineStatistic();
		int result = lineStatistic.getLengthOfLine(LINE);
		assertEquals("Should be 233", 233, result);
	}


	@Test
	public void getAverageWordLengthInLineTest() {
		LineStatistic lineStatistic = new LineStatistic();
		double result = lineStatistic.getAverageWordLengthInLine(LINE);
		assertEquals(5.157, result, 0.001);
	}

	@Test
	public void getAverageWordLengthInEmptyLineTest() {
		LineStatistic lineStatistic = new LineStatistic();
		double result = lineStatistic.getAverageWordLengthInLine(" ");
		assertEquals(0.0, result, 0);
	}
}
