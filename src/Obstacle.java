import java.io.*;

/**
 * An obstacle is a GameElement which the bird must try an avoid
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Obstacle extends GameElement implements Serializable {
	/**
	 * Constant representing the negative effect on stamina of hitting this obstacle
	 */
	private static final int STAMINA_VALUE = 1;
	/**
	 * Constant representing the negative effect of points of hitting this obstacle
	 */
	private static final int POINT_VALUE = -100;

	/**
	 * @param x             an int representing the x location of the obstacle
	 * @param y             an int representing the y location of the obstacle
	 * @param xSpeed        an int representing the horizontal speed of the obstacle
	 * @param ySpeed        an int representing the vertical speed of the obstacle
	 * @param imagePath     a string representing the path of the image of the
	 *                      obstacle
	 * @param type			the enumeration type for the obstacle
	 */
	public Obstacle(int x, int y, int xSpeed, int ySpeed, String imagePath, Images type) {
		super(x, y, xSpeed, ySpeed, imagePath, type);
	}

	/**
	 * @param bird representing the bird the player is controlling decrements the
	 *             bird's stamina upon collision with an obstacle
	 * @return false because the obstacle never has to be removed after collision.
	 */
	@Override
	public boolean collision(Bird bird) {
		if(bird.isPoweredUp() && (bird.getPowerTimer().getState() <=5)) {
			bird.setPowerTimer(bird.getStunTimer().getState() + 5);
		}
		if(bird.isStunned() && (bird.getStunTimer().getState() <= 5)) {
			bird.setStunTimer(bird.getStunTimer().getState() + 5);
		}
		if (!bird.isStunned() && !bird.isPoweredUp()) {
			bird.setStamina(bird.getStamina() - STAMINA_VALUE);
			bird.setStunned(true);
		}
		return false;
	}

	/**
	 * Overrides the GameElement getPointValue() method to return this
	 * element's point value to the constant value assigned
	 * 
	 * @return integer representing point value
	 */
	@Override
	public int getPointValue() {
		return POINT_VALUE;
	}
}
