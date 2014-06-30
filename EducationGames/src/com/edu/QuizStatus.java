package com.edu;


/**
 * This class holds methods which set the score in the game
 * set's and increments the questions and checks the game status. 
 * 
 * @author Rajin Madahar
 */


public class QuizStatus {

	public static int pointsBeingRisked = 25;
	public static int rowIndex = 1;
	
	/** This method sets the score **/
	public static void setScore() {
		// gets the points chosen from SeekBar
		pointsBeingRisked = Integer.parseInt(QuizScrollAnimation.get());
		 //sets pointsBeingRisked in TextView
		QuizMain.ScoreTv.setText(String.valueOf(pointsBeingRisked));
		
		QuizMain.ScoreTv.setFocusable(true);
		QuizMain.ScoreTv.setFocusableInTouchMode(true);
		QuizMain.ScoreTv.setClickable(true);
	}
	
	
	/** This method sets the question **/
	public static void setQuestion() {
		if (rowIndex <= QuizMain.app.getNumberOfQuestionsInQuiz()){
			// store rowIndex into c1 cursor
			QuizMain.c1 = QuizMain.db.getQuiz_Content(rowIndex);
			//sets the rowIndex into the QuizApp class
			QuizMain.app.setCurrentQuestionID(rowIndex);
			rowIndex++;// increments row index each time called.
		}
		// calls the textview to display
		QuizMain.valid.setText(QuizMain.c1.getString(0));
	}

	
	/** This method changes the state once the game is over **/
	public static void gameStatus() {
			if (QuizMain.tv.getText().equals("20")) {							// to display
				QuizMain.tv.setText("Game Over " + "Your final score is: " + QuizMain.currentScore.getText());
				rowIndex = 1;
				QuizMain.app.setScore(0);
				QuizMain.currentScore.setText("");
			}
	}
}
