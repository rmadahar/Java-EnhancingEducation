package com.edu;

import android.media.MediaPlayer;
import android.view.View;

/**
 * This class controls the state of the buttons on Enter and Answer
 * 
 * @author Rajin
 * 
 */

public class WordJumbleStatus {

	MediaPlayer mp; 
	
	
	/**
	 * This method changes the state when the player enters a word
	 */
	public static void changeStateOnPlayerEnter() {
		// if the textView is equal to player 1 enter word
		if (WordJumbleMain.state1.getText().equals(
				WordJumbleMain.Player1 + " please enter a word")) {
			// change the state to let the player know to wait
			WordJumbleMain.state1.setText(WordJumbleMain.Player1 + "please wait...");
			// change the state in state2 textview to player 2 guess word
			WordJumbleMain.state2.setText(WordJumbleMain.Player2 + " please guess the word");
			// set the confirm button to true
			WordJumbleMain.ConfirmAnswer.setClickable(true);
			// make sure all the turns are shown
			WordJumbleMain.ImageTurn1.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn2.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn3.setVisibility(View.VISIBLE);
			WordJumbleTurns.Turns = 3;
		}
		//
		else if (WordJumbleMain.state1.getText().equals(
				WordJumbleMain.Player1 + "please wait...")) {
			// set the confirm button true
			WordJumbleMain.ConfirmAnswer.setClickable(true);
		}
		// if the textView is equal to player 2
		else if (WordJumbleMain.state1.getText().equals(
				WordJumbleMain.Player2 + " please enter a word")) {
			WordJumbleMain.state1.setText(WordJumbleMain.Player2 + "please wait...");
			WordJumbleMain.state2.setText(WordJumbleMain.Player1 + " please guess the word");
			WordJumbleMain.ConfirmAnswer.setClickable(true);
			WordJumbleMain.ImageTurn1.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn2.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn3.setVisibility(View.VISIBLE);
			WordJumbleTurns.Turns = 3;
		}
	}

	/**
	 * This method changes the state of the game when the player loses
	 */
	public static void changeStateOnPlayerAnswer() {

		if ((WordJumbleMain.correctAnswer.getText().equals("Correct")) && 
				(WordJumbleMain.state2.getText().equals(WordJumbleMain.Player2 
				+ " please guess the word"))) {
			// change the state to player 2 take a turn
			WordJumbleMain.state1.setText(WordJumbleMain.Player2 + " please enter a word");
			WordJumbleMain.wordAnsE.setText("");
			// switch state of buttons on click boolean
			WordJumbleMain.ConfirmJumble.setClickable(true);
			WordJumbleMain.ConfirmAnswer.setClickable(false);
			// set the images back to intial state
			WordJumbleMain.ImageTurn1.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn2.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn3.setVisibility(View.VISIBLE);
			// set the turns to 3
			WordJumbleTurns.Turns = 3;
			// clear the jumbled word
			WordJumbleMain.jumble.setText("");
		} else if ((WordJumbleMain.correctAnswer.getText().equals("Correct"))
				&& (WordJumbleMain.state2.getText()
						.equals(WordJumbleMain.Player1
								+ " please guess the word"))) {
			// change the state to player 2 take a turn
			WordJumbleMain.state1.setText(WordJumbleMain.Player1
					+ " please enter a word");
			WordJumbleMain.wordAnsE.setText("");
			// switch state of buttons on click boolean
			WordJumbleMain.ConfirmJumble.setClickable(true);
			WordJumbleMain.ConfirmAnswer.setClickable(false);
			// set the images back to intial state
			WordJumbleMain.ImageTurn1.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn2.setVisibility(View.VISIBLE);
			WordJumbleMain.ImageTurn3.setVisibility(View.VISIBLE);
			// set the turns to 3
			WordJumbleTurns.Turns = 3;
			// clear the jumbled word
			WordJumbleMain.jumble.setText("");
		}
		
	}
	
	
	/*
	 * This method resets the game to the original state.
	 */
	public static void resetGame() {
		WordJumbleMain.state1.setText(WordJumbleMain.Player1 + " please enter a word");
		WordJumbleMain.correctAnswer.setText("Answer");
		WordJumbleMain.jumble.setText("");
		WordJumbleMain.state2.setText("");
		WordJumbleMain.wordE.setText("");
		WordJumbleMain.wordAnsE.setText("");
		WordJumbleTurns.Turns = 3;
		WordJumbleMain.ImageTurn1.setVisibility(View.VISIBLE);
		WordJumbleMain.ImageTurn2.setVisibility(View.VISIBLE);
		WordJumbleMain.ImageTurn3.setVisibility(View.VISIBLE);
		WordJumbleScore.score1 = 0;
		WordJumbleMain.ScoreP1.setText(String.valueOf(WordJumbleScore.score1));
		WordJumbleScore.score2 = 0;
		WordJumbleMain.ScoreP2.setText(String.valueOf(WordJumbleScore.score2));
		WordJumbleMain.ConfirmAnswer.setClickable(false);	
		WordJumbleMain.ConfirmJumble.setClickable(true);
	}

	
	
}