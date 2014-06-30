package com.edu;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Menu extends Activity {
  
   
   MediaPlayer mp; 
  
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button StartGameButton = (Button)findViewById(R.id.StartQuizGame);
        StartGameButton.setOnClickListener(new OnClickListener() {
        	
        	@Override
			public void onClick(View v) {
        		Intent StartGameIntent = new Intent(Menu.this,QuizGuider.class);
        		startActivity(StartGameIntent);
        		  mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
                  mp.start(); 
        			
        	}
        });
        
        Button CreditsButton = (Button)findViewById(R.id.Credits);
        CreditsButton.setOnClickListener(new OnClickListener() {
        	
        	@Override
			public void onClick(View v) {
        		Intent CreditsIntent= new Intent(Menu.this,WordJumbleMain.class);
        		startActivity(CreditsIntent);
        		mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
                mp.start(); 
        	}
        });
        
        
        Button OptionsButton = (Button)findViewById(R.id.SoundGame);
        OptionsButton.setOnClickListener(new OnClickListener() {
        	
        	@Override
			public void onClick(View v) {
        		Intent OptionsIntent = new Intent(Menu.this,SoundGuider.class);
        		startActivity(OptionsIntent);
        		
        	mp = MediaPlayer.create(getApplicationContext(), R.raw.button_3); 
                mp.start(); 
        	}
        });
        
        
        
    }
        
    
        
        
    
}