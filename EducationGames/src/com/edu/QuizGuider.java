package com.edu;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class QuizGuider extends Activity {
	
	MediaPlayer mp; 
	
	 @Override
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.teacher);
	
	        Button link1Btn = (Button) findViewById(R.id.skip);
			link1Btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					//StartGameIntent new Intent object stores a new Intent and starts activity
					Intent StartGameIntent = new Intent(QuizGuider.this, QuizMain.class);
					startActivity(StartGameIntent);
					
					//play media
					 mp = MediaPlayer.create(getApplicationContext(), R.raw.normalbutton); 
		               mp.start(); 

				}
			});
}
}
