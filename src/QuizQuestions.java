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
	
	/**
	 * 
	 * @param filename: the file that stores all of the quiz questions
	 * The file format is question, answers, and correct all on separate lines
	 */
	public QuizQuestions(String filename) {
		this.unusedQuestions = new ArrayList<QuizQuestion>();
		this.current = null; 
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
							this.unusedQuestions.add(new QuizQuestion(question, answers, correct));
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
		QuizQuestion result = unusedQuestions.get(0);
		unusedQuestions.remove(result); 
		current = result; 
	}
	/**
	 * 
	 * @return an int of the number of questions remaining
	 */
	public int questionsRemaining() {
		return this.unusedQuestions.size(); 
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.unusedQuestions.toString(); 
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
	 * @return true if the answer is correct and false is the 
	 * answer is incorrect. 
	 */
	public boolean answerQuestion(String answer) {
		boolean result = this.current.getCorrectAnswer().equals(answer); 
		if (result) {
			newQuestion(); 
		}
		return result; 
	}
	
	public static void main(String[] args) {
		QuizQuestions qs = new QuizQuestions("images/questions.txt"); 
		
		System.out.println(qs); 
	}
	
	
}
