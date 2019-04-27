import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Handles the visual component of the game. Changes the positions of objects
 * as the game progresses and according to player input.
 * 
 * @author 10-4
 *
 */

@SuppressWarnings("serial")
public class View extends JPanel{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * The frame used to display the game
	 */
	private JFrame frame;	
	/**
	 * The panel that will be drawn on to display the game
	 */
	private DrawPanel drawPanel;	
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
	private BufferedImage background;

    private BufferedImage box;
    private BufferedImage thanos; 
    private Bird bird; 
    private List<GameElement> elements; 
	/**
	 * View constructor, sets up the frame and its contents
	 * @param c reference to the Controller object in use
	 */
	public View(Controller c) {
		this.box = createImage("images/rectangle-icon-256.png");
		//this.thanos = createImage("images/thanosbird.jpg"); 
		this.thanos = createImage(generateImgPath() );
		frame = new JFrame();
		drawPanel = new DrawPanel(); 
		drawPanel.setBackground(Color.pink);
    	frame.add(drawPanel);
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
	 * @return The path for the image.  Uses the Images Enum to return the path for an image based off 
	 * a random number.
	 */
	String generateImgPath() {
		Random rand = new Random();
		int curImage = rand.nextInt(2);
		String ImgPath = "";
		Images dir;
		     switch (curImage) {
		       case 0:
		    	  dir = Images.THANOS;
		    	  ImgPath = dir.getName();
		          break;
		       case 1:
		    	  dir = Images.FOOD;
		    	  ImgPath = dir.getName();
		          break;
		     }
		   return ImgPath;
	}
	/**
	 * Will update the game display based on changes to different game components.
	 * 
	 * @param bird The bird the user controls
	 * @param elements The list of GameElement objects on screen
	 * @param miniMap The MiniMap that displays progress
	 */
	
	void updateView(Bird bird, List<GameElement>elements,MiniMap miniMap) {
				
        this.bird = bird; 
		if(this.bird.getImage() == null) {
			this.bird.setImage(thanos);
			//this.bird.setImage(thanos);
		}
		this.elements = elements; 
		
		for(GameElement e : elements) {
			if(e.getImage() == null) {
				e.setImage(createImage(generateImgPath() ));
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
	void displayQuiz() {}
	
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
	 * @return the drawPanel
	 */
	public DrawPanel getDrawPanel() {
		return drawPanel;
	}

	/**
	 * @param drawPanel the drawPanel to set
	 */
	public void setDrawPanel(DrawPanel drawPanel) {
		this.drawPanel = drawPanel;
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
	public BufferedImage getViewBackground() {
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setViewBackground(BufferedImage background) {
		this.background = background;
	}

	private class DrawPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);	
			g.setColor(Color.gray);
			if (elements != null) {
				for (GameElement e: elements) {
					g.drawImage(e.getImage(), e.getXloc(), e.getYloc(), Color.gray, this); 
				} 
				if (bird != null) {
					g.drawImage(thanos, bird.getXloc(), bird.getYloc(), Color.gray, this); 
				}
			} 	
		}

		public Dimension getPreferredSize() {
			return new Dimension(frameWidth, frameHeight); 
		}
	}
	/**
	public static void main(String[] args) {
		View view = new View(new Controller()); 
		List<GameElement> theElements = new ArrayList<GameElement>();
		GameElement g1 = new GameElement(); 
		g1.setXloc(800); 
		g1.setYloc(666); 
		GameElement g2= new GameElement(); 
		g2.setXloc(1500); 
		g2.setYloc(250); 
		theElements.add(g2); 
		theElements.add(g1);
		Bird b = new Bird(); 
		b.setXloc(1500); 
		b.setYloc(666); 
		view.updateView(b,theElements, null); 
		
		
	}
	**/ 
}
