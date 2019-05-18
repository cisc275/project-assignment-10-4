import java.io.Serializable;

@SuppressWarnings("serial")
public class Tutorial implements Serializable{
	/**
	 * The bird for the tutorial the user will control
	 */
	private Bird bird;
	/**
	 * An obstacle that will need to be dodged
	 */
	private Obstacle obstacle;
	/**
	 * An obstacle that will be hit
	 */
	private Obstacle hitObstacle;
	/**
	 * Food that will be eaten
	 */
	private Food food;
	/**
	 * The width of the frame
	 */
	private int frameWidth;
	/**
	 * The height of the frame
	 */
	private int frameHeight;
	/**
	 * True if the tutorial is in the food collection phase
	 */
	private boolean collectFood;
	/**
	 * True if the tutorial is in the avoid obstacle phase
	 */
	private boolean avoidObstacle;
	/**
	 * True if the arrow to highlight the stamina should be dispalyed
	 */
	private boolean displayArrow;
	/**
	 * True if the hitting an obstacle phase is active
	 */
	private boolean hitAnObstacle;
	/**
	 * True if the tutorial has been completed
	 */
	private boolean doneTutorial;
	
	Tutorial(int frameWidth, int frameHeight){
		setBird(new Bird(0,0,0,0,Images.NORTHERN_HARRIER.getName()));
		bird.setBirdType("Northern Harrier");
		bird.setYloc(300);
		bird.setStamina(4);
		bird.updateStaminaImage();
		setObstacle(new Obstacle(4*frameWidth,frameHeight - Images.getCorrespondingImage(Images.FOX).getHeight() , 20, 0, Images.FOX.getName(), Images.FOX));
		obstacle.setType(Images.FOX);
		setHitObstacle(new Obstacle(5*frameWidth/2,frameHeight - Images.getCorrespondingImage(Images.FOX).getHeight() , 20, 0, Images.FOX.getName(), Images.FOX));
		hitObstacle.setType(Images.FOX);
		setFood(new Food(false,3*frameWidth/2,frameHeight - Images.getCorrespondingImage(Images.MOUSE).getHeight(),20,0,Images.MOUSE.getName(),Images.MOUSE));
		food.setType(Images.MOUSE);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		collectFood = false;
		avoidObstacle = false;
		displayArrow = false;
		hitAnObstacle = false;
		doneTutorial = false;
		
	}
	
	/**
	 * Updates the tutorial to the different phases to ensure that the player understands
	 * the different aspects of the game
	 */
	public void updateTutorial() {
		if(!collectFood && !avoidObstacle) {
			obstacle.update(0);
			hitObstacle.update(0);
			food.update(0);
			if(food.getXloc()== (frameWidth*3/4)) {
				collectFood = true;
			}
			
			if(obstacle.getXloc() == (frameWidth/2)) {
				avoidObstacle = true;
			}
			
			if (food.polyBounds().intersects(bird.getBounds())) {
				food.setEaten(true);
				bird.setStamina(5);
				bird.updateStaminaImage();
			}
			
			if(hitObstacle.polyBounds().intersects(bird.getBounds())) {
				bird.setStunned(true);
				bird.setStunTimer(1);
				bird.setStamina(4);
				bird.updateStaminaImage();
			}
			
			if(obstacle.getXloc() == frameWidth) {
				bird.setStunned(false);
			}
			
			if(!hitAnObstacle && ((food.getXloc() + food.getWidth()) < 0 || (hitObstacle.getXloc() + hitObstacle.getWidth()) < 0)) {
				displayArrow = false;
			}
			
			if(hitObstacle.getXloc() == (frameWidth*5/8)) {
				hitAnObstacle = true;
				displayArrow = true;
			}
			
			if(obstacle.getXloc() + obstacle.getWidth() < 0) {
				doneTutorial = true;
			}
		}
		
		if(collectFood || avoidObstacle) {
			bird.update(0);
		}
		
		if(collectFood && ((bird.getYloc()+bird.getHeight()/2) >= food.getYloc())) {
			collectFood = false;
			displayArrow = true;
		}
		
		if(avoidObstacle && ((bird.getYloc()+bird.getHeight()*3/2 < obstacle.getYloc()))) {
			avoidObstacle = false;
		}
		
		if(hitAnObstacle && (hitObstacle.getXloc() + hitObstacle.getWidth() < 100)) {
			hitAnObstacle = false;
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


	/**
	 * @return the hitObstacle
	 */
	public Obstacle getHitObstacle() {
		return hitObstacle;
	}


	/**
	 * @param hitObstacle the hitObstacle to set
	 */
	public void setHitObstacle(Obstacle hitObstacle) {
		this.hitObstacle = hitObstacle;
	}


	/**
	 * @return the displayArrow
	 */
	public boolean isDisplayArrow() {
		return displayArrow;
	}


	/**
	 * @param displayArrow the displayArrow to set
	 */
	public void setDisplayArrow(boolean displayArrow) {
		this.displayArrow = displayArrow;
	}


	/**
	 * @return the hitAnObstacle
	 */
	public boolean isHitAnObstacle() {
		return hitAnObstacle;
	}


	/**
	 * @param hitAnObstacle the hitAnObstacle to set
	 */
	public void setHitAnObstacle(boolean hitAnObstacle) {
		this.hitAnObstacle = hitAnObstacle;
	}


	/**
	 * @return the doneTutorial
	 */
	public boolean isDoneTutorial() {
		return doneTutorial;
	}


	/**
	 * @param doneTutorial the doneTutorial to set
	 */
	public void setDoneTutorial(boolean doneTutorial) {
		this.doneTutorial = doneTutorial;
	}
}
