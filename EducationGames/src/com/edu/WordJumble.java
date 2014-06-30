package com.edu;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class jumbles the word the user entered in the EditText.
 * 
 * @author Rajin
 *
 */

public class WordJumble {

	public static String wordJ;

	/**
	 * This method jumbles the word entered and sets the jumbled wored in the
	 * textview.
	 * 
	 * @param word
	 */
	public static void jumbleWord(String word) {
		// String object is equal to string in edit text
		wordJ = WordJumbleMain.wordE.getText().toString();
		// Store string into array
		ArrayList<Character> wordJ = new ArrayList<Character>();
		// loop the index of the string
		for (int i = 0; i < WordJumbleMain.wordE.length(); i++) {
			wordJ.add(word.charAt(i));// add the word
		}
		Collections.shuffle(wordJ);// shuffle the word
		// set the jumbled word into textView
		WordJumbleMain.jumble.setText((wordJ.toString()));
		WordJumbleMain.ConfirmJumble.setClickable(false);
	}
}
