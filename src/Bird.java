import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * Bird is the sole user controlled GameElement and the focus of the game.  The game is divided into two portions,
 * depending on if the Bird is currently a Northern Harrier or an Osprey
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Bird extends GameElement implements Serializable{
	/**
	 * Which bird the player is currently using. Should be "osprey" or "northern harrier".
	 */
	private String birdType;
	/**
	 * Constant for Bird's width
	 */
	private static final int BIRD_WIDTH = 224;
	/**
	 * Constant for Bird's height
	 */
	private static final int BIRD_HEIGHT = 224;
	/**
	 * Constant for Bird's starting y speed
	 */
	private static final int START_Y_SPEED = 10;
	/**
	 * Constant for Bird's starting y location
	 */
	private static final int START_Y_LOC = 500;
	/**
	 * Constant for Bird's starting x location
	 */
	private static final int START_X_LOC = 10;
	/**
	 * This constant represents how many ticks the bird stays stunned for
	 */
	private static final int STUN_TIME_LIMIT = 75;
    /**
     * An int for the number of frames that the bird cycles through to animate.
     */
    private static final int FRAME_COUNT = 4;
    /**
     * Constant for Bird's starting stamina value
     */
	private static final int START_STAMINA = 5;
	/**
	 * An int representing the birds speed
	 */
	private int flyingSpeed;
	/**
	 * A boolean value representing whether or not the bird is currently powered up, true indicates that it powered up,
	 * while false means that it is not.  A bird becomes powered up after consuming an instance of food with a true,
	 * value for its isSpecialFood attribute
	 */
	private boolean poweredUp;
	/**
	 * A boolean value representing whether or not the bird is currently stunned, true indicates that it is stunned, 
	 * while false means that it is not.  A bird becomes stunned after colliding with an Obstacle
	 */
	private boolean isStunned;
	/**
	 * An int representing which way the bird is moving.  If direction == 1, then the bird is moving upwards and if 
	 * direction == -1, the bird is moving downwards.
	 */
    private int direction;
    /**
     * An int representing how much energy to bird has, lost over time and when damaged, and gained upon eating.
     */
    private int stamina;
	/**
	 * An array of BufferedImages that stores the different bird image frames
	 */
    transient private BufferedImage[] pics;
    /**
	 * An array of BufferedImages that stores the different stamina bar pictures
	 */
    transient private BufferedImage[] staminaPics;
    /**
     * A BufferedImage representing the current stamina bar image
     */
    transient private BufferedImage staminaImage;
    /**
     * An int representing the current frame image that is being displayed
     */
    private int frameNum;
    /**
     * Represents the time stunned upon collision
     */
    private int stunTimer;
    /**
     * If true, the bird has run out of stamina and the level needs to restart
     */
	private boolean fainted = false;
    
    /**
	 * A constructor which initializes the attributes for the start of the game.  The bird's starting location is set, 
	 * its direction is set to 0 because it is not moving up or down.  Its xSpeed is set to 0 because it is not moving 
	 */	
	public Bird(int x, int y, int xSpeed, int ySpeed, String imagePath) {
		super(x, y, xSpeed, ySpeed, imagePath, null);
		setXloc(START_X_LOC);
		setYloc(START_Y_LOC);
		direction = 0;
		setxSpeed(0);
		setySpeed(START_Y_SPEED);
		setHeight(BIRD_HEIGHT);
		setWidth(BIRD_WIDTH);
		frameNum = 0;
		pics = new BufferedImage[FRAME_COUNT];
		stunTimer = 0;
		stamina = START_STAMINA;
		staminaPics = new BufferedImage[6];
		this.setType(Images.BIRD);
	}
	
	/**
	 * Handles the non-serlializable fields of class in writing to file
	 * @param ObjectOutputStream to be written to
	 * 
	 */
	public void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(pics.length);
		for(int i = 0; i<pics.length; i++) {
			ImageIO.write(pics[i], "png", out);
		}
		out.writeInt(staminaPics.length);
		for(int i = 0; i<staminaPics.length; i++) {
			ImageIO.write(staminaPics[i], "png", out);
		}
		ImageIO.write(staminaImage, "png", out);
	}
	
	/**
	 * Handles the non-serlializable fields of class in reading from a file
	 * @param ObjectOutputStream to be read from
	 * 
	 */
	@SuppressWarnings("static-access")
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		int imageCount = in.readInt();
		pics = new BufferedImage[imageCount];
		for (int i=0; i<imageCount; i++) {
			pics[i] = ImageIO.read(in);
		}
		imageCount = in.readInt();
		staminaPics = new BufferedImage[imageCount];
		for (int i=0; i<imageCount; i++) {
			staminaPics[i] = ImageIO.read(in);
		}
		this.staminaImage = ImageIO.read(in);
	}
	
	/**
	 * Updates the position of the bird.  If direction == 1 then the bird moves up.  If direction == 0 
	 * then the bird stays in the same y position.  If direction == -1 then the bird moves down.
	 * Also handles the stunned status of the bird.
	 */
	@Override
	void update(){
		if (stamina <= 0) {
			setFainted(true);
			System.out.println(fainted);
		}
		setXloc(getXloc()+getxSpeed());
		setYloc(getYloc()+(getySpeed()*(-1)*direction));	
		if (isStunned) {
			stunTimer++;
			if (stunTimer >= STUN_TIME_LIMIT) {
				stunTimer = 0;
				isStunned = false;
			}
		}
	}

	/**
	 * It changes the frame to be the next frame and returns the old frame to be displayed
	 * 
	 * @return the next frame image of the bird
	 */
	public BufferedImage nextFrame() {
		int currentFrame = frameNum;
		frameNum = (frameNum+1)%FRAME_COUNT;
		return pics[currentFrame];
		
	}
	
	/**
	 * This sets the image of the bird and creates the frames of the bird
	 * 
	 * @param image the BufferedImage to set
	 */
	@Override
	public void setImage(BufferedImage image) {
		this.image = image;
		this.width = image.getWidth()/FRAME_COUNT;
		this.height = image.getHeight();
		for(int i = 0; i < FRAME_COUNT; i++)
    		pics[i] = image.getSubimage(this.width*i, 0, this.width, this.height);
	}
	
	/**
	 * Handles adjusting the birds attributes after it becomes powered up by consuming an instance of food with a, 
	 * true value for its isSpecialFood attribute
	 */
	void powerUp() {}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.xloc+25,this.yloc+140,this.width-75,60);
	}
	
	
	/** 
	 * @return the flying speed
	 */
	public int getFlyingSpeed() {
		return flyingSpeed;
	}
	
	/**
	 * @param flyingSpeed the speed to fly at
	 */
	public void setFlyingSpeed(int flyingSpeed) {
		this.flyingSpeed = flyingSpeed;
	}
	
	/**
	 * @return true if the bird is powered up.
	 */
	public boolean isPoweredUp() {
		return poweredUp;
	}
	/**
	 * @param poweredUp- a boolean which is true if the bird is currently powered up, false otherwise
	 */
	public void setPoweredUp(boolean poweredUp) {
		this.poweredUp = poweredUp;
	}
	
	/** 
	 * @return true if the bird is stunned.
	 */
	public boolean isStunned() {
		return isStunned;
	}
	
	/**
	 * @param isStunned- a boolean which is true if the bird is currently stunned, false otherwise
	 */
	public void setStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}
	
	/**
	 * @return a 1 if the bird is moving up, a 0 if it is not moving up or down and a -1 if it is moving down.
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * @param direction- an int which is 1 if the bird is moving up, a 0 if it is not moving up or down and a -1 
	 * if it is moving down.
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @return the stamina
	 */
	public int getStamina() {
		return stamina;
	}

	/**
	 * @param stamina the stamina to set
	 */
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	/**
	 * 
	 * @return the frames of the bird
	 */
	public BufferedImage[] getPics() {
		return this.pics;
	}

	/**
	 * 
	 * @param pics the frames to set
	 */
	public void setPics(BufferedImage[] pics) {
		this.pics = pics;
	}
	/**
	 * 
	 * @return the staminaPics of the bird
	 */
	public BufferedImage[] getStaminaPics() {
		return this.staminaPics;
	}

	/**
	 * 
	 * @param pics the frames to set
	 */
	public void setStaminaPics(BufferedImage[] pics) {
		this.staminaPics = pics;
	}
	
	/**
	 * 
	 * @return the frameNum
	 */
	public int frameNum() {
		return this.frameNum;
	}
	
	/**
	 * 
	 * @param frameNum the frameNum to set
	 */
	public void setFrameNum(int frameNum) {
		this.frameNum = frameNum;
	}
	
	/**
	 * 
	 * @return the frame count
	 */
	public int getFrameCount() {
		return Bird.FRAME_COUNT;
	}
	

	/**
	 * @return the birdType
	 */
	public String getBirdType() {
		return birdType;
	}

	/**
	 * @param birdType the birdType to set
	 */
	public void setBirdType(String birdType) {
		if (birdType.equalsIgnoreCase("osprey")) {
			this.birdType = "osprey";
		} else {
			this.birdType = "northern harrier";
		}
	}

	/**
	 * Implements the required method from GameElement
	 * 
	 * @param Bird the bird of the game
	 * @return a boolean value of whether there was a collision. Always returns false for Bird
	 */
	@Override
	public boolean collision(Bird bird) {
		return false;
	}

	/**
	 * Gets the current stamina image
	 * @return BufferedImage the staminaImage
	 */
	public BufferedImage getStaminaImage() {
		return staminaImage;
	}

	/**
	 * Sets the current stamina image based on current stamina
	 */
	public void updateStaminaImage() {
		staminaImage = staminaPics[stamina];
	}

	/**
	 * @return the fainted
	 */
	public boolean isFainted() {
		return fainted;
	}

	/**
	 * @param fainted the fainted to set
	 */
	public void setFainted(boolean fainted) {
		this.fainted = fainted;
	}
}
