package com.edu;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * This class sets the score and adds the score depending on the amount 
 * of turns the user has taken. When the answer is correct or incorrect it sets  
 * the state of the game. If the user has run out of turns the score will decrease
 * by 10. Within this class there is also validation if the answer is correct or incorrect
 * and the amount of turns. 
 * 
 * @author Rajin Madahar
 */

public class SoundScore {

	static TextView ScoreTV = SoundChallengeMain.ScoreTV;
	static TextView GameOver =  SoundChallengeMain.GameOver;
	public static int score = 0;
	

	
	
	/**
	 * This method checks the score against the number of turns the user has taken. 
	 * The image will also change to show the status of incorrect or correct.
	 */
	public static void setScore(){
		//stores answer in ans object
		
		 String CorrectAns = SoundQuestionsAnswers.setAnswer(); 
		 int Turns = SoundChallengeMain.Turns;
	     ImageView Status = SoundChallengeMain.Status;
	     EditText Answer = SoundChallengeMain.Answer;
		  
	     
		 //if the answer is correct and the answer is correct on the first go
			if((CorrectAns.equals("Correct")) && (Turns == 3)){
			    //add 20 points to the score
				score = score + 20;
				//set the status image to show the tick
				Status.setImageResource(R.drawable.orange_tick);
				//set the score in the textview
				ScoreTV.setText("Score:" + String.valueOf(score));
				//clear the entered text
				Answer.setText(""); 
				//set validate to 1;
				SoundChallengeMain.validate = 1;	
			}
			//if the answer is correct on the second turn add 15 points
			else if((CorrectAns.equals("Correct")) && (Turns == 2)){
				score = score + 15;
				Status.setImageResource(R.drawable.orange_tick);
				ScoreTV.setText(   "Score:" + String.valueOf(score));
				Answer.setText(""); 
				SoundChallengeMain.alertDialog.show();
			}
			//if the answer is correct on the third turn add 10 points
			else if((CorrectAns.equals("Correct")) && (Turns == 1)){
				//SoundChallengeMain.score++;
				score = score + 10;
				Status.setImageResource(R.drawable.orange_tick);
				ScoreTV.setText(   "Score:" + String.valueOf(score));
				Answer.setText(""); 
				
			}
			//if the answer is incorrect deduct 
			else if(CorrectAns.equals("Incorrect")){
				SoundChallengeMain.Turns--;
				Status.setImageResource(R.drawable.green_cross);
			    
				Answer.setText(""); 
			}
			else if(SoundQuestionsAnswers.Index==0){
				ScoreTV.setText("Score: " + "");
			}
		}
		
	

}