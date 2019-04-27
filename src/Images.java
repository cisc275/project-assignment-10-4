/**
 * ENUM representing every the image sources for food and obstacles
 * 
 * @author 10-4
 *
 */
public enum Images {

	OBSTACLE("images/building.png"),
	FOOD("images/birdFood.jpg"), 
	RECTANGLE("images/rectangle-icon-256.png");
	
		
	private Images(String s){
		name = s;
	}
	
	private String name = null;
	
	public String getName() {
		return name;
	}
}