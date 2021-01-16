package laofuzi.java.core.security;

import java.security.Policy;
import java.security.URIParameter;

public class SecurityManagerApp3 {
	public static void main(String[] args) throws Exception{
		URIParameter myPolicyPath = new URIParameter(SecurityPolicyApp.class.getResource("/my2.policy").toURI());
		Policy policy = Policy.getInstance("JavaPolicy", myPolicyPath); 
		Policy.setPolicy(policy);
		System.setSecurityManager(new SecurityManager());
		SecurityCases.readFile();
		
		SecurityCases.localHttpPOST(); 
	}
}
