package com.edu;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * This  class calls the webview for the revision notes. 
 * This class is called from the QuizMain activity 
 *
 * @author Rajin
 *
 */
public class QuizWeb extends Activity {
	
	      public WebView webview;
	      
		  /** Called when the activity is first created. */
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.webview1);
		        
		        //stores the WebView class into object
		        webview = new WebView(this);
		        //sets the view of the WebView class
		        setContentView(webview);
		        //load URL
		        webview.loadUrl("http://192.168.10.1/EducationGamesApp/Revision.jsp");

		        }
		    }
		
	