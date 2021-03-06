import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ENUM representing every the BufferedImages for all game elements
 * 
 * @author 10-4
 *
 */
public enum ImagesLoaded {
	/**
	 * Every BufferedImage is the BufferedImage of the corresponding Images
	 */
	NH_MINIMAP_I(createImage(Images.NH_MINIMAP.getName())),
	OBSTACLE_I(createImage(Images.OBSTACLE.getName())),
	OSPREY_MINIMAP_I(createImage(Images.OSPREY_MINIMAP.getName())),
	NH_IMG_FOR_MINIMAP_I(createImage(Images.NH_IMG_FOR_MINIMAP.getName())),
	OSPREY_IMG_FOR_MINIMAP_I(createImage(Images.OSPREY_IMG_FOR_MINIMAP.getName())),
	BUILDING_I(createImage(Images.BUILDING.getName())),
	MOUSE_I(createImage(Images.MOUSE.getName())),
	GOLDENFISH_I(createImage(Images.GOLDENFISH.getName())),
	FISH_I(createImage(Images.FISH.getName())),
	GOLDENMOUSE_I(createImage(Images.GOLDENMOUSE.getName())),
	EAGLE_I(createImage(Images.EAGLE.getName())),
	OWL_I(createImage(Images.OWL.getName())),
	FOX_I(createImage(Images.FOX.getName())),
    OSPREY_I(createImageArray(Images.OSPREY.getName(),5),5),
    NORTHERN_HARRIER_I(createImageArray(Images.NORTHERN_HARRIER.getName(),5),5),
    BIRD_I(createImageArray(Images.BIRD.getName(),5),5),
    POWERUP_I(createImageArray(Images.POWERUP.getName(),5),5),
    POWERUP_OSPREY_I(createImageArray(Images.POWERUP_OSPREY.getName(),5),5),
	RECTANGLE_I(createImage(Images.RECTANGLE.getName())),
	GRASS_PATH_I(createImage(Images.GRASS_PATH.getName())),
	GRASS_PATH_MIRROR_I(createImage(Images.GRASS_MIRROR_PATH.getName())),
	WATER_PATH_I(createImage(Images.WATER_PATH.getName())),
	WATER_PATH_MIRROR_I(createImage(Images.WATER_MIRROR_PATH.getName())),
	HEALTH_0_I(createImage(Images.HEALTH_0.getName())),
	HEALTH_0_I_OSPREY(createImage(Images.HEALTH_0_OSPREY.getName())),
	HEALTH_1_I_OSPREY(createImage(Images.HEALTH_1_OSPREY.getName())),
	HEALTH_2_I_OSPREY(createImage(Images.HEALTH_2_OSPREY.getName())),
	HEALTH_3_I_OSPREY(createImage(Images.HEALTH_3_OSPREY.getName())),
	HEALTH_4_I_OSPREY(createImage(Images.HEALTH_4_OSPREY.getName())),
	HEALTH_5_I_OSPREY(createImage(Images.HEALTH_5_OSPREY.getName())),
	HEALTH_1_I(createImage(Images.HEALTH_1.getName())),
	HEALTH_2_I(createImage(Images.HEALTH_2.getName())),
	HEALTH_3_I(createImage(Images.HEALTH_3.getName())),
	HEALTH_4_I(createImage(Images.HEALTH_4.getName())),
	HEALTH_5_I(createImage(Images.HEALTH_5.getName())),
	TRASH_I(createImage(Images.TRASH.getName())),
	DOWN_ARROW_I(createImage(Images.DOWN_ARROW.getName())),
	UP_ARROW_I(createImage(Images.UP_ARROW.getName())),
	RED_ARROW_I(createImage(Images.RED_ARROW.getName())),
	RED_ARROW_BACKWARD_I(createImage(Images.RED_ARROW_BACKWARD.getName()));
	
	/**
	 * @param a BufferedImage representing the BufferedImage of the GameElement
	 */	
	private ImagesLoaded(BufferedImage b){
		image = b;
	}
	/**
	 * constructor for creating an animated image
	 * @param a BufferedImage array representing the frames of the animation
	 * @param f the number of frames as an int
	 */
	private ImagesLoaded(BufferedImage[] a, int f) {
		setImageArray(a);
		setFrameCount(f);
	}
	/**
	 * a BufferedImage attribute the BufferedImage of the GameElement
	 */
	private BufferedImage image = null;
	/**
	 * BufferedImmage array attribute for animated Images
	 */
	private BufferedImage[] imageArray = null;
	/**
	 * frame count initialized to one for non-animated images
	 */
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
	
	/**
	 * Creates a BufferedImage array using specified path
	 * @param path location of image file to create from
	 * @param the number of frames
	 * 
	 * @return the BufferedImage array
	 */
	static BufferedImage[] createImageArray(String path, int f) {
		BufferedImage temp = createImage(path);
		BufferedImage[] arr = new BufferedImage[f];
		for (int i = 0; i < f; i++) {
			arr[i] = temp.getSubimage((temp.getWidth()/f) * i, 0, temp.getWidth()/f, temp.getHeight());
		}
		return arr;
	}
	/**
	 *
	 * @return bufferedImage array
	 */
	public BufferedImage[] getImageArray() {
		return imageArray;
	}
	/**
	 * 
	 * @param imageArray to set
	 */
	public void setImageArray(BufferedImage[] imageArray) {
		this.imageArray = imageArray;
	}
	/**
	 * 
	 * @return the frame count
	 */
	public int getFrameCount() {
		return frameCount;
	}
	/**
	 * 
	 * @param frameCount to set
	 */
	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}
	

}
