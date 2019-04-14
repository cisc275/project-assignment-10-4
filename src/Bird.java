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
	
	private int direction; 
	
	public Bird() {
		setXloc(10);
		setYloc(500);
		direction = 0;
		setxSpeed(0);
		setySpeed(10);
		setHeight(224);
		setWidth(224);
	}
	
	
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
	 * 
	 * @return the flying speed
	 */
	public int getFlyingSpeed() {
		return flyingSpeed;
	}

	public void setFlyingSpeed(int flyingSpeed) {
		this.flyingSpeed = flyingSpeed;
	}

	public boolean isPoweredUp() {
		return poweredUp;
	}

	public void setPoweredUp(boolean poweredUp) {
		this.poweredUp = poweredUp;
	}

	public boolean isStunned() {
		return isStunned;
	}

	public void setStunned(boolean isStunned) {
		this.isStunned = isStunned;
	}

	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int d) {
		this.direction = d;
	}
	
}
