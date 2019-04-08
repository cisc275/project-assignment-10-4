import java.util.*;
import java.awt.image.BufferedImage;
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
	 * Height of the frame to dislpay the game
	 */
	private int frameHeight;
	/**
	 * Image for the background
	 */
	private BufferedImage background;
	
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
	 */
	void createImage() {}
	
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
	 * The player selects whehter it wants to play as the Osprey or the Nothern Harrier.
	 * 
	 * @return An int representing the bird chosen. 0 = Osprey, 1 = Northern Harrier
	 */
	int selectBird() {return 0;}
	
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

	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);			
		}

		public Dimension getPreferredSize() {
			return new Dimension(0, 0);
		}
	}
	
	
}
