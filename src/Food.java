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
	 * The boolean value indicating whether or not consuming this instance of food will give the bird a power up
	 * True indicates consuming this instance of food will give the bird a power up
	 */
	private boolean specialFood;
	/**
	 * Boolean indicating if the food has been eaten yet
	 */
	private boolean eaten;
	
	/**
	 * Constructor for food
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
	
	@Override
	public boolean collision(Bird bird) {
		bird.setStamina(bird.getStamina()+staminaValue);
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
