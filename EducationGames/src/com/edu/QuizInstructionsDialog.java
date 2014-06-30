package com.edu;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;


/** 
 * This class allows the instructions dialog to appear 
 * and implements the OnClickListener
 * 
 * @author Rajin Madahar
 **/

public class QuizInstructionsDialog implements OnClickListener {
private Dialog dialog;

public QuizInstructionsDialog(Dialog dialog) {
	this.dialog = dialog;
}

//When the button is clicked dismiss the instructions
@Override
public void onClick(View v) {
	dialog.dismiss();
}
}