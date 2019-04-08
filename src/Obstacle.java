/**
 * An obstacle is a GameElement which the bird must try an avoid
 * 
 * @author 10-4
 *
 */
public class Obstacle extends GameElement implements Collidable{
	/**
	 * Implementation of the isOffScreen method in Collidable, will return true if the current instance of an 
	 * Obstacle is off the screen, returns false otherwise
	 */
	public boolean isOffScreen() {
		return true;
	}
}
