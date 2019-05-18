import java.io.Serializable;

public class Tutorial implements Serializable{
	private Bird bird;
	
	private Obstacle obstacle;
	
	private Food food;
	
	private int frameWidth;
	
	private int frameHeight;
	
	private boolean collectFood;
	
	private boolean avoidObstacle;
	
	Tutorial(int frameWidth, int frameHeight){
		setBird(new Bird(0,0,0,0,Images.NORTHERN_HARRIER.getName()));
		bird.setBirdType("Northern Harrier");
		bird.setYloc(300);
		setObstacle(new Obstacle(4*frameWidth,frameHeight - Images.getCorrespondingImage(Images.FOX).getHeight() , 20, 0, Images.FOX.getName(), Images.FOX));
		obstacle.setType(Images.FOX);
		setFood(new Food(false,3*frameWidth/2,frameHeight - Images.getCorrespondingImage(Images.MOUSE).getHeight(),20,0,Images.MOUSE.getName(),Images.MOUSE));
		food.setType(Images.MOUSE);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		collectFood = false;
		avoidObstacle = false;
	}
	
	
	public void updateTutorial() {
		if(!collectFood && !avoidObstacle) {
			obstacle.update(0);
			food.update(0);
			if(food.getXloc()== (frameWidth*3/4)) {
				collectFood = true;
			}
			if (food.polyBounds().intersects(bird.getBounds())) {
				food.setEaten(true);
			}
		}
		if(collectFood) {
			bird.update(0);
		}
		if((bird.getYloc()+bird.getHeight()/2) >= food.getYloc()) {
			collectFood = false;
		}
		
		
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


	/**
	 * @return the collectFood
	 */
	public boolean isCollectFood() {
		return collectFood;
	}


	/**
	 * @param collectFood the collectFood to set
	 */
	public void setCollectFood(boolean collectFood) {
		this.collectFood = collectFood;
	}


	/**
	 * @return the avoidObstacle
	 */
	public boolean isAvoidObstacle() {
		return avoidObstacle;
	}


	/**
	 * @param avoidObstacle the avoidObstacle to set
	 */
	public void setAvoidObstacle(boolean avoidObstacle) {
		this.avoidObstacle = avoidObstacle;
	}
}
