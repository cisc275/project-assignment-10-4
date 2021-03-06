import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines the common attributes and action which all elements that appear in
 * the game (obstacles, food, Bird, minimap) share
 * 
 * @author 10-4
 */
@SuppressWarnings("serial")
public abstract class GameElement implements Serializable {
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
	 * The image of Game Element
	 */
	protected Images image;
	/**
	 * A HashMap that stores the x coordinates for each game element's collision
	 * polygon
	 */
	protected static Map<Images, int[]> xPolyVals = new HashMap<Images, int[]>();
	/**
	 * A HashMap that stores the y coordinates for each game element's collision
	 * polygon
	 */
	protected static Map<Images, int[]> yPolyVals = new HashMap<Images, int[]>();
	/**
	 * The polygon of the GameElement
	 */
	protected Polygon polygon;
	
	/**
	 * y sign flip 
	 */
	private int flip; 
	/**
	 * @param x         an int representing the x location of the GameElement
	 * @param y         an int representing the y location of the GameElement
	 * @param xSpeed    an int representing the horizontal speed of the GameElement
	 * @param ySpeed    an int representing the vertical speed of the GameElement
	 * @param imagePath a string representing the path of the image of the
	 *                  GameElement
	 * @param type      The enumeration type of the game element
	 */
	public GameElement(int x, int y, int xSpeed, int ySpeed, String imagePath, Images type) {
		xloc = x;
		yloc = y;
		this.xSpeed = xSpeed;
		this.ySpeed = 2; 
		this.imagePath = imagePath;
		this.image = type;
		if (type.equals(Images.BIRD) || type.equals(Images.OSPREY) || 
				type.equals(Images.NORTHERN_HARRIER) || type.equals(Images.POWERUP) 
				|| type.equals(Images.POWERUP_OSPREY)) {
			this.width = Images.getCorrespondingImageArray(type)[0].getWidth();
			this.height = Images.getCorrespondingImageArray(type)[0].getHeight();
		} else {
			this.width = Images.getCorrespondingImage(type).getWidth();
			this.height = Images.getCorrespondingImage(type).getHeight();
		}
		if (xPolyVals.isEmpty()) {
			putPolyCoords();
		}
		this.flip = 1; 
	}
	
