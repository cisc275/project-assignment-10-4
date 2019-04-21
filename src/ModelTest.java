import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
/**
 * Handles all unit testing of Model class
 * @author eakresho
 * @author LandonJones
 */
class ModelTest {

	@Test
	void updateTest() {
		Model m = new Model(500,500);
		Bird b = new Bird();
		b.setYloc(280);
		m.setBird(b);
		List<GameElement> list = new ArrayList<GameElement>();
		GameElement g1 =  new GameElement(100,100,1,0);
		GameElement gOffScreen =  new GameElement(-10,100,1,0);
		gOffScreen.setWidth(10);
		list.add(gOffScreen);
		list.add(g1);
		m.setOnScreenElements(list);
		m.update();
		
		Model model2 = new Model(10,10);
		List<GameElement> list2 = new ArrayList<GameElement>();
		GameElement g12 =  new GameElement(99,100,1,0);
		list2.add(g12);
		GameElement rand = new GameElement(10,10,1,1);
		list2.add(rand);
		model2.setOnScreenElements(list2);
		
		assertEquals(m.getOnScreenCollidables().get(0).xloc, 
				model2.getOnScreenCollidables().get(0).xloc);
		assertEquals(m.getOnScreenCollidables().size(), 
				model2.getOnScreenCollidables().size());
		assertEquals(276,m.getBird().getYloc());
	}
	
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
	void updateGameElementsTest() {
		Model model = new Model(10,10);
		List<GameElement> list = new ArrayList<GameElement>();
		GameElement g1 =  new GameElement(100,100,1,0);
		GameElement gOffScreen =  new GameElement(-10,100,1,0);
		gOffScreen.setWidth(10);
		list.add(gOffScreen);
		list.add(g1);
		model.setOnScreenElements(list);
		model.updateGameElements();
		
		Model model2 = new Model(10,10);
		List<GameElement> list2 = new ArrayList<GameElement>();
		GameElement g12 =  new GameElement(99,100,1,0);
		list2.add(g12);
		GameElement rand = new GameElement(10,10,1,1);
		list2.add(rand);
		model2.setOnScreenElements(list2);
		
		assertEquals(model.getOnScreenCollidables().get(0).xloc, 
				model2.getOnScreenCollidables().get(0).xloc);
		assertEquals(model.getOnScreenCollidables().size(), 
				model2.getOnScreenCollidables().size());
		
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
	public void setBirdModeTest() throws NoSuchFieldException, IllegalAccessException {
	        
        final Model ModelTestObject= new Model(10,10);
  
        ModelTestObject.setBirdMode(true);

        //then
        final Field field = ModelTestObject.getClass().getDeclaredField("birdMode");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(ModelTestObject), true);
    }
	
	

    @Test
    public void isBirdModeTest() throws NoSuchFieldException, IllegalAccessException {
        
    	final Model ModelTestObject= new Model(10,10);
        
    	final Field field = ModelTestObject.getClass().getDeclaredField("birdMode");
        field.setAccessible(true);
        field.set(ModelTestObject, true); 

        final boolean value = ModelTestObject.isBirdMode();
       
       // assertEquals("Field wasn't retrieved properly", value, true);
       
        assertTrue(ModelTestObject.isBirdMode() == true );    
        
    }

