import java.util.*;

/**
 * Contains the different components of the bird game. As the game updates, 
 * this handles the movement of elements and their current states.
 * 
 * @author 10-4
 *
 */
public class Model {
	/**
	 * The Bird the player will control
	 */
	private Bird bird;	
	/**
	 * Which bird the player is currently using. 0 = Osprey, 1 = Northern Harrier
	 */
	private int birdType;
	/**
	 * A list containing all GameElement objects that are currently on the screen
	 */
	private List<GameElement>onScreenCollidables;	
	/**
	 * The distance currently travelled
	 */
	private int distance;	
	/**
	 * The total distance needed to be travelled
	 */
	private int endDistance;	
	/**
	 * The MiniMap that will update as the game progresses
	 */
	private MiniMap miniMap;	
	/**
	 * The current score for the game
	 */
	private int points;	
	/**
	 * Stores whether the bird is in a powerup state
	 */
	private boolean birdMode;	
	/**
	 * Stores whether there is currently a quiz
	 */
	private boolean quizMode;	
	/**
	 * A list of all the different potential quiz questions
	 */
	private List<QuizQuestion>quizQuestions;
	/**
	 * The width of the game frame
	 */
	private QuizQuestions theQuestions; 
	private int frameWidth;
	/**
	 * The height of the game frame
	 */
    private int frameHeight;
    /**
	 * the width of the image
	 */
	private int imgWidth;
	/**
	 * the height of the image
	 */
	private int imgHeight;
    
	private Background background;
	
	
	/**
	 * Model constructor, sets up frame dimensions
	 * @param frameWidth
	 * @param frameHeight
	 */
	public Model(int frameWidth,int frameHeight) {
		bird = new Bird();
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		theQuestions = new QuizQuestions("images/questions.txt"); 
		this.background = new Background(frameWidth);
		
		onScreenCollidables = new ArrayList<GameElement>();
		GameElement obstacle1 = new Obstacle();
		obstacle1.setXloc(frameWidth + 500);
		obstacle1.setYloc(0);
		obstacle1.setxSpeed(10);
		GameElement obstacle2 = new Obstacle();
		obstacle2.setXloc(frameWidth + 900);
		obstacle2.setYloc(0);
		obstacle2.setxSpeed(10);
		GameElement obstacle3 = new Obstacle();
		obstacle3.setXloc(frameWidth + 1300);
		obstacle3.setYloc(0);
		obstacle3.setxSpeed(10);
		//onScreenCollidables.add(obstacle1);
		//onScreenCollidables.add(obstacle2);
		//onScreenCollidables.add(obstacle3);
		
		GameElement Food1 = new Food(10, true);
		Food1.setXloc(frameWidth);
		Food1.setYloc(0);
		Food1.setxSpeed(10);
		GameElement Food2 = new Food(10, true);
		Food2.setXloc(frameWidth - 100);
		Food2.setYloc(0);
		Food2.setxSpeed(10);
		GameElement Food3 = new Food(10, true);
		Food3.setXloc(frameWidth - 400);
		Food3.setYloc(0);
		Food3.setxSpeed(10);
		onScreenCollidables.add(Food1);
		onScreenCollidables.add(Food2);
		onScreenCollidables.add(Food3);             
	}
	
	/**
	 * Used to update the current status and positions of the different game components.
	 * Will call helper update methods for different components.  Calls all other update methods
	 */
	void update() {
		updateBird();
		updateGameElements();
		updateBackground();
		updateMiniMap();
		updateBackground();
		collisionDetection(); 
	}
	/**
	 * Used to update the current status and position of the bird based on user input
	 * and game states.
	 * 
	 * 
	 */
	void updateBird() {
		if((bird.getYloc()+bird.getHeight())<=frameHeight && bird.getYloc()>=0) {
			bird.updatePosition();			
		}
		else if(bird.getYloc() < 0){
			bird.setYloc(0);
		}
		else {
			bird.setYloc(frameHeight-bird.getHeight());
		}
	}	
	
