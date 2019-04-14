import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.Timer;

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
	 * Holds the Action code
	 */
	Action drawAction;
	/**
	 * Time between draw events
	 */
	final int drawDelay = 30;
	
	@SuppressWarnings("serial")
	public Controller() {
		view = new View(this);
		model = new Model(view.getWidth(), view.getHeight());
		//model.setBirdType(view.selectBirdType());
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e) {
    			model.update();
    			view.updateView(model.getBird(), model.getOnScreenCollidables(), model.getMiniMap());
    		}
    	};
	}
	
	/**
	 * Starts the game play. Will then prompt user to choose a bird to play as.
	 * Will update game as it progresses and end the game when the nest is reached.
	 */
	void start() {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Timer t = new Timer(drawDelay, drawAction);
				t.start();
			}
		});
	}
	
	/**
	 * Required from KeyListener. Will handle any key presses by the player
	 * 
	 * @param k The KeyEvent entered by the player
	 */
	@Override
	public void keyPressed(KeyEvent k) {
		System.out.println("A key has been pressed.");
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			model.getBird().setDirection(1);
		} else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
			model.getBird().setDirection(-1);
		}
	}

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
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the button
	 */
	public JButton getButton() {
		return button;
	}

	/**
	 * @param button the button to set
	 */
	public void setButton(JButton button) {
		this.button = button;
	}

	/**
	 * @return the keyInputs
	 */
	public KeyEvent getKeyInputs() {
		return keyInputs;
	}

	/**
	 * @param keyInputs the keyInputs to set
	 */
	public void setKeyInputs(KeyEvent keyInputs) {
		this.keyInputs = keyInputs;
	}
}
