import java.io.*;

/**
 * In the game food has the property that when it is consumed by the bird, it
 * adds stamina and if it is a special piece of food it gives the bird a powerup.
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Food extends GameElement implements Serializable {
	/**
	 * The int value of the stamina increase the bird receives from consuming the
	 * food
	 */
	private static final int STAMINA_VALUE = 1;
	/**
	 * Constant representing the negative effect of points of hitting this obstacle
	 */
	private static final int POINT_VALUE = 100;
	/**
	 * The boolean value indicating whether or not consuming this instance of food
	 * will give the bird a power up. True indicates consuming this instance of food
	 * will give the bird a power up
	 */
	private boolean specialFood;
	/**
	 * Boolean indicating if the food has been eaten yet
	 */
	private boolean eaten;

	/**
	 * Constructor for food
	 * 
	 * @param special   a boolean representing whether of not the food will give
	 *                  the bird a chance to get a a power up if the food is
	 *                  consumed
	 * @param x         an int representing the x location of the food
	 * @param y         an int representing the y location of the food
	 * @param xSpeed    an int representing the horizontal speed of the food
	 * @param ySpeed    an int representing the vertical speed of the food
	 * @param imagePath a string representing the path of the image of the food
	 * @param type      The enumeration type of the game element
	 */
	public Food(boolean special, int x, int y, int xSpeed, int ySpeed, String imagePath, Images type) {
		super(x, y, xSpeed, ySpeed, imagePath, type);
		specialFood = special;
		eaten = false;
		xloc = x;
		yloc = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.imagePath = imagePath;
	}

	/**
	 * increases the bird stamina by the STAMINA_VALUE in the food that the bird
	 * collided with
	 * 
	 * @return a boolean representing whether a the food that the bird collided with
	 *         needs to be removed from the list of collidables.
	 * @param bird the bird the player is controlling
	 */
	@Override
	public boolean collision(Bird bird) {
		if (!bird.isStunned()) {
			bird.setStamina(bird.getStamina() + STAMINA_VALUE);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Once the bird has had contact with the food then the food will no longer be
	 * visible
	 */
	void eaten() {
		eaten = true;
	}

	/**
	 * @return the STAMINA_VALUE
	 */
	public int getStaminaValue() {
		return STAMINA_VALUE;
	}

	/**
	 * @return the specialFood
	 */
	public boolean getSpecialFood() {
		return specialFood;
	}

	/**
	 * @param specialFood the specialFood to set
	 */
	public void setSpecialFood(boolean specialFood) {
		this.specialFood = specialFood;
	}

	/**
	 * @return the eaten food
	 */
	public boolean isEaten() {
		return eaten;
	}

	/**
	 * @param eaten 	the eaten to set
	 */
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
	
	/**
	 * @return the constant POINT_VALUE
	 */
	@Override
	public int getPointValue() {
		return POINT_VALUE;
	}

}
