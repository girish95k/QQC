package com.pesit.qqc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainActivity extends ActionBarActivity {
	
	/** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setTitle("");
        /*
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("UpcomingQuizzes");
        //query.whereEqualTo("playerEmail", "dstemkoski@example.com");
        query.orderByDescending("createdAt");
        query.setLimit(10);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
          public void done(ParseObject object, ParseException e) {
        	  
            if (object == null) {
              Log.d("score", "The getFirst request failed.");
            } else {
              Log.d("score", "Retrieved the object.");
            }
            
        	  tv.setText(object.getString("Details"));
        	  
        	  
          }
        });
        */
        
        /*
        Button button;
        button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Intent i = new Intent(getApplicationContext(),ListActivity.class);
                        startActivity(i);
                    }
                });
                
                */
        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainActivity.this, ListActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
