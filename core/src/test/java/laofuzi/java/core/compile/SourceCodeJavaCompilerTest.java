package laofuzi.java.core.compile;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Policy;
import java.security.URIParameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import laofuzi.java.core.security.MyPolicy;
import laofuzi.java.core.security.SecurityPolicyApp;

public class SourceCodeJavaCompilerTest {
	@Test
	public void testCompile() throws Exception {

		SourceCodeJavaCompiler compiler = new SourceCodeJavaCompiler(this.getClass().getClassLoader(),
				Arrays.asList("-target", "1.8"));

		Path path = Paths.get("./src/main/resources/PolicyTest2");
		String lines = Files.readAllLines(path).stream().collect(Collectors.joining(" "));
		Class<?> clazz = compiler.compile(lines);

		// read my plugin security policy
		URIParameter myPolicyPath = new URIParameter(SecurityPolicyApp.class.getResource("/dynamicClass.policy").toURI());
		Policy policy = Policy.getInstance("JavaPolicy", myPolicyPath);

		Map<String, Policy> plugins = new HashMap<>();
		plugins.put("/SCRIPT_SOURCE", policy);
		MyPolicy myPolicy = new MyPolicy(plugins);
		Policy.setPolicy(myPolicy);
		// install the security manager
		System.setSecurityManager(new SecurityManager());

		Object instance = clazz.newInstance();
		Method method = clazz.getMethod("foobar");
		System.out.println("Invoked method: " + method.getName());
		method.invoke(instance);

	}
}
