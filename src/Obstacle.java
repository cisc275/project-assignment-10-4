import java.io.*;
/**
 * An obstacle is a GameElement which the bird must try an avoid
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Obstacle extends GameElement implements Serializable{
	public Obstacle(int x, int y, int xSpeed, int ySpeed, String imagePath) {
		super(x, y, xSpeed, ySpeed, imagePath);
		// TODO Auto-generated constructor stub
	}
}
