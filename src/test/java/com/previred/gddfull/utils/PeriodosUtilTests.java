package com.previred.gddfull.utils;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class PeriodosUtilTests {
	@Test
	void testGetPeriodosBetweenDatesEqual() {
		LocalDate startDate = LocalDate.of(1969, 3, 1);
		LocalDate endDate = LocalDate.of(1970, 1, 1);
		List<LocalDate> expected = new ArrayList<LocalDate>();
		expected.add(LocalDate.of(1969, 3, 1));
		expected.add(LocalDate.of(1969, 4, 1));
		expected.add(LocalDate.of(1969, 5, 1));
		expected.add(LocalDate.of(1969, 6, 1));
		expected.add(LocalDate.of(1969, 7, 1));
		expected.add(LocalDate.of(1969, 8, 1));
		expected.add(LocalDate.of(1969, 9, 1));
		expected.add(LocalDate.of(1969, 10, 1));
		expected.add(LocalDate.of(1969, 11, 1));
		expected.add(LocalDate.of(1969, 12, 1));
		expected.add(LocalDate.of(1970, 1, 1));
		List<LocalDate> actual = PeriodosUtil.getPeriodosBetweenDates(startDate, endDate);
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetPeriodosBetweenDatesNoEqual() {
		LocalDate startDate = LocalDate.of(1969, 12, 1);
		LocalDate endDate = LocalDate.of(1970, 3, 1);
		List<LocalDate> expected = new ArrayList<LocalDate>();
		expected.add(LocalDate.of(1969, 12, 1));
		expected.add(LocalDate.of(1970, 1, 1));
		expected.add(LocalDate.of(1970, 3, 1));
		List<LocalDate> actual = PeriodosUtil.getPeriodosBetweenDates(startDate, endDate);
		assertNotEquals(expected, actual);
	}
	
	@Test
	void testGetPeriodosBetweenDatesLessThan() {
		LocalDate startDate = LocalDate.of(1970, 3, 1);
		LocalDate endDate = LocalDate.of(1969, 12, 1);	
		List<LocalDate> expected = new ArrayList<LocalDate>();
		List<LocalDate> actual = PeriodosUtil.getPeriodosBetweenDates(startDate, endDate);
		assertEquals(expected, actual);
	}
	
	@Test
	void minusListEqual() {
		List<LocalDate> list = new ArrayList<LocalDate>();
		list.add(LocalDate.of(1969, 3, 1));
		list.add(LocalDate.of(1969, 4, 1));
		list.add(LocalDate.of(1969, 5, 1));
		list.add(LocalDate.of(1969, 6, 1));
		list.add(LocalDate.of(1969, 7, 1));
		list.add(LocalDate.of(1969, 8, 1));
		list.add(LocalDate.of(1969, 9, 1));
		list.add(LocalDate.of(1969, 10, 1));
		list.add(LocalDate.of(1969, 11, 1));
		list.add(LocalDate.of(1969, 12, 1));
		list.add(LocalDate.of(1970, 1, 1));
		
		List<LocalDate> other = new ArrayList<LocalDate>();
		other.add(LocalDate.of(1969, 3, 1));
		other.add(LocalDate.of(1969, 5, 1));
		other.add(LocalDate.of(1969, 9, 1));
		other.add(LocalDate.of(1970, 1, 1));
		
		List<LocalDate> expected = new ArrayList<LocalDate>();
		expected.add(LocalDate.of(1969, 4, 1));
		expected.add(LocalDate.of(1969, 6, 1));
		expected.add(LocalDate.of(1969, 7, 1));
		expected.add(LocalDate.of(1969, 8, 1));
		expected.add(LocalDate.of(1969, 10, 1));
		expected.add(LocalDate.of(1969, 11, 1));
		expected.add(LocalDate.of(1969, 12, 1));
		
		List<LocalDate> actual = PeriodosUtil.minusList(list, other);
		assertEquals(expected, actual);
	}
	
	@Test
	void minusListEmpty() {
		List<LocalDate> list = new ArrayList<LocalDate>();
		list.add(LocalDate.of(1969, 3, 1));
		list.add(LocalDate.of(1969, 4, 1));
		list.add(LocalDate.of(1969, 5, 1));
		list.add(LocalDate.of(1969, 6, 1));
		list.add(LocalDate.of(1969, 7, 1));
		list.add(LocalDate.of(1969, 8, 1));
		list.add(LocalDate.of(1969, 9, 1));
		list.add(LocalDate.of(1969, 10, 1));
		list.add(LocalDate.of(1969, 11, 1));
		list.add(LocalDate.of(1969, 12, 1));
		list.add(LocalDate.of(1970, 1, 1));
		
		List<LocalDate> other = new ArrayList<LocalDate>();
		other.add(LocalDate.of(1969, 3, 1));
		other.add(LocalDate.of(1969, 4, 1));
		other.add(LocalDate.of(1969, 5, 1));
		other.add(LocalDate.of(1969, 6, 1));
		other.add(LocalDate.of(1969, 7, 1));
		other.add(LocalDate.of(1969, 8, 1));
		other.add(LocalDate.of(1969, 9, 1));
		other.add(LocalDate.of(1969, 10, 1));
		other.add(LocalDate.of(1969, 11, 1));
		other.add(LocalDate.of(1969, 12, 1));
		other.add(LocalDate.of(1970, 1, 1));
		
		List<LocalDate> expected = new ArrayList<LocalDate>();
		List<LocalDate> actual = PeriodosUtil.minusList(list, other);
		assertEquals(expected, actual);
	}
}
