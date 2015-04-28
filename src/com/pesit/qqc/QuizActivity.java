package com.pesit.qqc;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class QuizActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz2);
		
		//Button button;
		setTitle("");
		Intent iin= getIntent();
        Bundle b = iin.getExtras();
        final TextView tvName = (TextView)findViewById(R.id.textView1);
        final TextView tvDate = (TextView)findViewById(R.id.textView2);
        //final TextView tvPrize = (TextView)findViewById(R.id.textView3);

        if(b!=null)
        {
            String j =(String) b.get("NAME");
            //tv.setText(j);
            System.out.println("Received: "+ j);
            
            
            ParseQuery<ParseObject> query = ParseQuery.getQuery("UpcomingQuizzes");
            //query.whereEqualTo("playerName", "Dan Stemkoski");
            query.whereEqualTo("ID", j);
            //query.orderByDescending("createdAt");
            query.setLimit(1);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> scoreList, ParseException e) {
                    if (e == null) {
                        Log.d("score", "Retrieved " + scoreList.size() + " scores");
                        
                        for(ParseObject object : scoreList)
                        {
                        	System.out.println("This: "+object.getString("Details")+ " " + object.getString("objectId"));
                        	tvName.setText(object.getString("Details")+"\n");
                        	
                        	String prize="0";
                        	Number n = 0;
                        	if(object.getNumber("PrizeMoney")==n)
                        	       	prize = "Not known";
                        	else
                        		prize = object.getNumber("PrizeMoney").toString();
                        	tvDate.setText("Date: " + object.getString("Date") + "\n" + "Prize Money: " + prize + "\n" + "Quiz Masters: " + object.getString("QuizMaster") + "\n" + "Venue: " + object.getString("Venue") + "\n" + "Topic: " + object.getString("Topic") + "\nAdditional Details: " + object.getString("AdditionalDetails") + "\nWebsite: " + object.getString("Website"));
                        	//tvPrize.setText("Prize: Rs "+object.getNumber("PrizeMoney"));
                        	setTitle(object.getString("Details"));
                        	
                        }   
                        
                        LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayout1);
                        
                        
                        
                        ll.setOnClickListener(new OnClickListener() {      
                            @SuppressWarnings("deprecation")
							@Override
                            public void onClick(View v) {
                                //Intent picture_intent = new Intent(CurrentActivity.this,PictureActivity.class);
                            //startActivity(picture_intent );     
                            	String text = tvName.getText().toString() + tvDate.getText().toString();


                            	int sdk = android.os.Build.VERSION.SDK_INT;
                                if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                                    @SuppressWarnings("deprecation")
									android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    clipboard.setText(text);
                                } else {
                                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); 
                                    android.content.ClipData clip = android.content.ClipData.newPlainText("text label",text);
                                    clipboard.setPrimaryClip(clip);
                                }
                                
                                Toast.makeText(getApplicationContext(),
                          			      "Copied to clipboard.", Toast.LENGTH_LONG)
                          			      .show();
                            }
                           });
                        
                        
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
            
            
            
        }
        
        
        
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/
}
