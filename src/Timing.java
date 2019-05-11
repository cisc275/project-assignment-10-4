import java.io.Serializable;

@SuppressWarnings("serial")
public class Timing implements Serializable {
	int state; 
	int start; 
	public Timing(int start) {
		this.state = start; 
		this.start = start; 
		
	}
	public void reset() {
		this.state = this.start; 
	}
	public void decr() {
		this.state = this.state - 1; 
	}
	public boolean end() {
		return this.state < 0; 
	}
	public void setTime(int amount) {
		this.state = amount; 
	}
	public int getState() {
		return state; 
	}
}
