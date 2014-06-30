package com.edu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/** 
 * This is the database class which extends the SQLiteOpenHelper
 * class from the android.database.sqlite package. 
 * which queries the SQLite database
 * The Quiz class and Quiz main use this class to set the questions
 * and answers.
 * 
 * @author Rajin Madahar
 **/




public class QuizDataBase extends SQLiteOpenHelper{
	 
	
    //The Android's default system path of application database.
    private static String DB_PATH = "/data/data/com.edu/databases/";
    private static String DB_NAME = "quiz";
    private SQLiteDatabase myDataBase; 
    private SQLiteDatabase myData; 
    private final Context myContext;
 
    
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public QuizDataBase(Context context) {
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
    }	
    


    /**
      * Creates an empty database on the system and rewrites it with your own database.
      * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
    	if(dbExist){
    		//do nothing - database already exist
    	}else{	
    		CopyFiles();
    	}
    }
    
    
    /**
     * Copies files into my own database
     * */
    private void CopyFiles()
    {
    	try
        { 
    	   InputStream is = myContext.getAssets().open(DB_NAME); 
     	   File outfile = new File(DB_PATH,DB_NAME);
     	   outfile.getParentFile().mkdirs();
     	   outfile.createNewFile();
     	   
     	  if (is == null)
          throw new RuntimeException("stream is null");
     	  else
     	  {
             FileOutputStream out = new FileOutputStream(outfile);      
     		  byte buf[] = new byte[128];
	            do {
              int numread = is.read(buf);
	                if (numread <= 0)
	                    break;
              out.write(buf, 0, numread);
               } while (true);
	            
	            is.close();
	            out.close();
     	  }
        }
        catch (IOException e)
        {
        	  throw new RuntimeException(e); 
        }
        
    }    
 
    
    
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    	}
 
    	if(checkDB != null){
    		checkDB.close();
    	}
 
    	return checkDB != null ? true : false;
    }
 
 
    
    
    /**
     * This method opens the database
     * */
    public void openDataBase() throws SQLException{
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
 
    /**
     * This method closes the database
     * */
    @Override
	public synchronized void close() {
    	    if(myDataBase != null)
    		    myDataBase.close();
    	    super.close();
	}
    


    //Implemented methods
	@Override
	public void onCreate(SQLiteDatabase db) {
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
 
           

	  /// Gets content
    public Cursor getQuiz_Content(int qzId)     
    {  
    	String myPath = DB_PATH + DB_NAME;
    	myData = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);    	
  
    	Cursor cur;
     	cur=myData.rawQuery("select quiz_text from Quiz where quiz_id='"+qzId+"'",null);
     	cur.moveToFirst();
    	
     	myData.close();
     	return cur;
    };

 
      
    //gets all the answers 
    public Cursor getAllAnswersForThisQuestion(int quizid)
    {
  	  String myPath = DB_PATH + DB_NAME;
  	  myData = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
  	  
  	  Cursor cur;
  	  cur = myData.rawQuery("select answers from answer where quiz_id='"+quizid+"'", null);
  	  cur.moveToFirst();
  	  myData.close();
  	  return cur;
    }
      
     
     //gets the correct answer
      public Cursor getCorrAnswerForThisQuestion(int QID)
      {
    	  String myPath = DB_PATH + DB_NAME;
    	  myData = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	  Cursor cur;
    	  cur = myData.rawQuery("select correct_answer from quiz where Quiz_ID =" + QID, null);
    	  cur.moveToFirst();
    	  myData.close();
    	  return cur;  
      }
      

      //Gets number of questions
      public Cursor getNumberOfQuestions()
      {
    	  String myPath = DB_PATH + DB_NAME;
    	  myData = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	  Cursor cur;
    	  cur = myData.rawQuery("select count(Quiz_id) from quiz", null);
    	  cur.moveToFirst();
    	  myData.close();
    	  return cur;  
      }
      
}