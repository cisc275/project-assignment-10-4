import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines the common attributes and action which all elements that appear 
 * in the game (obstacles, food, Bird, minimap) share
 * 
 * @author 10-4
 */
@SuppressWarnings("serial")
public abstract class GameElement implements Serializable{
	/**
	 * The path of the image this GameElement
	 */
	protected String imagePath;
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
	 * The image of the GameElement
	 */
	protected BufferedImage image;
	
	protected Images type;
	
	protected static Map<Images,int[]>xPolyVals = new HashMap<Images,int[]>();
	protected static Map<Images,int[]>yPolyVals = new HashMap<Images,int[]>();
;
	 
	/**
	 * @param x - an int representing the x location of the GameElement
	 * @param y - an int representing the y location of the GameElement
	 * @param xSpeed - an int representing the horizontal speed of the GameElement
	 * @param ySpeed - an int representing the vertical speed of the GameElement
	 * @param imagePath - a string representing the path of the image of the GameElement
	 */
	public GameElement(int x, int y, int xSpeed, int ySpeed, String imagePath, Images type) {
		xloc = x; 
		yloc = y; 
		this.xSpeed = xSpeed; 
		this.ySpeed = ySpeed; 
		this.imagePath = imagePath;
		this.type = type;
		if(xPolyVals.isEmpty()) {
			putPolyCoords();
		}
	}
	
	void putPolyCoords() {
		xPolyVals.put(Images.BUILDING,new int[]{0,263,263,0});
		yPolyVals.put(Images.BUILDING,new int[]{0,0,675,675});
		System.out.println(xPolyVals.get(Images.BUILDING).length == yPolyVals.get(Images.BUILDING).length);
		
		xPolyVals.put(Images.MOUSE,new int[]{6,23,38,35,42,49,57,66,71,94,115,129,132,132,139,132,116,67,53,63,54,44,44,60,62,46,32,33,50,49,38,21,9});
		yPolyVals.put(Images.MOUSE,new int[]{43,34,19,9,3,8,4,2,13,8,9,20,40,52,65,82,93,108,124,139,140,130,114,102,89,92,85,77,68,58,48,48,47});
		System.out.println(xPolyVals.get(Images.MOUSE).length == yPolyVals.get(Images.MOUSE).length);
		
		xPolyVals.put(Images.GOLDENFISH,new int[]{});
		yPolyVals.put(Images.GOLDENFISH,new int[]{});
		System.out.println(xPolyVals.get(Images.GOLDENFISH).length == yPolyVals.get(Images.GOLDENFISH).length);
		
		xPolyVals.put(Images.FISH,new int[]{});
		yPolyVals.put(Images.FISH,new int[]{});
		System.out.println(xPolyVals.get(Images.FISH).length == yPolyVals.get(Images.FISH).length);
		
		xPolyVals.put(Images.GOLDENMOUSE,new int[]{});
		yPolyVals.put(Images.GOLDENMOUSE,new int[]{});
		System.out.println(xPolyVals.get(Images.GOLDENMOUSE).length == yPolyVals.get(Images.GOLDENMOUSE).length);
		
		xPolyVals.put(Images.EAGLE,new int[]{});
		yPolyVals.put(Images.EAGLE,new int[]{});
		System.out.println(xPolyVals.get(Images.EAGLE).length == yPolyVals.get(Images.EAGLE).length);
		
		xPolyVals.put(Images.OWL,new int[]{});
		yPolyVals.put(Images.OWL,new int[]{});
		System.out.println(xPolyVals.get(Images.OWL).length == yPolyVals.get(Images.OWL).length);
		
		xPolyVals.put(Images.FOX,new int[]{});
		yPolyVals.put(Images.FOX,new int[]{});
		System.out.println(xPolyVals.get(Images.FOX).length == yPolyVals.get(Images.FOX).length);
	}
	
	
	/**
	 * Will update the location of the GameElement
	 * 
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
	/**
	 * @param a BufferedImage img of the GameElement 
	 */
	public void setImage(BufferedImage img) {
		image = img;
		setWidth(image.getWidth());
		setHeight(image.getHeight());
	}
	/**
	 * @return BufferedImage
	 */
	public BufferedImage getImage() {
		return image;
	}
	/**
	 * @param a String representing the ImagePath of the GameElement 
	 */
	public void setImagePath(String ImagePath) {
		this.imagePath = ImagePath;
	}
	/**
	 * @return a String representing the ImagePath of the GameElement
	 */
	public String getImagePath() {
		return imagePath;
	}
	
	public void setType(Images i) {
		this.type = i;
	}
	
	public Images getType() {
		return this.type;
	}
	
	
	/**
	 * @return a bounded rectangle around the gameElement
	 */
	public Rectangle getBounds() {
		return new Rectangle(xloc,yloc,width,height);
	}
	
	public Polygon polyBounds() {
		// x coordinates of vertices
		int x[] = { 10, 30, 40, 50, 110, 140 }; 
		  
        // y coordinates of vertices 
        int y[] = { 140, 110, 50, 40, 30, 10 }; 
  
        // number of vertices 
        int numberofpoints = 6; 
  
        // create a polygon with given x, y coordinates 
        Polygon p = new Polygon(x, y, numberofpoints); 
        return p;
	}
	
	/**
	 * Basic isOffScreen, will return true if the current instance of an 
	 * Obstacle is off the screen, returns false otherwise
	 * @return true if the GameElement is off the screen
	 */
	public boolean isOffScreen() {
		return ((getXloc()+getWidth()) < 0);
	}
	
	/**
	 * Basic collision behavior handler. Called whenever the GE runs into the Bird.
	 * @return boolean indicates if the GameElement should be removed from screen
	 */
	public abstract boolean collision(Bird bird);
	
	/**
	 * @return false because this method will only ever be called if this particular gameElement is not
	 * food.  And if the GameElement is not food then it cannot ever be a specialFood.
	 */
	public boolean getSpecialFood() {
		return false;  
	}
}
