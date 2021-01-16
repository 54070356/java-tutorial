package laofuzi.java.core.bit;

import org.junit.Assert;
import org.junit.Test;

public class BitOperationTest {
	@Test
	public void test1() {
		byte b1 = 100, b2 = 50, b3 = 30;
		
		int encoded = (b1 << 16 + b2<<8 + b3);
		int sum = b1*0xffff+b2*0xff+b3;
		Assert.assertEquals(0x640000, 100<<16);
		Assert.assertEquals(0x3200, b2<<8);
		Assert.assertEquals(0x64321e, (b1 << 16) + (b2<<8) + b3);
	}
	
	@Test
	public void test2() {
		byte b1 = 100, b2 = 50, b3 = 30;
		int temp = 0x64321e;
		System.out.println(Integer.toHexString(temp & 0x0000ff));  
		System.out.println(Integer.toHexString((temp>>8) & 0x0000ff));  
		System.out.println(Integer.toHexString((temp>>16) & 0x0000ff));  
		
		System.out.println(Integer.toHexString(0x1e>>8));  
		System.out.println(Integer.toHexString(0x64<<16));  
		System.out.println(Integer.toHexString(0x640000+0x3200+0x1e));  
	}
		
}
