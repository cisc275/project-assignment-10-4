import java.awt.image.BufferedImage;
import java.io.Serializable;


/**
 * ENUM representing every the image sources for food and obstacles
 * 
 * @author 10-4
 *
 */
public enum Images implements Serializable {
	/**
	 * Every String is the path of the corresponding image
	 */
	NH_MINIMAP("images/NHMiniMap.png"),   //("images/NHMiniMapp.png"),          306x215
	OBSTACLE("images/building.png"),
	OSPREY_MINIMAP("images/OspreyMiniMap.png"),   //("images/NHMiniMapp.png"),          //250x196
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
    OSPREY("images/osprey_animate.png"),
    NORTHERN_HARRIER("images/northern_harrier_animate.png"),
    BIRD("images/northern_harrier_animate.png"),
    POWERUP("images/northern_harrier_powerup.png"),
    POWERUP_OSPREY("images/osprey_powerup.png"),
	RECTANGLE("images/rectangle-icon-256.png"),
	GRASS_PATH("images/background_grass_1080.png"),
	GRASS_MIRROR_PATH("images/background_grass_mirror_1080.png"),
	WATER_PATH("images/background_water_1080.png"),
	WATER_MIRROR_PATH("images/background_water_mirror_1080.png"),
	HEALTH_0("images/0_health_northern_harrier.png"),
	HEALTH_1("images/1_health_northern_harrier.png"),
	HEALTH_2("images/2_health_northern_harrier.png"),
	HEALTH_3("images/3_health_northern_harrier.png"),
	HEALTH_4("images/4_health_northern_harrier.png"),
	HEALTH_5("images/5_health_northern_harrier.png"),
	HEALTH_0_OSPREY("images/0_health_osprey.png"),
	HEALTH_1_OSPREY("images/1_health_osprey.png"),
	HEALTH_2_OSPREY("images/2_health_osprey.png"),
	HEALTH_3_OSPREY("images/3_health_osprey.png"),
	HEALTH_4_OSPREY("images/4_health_osprey.png"),
	HEALTH_5_OSPREY("images/5_health_osprey.png"),
	TRASH("images/plasticbag_1080.png"),
	DOWN_ARROW("images/down_arrow.png"),
	UP_ARROW("images/up_arrow.png"),
	RED_ARROW("images/red_arrow.png"),
	RED_ARROW_BACKWARD("images/minimap_arrow.png");
	
	
	
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
	
	/**
	 * gets the BufferedImage array for Images
	 * @param e, an enum
	 * @return the BufferedImage array for an animated Image
	 */
	static BufferedImage[] getCorrespondingImageArray(Images e) {
		switch(e) {
		case BIRD:
			return ImagesLoaded.BIRD_I.getImageArray();
		case OSPREY:
			return ImagesLoaded.OSPREY_I.getImageArray();
		case NORTHERN_HARRIER:
			return ImagesLoaded.NORTHERN_HARRIER_I.getImageArray();
		case POWERUP:
			return ImagesLoaded.POWERUP_I.getImageArray();
		case POWERUP_OSPREY:
			return ImagesLoaded.POWERUP_OSPREY_I.getImageArray();
		default:
			return null;
		}
	}
	
	/**
	 * gets the corresponding image for Images
	 * @param e an enum
	 * @return the BufferedImage for corresponding enum
	 */
	static BufferedImage getCorrespondingImage(Images e) {
		switch(e) {
		case NH_MINIMAP:
			return ImagesLoaded.NH_MINIMAP_I.getImage();
		case OBSTACLE:
			return ImagesLoaded.OBSTACLE_I.getImage();
		case OSPREY_MINIMAP:
			return ImagesLoaded.OSPREY_MINIMAP_I.getImage();
		case NH_IMG_FOR_MINIMAP:
			return ImagesLoaded.NH_IMG_FOR_MINIMAP_I.getImage();
		case OSPREY_IMG_FOR_MINIMAP:
			return ImagesLoaded.OSPREY_IMG_FOR_MINIMAP_I.getImage();
		case BUILDING:
			return ImagesLoaded.BUILDING_I.getImage();
		case MOUSE:
			return ImagesLoaded.MOUSE_I.getImage();
		case GOLDENFISH:
			return ImagesLoaded.GOLDENFISH_I.getImage();
		case FISH:
			return ImagesLoaded.FISH_I.getImage();
		case GOLDENMOUSE:
			return ImagesLoaded.GOLDENMOUSE_I.getImage();
		case EAGLE:
			return ImagesLoaded.EAGLE_I.getImage();
		case OWL:
			return ImagesLoaded.OWL_I.getImage();
		case FOX:
			return ImagesLoaded.FOX_I.getImage();
		case RECTANGLE:
			return ImagesLoaded.RECTANGLE_I.getImage();
		case GRASS_PATH:
			return ImagesLoaded.GRASS_PATH_I.getImage();
		case GRASS_MIRROR_PATH:
			return ImagesLoaded.GRASS_PATH_MIRROR_I.getImage();
		case WATER_PATH:
			return ImagesLoaded.WATER_PATH_I.getImage();
		case WATER_MIRROR_PATH:
			return ImagesLoaded.WATER_PATH_MIRROR_I.getImage();
		case HEALTH_0:
			return ImagesLoaded.HEALTH_0_I.getImage();
		case HEALTH_1:
			return ImagesLoaded.HEALTH_1_I.getImage();
		case HEALTH_2:
			return ImagesLoaded.HEALTH_2_I.getImage();
		case HEALTH_3:
			return ImagesLoaded.HEALTH_3_I.getImage();
		case HEALTH_4:
			return ImagesLoaded.HEALTH_4_I.getImage();
		case HEALTH_5:
			return ImagesLoaded.HEALTH_5_I.getImage();
		case HEALTH_0_OSPREY:
			return ImagesLoaded.HEALTH_0_I_OSPREY.getImage();
		case HEALTH_1_OSPREY:
			return ImagesLoaded.HEALTH_1_I_OSPREY.getImage();
		case HEALTH_2_OSPREY:
			return ImagesLoaded.HEALTH_2_I_OSPREY.getImage();
		case HEALTH_3_OSPREY:
			return ImagesLoaded.HEALTH_3_I_OSPREY.getImage();
		case HEALTH_4_OSPREY:
			return ImagesLoaded.HEALTH_4_I_OSPREY.getImage();
		case HEALTH_5_OSPREY:
			return ImagesLoaded.HEALTH_5_I_OSPREY.getImage();
		case TRASH:
			return ImagesLoaded.TRASH_I.getImage();
		case DOWN_ARROW:
			return ImagesLoaded.DOWN_ARROW_I.getImage();
		case UP_ARROW:
			return ImagesLoaded.UP_ARROW_I.getImage();
		case RED_ARROW:
			return ImagesLoaded.RED_ARROW_I.getImage();
		case RED_ARROW_BACKWARD:
			return ImagesLoaded.RED_ARROW_BACKWARD_I.getImage();
		default:
			return null;
		}
	}
}