	/**
	 * Adds the x coordinates and y coordinates of each different type of game
	 * element to their respective hashmap
	 */
	void putPolyCoords() {
		xPolyVals.put(Images.BUILDING, new int[] { 0, 263, 263, 0 });
		yPolyVals.put(Images.BUILDING, new int[] { 0, 0, 675, 675 });
		
		xPolyVals.put(Images.TRASH, new int[] { 21, 9, 8, 1, 4, 1, 16, 38, 58, 78, 81, 81, 73, 72, 49,
				47, 33, 32});
		yPolyVals.put(Images.TRASH, new int[] {2, 6, 31, 54, 75, 95, 98, 102, 94, 95, 77, 53, 29, 4, 2,
				27, 28, 7});

		xPolyVals.put(Images.MOUSE, new int[] { 6, 23, 38, 35, 42, 49, 57, 66, 71, 94, 115, 129, 132, 132, 139, 132,
				116, 67, 53, 63, 54, 44, 44, 60, 62, 46, 32, 33, 50, 49, 38, 21, 9 });
		yPolyVals.put(Images.MOUSE, new int[] { 43, 34, 19, 9, 3, 8, 4, 2, 13, 8, 9, 20, 40, 52, 65, 82, 93, 108, 124,
				139, 140, 130, 114, 102, 89, 92, 85, 77, 68, 58, 48, 48, 47 });

		xPolyVals.put(Images.GOLDENFISH, new int[] { 2, 17, 37, 49, 61, 71, 73, 62, 83, 110, 137, 106, 100, 106, 95, 80,
				63, 69, 56, 47, 32, 17, 5 });
		yPolyVals.put(Images.GOLDENFISH, new int[] { 68, 59, 48, 33, 26, 26, 43, 49, 67, 58, 63, 76, 90, 106, 104, 87,
				102, 107, 112, 104, 100, 85, 79 });

		xPolyVals.put(Images.FISH, new int[] { 1, 15, 33, 47, 64, 85, 82, 71, 88, 105, 138, 114, 109, 118, 110, 101, 92,
				63, 69, 53, 46, 26, 14, 10, 2 });
		yPolyVals.put(Images.FISH, new int[] { 36, 35, 26, 23, 5, 5, 29, 28, 49, 37, 37, 54, 79, 81, 89, 83, 64, 86, 95,
				100, 90, 81, 63, 52, 46 });

		xPolyVals.put(Images.GOLDENMOUSE, new int[] { 9, 13, 26, 39, 37, 42, 50, 56, 63, 69, 73, 99, 117, 129, 131, 128,
				137, 121, 89, 59, 60, 63, 52, 45, 50, 64, 62, 45, 31, 43, 52, 40, 19 });
		yPolyVals.put(Images.GOLDENMOUSE, new int[] { 48, 43, 34, 20, 12, 6, 9, 8, 4, 7, 17, 10, 14, 25, 40, 51, 68, 89,
				99, 113, 129, 137, 136, 124, 106, 98, 90, 90, 83, 71, 61, 51, 49 });

		xPolyVals.put(Images.EAGLE,
				new int[] { 135, 154, 187, 223, 232, 255, 249, 283, 272, 288, 265, 292, 275, 285, 271, 246, 236, 209,
						179, 190, 232, 227, 210, 191, 185, 175, 158, 147, 129, 130, 117, 111, 121, 130, 138, 141, 133,
						129, 120, 118, 109, 105, 84, 69, 60, 45, 30, 10, 26, 7, 22, 7, 23, 44, 80, 109, 124 });
		yPolyVals.put(Images.EAGLE,
				new int[] { 98, 91, 52, 27, 28, 10, 27, 4, 25, 19, 40, 38, 48, 50, 64, 89, 95, 120, 123, 135, 138, 151,
						159, 158, 154, 159, 183, 185, 195, 187, 189, 168, 167, 173, 161, 150, 138, 126, 126, 137, 135,
						130, 136, 138, 136, 130, 114, 102, 104, 85, 91, 69, 82, 86, 89, 91, 98 });

		xPolyVals.put(Images.OWL,
				new int[] { 6, 17, 34, 57, 75, 88, 92, 84, 87, 94, 102, 110, 111, 123, 130, 124, 142, 132, 155, 140,
						161, 148, 164, 160, 149, 126, 110, 87, 88, 105, 122, 107, 85, 64, 45, 28, 23, 8, 13, 3, 11, 23,
						33, 37, 32, 23, 13, 10, 19, 37, 27, 15 });
		yPolyVals.put(Images.OWL,
				new int[] { 13, 10, 20, 34, 53, 75, 73, 40, 11, 25, 11, 5, 22, 12, 10, 28, 19, 42, 35, 54, 56, 65, 74,
						97, 115, 128, 128, 128, 139, 150, 181, 190, 195, 192, 182, 173, 165, 150, 144, 138, 133, 139,
						139, 134, 120, 112, 95, 82, 69, 67, 57, 42 });

		xPolyVals.put(Images.FOX,
				new int[] { 6, 23, 31, 40, 39, 50, 66, 77, 119, 130, 162, 215, 241, 261, 275, 285, 281, 315, 358, 397,
						398, 430, 462, 485, 498, 465, 439, 440, 412, 374, 362, 347, 333, 326, 289, 299, 311, 322, 338,
						352, 352, 330, 311, 320, 320, 304, 306, 311, 301, 270, 247, 232, 177, 147, 98, 92, 87, 79, 83,
						77, 50, 37, 26, 39, 63, 84, 96, 69, 53, 30, 17 });
		yPolyVals.put(Images.FOX,
				new int[] { 70, 62, 46, 38, 7, 5, 18, 31, 41, 39, 44, 39, 43, 52, 46, 35, 53, 40, 34, 35, 42, 47, 60,
						73, 80, 83, 82, 89, 95, 95, 98, 97, 93, 97, 77, 102, 113, 115, 140, 151, 164, 144, 144, 169,
						190, 190, 182, 174, 149, 132, 114, 91, 101, 104, 141, 168, 175, 168, 136, 127, 137, 157, 153,
						130, 119, 108, 103, 83, 78, 81, 83 });

	}

