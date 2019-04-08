import java.util.*;

/**
 * Contains the different componenets of the bird game. As the game updates, 
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
	 * A list containing all Collidable objects that are currently on the screen
	 */
	private List<Collidable>onScreenCollidables;
	/**
	 * The distance currently traveled
	 */
	private int distance;
	/**
	 * The total distance needed to be traveled
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
	 * Used to update the current status and positions of the different game components.
	 * Will call helper update methods for different components.
	 */
	void update() {}
	
	/**
	 * Used to update the current status and position of the bird based on user input
	 * and game states.
	 */
	void updateBird() {}
	
	/**
	 * Updates the collidable objects to update positions and remove objects with positions
	 * not on the screen.
	 */
	void updateCollides() {}
	
	/**
	 * Updates the background depending on the distance the player has reached.
	 */
	void updateBackground() {}
	
	/**
	 * Updates the MiniMap to display the current traveled status
	 */
	void updateMiniMap() {}
	
	/**
	 * Checks for collision between the Bird and any Collidable on screen.
	 * 
	 * @return The Collidable that has been collided with by the bird
	 */
	Collidable collisionDetection() {return null;}
	
	/**
	 * Starts a quiz if the bird has eaten a special food.
	 * 
	 * @return The quiz question that will be diplayed for the player to answer.
	 */
	QuizQuestion startQuiz() {return new QuizQuestion();}
	
	/**
	 * Ends the quiz and restarts the player controlling the bird. Handles powerup start
	 * if the player answered the quiz correctly.
	 */
	void endQuiz() {}
	
	/**
	 * Spawns new collidables periodically.
	 */
	void spawnCollidables() {}
	
	/**
	 * Controls the bird positions for the entering the nest animation upon level completion
	 */
	void enterNest() {}

	public Bird getBird() {
		return bird;
	}

	public void setBird(Bird bird) {
		this.bird = bird;
	}

	public List<Collidable> getOnScreenCollidables() {
		return onScreenCollidables;
	}

	public void setOnScreenCollidables(List<Collidable> onScreenCollidables) {
		this.onScreenCollidables = onScreenCollidables;
	}

	public boolean isBirdMode() {
		return birdMode;
	}

	public void setBirdMode(boolean birdMode) {
		this.birdMode = birdMode;
	}

	public boolean isQuizMode() {
		return quizMode;
	}

	public void setQuizMode(boolean quizMode) {
		this.quizMode = quizMode;
	}
}
