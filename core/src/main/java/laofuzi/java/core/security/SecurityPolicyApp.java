package laofuzi.java.core.security;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Policy;
import java.security.URIParameter;
import java.util.HashMap;
import java.util.Map;

public class SecurityPolicyApp {
	public static void main(String[] args) throws Exception {
		// create a new url class loader, this can be used to load a jar or classes
		// directory
		URL pluginClass = new File("./myplugin/").toURI().toURL();
		URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { pluginClass },
				SecurityPolicyApp.class.getClassLoader());

		// load a dynamic TestClass class
		Class<?> loadedMyClass = urlClassLoader.loadClass("laofuzi.java.core.security.PolicyTest");

		// read my plugin security policy
		URIParameter myPolicyPath = new URIParameter(SecurityPolicyApp.class.getResource("/dynamicClass.policy").toURI());
		Policy policy = Policy.getInstance("JavaPolicy", myPolicyPath);

		Map<String, Policy> plugins = new HashMap<>();
		System.out.println(pluginClass.getPath());
		plugins.put(pluginClass.getPath(), policy);
		MyPolicy myPolicy = new MyPolicy(plugins);
		Policy.setPolicy(myPolicy);
		// install the security manager
		System.setSecurityManager(new SecurityManager());
		SecurityCases.localHttpGet();

		System.out.println("Loaded class: " + loadedMyClass.getName());

		Object myClassObject = loadedMyClass.getConstructor().newInstance();
		Method method = loadedMyClass.getMethod("foobar");
		System.out.println("Invoked method: " + method.getName());
		method.invoke(myClassObject);
		urlClassLoader.close();
	}
	
	
}
