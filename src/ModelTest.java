import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

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
	        assertEquals("Field wasn't retrieved properly", value, true);
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
		        assertEquals("Field wasn't retrieved properly", value, true);
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

			        final boolean value = ModelTestObject.isBirdMode();
			        assertEquals("Field wasn't retrieved properly", value, questions);
			    }		   
	}
	
	
	
	
	
	

	//private List<QuizQuestion> quizQuestions;
	/**
	 * The width of the game frame
	 */
	//private int frameWidth;
	/**
	 * The height of the game frame
	 */
    //private int frameHeight;
    /**
	 * the width of the image
	 */
	//private int imgWidth;
	/**
	 * the height of the image
	 */
	//private int imgHeight;
	    

