import java.io.Serializable;

public class Tutorial implements Serializable{
	private Bird bird;
	
	private Obstacle obstacle;
	
	private Obstacle hitObstacle;
	
	private Food food;
	
	private int frameWidth;
	
	private int frameHeight;
	
	private boolean collectFood;
	
	private boolean avoidObstacle;
	
	private boolean displayArrow;
	
	private boolean hitAnObstacle;
	
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
	}
	
	
	public void updateTutorial() {
		//System.out.println(bird.getStaminaPics()[5].getName());
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
			
			if(hitObstacle.getXloc() == (frameWidth*3/4)) {
				hitAnObstacle = true;
				displayArrow = true;
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
}
