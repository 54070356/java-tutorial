package laofuzi.java.core;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

public class Random {
	@Test
	public void randomPositiveInt() {
		int randomInt = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
		System.out.println(randomInt);
		
	}
}
