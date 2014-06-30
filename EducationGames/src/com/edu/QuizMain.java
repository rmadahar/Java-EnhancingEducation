package com.edu;

import java.io.IOException;





import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/** 
 * This is the main activity class for the quiz
 * The user will set all preferences for the game within this class
 * The preferences which are the score and the question will be used 
 * to store global items which are available through all activities. 
 * Within this class the user can also go to 
 * video tutorials and revision notes. 
 * 
 * @author Rajin Madahar
 **/


public class QuizMain extends Activity {

	public static Cursor c1;
	public static QuizDataBase db;
	public static QuizApp app;
	public static TextView currentScore;
	public static TextView valid;
	public static TextView tv;
	public static TextView ScoreTv;
	AlertDialog alertDialog;
	AlertDialog alertbox;
	MediaPlayer mp; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quizmain);
		quizLayout();
		
		/** This dialog sets the instructions for the game */
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.quizgameinstructions);
		dialog.setTitle("Instructions");

		//Allows user to click ok and go back to Quiz main from instructions
		Button buttonOK = (Button) dialog.findViewById(R.id.buttonOK);
		//Uses listener within Quiz Instructions Dialog class
		buttonOK.setOnClickListener(new QuizInstructionsDialog(dialog));
		
	
		/*
		 * This alert dialog is used to ask the player if they are sure 
		 * they want to go to revision notes. 
		 */
		//sets final so can only be used in this instance
		final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		//creates a new object for the AlertDialog.Builder
		alertDialog = new AlertDialog.Builder(this).create();
		//Sets content for the Alert Dialog
		alertbox.setTitle("Revision Notes");
		alertbox.setMessage("Are you sure?");
		alertbox.setPositiveButton("CONTINUE",
				//creates a new listener OnClick
				new DialogInterface.OnClickListener() {
                  @Override
                  /*
                   *On Click method for the dialog which calls the Quiz Web class 
                   *for the revision notes. 
                   */
					public void onClick(DialogInterface dialog, int which) {
					   	
                	  //Intent will start a new activity from this class into the QuizWeb.class
                	    Intent revisionNotes = new Intent(QuizMain.this, QuizWeb.class);
                	   //Object from Intent is used to start activity 
                	    startActivity(revisionNotes);
                  }  
			});
		//sets the cancel button if the user decides to continue with the game
		alertbox.setNegativeButton("CANCEL",
				//new listener
				new DialogInterface.OnClickListener() {
					@Override
					/*
					 * OnClick method for the cancel button contains no logic 
					 * which will allow the user to cancel the dialog.
					 */
					public void onClick(DialogInterface dialog, int which) {}
		});
		//set the warning image within the dialog box
		alertbox.setIcon(R.drawable.warning);
		
		/*
		 * This alert dialog is for the you tube video which asks the player
		 * if they are sure they want to continue to the video
		 */
		final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);
		alertDialog = new AlertDialog.Builder(this).create();
		alertbox2.setTitle("Video Tutorial");
		alertbox2.setMessage("Are you sure?");
		alertbox2.setPositiveButton("CONTINUE",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
                      //Starts a new activity which puts the video into an Action view. 
						startActivity(new Intent(
								Intent.ACTION_VIEW,
								Uri.parse("http://www.youtube.com/watch?v=XS998HaGk9M")));
					}
				});
		alertbox2.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {}
				});
		alertDialog.setIcon(R.drawable.warning);

		
	
		/*
		 *OnClick method for Blue face button
		 */
		Button btnBlueFace = (Button) findViewById(R.id.Circle);
		btnBlueFace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Method  called from Quiz status to set the button and increment on click
				QuizStatus.setQuestion();
				//Method called from Quiz status to check if the questions have ended then display game over 
				QuizStatus.gameStatus();
				//starts the mp3 for the button sound
				 mp = MediaPlayer.create(getApplicationContext(), R.raw.blueface); 
	               mp.start(); 
			}
		});

		
		/*
		 *Onclick method sets the points for the game
		 */
		Button btnSetPoints = (Button) findViewById(R.id.btnSetPoints);
		btnSetPoints.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//if the user has not yet selected a question prompt user with toast
				if(valid.getText().equals("Press Blue face to receive a question")){
					textToast("Press Blue Face");
					 mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
		               mp.start(); 
					
				}
				else{
				//set the score	
				QuizStatus.setScore();
				
				//play media
				 mp = MediaPlayer.create(getApplicationContext(), R.raw.normalbutton); 
	               mp.start(); 
				
				}
			}
		});

		
		
		/*
		 *OnClick method quits the game and resets the question and score
		 *so player can start again 
		 */
		Button btnQuit = (Button) findViewById(R.id.btnQuit);
		btnQuit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				app.setScore(0);//sets the points to 0
				QuizStatus.rowIndex=1;//sets the question back to 1
			    currentScore.setText("");//clears the score from textview
			    valid.setText("Press Blue face to receive a question");
			  //play quit mp3
				mp = MediaPlayer.create(getApplicationContext(), R.raw.quit); 
	               mp.start(); 
			}
		});
		
		

		/*
		 *OnClick method for revision notes on JSP page
		 *If user clicks cancel goes back to QuizMain
		 */
		Button revision = (Button) findViewById(R.id.books);
		revision.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alertbox.show();// calls the books alert dialog
				 mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
	               mp.start(); 
			}
		});

		
		
		
		/*
		 *OnClick method Shows instructions for the game
		 * 
		 */
		Button link4Btn = (Button) findViewById(R.id.mag);
		link4Btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.show();// calls the dialog method for the instructions
				 mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
	               mp.start(); 
				
			}
		});

		/*
		 * OnClick method for the answer button
		 */
		Button btnAnswer = (Button) findViewById(R.id.btnAnswer);
		btnAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String validate = String.valueOf(ScoreTv.getText());
				if(!(validate.equals(String.valueOf(QuizStatus.pointsBeingRisked)))
						&& !valid.getText().equals("Press Blue face to receive a question")){
					textToast("Set Score first");
				}
				
				//if no question is shown
				else if(valid.getText().equals("Press Blue face to receive a question")){
			    //prompts user with toast to press blue face to receive a question
				textToast("Press Blue Face");
				//plays mp3 warning sound
				mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
	               mp.start(); 	
			}
			else{
				//Start quiz class and store question number and points being risked
				Intent StartGameIntent = new Intent(QuizMain.this, Quiz.class);
				//puts the global variables into the object of intent and continues to the new activity
				StartGameIntent.putExtra("questionNum", app.getCurrentQuestionID());
				StartGameIntent.putExtra("pointsBeingRisked", QuizStatus.pointsBeingRisked);
				startActivity(StartGameIntent);
				//starts the mp3 for the button sound
				mp = MediaPlayer.create(getApplicationContext(), R.raw.normalbutton); 
	               mp.start(); 
			}
		}
		});

		/*
		 *OnClick method Shows the video tutorial 
		 *if user clicks cancel stays in current activity. 
		 * 
		 */
		Button btnVideo = (Button) findViewById(R.id.video);
		btnVideo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// shows the alert when the video button is pressed
				alertbox2.show();
				//play warning mp3
				mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
	               mp.start(); 
			}
		});
		
		
		
		
		
		
}//OnCreate ends
		
	
    /*
     * This  method sets the layout for the game
     */
	public void quizLayout(){
		//textview for setting score
		ScoreTv = (TextView) findViewById(R.id.scoreset);
		ScoreTv.setFocusable(false);
		ScoreTv.setFocusableInTouchMode(false);
		ScoreTv.setClickable(false);
		//stores current application within app
		app = (QuizApp) getApplication();
		//Method gets the database
		prepQuizDBHelper();
		//puts number of questions into cursor
		c1 = db.getNumberOfQuestions();
		// sets number of questions into quiz
		app.setNumberOfQuestionsInQuiz
		(Integer.parseInt(c1.getString(0)));
		//Stores currentscore textview into variable
		currentScore = (TextView) findViewById(R.id.currentScore);
		tv = (TextView) findViewById(R.id.questions);
		//Sets the score from quiz app
		currentScore.setText(String.valueOf(app.getScore()));
		//Sets up seek bar with default points
		setup((SeekBar) findViewById(R.id.seek_bar0), 25);
		//Stores questions textview into valid
		valid = (TextView) findViewById(R.id.questions);
		//Sets text within questions 
		
	}
	
	/** This method sets up the ScoreSeekbar **/
	private void setup(SeekBar seekBar, int v) {
		//Resource object to get the drawables
		Resources res = getResources();
		//Stores seekbar from class into object d
		Drawable d = new QuizScoreSeekBar(res, v < seekBar.getMax() / 2);
		//Define the drawable used to draw the progress bar in progress mode
		seekBar.setProgressDrawable(d);
		//Set the current progress to the specified value.
		seekBar.setProgress(v);
	}
	

	/*
	 * This method allows a toast to be displayed
	 * @param textToDisplay
	 */
	public void textToast(String textToDisplay) 
	{
		//sets an object from the current application context
		Context context = getApplicationContext();
		CharSequence text = textToDisplay;
		int duration = Toast.LENGTH_SHORT;
		//sets context, text and duration of toast
		Toast toast = Toast.makeText(context, text, duration);
		//sets positioning of toast
		toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER, 50, 50);
		toast.show();
	}
	
	
	/** This method gets the database **/
	private void prepQuizDBHelper() {
		//stores database class within object
		db = new QuizDataBase(this);

		try {
			// creates database using method
			db.createDataBase();
			//if there are any problems
		} catch (IOException ioe) {
			//error
			throw new Error("Unable to create database");
		}
		try {
			//Uses the open data base method 
			db.openDataBase();
			//Exception thrown by class
		} catch (SQLException sqle) {
			throw sqle;
		}
	}

	@Override
	protected void onResume() {
	    super.onResume();
	    //now perform any operations on your textview
	    //for example
	    valid.setText("Press Blue face to receive a question");
	}
	
	
	
	@Override
	protected void onStart() {
		//starts parent class
		super.onStart();
		// populateViews();
		prepQuizDBHelper();
	}
}