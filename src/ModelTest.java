import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Handles all unit testing of Model class
 * @author eakresho
 * @author LandonJones
 */
class ModelTest {

	@Test
	void updateBirdTest() { 
		Model model = new Model(500,500); 
		Bird bird = model.getBird();
		bird.setYloc(-1);
		model.setBird(bird);
		model.updateBird(); 
		assertEquals(0,model.getBird().getYloc());
		
		bird = model.getBird();
		bird.setYloc(5);
		model.setBird(bird);
		model.updateBird();
		assertEquals(5,model.getBird().getYloc());
		
		bird = model.getBird();
		bird.setYloc(280);
		model.setBird(bird);
		model.updateBird();
		assertEquals(276,model.getBird().getYloc());
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
		assertEquals(true,c.getXloc() <= 10 && c.getXloc() >= 0 && c.getYloc() <= 10 && c.getYloc() >= 0);
		assertEquals(c.getxSpeed(),10);
		assertEquals(c.getySpeed(),0);
	}
}
