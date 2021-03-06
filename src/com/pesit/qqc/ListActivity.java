package com.pesit.qqc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ListActivity extends ActionBarActivity {

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
        setContentView(R.layout.activity_list);
        
        setTitle("QQC");
        //getActionBar().setIcon(R.drawable.my_icon);
        
        lv = (ListView) findViewById(R.id.list_view);
        
        String[] gg = {" "};
		adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.list_item, R.id.firstLine, gg );
		lv.setAdapter(adapter);
		
		 
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UpcomingQuizzes");
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
                    	
                    	quizName += object.getString("Details")+":";
                    	
                    	System.out.println(object.getString("Details"));
                    	
                    	
                    	quizDate += object.getString("Date")+":";
                    	
                    	System.out.println(object.getString("Date"));
                    	
                    }
                    
                    System.out.println(quizName);
                    quizName = quizName.substring(0, quizName.length()-1);
                    String names[]=quizName.split(":");
                    
                    names1 = names;
                    
                    quizDate = quizDate.substring(0, quizDate.length()-1);
                    String dates[]=quizDate.split(":");
                    
                    dates1 = dates;
                    
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
                    
                 // 1. pass context and data to the custom adapter
                    MyAdapter adapter = new MyAdapter(ListActivity.this, generateData());
                    
                    TextView t = (TextView)findViewById(R.id.t);
                    
                    t.setText("");
             
                    // 2. Get ListView from activity_main.xml
                    ListView listView = (ListView) findViewById(R.id.list_view);
             
                    // 3. setListAdapter
                    listView.setAdapter(adapter);
                    
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
          					{
          						System.out.println(i);
          						Intent i = new Intent(ListActivity.this, QuizActivity.class);   
                  				String keyIdentifer  = null;
                  				i.putExtra("NAME", object.getString("ID"));
                  				//i.putExtra("NAME", "wow");
                  				startActivity(i); 
                  				System.out.println("This one: "+object.getString("Details")+ " " + object.getString("ID"));
                  				//break;
          					}
          					i++;
          					System.out.println(object.getString("Details"));
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
        //System.out.println(hello);
        
        
		
       //new InflateItems().execute();
         
        // Listview Data
        /**String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                                "iPhone 4S", "Samsung Galaxy Note 800",
                                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};
         
        lv = (ListView) findViewById(R.id.list_view);*/
        //inputSearch = (EditText) findViewById(R.id.inputSearch);
    
        // Adding items to listview
        /**adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.firstLine, products);
        lv.setAdapter(adapter);
         */
        /**
         * Enabling Search Filter
         * */
        /*
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                ListActivity.this.adapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }
        });*/
    }    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_announcements) {
			
			Intent i = new Intent(ListActivity.this, Announcements.class);   
				//String keyIdentifer  = null;
				//i.putExtra("NAME", "u0K477ieEA");
				startActivity(i);
				
			return true;
		}
		if (id == R.id.action_contact) {
			
			Intent i = new Intent(ListActivity.this, Contact.class);   
				//String keyIdentifer  = null;
				//i.putExtra("NAME", "u0K477ieEA");
				startActivity(i);
				
			return true;
		}
		if (id == R.id.action_about) {
			
			Intent i = new Intent(ListActivity.this, About.class);   
				//String keyIdentifer  = null;
				//i.putExtra("NAME", "u0K477ieEA");
				startActivity(i);
				
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class InflateItems extends AsyncTask<String, String, String>{

		
		
		@Override
		protected void onPostExecute(String result) {
			//System.out.println(result);
			//String[] app = result.split(":");
			//System.out.println(app[1]);
			String[] app = {result , "adf ", "sdf"};
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this, R.layout.list_item, R.id.firstLine, app);
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
	//what you see? looks sexy on my phone
	//but what about kutti screens?
	//check out the graphical view on the xml page
	//oh wait
	//you didn't modify the second thingy :P
	//do that also. twhahforgot lo what is second?
	//check out adithya krishna's photo in that xml's graphical 
	//done
	//looks really awesome.
	//now what if i want to add a third one?
	//just alignbottom of parent?
	//shove whole thing in scrollview. Just copy paste the linearlayout for each image-text pair. one
	//thats it
	//wait, watch me copy paste
	//running
	//dude it looks beautiful!
	//acknowledge?
	//pls?
	//;_;
}
