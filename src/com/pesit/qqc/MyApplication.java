package com.pesit.qqc;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	
	@Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "ElJH8fiDw1eSm22D0gIbd8KAMbZjBQXttTbEb1Xu", "zqCptvasMVnKFoUwMi3v6D9DvcSPlybv1HAu06Db");
        // Also in this method, specify a default Activity to handle push notifications
        //PushService.setDefaultPushCallback(this, MainActivity.class);
        
        ParsePush.subscribeInBackground("", new SaveCallback() {
        	  @Override
        	  public void done(ParseException e) {
        	    if (e != null) {
        	      Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
        	    } else {
        	      Log.e("com.parse.push", "failed to subscribe for push", e);
        	    }
        	  }
        	});
    }

}
