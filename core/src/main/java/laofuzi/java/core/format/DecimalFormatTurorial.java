package laofuzi.java.core.format;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;
 

public class DecimalFormatTurorial {
	@Test
	public void testFormat() {
		DecimalFormat f = new DecimalFormat("#.####");
		Assert.assertEquals("3.14", f.format(3.14));
		Assert.assertEquals("3.1414", f.format(3.14135));
		Assert.assertEquals("0.99", f.format(0.99));
		Assert.assertEquals("2.99", f.format(2.99));
		Assert.assertEquals("2", f.format(2.0));
	}
}
