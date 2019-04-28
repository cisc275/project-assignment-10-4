import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.*;

public class Background {
	private BufferedImage background1;
	private BufferedImage background2;
	private int b1x;
	private int b2x;
	private int speed;
	private int width;
	
	
	
	Background(int dim){
		setBackground1(createImage());
		setBackground2(createImage());
		setB1x(0);
		setB2x(dim);
		setSpeed(10);
		setWidth(dim);
	}
	
	public void update() {
		b1x -= speed;
		b2x -= speed;
		if(b1x+width<=0) {
			b1x = width;
		}
		
		if(b2x + width <= 0) {
			b2x = width;
		}
	}
	
	
	
	
	
	
	BufferedImage createImage(){
		BufferedImage bufferedImage;
		try {
		    bufferedImage = ImageIO.read(new File("images/big_grass_background.png"));
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
}
