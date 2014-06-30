package com.edu;

import android.content.res.Resources;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.StateSet;
import android.view.animation.AnimationUtils;
	
/** 
 * This class is adapted from the SeekBar class used 
 * in the API demo's.
 * This class draws the seekbar for the user to set the points. 
 * 
 * @author Rajin Madahar
 **/

public class QuizScoreSeekBar extends Drawable implements Runnable {
	
	//Used to check the state of what the drawable is in. 
	static final int[] STATE_FOCUS = {android.R.attr.state_focused};
	static final int[] STATE_PRESS = {android.R.attr.state_pressed};		
	private static final long DELAY = 30;
	//This is the text which will be drawn over the drawable
	public static String Points;
	private float PointsWidth;
	private Drawable PointsProgress;
	//Drawing the text within mText
	private Paint PaintScore;
	private Paint OutlineScore;
	//ranges from 0 to 1
	private float PointsXScale;
	private int mDelta;
	//custom animation
	private QuizScrollAnimation mAnimation;
	

	/*
	 * Constructor initialises variables
	 */
	public QuizScoreSeekBar(Resources res, boolean labelOnRight) {
		
		PointsProgress = res.getDrawable(android.R.drawable.progress_horizontal);
		Points = "";
		PaintScore = new Paint(Paint.ANTI_ALIAS_FLAG);
		PaintScore.setTypeface(Typeface.DEFAULT_BOLD);
		PaintScore.setTextSize(16);
		OutlineScore = new Paint(PaintScore);
		OutlineScore.setStyle(Style.STROKE);
		OutlineScore.setStrokeWidth(4);
		OutlineScore.setMaskFilter(new BlurMaskFilter(1, Blur.NORMAL));
		PointsXScale = labelOnRight? 1 : 0;
		mAnimation = new QuizScrollAnimation();
	}

	
	/*
	 * Resizes the PointsProgress when the Drawable is resized
	 * (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#onBoundsChange(android.graphics.Rect)
	 */
	@Override
	protected void onBoundsChange(Rect bounds) {
		//bounds of the drawable assigned - absolute size and location
		PointsProgress.setBounds(bounds);
	}
	
	/*
	 * Local variable active indicates true or false as Drawable is 
	 * stateful - pressed, focused and in normal state
	 * (non-Javadoc)
	 * @see android.graphics.drawable.Drawable#onStateChange(int[])
	 */
	@Override
	protected boolean onStateChange(int[] state) {
		//sets states
		boolean active = StateSet.stateSetMatches(STATE_FOCUS, state) | StateSet.stateSetMatches(STATE_PRESS, state);
		//sets colour
		OutlineScore.setColor(active? 0xffffffff : 0xffbbbbbb);
		PaintScore.setColor(active? 0xff000000 : 0xff606060);
		invalidateSelf();//Drawable callback
		return false;
	}
	

	@Override
	public boolean isStateful() {
		return true;
	}

	//Moves the scroll to the left and right
	@Override
	public boolean onLevelChange(int level) {
		Points = (level / 100) + "";//set the seekbar to 100
		PointsWidth = OutlineScore.measureText(Points);
		if (level < 4000 && mDelta <= 0) {
			mDelta = 1;
			// move to the right
			startScrolling(1);	
		} else
		if (level > 6000 && mDelta >= 0) {
			mDelta = -1;
			// move to the left
			startScrolling(0);
		}
		return PointsProgress.setLevel(level);	
	}
	
	/*
	 *Calls scheduleSelf method to allow the scrolling
	 */
	private void startScrolling(int to) {
		mAnimation.startScrolling(PointsXScale, to);
		scheduleSelf(this, SystemClock.uptimeMillis() + DELAY);
	}

	/*
	 * This method draws the scroll bar
	 */
	@Override
	public void draw(Canvas canvas) {
		PointsProgress.draw(canvas);

		if (mAnimation.hasStarted() && !mAnimation.hasEnded()) {
			// pending animation
			mAnimation.getTransformation(AnimationUtils.currentAnimationTimeMillis(), null);
			PointsXScale = mAnimation.getCurrent();
		}
		Rect bounds = getBounds();
		float x = 6 + PointsXScale * (bounds.width() - PointsWidth - 6 - 6);
		float y = (bounds.height() + PaintScore.getTextSize()) / 2;
		canvas.drawText(Points, x, y, OutlineScore);
		canvas.drawText(Points, x, y, PaintScore);
	}
	
	
	

	@Override
	public void setAlpha(int alpha) {
	}
	@Override
	public void setColorFilter(ColorFilter cf) {
	}
	@Override
	public void run() {	
	}
	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}	
}