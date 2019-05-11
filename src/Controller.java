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
	 * True once the user selects a bird
	 */
	private boolean isGameInProgress;
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
	 * Northern Harrier button to continue to game after map
	 */
	private JButton NHPlanButton;
	/**
	 * Button to continue to bird selection after nesting animation
	 */
	private JButton doneAnimationButton;
	/**
	 * Button to serialize game during Osprey
	 */
	private JButton saveGameButtonO;
	/**
	 * Button to serialize game during Northern Harrier
	 */
	private JButton saveGameButtonNH;
	/**
	 * Button to start the game at a previously saved point
	 */
	private JButton reloadGameButton; 
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
	 * Action for animating the nesting
	 */
	Action animateAction;
	/**
	 * Time between draw events
	 */
	final static int DRAW_DELAY = 30; 
	/**
	 * Timer for handling game play
	 */
	Timer t; 
	/**
	 * Timer for handling the nesting animation
	 */
	Timer s;
	/**
	 * number of iterations through the game
	 */
	private int timesPlayed;
	
	/**
	 * constructor for Controller
	 * instantiates all buttons
	 * sets all panels
	 */
	public Controller() {
		timesPlayed = 0;
		Obutton = new JButton("Osprey");
		NHbutton = new JButton("Northern Harrier");
		OPlanButton = new JButton("Start Flight");
		NHPlanButton = new JButton("Start Flight");
		doneAnimationButton = new JButton("Continue");
		saveGameButtonO = new JButton("Save Game");
		saveGameButtonNH = new JButton("Save Game");
		reloadGameButton = new JButton("Reload Game");
		Obutton.addActionListener(this);
		NHbutton.addActionListener(this);
		OPlanButton.addActionListener(this);
		NHPlanButton.addActionListener(this);
		doneAnimationButton.addActionListener(this);
		saveGameButtonO.addActionListener(this);
		saveGameButtonNH.addActionListener(this);
		reloadGameButton.addActionListener(this);
		saveGameButtonO.setFocusable(false);
		saveGameButtonNH.setFocusable(false);
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
		drawAction = new AbstractAction() {
    		public void actionPerformed(ActionEvent e) {
    			if (!model.isQuizMode() && !model.isDoingQuiz() && isGameInProgress && !model.isReachedEnd()) {
    				model.update();
    				view.updateView(model.getBird(), model.getOnScreenCollidables(), model.getMiniMap(),
    								model.getBackground());
    			} else if (model.isQuizMode()) {
    				t.stop(); 
    				quizButtons.clear(); 
    				QuizQuestion q = model.startQuiz();
    				for (String s: q.getAnswers()) {
    					JButton next = new JButton(s); 
    					next.addActionListener(quizAnswer); 
    					quizButtons.add(next); 
    				}
    				view.displayQuiz(model.startQuiz(), quizButtons);
    			} else if (model.isReachedEnd()) {
    			    t.stop();
    			    model.configureNestAnimation();
    				view.setPanel("NA");
    				animate();
    			} 
    			if (model.birdIsFainted()) {
    				System.out.println("Resetting the game");
    				model = new Model(view.getFrameWidth(), view.getFrameHeight(), model.getBird().getBirdType());
    			}
    		}
    	};
	}
	
	/**
	 * @return the controller object
	 */
	protected Controller getController() {
		return this;
	}

	/**
	 * Starts the animation. Will continue until the user presses a button
	 * that will return to the bird selection screen
	 */
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
		if (e.getSource() == Obutton) {
			view.setPanel("OP"); 
		}
		else if (e.getSource() == NHbutton) {
			view.setPanel("NHP");
		}
		else if (e.getSource() == NHbutton) {
			view.setPanel("NHP");
			//System.out.println(model.getBird().getBirdType());
			//start();
		}
		else if (e.getSource() == OPlanButton) {
			model.setBird(new Bird(0,0,0,0,Images.OSPREY.getName()) );
			model.getBird().setBirdType("Osprey");
			model.createQuestions("Osprey");
			isGameInProgress = true;
			view.setPanel("O");
			//System.out.println(model.getBird().getBirdType());
			start();
		}
		else if (e.getSource() == NHPlanButton) {
			model.setBird(new Bird(0,0,0,0,Images.NORTHERN_HARRIER.getName()) );
			model.getBird().setBirdType("Northern Harrier");
			model.createQuestions("Northern Harrier");
			isGameInProgress = true;
			view.setPanel("NH");
			//System.out.println(model.getBird().getBirdType());
			start();
		}
		else if (e.getSource() == doneAnimationButton) {
			timesPlayed++;
			s.stop();
			doneAnimationButton.setVisible(false);
			if(timesPlayed%2==0) {
				Obutton.setVisible(true);
				NHbutton.setVisible(true);
			}
			else if(model.getBird().getBirdType().equals("osprey")) {
				Obutton.setVisible(false);
				NHbutton.setVisible(true);
			}
			else{
				Obutton.setVisible(true);
				NHbutton.setVisible(false);
			}
			view.setPanel("B");
			model = new Model(view.getFrameWidth(), view.getFrameHeight());
			//view.setNestAnimation(model.getNestAnimation());
		}
		else if(e.getSource() == saveGameButtonO || e.getSource() == saveGameButtonNH) {
			try {
				this.saveGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == reloadGameButton) {
			try {
				this.reloadGame();
				//System.out.println("pressed");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Serialize method which saves the state of the game
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public void saveGame() throws IOException{
		FileOutputStream out = new FileOutputStream("gameState.txt");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(model);
	}
	
	/**
	 * reloads the game at the previously saved point
	 * restarts the game at that point immediately
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("resource")
	public void reloadGame() throws IOException, ClassNotFoundException {
		FileInputStream in = new FileInputStream("gameState.txt");
		ObjectInputStream ois = new ObjectInputStream(in);
		model = (Model) ois.readObject();
		if(model.getBird().getBirdType().equalsIgnoreCase("osprey")){
			view.setPanel("O");
		}else {
			view.setPanel("NH");
		}
		isGameInProgress = true;
		this.start();
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
	public JButton getDoneAnimationButton() {
		return doneAnimationButton;
	}

	/**
	 * @param doneAmination the doneAmination to set
	 */
	public void setDoneAnimationButton(JButton doneAminationButton) {
		this.doneAnimationButton = doneAminationButton;
	}
	/**
	 * @return the saveGameButtonO
	 */
	public JButton getSaveGameButtonO() {
		return saveGameButtonO;
	}
	/**
	 * @param saveGameButtonO the saveGameButtonO to set
	 */
	public void setSaveGameButtonO(JButton saveGameButtonO) {
		this.saveGameButtonO = saveGameButtonO;
	}
	/**
	 * @return the saveGameButtonNH
	 */
	public JButton getSaveGameButtonNH() {
		return saveGameButtonNH;
	}
	/**
	 * @param saveGameButtonNH the saveGameButtonNH to set
	 */
	public void setSaveGameButtonNH(JButton saveGameButtonNH) {
		this.saveGameButtonNH = saveGameButtonNH;
	}
	/**
	 * @return the reloadGameButton
	 */
	public JButton getReloadGameButton() {
		return reloadGameButton;
	}
	/**
	 * @param reloadGameButton the reloadGameButton to set
	 */
	public void setReloadGameButton(JButton reloadGameButton) {
		this.reloadGameButton = reloadGameButton;
	}
}
