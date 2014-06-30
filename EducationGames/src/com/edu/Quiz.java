package com.edu;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/** 
 * This is the Quiz class where the user can answer the questions,
 * get feedback and will know whether they have lost or won the points. 
 * The score and questions are retrieved from the global objects using
 * QuizApp where the objects are initialised using the QuizMain class
 * the score is then sent back into QuizApp. 
 * 
 * @author Rajin Madahar
 **/

public class Quiz extends Activity{
  
	public RadioButton radioButton;
	public RadioGroup radioGroup;
	public TextView quizQuestion;
	public TextView Answer;
	public TextView Feedback;
	public static String answer;
	public int questNo=0;
	public int pointsBeingRisked;
	public boolean checked=false;
	public boolean flag=true;	
	QuizDataBase db;
	QuizApp app;
	Cursor c1;
	Cursor c2;
	Cursor c3;
	MediaPlayer mp; 
	
  
	  /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
       
        //initialises the object to the application of QuizApp activity 
        app = (QuizApp)getApplication();
        //calls the method for the layout in the game
        setupLayout();
        //calls the method to get the database
    	prepQuizDBHelper();
    	//gets the chosen question
        questNo = getIntent().getIntExtra("questionNum", 0);
        //gets the points being risked 
        pointsBeingRisked = getIntent().getIntExtra("pointsBeingRisked", 25);
        //initialises the object to the TextView
        quizQuestion = (TextView) findViewById(R.id.Question);       
        //calls display question method
        displayQuestion();     
        //sets answer TextView to Answer
        Answer.setText("Answer");
}

	/**
	 * This  method sets up the layout for the quiz
	 */
	private void setupLayout() {
		//Stores feedback button from XML into object and sets layout
        Button btnNext = (Button) findViewById(R.id.btnFeedB);
        btnNext.setOnClickListener(btnFeedB_Listener);
        
        ////Stores back button from XML into object and sets layout
        Button btnConf = (Button) findViewById(R.id.btnBack);
        btnConf.setOnClickListener(btnBack_Listener);   
              
        //Initialises object into radio group
    	radioGroup = (RadioGroup) findViewById(R.id.rdbGp1);  
    	
    	//Initialises object into TextView
    	Answer = (TextView) findViewById(R.id.AnswerState);
    	
    	//Initialises object into TextView
    	Feedback = (TextView) findViewById(R.id.feedback);
	}
    

	/**
	 * OnClick method will provide feedback for the user, validation was added to 
	 * stop the student getting feedback before answering. 
	 * */
    private View.OnClickListener btnFeedB_Listener= new View.OnClickListener() 
    {
		@Override
		public void onClick(View v) 
		{
			//if the TextView is still in state Answer show toast
			   if (Answer.getText().equals("Answer")){
					  textToast("Choose an answer first!");
					  //Use the MediaPlayer class to play warning.mp3
					  mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
		               mp.start(); 
			   }
			   else{
				   //show answer
				   String correctAnswer;
				   //sets the correct answer from the database into Cursor object
				   c3 = db.getCorrAnswerForThisQuestion(questNo);
				   //stores the Cursor object into String object
			       correctAnswer = c3.getString(0);	
			       //Set the TextView text to show the correct answer
			       Feedback.setText("Feedback: " + correctAnswer);
			       //When the button is pressed play the mp3
			       mp = MediaPlayer.create(getApplicationContext(), R.raw.normalbutton); 
	               mp.start(); 
			   }
		}		
	};
	
   
	/**
	 *This method allows the user to go back to the main quiz 
	 */
	private View.OnClickListener btnBack_Listener= new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//Calls the QuizMain class activity with Intent
			Intent StartGameIntent = new Intent(Quiz.this,QuizMain.class);
	 		startActivity(StartGameIntent);
	 		//Plays the mp3 when user clicks back button
	 		 mp = MediaPlayer.create(getApplicationContext(), R.raw.normalbutton); 
             mp.start(); 
		}
	};	
	
	
	/**
	 * This method displays the question to the user and also 
	 * adds the answers. This method also calls a method which 
	 * checks if the answer is correct. 
	 */
	private void displayQuestion()
	{
		//stores question number from the database into Cursor object
		c1=db.getQuiz_Content(questNo);		
		//Stores answers available for the question number into cursor
		c2 =db.getAllAnswersForThisQuestion(questNo);
		//sets the TextView with the question
		quizQuestion.setText(c1.getString(0));
		//loops the content of the cursor object which is the answers
		for (int i=0;i<=3;i++){
			radioButton = new RadioButton(this);
			radioButton.setText(c2.getString(0));//sets the buttons
			radioButton.setId(i); //sets answers in the buttons	
			radioButton.setOnClickListener(new OnClickListener() 
			{	
				@Override
				public void onClick(View v) 
				{
					//new rb object created in radio button view
					RadioButton rb = (RadioButton)v;
					//Checks answer 
					isThisAnswerCorrect(rb.getText().toString());
					//when the selected answer has been made disable the buttons
					disableRadioButtons();	
				}
			});
			c2.moveToNext();//sets answers in place
			radioGroup.addView(radioButton);//sets view of radio buttons			
		}		
	}
	
	/**
	 * This method checks if the answer chosen is correct
	 * @param thisAnswer
	 */
	public void isThisAnswerCorrect(String thisAnswer) 
	{
   //setting a default value in case both the following statements fail
       answer = "incorrect";
       String correctAnswer;
       //gets the correct answer from the database and stores in cursor object
       c3 = db.getCorrAnswerForThisQuestion(questNo);
       //stores correct answer into String object
       correctAnswer = c3.getString(0);
       //logs errors if there is an exception
       Log.e("corrAns[questNo]",correctAnswer);
       //if the correct answer is equal to the answer entered and flag is true
	  if (correctAnswer.equals(thisAnswer.trim()) && flag==true)
	  {
		  //change state of String
		  answer = "correct";
		  flag=false;
		  checked = true;
		  //allows the score to be updated by setting the score in QuizApp 
		  app.setScore(app.getScore() + pointsBeingRisked);
		  //Shows the points being added
		  textToast("Well Done, you won " + pointsBeingRisked + " points!");
		  mp = MediaPlayer.create(getApplicationContext(), R.raw.correct); 
          mp.start(); 
	  }
	  else 
	  {
		  //the answer is incorrect set conditions 
		  answer = "incorrect";
		  flag=true;
		  //if checked is false
		  if(checked == false)
		  {
			  app.setScore(app.getScore() - pointsBeingRisked);//take the points away
			  textToast("Oops, you lost " + pointsBeingRisked + " points.");
			   mp = MediaPlayer.create(getApplicationContext(), R.raw.incorrect); 
               mp.start(); 
		  }
	  }
	  //Set the state of the TextView
		Answer.setText("Answer: " + (answer));
		Log.e("Answer:",(answer));
	}
	
	/**
	 * This  method disables the radio button once it has been clicked. 
	 */
	protected void disableRadioButtons() 
	{
		//loops the group of radio buttons
		for (int i = 0; i < radioGroup.getChildCount(); i++) 
		{
			//Gets the radio buttons within the loop
			RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
			//sets them to false 
			rb.setEnabled(false);
		}		
	}

	/**
	 * This method gets the database 
	 */
    private void prepQuizDBHelper()
    {
    	 db = new  QuizDataBase(this);
	        
        try {
        		db.createDataBase();
        	} 
        	catch (IOException ioe) 
        	{
        		throw new Error("Unable to create database");
        	}	    

			try 
			{
				db.openDataBase();
			}
			catch(SQLException sqle)
			{
				throw sqle;
			}    	
    }
    
    /**
     * This method allows a toast to be displayed
     * @param textToDisplay
     */
	public void textToast(String textToDisplay) 
	{
		Context context = getApplicationContext();
		CharSequence text = textToDisplay;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 50);
		toast.show();
	}
}