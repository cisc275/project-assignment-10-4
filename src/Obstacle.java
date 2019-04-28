import java.io.*;
/**
 * An obstacle is a GameElement which the bird must try an avoid
 * 
 * @author 10-4
 *
 */
public class Obstacle extends GameElement implements Collidable, Serializable{
	/**
	 * Implementation of the isOffScreen method in Collidable, will return true if the current instance of an 
	 * Obstacle is off the screen, returns false otherwise
	 */
	@Override
	public boolean isOffScreen() {
		return ((getXloc()+getWidth()) < 0);
	}
}
