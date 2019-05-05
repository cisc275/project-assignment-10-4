import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JButton;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Handles the visual component of the game. Changes the positions of objects as
 * the game progresses and according to player input.
 * 
 * @author 10-4
 *
 */

@SuppressWarnings("serial")
public class View extends JPanel implements Serializable {
	/**
	 * A Dimension that stores the size of the player's screen
	 */
	static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * The frame used to display the game
	 */
	private JFrame frame;
	/**
	 * The panel that is currently active
	 */
	private JPanel currentPanel;
	/**
	 * The panel at the beginning with the bird choices
	 */
	private ButtonPanel buttonPanel;
	/**
	 * The panel that will be drawn on to display the Osprey game
	 */
	private DrawPanel OPanel;
	/**
	 * The panel that will be drawn on to display the Northern Harrier game
	 */
	private DrawPanel NHPanel;
	/**
	 * The panel that will be on display when the quiz starts
	 */
	private QuizPanel quizPanel;
	/**
	 * The deck of panels
	 */
	private JPanel cards;
	/**
	 * The font that is used for text on buttons
	 */
	private Font buttonFont;
	/**
	 * Width of the frame to display the game
	 */
	// private static final int FRAMEWIDTH = (int)SCREENSIZE.getWidth();
	private static final int FRAMEWIDTH = 1920;
	/**
	 * Height of the frame to display the game
	 */
	// private static final int FRAMEHEIGHT = (int)SCREENSIZE.getHeight();
	private static final int FRAMEHEIGHT = 1080;
	/**
	 * The miniMap that is on the screen
	 */
	private MiniMap miniMap;
	/**
	 * The bird that is being controlled by the player to be displayed
	 */
	private Bird bird;
	/**
	 * The list of GameElements to be displayed on the screen
	 */
	private List<GameElement> elements;
	/**
	 * The background of the game that is scrolling in when displayed
	 */
	private Background background;
	/**
	 * Image for the bird selection background
	 */
	private BufferedImage buttonPanelBackground;
	/**
	 * Image for the osprey flight map background
	 */
	private BufferedImage opreyFlightPlanBack;
	/**
	 * Image for the osprey flight map
	 */
	private BufferedImage opreyFlightPlan;
	/**
	 * Panel for osprey flight map
	 */
	private OspreyFlightPlan ospreyPlan;
	/**
	 * Image for the norther harrier flight map background
	 */
	private BufferedImage NHFlightPlanBack;
	/**
	 * Image for the northern harrier flight map
	 */
	private BufferedImage NHFlightPlan;
	/**
	 * Panel for the northern harrier flight map
	 */
	private NHFlightPlan NHPlan;
	/**
	 * Panel to display nesting animation
	 */
	private NestAnimationPanel animation;
	/**
	 * The nesting animation to be displayed
	 */
	private NestAnimation nestAnimation;
	private Component score;

	/**
	 * View constructor, sets up the frame and its contents
	 * 
	 * @param c reference to the Controller object in use
	 */
	public View(Controller c) {
		System.out.println("(" + FRAMEWIDTH + "," + FRAMEHEIGHT + ")");
		frame = new JFrame();
		cards = new JPanel(new CardLayout());

		this.setUpButtonPanel(c);
		this.setUpOspreyPlan(c);
		this.setUpNHPlan(c);
		this.setUpAnimation(c);

		OPanel = new DrawPanel();
		OPanel.setBackground(Color.gray);
		NHPanel = new DrawPanel();
		NHPanel.setBackground(Color.gray);

		cards.add(buttonPanel, "B");
		cards.add(ospreyPlan, "OP");
		cards.add(OPanel, "O");
		cards.add(NHPanel, "NH");
		cards.add(NHPlan, "NHP");
		cards.add(animation, "NA");
		// cards.add(quizPanel, "Q");

		currentPanel = buttonPanel;

		setUpFrame(c);

		System.out.print(SCREENSIZE);
	}

	/**
	 * Sets up the JFrame with its attributes
	 * 
	 * @param c reference to the Controller object in use
	 */
	void setUpFrame(Controller c) {
		frame.add(cards);
		frame.setFocusable(true);
		frame.addKeyListener(c);
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}

	/**
	 * Sets up panel for selection of bird
	 * 
	 * @param c reference to the Controller object in use
	 */
	void setUpButtonPanel(Controller c) {
		buttonPanelBackground = createImage("images/selection_background_1080.png");
		buttonPanel = new ButtonPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBackground(Color.gray);
		buttonFont = new Font("Verdana", Font.BOLD, 50);
		c.getOButton().setFont(buttonFont);
		c.getNHButton().setFont(buttonFont);
		c.getNHButton().setBounds(100, 20, 600, 100);
		c.getOButton().setBounds(1300, 20, 400, 100);
		JLabel text = new JLabel();
		text.setText("Choose a Bird");
		text.setFont(buttonFont);
		text.setBounds(800, 600, 400, 200);
		text.setBackground(Color.blue);
		buttonPanel.add(text);
		buttonPanel.add(c.getNHButton());
		buttonPanel.add(c.getOButton());
	}

