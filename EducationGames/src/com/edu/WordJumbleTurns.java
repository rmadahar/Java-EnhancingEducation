package com.edu;

import android.view.View;

/**
 * This class controls the turns within the game. 
 * 
 * @author Rajin
 *
 */


public class WordJumbleTurns {


	public static int Turns;
	
	public WordJumbleTurns(){
		Turns = 3;
	}
	
	
	/*
	 *This method validates when the user has run out of turns
	 */
	public static  void endOfTurns(){
		//if the turns have reached 0 and the state of the entered text 
		if((Turns==0) && (WordJumbleMain.state1.getText().equals(WordJumbleMain.Player1 + "please wait..."))){
			WordJumbleMain.correctAnswer.setText("Unlucky");
			//Switch Clickable buttons
			WordJumbleMain.ConfirmAnswer.setClickable(false);
			WordJumbleMain.ConfirmJumble.setClickable(true);
			//clear following:
			WordJumbleMain.state2.setText("");
			WordJumbleMain.jumble.setText("");
			WordJumbleMain.wordAnsE.setText("");
			//Change the state to show player 2
			WordJumbleMain.state1.setText(WordJumbleMain.Player2 + " please enter a word");
			//Show user they have run out of turns
		}
		//else if the turns are 0 and the state of the entered text is player 2
		else if((Turns==0) && (WordJumbleMain.state1.getText().equals(WordJumbleMain.Player2 + "please wait..."))){
			WordJumbleMain.correctAnswer.setText("Unlucky");
			//Switch clickable buttons
			WordJumbleMain.ConfirmAnswer.setClickable(false);
			WordJumbleMain.ConfirmJumble.setClickable(true);
			//clear following:
			WordJumbleMain.state2.setText("");
			WordJumbleMain.jumble.setText("");
			WordJumbleMain.wordAnsE.setText("");
			//Change state to show player 1
			WordJumbleMain.state1.setText(WordJumbleMain.Player1 + " please enter a word");
			//textToast("Unlucky Player 1, You have run out of turns");
		}
	}


	
	/**
	 * This  method controls the turn taking
	 * shown to the user. 
	 */
	public static void amountOfTurns(){
		if(Turns==2){
			WordJumbleMain.ImageTurn1.setVisibility(View.GONE);
			WordJumbleMain.ImageTurn2.setImageResource(R.drawable.red_circle);
			WordJumbleMain.ImageTurn3.setImageResource(R.drawable.red_circle);
		
			}
		else if (Turns==1){
			WordJumbleMain.ImageTurn1.setImageResource(R.drawable.red_circle);
			WordJumbleMain.ImageTurn2.setVisibility(View.GONE);
			WordJumbleMain.ImageTurn3.setImageResource(R.drawable.red_circle);
			
		}
		
		else if (Turns==0){
			WordJumbleMain.ImageTurn1.setImageResource(R.drawable.red_circle);
			WordJumbleMain.ImageTurn2.setImageResource(R.drawable.red_circle);
			WordJumbleMain.ImageTurn3.setVisibility(View.GONE);
		}
		
	}
	
	
}
