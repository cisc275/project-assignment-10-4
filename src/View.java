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
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * The frame used to display the game
	 */
	private JFrame frame;
	/**
	 * The panel that is currently active
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
	
	private Font buttonFont;
	/**
	 * Width of the frame to display the game
	 */
	private final int frameWidth = (int)screenSize.getWidth();
	/**
	 * Height of the frame to display the game
	 */
	private final int frameHeight = (int)screenSize.getHeight(); 
	/**
	 * Image for the background
	 */

    private Bird bird; 
    private List<GameElement> elements; 
    
    
    private Background background;
    
	/**
	 * View constructor, sets up the frame and its contents
	 * @param c reference to the Controller object in use
	 */
	public View(Controller c) {
		//this.box = createImage("images/rectangle-icon-256.png");
		//this.thanos = createImage("images/big_bird_animate.png");
		//this.background = createImage("images/big_grass_background.png");
		frame = new JFrame();
		cards = new JPanel(new CardLayout());
		buttonPanel = new DrawPanel(); 
		buttonPanel.setBackground(Color.gray);
		buttonFont = new Font("Verdana", Font.BOLD, frameHeight/6);
		c.getOButton().setFont(buttonFont);
		c.getNHButton().setFont(buttonFont);
		c.getOButton().setPreferredSize(new Dimension(frameWidth,frameHeight/2));
		c.getNHButton().setPreferredSize(new Dimension(frameWidth,frameHeight/2));
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
		frame.add(cards);
		frame.setFocusable(true);
    	//frame.addMouseListener(c);
    	frame.addKeyListener(c);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//frame.setSize(frameWidth, frameHeight);
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.setResizable(false);
    	frame.setVisible(true);
    	frame.pack();
    	

	}
	
	/**
	 * Will update the game display based on changes to different game components.
	 * 
	 * @param bird The bird the user controls
	 * @param elements The list of GameElement objects on screen
	 * @param miniMap The MiniMap that displays progress
	 */
	
	void updateView(Bird bird, List<GameElement>elements,MiniMap miniMap, Background background) {
		this.background = background;	
        this.bird = bird; 
		if(this.bird.getImage() == null) {
			this.bird.setImage(createImage("images/big_bird_animate.png"));
			//this.bird.setImage(thanos);
		}
		this.elements = elements; 
		
		for(GameElement e : elements) {
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
		Font font = new Font("Verdana", Font.BOLD, frameHeight/50); 
		text.setText(question.getQuestion());
		text.setFont(font);
		text.setPreferredSize(new Dimension(frameWidth / 5, frameHeight / 5));
		quizPanel.add(text);
		for (JButton b: buttons) {
			b.setFont(font); 
			b.setPreferredSize(new Dimension(frameWidth / 5, frameHeight / 5));
			quizPanel.add(b); 
		}
		cards.add(quizPanel, "Q"); 
		setPanel("Q"); 
	}
	
	/**
	 * Handles the animation for the bird landing in the nest when the player reaches
	 * the end of the game
	 */
	void nestAnimation() {}
	
	/**
	 * The player selects whether it wants to play as the Osprey or the Northern Harrier.
	 * 
	 * @return An int representing the bird chosen. 0 = Osprey, 1 = Northern Harrier
	 */
	int selectBirdType() {return -1;}
	
	/**
	 * Updates the display of the bird
	 */
	void updateBird() {}
	
	/**
	 * Updates the display of the bird
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
	 * @return the NHPanel
	 */
	public DrawPanel getNHPanel() {
		return NHPanel;
	}
	
	public void setPanel(String name) {
		((CardLayout) cards.getLayout()).show(cards, name);
	}
	/**
	 * @return the cards
	 */
	public JPanel getCards() {
		return cards;
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
		return frameWidth;
	}

	/**
	 * @return the frameHeight
	 */
	public int getFrameHeight() {
		return frameHeight;
	}

	/**
	 * @return the background
	 */
	/*public BufferedImage getViewBackground() {
		return background;
	}*/

	/**
	 * @param background the background to set
	 */
	/*public void setViewBackground(BufferedImage background) {
		this.background = background;
	}*/

	private class DrawPanel extends JPanel {
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
				} 
				if (bird != null) {
					if (bird.isStunned()) {
						AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
						g2d.setComposite(ac);
					}
			    	g2d.drawImage(bird.nextFrame(), bird.getXloc(), bird.getYloc(), this);
				}
			} 	
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight); 
		}
	}
}
