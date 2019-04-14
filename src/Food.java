/**
 * In the game food has the property that when it is consumed by the bird, it adds stamina and if it is a
 * special piece of food it gives the bird a powerup.
 * 
 * @author 10-4
 *
 */
public class Food extends GameElement implements Collidable{
	/**
	 * The int value of the stamina incease the bird recieves from consuming the food
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
	public Food(int val, boolean special) {
		staminaValue = val;
		specialFood = special;
		eaten = false;
	}
	/**
	 * Once the bird has had contact with the food then the food will no longer be visible
	 */
	void eaten() {
		eaten = true;
	}
	/**
	 * Implementation of the isOffScreen method in Collidable, will return true if the current instance of food 
	 * is off the screen, returns false otherwise
	 */
	public boolean isOffScreen() {
		return true;
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
	 * @return the isSpecialFood
	 */
	public boolean isSpecialFood() {
		return specialFood;
	}
	/**
	 * @param isSpecialFood the isSpecialFood to set
	 */
	public void setSpecialFood(boolean isSpecialFood) {
		this.specialFood = isSpecialFood;
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
