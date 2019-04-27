import java.awt.image.BufferedImage;

/**
 * Bird is the sole user controlled GameElement and the focus of the game.  The game is divided into two portions,
 * depending on if the Bird is currently a Northern Harrier or an Osprey
 * 
 * @author 10-4
 *
 */
public class Bird extends GameElement{
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
	 * A constructor which initializes 7 attributes for the start of the game.  The bird's starting location is set, 
	 * its direction is set to 0 because it is not moving up or down.  Its xSpeed is set to 0 because it is not moving
	 *  
	 */
	
	public Bird() {
		setXloc(10);
		setYloc(500);
		direction = 0;
		setxSpeed(0);
		setySpeed(10);
		setHeight(224);
		setWidth(224);
	}
	
	/**
	 * Updates the position of the bird.  If direction == 1 then the bird moves up.  If direction == 0 
	 * then the bird stays in the same y position.  If direction == -1 then the bird moves down.
	 */
	@Override
	void updatePosition(){
		setXloc(getXloc()+getxSpeed());
		setYloc(getYloc()+(getySpeed()*(-1)*direction));		
	}
	
	/**
	 * Handles adjusting the birds attributes after it becomes powered up by consuming an instance of food with a, 
	 * true value for its isSpecialFood attribute
	 */
	void powerUp() {}

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
	 * 
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

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