	/**
	 * Will update the location of the GameElement
	 * @param speedAdjust the amount to add to the base speed for this tick
	 * 
	 */
	void update(int speedAdjust) {
		xloc -= (xSpeed + speedAdjust * 2);
		if (imagePath.equals("images/owl_1080.png") || imagePath.equals("images/eagle_1080.png")) {
			yloc -= flip*(ySpeed); 
			polygon.translate(-(xSpeed + speedAdjust * 2), -flip*(ySpeed));
		} 
		else {
			polygon.translate(-(xSpeed + speedAdjust * 2), 0);
		} 
	}

	/**
	 * Corrects the Polygon of the GameElement if there has been a change in the
	 * objects location
	 */
	public void fixPolygon() {
		this.polygon.reset();
		int[] x = xPolyVals.get(this.image);
		int[] y = yPolyVals.get(this.image);
		polygon = new Polygon(x, y, x.length);
		polygon.translate(this.xloc, this.yloc);
	}
	/** When a game element hits the top or the bottom of the screen it goes in the opposite y direction
	 * 
	 */
	public void flipSign() {
		flip = (-1)*flip; 
	}
	/**
	 * @return the xloc of the gameElement
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
		if (polygon != null) {
			this.fixPolygon();
		}
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
	public void setImage(Images img) {
		image = img;
		setWidth(Images.getCorrespondingImage(image).getWidth());
		setHeight(Images.getCorrespondingImage(image).getHeight());
	}

	/**
	 * @return Images
	 */
	public Images getImage() {
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

	/**
	 * Sets the type of the object and creates the polygon of this particular game
	 * element type
	 * 
	 * @param i the GameElement type to become
	 */
	public void setType(Images i) {
		this.image = i;
		if (this.image != Images.OSPREY_MINIMAP && this.image != Images.NH_MINIMAP
				&& this.image != Images.OSPREY && this.image != Images.NORTHERN_HARRIER && this.image != Images.BIRD) {
			int[] x = xPolyVals.get(this.image);
			int[] y = yPolyVals.get(this.image);
			polygon = new Polygon(x, y, x.length);
			polygon.translate(this.xloc, this.yloc);
		}
	}

	/**
	 * 
	 * @return the type of the GameElement
	 */
	public Images getType() {
		return this.image;
	}

	/**
	 * @return a bounded rectangle around the gameElement
	 */
	public Rectangle getBounds() {
		return new Rectangle(xloc, yloc, width, height);
	}

	/**
	 * 
	 * @return the polygon
	 */
	public Polygon polyBounds() {
		return polygon;
	}

	/**
	 * Basic isOffScreen, will return true if the current instance of an Obstacle is
	 * off the screen, returns false otherwise
	 * 
	 * @return true if the GameElement is off the screen
	 */
	public boolean isOffScreen() {
		return ((getXloc() + getWidth()) < 0);
	}

	/**
	 * Basic collision behavior handler. Called whenever the GE runs into the Bird.
	 * 
	 * @return boolean indicates if the GameElement should be removed from screen
	 */
	public abstract boolean collision(Bird bird);

	/**
	 * @return false because this method will only ever be called if this particular
	 *         gameElement is not food. And if the GameElement is not food then it
	 *         cannot ever be a specialFood.
	 */
	public boolean getSpecialFood() {
		return false;
	}

	/**
	 * @return the pointValue
	 */
	public abstract int getPointValue();

	/**
	 * @return the flip
	 */
	public int getFlip() {
		return flip;
	}

	/**
	 * @param flip the flip to set
	 */
	public void setFlip(int flip) {
		this.flip = flip;
	}
}
