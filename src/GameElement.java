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
	/**
	 * @return the xloc
	 */
	public int getXloc() {
		return xloc;
	}
	/**
	 * @param xloc the xloc to set
	 */
	public void setXloc(int xloc) {
		this.xloc = xloc;
	}
	/**
	 * @return the yloc
	 */
	public int getYloc() {
		return yloc;
	}
	/**
	 * @param yloc the yloc to set
	 */
	public void setYloc(int yloc) {
		this.yloc = yloc;
	}
	/**
	 * @return the spriteFile
	 */
	public String getSpriteFile() {
		return spriteFile;
	}
	/**
	 * @param spriteFile the spriteFile to set
	 */
	public void setSpriteFile(String spriteFile) {
		this.spriteFile = spriteFile;
	}
	/**
	 * @return the xSpeed
	 */
	public int getxSpeed() {
		return xSpeed;
	}
	/**
	 * @param xSpeed the xSpeed to set
	 */
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	/**
	 * @return the ySpeed
	 */
	public int getySpeed() {
		return ySpeed;
	}
	/**
	 * @param ySpeed the ySpeed to set
	 */
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
}
