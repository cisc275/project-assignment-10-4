import java.io.Serializable;
import java.util.*;

/**
 * Represents a quiz question that will be prompted throughout the game.
 * There are multiple questions.
 * 
 * @author 10-4
 *
 */
@SuppressWarnings("serial")
public class QuizQuestion implements Serializable{
	/**
	 * An int that stores the index of the correct answer
	 */
	private String correctAnswer;
	/**
	 * The question that the player needs to answer
	 */
	private String question;
	/**
	 * A list of potential answers to the multiple choice question
	 */
	private List<String>answers;
	/**
	 * 
	 * @param question: the quiz question
	 * @param answers: the list of possible answers 
	 * @param correct: the correct answer
	 */
	public QuizQuestion(String question, List<String> answers, String correct) {
		this.question = question; 
		this.answers = answers; 
		this.correctAnswer = correct; 
		
	}
	/**
	 * Checks whether the question has been answered correctly
	 * 
	 * @return True is answered correct, false otherwise.
	 */
	boolean checkCorrect(String userAnswer) {
		return correctAnswer.equals(userAnswer);
	}

	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answers
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	/**
	 * @return a String representation of the Quiz Question
	 */
	public String toString() {
		return this.question + " " + this.answers + " " + this.correctAnswer; 
	}
}
