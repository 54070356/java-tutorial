package laofuzi.java.core.generic;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {
		Player<?> player = new PlayerA();
		
		Game game = player.getGame();
		
		System.out.println(game.getName());
		
		Class<?> clazz = player.getValueClass();
		
		System.out.println(clazz.getName());
		
		Constructor<?> c = clazz.getDeclaredConstructor(Map.class);
		System.out.println(c.getName());
		
		Map<String, Object> values = new HashMap<>();
		values.put("name", "this is game 1");
		
		Game newGame = (Game) c.newInstance(values);
		System.out.println(newGame.getName());
		System.out.println(newGame.getClass().getName());
		
		
	}
}
