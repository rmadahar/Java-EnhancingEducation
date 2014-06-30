package com.edu;

import android.view.View;

/**
 * This class checks the answer against the word jumbled.
 * 
 * @author Rajin
 * 
 */

public class WordJumbleCheck {

	public static String wordChk;

	/**
	 * This method validates the answer entered by the user by comparing the
	 * word jumbled and the word entered.
	 */
	public static void getAnswer() {

		// Stores answer from user in string object
		wordChk = WordJumbleMain.wordAnsE.getText().toString();
		// if the attempts are greater than 3 user lost
		if (WordJumbleTurns.Turns == 0) {
			// set attempts back to 3
			WordJumbleTurns.Turns = 3;
			WordJumbleMain.ImageTurn1.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn2.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn3.setVisibility(View.VISIBLE);

		} else {
			// decrement the attempts by 1
			WordJumbleTurns.Turns--;
			// if the word jumbled is equal to the word entered
			if (WordJumbleMain.getWordE.equalsIgnoreCase(wordChk)) {
				// set the text as correct answer
				WordJumbleMain.correctAnswer.setText("Correct");
				// turn the on click listener off
				WordJumbleMain.ConfirmAnswer.setClickable(false);
			} else {
				// if the word jumbled is not equal to the word entered
				if (!(WordJumbleMain.getWordE.equals(wordChk))) {
					// set the text as wrong answer
					WordJumbleMain.correctAnswer.setText("Incorrect");
				}
			}
		}
	}

}
