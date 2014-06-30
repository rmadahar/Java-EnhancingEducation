package com.edu;

/*
 *This is the main class for the sound challenge game. The class was adapted from 
 *the text to speech API demos and expanded to show Questions, Answers, Images and Turn Taking. 
 *
 *The text to speech demo also used a random generator which I have amended to increment from the index.
 *
 *@author Rajin Madahar
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SoundChallengeMain extends Activity implements
		TextToSpeech.OnInitListener {

	private final String TAG = "TextToSpeechDemo";
	public static TextToSpeech mTts;
	public static EditText Answer;
	public static TextView ScoreTV;
	public static TextView TurnsTV;
	public static TextView GameOver;
	public static int Turns = 3;
	public static int validate = 0;
	public static ImageView Image;
	public static ImageView Status;
	public static ImageView ImageTurn1;
	public static ImageView ImageTurn2;
	public static ImageView ImageTurn3;
	public ImageView Title;
	public static AlertDialog alertDialog;
	public static AlertDialog alertDialog2;
	public static AlertDialog alertDialog3;
	public Button Replay;
	public Button ShowHint;
	public Button Play;
	public  static Button confirm;
	MediaPlayer mp; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.soundchallengemain);
		Layout();

		// sets dialog for validation if buttons are pressed
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Press Play");
		alertDialog.setMessage("");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog.setIcon(R.drawable.sound_guy);

		
		// sets dialog for validation if player only has one chance for answer
		alertDialog2 = new AlertDialog.Builder(this).create();
		alertDialog2.setTitle("Press Play");
		alertDialog2.setMessage("You only get one chance");
		alertDialog2.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog2.setIcon(R.drawable.sound_guy);
		
		
		
		// sets dialog for validation if player only has one chance for answer
		alertDialog3 = new AlertDialog.Builder(this).create();
		alertDialog3.setTitle("Warning!");
		alertDialog3.setMessage("Only text can be entered or  you have not entered a word");
		alertDialog3.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog3.setIcon(R.drawable.sound_guy);
		

		
		// Initialize text-to-speech. This is an asynchronous operation.
		// The OnInitListener (second argument) is called after initialization
		// completes.
		mTts = new TextToSpeech(this, this // TextToSpeech.OnInitListener
		);

		
		/*
		 * OnClick method used when user clicks show hint
		 */
		ShowHint = (Button) findViewById(R.id.ShowHint);

		ShowHint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (SoundQuestionsAnswers.Index == 0) {
					alertDialog.show();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
		               mp.start(); 
				} else {

					SoundShowHint.imageChange();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
		               mp.start(); 
				}
			}
		});

		/*
		 * OnClick method used when user clicks replay
		 */
		Replay = (Button) findViewById(R.id.btnReplay);
		Replay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (SoundQuestionsAnswers.Index == 0) {
					alertDialog.show();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
		               mp.start(); 
				} else {
					SoundQuestionsAnswers.replay();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
		               mp.start(); 
					
				}
			}
		});

		/*
		 * Onclick method when user click play
		 */
		Play = (Button) findViewById(R.id.Play);
		Play.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if((SoundQuestionsAnswers.Index==20) && (!GameOver.getText().equals("GameOver" + SoundScore.score))){
					textToast("You have reached the last question please answer");
				}
				// if the index reaches the last question
				else if (SoundQuestionsAnswers.Index == 20) {
					// set the question to 0
					SoundQuestionsAnswers.Index = 0;
					// set the score to 0
					SoundScore.score = 0;
				
				} else {
					SoundQuestionsAnswers.sayQuestion();
					Status.setImageResource(R.drawable.status);
					Turns = 3;
					Image.setImageResource(R.drawable.default_hint);
					GameOver.setText("");
					// set validate to go back if user presses play
					validate = 0;
					SoundChallengeMain.confirm.setClickable(true);
					ImageTurn1.setVisibility(View.VISIBLE);
					ImageTurn2.setVisibility(View.VISIBLE);
					ImageTurn3.setVisibility(View.VISIBLE);
					mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
		               mp.start(); 
					
				}
			}
		});

		/*
		 * OnClick method to confirm the answer entered
		 */
		confirm = (Button) findViewById(R.id.btnConfirm);
		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// if a question has not been played
				
				  String str = Answer.getText().toString();
			     
				if (SoundQuestionsAnswers.Index == 0) {
					// show dialog
					alertDialog.show();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
		               mp.start(); 
				}
				//check if the value entered is a number
				else if(str.matches("[0-9]*")){	
					textToast("Only text is valid or you have not entered a word!");
					mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
		               mp.start();
				}
				// validate increments if user gets the answer correct.
				else if (validate == 1){
					alertDialog.show();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
		               mp.start(); }  
				else { // check the score, turn and validate the turn
					showToastOnTurns();
					SoundTurns.amountOfTurns();
					SoundScore.setScore();
					SoundTurns.validateTurn();
					SoundTurns.oneTurn();
					
					 mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
		               mp.start(); 
					// check if game over
					status();
				}
			}
		});
	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown!
		if (mTts != null) {
			mTts.stop();
			mTts.shutdown();
		}
		super.onDestroy();
	}

	// Implements TextToSpeech.OnInitListener.
	@Override
	public void onInit(int status) {
		// if status of TextToSpeech is a successful operation
		if (status == TextToSpeech.SUCCESS) {
			// Set preferred language to US english.
			int result = mTts.setLanguage(Locale.US);
			// checks if the language exists
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				// Lanuage data is missing or the language is not supported.
				Log.e(TAG, "Language is not available.");
			} else {
				// The TTS engine has been successfully initialized.
				// Allow the user to press the button for the app to speak again
				Play.setEnabled(true);
			}
		} else {
			// Initialization failed.
			Log.e(TAG, "Could not initialize TextToSpeech.");
		}
	}

	/*
	 * This method provides the layout
	 */
	public void Layout() {
		Answer = (EditText) findViewById(R.id.Answer);
		ScoreTV = (TextView) findViewById(R.id.Score);
		TurnsTV = (TextView) findViewById(R.id.Turns);
		GameOver = (TextView) findViewById(R.id.GameOver);
		Image = (ImageView) findViewById(R.id.Various);
		Title = (ImageView) findViewById(R.id.Title);
		Status = (ImageView) findViewById(R.id.Status);
		Title.setImageResource(R.drawable.speech_title);
		Status.setImageResource(R.drawable.status);
		Image.setImageResource(R.drawable.default_hint);
		ImageTurn1 = (ImageView) findViewById(R.id.turn1);
		ImageTurn2 = (ImageView) findViewById(R.id.turn2);
		ImageTurn3 = (ImageView) findViewById(R.id.turn3);
		
		
	}

	/*
	 * This method ends the game, shows the score and sets the question number
	 * and score back to 0
	 */
	public void status() {
		if ((SoundQuestionsAnswers.Index == 20) && (Turns == 0)) {
			GameOver.setText("GameOver" + SoundScore.score);
			textToast("GameOver You scored: " + SoundScore.score + "Points");
			Turns = 4;
			SoundQuestionsAnswers.Index = 0;
			SoundScore.score=0;
			ScoreTV.setText("Score:");
			mp = MediaPlayer.create(getApplicationContext(), R.raw.correct); 
            mp.start(); 
		} else if ((SoundQuestionsAnswers.Index == 20)
				&& (SoundQuestionsAnswers.Answer.equals("Correct"))) {
			GameOver.setText("GameOver You scored: " + SoundScore.score + "Points");
			Turns = 4;
			textToast("GameOver You Scored " + SoundScore.score + "Points");
			SoundQuestionsAnswers.Index = 0;
			SoundScore.score=0;
			ScoreTV.setText("Score:");
			mp = MediaPlayer.create(getApplicationContext(), R.raw.correct); 
            mp.start(); 
		}
	}
	
	
	
	public void endGame(){
		if((SoundQuestionsAnswers.Index==20) && (!GameOver.getText().equals("GameOver" + SoundScore.score))){
			textToast("You have reached the last question please answer");
		}
		
	}
	
	
	/*
	 * This method guides the user throughout the game by controlling 
	 * when to show a toast to the user.  
	 */
	
	public void showToastOnTurns(){
		//The object is equal to the returned object
	String validate = SoundQuestionsAnswers.setAnswer(); 
	
	//User scored 20 points if the answer is correct on first turn
	 if((Turns ==3) && (validate.equals("Correct"))){
		textToast("Well Done, You have scored 20 Points");
	}
	//User scored 15 points if the answer is correct on second turn
	else if((Turns ==2) && (validate.equals("Correct"))){
		textToast("Well Done, You have scored 15 Points");		
	}
	//User scored 10 points if the answer is correct on first turn
	else if((Turns ==1) && (validate.equals("Correct"))){
		textToast("Well Done, You have scored 10 Points");
		
	}
	 
	//User gets third answer wrong
	else if(Turns==3){
			textToast("Wrong Answer, You have 2 more turns");
		}
	 //User gets second answer wrong
	else if(Turns==2){ 
			textToast("Wrong Answer, You have 1 more turn");
		}
	 //User gets third answer wrong	
	else if(Turns==1){
			textToast("Unlucky No More Turns, You lost 10 points");
		}
	}
	
	/*
	 * This  method allows the toast to be displayed for the user. 
	 */
  
	public void textToast(String textToDisplay) {
		Context context = getApplicationContext();
		CharSequence text = textToDisplay;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 100, 100);
		toast.show();
	}
	

}