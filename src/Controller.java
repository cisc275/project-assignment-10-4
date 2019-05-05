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
	 * Osprey button to continue to game after map
	 */
	private JButton OPlanButton;
	/**
	 * Osprey button to continue to game after map
	 */
	private JButton NHPlanButton;
	
	private JButton doneAminationButton;
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
	
	Action animateAction;
	
	/**
	 * Time between draw events
	 */
	final static int DRAW_DELAY = 30; 
	
	/**
	 * 
	 */
	Timer t; 
	
	Timer s;
	 
	public Controller() {
		Obutton = new JButton("Osprey");
		NHbutton = new JButton("Northern Harrier");
		OPlanButton = new JButton("Start Flight");
		NHPlanButton = new JButton("Start Flight");
		doneAminationButton = new JButton("Continue");
		Obutton.addActionListener(this);
		NHbutton.addActionListener(this);
		OPlanButton.addActionListener(this);
		NHPlanButton.addActionListener(this);
		doneAminationButton.addActionListener(this);
		quizAnswer = new AbstractAction() {
    		public void actionPerformed(ActionEvent e) {
    			model.endQuiz(((JButton)e.getSource()).getText().toString()); 
    			view.endQuiz(); 
    			t.start(); 
    		}
    	};
    	quizButtons = new ArrayList<JButton>(); 
    	
		view = new View(this);
		model = new Model(view.getFrameWidth(), view.getFrameHeight());
		
		animateAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				model.updateNestAnimation();
				view.nestAnimationUpdate(model.getNestAnimation());
			}
		};
		
		
		//model.setBirdType(view.selectBirdType());
		view.setPanel("B");
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e) {
    			if (!model.isQuizMode() && !model.isDoingQuiz() && !model.isReachedEnd()) {
    				model.update();
    				view.updateView(model.getBird(), model.getOnScreenCollidables(), model.getMiniMap(),model.getBackground());
    			} 
    			else if (model.isQuizMode()){
    				t.stop(); 
    				quizButtons.clear(); 
    				QuizQuestion q = model.startQuiz();
    				for (String s: q.getAnswers()) {
    					JButton next = new JButton(s); 
    					next.addActionListener(quizAnswer); 
    					quizButtons.add(next); 
    				}
    				view.displayQuiz(model.startQuiz(), quizButtons);
    			}
    			else if(model.isReachedEnd()) {
    			    t.stop();
    			    model.configureNestAnimation();
    				view.setPanel("NA");
    				animate();
    			}
    			
    				
    		}
    		
    	};
	}
	
	void animate() {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				s = new Timer(DRAW_DELAY, animateAction);
				s.start();
			}
		});
	}
	
	/**
	 * Starts the game play. Will then prompt user to choose a bird to play as.
	 * Will update game as it progresses and end the game when the nest is reached.
	 */
	void start() {
			EventQueue.invokeLater(new Runnable(){
				public void run(){
					t = new Timer(DRAW_DELAY, drawAction);
					t.start();
				}
			});
	}
	
	/**
	 * Required from KeyListener. Will handle any key presses by the player
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
			view.setPanel("OP");
			model.getBird().setBirdType("Osprey");
			//System.out.println(model.getBird().getBirdType());
			//start();
		}
		else if(e.getSource() == NHbutton) {
			view.setPanel("NHP");
			model.getBird().setBirdType("Northern Harrier");
			//System.out.println(model.getBird().getBirdType());
			//start();
		}
		else if(e.getSource() == OPlanButton) {
			view.setPanel("O");
			//System.out.println(model.getBird().getBirdType());
			start();
		}
		else if(e.getSource() == NHPlanButton) {
			view.setPanel("NH");
			//System.out.println(model.getBird().getBirdType());
			start();
		}
		else if(e.getSource() == doneAminationButton) {
			view.setPanel("B");
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
	public void setNHbutton(JButton button) {
		this.NHbutton = button;
	}

	/**
	 * @return the OPlanButton
	 */
	public JButton getOPlanButton() {
		return OPlanButton;
	}

	/**
	 * @param button the Obutton to set
	 */
	public void setOPlanButton(JButton button) {
		this.OPlanButton = button;
	}
	
	/**
	 * @return the NHPlanButton
	 */
	public JButton getNHPlanButton() {
		return NHPlanButton;
	}

	/**
	 * @param button the Obutton to set
	 */
	public void setNHPlanButton(JButton button) {
		this.NHPlanButton = button;
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

	/**
	 * @return the doneAmination
	 */
	public JButton getDoneAminationButton() {
		return doneAminationButton;
	}

	/**
	 * @param doneAmination the doneAmination to set
	 */
	public void setDoneAminationButton(JButton doneAminationButton) {
		this.doneAminationButton = doneAminationButton;
	}
}
