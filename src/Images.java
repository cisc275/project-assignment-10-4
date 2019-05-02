/**
 * ENUM representing every the image sources for food and obstacles
 * 
 * @author 10-4
 *
 */
public enum Images {
	/**
	 * Every String is the path of the corresponding image
	 */
	OBSTACLE("images/building.png"),
	MOUSE("images/normal_mouse.png"), 
	GOLDENFISH("images/golden_fish.png"),
	FISH("images/normal_fish.png"),
	GOLDENMOUSE("images/golden_mouse.png"),
	NH_MINIMAP("images/NHMiniMapp.png"),   //("images/NHMiniMapp.png"),          //250x196
	NH_IMG_FOR_MINIMAP("images/NHImageMINI.png"),     //32x26
	OSPREY_IMG_FOR_MINIMAP("images/OspreyMINI.png"),  //31x27
	BUILDING("images/building_1080.png"),
	MOUSE("images/normal_mouse_1080.png"), 
	GOLDENFISH("images/golden_fish_1080.png"),
	FISH("images/normal_fish_1080.png"),
	GOLDENMOUSE("images/golden_mouse_1080.png"),
	EAGLE("images/eagle_1080.png"),
	OWL("images/owl_1080.png"),
	FOX("images/fox_1080.png"),
	RECTANGLE("images/rectangle-icon-256.png");
	
	
	/**
	 * @param a String representing the ImagePath of the GameElement
	 */
		
	private Images(String s){
		name = s;
	}
	/**
	 * a String attribute representing the ImagePath of the GameElement
	 */
	private String name = null;
	
	/**
	 * @return a String representing the ImagePath of the GameElement
	 */
	public String getName() {
		return name;
	}
}
