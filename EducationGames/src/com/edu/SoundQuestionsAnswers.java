package com.edu;

/**
 * 
 * This class uses the TextToSpeech API and holds the questions and Answerwers. 
 * 
 * @author Rajin Madahar
 */

import android.speech.tts.TextToSpeech;

public class SoundQuestionsAnswers {
	
	public static TextToSpeech mTts = SoundChallengeMain.mTts;
	public static int Index =0;
	public static String Answer;

	//stores question in String array
	static final String[] Questions = {
		  "Lennie crushed blank hand",	
		"George and Lennie dream of owning a blank one day",
			"Lennie had a dead blank in his pocket",
			"What does Lennie like to eat with his beans", 
			"Candy, the old swamper who shows George and Lennie to their bunks, is missing what limb", 
			"According to Candy, what is Curley good at",
			"George played his last trick on Lennie but telling him to jump into the blank",
			"True or False did Lennie thank George for saving him after he told him to jump in the water",
			"Who made the final decision on whether Candy’s dog would be shot",
			"True or False Curley’s wife loved to have deep conversations with him",
			"True or False towards the end of the book Curley’s wife admits to never loving him",
			"Lennies puppy blank from being mishandled by Lennie",
			"True or False Curley’s wife tells Lennie she wants to be a ballet dancer",
			"True or False Curley’s wife tells Lennie she doesn’t like her husband",
			"While stroking Curley’s wife’s hair Lennie broke her blank",
			"True or False Curley is afraid that he will be  disposed of when he is no longer in use in the ranch",
			"True or False in the book Lennie imagines a rabbit talking to him",
			"True or False a rabbit tells Lennie George will leave him",
			"Does George lie in the end and say he fought with Lennie",
			"Who stays with with curleys wife as the other go off in pursuit of lennie"};	


/*
 * This method gets the question and replays it 
 * by playing the previous index as the index moves 
 * forward on play
 */
public static void replay (){
	//As index moves forward play the last question
	String getQuestion = Questions[Index-1];
	mTts.speak(getQuestion, TextToSpeech.QUEUE_FLUSH, 
			null);
}

/*
 * This method plays the question using the TextToSpeech API
 */
public static void sayQuestion() {
	//store array index within getQuestion object
	String getQuestion = Questions[Index];
	//The index object + 1
	Index = (Index + 1);
	//Plays the question using the TTS engine
	mTts.speak(getQuestion, TextToSpeech.QUEUE_FLUSH,
			null);
}



/*
 * This method returns the answer depending on the question Index
 * 
 * @return
 */
public static String setAnswer() {
	Answer = SoundChallengeMain.Answer.getText().toString();	
	
	if ((Answer.equalsIgnoreCase("Curley's")) && (Index == 1)) {
		Answer = "Correct";
		//check.setText("Correct");
		//score++;
	}
	else if ((Answer.equalsIgnoreCase("Farm")) && (Index == 2)) {
		Answer = "Correct";
		//check.setText("Correct");
	} else if((Answer.equalsIgnoreCase("Mouse")) && (Index == 3)){
		Answer = "Correct";
	//check.setText("Correct");
	 
	      }
	
	else if((Answer.equalsIgnoreCase("Ketchup")) && (Index == 4)){
		Answer = "Correct";
	//check.setText("Correct");

	      }	
	
	else if((Answer.equalsIgnoreCase("Hand")) && (Index == 5)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	
	else if((Answer.equalsIgnoreCase("Boxing")) && (Index == 6)){
		Answer = "Correct";
	//check.setText("Correct");

	      }	
	else if((Answer.equalsIgnoreCase("Water")) && (Index == 7)){
		Answer = "Correct";
	//check.setText("Correct");
	      }	
	
	else if((Answer.equalsIgnoreCase("True")) && (Index == 8)){
		Answer = "Correct";
	//check.setText("Correct");
	      }	
	
	else if((Answer.equalsIgnoreCase("Candy")) && (Index == 9)){
		Answer = "Correct";
	//check.setText("Correct");
	      }	
	
	else if((Answer.equalsIgnoreCase("False")) && (Index == 10)){
		Answer = "Correct";
	//check.setText("Correct");

	      }	
	
	else if((Answer.equalsIgnoreCase("True")) && (Index == 11)){
		Answer = "Correct";
	//check.setText("Correct");
	      }	
	
	else if((Answer.equalsIgnoreCase("Dies")) && (Index == 12)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	
	else if((Answer.equalsIgnoreCase("False")) && (Index == 13)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	else if((Answer.equalsIgnoreCase("True")) && (Index == 14)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	else if((Answer.equalsIgnoreCase("Neck")) && (Index == 15)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	else if((Answer.equalsIgnoreCase("True")) && (Index == 16)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	else if((Answer.equalsIgnoreCase("True")) && (Index == 17)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	else if((Answer.equalsIgnoreCase("True")) && (Index == 18)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	else if((Answer.equalsIgnoreCase("Yes")) && (Index == 19)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	
	else if((Answer.equalsIgnoreCase("Candy")) && (Index == 20)){
		Answer = "Correct";
	//check.setText("Correct");
	
	      }	

else    Answer = "Incorrect";	

		return Answer;
	}
	
	
}
