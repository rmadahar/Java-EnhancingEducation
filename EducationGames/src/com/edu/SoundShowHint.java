package com.edu;

import android.widget.ImageView;

/**
 * 
 * This class holds the conditions of the hint button.
 * Which checks the index of the question and gives the hint for the 
 * question.  
 * 
 * @author Rajin Madahar
 */


public class SoundShowHint {

	/*
	 * This method checks the index of the question and adds the image 
	 * which is a hint to the index. 
	 */
	public static void imageChange() {
	
		int Index = SoundQuestionsAnswers.Index;
		ImageView Image = SoundChallengeMain.Image;
		
		if (Index == 1) {
			Image.setImageResource(R.drawable.answer_hint1);
		} else if (Index == 2) {
			Image.setImageResource(R.drawable.answer_hint2);
		}
		else if (Index == 3) {
			Image.setImageResource(R.drawable.answer_hint3);
		}
		else if (Index == 4) {
			Image.setImageResource(R.drawable.answer_hint4);
		}
		else if (Index == 5) {
			Image.setImageResource(R.drawable.answer_hint5);
		}
		else if (Index == 6) {
			Image.setImageResource(R.drawable.answer_hint6);
		}
		else if (Index == 7) {
			Image.setImageResource(R.drawable.default_hint7);
		}
		else if (Index == 8) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 9) {
			Image.setImageResource(R.drawable.answer_hint9);
		}
		else if (Index == 10) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 11) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 12) {
			Image.setImageResource(R.drawable.answer_hint12);
		}
		else if (Index == 13) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 14) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 15) {
			Image.setImageResource(R.drawable.answer_hint15);
		}
		else if (Index == 16) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 17) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 18) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 19) {
			Image.setImageResource(R.drawable.answer_hint_tf);
		}
		else if (Index == 20) {
			Image.setImageResource(R.drawable.answer_hint9);
		}
		
	
	}
	
	
}
