import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.Timer;
import java.io.*;
import java.util.List; 
import java.util.ArrayList; 

/**
 * Handles the flow of the game and passes information between the model and view.
 * Responsible for updating the game as it progresses.
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class Controller implements KeyListener, ActionListener, Serializable{
	/**
	 * The view for the game
	 */
	private View view;
	/**
	 * The model for the game
	 */
	private Model model;
	/**
	 * The Osprey button for the user to click
	 */
	private JButton Obutton;
	/**
	 * The Northern Harrier button for the user to click
	 */
	private JButton NHbutton;
	/**
	 * The list of answer buttons for the quiz
	 */
	private List<JButton> quizButtons; 
	/**
	 * Stores the key inputs by the player
	 */
	private KeyEvent keyInputs;
	/**
	 * Holds the Action code
	 */
	Action drawAction;
	/**
	 * Action for answering a quiz question
	 */
	Action quizAnswer; 
	/**
	 * Time between draw events
	 */
	final int drawDelay = 30; 
	 
	public Controller() {
		Obutton = new JButton("Osprey");
		NHbutton = new JButton("Northern Harrier");
		Obutton.addActionListener(this);
		NHbutton.addActionListener(this);
		quizAnswer = new AbstractAction() {
    		public void actionPerformed(ActionEvent e) {
    			model.endQuiz(((JButton)e.getSource()).getText().toString()); 
    			view.setPanel("O"); 
    		}
    	}; 
    	quizButtons = new ArrayList<JButton>(); 
    	
		view = new View(this);
		model = new Model(view.getFrameWidth(), view.getFrameHeight());
		
		//model.setBirdType(view.selectBirdType());
		view.setPanel("B");
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e) {
    			if (!model.isQuizMode()) {
    				model.update();
    				view.updateView(model.getBird(), model.getOnScreenCollidables(), model.getMiniMap(),model.getBackground());
    			} 
    			else {
    				quizButtons.clear(); 
    				QuizQuestion q = model.startQuiz();
    				for (String s: q.getAnswers()) {
    					JButton next = new JButton(s); 
    					next.addActionListener(quizAnswer); 
    					quizButtons.add(next); 
    				}
    				view.displayQuiz(model.startQuiz(), quizButtons);
    			}
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
		//System.out.println("A key has been pressed.");
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
	public void keyReleased(KeyEvent k) {
		//System.out.println("A key has been pressed.");
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			model.getBird().setDirection(0);
		} else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
			model.getBird().setDirection(0);
		}
	}

	/**
	 * Required from KeyListener. Will handle any key types by the player
	 * 
	 * @param k The KeyEvent entered by the player
	 */
	@Override
	public void keyTyped(KeyEvent k) {}
	
	/**
	 * Required from ActionListener. Will handle any buttons pressed by the player
	 * 
	 * @param e The ActionEvent triggered by the player
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Obutton) {
			view.setPanel("O");
			model.setBirdType(0);
			start();
		}
		else if(e.getSource() == NHbutton) {
			view.setPanel("NH");
			model.setBirdType(1);
			start();
		}
		
	}

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
	 * @return the Obutton
	 */
	public JButton getOButton() {
		return Obutton;
	}

	/**
	 * @param button the Obutton to set
	 */
	public void setOButton(JButton button) {
		this.Obutton = button;
	}
	/**
	 * @return the NHbutton
	 */
	public JButton getNHButton() {
		return NHbutton;
	}

	/**
	 * @param button the NHbutton to set
	 */
	public void setNHButton(JButton button) {
		this.NHbutton = button;
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
	/**
	 * @return the quizButtons
	 */
	public List<JButton> getQuizButtons(){
		return this.quizButtons; 
	}
}
