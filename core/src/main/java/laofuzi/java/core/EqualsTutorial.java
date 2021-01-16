package laofuzi.java.core;

import org.junit.Assert;
import org.junit.Test;

public class EqualsTutorial {
	@Test
	public void test_1() {
		String ts = "true";
		boolean t = true;
		Boolean T = t;
		Assert.assertTrue(T == Boolean.valueOf(ts));
		
		Integer one1 = new Integer(1);
		Integer one2 = new Integer(1);
		Assert.assertTrue( "not equal", one1.equals(one2));
	}

}
