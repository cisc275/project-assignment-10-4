import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
	 * Constant for speed at which Background begins scroll at
	 */
	private static final int INITIAL_SCROLL_SPEED = 3;
	/**
	 * A speed variable which can fluctuate
	 */
	private int backgroundScrollSpeed = 3;
	/**
	 * BufferedImage representing a portion of the background
	 */
	private BufferedImage background1;
	/**
	 * BufferedImage representing another portion of the background
	 */
	private BufferedImage background2;
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
	 * The dimension of the screen and inturn the background image
	 */
	private int width;
	
	
	/**
	 * 
	 * @param dim the width dimension of the screen
	 */
	Background(int dim){
		setBackground1(createImage());
		setBackground2(createImage());
		setB1x(0);
		setB2x(dim-3);
		setSpeed(INITIAL_SCROLL_SPEED );
		setWidth(dim);
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
		}
		
		if(b2x + width <= 0) {
			b2x = width-8;
		}
	}
	

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Background) {
			Background b = (Background)obj;
			return (b.getB1x() == this.getB1x() &&
					b.getB2x() == this.getB2x() &&
					b.getSpeed() == this.getSpeed() && 
					b.getWidth() == this.getWidth());
		} else {
			return false;
		}
	}
	
	
	
	
	
	

	/**
	 * Creates a BufferedImage of the background using the grass background file as a default
	 * The game will always start with this background
	 * 
	 * @return the BufferedImage of the background
	 */
	BufferedImage createImage(){
		BufferedImage bufferedImage;
		try {
		    bufferedImage = ImageIO.read(new File("images/background_grass_1080.png"));
		    return bufferedImage;
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return the b1x
	 */
	public int getB1x() {
		return b1x;
	}

	/**
	 * @param b1x the b1x to set
	 */
	public void setB1x(int b1x) {
		this.b1x = b1x;
	}

	/**
	 * @return the b2x
	 */
	public int getB2x() {
		return b2x;
	}

	/**
	 * @param b2x the b2x to set
	 */
	public void setB2x(int b2x) {
		this.b2x = b2x;
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
	 * @return the background1
	 */
	public BufferedImage getBackground1() {
		return background1;
	}

	/**
	 * @param background1 the background1 to set
	 */
	public void setBackground1(BufferedImage background1) {
		this.background1 = background1;
	}

	/**
	 * @return the background2
	 */
	public BufferedImage getBackground2() {
		return background2;
	}

	/**
	 * @param background2 the background2 to set
	 */
	public void setBackground2(BufferedImage background2) {
		this.background2 = background2;
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
}
