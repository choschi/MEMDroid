package com.choschi.memdroid;

import com.choschi.memdroid.webservice.Client;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MEMDroidActivity extends Activity {
    /** Called when the activity is first created. */
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView view = (TextView) findViewById(R.id.textview);
        Client client = Client.getInstance();
        client.setConsole (view);
        client.login("webservice", "webService1");
    }
}
