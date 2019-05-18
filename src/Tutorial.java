import java.io.Serializable;

public class Tutorial implements Serializable{
	Bird bird;
	
	Obstacle obstacle;
	
	Food food;
	
	int frameWidth;
	
	int frameHeight;
	
	Tutorial(int frameWidth, int frameHeight){
		bird = new Bird(0,0,0,0,Images.NORTHERN_HARRIER.getName());
		obstacle = new Obstacle(frameWidth,frameHeight - Images.getCorrespondingImage(Images.FOX).getHeight() , 20, 0, Images.FOX.getName(), Images.FOX);
		food = new Food(false,frameWidth,frameHeight - Images.getCorrespondingImage(Images.MOUSE).getHeight(),20,0,Images.MOUSE.getName(),Images.MOUSE);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
	}
	
	
	public void updateTutorial() {
		
	}
}
