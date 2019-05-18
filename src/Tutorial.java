import java.io.Serializable;

public class Tutorial implements Serializable{
	private Bird bird;
	
	private Obstacle obstacle;
	
	private Food food;
	
	int frameWidth;
	
	int frameHeight;
	
	Tutorial(int frameWidth, int frameHeight){
		setBird(new Bird(0,0,0,0,Images.NORTHERN_HARRIER.getName()));
		bird.setBirdType("Northern Harrier");
		bird.setYloc(300);
		setObstacle(new Obstacle(frameWidth,frameHeight - Images.getCorrespondingImage(Images.FOX).getHeight() , 20, 0, Images.FOX.getName(), Images.FOX));
		setFood(new Food(false,frameWidth,frameHeight - Images.getCorrespondingImage(Images.MOUSE).getHeight(),20,0,Images.MOUSE.getName(),Images.MOUSE));
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
	}
	
	
	public void updateTutorial() {
		
	}


	/**
	 * @return the bird
	 */
	public Bird getBird() {
		return bird;
	}


	/**
	 * @param bird the bird to set
	 */
	public void setBird(Bird bird) {
		this.bird = bird;
	}


	/**
	 * @return the obstacle
	 */
	public Obstacle getObstacle() {
		return obstacle;
	}


	/**
	 * @param obstacle the obstacle to set
	 */
	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}


	/**
	 * @return the food
	 */
	public Food getFood() {
		return food;
	}


	/**
	 * @param food the food to set
	 */
	public void setFood(Food food) {
		this.food = food;
	}
}
