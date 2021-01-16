package laofuzi.java.core;

import org.junit.Assert;
import org.junit.Test;

public class StringDemo {

	@Test
	public void slit() { 
		doSplit("a");
		doSplit("a.");
		doSplit("a.b");
		doSplit("哦挂墙上");
		
		Object o = null;
		
		double d1 = 0.9999186573032185;
		System.out.println(1-d1);
		 
	}
	
	@Test
	public void split_1() {
		String[] texts = {
			"额挂挂是挂墙上",
			"嗯，挂在墙上面"
		};
		
		for(String text : texts) {
			System.out.println(text + "=" + do_split_1(text));
			
		}
	}
	
	public String do_split_1(String txt) {
		
		String[] splits = txt.split("嗯|啊|呃|哎|诶|吧|嘛|额|恩");
		StringBuilder builder = new StringBuilder();
		for(String s : splits) {
			builder.append(s)
			.append("|");
		}
		return builder.toString();
	}
	
	@Test
	public void subString() {
		Assert.assertEquals("abc".substring(1,1), "");
		Assert.assertEquals("abc".substring(0,3), "abc");
	}
	
	private void doSplit(String s) {
		String[] parts = s.split("\\.");
		System.out.println();
		System.out.print("len="+parts.length);
		for(String p : parts) {
			System.out.print(" ");
			System.out.print(p);
			
		}
	}
	
	@Test
	public void replace1() {
		System.out.print("挂挂是挂墙上".replace("挂挂", "挂"));
	}
	
	@Test
	public void replace2() {
		String s = "我们是“超人”啊";
		String r = s.replaceAll("[“”]", "");
		System.out.println(r);
	}
}
