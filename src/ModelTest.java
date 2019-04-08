import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {

	@Test
	void updateTest() {
		fail("Not yet implemented");
	}

	@Test
	void updateBirdTest() { 
		fail("Not yet implemented");
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
