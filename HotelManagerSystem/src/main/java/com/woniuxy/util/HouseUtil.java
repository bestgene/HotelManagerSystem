package com.woniuxy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.woniuxy.pojo.House;

public class HouseUtil {
	// 已知 一个集合 Collection 从集合中查找 n个不同的对象;
	public static List<House> findDifferObj(List<House> all, Integer n) {
		List<House> result = new ArrayList<House>();
		Random random = new Random();
		if (all.size() > 0) {
			boolean flag[] = new boolean[all.size()];
			int index;
			for (int i = 0; i < n; i++) {
				do {
					index = random.nextInt(all.size());
					if (flag[index] == false) {
						result.add(all.get(index));
						flag[index] = true;
						break;
					}
				} while (flag[index]);
			}
			random.nextInt(all.size());
			return result;
		}
		return null;
	}

	// 根据起止时间差错所有天数

	public static List<String> allDay(String startTime, String endTime) throws ParseException {
		List<String> allDay = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse(startTime);
		Date end = sdf.parse(endTime);
		int day = (int) ((end.getTime() - start.getTime()) / 1000 / 3600 / 24);
		if (day > 0) {
			for (int d = 0; d < day; d++) {
				String currentDay = sdf.format(new Date(start.getTime() + d * 3600 * 24 * 1000));
				allDay.add(currentDay);
			}
		}
		return allDay;
	}
}
