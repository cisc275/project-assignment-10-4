import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * Handles all unit testing of Model class
 * 
 * @author eakresho
 * @author LandonJones
 * @author jhdavis
 * @author dwarszaw
 */
class ModelTest {
	
	@Test 
	void createModelTest() {
		Model m = new Model(100,100, "Osprey");
		Model m2 = new Model(100,100, "Northern Harrier");
		assertTrue(false == m.isReachedEnd() );
		assertTrue(false == m2.isReachedEnd() );
	}

	@Test
	void randomImageTest() {
		Model m = new Model(100,100, "Osprey");
		m.randomImage(-1);
		m.getBackground().setBackground(1, Images.WATER_PATH);   
		m.getBackground().setBackground(1, Images.FOX);
		Model m2 = new Model(100,100, "Northern Harrier");
		m2.getBackground().setBackground(1, Images.WATER_PATH);
		m2.randomImage(-1);
		Model m3 = new Model(100,100, "Osprey");
		assertTrue(m3.randomImage(5) == 5);
		Model m4 = new Model(100,100, "Osprey");
		m4.randomImage(-1);
		Model m5 = new Model(100,100, "Osprey");
		m5.randomImage(-1);
		Model m6 = new Model(100,100, "Osprey");
		m6.randomImage(-1);
	}
	@Test
	void updateTest() {
		Model m = new Model(500, 500, "Osprey");
		m.getBird().setYloc(280);
		List<GameElement> list = new ArrayList<GameElement>();
		GameElement g1 = new Obstacle(100, 100, 1, 0, "", Images.BUILDING);
		GameElement gOffScreen = new Obstacle(-10, 100, 1, 0, "", Images.BUILDING);
		gOffScreen.setWidth(10);
		list.add(gOffScreen);
		list.add(g1);
		m.update();
		List<GameElement> list2 = new ArrayList<GameElement>();
		GameElement g12 = new Obstacle(99, 100, 1, 0, "", Images.BUILDING);
		list2.add(g12);
		Model model3 = new Model(10, 10, "Osprey");
		model3.setDistance(0);
		model3.update();
		model3.setDistance(10000000);
		model3.update();
		assertTrue(true == model3.isReachedEnd() );
	}

	@Test
	void updateBirdTest() {
		Model model = new Model(500, 500, "Osprey");
		Bird bird = model.getBird();
		bird.setYloc(-1);
		model.setBird(bird);
		model.updateBird();
		assertEquals(0, model.getBird().getYloc());

		bird = model.getBird();
		bird.setYloc(5);
		model.setBird(bird);
		model.updateBird();
		assertEquals(5, model.getBird().getYloc());

		bird = model.getBird();
		bird.setYloc(280);
		model.setBird(bird);
		model.updateBird();
		assertEquals(276, model.getBird().getYloc());
	}

	@Test
	void updateGameElementsTest() {
		Model model = new Model(10, 10, "Osprey");
		List<GameElement> list = new ArrayList<GameElement>();
		GameElement g1 = new Obstacle(100, 100, 1, 0, "", Images.BUILDING);
		g1.setType(Images.BUILDING);
		GameElement gOffScreen = new Obstacle(-10, 100, 1, 0, "", Images.BUILDING);
		gOffScreen.setWidth(10);
		gOffScreen.setType(Images.BUILDING);
		list.add(gOffScreen);
		list.add(g1);
		model.setOnScreenElements(list);
		model.updateGameElements();
		Model model2 = new Model(10, 10);
		List<GameElement> list2 = new ArrayList<GameElement>();
		GameElement g12 = new Obstacle(99, 100, 1, 0, "", Images.BUILDING);
		g12.setType(Images.BUILDING);
		list2.add(g12);
		model2.setOnScreenElements(list2);
		assertEquals(model.getOnScreenCollidables().get(0).xloc, model2.getOnScreenCollidables().get(0).xloc);
		assertEquals(model.getOnScreenCollidables().size(), model2.getOnScreenCollidables().size());
	}
	
