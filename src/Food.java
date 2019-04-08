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
	private boolean isSpecialFood;
	/**
	 * Once the bird has had contact with the food then the food will no longer be visible
	 * 
	 * 
	 */
	void eaten() {}
	/**
	 * Implementation of the isOffScreen method in Collidable, will return true if the current instance of food 
	 * is off the screen, returns false otherwise
	 */
	public boolean isOffScreen() {
		return true;
	}
}