	/**
	 * Sets up panel for osprey flight plan map
	 * 
	 * @param c reference to the Controller object in use
	 */
	void setUpOspreyPlan(Controller c) {
		opreyFlightPlanBack = createImage("images/osprey_flight_plan_yellow_background.png");
		opreyFlightPlan = createImage("images/oprey_flight_plan_1080.png");
		ospreyPlan = new OspreyFlightPlan();
		c.getOPlanButton().setFont(buttonFont);
		ospreyPlan.add(c.getOPlanButton());
	}

	/**
	 * Sets up panel for northern harrier flight plan map
	 * 
	 * @param c reference to the Controller object in use
	 */
	void setUpNHPlan(Controller c) {
		NHPlan = new NHFlightPlan();
		c.getNHPlanButton().setFont(buttonFont);
		NHPlan.add(c.getNHPlanButton());
		NHFlightPlanBack = createImage("images/nh_flight_plan_green_background.png");
		NHFlightPlan = createImage("images/nh_flight_plan_1080.png");
	}

	/**
	 * Sets up panel for nest landing animation
	 * 
	 * @param c reference to the Controller object in use
	 */
	void setUpAnimation(Controller c) {
		animation = new NestAnimationPanel();
		c.getDoneAnimationButton().setFont(buttonFont);
		animation.add(c.getDoneAnimationButton());
		animation.getComponent(0).setVisible(false);
	}

	/**
	 * Will update the game display based on changes to different game components.
	 * If a game component does not currently have an image, it gets it assigned
	 * 
	 * @param bird       The bird the user controls
	 * @param elements   The list of GameElement objects on screen
	 * @param miniMap    The MiniMap that displays progress
	 * @param background The background that displays and scrolls
	 */
	void updateView(Bird bird, List<GameElement> elements, MiniMap miniMap, Background background) {
		this.background = background;
		this.bird = bird;
		if (this.bird.getImage() == null) {
			this.bird.setImage(createImage("images/big_bird_animate.png"));
		}
		if (this.bird.getStaminaPics()[0] == null) {
			BufferedImage[] arr = new BufferedImage[6];
			arr[0] = createImage("images/0_health.png");
			arr[1] = createImage("images/1_health.png");
			arr[2] = createImage("images/2_health.png");
			arr[3] = createImage("images/3_health.png");
			arr[4] = createImage("images/4_health.png");
			arr[5] = createImage("images/5_health.png");
			this.bird.setStaminaPics(arr);
		}
		if (this.bird.getPoweredUpPics()[0] == null) {
			this.bird.setPoweredUpPics(createImage("images/powers.png"));
		}
		this.elements = elements;
		this.miniMap = miniMap;
		// this.miniMap = (MiniMap)elements.get(0); //first element will always be a
		// MiniMap
		if (miniMap.getSmallBird() == null) {
			miniMap.setSmallBird(createImage(miniMap.getMapSpriteFile()));
		}

		if (miniMap.getImage() == null) {
			miniMap.setImage(createImage(miniMap.getImagePath()));
		}

		Iterator<GameElement> it = this.elements.iterator();
		GameElement e;
		while (it.hasNext()) {
			e = it.next();
			if (e.getImage() == null) {
				e.setImage(createImage(e.getImagePath()));
			}
		}
		if (score != null) currentPanel.remove(score);
		score = new JLabel("Score: " + bird.getPoints(), SwingConstants.CENTER); 
		Font font = new Font("Verdana", Font.BOLD, FRAMEHEIGHT / 35); 
		score.setFont(font);
		score.setBounds(0, 0, FRAMEWIDTH / 2, FRAMEHEIGHT / 2);
		currentPanel.add(score);
		frame.repaint();
	}

