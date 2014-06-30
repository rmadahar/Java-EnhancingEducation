package com.edu;

/**
 * This class controls the scores in the game for both players.
 * 
 * @author Rajin
 * 
 */

public class WordJumbleScore {

	public static int score1;
	public static int score2;

	/*
	 * Constructor for WordJumbleScore class
	 */
	public WordJumbleScore() {

		// initialises score to 0
		score1 = 0;
		score2 = 0;
	}

	/**
	 * This method sets the score
	 */

	public static void setScore() {
		// if the user has guessed correct and the user is player 2
		if ((WordJumbleMain.correctAnswer.getText().equals("Correct"))
				&& (WordJumbleMain.state2.getText()
						.equals(WordJumbleMain.Player2
								+ " please guess the word"))) {
			score2 = score2 + 10;
			WordJumbleMain.ScoreP2.setText(String.valueOf(score2));
			WordJumbleMain.state2.setText("");
		}
		// if the user has guessed correct and the user is player 1
		else if ((WordJumbleMain.correctAnswer.getText().equals("Correct"))
				&& (WordJumbleMain.state2.getText()
						.equals(WordJumbleMain.Player1
								+ " please guess the word"))) {
			score1 = score1 + 10;
			WordJumbleMain.ScoreP1.setText(String.valueOf(score1));
			WordJumbleMain.state2.setText("");
		}
	}

}
