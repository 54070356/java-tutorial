package laofuzi.java.core;

import org.junit.Assert;
import org.junit.Test;

 

public class EnumTutorial {
	
	@Test
	public void test_valueOf() {
		EnumSimple r = EnumSimple.valueOf("A");
		Assert.assertEquals(EnumSimple.A, r);
		
		try {
			EnumSimple r2 = EnumSimple.valueOf("a");
			Assert.assertNull(r2);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		try {
			EnumSimple r3 = EnumSimple.valueOf(null);
			Assert.assertNull(r3);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}

enum EnumSimple {
	A,
	B
}
