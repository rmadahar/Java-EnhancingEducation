package com.edu;

import android.app.Application;

/** 
 * This is the QuizApp class which extends android.app.Application
 * This class uses set and get methods to control global items. 
 * The global items for the number of questions in the quiz and the score.
 * 
 * @author Rajin Madahar
 **/


public class QuizApp extends Application {
	
	private int currentQuestionID = 0;
	private int score = 0;
	private int numberOfQuestionsInQuiz = 0;
		
	
	//Gets the number of questions in the quiz
	public int getNumberOfQuestionsInQuiz() {
		return numberOfQuestionsInQuiz;
	}

	//Sets the number of questions in the quiz with the current number
	public void setNumberOfQuestionsInQuiz(int numberOfQuestionsInQuiz) {
		this.numberOfQuestionsInQuiz = numberOfQuestionsInQuiz;
	}

	//gets the question ID
	public int getCurrentQuestionID() {
		return currentQuestionID;
	}

	//sets the current question ID
	public void setCurrentQuestionID(int currentQuestionID) {
		this.currentQuestionID = currentQuestionID;
	}

	//Gets the score
	public int getScore() {
		return score;
	}

	//sets the current score 
	public void setScore(int score) {
		this.score = score;
	}

	
	public QuizApp()
	{
		super();
	}
}
