import java.awt.event.*;
import javax.swing.JButton;

/**
 * Handles the flow of the game and passes information between the model and view.
 * Responsible for updating the game as it progresses.
 * 
 * @author 10-4
 *
 */
public class Controller implements KeyListener{
	/**
	 * The view for the game
	 */
	private View view;
	/**
	 * The model for the game
	 */
	private Model model;
	/**
	 * A button for the user to click
	 */
	private JButton button;
	/**
	 * Stores the key inputs by the player
	 */
	private KeyEvent keyInputs;
	
	/**
	 * Starts the game play. Will then prompt user to choose a bird to play as.
	 * Will update game as it progresses and end the game when the nest is reached.
	 */
	void start() {}
	
	/**
	 * Required from KeyListener. Will handle any key presses by the player
	 * 
	 * @param k The KeyEvent entered by the player
	 */
	@Override
	public void keyPressed(KeyEvent k) {}

	/**
	 * Required from KeyListener. Will handle any key releases by the player
	 * 
	 * @param k The KeyEvent entered by the player
	 */
	@Override
	public void keyReleased(KeyEvent k) {}

	/**
	 * Required from KeyListener. Will handle any key types by the player
	 * 
	 * @param k The KeyEvent entered by the player
	 */
	@Override
	public void keyTyped(KeyEvent k) {}
	
	/**
	 * Will call the start method when run.
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
		System.out.println("Welcome");
	}
}
