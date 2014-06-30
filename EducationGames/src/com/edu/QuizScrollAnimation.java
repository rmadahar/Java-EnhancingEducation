package com.edu;

import android.view.animation.Animation;

/** 
 * This is the QuizScrollAnimation class, which
 * extends the Animation API. This class updates
 * the current scroll. 
 *  
 * @author Rajin Madahar
 **/


public class QuizScrollAnimation extends Animation {
	
	public float mFrom;
	public float mTo;
	private float mCurrent;
	
	
	public void startScrolling(float from, float to) {
		mFrom = from;
		mTo = to;
		startNow();
	}

	public float getCurrent() {
		return mCurrent;
	}
	
	public static String get(){
		return QuizScoreSeekBar.Points;

	}
}
