import java.io.*;
/**
 * An obstacle is a GameElement which the bird must try an avoid
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Obstacle extends GameElement implements Serializable{
	int staminaValue;
	
	public Obstacle(int staminaValue, int x, int y, int xSpeed, int ySpeed, String imagePath) {
		super(x, y, xSpeed, ySpeed, imagePath);
		this.staminaValue = staminaValue;
	}

	@Override
	public boolean collision(Bird bird) {
		bird.setStamina(bird.getStamina()-staminaValue);
		return false;
	}	
}
