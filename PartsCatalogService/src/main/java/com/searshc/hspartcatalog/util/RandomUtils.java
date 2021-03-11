package com.searshc.hspartcatalog.util;

import java.util.Random;

public class RandomUtils {

	public static int inRange(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min) + min;
	}
}
