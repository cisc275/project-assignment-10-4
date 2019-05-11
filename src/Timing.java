import java.io.Serializable;
/**
 * This is a generalizable timing system used throughout the game
 * @author jhdavis LandonJones
 *
 */
@SuppressWarnings("serial")
public class Timing implements Serializable {
	int state;
	int start;
	/**
	 * Construct a new timer
	 * @param start the number of ticks to count down from
	 */
	public Timing(int start) {
		this.state = start;
		this.start = start;
	}
	
	/**
	 * Reset the timer to its original starting value
	 */
	public void reset() {
		this.state = this.start; 
	}
	
	/**
	 * Reset the timer to a new specified starting value
	 * @param start the new starting value for the countdown
	 */
	public void reset(int start) {
		this.state = start;
	}
	
	/**
	 * Tick down the timer
	 */
	public void decr() {
		this.state = this.state - 1;
	}
	
	/**
	 * Get whether or not the timer is done
	 * @return boolean true if the timer has ended
	 */
	public boolean end() {
		return this.state < 0;
	}
	
	/**
	 * Set the current time left on the timer
	 * @param amount
	 */
	public void setState(int amount) {
		this.state = amount;
	}
	
	/**
	 * Get the current time left on the timer
	 * @return int the current number of ticks left
	 */
	public int getState() {
		return state;
	}
}
