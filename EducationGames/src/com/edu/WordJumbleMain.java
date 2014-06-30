package com.edu;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * This class is the Main class for the jumble word game. This class calls
 * methods from other classes in order to function correctly in the game.
 * 
 * @author Rajin
 * 
 */

public class WordJumbleMain extends Activity {

	
	static String Player1 = "Player 1";
	static String Player2 = "Player 2";
	static String getWordE;
	static EditText wordE;
    static EditText wordAnsE;
    static TextView state1;
    static TextView state2;
    static TextView jumble;
    static TextView ScoreP1;
	static TextView ScoreP2;
	static TextView correctAnswer;
	static ImageView ImageTurn1;
	static ImageView ImageTurn2;
	static ImageView ImageTurn3;
	static Button ConfirmAnswer;
	static Button ConfirmJumble;
	static Button WordServe;
	WebView MyWebView;
	MediaPlayer mp; 
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wordjumblemain);

		layout();

		
		
		/** This dialog sets the instructions for the game */
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.wordjumbinstructions);
		dialog.setTitle("Instructions");

		//Allows user to click ok and go back to Quiz main from instructions
		Button buttonOK = (Button) dialog.findViewById(R.id.buttonOK);
		//Uses listener within Quiz Instructions Dialog class
		buttonOK.setOnClickListener(new QuizInstructionsDialog(dialog));
	
		
		/*
		 * Onclick method for the confirm button when the user enters a word
		 */
		ConfirmJumble = (Button) findViewById(R.id.selected);
		ConfirmJumble.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			String str = wordE.getText().toString();
				// if the user has not entered a word
				if ((wordE.length() == 0) || (str.matches("[0-9]*"))) {
					textToast("Enter a word");
				}
				// if player 1 has entered a word
				else if (state1.getText().equals(
						Player1 + " please enter a word")) {
					WordJumble.jumbleWord(wordE.getText().toString());
					WordJumbleStatus.changeStateOnPlayerEnter();
					//Do not allow next player to see words
					WordServe.setClickable(false);
					getWordE = (wordE.getText().toString());
					wordE.setText("");
					correctAnswer.setText("Answer");
					textToast("Pass the phone to player 2");
					 mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
		               mp.start(); 
				}
				// if player 2 has entered a word
				else if (state1.getText().equals(
						Player2 + " please enter a word")) {
					WordJumble.jumbleWord(wordE.getText().toString());
					WordJumbleStatus.changeStateOnPlayerEnter();
					//Do not allow next player to see words
					WordServe.setClickable(false);
					getWordE = (wordE.getText().toString());
					wordE.setText("");
					correctAnswer.setText("Answer");
					textToast("Pass the phone to player 1");
					 mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
		               mp.start(); 
				}
			}
		});

		
		/*
		 * Onclick method for word definition
		 */
		Button definition = (Button) findViewById(R.id.definition);
		definition.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MyWebView = (WebView) findViewById(R.id.webkit);        
				MyWebView.getSettings().setJavaScriptEnabled(true);
				MyWebView.loadUrl("http://wordnetweb.princeton.edu/perl/webwn");
				 mp = MediaPlayer.create(getApplicationContext(), R.raw.blueface); 
	               mp.start(); 
				
			}
		});

		
		/*
		 * Onclick method for fetching words from servlet
		 */
		WordServe = (Button) findViewById(R.id.WordServ);
		WordServe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebView MyWebView = (WebView) findViewById(R.id.webkit);        
				MyWebView.getSettings().setJavaScriptEnabled(true);
				MyWebView.loadUrl("http://192.168.10.1/EducationGamesApp/WordServ");
				 mp = MediaPlayer.create(getApplicationContext(), R.raw.blueface); 
	               mp.start(); 
			}
		});
		
		
		
		/*
		 * Onclick method for resetting the game
		 */
		Button resetGame = (Button) findViewById(R.id.reset);
		resetGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WordJumbleStatus.resetGame();
				 mp = MediaPlayer.create(getApplicationContext(), R.raw.reset); 
	               mp.start(); 
			}
		});


		
		
		

		/*
		 * Onclick method for clearing the web
		 */
		Button clearWeb= (Button) findViewById(R.id.ClearWeb);
		clearWeb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebView MyWebView = (WebView) findViewById(R.id.webkit);        
				MyWebView.getSettings().setJavaScriptEnabled(true);
				MyWebView.loadUrl("about:blank");
				mp = MediaPlayer.create(getApplicationContext(), R.raw.jumbleconfirm); 
	               mp.start(); 
			}
		});

		
		
		
		
		
		
		
		/*
		 * Onclick method which checks the answer entered from the user
		 */
		ConfirmAnswer = (Button) findViewById(R.id.answerSelected);
		ConfirmAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// if the word entered in answer is 0
				if ((getWordE.length() == 0) || (state2.getText().equals("Player 2"))) {
					textToast("Enter a word");
				}
				else{
				WordJumbleCheck.getAnswer();
				WordJumbleStatus.changeStateOnPlayerAnswer();
				WordJumbleScore.setScore();
				WordJumbleTurns.amountOfTurns();
				WordJumbleTurns.endOfTurns();
				showToastOnTurns();
				endGame();
				}
			}
		});
	
	
		/*
		 * Onclick method for showing the instructions
		 */
		Button Instructions = (Button) findViewById(R.id.mag);
         Instructions.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.show();// calls the dialog method for the instructions
				 mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
	               mp.start(); 
			}
		});
	}
	

	/*
	 * This method sets the colour of the buttons and lays out the structure for 
	 * the jumble game. 
	 */
	public void layout() {
		state1 = (TextView) findViewById(R.id.status);
		state1.setText(Player1 + " please enter a word");
		state2 = (TextView) findViewById(R.id.Turn2);
		wordE = (EditText) findViewById(R.id.entry);
		wordAnsE = (EditText) findViewById(R.id.answerEntry);
		jumble = (TextView) findViewById(R.id.jumble);
		correctAnswer = (TextView) findViewById(R.id.chosenAnswer);
		ScoreP1 = (TextView) findViewById(R.id.Score1);
		ScoreP2 = (TextView) findViewById(R.id.Score2);
		ImageTurn1 = (ImageView) findViewById(R.id.turn1);
		ImageTurn2 = (ImageView) findViewById(R.id.turn2);
		ImageTurn3 = (ImageView) findViewById(R.id.turn3);
	}

	
	/*
	 * This method ends the game if the users score reaches over 100
	 */
	public void endGame() {
		if (WordJumbleScore.score1 == 100) {
			textToast("Game Over, Player 1 wins " + WordJumbleScore.score1 + "Points!");
			ConfirmAnswer.setClickable(false);
			ConfirmJumble.setClickable(false);
			mp = MediaPlayer.create(getApplicationContext(), R.raw.applause); 
            mp.start();
			
		}

		else if (WordJumbleScore.score2 == 100) {
			textToast("Game Over, Player 2 wins " + WordJumbleScore.score2 + "Points!");
			ConfirmAnswer.setClickable(false);
			ConfirmJumble.setClickable(false);
			mp = MediaPlayer.create(getApplicationContext(), R.raw.applause); 
            mp.start();
		}
	}

	
	
	
	/*
	 * This method shows the toast when the user has used up all the turns
	 */
	public void showToastOnTurns() {
		// if the turns are 0
		if ((WordJumbleTurns.Turns == 0)
				&& (state1.getText().equals(Player1 + " please enter a word"))) {
			textToast("Unlucky Player 1, You have run out of turns");
			 mp = MediaPlayer.create(getApplicationContext(), R.raw.fail); 
             mp.start();
		}

		else if ((WordJumbleTurns.Turns == 0)
				&& (state1.getText().equals(Player2 + " please enter a word"))) {
			textToast("Unlucky Player 2, You have run out of turns");
			 mp = MediaPlayer.create(getApplicationContext(), R.raw.fail); 
             mp.start();
		}

		else if (WordJumbleTurns.Turns == 2) {
			textToast("2 More turns");
			 mp = MediaPlayer.create(getApplicationContext(), R.raw.fail); 
             mp.start();
		} else if (WordJumbleTurns.Turns == 1) {
			textToast("1 More turn");
			mp = MediaPlayer.create(getApplicationContext(), R.raw.fail); 
            mp.start();
		}

		else if (getWordE.equalsIgnoreCase(WordJumbleCheck.wordChk)) {
			// set Toast to show user Correct
			textToast("Well Done, Correct Answer");
			mp = MediaPlayer.create(getApplicationContext(), R.raw.correct); 
            mp.start();
		}
		
		

	}

	
	/*
	 * This  method allows a toast to be displayed
	 */
	public void textToast(String textToDisplay) {
		Context context = getApplicationContext();
		CharSequence text = textToDisplay;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 50, 50);
		toast.show();
	}

}