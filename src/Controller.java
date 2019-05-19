import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.Timer;
import java.io.*;
import java.util.List; 
import java.util.ArrayList;
import java.util.HashMap; 

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
	 * Maps names of buttons to their listener 
	 */
	private HashMap<String, ActionListener> listeners; 
	/**
	 * The list of answer buttons for the quiz
	 */
	private List<JButton> quizButtons; 
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
	 * Action for animating the tutorial
	 */
	Action tutorialAction;
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
	 * Timer for handling the tutorial
	 */
	Timer r;
	/**
	 * number of iterations through the game
	 */
	private int timesPlayed;
	/**
	 * True if the tutorial is in progress
	 */
	private boolean tutorialMode;
	
	/**
	 * constructor for Controller
	 * instantiates all buttons
	 * sets all panels
	 */
	public Controller() {
		timesPlayed = 0;
		 
		listeners  = new HashMap<String, ActionListener>(); 
		createListeners(); 
		quizAnswer = new AbstractAction() {
    		public void actionPerformed(ActionEvent e) {
    			model.endQuiz(((JButton)e.getSource()).getText().toString()); 
    			view.endQuiz(); 
    			t.start(); 
    		}
    	};
    	quizButtons = new ArrayList<JButton>(); 
    	
		view = new View();
		model = new Model(view.getFrameWidth(), view.getFrameHeight());
		for (String s: view.getButtons().keySet()) {
			
			if (s.substring(0, 4).equals("save")) {
				view.getButtons().get(s).addActionListener(listeners.get("save"));
				view.getButtons().get(s).setFocusable(false);
			}
			else {
				view.getButtons().get(s).addActionListener(listeners.get(s));
			}
			
		}
		view.getFrame().addKeyListener(this);
		animateAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				model.updateNestAnimation();
				view.nestAnimationUpdate(model.getNestAnimation());
			}
		};
		
		tutorialAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				model.updateTutorial();
				view.tutorialUpdate(model.getTutorial());
				
			}
		};
		
		
		//model.setBirdType(view.selectBirdType());
		view.setPanel("TP");
		drawAction = new AbstractAction() {
    		public void actionPerformed(ActionEvent e) {
    			if (!model.isQuizMode() && !model.isDoingQuiz() && isGameInProgress && !model.isReachedEnd()) {
    				model.update();
    				view.updateView(model.getBird(), model.getOnScreenCollidables(), model.getMiniMap(),
    								model.getBackground());
    			} else if (model.isQuizMode()) {
    				 
    				quizButtons.clear(); 
    				QuizQuestion q = model.startQuiz();
    				for (String s: q.getAnswers()) {
    					JButton next = new JButton(s); 
    					next.addActionListener(quizAnswer); 
    					quizButtons.add(next); 
    				}
    				view.displayQuiz(model.startQuiz(), quizButtons);
    				t.stop();
    			} else if (model.isReachedEnd()) {
    			    t.stop();
    			    view.getCurrentPanel().remove(view.getScore());
    			    model.configureNestAnimation();
    				view.setPanel("NA");
    				animate();
    			} 
    			if (model.birdIsFainted()) {
    				System.out.println("Resetting the game");
    				t.stop();
    				view.getCurrentPanel().getComponent(1).setVisible(true);
    				//model = new Model(view.getFrameWidth(), view.getFrameHeight(), model.getBird().getBirdType());
    			}
    		}
    	};
    	tutorialMode = true;
    	this.executeTutorial();
	}
	
	/**
	 * @return the controller object
	 */
	protected Controller getController() {
		return this;
	}
	public void createListeners() {
		listeners.put("noStaminaO", new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				view.getCurrentPanel().getComponent(1).setVisible(false);
				view.setPanel("B");
				view.show("OButton"); 
				view.show("NHButton"); 
				model = new Model(view.getFrameWidth(), view.getFrameHeight());
			}
		});
		listeners.put("noStaminaNH", new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				view.getCurrentPanel().getComponent(1).setVisible(false);
				view.setPanel("B");
				view.show("OButton"); 
				view.show("NHButton"); 
				model = new Model(view.getFrameWidth(), view.getFrameHeight());
			}
		});
		listeners.put("tutorial",new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setPanel("TP");
				tutorialMode = true;
				executeTutorial();
			}
		});
		listeners.put("endTutorial",new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setPanel("B");
				r.stop();
				tutorialMode = false;
			}
		});
		listeners.put("OButton", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setPanel("OP");
			}
		}); 
		listeners.put("NHButton", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setPanel("NHP");
			}
		}); 
		listeners.put("OPlanButton",  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setBird(new Bird(0,0,0,0,Images.OSPREY.getName()) );
				model.getBird().setBirdType("Osprey");
				model.createQuestions("Osprey");
				isGameInProgress = true;
				view.setPanel("O");
				//System.out.println(model.getBird().getBirdType());
				start();
			}
		}); 
		listeners.put("NHPlanButton", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setBird(new Bird(0,0,0,0,Images.NORTHERN_HARRIER.getName()) );
				model.getBird().setBirdType("Northern Harrier");
				model.createQuestions("Northern Harrier");
				isGameInProgress = true;
				view.setPanel("NH");
				//System.out.println(model.getBird().getBirdType());
				start();
			}
		}); 
		listeners.put("doneAnimationButton", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timesPlayed++;
				s.stop();
				//doneAnimationButton.setVisible(false);
				view.hide("doneAnimationButton"); 
				if(timesPlayed%2==0) {
					//Obutton.setVisible(true);
					//NHbutton.setVisible(true);
					view.show("OButton"); 
					view.show("NHButton"); 
					
				}
				else if(model.getBird().getBirdType().equals("osprey")) {
					//Obutton.setVisible(false);
					//NHbutton.setVisible(true);
					view.hide("OButton"); 
					view.show("NHButton"); 
				}
				else{
					//Obutton.setVisible(true);
					//NHbutton.setVisible(false);
					view.show("OButton"); 
					view.hide("NHButton"); 
				}
				view.setPanel("B");
				model = new Model(view.getFrameWidth(), view.getFrameHeight());
				//view.setNestAnimation(model.getNestAnimation());
			}
		}); 
		listeners.put("save",  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); 
		listeners.put("reloadGameButton",  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reloadGame();
					//System.out.println("pressed");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); 
		
	}
	
	/**
	 * Starts the tutorial. Will continue until the user presses the continue button
	 * at the end of the tutorial
	 */
	void executeTutorial() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				model.setTutorial(new Tutorial(view.getFrameWidth(),view.getFrameHeight()));
				view.getTutorialPanel().getComponent(0).setVisible(false);
				r = new Timer(DRAW_DELAY, tutorialAction);
				r.start();
			}
		});
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
				if (model.isQuizMode()) {
					t.stop(); 
				}
			}
		});
	}
	
	/**
	 * Required from KeyListener. Will handle any key presses by the player
	 * @param k The KeyEvent entered by the player
	 */
	@Override
	public void keyPressed(KeyEvent k) {
		if(!tutorialMode) {
			if (k.getKeyCode() == KeyEvent.VK_UP) {
				model.getBird().setDirection(1);
			} else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
				model.getBird().setDirection(-1);
			}
		}
		else {
			if (k.getKeyCode() == KeyEvent.VK_UP && model.getTutorial().isAvoidObstacle()) {
				model.getTutorial().getBird().setDirection(1);
			} else if (k.getKeyCode() == KeyEvent.VK_DOWN && model.getTutorial().isCollectFood()) {
				model.getTutorial().getBird().setDirection(-1);
			}
		}
		
	}

	/**
	 * Required from KeyListener. Will handle any key releases by the player
	 * 
	 * @param k The KeyEvent entered by the player
	 */
	@Override
	public void keyReleased(KeyEvent k) {
		if(!tutorialMode) {
			//System.out.println("A key has been pressed.");
			if (k.getKeyCode() == KeyEvent.VK_UP) {
				model.getBird().setDirection(0);
			} else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
				model.getBird().setDirection(0);
			}
		}
		else {
			if (k.getKeyCode() == KeyEvent.VK_UP) {
				model.getTutorial().getBird().setDirection(0);
			} else if (k.getKeyCode() == KeyEvent.VK_DOWN) {
				model.getTutorial().getBird().setDirection(0);
			}
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
	
	
}
