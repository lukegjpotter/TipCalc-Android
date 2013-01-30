package net.lukegjpotter.app.tipcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author Luke Potter
 * 19/January/2013
 * 
 * This it the splash screen activity for the Tip Calc App
 */
public class Splashscreen extends Activity {

	// Dictates how long the splash screen appears for
	protected static final long timeout = 2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splashscreen);
		
		Thread timer = new Thread() {
			
			public void run() {
				
				try {
					
					sleep(timeout);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} finally {
					
					Intent openNextActivity = new Intent("net.lukegjpotter.app.tipcalc.TIPCALC");
					startActivity(openNextActivity);
				}
			} // End run method
		}; // End Thread definition
		
		timer.start();
	}

	@Override
	protected void onPause() {
		
		super.onPause();
		
		finish();
	}
}
