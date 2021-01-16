package laofuzi.java.core.generic;

public interface Player <T extends Game>{
	public T getGame();
	public Class<T> getValueClass();
	
	public <W> W getPet();
	public <W> void setPet(W pet);
}