	/**
	 * Creates an image to be displayed
	 * 
	 * @param f a File to generate image from
	 * @return BufferedImage the generated image
	 */
	BufferedImage createImage(String file) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(file));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Draws an image onto the frame
	 */
	void drawImage() {
	}

	/**
	 * Displays a quiz question that will need to be answered by the player to
	 * progress
	 */
	void displayQuiz(QuizQuestion question, List<JButton> buttons) {
		quizPanel = new QuizPanel();
		quizPanel.setBackground(Color.gray);
		quizPanel.setLayout(null);
		int xscale = 2 * 2;
		int yscale = 4 * 2;
		JLabel text = new JLabel(question.getQuestion(), SwingConstants.CENTER);
		Font font = new Font("Verdana", Font.BOLD, FRAMEHEIGHT / 35);
		text.setFont(font);
		text.setBounds(0, 0, FRAMEWIDTH / 2, FRAMEHEIGHT / 8);
		quizPanel.add(text);
		int xshift = 0;
		int yshift = 0;
		int count = 0;
		for (JButton b : buttons) {
			b.setFont(font);

			b.setBounds(0 + xshift, FRAMEHEIGHT / yscale + yshift, FRAMEWIDTH / xscale, FRAMEHEIGHT / yscale);
			count += 1;
			xshift += ((FRAMEWIDTH / xscale) * (count % 2));
			xshift %= 2 * FRAMEWIDTH / xscale;
			yshift += (FRAMEHEIGHT / yscale) * ((count - 1) % 2);
			quizPanel.add(b);
		}
		quizPanel.setBounds(FRAMEWIDTH / 4, FRAMEHEIGHT / 4, FRAMEWIDTH / 2, FRAMEHEIGHT / 2);
		currentPanel.add(quizPanel);
		currentPanel.repaint();
	}

	/**
	 * Removes the quiz from the current panel being displayed
	 */
	void endQuiz() {
		currentPanel.remove(quizPanel);
	}

	/**
	 * Handles the animation for the bird landing in the nest when the player
	 * reaches the end of the game
	 * 
	 * @param nestAnimation the instance of nestAnimation that has been updated
	 */
	void nestAnimationUpdate(NestAnimation nestAnimation) {
		this.setNestAnimation(nestAnimation);
		if (this.getNestAnimation().getBird() == null) {
			this.getNestAnimation().setBird(this.bird.getPics()[2]);
		}
		if (this.getNestAnimation().getBackground() == null) {
			if (this.bird.getBirdType().equals("osprey")) {
				this.getNestAnimation().setBackground(createImage("images/osprey_nest_background_1080.png"));
			} else {
				this.getNestAnimation().setBackground(createImage("images/nh_nest_background_1080.png"));
			}
		}
		if (this.getNestAnimation().isDoneAnimation()) {
			this.getNestAnimation().setBird(bird.getPics()[1]);
			currentPanel.getComponent(0).setVisible(true);
		}
		currentPanel.repaint();
	}

	/**
	 * Updates the display of the bird
	 */
	void updateBird() {
	}

	/**
	 * Updates the display of the onscreen collidables
	 * 
	 * @param bird the bird controlled by the player
	 */
	void updateCollidables(Bird bird) {
	}

	/**
	 * Updates the display of the minimap based on game progress
	 * 
	 * @param miniMap The minimap to be updated and displayed
	 */
	void updateMiniMap(MiniMap miniMap) {
	}

	/**
	 * update the background based on how far the player has traveled.
	 */
	void updateBackground() {
	}

	/**
	 * @param String the panel to set
	 */
	public void setPanel(String name) {
		((CardLayout) cards.getLayout()).show(cards, name);
		switch (name) {
		case "O":
			currentPanel = OPanel;
			break;
		case "NH":
			currentPanel = NHPanel;
			break;
		case "NA":
			currentPanel = animation;
			break;
		case "OP":
			currentPanel = ospreyPlan;
			break;
		case "NHP":
			currentPanel = NHPlan;
			break;
		case "B":
			currentPanel = buttonPanel;
			break;
		}
	}

	/**
	 * @return the currentPanel
	 */
	public JPanel getCurrentPanel() {
		return currentPanel;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * @return the buttonPanel
	 */
	public ButtonPanel getbuttonPanel() {
		return buttonPanel;
	}

	/**
	 * @param drawPanel the buttonPanel to set
	 */
	public void setbuttonPanel(ButtonPanel drawPanel) {
		this.buttonPanel = drawPanel;
	}

	/**
	 * @return the OPanel
	 */
	public DrawPanel getOPanel() {
		return OPanel;
	}

	/**
	 * @param drawPanel the OPanel to set
	 */
	public void setOPanel(DrawPanel drawPanel) {
		this.OPanel = drawPanel;
	}

	/**
	 * @return the quizPanel
	 */
	public QuizPanel getQuizPanel() {
		return quizPanel;
	}

	/**
	 * @param drawPanel the quizPanel to set
	 */
	public void setQuizPanel(QuizPanel drawPanel) {
		this.quizPanel = drawPanel;
	}

	/**
	 * @return the NHPanel
	 */
	public DrawPanel getNHPanel() {
		return NHPanel;
	}

	/**
	 * @return the cards
	 */
	public JPanel getCards() {
		return cards;
	}

	/**
	 * @param JPanel the cards to set
	 */
	public void setCards(JPanel jPanel) {
		cards = jPanel;
	}

	/**
	 * @param drawPanel the NHPanel to set
	 */
	public void setNHPanel(DrawPanel drawPanel) {
		this.NHPanel = drawPanel;
	}

	/**
	 * @return the frameWidth
	 */
	public int getFrameWidth() {
		return FRAMEWIDTH;
	}

	/**
	 * @return the frameHeight
	 */
	public int getFrameHeight() {
		return FRAMEHEIGHT;
	}

	/**
	 * 
	 * @param bird the bird to set
	 */
	public void setBird(Bird bird) {
		this.bird = bird;
	}

	/**
	 * 
	 * @return the bird
	 */
	public Bird getBird() {
		return this.bird;
	}

	/**
	 * 
	 * @param elements the elements to set
	 */
	public void setElements(List<GameElement> elements) {
		this.elements = elements;
	}

	/**
	 * 
	 * @return the elements
	 */
	public List<GameElement> getElements() {
		return this.elements;
	}

	/**
	 * 
	 * @param background the background to set
	 */
	public void setGameBackground(Background background) {
		this.background = background;
	}

	/**
	 * 
	 * @return the background
	 */
	public Background getGameBackground() {
		return this.background;
	}

	/**
	 * 
	 * @return a DrawPanel
	 */
	public DrawPanel getDrawPanel() {
		return new DrawPanel();
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
	 * Panel to display the nesting animation
	 * 
	 * @author 10-4
	 *
	 */
	class NestAnimationPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);
			try {
				g.drawImage(getNestAnimation().getBackground(), 0, 0, null);
				g.drawImage(getNestAnimation().getBird(), getNestAnimation().getBirdx(), getNestAnimation().getBirdy(),
						null);
			} catch (Exception e) {

			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(FRAMEWIDTH, FRAMEHEIGHT);
		}
	}

	/**
	 * Panel to display northern harrier flight plan map
	 * 
	 * @author 10-4
	 *
	 */
	class NHFlightPlan extends JPanel {
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);
			g.drawImage(NHFlightPlanBack, 0, 0, null);
			g.drawImage(NHFlightPlan, 310, 80, null);
		}

		public Dimension getPreferredSize() {
			return new Dimension(FRAMEWIDTH, FRAMEHEIGHT);
		}
	}

	/**
	 * Panel to display osprey flight plan map
	 * 
	 * @author 10-4
	 *
	 */
	class OspreyFlightPlan extends JPanel {
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);
			g.drawImage(opreyFlightPlanBack, 0, 0, null);
			g.drawImage(opreyFlightPlan, 310, 0, null);
		}

		public Dimension getPreferredSize() {
			return new Dimension(FRAMEWIDTH, FRAMEHEIGHT);
		}
	}

	/**
	 * Panel to display bird selection
	 * 
	 * @author 10-4
	 *
	 */
	class ButtonPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);
			g.drawImage(buttonPanelBackground, 0, 0, null);
		}

		public Dimension getPreferredSize() {
			return new Dimension(FRAMEWIDTH, FRAMEHEIGHT);
		}
	}

	/**
	 * Panel to display quiz to be answered by player
	 * 
	 * @author 10-4
	 *
	 */
	class QuizPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}

		public Dimension getPreferredSize() {
			return new Dimension(FRAMEWIDTH / 2, FRAMEHEIGHT / 2);
		}
	}

	/**
	 * The game panel that is drawn to show the gameplay
	 * 
	 * @author 10-4
	 *
	 */
	class DrawPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);
			float alpha = (float) 0.5;
			g2d.setColor(Color.blue);
			try {
				g2d.drawImage(background.getBackground(0), background.getBackgroundX(0), 0, this);
				g2d.drawImage(background.getBackground(1), background.getBackgroundX(1), 0, this);
			} catch (NullPointerException e) {
				System.out.println("Null pointer exception!\n" + e);
			}

			if (elements != null) {
				for (GameElement e : elements) {
					g2d.drawImage(e.getImage(), e.getXloc(), e.getYloc(), this);
					// g2d.drawPolygon(e.polyBounds());
				}
				if (bird != null) {
					if (bird.isStunned()) {
						AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
						g2d.setComposite(ac);
					}
					g2d.drawImage(bird.nextFrame(), bird.getXloc(), bird.getYloc(), this);
					g2d.drawImage(bird.getStaminaImage(), 0, 0, this);
					// g2d.drawRect(bird.xloc+25,bird.yloc+140,bird.width-75,60);
				}
				if (miniMap != null) {
					g2d.drawImage(miniMap.getImage(), miniMap.getXloc(), miniMap.getYloc(), this);
					// System.out.println(miniMap.getMapXLoc() + ", " + miniMap.getMapYLoc() );
					// System.out.println(miniMap.getXloc() + ", " + miniMap.getYloc() );
					g2d.drawImage(miniMap.getSmallBird(), miniMap.getMapXLoc(), miniMap.getMapYLoc(), this);
				}
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(FRAMEWIDTH, FRAMEHEIGHT);
		}
	}
}