	/**
	 * Updates the GameElement objects to update positions and remove objects with positions
	 * not on the screen.
	 */
	void updateGameElements() {
		int size = 0; 
		Iterator<GameElement> iter = this.onScreenCollidables.iterator();
		while (iter.hasNext()) {
			GameElement curr = iter.next(); 
			curr.updatePosition(); 
			if (curr.isOffScreen()) {
				size++; 
				iter.remove(); 
			}
			if((curr.getYloc()+curr.getHeight())>frameHeight) {
				curr.setYloc(frameHeight-curr.getHeight());
			}
		}
		for (int i = 0; i < size; i++) {
			spawnGameElement(); 
		}
	}
	
	/**
	 * Updates the background depending on the distance the player has reached.
	 */
	void updateBackground() {
		background.update();
	}
	
	/**
	 * Updates the MiniMap to display the current travelled status
	 */
	void updateMiniMap() {
		
	}
	
	/**
	 * Checks for collision between the Bird and any Collidable on screen.
	 * 
	 * @return The Collidable that has been collided with by the bird
	 */

	GameElement collisionDetection() {
		GameElement collided = null;
		for (GameElement e : onScreenCollidables) {
			if (e.getBounds().intersects(bird.getBounds())) {
				collided = e;
			}
		}
		//bird.collisionWith(collided);
		//collided.collision();
		return collided;
	}

	/**
	 * Starts a quiz if the bird has eaten a special food.
	 * 
	 * @return The quiz question that will be displayed for the player to answer.
	 */
	QuizQuestion startQuiz() {return theQuestions.getCurrent();}
	
	/**
	 * Ends the quiz and restarts the player controlling the bird. Handles powerup start
	 * if the player answered the quiz correctly.
	 */
	void endQuiz() {}
	
	/**
	 * Spawns new collidables periodically.
	 */
	void spawnGameElement() {
		Random rand = new Random(); 
		int x = frameWidth; 
		int y = rand.nextInt(frameHeight);	
		int xSpeed = 10;
		int ySpeed = 0; 
		
		onScreenCollidables.add(new GameElement(x, y, xSpeed, ySpeed)); 
	}
	/**
	 * Controls the bird positions for the entering the nest animation upon level completion
	 */
	void enterNest() {}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the endDistance
	 */
	public int getEndDistance() {
		return endDistance;
	}

	/**
	 * @param endDistance the endDistance to set
	 */
	public void setEndDistance(int endDistance) {
		this.endDistance = endDistance;
	}

	/**
	 * @return the miniMap
	 */
	public MiniMap getMiniMap() {
		return miniMap;
	}

	/**
	 * @param miniMap the miniMap to set
	 */
	public void setMiniMap(MiniMap miniMap) {
		this.miniMap = miniMap;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the quizQuestions
	 */
	public List<QuizQuestion> getQuizQuestions() {
		return quizQuestions;
	}

	/**
	 * @param quizQuestions the quizQuestions to set
	 */
	public void setQuizQuestions(List<QuizQuestion> quizQuestions) {
		this.quizQuestions = quizQuestions;
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
	 * @return the onScreenCollidables
	 */
	public List<GameElement> getOnScreenCollidables() {
		return onScreenCollidables;
	}

	/**
	 * @param onScreenCollidables the onScreenCollidables to set
	 */
	public void setOnScreenElements(List<GameElement> onScreenCollidables) {
		this.onScreenCollidables = onScreenCollidables;
	}

	/**
	 * @return the birdMode
	 */
	public boolean isBirdMode() {
		return birdMode;
	}

	/**
	 * @param birdMode the birdMode to set
	 */
	public void setBirdMode(boolean birdMode) {
		this.birdMode = birdMode;
	}

	/**
	 * @return the quizMode
	 */
	public boolean isQuizMode() {
		return quizMode;
	}

	/**
	 * @param quizMode the quizMode to set
	 */
	public void setQuizMode(boolean quizMode) {
		this.quizMode = quizMode;
	}

	/**
	 * @return the birdType
	 */
	public int getBirdType() {
		return birdType;
	}

	/**
	 * @param birdType the birdType to set
	 */
	public void setBirdType(int birdType) {
		this.birdType = birdType;
	}

	/**
	 * @return the frameWidth
	 */
	public int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * @param frameWidth the frameWidth to set
	 */
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	/**
	 * @return the frameHeight
	 */
	public int getFrameHeight() {
		return frameHeight;
	}

	/**
	 * @param frameHeight the frameHeight to set
	 */
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	
	public Background getBackground() {
		return this.background;
	}
	
	public void setBackground(Background background) {
		this.background = background;
	}
	
}
