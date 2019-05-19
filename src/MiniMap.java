import java.io.*;
import java.util.ArrayList;

/**
 * Provides the image showing the birds progress through its migratory journey.
 * The minimap is different, depending on if the Osprey or the Northern Harrier
 * is the current bird. The birds progress on the minimap is dependent on the
 * amount of food the bird has eaten
 * 
 * @author 10-4
 *
 */

@SuppressWarnings("serial")
public class MiniMap extends GameElement implements Serializable {
	/**
	 * An ArrayList where each index contains an int array which will contain an ordered pair of x and y
	 * coordinates of a point where the small bird on the miniMap has been
	 */
	private ArrayList<int []> birdPath;
	/**
	 * Offset to make drawing the path from the center of the bird image which is on the minimap
	 * instead of the top left corner
	 */
	private static final int OSPREY_OFFSET = 15;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel in the x direction for the Osprey during the first stage of the migration 
	 */
	private static final int OSPREY_SMALL_BIRD_TOTAL_X_DIST = 65;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel in the y direction for the Osprey during the first stage of the migration 
	 */
	private static final int OSPREY_SMALL_BIRD_TOTAL_Y_DIST = 57;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel in the x direction for the Osprey during the second stage of the migration 
	 */	
	private static final int OSPREY_BIRD_TOTAL_Y_DIST_SECOND_PATH = 50;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel in the x direction for the Osprey during the second stage of the migration 
	 */	
	private static final int OSPREY_BIRD_TOTAL_X_DIST_SECOND_PATH = 90;
	/**
	 * The constant int value of the x location of the small bird Sprite on the
	 * minimap's starting position for the Osprey
	 */
	static final int OSPREY_INITIAL_SMALL_BIRD_X_LOC = 1764; // 1819;
	/**
	 * The constant int value of the y location of the small bird Sprite on the
	 * minimap's starting position for the Osprey
	 */
	static final int OSPREY_INITIAL_SMALL_BIRD_Y_LOC = 120;
	/**
	 * The constant int value of the x location of the small bird Sprite on the
	 * minimap's starting position for the Northern Harrier
	 */
	static final int NH_INITIAL_SMALL_BIRD_X_LOC = 1812; // 1819;
	/**
	 * The constant int value of the y location of the small bird Sprite on the
	 * minimap's starting position for the Northern Harrier
	 */
	static final int NH_INITIAL_SMALL_BIRD_Y_LOC = 150;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel during the first phase of migration in the x direction for the Northern Harrier
	 */
	private static final int NH_SMALL_BIRD_TOTAL_X_DIST = 40;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel during the first phase of migration in the y direction for the Northern Harrier
	 */
	private static final int NH_SMALL_BIRD_TOTAL_Y_DIST = 30;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel during the second phase of migration in the x direction for the Northern Harrier
	 */
	private static final int NH_SMALL_BIRD_TOTAL_X_DIST_SECOND_PATH = 40;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel during the second phase of migration in the y direction for the Northern Harrier
	 */
	private static final int NH_SMALL_BIRD_TOTAL_Y_DIST_SECOND_PATH = 15;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel during the third phase of migration in the x direction for the Northern Harrier
	 */
	private static final int NH_SMALL_BIRD_TOTAL_X_DIST_THIRD_PATH = 1;
	/**
	 * The constant int value of the distance the small bird Sprite on the minimap
	 * has to travel during the third phase of migration in the y direction for the Northern Harrier
	 */
	private static final int NH_SMALL_BIRD_TOTAL_Y_DIST_THIRD_PATH = 44;
	/**
	 * The int value of the X location of the small bird Sprite on the minimap
	 */
	private int mapXLoc;
	/**
	 * The int value of the Y location of the small bird Sprite on the minimap
	 */
	private int mapYLoc;
	/**
	 * The String of the file containing the Sprite corresponding to either the
	 * Northern Harrier or the Osprey, depending on what the current bird is. This
	 * is the Sprite that will be placed on the miniMap
	 */
	private Images mapSpriteFile;
	/**
	 * This is the bufferedImage that will be placed on the miniMap to represent the
	 * current location of the bird
	 */
	private Images smallBird;
	/**
	 * This is the value of the X location of the small bird Sprite on the minimap, prior to the last
	 * call to updateMinimap in model
	 */
	private int lastMapXLoc;
	/**
	 * This is the value of the Y location of the small bird Sprite on the minimap, prior to the last
	 * call to updateMinimap in model
	 */
	private int lastMapYLoc;
	
