import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
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
	private int frameWidth;	
	/**
	 * Height of the frame to display the game
	 */
	private int frameHeight;	
	/**
	 * Image for the background
	 */
	private BufferedImage background;	
	
	/**
	 * View constructor, sets up the frame and its contents
	 * @param c reference to the Controller object in use
	 */
	public View(Controller c) {
		
	}
	
	/**
	 * Will update the game display based on changes to different game components.
	 * 
	 * @param bird The bird the user controls
	 * @param collidables The list of collidable objects on screen
	 * @param miniMap The MiniMap that displays progress
	 */
	void updateView(Bird bird, List<Collidable>collidables,MiniMap miniMap) {}
	
	/**
	 * Creates an image to be displayed
	 * @param f a File to generate image from
	 * @return BufferedImage the generated image
	 */
	BufferedImage createImage(File f) {
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
	 * @param frameWidth the frameWidth to set
	 */
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	/**
	 * @return the frameHeight
	 */
	public int getFrameHeight() {
		return frameHeight;
	}

	/**
	 * @param frameHeight the frameHeight to set
	 */
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
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
		}

		public Dimension getPreferredSize() {
			return new Dimension(0, 0);
		}
	}
	
	
}
