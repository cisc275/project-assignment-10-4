
public class Food extends GameElement implements Collidable{
	private int staminaValue;
	private boolean isSpecialFood;
	
	void eaten() {}
	
	public boolean isOffScreen() {
		return true;
	}
}
