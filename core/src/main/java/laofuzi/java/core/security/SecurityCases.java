package laofuzi.java.core.security;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SecurityCases {
	public static void foobar() throws IOException {
		Path path = Paths.get("./test.text");
		String lines = Files.readAllLines(path).stream().collect(Collectors.joining(","));
		System.out.println(lines);
	}
	
	public static void readFile()  throws Exception {
		Path path = Paths.get("./test.text");
        String lines = Files.readAllLines(path).stream().collect(Collectors.joining(","));
        System.out.println(lines);
	}

	public static void readLocalFile() throws Exception {
		// create a new url class loader, this can be used to load a jar or classes
		// directory
		URL pluginClass = new File("./myplugin/").toURI().toURL();
		URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { pluginClass },
				SecurityPolicyApp.class.getClassLoader());

		// load a dynamic TestClass class
		Class<?> loadedMyClass = urlClassLoader.loadClass("laofuzi.java.core.security.PolicyTest");
		System.out.println("Loaded class: " + loadedMyClass.getName());

		Object myClassObject = loadedMyClass.getConstructor().newInstance();
		Method method = loadedMyClass.getMethod("foobar");
		System.out.println("Invoked method: " + method.getName());
		method.invoke(myClassObject);
		urlClassLoader.close();
	}

	public static void localHttpGet() {
		String url = "http://localhost:8080/simple/1";

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(url).build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
			String respTxt = response.body().string();
			System.out.println(respTxt);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			if (response != null) {
				// response.c
			}
		}

	}
	
	public static void localHttpPOST() {
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		String url = "http://localhost:8080/simple/1";
		String body = "{\"id\":\"1\",\"name\":\"Tom\"}";

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(url).post(RequestBody.create(JSON, body)).build();

		Response response = null;
		try {
			response = client.newCall(request).execute();
			String respTxt = response.body().string();
			System.out.println(respTxt);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			if (response != null) {
				// response.c
			}
		}

	}
}
