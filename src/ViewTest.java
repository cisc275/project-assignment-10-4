import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.jupiter.api.Test;

/**
 * Handles all unit tests of View class
 * @author jhdavis
 *
 */
class ViewTest {
	
	@Test
	void updateViewTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void createImageTest() {
		Controller c = new Controller();
		View view = new View(c);
		BufferedImage i = view.createImage("images/test-image.jpg");
		assertTrue(i != null);
	}
	
	@Test
	void drawImageTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void displayQuizTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void nestAnimationTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void selectBirdTest() {
		View view = new View(new Controller());
		assertTrue(view.selectBirdType() == 1 || view.selectBirdType() == 0);
	}
	
	@Test
	void updateBirdTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void updateCollidablesTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void updateMiniMapTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void updateBackgroundTest() {
		fail("Not yet implemented");
	}

}