	@Test
	void updateBackgroundTest() {
		Model model = new Model(10, 10, "Osprey");
		Model old = model;
		model.updateBackground();
		assertEquals(old, model);
		
		Model model2 = new Model(10, 10, "Northern Harrier");
		Model old2 = model2;
		model2.updateBackground();
		assertEquals(old2, model2);
	}

	@Test
	void updateMiniMapTest() {
		Model modelO = new Model(10, 10, "Osprey");
		modelO.updateMiniMap(0);
		Model modelH = new Model(10, 10, "Northern Harrier");
		modelH.updateMiniMap(0);
		assertEquals(Images.OSPREY_IMG_FOR_MINIMAP, modelO.getMiniMap().getMapSpriteFile() );
		modelH.setMiniMap( (MiniMap)modelH.genMap(Images.NH_MINIMAP));
		assertEquals(Images.NH_IMG_FOR_MINIMAP, modelH.getMiniMap().getMapSpriteFile() );
		
		
	}

	@Test
	void collisionDetectionTest() throws NoSuchFieldException, IllegalAccessException {
		
		Model model = new Model(1000, 1000, "Osprey");
		final Field field = model.getClass().getDeclaredField("onScreenCollidables");
		field.setAccessible(true);
		Obstacle obstacle = new Obstacle(10, 500, 0, 0, "images/building.png", Images.BUILDING);
		obstacle.setType(Images.BUILDING);
		obstacle.setWidth(100);
		obstacle.setHeight(100);
		model.getOnScreenCollidables().add(obstacle);
		model.updateBird();
		assertEquals(obstacle, model.collisionDetection());
		Food food = new Food(true, 10, 500, 0, 0, "images/golden_fish.png", Images.GOLDENFISH);
		food.setType(Images.GOLDENFISH);
		food.setWidth(100);
		food.setHeight(100);
		model.getOnScreenCollidables().add(food);
		model.getOnScreenCollidables().remove(obstacle);
		assertEquals(food, model.collisionDetection());
		assertTrue(model.isQuizMode());
	}

	@Test
	void startQuizTest() {
		Model model = new Model(10, 10, "Osprey");
		assertFalse(model.isQuizMode());
		assertFalse(model.isBirdMode());
		model.startQuiz();
		assertFalse(model.isQuizMode());
		assertFalse(model.isBirdMode());
	}

	@Test
	void endQuizTest() {
		Model model = new Model(10, 10,  "Osprey");
		model.startQuiz();
		model.endQuiz("");
		assertFalse(model.isBirdMode());
		assertFalse(model.isQuizMode());
		QuizQuestion q = model.startQuiz();
		model.endQuiz(q.getCorrectAnswer());
		assertFalse(model.isBirdMode());
		assertFalse(model.isQuizMode());
	}

	@Test
	void enterNestTest() {
		Model model = new Model(10, 10);
		Model old = model;
		model.enterNest();
		assertEquals(old, model);
	}

