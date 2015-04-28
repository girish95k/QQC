package com.pesit.qqc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Announcements extends ActionBarActivity {

	// List view
	String quizName="";
	String quizDate="";
	String objectId;
	int i=0;
	List<ParseObject> temp;
    private ListView lv;
    private ProgressDialog pDialog;
    
    String names1[], dates1[];
     
    // Listview Adapter
    ArrayAdapter<String> adapter;
     
    // Search EditText
    EditText inputSearch;
    
    String products[]=null;
     
     
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        
        //setTitle("QQC");
        //getActionBar().setIcon(R.drawable.my_icon);
        
        lv = (ListView) findViewById(R.id.list_view);
        
        String[] gg = {"Please wait..."};
		
        adapter = new ArrayAdapter<String>(Announcements.this, R.layout.list_item2, R.id.firstLine, gg);
		lv.setAdapter(adapter);
		 
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Announcements");
        //query.whereEqualTo("playerName", "Dan Stemkoski");
        query.orderByDescending("createdAt");
        query.setLimit(15);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    
                    temp = scoreList;
                    for(ParseObject object : temp)
                    {
                    	
                    	quizName += object.getString("Announcement")+":";
                    	
                    	System.out.println(object.getString("Announcement"));
                    	
                    	
                    	quizDate += " "+":";
                    	
                    	
                    }
                    
                    System.out.println(quizName);
                    quizName = quizName.substring(0, quizName.length()-1);
                    String names[]=quizName.split(":");
                    
                    names1 = names;
                    
                    quizDate = quizDate.substring(0, quizDate.length()-1);
                    String dates[]=quizDate.split(":");
                    
                    dates1 = dates;
                    
                    adapter = new ArrayAdapter<String>(Announcements.this, R.layout.list_item2, R.id.firstLine, names );
            		lv.setAdapter(adapter);
                    
                    /*
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.list_item, R.id.firstLine, names);
            		lv.setAdapter(adapter);
            		
            		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ListActivity.this, R.layout.list_item, R.id.secondLine, dates);
            		lv.setAdapter(adapter);
            		
            		lv.setOnItemClickListener(new OnItemClickListener() {
            			  @Override
            			  public void onItemClick(AdapterView<?> parent, View view,
            			    int position, long id) {
            			    Toast.makeText(getApplicationContext(),
            			      "Click ListItem Number " + position, Toast.LENGTH_LONG)
            			      .show();
            			  }
            			}); 
                    */
                    /*
                 // 1. pass context and data to the custom adapter
                    MyAdapter adapter = new MyAdapter(Announcements.this, generateData());
             */
                    // 2. Get ListView from activity_main.xml
                    ListView listView = (ListView) findViewById(R.id.list_view);
             
                    // 3. setListAdapter
                    //listView.setAdapter(adapter);
                    
                    listView.setOnItemClickListener(new OnItemClickListener() {
          			  @Override
          			  public void onItemClick(AdapterView<?> parent, View view,
          			    int position, long id) {
          			    //Toast.makeText(getApplicationContext(),
          			    //  "Click ListItem Number " + position, Toast.LENGTH_LONG)
          			    //  .show();
          				  System.out.println(position);
          				  i=0;
          				for(ParseObject object : temp)
          				{
          					if(i==position)
          					{/*
          						System.out.println(i);
          						Intent i = new Intent(Announcements.this, QuizActivity.class);   
                  				String keyIdentifer  = null;
                  				i.putExtra("NAME", object.getString("ID"));
                  				//i.putExtra("NAME", "wow");
                  				startActivity(i); 
                  				System.out.println("This one: "+object.getString("Details")+ " " + object.getString("ID"));
                  				//break;
                  				 */
          					}
          					i++;
          					//System.out.println(object.getString("Details"));
          				}
          				/*
          				Intent i = new Intent(ListActivity.this, QuizActivity.class);   
          				String keyIdentifer  = null;
          				i.putExtra("NAME", "faget");
          				startActivity(i);
          				*/
          			  }
          			}); 
                    
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        
    }    

	
	
	private class InflateItems extends AsyncTask<String, String, String>{

		
		
		@Override
		protected void onPostExecute(String result) {
			//System.out.println(result);
			//String[] app = result.split(":");
			//System.out.println(app[1]);
			String[] app = {result , "adf ", "sdf"};
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(Announcements.this, R.layout.list_item2, R.id.firstLine, app);
			lv.setAdapter(adapter);
			//adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.list_item, R.id.firstLine, app);
			//lv.setAdapter(adapter);
			
			//adapter = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1, new ArrayList<String>());
			 //adapter.clear();
			 //adapter.addAll(app);
		}

		@Override
		protected void onPreExecute() {
			System.out.println("Chill Madi");
		}

		@Override
		protected String doInBackground(String... params) {
			
			
			return "";
		}
		
	}
	
	private ArrayList<Item> generateData(){
        ArrayList<Item> items = new ArrayList<Item>();
        //items.add(new Item("Item 1","First Item on the list"));
        //items.add(new Item("Item 2","Second Item on the list"));
        //items.add(new Item("Item 3","Third Item on the list"));
        
        for(int i=0; i<names1.length; i++)
        {
        	items.add(new Item(names1[i], dates1[i]));
        }
 
        return items;
    }
 
}
