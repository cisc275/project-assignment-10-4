import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {

	@Test
	void updateTest() {
		fail("Not yet implemented");
	}

	@Test
	void updateBirdTest() { 
		Model model = new Model(); 
		Bird bird = model.getBird(); 
		bird.getXloc(); 
		model.updateBird(); 
		Bird newBird = model.getBird(); 
		
		assertTrue(newBird.getXloc() == bird.getXloc() + bird.getxSpeed() + bird.getFlyingSpeed());
		assertTrue(newBird.getYloc() == bird.getYloc() + bird.getySpeed()); 
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
		Model model = new Model(); 
		assertFalse(model.isQuizMode()); 
		assertTrue(model.isBirdMode());
		model.startQuiz(); 
		assertTrue(model.isQuizMode());
		assertFalse(model.isBirdMode()); 
	}

	@Test
	void endQuizTest() {
		Model model = new Model(); 
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
}
