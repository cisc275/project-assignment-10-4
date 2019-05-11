import java.io.Serializable;
import java.util.*;
import java.lang.System;

/**
 * Contains the different components of the bird game. As the game updates, this
 * handles the movement of elements and their current states.
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Model implements Serializable {
	/**
	 * Constant for the maximum number of game elements that can be onscreen at any
	 * given moment
	 */
	private static final int MAX_GAME_ELEMENTS_ONSCREEN = 3;
	/**
	 * Constant for the maximum number of ticks between spawning of game elements
	 */
	static final int SPAWN_TIME_MAX = 50;
	/**
	 * Constant for minimum number of ticks between spawning of game elements
	 */
	static final int SPAWN_TIME_MIN = 15;
	/**
	 * The constant representing the total distance needed to be traveled
	 */
	private static final int END_DISTANCE = 100000;
	/**
	 * The Bird the player will control
	 */
	private Bird bird;
	/**
	 * A list containing all GameElement objects that are currently on the screen
	 */
	private List<GameElement> onScreenCollidables;
	/**
	 * The distance currently traveled
	 */
	private int distance;
	/**
	 * The variable of the total distance needed to be traveled
	 */
	private int endDistance;
	/**
	 * The MiniMap that will update as the game progresses
	 */
	private MiniMap miniMap;
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
	private List<QuizQuestion> quizQuestions;
	/**
	 * The questions
	 */
	private QuizQuestions theQuestions;
	/**
	 * The width of the game frame
	 */
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
	/**
	 * The background for the game
	 */
	private Background background;
	/**
	 * A random object used for generating random numbers
	 */
	private Random rand;
	/**
	 * the current number of GameElements that need to be spawned
	 */
	private int spawnCount;
	/**
	 * The time passed on the way to timeToSpawn (in ticks)
	 */
	private Timing spawnTimer;
	/**
	 * The number of ticks before a new GameElement is to be spawned
	 */
	private int timeToSpawn;
	/**
	 * If true, we are doing a quiz
	 */
	private boolean doingQuiz;
	/**
	 * if true, the end of the level is reached
	 */
	private boolean reachedEnd;
	/**
	 * The nesting animation at the end of the game
	 */
	private NestAnimation nestAnimation;

	/**
	 * Model constructor, sets up frame dimensions
	 * 
	 * @param frameWidth
	 * @param frameHeight
	 */
	public Model(int frameWidth, int frameHeight) {
		// bird = new Bird(0,0,0,0,"");
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.endDistance = END_DISTANCE;
		// theQuestions = new QuizQuestions("quiz/osprey_questions.txt");
		this.background = new Background(frameWidth);
		this.quizMode = false;
		miniMap = (MiniMap) generateImgPath(9);
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		spawnCount = 0;
		 
		doingQuiz = false;
		timeToSpawn = rand.nextInt(SPAWN_TIME_MAX - SPAWN_TIME_MIN) + SPAWN_TIME_MIN;
		spawnTimer = new Timing(timeToSpawn);
		onScreenCollidables = new ArrayList<GameElement>();
		// onScreenCollidables.add(generateImgPath(8));
		// onScreenCollidables.add(generateImgPath(6));
		for (int i = 0; i < MAX_GAME_ELEMENTS_ONSCREEN; i++) {
			spawnCount++;
		}
		this.reachedEnd = false;
		setNestAnimation(new NestAnimation());
	}

	/**
	 * Model constructor, sets up frame dimensions but also specifies a bird type
	 * 
	 * @param frameWidth
	 * @param frameHeight
	 * @param birdType
	 */
	public Model(int frameWidth, int frameHeight, String birdType) {
		bird = new Bird(0, 0, 0, 0, "");
		bird.setBirdType(birdType);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.endDistance = END_DISTANCE;
		if (birdType.equals("Osprey")) {
			theQuestions = new QuizQuestions("quiz/osprey_questions.txt");
		} else {
			theQuestions = new QuizQuestions("quiz/northern_harrier_questions.txt");
		}
		this.background = new Background(frameWidth);
		this.quizMode = false;
		miniMap = (MiniMap) generateImgPath(9);
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		spawnCount = 0;
		
		doingQuiz = false;
		timeToSpawn = rand.nextInt(SPAWN_TIME_MAX - SPAWN_TIME_MIN) + SPAWN_TIME_MIN;
		spawnTimer = new Timing(timeToSpawn);
		onScreenCollidables = new ArrayList<GameElement>();
		// onScreenCollidables.add(generateImgPath(8));
		// onScreenCollidables.add(generateImgPath(6));
		for (int i = 0; i < MAX_GAME_ELEMENTS_ONSCREEN; i++) {
			spawnCount++;
		}
		this.reachedEnd = false;
		setNestAnimation(new NestAnimation());
	}

	/**
	 * Used to update the current status and positions of the different game
	 * components. Will call helper update methods for different components. Calls
	 * all other update methods
	 */
	void update() {
		if (distance == 0) {
			createMiniMap();
		}
		double percentDistTraveled = (double) this.getDistance() / this.getEndDistance();
		if (percentDistTraveled >= 1) {
			this.reachedEnd = true;
		}
		updateBird();
		updateGameElements();
		updateBackground();
		updateMiniMap(percentDistTraveled);
		updateBackground();
		collisionDetection();
		updateSpawnTimer();
	}

	/**
	 * Only called 1 time at the time immediately after the user slects the birdType
	 * for gamePlay Creates the miniMap corresponding to the bird that was selected
	 */
	public void createMiniMap() {
		if (bird.getBirdType().equalsIgnoreCase("Osprey")) {
			miniMap = (MiniMap) generateImgPath(9);
		} else if (bird.getBirdType().equalsIgnoreCase("Northern Harrier")) {
			miniMap = (MiniMap) generateImgPath(10);
		}
	}

	/**
	 * Ticks the spawn timer if needed to introduce a random delay to the Element
	 * spawner, calling spawnGameElement when needed
	 */
	void updateSpawnTimer() {
		if (spawnCount > 0) {
			spawnTimer.decr(); 
			if (spawnTimer.end()) {
				spawnCount--;
				spawnGameElement();
				timeToSpawn = rand.nextInt(SPAWN_TIME_MAX - SPAWN_TIME_MIN) + SPAWN_TIME_MIN;
				spawnTimer.reset(timeToSpawn); 
			}
		}
	}

	/**
	 * Used to update the current status and position of the bird based on user
	 * input and game states.
	 */
	void updateBird() {
		bird.updateStaminaImage();
		if ((bird.getYloc() + bird.getHeight()) <= frameHeight && bird.getYloc() >= 0) {
			bird.update(0);
		} else if (bird.getYloc() < 0) {
			bird.setYloc(0);
		} else {
			bird.setYloc(frameHeight - bird.getHeight());
		}
	}

	/**
	 * Updates the GameElement objects to update positions and remove objects with
	 * positions not on the screen.
	 */
	void updateGameElements() {
		int size = 0;
		Iterator<GameElement> iter = this.onScreenCollidables.iterator();
		while (iter.hasNext()) {
			GameElement curr = iter.next();
			curr.update(bird.getFoodStreak());
			if (curr.isOffScreen()) {
				size++;
				iter.remove();
			}
			if ((curr.getYloc() + curr.getHeight()) > frameHeight) {
				curr.setYloc(frameHeight - curr.getHeight());
			}
		}
		for (int i = 0; i < size; i++) {
			spawnCount++;
		}
	}

	/**
	 * Updates the background depending on the distance the player has reached and
	 * bird's species
	 */
	void updateBackground() {
		distance += background.getBackgroundScrollSpeed();
		if (bird.getBirdType().equalsIgnoreCase("osprey")) {
			background.setOspreyMode(true);
		} else {
			background.setOspreyMode(false);
		}
		background.update(bird.getFoodStreak());
	}

	/**
	 * Updates the MiniMap to display the current traveled status
	 */
	void updateMiniMap(double percentDistTraveled) {
		if (percentDistTraveled >= 1) {
			this.reachedEnd = true;
		}
		if (bird.getBirdType().equalsIgnoreCase("Osprey")) {
			miniMap.updatePositionOsprey(percentDistTraveled);
		} else {
			miniMap.updatePositionNH(percentDistTraveled);
		}
	}

	/**
	 * Updates the nesting animation
	 */
	void updateNestAnimation() {
		this.nestAnimation.animationUpdate();
	}

	/**
	 * Configures the nest animation attributes if the bird being played is the
	 * northern harrier. The default within the nestanimation class is for the
	 * Osprey
	 */
	void configureNestAnimation() {
		if (this.bird.getBirdType().equals("northern harrier")) {
			this.nestAnimation.setEndx(1400);
			this.nestAnimation.setEndy(950);
			this.nestAnimation.setXvel(8);
			this.nestAnimation.setYvel(5);
		}
	}

	/**
	 * Checks for collision between the Bird and any Collidable on screen.
	 * 
	 * @return The Collidable that has been collided with by the bird
	 */
	GameElement collisionDetection() {
		GameElement collided = null;
		for (GameElement e : onScreenCollidables) {
			/*
			 * if (e.getBounds().intersects(bird.getBounds())) { collided = e; }
			 */
			if (e.polyBounds().intersects(bird.getBounds())) {
				collided = e;
			}
		}
		if (collided != null) {
			bird.updateScore(collided.getPointValue());
			boolean shouldRemove = collided.collision(bird);
			if (shouldRemove) {
				onScreenCollidables.remove(collided);
				spawnCount++;
			}
			if (collided.getSpecialFood() && !theQuestions.noMoreQuestions()) {
				quizMode = true;
			}
		}
		return collided;
	}

	/**
	 * Starts a quiz if the bird has eaten a special food.
	 * 
	 * @return The quiz question that will be displayed for the player to answer.
	 */
	QuizQuestion startQuiz() {
		return theQuestions.getCurrent();
	}

	/**
	 * Ends the quiz and restarts the player controlling the bird. Handles powerup
	 * start if the player answered the quiz correctly. Ends quiz mode
	 */
	void endQuiz(String answer) {
		if (theQuestions.answerQuestion(answer)) {
			System.out.println("Correct");
			this.bird.setPoweredUp(true);
		} else {
			System.out.println("False");
		}
		quizMode = false;
		doingQuiz = false;
	}
	
	/**
	 * Method to generate random choice for generating image
	 * @param choice
	 * @return new randomized choice
	 */
	public int randomImage(int choice) {
		Random randImg = new Random();
		int curImage = 0;
		
		if (choice < 0) {
			if (getBird().getBirdType().equalsIgnoreCase("osprey")) {
				if (background.isWaterNextZone()) {
					curImage = randImg.nextInt(4) + 1;
					if (curImage == 3 && !theQuestions.noMoreQuestions()) {
						curImage = randImg.nextInt(2) + 3; // re-roll on a golden fish
					} 
					else if (theQuestions.noMoreQuestions() && curImage == 3) {
						curImage += 1; 
					}
				
				} else {
					curImage = randImg.nextInt(2);
				}
			} else {
				curImage = randImg.nextInt(4) + 5;
				if (curImage == 6 && !theQuestions.noMoreQuestions()) {
					curImage = randImg.nextInt(2) + 5; // re-roll on a golden mouse
				} 
				else if (theQuestions.noMoreQuestions() && curImage == 6) {
					curImage -= 1; 
				}
			}
		} else {
			curImage = choice;
		}
		return curImage;
	}
	
	/**
	 * Modularized for generateImgPath
	 * @param i representing the Images enumeration for the game element
	 * @return a Food in the correct location based on its type
	 */
	public GameElement genFood(Images i) {
		Random randLoc = new Random();
		GameElement newGameElement;
		if(i.equals(Images.FISH) || i.equals(Images.GOLDENFISH)) {
			int y = (frameHeight * 4) / 5 + randLoc.nextInt(frameHeight / 10) - frameHeight / 20;
			newGameElement = new Food(i.equals(Images.GOLDENFISH), frameWidth, y, 20, 0, i.getName(), i);
		} else {
			int y = frameHeight - Images.getCorrespondingImage(i).getHeight();
			newGameElement = new Food(i.equals(Images.GOLDENMOUSE), frameWidth, y, 20, 0, i.getName(), i);
		}
		return newGameElement;
	}
	
	/**
	 * Modularized for generateImgPath
	 * @param i representing the Images enumeration for the game element
	 * @return an Obstacle in the correct location based on its type
	 */
	public GameElement genObstacle(Images i) {
		Random randLoc = new Random();
		GameElement newGameElement;
		if(i.equals(Images.BUILDING) || i.equals(Images.FOX)) {
			int y = frameHeight - Images.getCorrespondingImage(i).getHeight();
			newGameElement = new Obstacle(frameWidth, y, 20, 0, i.getName(), i);
		}else if(i.equals(Images.TRASH)) {
			int y = (frameHeight * 4) / 5 + randLoc.nextInt(frameHeight / 10) - frameHeight / 20;
			newGameElement = new Obstacle(frameWidth, y, 20, 0, i.getName(), i);
		}else {
			int y = randLoc.nextInt(frameHeight / 2);
			newGameElement = new Obstacle(frameWidth, y, 20, 0, i.getName(), i);
		}
		return newGameElement;
	}
	
	/**
	 * Modularized for generateImgPath
	 * @param i representing the Images enumeration for the game element
	 * @return a Map and MapSprite in the correct location based on its type
	 */
	public GameElement genMap(Images i) {
		GameElement newGameElement;
		int x = this.frameWidth - 250;
		// x =1120;
		int y = 0;
		int xSpeed = 0;
		int ySpeed = 0;
		if(i.equals(Images.OSPREY_MINIMAP)) {
			int xLocOfBird = MiniMap.OSPREY_INITIAL_SMALL_BIRD_X_LOC;
			int yLocOfBird = MiniMap.OSPREY_INITIAL_SMALL_BIRD_Y_LOC;
			newGameElement = new MiniMap(x, y, xSpeed, ySpeed, i, Images.OSPREY_IMG_FOR_MINIMAP, xLocOfBird, yLocOfBird);
		}else {
			int xLocOfBird = MiniMap.NH_INITIAL_SMALL_BIRD_X_LOC;
			int yLocOfBird = MiniMap.NH_INITIAL_SMALL_BIRD_Y_LOC;
			newGameElement = new MiniMap(x, y, xSpeed, ySpeed, i, Images.NH_IMG_FOR_MINIMAP, xLocOfBird, yLocOfBird);
		}
		return newGameElement;
	}

	/**
	 * @return A GameElement . Uses the Images enumeration to select the path for an
	 *         image based off a random number. And depending on which type of image
	 *         it is, it will generate its starting position appropriately.
	 */
	private GameElement generateImgPath(int choice) {
		int curImage = randomImage(choice);
		Random randLoc = new Random();
		Images dir;
		GameElement newGameElement;

		switch (curImage) {
		case 0:
			dir = Images.BUILDING;
			newGameElement = genObstacle(Images.BUILDING);
			break;
		case 1:
			dir = Images.EAGLE;
			newGameElement = genObstacle(Images.EAGLE);
			break;
		case 2:
			dir = Images.TRASH;
			newGameElement = genObstacle(Images.TRASH);
			break;
		case 3:
			dir = Images.GOLDENFISH;
			newGameElement = genFood(Images.GOLDENFISH);
			break;
		case 4:
			dir = Images.FISH;
			newGameElement = genFood(Images.FISH);
			break;
		case 5:
			dir = Images.MOUSE;
			newGameElement = genFood(Images.MOUSE);
			break;
		case 6:
			dir = Images.GOLDENMOUSE;
			newGameElement = genFood(Images.GOLDENMOUSE);
			break;
		case 7:
			dir = Images.OWL;
			newGameElement = genObstacle(Images.OWL);
			break;
		case 8:
			dir = Images.FOX;
			newGameElement = genObstacle(Images.FOX);
			break;
		case 9:
			dir = Images.OSPREY_MINIMAP;
			newGameElement = genMap(dir);
			break;
		case 10:
			dir = Images.NH_MINIMAP;
			newGameElement = genMap(dir);
			break;
		default:
			dir = Images.RECTANGLE;
			int y = randLoc.nextInt(frameHeight);
			newGameElement = new Obstacle(frameWidth, y, 20, 0, dir.getName(), dir);
		}
		newGameElement.setType(dir);
		return newGameElement;
	}
		

	/**
	 * Spawns new collidable immediately
	 */
	void spawnGameElement() {
		onScreenCollidables.add(generateImgPath(-1));
	}

	/**
	 * Spawns new collidable immediately
	 * 
	 * @param choice the index of which type of collidable to spawn
	 */
	void spawnGameElement(int choice) {
		onScreenCollidables.add(generateImgPath(choice));
	}

	/**
	 * Controls the bird positions for the entering the nest animation upon level
	 * completion
	 */
	void enterNest() {
	}

	/**
	 * sets the quiz questions based upon the bird chosen
	 * @param birdType
	 */
	public void createQuestions(String birdType) {
		if (birdType.equals("Osprey")) {
			theQuestions = new QuizQuestions("quiz/osprey_questions.txt");
		} else {
			theQuestions = new QuizQuestions("quiz/northern_harrier_questions.txt");
		}
	}

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
	 * @return the spawnCount
	 */
	public int getSpawnCount() {
		return spawnCount;
	}

	/**
	 * @param spawnCount the spawnCount to set
	 */
	public void setSpawnCount(int spawnCount) {
		this.spawnCount = spawnCount;
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
	 * @return whether this game is in quiz mode or not
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

	/**
	 * @return the ImgWidth
	 */
	public int getImgWidth() {
		return imgWidth;
	}

	/**
	 * @param the imgWidth
	 */
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	/**
	 * @return the ImgHeight
	 */
	public int getImgHeight() {
		return imgHeight;
	}

	/**
	 * @param the ImgHeight
	 */
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	/**
	 * @return the Background
	 */
	public Background getBackground() {
		return this.background;
	}

	/**
	 * @param the background
	 */
	public void setBackground(Background background) {
		this.background = background;
	}

	/**
	 * @return is the user doing the quiz or not
	 */
	public boolean isDoingQuiz() {
		return this.doingQuiz;
	}

	/**
	 * @param b: sets the value of isDoingQuiz
	 */
	public void setDoingQuiz(boolean b) {
		this.doingQuiz = b;
	}

	/**
	 * @return the reachedEnd
	 */
	public boolean isReachedEnd() {
		return reachedEnd;
	}

	/**
	 * @param reachedEnd the reachedEnd to set
	 */
	public void setReachedEnd(boolean reachedEnd) {
		this.reachedEnd = reachedEnd;
	}

	/**
	 * @return the nestAnimation
	 */
	public NestAnimation getNestAnimation() {
		return nestAnimation;
	}

	/**
	 * @param nestAnimation the nestAnimation to set
	 */
	public void setNestAnimation(NestAnimation nestAnimation) {
		this.nestAnimation = nestAnimation;
	}

	/**
	 * Returns true if the bird is fainted
	 * 
	 * @return boolean indicating bird faintedness
	 */
	public boolean birdIsFainted() {
		return bird.isFainted();
	}
}
