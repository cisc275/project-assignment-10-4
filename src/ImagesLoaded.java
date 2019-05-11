import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * ENUM representing every the BufferedImages for all game elements
 * 
 * @author 10-4
 *
 */
public enum ImagesLoaded {
	/**
	 * Every String is the path of the corresponding image
	 */
	NH_MINIMAP_I(createImage(Images.NH_MINIMAP.getName())),   //("images/NHMiniMapp.png"),          306x215
	OBSTACLE_I(createImage(Images.OBSTACLE.getName())),
	OSPREY_MINIMAP_I(createImage(Images.OSPREY_MINIMAP.getName())),   //("images/NHMiniMapp.png"),          //250x196
	NH_IMG_FOR_MINIMAP_I(createImage(Images.NH_IMG_FOR_MINIMAP.getName())),     //32x26
	OSPREY_IMG_FOR_MINIMAP_I(createImage(Images.OSPREY_IMG_FOR_MINIMAP.getName())),  //31x27
	BUILDING_I(createImage(Images.BUILDING.getName())), //Osprey
	MOUSE_I(createImage(Images.MOUSE.getName())),  //Northern Harrier
	GOLDENFISH_I(createImage(Images.GOLDENFISH.getName())), //Osprey
	FISH_I(createImage(Images.FISH.getName())), //Osprey
	GOLDENMOUSE_I(createImage(Images.GOLDENMOUSE.getName())), //Northern Harrier
	EAGLE_I(createImage(Images.EAGLE.getName())), //Osprey
	OWL_I(createImage(Images.OWL.getName())), //Northern Harrier
	FOX_I(createImage(Images.FOX.getName())), //Northern Harrier
    BIRD_I(createImageArray(Images.BIRD.getName(),4),4),
    POWERUP_I(createImageArray(Images.POWERUP.getName(),4),4),
	RECTANGLE_I(createImage(Images.RECTANGLE.getName())),
	GRASS_PATH_I(createImage(Images.GRASS_PATH.getName())),
	GRASS_PATH_MIRROR_I(createImage(Images.GRASS_MIRROR_PATH.getName())),
	WATER_PATH_I(createImage(Images.WATER_PATH.getName())),
	WATER_PATH_MIRROR_I(createImage(Images.WATER_MIRROR_PATH.getName())),
	HEALTH_0_I(createImage(Images.HEALTH_0.getName())),
	HEALTH_1_I(createImage(Images.HEALTH_1.getName())),
	HEALTH_2_I(createImage(Images.HEALTH_2.getName())),
	HEALTH_3_I(createImage(Images.HEALTH_3.getName())),
	HEALTH_4_I(createImage(Images.HEALTH_4.getName())),
	HEALTH_5_I(createImage(Images.HEALTH_5.getName()));
	
	
	
	/**
	 * @param a BufferedImage representing the BufferedImage of the GameElement
	 */
		
	private ImagesLoaded(BufferedImage b){
		image = b;
		
	}
	private ImagesLoaded(BufferedImage[] a, int f) {
		setImageArray(a);
		setFrameCount(f);
	}
	/**
	 * a BufferedImage attribute the BufferedImage of the GameElement
	 */
	private BufferedImage image = null;
	private BufferedImage[] imageArray = null;
	private int frameCount = 1;
	
	/**
	 * @return a BufferedImage representing the ImagePath of the GameElement
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Creates a BufferedImage using specified path
	 * @param path location of image file to create from
	 * 
	 * @return the BufferedImage
	 */
	static BufferedImage createImage(String path){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(path));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static BufferedImage[] createImageArray(String path, int f) {
		BufferedImage temp = createImage(path);
		BufferedImage[] arr = new BufferedImage[f];
		for (int i = 0; i < f; i++) {
			arr[i] = temp.getSubimage((temp.getWidth()/f) * i, 0, temp.getWidth()/f, temp.getHeight());
		}
		return arr;
	}
	public BufferedImage[] getImageArray() {
		return imageArray;
	}
	public void setImageArray(BufferedImage[] imageArray) {
		this.imageArray = imageArray;
	}
	public int getFrameCount() {
		return frameCount;
	}
	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}
	

}
