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

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Handles the visual component of the game. Changes the positions of objects
 * as the game progresses and according to player input.
 * 
 * @author 10-4
 *
 */

@SuppressWarnings("serial")
public class View extends JPanel implements Serializable{
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
	private DrawPanel currentPanel;
	/**
	 * The panel at the beginning with the bird choices
	 */
	private DrawPanel buttonPanel;
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
	private DrawPanel quizPanel; 
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
	//private static final int FRAMEWIDTH = (int)SCREENSIZE.getWidth();
	private static final int FRAMEWIDTH = 1920;
	/**
	 * Height of the frame to display the game
	 */
	//private static final int FRAMEHEIGHT = (int)SCREENSIZE.getHeight(); 
	private static final int FRAMEHEIGHT = 1080;
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
	 * View constructor, sets up the frame and its contents
	 * @param c reference to the Controller object in use
	 */
	public View(Controller c) {
		System.out.println("(" + FRAMEWIDTH + "," + FRAMEHEIGHT + ")");
		frame = new JFrame();
		cards = new JPanel(new CardLayout());
		buttonPanel = new DrawPanel(); 
		buttonPanel.setBackground(Color.gray);
		buttonFont = new Font("Verdana", Font.BOLD, FRAMEHEIGHT/6);
		c.getOButton().setFont(buttonFont);
		c.getNHButton().setFont(buttonFont);
		c.getOButton().setPreferredSize(new Dimension(FRAMEWIDTH,FRAMEHEIGHT/2));
		c.getNHButton().setPreferredSize(new Dimension(FRAMEWIDTH,FRAMEHEIGHT/2));
		buttonPanel.add(c.getNHButton());
		buttonPanel.add(c.getOButton());
    	OPanel = new DrawPanel(); 
		OPanel.setBackground(Color.gray);
    	NHPanel = new DrawPanel(); 
		NHPanel.setBackground(Color.gray);				
		cards.add(buttonPanel, "B");
		cards.add(OPanel, "O");
		cards.add(NHPanel, "NH");
		//cards.add(quizPanel, "Q"); 
		currentPanel = buttonPanel;
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
	 * Will update the game display based on changes to different game components.
	 * If a game component does not currently have an image, it gets it assigned
	 * 
	 * @param bird The bird the user controls
	 * @param elements The list of GameElement objects on screen
	 * @param miniMap The MiniMap that displays progress
	 * @param background The background that displays and scrolls
	 */
	
	void updateView(Bird bird, List<GameElement>elements,MiniMap miniMap, Background background) {
		this.background = background;	
        this.bird = bird; 
		if(this.bird.getImage() == null) {
			this.bird.setImage(createImage("images/big_bird_animate.png"));
		}
		if(this.bird.getStaminaPics()[0] == null) {
			BufferedImage[] arr = new BufferedImage[6];
			arr[0] = createImage("images/0_health.png");
			arr[1] = createImage("images/1_health.png");
			arr[2] = createImage("images/2_health.png");
			arr[3] = createImage("images/3_health.png");
			arr[4] = createImage("images/4_health.png");
			arr[5] = createImage("images/5_health.png");
			this.bird.setStaminaPics(arr);
		}
		this.elements = elements; 
		
		//MiniMap curMap = (MiniMap)elements.get(0); //first element will always be a MiniMap
		//curMap.setImage(createImage(curMap.getMapSpriteFile()));
		Iterator<GameElement> it = this.elements.iterator();	
		GameElement e;
		while(it.hasNext()) {
			e = it.next();
			if(e.getImage() == null) {
				e.setImage(createImage(e.getImagePath()));
			}                            
		}
		frame.repaint(); 
	}
	
	/**
	 * Creates an image to be displayed
	 * @param f a File to generate image from
	 * @return BufferedImage the generated image
	 */
	
	BufferedImage createImage(String file){
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
	void drawImage() {}
	
	/**
	 * Displays a quiz question that will need to be answered by the player to progress
	 */
	void displayQuiz(QuizQuestion question, List<JButton> buttons) {
		quizPanel = new DrawPanel(); 
		quizPanel.setBackground(Color.gray);
		JLabel text = new JLabel(); 
		Font font = new Font("Verdana", Font.BOLD, FRAMEHEIGHT/50); 
		text.setText(question.getQuestion());
		text.setFont(font);
		text.setPreferredSize(new Dimension(FRAMEWIDTH / 5, FRAMEHEIGHT / 5));
		quizPanel.add(text);
		for (JButton b: buttons) {
			b.setFont(font); 
			b.setPreferredSize(new Dimension(FRAMEWIDTH / 5, FRAMEHEIGHT / 5));
			quizPanel.add(b); 
		}
		cards.add(quizPanel, "Q"); 
		setPanel("Q"); 
		currentPanel = quizPanel;
	}
	
	/**
	 * Handles the animation for the bird landing in the nest when the player reaches
	 * the end of the game
	 */
	void nestAnimation() {}
	
	/**
	 * Updates the display of the bird
	 */
	void updateBird() {}
	
	/**
	 * Updates the display of the onscreen collidables
	 * 
	 * @param bird the bird controlled by the player
	 */
	void updateCollidables(Bird bird) {}
	
	/**
	 * Updates the display of the minimap based on game progress
	 * 
	 * @param miniMap The minimap to be updated and displayed
	 */
	void updateMiniMap(MiniMap miniMap) {}
	
	/**
	 * update the background based on how far the player has traveled.
	 */
	void updateBackground() {}
	
	/**
	 * @param String the panel to set
	 */
	public void setPanel(String name) {
		((CardLayout) cards.getLayout()).show(cards, name);
		switch(name) {
			case "O":
				currentPanel = OPanel;
				break;
			case "NH":
				currentPanel = NHPanel;
		}
	}
	
	/**
	 * @return the currentPanel
	 */
	public DrawPanel getCurrentPanel() {
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
	public DrawPanel getbuttonPanel() {
		return buttonPanel;
	}

	/**
	 * @param drawPanel the buttonPanel to set
	 */
	public void setbuttonPanel(DrawPanel drawPanel) {
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
	public DrawPanel getQuizPanel() {
		return quizPanel;
	}

	/**
	 * @param drawPanel the quizPanel to set
	 */
	public void setQuizPanel(DrawPanel drawPanel) {
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
	public void setElements(List<GameElement>elements) {
		this.elements = elements;
	}
	
	/**
	 * 
	 * @return the elements
	 */
	public List<GameElement> getElements(){
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
	 * The game panel that is drawn to show the gameplay
	 * 
	 * @author 10-4
	 *
	 */
	class DrawPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			super.paintComponent(g2d);
			float alpha = (float) 0.5;
			g2d.setColor(Color.blue);
			try {
				g2d.drawImage(background.getBackground1(),background.getB1x(),0,this);
				g2d.drawImage(background.getBackground2(),background.getB2x(),0,this);
			}
			catch(NullPointerException e) {
				
			}
			
			if (elements != null) {
				for (GameElement e: elements) {
					g2d.drawImage(e.getImage(), e.getXloc(), e.getYloc(), this); 
					//g2d.drawPolygon(e.polyBounds());
				} 
				if (bird != null) {
					if (bird.isStunned()) {
						AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
						g2d.setComposite(ac);
					}
			    	g2d.drawImage(bird.nextFrame(), bird.getXloc(), bird.getYloc(), this);
			    	g2d.drawImage(bird.getStaminaImage(), 0, 0, this);
				}
			} 	
		}

		public Dimension getPreferredSize() {
			return new Dimension(FRAMEWIDTH, FRAMEHEIGHT); 
		}
	}
}
