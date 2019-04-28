/**
 * ENUM representing every the image sources for food and obstacles
 * 
 * @author 10-4
 *
 */
public enum Images {

	OBSTACLE("images/building.png"),
	MOUSE("images/normal_mouse.png"), 
	GOLDENFISH("images/golden_fish.png"),
	FISH("images/normal_fish.png"),
	GOLDENMOUSE("images/golden_mouse.png");
	
		
	private Images(String s){
		name = s;
	}
	
	private String name = null;
	
	public String getName() {
		return name;
	}
}