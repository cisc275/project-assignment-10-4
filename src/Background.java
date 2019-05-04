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
	 * Constant path to grass image 
	 */
	private static final String GRASS_PATH = "images/background_grass_1080.png";
	/**
	 * Constant path to grass image 
	 */
	private static final String WATER_PATH = "images/background_water_1080.png";
	/**
	 * Constant for speed at which Background begins to scroll at
	 */
	private static final int INITIAL_SCROLL_SPEED = 3;
	/**
	 * A speed variable which can fluctuate
	 */
	private int backgroundScrollSpeed = 3;
	/**
	 * Constant for Grass background object
	 */
	private static final BufferedImage GRASS = createImage(GRASS_PATH);
	/**
	 * Constant for water background object
	 */
	private static final BufferedImage WATER = createImage(WATER_PATH);
	/**
	 * First background portion position
	 */
	private int b1x;
	/**
	 * Second background portion position
	 */
	private int b2x;
	/**
	 * The speed the background will scroll
	 */
	private int speed;
	/**
	 * The dimension of the screen and in turn the dimension of the background image
	 */
	private int width;
	/**
	 * If true, add in some water zones since the osprey is migrating over water
	 */
	private boolean ospreyMode = false;
	/**
	 * Current first background image to display
	 */
	private BufferedImage background1 = GRASS;
	/**
	 * Current second background image to display
	 */
	private BufferedImage background2 = GRASS;
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
		setBackgroundX(1,0);
		setBackgroundX(2,frameWidth-3);
		setSpeed(INITIAL_SCROLL_SPEED);
		setWidth(frameWidth);
	}
	
	/**
	 * Updates the position of the background images to scroll.
	 * If a background image is completely off screen to the left, move it
	 * so it's position is off screen to the right, next to the other background
	 */
	public void update() {
		b1x -= speed;
		b2x -= speed;
		if(b1x+width<=0) {
			b1x = width-8;
			updateBackgroundZone(1);
		}
		
		if(b2x + width <= 0) {
			b2x = width-8;
			updateBackgroundZone(2);
		}
	}

	private void updateBackgroundZone(int num) {
		if (ospreyMode) {
			int randResult = rand.nextInt(4);
			if (randResult == 3) {
				setBackground(num, WATER);
			}
		} else {
			setBackground(num, GRASS);
		}
	}
	

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Background) {
			Background b = (Background)obj;
			return (b.getBackgroundX(1) == this.getBackgroundX(1) &&
					b.getBackgroundX(2) == this.getBackgroundX(2) &&
					b.getSpeed() == this.getSpeed() && 
					b.getWidth() == this.getWidth());
		} else {
			return false;
		}
	}
	
	
	
	
	
	

	/**
	 * Creates a BufferedImage of the background using specified path
	 * The game will always start with this background
	 * @param path location of image file to create from
	 * 
	 * @return the BufferedImage of the background
	 */
	static BufferedImage createImage(String path){
		BufferedImage bufferedImage;
		try {
		    bufferedImage = ImageIO.read(new File(path));
		    return bufferedImage;
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return the BackgroundX
	 */
	public int getBackgroundX(int num) {
		if (num == 1) {
			return b1x;
		} else {
			return b2x;
		}
	}

	/**
	 * @param BackgroundX the BackgroundX to set
	 */
	public void setBackgroundX(int num, int bx) {
		if (num == 1) {
			this.b1x = bx;
		} else {
			this.b2x = bx;
		}
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
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
	 * @return the background1
	 */
	public BufferedImage getBackground(int num) {
		if (num == 1) {
			return background1;			
		} else {
			return background2;
		}
	}
	
	/**
	 * @param num which background image object to set, 1 or 2
	 * @param bg the background BufferedImage to set
	 */
	public void setBackground(int num, BufferedImage bg) {
		if (num == 1) {
			this.background1 = bg;			
		} else {
			this.background2 = bg;
		}
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
