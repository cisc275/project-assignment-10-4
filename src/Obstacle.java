import java.io.*;
/**
 * An obstacle is a GameElement which the bird must try an avoid
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Obstacle extends GameElement implements Serializable{
	/**
	 * @param staminaValue - an int representing the negative effect on stamina consuming this obstacle 
	 */
	int staminaValue;
	
   /**
	 * @param staminaValue - an int representing the negative effect on stamina consuming this obstacle 
	 * will give the bird
	 * @param x - an int representing the x location of the obstacle
	 * @param y - an int representing the y location of the obstacle
	 * @param xSpeed - an int representing the horizontal speed of the obstacle
	 * @param ySpeed - an int representing the vertical speed of the obstacle
	 * @param imagePath - a string representing the path of the image of the obstacle
	 */
	public Obstacle(int staminaValue, int x, int y, int xSpeed, int ySpeed, String imagePath) {
		super(x, y, xSpeed, ySpeed, imagePath);
		this.staminaValue = staminaValue;
	}
	/**
	 * @param bird- representing the bird the player is controlling
	 * decrements the bird's stamina upon collision with an obstacle
	 * @return false because the obstacle never has to be removed after collision.
	 */
	@Override
	public boolean collision(Bird bird) {
		if (!bird.isStunned()) {
			bird.setStamina(bird.getStamina()-staminaValue);
			bird.setStunned(true);
		}
		return false;
	}	
}
