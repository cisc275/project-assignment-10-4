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
	protected int xloc;
	/**
	 * The current y-coordinate of the GameElement
	 */
	protected int yloc;
	/**
	 * The name of the file containing the Sprite of the GameElement
	 */
	protected String spriteFile;
	/**
	 * The width in pixels of the game element's image
	 */
	protected int width;
	/**
	 * The height in pixels of the game element's image
	 */
	protected int height;
	/**
	 * The current x-axis speed of the GameElement
	 */
	protected int xSpeed;
	/**
	 * The current y-axis speed of the GameElement
	 */
	protected int ySpeed;
	
	/**
	 * Will update the location of the GameElement
	 */
	void updatePosition() {
		xloc -= xSpeed;
	}
	
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
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
