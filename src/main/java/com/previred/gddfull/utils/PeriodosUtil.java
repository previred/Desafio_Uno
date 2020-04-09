package com.previred.gddfull.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class PeriodosUtil {

	public static List<LocalDate> getPeriodosBetweenDates(LocalDate startDate, LocalDate endDate) {
		if (endDate.compareTo(startDate) < 0)
			return new ArrayList<LocalDate>();
		Set<LocalDate> fullDates = new HashSet<LocalDate>();
		for (LocalDate nextDate = startDate; endDate.compareTo(nextDate) >= 1; nextDate = nextDate.plusMonths(1)) {
			fullDates.add(nextDate);
		}
		fullDates.add(endDate);
		return setToList(fullDates);
	}

	private static List<LocalDate> setToList(Set<LocalDate> set) {
		List<LocalDate> list = Lists.newArrayList(set);
		Collections.sort(list);
		return list;
	}

	public static List<LocalDate> minusList(List<LocalDate> list, List<LocalDate> other) {
		List<LocalDate> minus = list.stream()
				.filter(e -> !other.contains(e))
				.collect(Collectors.toList());
		return minus;
	}


}
