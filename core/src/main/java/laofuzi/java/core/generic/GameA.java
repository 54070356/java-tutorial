package laofuzi.java.core.generic;

import java.util.Map;

public class GameA implements Game{
	private String name = "game a";
	
	public GameA() {
		
	}
	public GameA(Map<String, Object> map) {
		this.name = map.get("name").toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
 
	 

}