	@Test
	public void setBirdModeTest() throws NoSuchFieldException, IllegalAccessException {

		final Model model = new Model(10, 10);

		model.setBirdMode(true);

		final Field field = model.getClass().getDeclaredField("birdMode");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(model), true);
	}

	@Test
	public void isBirdModeTest() throws NoSuchFieldException, IllegalAccessException {

		final Model model = new Model(10, 10);

		final Field field = model.getClass().getDeclaredField("birdMode");
		field.setAccessible(true);
		field.set(model, true);

		final boolean value = model.isBirdMode();
		assertEquals("Field wasn't retrieved properly", value, true);

		assertTrue(model.isBirdMode() == true);

	}

	@Test
	public void setQuizModeTest() throws NoSuchFieldException, IllegalAccessException {

		final Model model = new Model(10, 10);

		model.setQuizMode(true);

		final Field field = model.getClass().getDeclaredField("quizMode");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(model), true);
	}

	@Test
	public void isQuizMode() throws NoSuchFieldException, IllegalAccessException {

		final Model model = new Model(10, 10);

		final Field field = model.getClass().getDeclaredField("quizMode");
		field.setAccessible(true);
		field.set(model, true);

		final boolean value = model.isBirdMode();
		assertEquals("Field wasn't retrieved properly", value, false);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void setQuizQuestionsTest() throws NoSuchFieldException, IllegalAccessException {

		final Model model = new Model(10, 10);
		QuizQuestion q1 = new QuizQuestion("", new ArrayList<String>(), "", "");
		List<QuizQuestion> questions = new ArrayList<>();
		questions.add(q1);
		model.setQuizQuestions(questions);
		final Field field = model.getClass().getDeclaredField("quizQuestions");
		field.setAccessible(true);
		final ArrayList<QuizQuestion> getQuest = (ArrayList<QuizQuestion>) field.get(model);
		assertEquals("Fields didn't match", getQuest.size(), 1);
	}

	@Test
	public void getQuizQuestionsTest() throws NoSuchFieldException, IllegalAccessException {

		final Model model = new Model(10, 10);
		final Field field = model.getClass().getDeclaredField("quizQuestions");
		field.setAccessible(true);
		QuizQuestion q1 = new QuizQuestion("", new ArrayList<String>(), "", "");
		List<QuizQuestion> questions = new ArrayList<>();
		questions.add(q1);
		field.set(model, questions);

		final List<QuizQuestion> value = model.getQuizQuestions();
		assertEquals("Field wasn't retrieved properly", value, questions);
	}

	@Test
	public void getFrameWidthTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		final Field field = model.getClass().getDeclaredField("frameWidth");
		field.setAccessible(true);
		field.set(model, 5);
		final int value = model.getFrameWidth();
		assertEquals("Field wasn't retrieved properly", value, 5);
	}

	@Test
	public void setFrameWidthTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		model.setFrameWidth(4);
		final Field field = model.getClass().getDeclaredField("frameWidth");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(model), 4);
	}

	@Test
	public void getFrameHeightTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		final Field field = model.getClass().getDeclaredField("frameHeight");
		field.setAccessible(true);
		field.set(model, 5);
		final int value = model.getFrameHeight();
		assertEquals("Field wasn't retrieved properly", value, 5);
	}

	@Test
	public void setFrameHeightTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		model.setFrameHeight(4);
		final Field field = model.getClass().getDeclaredField("frameHeight");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(model), 4);
	}

	@Test
	public void getImgWidthTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		final Field field = model.getClass().getDeclaredField("imgWidth");
		field.setAccessible(true);
		field.set(model, 5);
		final int value = model.getImgWidth();
		assertEquals("Field wasn't retrieved properly", value, 5);
	}

	@Test
	public void setImgWidthTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		model.setImgWidth(4);
		final Field field = model.getClass().getDeclaredField("imgWidth");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(model), 4);
	}

	@Test
	public void getImgHeightTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		final Field field = model.getClass().getDeclaredField("imgHeight");
		field.setAccessible(true);
		field.set(model, 5);
		final int value = model.getImgHeight();
		assertEquals("Field wasn't retrieved properly", value, 5);
	}

	@Test
	public void getBackgroundTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		final Field field = model.getClass().getDeclaredField("background");
		field.setAccessible(true);
		field.set(model, new Background(5));
		final Background value = model.getBackground();
		assertEquals("Field wasn't retrieved properly", value, new Background(5));
	}

	@Test
	public void setBackgroundTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		model.setBackground(new Background(100));
		final Field field = model.getClass().getDeclaredField("background");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(model), (new Background(100)));
	}

	@Test
	public void setImgHeightTest() throws NoSuchFieldException, IllegalAccessException {
		final Model model = new Model(10, 10);
		model.setImgHeight(4);
		final Field field = model.getClass().getDeclaredField("imgHeight");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(model), 4);
	}

	@Test
	public void getBirdTest() {
		Model model = new Model(1500, 1500, "Osprey");
		Bird old = model.getBird();
		assertNotEquals(old, null);
	}

	@Test
	public void setBirdTest() {
		Model model = new Model(1000, 1000);
		Bird old = model.getBird();
		model.setBird(new Bird(0, 0, 0, 0, ""));
		assertNotEquals(old, model.getBird());
	}

	@Test
	public void getSetBirdTypeTest() {
		Model model = new Model(100, 100, "Osprey");
		String typeOld = model.getBird().getBirdType();

		model.getBird().setBirdType("");
		assertNotEquals(model.getBird().getBirdType(), typeOld);
	}

	@Test
	public void getSetGetOnScreenCollidablesTest() {
		Model model = new Model(100, 100);
		int oldCollides = model.getOnScreenCollidables().size();
		List<GameElement> newCollides = model.getOnScreenCollidables();
		newCollides.add(new Bird(0, 0, 0, 0, ""));
		model.setOnScreenElements(model.getOnScreenCollidables());
		assertNotEquals(oldCollides, model.getOnScreenCollidables().size());
	}

	@Test
	public void getSetDistanceTest() {
		Model model = new Model(10, 10);
		int oldDist = model.getDistance();
		model.setDistance(model.getDistance() + 1);
		assertNotEquals(oldDist, model.getDistance());
	}

	@Test
	public void getSetEndDistanceTest() {
		Model model = new Model(10, 10);
		int oldEnd = model.getEndDistance();
		model.setEndDistance(oldEnd + 1);
		assertNotEquals(oldEnd, model.getEndDistance());
	}

	@Test
	public void getSetPointsTest() {
		Model model = new Model(10, 10, "Osprey");
		int oldPoints = model.getBird().getPoints();
		model.getBird().setPoints(oldPoints + 1);
		assertNotEquals(oldPoints, model.getBird().getPoints());
	}

	@Test
	public void getSetMiniMapTest() {
		Model modelO = new Model(10, 10, "Osprey");
		//MiniMap old = model.getMiniMap();
		int xLocOfBird = MiniMap.OSPREY_INITIAL_SMALL_BIRD_X_LOC;
		int yLocOfBird = MiniMap.OSPREY_INITIAL_SMALL_BIRD_Y_LOC;
		int x = modelO.getFrameWidth() - 250;
		int y = 0;
		int xSpeed = 0;
		int ySpeed = 0;
		Model modelH = new Model(10, 10, "Northern Harrier");
		
		assertFalse(modelO.getMiniMap().equals( new MiniMap(x, y, xSpeed, ySpeed, Images.OSPREY_MINIMAP, Images.OSPREY_IMG_FOR_MINIMAP, xLocOfBird, yLocOfBird) ));
		x = modelO.getFrameWidth() - 250;
		xLocOfBird = MiniMap.NH_INITIAL_SMALL_BIRD_X_LOC;
		yLocOfBird = MiniMap.NH_INITIAL_SMALL_BIRD_Y_LOC;
	
		assertFalse(modelH.getMiniMap().equals( new MiniMap(x, y, xSpeed, ySpeed, Images.NH_MINIMAP, Images.NH_IMG_FOR_MINIMAP, xLocOfBird, yLocOfBird)));
		
	}

	@Test
	void spawnGameElementTest() {
		Model m = new Model(1000, 1000, "Osprey");
		int oldSize = m.getOnScreenCollidables().size();
		m.spawnGameElement();
		assertEquals(m.getOnScreenCollidables().size(), oldSize + 1);
	}

	@Test
	void updateSpawnTimerTest() {
		Model m = new Model(500, 500,  "Osprey");
		int toSpawn = m.getSpawnCount();
		for (int i = 0; i < Model.SPAWN_TIME_MAX * toSpawn; i++) {
			m.updateSpawnTimer();
		}
		int onScreenCount = m.getOnScreenCollidables().size();
		assertTrue(onScreenCount >= toSpawn);
		m.setSpawnCount(0);
		m.updateSpawnTimer();
		assertTrue(m.getOnScreenCollidables().size() == onScreenCount);
	}

	@Test
	void generateImgPathTest() {
		Model m = new Model(500, 500, "Osprey");
		Images[] imgs = { Images.BUILDING, Images.EAGLE, Images.TRASH, Images.GOLDENFISH, Images.FISH, 
				Images.MOUSE, Images.GOLDENMOUSE, Images.OWL, Images.FOX, Images.OSPREY_MINIMAP, 
				Images.NH_MINIMAP, Images.RECTANGLE };
			for (int i = 0; i < 11; i++) {
			m.spawnGameElement(i);
			assertEquals(m.getOnScreenCollidables().get(i).getImagePath(), imgs[i].getName());
		}		 
	}
	@Test 
	void createQuestionsTest() {
		Model m = new Model(500, 500, "Osprey"); 
		assertTrue(m.getQuizQuestions()== null);
		m.createQuestions("Osprey");
		assertTrue(m.getQuizQuestions() == null); 
		m.createQuestions("NorthernHarrier");
		assertTrue(m.getQuizQuestions() == null); 
		 
		
	}
	@Test 
	void createMiniMapTest() {
		Model m = new Model(500, 500, "Osprey"); 
		m.createMiniMap();
		assertTrue(m.getMiniMap() != null); 
	    m = new Model(500, 500, "Northern Harrier"); 
		m.createMiniMap(); 
		assertTrue(m.getMiniMap() != null); 
		
	}
	@Test
	void nestAnimationTest() {
		Model m = new Model(500, 500, "Osprey"); 
		m.configureNestAnimation(); 
		assertTrue(m.getNestAnimation() != null); 
		m = new Model(500, 500, "northern harrier"); 
		m.configureNestAnimation();
		assertTrue(m.getNestAnimation() != null); 
	}
	@Test 
	void setTutorialTest() {
		Model m = new Model(500, 500, "Osprey"); 
		Tutorial t = new Tutorial(500, 500);
		m.setTutorial(t); 
		assertTrue(new Tutorial(100, 100) != m.getTutorial()); 
	}
	@Test 
	void isFaintedTest() {
		Model m = new Model(500, 500, "Osprey"); 
		assertFalse(m.birdIsFainted()); 
		m.getBird().setStamina(-1000);
		m.getBird().update(20); 
		assertTrue(m.birdIsFainted());
		
	}
	@Test 
	void setDoingQuizTest() {
		Model m = new Model(500, 500, "Osprey"); 
		m.setDoingQuiz(true); 
		assertTrue(m.isDoingQuiz());
	}
	@Test 
	void setReachedEndTest() {
		Model m = new Model(500, 500, "Osprey");
		m.setReachedEnd(true);
		assertTrue(m.isReachedEnd()); 
	}
	@Test 
	void testUpdateNest() {
		Model m = new Model(500, 500, "Osprey"); 
		m.updateNestAnimation();
		assertTrue(m.getNestAnimation() != null); 
	}
	@Test 
	void testUpdateTutorial() {
		Model m = new Model(500, 500, "Osprey"); 
		m.setTutorial(new Tutorial(500, 500));
		m.updateTutorial(); 
		assertTrue(m.getTutorial() != null); 
	}
	@Test 
	void tooHighTooLow() {
		Model m = new Model(500, 500, "Osprey"); 
		GameElement g = new Obstacle(0, -100, 0, 0, "bird", Images.TRASH); 
		assertTrue(m.tooHigh(g)); 
		assertFalse(m.tooLow(g)); 
		g = new Obstacle(0, (3*m.getFrameHeight()/4)-g.getHeight(), 0, 0, "bird", Images.TRASH); 
		assertTrue(m.tooLow(g)); 
		
	}
}
