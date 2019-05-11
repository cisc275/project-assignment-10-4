import java.util.*; 
import java.io.*;
@SuppressWarnings("serial")
public class QuizQuestions implements Serializable{
	/**
	 * A list of the unused QuizQuestions
	 */
	private List<QuizQuestion> unusedQuestions;
	/**
	 * The current question of the game
	 */
	private QuizQuestion current;
	
	//private QuizQuestion last; 
	
	/**
	 * 
	 * @param filename: the file that stores all of the quiz questions
	 * The file format is question, answers, and correct all on separate lines
	 */
	public QuizQuestions(String filename) {
		this.setUnusedQuestions(new ArrayList<QuizQuestion>());
		this.current = null; 
		List<String> qs = new ArrayList<String>(); 
		qs.add("you gucci"); 
		qs.add("so powerful"); 
		qs.add("you is a winner in my <3"); 
		qs.add("so smart"); 
		//this.last = new QuizQuestion("Yo you win", qs, "I'm crying bc sad"); 
		/**
		 * Create the questions
		 */
		try {
			BufferedReader reader = new BufferedReader(new 
			FileReader(filename));
			String line;
			int index = 0;
			String question = ""; 
			List<String> answers = new ArrayList<String>(); 
			String correct = ""; 
			while ((line = reader.readLine()) != null) {
				switch(index) {
					case 0: question = line;
							answers = new ArrayList<String>(); 
							break; 
					case 1: answers.add(line); 
							break; 
					case 2: answers.add(line); 
							break; 
					case 3: answers.add(line); 
							break; 
					case 4: answers.add(line); 
							break; 
					case 5: correct = line;
							this.getUnusedQuestions().add(new QuizQuestion(question, answers, correct));
							break; 
				}
				index = (index + 1) % 7; 
			}
			reader.close();
			} 
		catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}
		newQuestion(); 
			
		
	}
	/**
	 * Gets a newQuestion from the list
	 */
	public void newQuestion() {
		if (questionsRemaining() > 0) {
			QuizQuestion result = getUnusedQuestions().get(0);
			getUnusedQuestions().remove(result); 
			current = result;
		} 
	}
	/**
	 * 
	 * @return an int of the number of questions remaining
	 */
	public int questionsRemaining() {
		return this.getUnusedQuestions().size(); 
	}
	/**
	 * returns the unused questions as a String
	 * @return String of unused questinos
	 */
	@Override
	public String toString() {
		return this.getUnusedQuestions().toString(); 
	}
	/**
	 * 
	 * @return the current QuizQuestion
	 */
	public QuizQuestion getCurrent() {
		return this.current; 
	}
	/**
	 * 
	 * @param answer: a String representing the user's answer
	 * @return true if the answer to quiz question is correct and false is the 
	 * answer is incorrect. 
	 */
	public boolean answerQuestion(String answer) {
		boolean result = this.current.getCorrectAnswer().equals(answer); 
		if (result) {
			newQuestion(); 
		}
		return result; 
	}
	/**
	 * @return the unusedQuestions
	 */
	public List<QuizQuestion> getUnusedQuestions() {
		return unusedQuestions;
	}
	/**
	 * @param unusedQuestions the unusedQuestions to set
	 */
	public void setUnusedQuestions(List<QuizQuestion> unusedQuestions) {
		this.unusedQuestions = unusedQuestions;
	}
	
	/**
	 * returns true if there are no more unused questions
	 * returns false if there are still unused questions
	 * @return boolean
	 */
	public boolean noMoreQuestions() {
		return unusedQuestions.size() == 0; 
	}
}
