/**
 * Defines the common attributes and action which all elements that appear 
 * in the game (obstacles, food, Bird, minimap) share
 * 
 * @author 10-4
 *
 */
public class GameElement {
	/**
	 * The current x-coordinate of the GameElement
	 */
	private int xloc;
	/**
	 * The current y-coordinate of the GameElement
	 */
	private int yloc;
	/**
	 * The name of the file containing the Sprite of the GameElement
	 */
	private String spriteFile;
	/**
	 * The current x-axis speed of the GameElement
	 */
	private int xSpeed;
	/**
	 * The current y-axis speed of the GameElement
	 */
	private int ySpeed;
	/**
	 * Will update the location of the GameElement
	 */
	void updatePosition() {}
}
