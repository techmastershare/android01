package com.app.disablewifi;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	    wifi();
    }

    public void wifi() {
    	try {
	    	WifiManager wifi;
	    	wifi=(WifiManager)getSystemService(Context.WIFI_SERVICE); 
//	    	Now the object wifi of WifiManager class is used to get the wifi status:
	    	if(wifi.isWifiEnabled()){
	    		wifi.setWifiEnabled(false);
	    	}else{
	    		wifi.setWifiEnabled(true);
	    	}
		} catch (Exception e) {
			// TODO: handle exception
		}    
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
}
