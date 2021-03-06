import java.util.Random;

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
public class Background implements Serializable {
	/**
	 * An int representing the offset gap between background panels
	 */
	private static final int BACKGROUND_OFFSET = 5;
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
	 * Constructor for backgrounds
	 * @param model.getWidth() the width dimension of the screen
	 */
	Background(int frameWidth) {
		rand = new Random();
		setBackground(0,Images.GRASS_PATH);
		setBackground(1,Images.GRASS_MIRROR_PATH);
		setBackgroundX(0,0);
		setBackgroundX(1,frameWidth-3);
		setWidth(frameWidth);
	}

	/**
	 * Updates the position of the background images to scroll.
	 * If a background image is completely off screen to the left, move it
	 * so its position is off screen to the right, next to the other background
	 * @param speedAdjust how much to adjust the scroll speed by for this tick
	 */
	public void update(int speedAdjust) {
		backgroundScrollSpeed = INITIAL_SCROLL_SPEED + speedAdjust;
		bgXs[0] -= backgroundScrollSpeed;
		bgXs[1] -= backgroundScrollSpeed;
		if(bgXs[0] + width <= 0) {
			bgXs[0] = bgXs[1] + width - BACKGROUND_OFFSET;
			updateBackgroundZone(0);
		}
		if(bgXs[1] + width <= 0) {
			bgXs[1] = bgXs[0] + width;
			updateBackgroundZone(1);
		}
	}

	/**
	 * Called when a specified background image goes offscreen, 
	 * possibly swaps grass/water
	 * @param num the background image to update
	 */
	private void updateBackgroundZone(int num) {
		int randResult = rand.nextInt(3);
		if (ospreyMode && randResult == 2) {
			if(num == 0) {
				setBackground(num, Images.WATER_PATH);
			}
			else {
				setBackground(num, Images.WATER_MIRROR_PATH);
			}
		} else {
			if(num == 0) {
				setBackground(num, Images.GRASS_PATH);
			}
			else {
				setBackground(num, Images.GRASS_MIRROR_PATH);
			}
			
		}
	}

	/**
	 * Returns true if the zone to the right is water
	 * 
	 * @return boolean indicating if water is the next zone
	 */
	public boolean isWaterNextZone() {
		if (ospreyMode) {
			if ((bgXs[0] < bgXs[1] && (bgs[1].equals(Images.WATER_PATH) || bgs[1].equals(Images.WATER_MIRROR_PATH)))
					|| (bgXs[1] < bgXs[0]
							&& (bgs[0].equals(Images.WATER_PATH) || bgs[0].equals(Images.WATER_MIRROR_PATH)))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Overrides the Object equals method
	 * @param object to be compared to this instance
	 * @return a boolean representing the equality of the parameter and this instance
	 */
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
