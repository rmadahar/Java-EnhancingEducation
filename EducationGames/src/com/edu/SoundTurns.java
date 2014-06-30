package com.edu;

import android.view.View;
import android.widget.TextView;

/**
 * This is the class which controls the turns in the game. 
 * The images for the turns disappear on each turn and 
 * 
 * @author Rajin
 *
 */

public class SoundTurns {

	
	
	static TextView ScoreTV = SoundChallengeMain.ScoreTV;
	static TextView GameOver =  SoundChallengeMain.GameOver;
	public static int validateOneTurn = 0;
	
	/*
	 * This method sets the validation of the images for the 
	 * turns. 
	 */
	public static void amountOfTurns(){
		
		 int Turns = SoundChallengeMain.Turns;
		
		if(Turns==3){
			SoundChallengeMain.ImageTurn1.setVisibility(View.GONE);
			SoundChallengeMain.ImageTurn2.setImageResource(R.drawable.speaker);
			SoundChallengeMain.ImageTurn3.setImageResource(R.drawable.speaker);
		
			}
		else if (Turns==2){
			SoundChallengeMain.ImageTurn1.setImageResource(R.drawable.speaker);
			SoundChallengeMain.ImageTurn2.setVisibility(View.GONE);
			SoundChallengeMain.ImageTurn3.setImageResource(R.drawable.speaker);
			
		}
		
		else if (Turns==1){
			SoundChallengeMain.ImageTurn1.setImageResource(R.drawable.speaker);
			SoundChallengeMain.ImageTurn2.setImageResource(R.drawable.speaker);
			SoundChallengeMain.ImageTurn3.setVisibility(View.GONE);
		}
		
	}
	
	
	
	/*
	 * This method checks if the user has answered incorrect or correct. 
	 */
	public static void validateTurn(){
		
		int Turns = SoundChallengeMain.Turns;
		String getAnswer = SoundQuestionsAnswers.Answer;
		
		
		if(Turns == 0){
			SoundScore.score = SoundScore.score - 10;
			 SoundChallengeMain.ScoreTV.setText("Score:" + String.valueOf(SoundScore.score));
			 SoundChallengeMain.GameOver.setText("Unlucky Press Play");
		}	
		//if the turns have gone and the answer is incorrect show a 
		else if((Turns ==0)&& (getAnswer.equals("Incorrect"))){
		SoundChallengeMain.alertDialog.show();
		
		}
		else if((Turns<3) &&(getAnswer.equals("Correct"))){
			SoundChallengeMain.alertDialog.show();	
		}
		else if((Turns<=0) &&(getAnswer.equals("Incorrect"))){
			SoundChallengeMain.alertDialog.show();	
		}
	}
	
	
	/*
	 * This method makes sure there is only one turn on true or false and yes and no questions
	 * and takes the score away if wrong
	 */
	public static void oneTurn(){
		
	String getAnswer = SoundQuestionsAnswers.Answer;
		int Index = SoundQuestionsAnswers.Index;
		 int score = SoundScore.score;
	
		
		if((getAnswer.equals("Incorrect") && ((Index == 8)))){
			SoundChallengeMain.alertDialog2.show();
			  score = score - 10;
			  ScoreTV.setText("Score:" + String.valueOf(score));
			  GameOver.setText("Unlucky Press Play");
			SoundChallengeMain.confirm.setClickable(false);
		}
		
	else if((getAnswer.equals("Incorrect") && ((Index == 10)))){
			SoundChallengeMain.alertDialog2.show();
					score = score - 10;
					ScoreTV.setText("Score:" + String.valueOf(score));
					 GameOver.setText("Unlucky Press Play");
		}
		else if((getAnswer.equals("Incorrect") && ((Index == 11)))){
			SoundChallengeMain.alertDialog2.show();
		 score = score - 10;
			 ScoreTV.setText("Score:" + String.valueOf(score));
			 GameOver.setText("Unlucky Press Play");
		}
		else if((getAnswer.equals("Incorrect") && ((Index == 13)))){
			SoundChallengeMain.alertDialog2.show();
		  score = score - 10;
			 ScoreTV.setText("Score:" + String.valueOf(score));
			 GameOver.setText("Unlucky Press Play");
		}
		else if((getAnswer.equals("Incorrect") && ((Index == 14)))){
			SoundChallengeMain.alertDialog2.show();
		  	score = score - 10;
			 ScoreTV.setText("Score:" + String.valueOf(score));
			 GameOver.setText("Unlucky Press Play");
		}
		else if((getAnswer.equals("Incorrect") && ((Index == 16)))){
			SoundChallengeMain.alertDialog2.show();
		  score = score - 10;
			ScoreTV.setText("Score:" + String.valueOf(score));
			GameOver.setText("Unlucky Press Play");
		}
		else if((getAnswer.equals("Incorrect") && ((Index == 17)))){
			SoundChallengeMain.alertDialog2.show();
		  	score = score - 10;
			 ScoreTV.setText("Score:" + String.valueOf(score));
			GameOver.setText("Unlucky Press Play");
		}
		else if((getAnswer.equals("Incorrect") && ((Index == 19)))){
			SoundChallengeMain.alertDialog2.show();
		  	score = score - 10;
			 ScoreTV.setText("Score:" + String.valueOf(score));
			 GameOver.setText("Unlucky Press Play");
		}
	}	

}
