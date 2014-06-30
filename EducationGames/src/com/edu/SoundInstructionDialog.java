package com.edu;

/** 
 * This class allows the instructions dialog to appear 
 * and implements an OnClickListener
 * 
 * @author Rajin Madahar
 **/


import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

public class SoundInstructionDialog implements OnClickListener {
private Dialog dialog;

public SoundInstructionDialog(Dialog dialog) {
	this.dialog = dialog;
}

//When the button is clicked dismiss the instructions
@Override
public void onClick(View v) {
	dialog.dismiss();
}
}

