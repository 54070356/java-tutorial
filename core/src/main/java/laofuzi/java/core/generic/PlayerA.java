package laofuzi.java.core.generic;

public class PlayerA implements Player<GameA>{
	
	
	@Override
	public GameA getGame() { 
		return new GameA();
	}

	@Override
	public Class<GameA> getValueClass() {
		return GameA.class;
	}

	@Override
	public <W> W getPet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <W> void setPet(W pet) {
		// TODO Auto-generated method stub
		
	}
	
	

}
