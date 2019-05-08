import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import java.io.*;

/**
 * This class controls the scrolling background for the game. It contains
 * 2 background images that will move across the screen and then reset to 
 * the far right once off screen to continue moving.
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Background implements Serializable{
	/**
	 * Constant for speed at which Background begins to scroll at
	 */
	private static final int INITIAL_SCROLL_SPEED = 10;
	/**
	 * A speed variable which can fluctuate
	 */
	private int backgroundScrollSpeed = INITIAL_SCROLL_SPEED;
	/**
	 * Array of the X positions of the two backgrounds
	 */
	private int[] bgXs = new int[2];
	/**
	 * The dimension of the screen and in turn the dimension of the background image
	 */
	private int width;
	/**
	 * If true, add in some water zones since the osprey is migrating over water
	 */
	private boolean ospreyMode = false;
	/**
	 * Array of containing the two image object paths current being used as backgrounds
	 */
	private Images[] bgs = new Images[2];
	/**
	 * Random object for inserting random sea zones
	 */
	private Random rand;

	/**
	 * 
	 * @param model.getWidth() the width dimension of the screen
	 */
	Background(int frameWidth) {
		rand = new Random();
		setBackground(0,Images.GRASS_PATH);
		setBackground(1,Images.GRASS_PATH);
		setBackgroundX(0,0);
		setBackgroundX(1,frameWidth-3);
		setWidth(frameWidth);
	}

	/**
	 * Updates the position of the background images to scroll.
	 * If a background image is completely off screen to the left, move it
	 * so its position is off screen to the right, next to the other background
	 */
	public void update() {
		bgXs[0] -= backgroundScrollSpeed;
		bgXs[1] -= backgroundScrollSpeed;
		if(bgXs[0] + width <= 0) {
			bgXs[0] = width-8;
			updateBackgroundZone(0);
		}
		if(bgXs[1] + width <= 0) {
			bgXs[1] = width-8;
			updateBackgroundZone(1);
		}
	}

	/**
	 * Called when a specified background image goes offscreen, 
	 * possibly swaps grass/water
	 * @param num the background image to update
	 */
	private void updateBackgroundZone(int num) {
		if (ospreyMode) {
			int randResult = rand.nextInt(4);
			if (randResult == 3) {
				setBackground(num, Images.WATER_PATH);
			}
		} else {
			setBackground(num, Images.GRASS_PATH);
		}
	}

	/**
	 * Returns true if the zone to the right is water
	 * @return boolean indicating if water is the next zone
	 */
	public boolean isWaterNextZone() {
		if (ospreyMode) {
			if ((bgXs[0] < bgXs[1] && bgs[1].equals(Images.WATER_PATH)) ||
					(bgXs[1] < bgXs[0] && bgs[0].equals(Images.WATER_PATH))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Background) {
			Background b = (Background)obj;
			return (b.getBackgroundX(0) == this.getBackgroundX(0) &&
					b.getBackgroundX(1) == this.getBackgroundX(1) &&
					b.getBackgroundScrollSpeed() == this.getBackgroundScrollSpeed() && 
					b.getWidth() == this.getWidth());
		} else {
			return false;
		}
	}

	/**
	 * @return the BackgroundX
	 */
	public int getBackgroundX(int num) {
		return bgXs[num];
	}

	/**
	 * @param BackgroundX the BackgroundX to set
	 */
	public void setBackgroundX(int num, int bx) {
		bgXs[num] = bx;
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
	 * @param num which background image object to get, 1 or 2
	 * @return the background
	 */
	public Images getBackground(int num) {
		return bgs[num];
	}

	/**
	 * @param num which background image object to set, 1 or 2
	 * @param waterPath the background BufferedImage to set
	 */
	public void setBackground(int num, Images path) {
		bgs[num] = path;
	}

	/**
	 * @return the backgroundScrollSpeed
	 */
	public int getBackgroundScrollSpeed() {
		return this.backgroundScrollSpeed;
	}

	/**
	 * @param backgroundScrollSpeed 
	 */
	public void setBackgroundScrollSpeed(int backgroundScrollSpeed) {
		this.backgroundScrollSpeed = backgroundScrollSpeed;
	}

	/**
	 * @return the ospreyMode
	 */
	public boolean isOspreyMode() {
		return ospreyMode;
	}

	/**
	 * @param ospreyMode the ospreyMode to set
	 */
	public void setOspreyMode(boolean ospreyMode) {
		this.ospreyMode = ospreyMode;
	}
}
