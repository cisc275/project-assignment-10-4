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
		//Bird b = new Bird(0, 0, 0, 0, "");
		m.getBird().setYloc(280);
		//b.setYloc(280);
		//m.setBird(b);
		List<GameElement> list = new ArrayList<GameElement>();
		GameElement g1 = new Obstacle(100, 100, 1, 0, "", Images.BUILDING);
		GameElement gOffScreen = new Obstacle(-10, 100, 1, 0, "", Images.BUILDING);
		gOffScreen.setWidth(10);
		list.add(gOffScreen);
		list.add(g1);
		//m.setOnScreenElements(list); 
		m.update();
		Model model2 = new Model(10, 10, "Osprey");
		List<GameElement> list2 = new ArrayList<GameElement>();
		GameElement g12 = new Obstacle(99, 100, 1, 0, "", Images.BUILDING);
		list2.add(g12);
		// GameElement rand = new Obstacle(1,10,10,1,1,"");
		// list2.add(rand);
	//	model2.setOnScreenElements(list2);
	/*	assertEquals(m.getOnScreenCollidables().get(0).xloc, model2.getOnScreenCollidables().get(0).xloc);
		assertEquals(m.getOnScreenCollidables().size(), model2.getOnScreenCollidables().size());
		assertEquals(276, m.getBird().getYloc());
		*/
		Model model3 = new Model(10, 10, "Osprey");
		model3.setDistance(0);
		model3.update();
		model3.setDistance(10000000);
		model3.update();
		assertTrue(true == model3.isReachedEnd() );
		//assertEquals();
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
		// GameElement rand = new Obstacle(1,10,10,1,1,"");
		// list2.add(rand);
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
		modelO.createMiniMap();
		Model modelH = new Model(10, 10, "Northern Harrier");
		modelH.createMiniMap();
		assertEquals(Images.OSPREY_IMG_FOR_MINIMAP, modelO.getMiniMap().getMapSpriteFile() );
		assertEquals(Images.NH_IMG_FOR_MINIMAP, modelH.getMiniMap().getMapSpriteFile() );
		//Images.OSPREY_MINIMAP Images.NH_MINIMAP
		// Model model = new Model(10, 10);
		// MiniMap old = model.getMiniMap();
		// TODO fix this test to use implemented minimap features
		// assertEquals(old, model.getMiniMap());
		fail("Incomplete test");
	}

	@Test
	void collisionDetectionTest() throws NoSuchFieldException, IllegalAccessException {
		Model model = new Model(1000, 1000);
		final Field field = model.getClass().getDeclaredField("onScreenCollidables");
		field.setAccessible(true);
		// field.get(model).add(new Obstacle(0,0,0,0,"images/building.png") );
		Obstacle obstacle = new Obstacle(10, 500, 0, 0, "images/building.png", Images.BUILDING);
		obstacle.setWidth(100);
		obstacle.setHeight(100);
		model.getOnScreenCollidables().add(obstacle);
		model.updateBird();
		assertEquals(obstacle, model.collisionDetection());
		Food food = new Food(true, 10, 500, 0, 0, "images/golden_fish.png", Images.GOLDENFISH);
		food.setWidth(100);
		food.setHeight(100);
		model.getOnScreenCollidables().add(food);
		model.getOnScreenCollidables().remove(obstacle);
		assertEquals(food, model.collisionDetection());
		assertTrue(model.isQuizMode());
	}

	@Test
	void startQuizTest() {
		Model model = new Model(10, 10);
		assertFalse(model.isQuizMode());
		assertFalse(model.isBirdMode());
		model.startQuiz();
		assertFalse(model.isQuizMode());
		assertFalse(model.isBirdMode());
	}

	@Test
	void endQuizTest() {
		Model model = new Model(10, 10);
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
		QuizQuestion q1 = new QuizQuestion("", new ArrayList<String>(), "");
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
		QuizQuestion q1 = new QuizQuestion("", new ArrayList<String>(), "");
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
		Model model = new Model(1500, 1500);
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
		Model model = new Model(100, 100);
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
		Model model = new Model(10, 10);
		int oldPoints = model.getBird().getPoints();
		model.getBird().setPoints(oldPoints + 1);
		assertNotEquals(oldPoints, model.getBird().getPoints());
	}

	@Test
	public void getSetMiniMapTest() {
		Model model = new Model(10, 10);
		MiniMap old = model.getMiniMap();
		assertEquals(model.getMiniMap(), null);
		//model.setMiniMap(new MiniMap(0, 0, 0, 0, new Image(), new Image(), 0, 0));
		assertNotEquals(old, model.getMiniMap());
	}

	@Test
	void spawnGameElementTest() {
		Model m = new Model(1000, 1000);
		int oldSize = m.getOnScreenCollidables().size();
		m.spawnGameElement();
		assertEquals(m.getOnScreenCollidables().size(), oldSize + 1);
		// GameElement c = m.getOnScreenCollidables().get(0);
		// assertEquals(true,c.getYloc() <= 1100 && c.getYloc() >= 0);
		// assertEquals(c.getxSpeed(),10);
		// assertEquals(c.getySpeed(),0);
	}

	@Test
	void updateSpawnTimerTest() {
		Model m = new Model(500, 500);
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
		Model m = new Model(500, 500);
		Images[] imgs = { Images.BUILDING, Images.EAGLE, Images.TRASH, Images.GOLDENFISH, Images.FISH, 
				Images.MOUSE, Images.GOLDENMOUSE, Images.OWL, Images.FOX, Images.OSPREY_MINIMAP, 
				Images.NH_MINIMAP, Images.RECTANGLE };
		for (int i = 0; i < 12; i++) {
			m.spawnGameElement(i);
			assertEquals(m.getOnScreenCollidables().get(i).getImagePath(), imgs[i].getName());
		} 
	}
}
