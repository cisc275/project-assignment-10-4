import java.io.*;
/**
 * In the game food has the property that when it is consumed by the bird, it adds stamina and if it is a
 * special piece of food it gives the bird a powerup.
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Food extends GameElement implements Serializable{
	/**
	 * The int value of the stamina increase the bird receives from consuming the food
	 */
	private int staminaValue;
	/**
	 * The boolean value indicating whether or not consuming this instance of food will 
	 * give the bird a power up.  True indicates consuming this instance of food will give the 
	 * bird a power up
	 */
	private boolean specialFood;
	/**
	 * Boolean indicating if the food has been eaten yet
	 */
	private boolean eaten;
	/**
	 * Constructor for food
	 * @param val - an int representing the stamina benefit consuming this piece of food will give the bird
	 * @param special - an boolean representing whether of not the food will give the bird a chance to get a 
	 * a power up if the food is consumed
	 * @param x - an int representing the x location of the food
	 * @param y - an int representing the y location of the food
	 * @param xSpeed - an int representing the horizontal speed of the food
	 * @param ySpeed - an int representing the vertical speed of the food
	 * @param imagePath - a string representing the path of the image of the food
	 */
	public Food(int val, boolean special, int x, int y, int xSpeed, int ySpeed, String imagePath) {
	    super(x, y, xSpeed, ySpeed, imagePath);
		staminaValue = val;
		specialFood = special;
		eaten = false;
		xloc = x; 
		yloc = y; 
		this.xSpeed = xSpeed; 
		this.ySpeed = ySpeed; 
		this.imagePath = imagePath;
	}
	/**
	 * increases the bird stamina by the staminaValue in the food that the bird collided with
	 * @return a boolean representing whether a the food that the bird collided with needs to be 
	 * removed from the list of collidables.
	 * @param bird- the bird the player is controlling
	 */
	@Override
	public boolean collision(Bird bird) {
		if((bird.getStamina()+staminaValue)<10) {
			bird.setStamina(bird.getStamina()+staminaValue);
		}else {
			bird.setStamina(10);
		}
		return true;
	}
	
	/**
	 * Once the bird has had contact with the food then the food will no longer be visible
	 */
	void eaten() {
		eaten = true;
	}
	
	/**
	 * @return the staminaValue
	 */
	public int getStaminaValue() {
		return staminaValue;
	}
	/**
	 * @param staminaValue the staminaValue to set
	 */
	public void setStaminaValue(int staminaValue) {
		this.staminaValue = staminaValue;
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
	 * @return the eaten
	 */
	public boolean isEaten() {
		return eaten;
	}
	/**
	 * @param eaten the eaten to set
	 */
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
	
}