	/**
	 * @param x             an int representing the x location of the GameElement
	 * @param y             an int representing the y location of the GameElement
	 * @param xSpeed        an int representing the horizontal speed of the
	 *                      GameElement
	 * @param ySpeed        an int representing the vertical speed of the
	 *                      GameElement
	 * @param imagePath     a string representing the path of the image of the
	 *                      GameElement
	 * @param mapSpriteFile a string of the file containing the Sprite
	 *                      corresponding to either the Northern Harrier or the
	 *                      Osprey
	 * @param mapXLoc       an int value of the X location of the small bird
	 *                      (either the Northern Harrier or the Osprey) on the
	 *                      minimap
	 * @param mapYLoc       an int value of the y location of the small bird
	 *                      (either the Northern Harrier or the Osprey) on the
	 *                      minimap
	 */
	public MiniMap(int x, int y, int xSpeed, int ySpeed, Images Img, Images mapSpriteFile, int mapXLoc,
			int mapYLoc) {
		super(x, y, xSpeed, ySpeed, Img.getName(), Img);
		this.mapSpriteFile = mapSpriteFile;
		this.mapXLoc = mapXLoc;
		this.mapYLoc = mapYLoc;
		this.birdPath = new ArrayList<int []>() ;
		
	}
	/**
	 * @return the current int value of the x location of the of the small bird on the minimap
	 */
	public int getMapXLoc() {
		return mapXLoc;
	}

	/**
	 * @param the current int value of the x location of the of the small bird on the minimap
	 */
	public void setMapXLoc(int mapXLoc) {
		this.mapXLoc = mapXLoc;
	}

	/**
	 * @return the current int value of the y location of the of the small bird on the minimap
	 */
	public int getMapYLoc() {
		return mapYLoc;
	}

	/**
	 * @param the current int value of the y location of the of the small bird on the minimap
	 */
	public void setMapYLoc(int mapYLoc) {
		this.mapYLoc = mapYLoc;
	}

	/**
	 * @return the mapSpriteFile
	 */
	public Images getMapSpriteFile() {
		return mapSpriteFile;
	}

	/**
	 * @return the BufferedImage of the small bird showing the player's progress on
	 *         the minimap
	 */
	public Images getSmallBird() {
		return smallBird;
	}

	/**
	 * 
	 * @param smallBird to set
	 */
	public void setSmallBird(Images smallBird) {
		this.smallBird = smallBird;
	}
	/**
	 * @param the previously current int value of the x location of the of the small bird on the minimap          
	 */
	public void setLastMapXLoc(int lastMapXLoc) {
		this.lastMapXLoc = lastMapXLoc;
	}
	/**
	 * @return the previously current int value of the x location of the of the small bird on the minimap
	 */
	public int getLastMapXLoc() {
		return this.lastMapXLoc;
	}
	/**
	 * @param the previously current int value of the y location of the of the small bird on the minimap          
	 */
	public void setLastMapYLoc(int lastMapXLoc) {
		this.lastMapYLoc = lastMapXLoc;
	}
	/**
	 * @return the previously current int value of the y location of the of the small bird on the minimap
	 */
	public int getLastMapYLoc() {
		return this.lastMapYLoc;
	}

	/**
	 * @param mapSpriteFile the mapSpriteFile to set
	 */
	public void setMapSpriteFile(Images mapSpriteFile) {
		this.mapSpriteFile = mapSpriteFile;
	}
	/**
	 * @param An ArrayList where each index contains an int array which will contain an ordered pair of x and y
	 * coordinates of a point where the small bird on the miniMap has been
	 */
	public void setBirdPath(ArrayList<int[]> birdPath) {
		this.birdPath = birdPath;
	}
	/**
	 * @return An ArrayList where each index contains an int array which will contain an ordered pair of x and y
	 * coordinates of a point where the small bird on the miniMap has been
	 */
	public ArrayList<int[]> getBirdPath() {
		return this.birdPath;
	}

	/**
	 * Overrides GameElement collision() method
	 * collision is irrelevant for the MiniMap and MapSprite
	 */
	@Override
	public boolean collision(Bird bird) {
		return false;
	}

