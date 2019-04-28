import java.io.*;
/**
 *Provides the image showing the birds progress through its migratory journey.  The minimap is different,
 *depending on if the Osprey or the Northern Harrier is the current bird.  The birds progress on the minimap is 
 *dependent on the amount of food the bird has eaten
 * 
 * @author 10-4
 *
 */
public class MiniMap extends GameElement implements Serializable{
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
	 * @param mapSpriteFile the mapSpriteFile to set
	 */
	public void setMapSpriteFile(String mapSpriteFile) {
		this.mapSpriteFile = mapSpriteFile;
	}
}