	@Test
	public void setQuizModeTest() throws NoSuchFieldException, IllegalAccessException {
	        
        final Model ModelTestObject= new Model(10,10);
  
        ModelTestObject.setQuizMode(true);

        
        final Field field = ModelTestObject.getClass().getDeclaredField("quizMode");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(ModelTestObject), true);
    }
	

    @Test
    public void isQuizMode() throws NoSuchFieldException, IllegalAccessException {
        
    	final Model ModelTestObject= new Model(10,10);
        
    	final Field field = ModelTestObject.getClass().getDeclaredField("quizMode");
        field.setAccessible(true);
        field.set(ModelTestObject, true); 

        final boolean value = ModelTestObject.isBirdMode();
        assertEquals("Field wasn't retrieved properly", (Object)value, (Object)true);
    }

	@Test
	public void setQuizQuestionsTest() throws NoSuchFieldException, IllegalAccessException {
	        
	        final Model ModelTestObject= new Model(10,10);
	        QuizQuestion q1 = new QuizQuestion();
	        List<QuizQuestion> questions = new ArrayList<>();
	        questions.add(q1);
	        ModelTestObject.setQuizQuestions(questions);      
	        final Field field = ModelTestObject.getClass().getDeclaredField("quizQuestions");
	        field.setAccessible(true);
	        assertEquals("Fields didn't match", field.get(ModelTestObject), true);
	    }
		

		    @Test
		    public void getQuizQuestionsTest() throws NoSuchFieldException, IllegalAccessException {
		        
		    	final Model ModelTestObject= new Model(10,10);
		      	final Field field = ModelTestObject.getClass().getDeclaredField("quizQuestions");
		        field.setAccessible(true);
		        QuizQuestion q1 = new QuizQuestion();
		        List<QuizQuestion> questions = new ArrayList<>();
		        questions.add(q1);
		        field.set(ModelTestObject, questions); 

		        final List<QuizQuestion> value = ModelTestObject.getQuizQuestions();
		        assertEquals("Field wasn't retrieved properly", value, questions);
		    }	
	
		    @Test
		    public void getFrameWidthTest() throws NoSuchFieldException, IllegalAccessException {
		        
		    	final Model ModelTestObject= new Model(10,10);
		      	final Field field = ModelTestObject.getClass().getDeclaredField("frameWidth");
		        field.setAccessible(true);
		        field.set(ModelTestObject, 5); 
		        final int value = ModelTestObject.getFrameWidth();
		        assertEquals("Field wasn't retrieved properly", value, 5);
		    }		   
		
			@Test
			public void setFrameWidthTest() throws NoSuchFieldException, IllegalAccessException {
			        
			        final Model ModelTestObject= new Model(10,10);
			        ModelTestObject.setFrameWidth(4);      
			        final Field field = ModelTestObject.getClass().getDeclaredField("frameWidth");
			        field.setAccessible(true);
			        assertEquals("Fields didn't match", field.get(ModelTestObject), 4);
			    }
	
		    @Test
		    public void getFrameHeightTest() throws NoSuchFieldException, IllegalAccessException {
		        
		    	final Model ModelTestObject= new Model(10,10);
		      	final Field field = ModelTestObject.getClass().getDeclaredField("frameHeight");
		        field.setAccessible(true);
		        field.set(ModelTestObject, 5); 
		        final int value = ModelTestObject.getFrameHeight();
		        assertEquals("Field wasn't retrieved properly", value, 5);
		    }		   
		
			@Test
			public void setFrameHeightTest() throws NoSuchFieldException, IllegalAccessException {
			        
			        final Model ModelTestObject= new Model(10,10);
			        ModelTestObject.setFrameHeight(4);      
			        final Field field = ModelTestObject.getClass().getDeclaredField("frameHeight");
			        field.setAccessible(true);
			        assertEquals("Fields didn't match", field.get(ModelTestObject), 4);
			    }
		
			@Test
		    public void getImgWidthTest() throws NoSuchFieldException, IllegalAccessException {
		        
		    	final Model ModelTestObject= new Model(10,10);
		      	final Field field = ModelTestObject.getClass().getDeclaredField("imgWidth");
		        field.setAccessible(true);
		        field.set(ModelTestObject, 5); 
		        final int value = ModelTestObject.getImgWidth();
		        assertEquals("Field wasn't retrieved properly", value, 5);
		    }		   
		
			@Test
			public void setImgWidthTest() throws NoSuchFieldException, IllegalAccessException {
			        
			        final Model ModelTestObject= new Model(10,10);
			        ModelTestObject.setImgWidth(4);      
			        final Field field = ModelTestObject.getClass().getDeclaredField("imgWidth");
			        field.setAccessible(true);
			        assertEquals("Fields didn't match", field.get(ModelTestObject), 4);
			    }
		    @Test
		    public void getImgHeightTest() throws NoSuchFieldException, IllegalAccessException {
		        
		    	final Model ModelTestObject= new Model(10,10);
		      	final Field field = ModelTestObject.getClass().getDeclaredField("imgHeight");
		        field.setAccessible(true);
		        field.set(ModelTestObject, 5); 
		        final int value = ModelTestObject.getImgHeight();
		        assertEquals("Field wasn't retrieved properly", value, 5);
		    }		   
		
			@Test
			public void setImgHeightTest() throws NoSuchFieldException, IllegalAccessException {
			        
			        final Model ModelTestObject= new Model(10,10);
			        ModelTestObject.setImgHeight(4);      
			        final Field field = ModelTestObject.getClass().getDeclaredField("imgHeight");
			        field.setAccessible(true);
			        assertEquals("Fields didn't match", field.get(ModelTestObject), 4);
			    }
   
	    
	void spawnGameElementTest() {
		Model m = new Model(10,10);
		m.spawnGameElement();
		GameElement c = m.getOnScreenCollidables().get(3);
		assertEquals(true,c.getXloc() <= 10 && c.getXloc() >= 0 && c.getYloc() <= 10 && c.getYloc() >= 0);
		assertEquals(c.getxSpeed(),10);
		assertEquals(c.getySpeed(),0);
	}
}

