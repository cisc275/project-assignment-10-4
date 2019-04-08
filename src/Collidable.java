/**
 * Defines the common method which all elements of the game that are capable of colliding with the birds must implement
 * 
 * 
 * @author 10-4
 *
 */

public interface Collidable {
	/**
	 * Will return true the object in question (an Obstacle or Food) is out of the visible screen
	 * 
	 * 
	 */
	boolean isOffScreen();
}
