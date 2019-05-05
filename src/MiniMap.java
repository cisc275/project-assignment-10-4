import java.awt.image.BufferedImage;
import java.io.*;
/**
 *Provides the image showing the birds progress through its migratory journey.  The minimap is different,
 *depending on if the Osprey or the Northern Harrier is the current bird.  The birds progress on the minimap is 
 *dependent on the amount of food the bird has eaten
 * 
 * @author 10-4
 *
 */

@SuppressWarnings("serial")
public class MiniMap extends GameElement implements Serializable{
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap has to travel 
	 * during migration in the x direction for the Osprey
	 */
	private static final int OSPREY_SMALL_BIRD_TOTAL_X_DIST = 42;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap has to travel 
	 * during migration in the y direction for the Osprey
	 */
	private static final int OSPREY_SMALL_BIRD_TOTAL_Y_DIST = 84;
	/**
	 * The constant int value of the x location of the small bird Sprite on the minimap's starting position
	 * for the Osprey
	 */
	static final int OSPREY_INITIAL_SMALL_BIRD_X_LOC = 1819;   //1819;
	/**
	 * The constant int value of the y location of the small bird Sprite on the minimap's starting position
	 * for the Osprey
	 */
	static final int OSPREY_INITIAL_SMALL_BIRD_Y_LOC = 110;
	/**
	 * The constant int value of the x location of the small bird Sprite on the minimap's starting position
	 * for the Northern Harrier
	 */
	static final int NH_INITIAL_SMALL_BIRD_X_LOC = 1805; //1819;
	/**
	 * The constant int value of the y location of the small bird Sprite on the minimap's starting position
	 * for the Northern Harrier
	 */
	static final int NH_INITIAL_SMALL_BIRD_Y_LOC = 140;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap has to travel 
	 * during migration in the x direction for the Northern Harrier
	 */
	private static final int NH_SMALL_BIRD_TOTAL_X_DIST = 30;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap has to travel 
	 * during migration in the y direction for the Northern Harrier
	 */
	private static final int NH_SMALL_BIRD_TOTAL_Y_DIST = 110;
	/**
	 * The int value of the X location of the small bird Sprite on the minimap 
	 */
	private int mapXLoc;
	/**
	 * The int value of the Y location of the small bird Sprite on the minimap 
	 */
	private int mapYLoc;
	/**
	 * The String of the file containing the Sprite corresponding to either the Northern Harrier or the Osprey,
	 * depending on what the current bird is.  This is the Sprite that will be placed on the miniMap
	 */
	private String mapSpriteFile;
	/**
	 * This is the bufferedImage that will be placed on the miniMap to represent the current location of the bird
	 */
	private BufferedImage smallBird;
	/**
	 * @param x - an int representing the x location of the GameElement
	 * @param y - an int representing the y location of the GameElement
	 * @param xSpeed - an int representing the horizontal speed of the GameElement
	 * @param ySpeed - an int representing the vertical speed of the GameElement
	 * @param imagePath - a string representing the path of the image of the GameElement
	 * @param mapSpriteFile - a string of the file containing the Sprite corresponding to either 
	 * the Northern Harrier or the Osprey
	 * @param mapXLoc - an int value of the X location of the small bird (either the Northern Harrier 
	 * or the Osprey) on the minimap
	 * @param mapYLoc - an int value of the y location of the small bird (either the Northern Harrier 
	 * or the Osprey) on the minimap
	 */
		
	public MiniMap(int x, int y, int xSpeed, int ySpeed, String imagePath, String mapSpriteFile ,int mapXLoc, int mapYLoc) {
		super(x, y, xSpeed, ySpeed, imagePath, null);
		this.mapSpriteFile = mapSpriteFile;
		this.mapXLoc = mapXLoc;
		this.mapYLoc = mapYLoc;	
	}
	/**
	 * @return the mapXLoc
	 */
	public int getMapXLoc() {
		return mapXLoc;
	}
	/**
	 * @param mapXLoc the mapXLoc to set
	 */
	public void setMapXLoc(int mapXLoc) {
		this.mapXLoc = mapXLoc;
	}
	/**
	 * @return the mapYLoc
	 */
	public int getMapYLoc() {
		return mapYLoc;
	}
	/**
	 * @param mapYLoc the mapYLoc to set
	 */
	public void setMapYLoc(int mapYLoc) {
		this.mapYLoc = mapYLoc;
	}
	/**
	 * @return the mapSpriteFile
	 */
	public String getMapSpriteFile() {
		return mapSpriteFile;
	}
	/**
	 * @return the BufferedImage of the small bird showing the player's progress on the minimap
	 */
	public BufferedImage getSmallBird() {
		return smallBird;
	}
	
	public void setSmallBird(BufferedImage smallBird) {
		this.smallBird= smallBird;
	}
	
	/**
	 * @param mapSpriteFile the mapSpriteFile to set
	 */
	public void setMapSpriteFile(String mapSpriteFile) {
		this.mapSpriteFile = mapSpriteFile;
	}
	
	@Override
	public boolean collision(Bird bird) {
		return false;
	}
	
	
	void updatePositionOsprey(double percentDistTraveled){
		int yLoc = OSPREY_INITIAL_SMALL_BIRD_Y_LOC - (int)( OSPREY_SMALL_BIRD_TOTAL_Y_DIST * percentDistTraveled);
		int xLoc = OSPREY_INITIAL_SMALL_BIRD_X_LOC - (int)( OSPREY_SMALL_BIRD_TOTAL_X_DIST * percentDistTraveled);
		this.setMapYLoc(yLoc);
		this.setMapXLoc(xLoc);
	 	} 
	
	void updatePositionNH(double percentDistTraveled){
		int yLoc = NH_INITIAL_SMALL_BIRD_Y_LOC - (int)( NH_SMALL_BIRD_TOTAL_Y_DIST * percentDistTraveled);
		int xLoc = NH_INITIAL_SMALL_BIRD_X_LOC - (int)( NH_SMALL_BIRD_TOTAL_X_DIST * percentDistTraveled);
		this.setMapYLoc(yLoc); 
		this.setMapXLoc(xLoc);
		} 
}

