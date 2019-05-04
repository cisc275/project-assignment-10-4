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
	NH_MINIMAP("images/NHMiniMapp.png"),   //("images/NHMiniMapp.png"),          //250x196
	OBSTACLE("images/building.png"),
	OSPREY_MINIMAP("images/NHMiniMapp.png"),   //("images/NHMiniMapp.png"),          //250x196
	NH_IMG_FOR_MINIMAP("images/NHImageMINI.png"),     //32x26
	OSPREY_IMG_FOR_MINIMAP("images/OspreyMINI.png"),  //31x27
	BUILDING("images/building_1080.png"), //Osprey
	MOUSE("images/normal_mouse_1080.png"),  //Northern Harrier
	GOLDENFISH("images/golden_fish_1080.png"), //Osprey
	FISH("images/normal_fish_1080.png"), //Osprey
	GOLDENMOUSE("images/golden_mouse_1080.png"), //Northern Harrier
	EAGLE("images/eagle_1080.png"), //Osprey
	OWL("images/owl_1080.png"), //Northern Harrier
	FOX("images/fox_1080.png"), //Northern Harrier
    BIRD("images/bird_animated.png"), 
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
