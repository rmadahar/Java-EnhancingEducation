package com.edu;

/**
 * This class provides an introduction to the user
 * and also provides instructions to the game. 
 * 
 * @author Rajin Madahar
 */

import com.edu.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundGuider extends Activity {
	
	MediaPlayer mp;
	
	 @Override
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.soundguider);
	
	        
	        /** This dialog sets the instructions for the game */
			final Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.soundgameinstructions);
			dialog.setTitle("Instructions");

			//Allows user to click ok and go back to Quiz main from instructions
			Button buttonOK = (Button) dialog.findViewById(R.id.buttonOK);
			buttonOK.setOnClickListener(new QuizInstructionsDialog(dialog));
	        
	        
	        /*
	         * This method starts main sound game class onClick
	         */
	        Button link1Btn = (Button) findViewById(R.id.skip);
			link1Btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Intent StartGameIntent = new Intent(SoundGuider.this, SoundChallengeMain.class);
					startActivity(StartGameIntent);
					mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
		               mp.start(); 

				}
			});
			
			/*
			 * This method shows the instructions
			 */
			  Button link2Btn = (Button) findViewById(R.id.mag);
				link2Btn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.show();
						mp = MediaPlayer.create(getApplicationContext(), R.raw.warning); 
			               mp.start(); 

					}
				});


	 
	 
	 
	 }
}
