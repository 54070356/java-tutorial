package laofuzi.java.core;

import org.junit.Assert;
import org.junit.Test;

public class BooleanTutorial {
	@Test
	public void equals() {
		String ts = "true";
		boolean t = true;
		Boolean T = t;
		Assert.assertTrue(T == Boolean.valueOf(ts));
	}
}