	/**
	 * For when the game is in Osprey mode
	 * updates the location of the mapSprite image based upon the percent of
	 * the game the player has completed
	 * @param percentDistTraveled
	 */
	void updatePositionOsprey(double percentDistTraveled) {
		int [] birdPos = {this.getMapXLoc() + OSPREY_OFFSET , this.getMapYLoc()+ OSPREY_OFFSET };  //31x27
		birdPath.add( birdPos  );
		this.setLastMapXLoc(this.getMapXLoc());
		this.setLastMapYLoc(this.getMapYLoc());
		if (percentDistTraveled <= .5) {
			int yLoc = OSPREY_INITIAL_SMALL_BIRD_Y_LOC - (int) (OSPREY_SMALL_BIRD_TOTAL_Y_DIST * 2 * percentDistTraveled);
			int xLoc = OSPREY_INITIAL_SMALL_BIRD_X_LOC - (int) (OSPREY_SMALL_BIRD_TOTAL_X_DIST * 2 * percentDistTraveled);
			this.setMapYLoc(yLoc);
			this.setMapXLoc(xLoc);
		}
		
		else
		{   percentDistTraveled -= .5;
			int midwayY = OSPREY_INITIAL_SMALL_BIRD_Y_LOC - OSPREY_SMALL_BIRD_TOTAL_Y_DIST ;
			int midwayX = OSPREY_INITIAL_SMALL_BIRD_X_LOC - OSPREY_SMALL_BIRD_TOTAL_X_DIST ;
			int yLoc = midwayY - (int) (OSPREY_BIRD_TOTAL_Y_DIST_SECOND_PATH * 2 *  percentDistTraveled);
			int xLoc = midwayX + (int) (OSPREY_BIRD_TOTAL_X_DIST_SECOND_PATH  * 2 * percentDistTraveled); 
			this.setMapYLoc(yLoc);
			this.setMapXLoc(xLoc);
		}
	}

	/**
	 * For when the game is in Northern Harrier mode
	 * updates the location of the mapSprite image based upon the percent of
	 * the game the player has completed
	 * @param percentDistTraveled
	 */
	void updatePositionNH(double percentDistTraveled) {
		int [] birdPos = {this.getMapXLoc() + OSPREY_OFFSET , this.getMapYLoc() + OSPREY_OFFSET };  //31x27
		birdPath.add( birdPos  );
		this.setLastMapXLoc(this.getMapXLoc());
		this.setLastMapYLoc(this.getMapYLoc());
		if (percentDistTraveled <= .333333) {
			int yLoc = NH_INITIAL_SMALL_BIRD_Y_LOC - (int) (NH_SMALL_BIRD_TOTAL_Y_DIST * 3 * percentDistTraveled);
			int xLoc = NH_INITIAL_SMALL_BIRD_X_LOC - (int) (NH_SMALL_BIRD_TOTAL_X_DIST * 3 * percentDistTraveled);
			this.setMapYLoc(yLoc);
			this.setMapXLoc(xLoc);
		}
		
		else if (percentDistTraveled <= .666666) {
			percentDistTraveled -= .3333333;
			int midwayY = NH_INITIAL_SMALL_BIRD_Y_LOC - NH_SMALL_BIRD_TOTAL_Y_DIST ;
			int midwayX = NH_INITIAL_SMALL_BIRD_X_LOC - NH_SMALL_BIRD_TOTAL_X_DIST ;
			int yLoc = midwayY - (int) (NH_SMALL_BIRD_TOTAL_Y_DIST_SECOND_PATH * 3 * percentDistTraveled);
			int xLoc = midwayX + (int) (NH_SMALL_BIRD_TOTAL_X_DIST_SECOND_PATH * 3 * percentDistTraveled); 
			this.setMapYLoc(yLoc);
			this.setMapXLoc(xLoc);
		}
		
		else 
		{   percentDistTraveled -= .666666;
			int midwayY = NH_INITIAL_SMALL_BIRD_Y_LOC - NH_SMALL_BIRD_TOTAL_Y_DIST - NH_SMALL_BIRD_TOTAL_Y_DIST_SECOND_PATH ;
			int midwayX = NH_INITIAL_SMALL_BIRD_X_LOC - NH_SMALL_BIRD_TOTAL_X_DIST + NH_SMALL_BIRD_TOTAL_X_DIST_SECOND_PATH;
			int yLoc = midwayY + (int) (NH_SMALL_BIRD_TOTAL_Y_DIST_THIRD_PATH * 3 * percentDistTraveled);
			int xLoc = midwayX - (int) (NH_SMALL_BIRD_TOTAL_X_DIST_THIRD_PATH * 3 * percentDistTraveled); 
			this.setMapYLoc(yLoc);
			this.setMapXLoc(xLoc);
		}

		
	}

	/**
	 * Overrides the GameElement getPointValue() method
	 * a MiniMap does not get points for collision
	 * 
	 * @return integer representing point value
	 */
	@Override
	public int getPointValue() {
		return 0;
	}
}
