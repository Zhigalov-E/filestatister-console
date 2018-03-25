package com.myapp.console;

import com.myapp.console.logic.FileStatistic;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class FileStatisticTest {


	@Test
	public void getLengthLongestWordInFileTest() {
		FileStatistic fileStatistic = new FileStatistic();
		Collection<Integer> collection = Arrays.asList(11, 11 ,12, 12, 13, 14, 15);
		int result = fileStatistic.getLengthLongestWordInFile(collection);
		assertEquals("Should be 15", 15, result);
	}

	@Test
	public void whenEmptyCollectionGetLengthLongestWordInFileTest() {
		FileStatistic fileStatistic = new FileStatistic();
		int result = fileStatistic.getLengthLongestWordInFile(Collections.emptyList());
		assertEquals("Should be 0", 0, result);
	}

	@Test
	public void getLengthShortestWordInFileTest() {
		FileStatistic fileStatistic = new FileStatistic();
		Collection<Integer> collection = Arrays.asList(11, 11 ,12, 12, 13, 14, 15);
		int result = fileStatistic.getLengthShortestWordInFile(collection);
		assertEquals("Should be 11", 11, result);
	}

	@Test
	public void whenEmptyCollectionGetLengthShortestWordInFileTest() {
		FileStatistic fileStatistic = new FileStatistic();
		int result = fileStatistic.getLengthShortestWordInFile(Collections.emptyList());
		assertEquals("Should be 0", 0, result);
	}

	@Test
	public void getLineLengthInFileTest() {
		FileStatistic fileStatistic = new FileStatistic();
		Collection<Integer> collection = Arrays.asList(111, 61 ,112, 111);
		int result = fileStatistic.getLineLengthInFile(collection);
		assertEquals("Should be 395", 395, result);
	}

	@Test
	public void whenEmptyCollectionGetLineLengthInFileTest() {
		FileStatistic fileStatistic = new FileStatistic();
		int result = fileStatistic.getLineLengthInFile(Collections.emptyList());
		assertEquals("Should be 0", 0, result);
	}

	@Test
	public void getAverageWordLengthInFileTest() {
		FileStatistic fileStatistic = new FileStatistic();
		Collection<Double> collection = Arrays.asList(5.346, 6.345 , 4.534355, 11.435);
		double result = fileStatistic.getAverageWordLengthInFile(collection);
		assertEquals(6.915, result, 0.001);
	}

	@Test
	public void whenEmptyCollectioGetAverageWordLengthInFileTest() {
		FileStatistic fileStatistic = new FileStatistic();
		double result = fileStatistic.getAverageWordLengthInFile(Collections.emptyList());
		assertEquals(0, result, 0);
	}

}
