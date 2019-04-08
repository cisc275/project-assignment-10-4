
public class Bird extends GameElement{
	private int flyingSpeed;
	private boolean poweredUp;
	private boolean isStunned;
	
	void powerUp() {}

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
}
