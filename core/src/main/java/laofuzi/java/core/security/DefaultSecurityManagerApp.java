package laofuzi.java.core.security;

import java.security.Policy;
import java.util.Collections;

public class DefaultSecurityManagerApp {
	public static void main(String[] args) throws Exception{ 
//		Policy policy = Policy.getInstance("JavaPolicy", myPolicyPath);
		MyPolicy myPolicy = new MyPolicy(Collections.emptyMap());
		Policy.setPolicy(myPolicy);
		System.setSecurityManager(new SecurityManager());
		SecurityCases.readFile();
		//SecurityCases.localHttpGet();
		
		SecurityCases.localHttpPOST();
		
		Thread.sleep(1000000);
	}
}
