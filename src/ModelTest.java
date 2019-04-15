import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Handles all unit testing of Model class
 * @author eakresho
 * @author LandonJones
 */
class ModelTest {

	@Test
	void updateTest() {
		fail("Not yet implemented");
	}

	@Test
	void updateBirdTest() { 
		Model model = new Model(10,10); 
		//Bird bird = model.getBird(); 
		model.updateBird(); 
		//Bird newBird = model.getBird(); 
		//No implementation since bird and new bird are NULL
		fail("Not fully implemented");
	}

	@Test
	void updateCollidesTest() {
		fail("Not yet implemented");
	}

	@Test
	void updateBackgroundTest() {
		fail("Not yet implemented");
	}

	@Test
	void updateMiniMapTest() {
		fail("Not yet implemented");
	}

	@Test
	void collisionDetectionTest() {
		fail("Not yet implemented");
	}

	@Test
	void startQuizTest() {
		Model model = new Model(10,10); 
		assertFalse(model.isQuizMode()); 
		assertTrue(model.isBirdMode());
		model.startQuiz(); 
		assertTrue(model.isQuizMode());
		assertFalse(model.isBirdMode()); 
	}

	@Test
	void endQuizTest() {
		Model model = new Model(10,10); 
		model.startQuiz(); 
		model.endQuiz(); 
		assertTrue(model.isBirdMode());
		assertFalse(model.isQuizMode()); 
	}

	@Test
	void spawnCollidablesTest() {
		fail("Not yet implemented");
	}

	@Test
	void enterNestTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void spawnGameElementTest() {
		Model m = new Model(10,10);
		m.spawnGameElement();
		GameElement c = m.getOnScreenCollidables().get(3);
		assertTrue(c.getXloc() <= 10 && c.getXloc() >= 0 && c.getYloc() <= 10 && c.getYloc() >= 0);
		assertTrue(c.getxSpeed() == 10 && c.getySpeed() == 0);
	}
}
