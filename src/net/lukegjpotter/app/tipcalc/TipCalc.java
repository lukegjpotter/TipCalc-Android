package net.lukegjpotter.app.tipcalc;

import android.os.Bundle;
import android.app.Activity;
//import android.view.Menu;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TipCalc extends Activity {

	// Constants used when saving/restoring state
	private static final String BILL_TOTAL = "BILL_TOTAL";
	private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";
	
	// UI Properties
	private double currentBillTotal;				// Bill total entered by user
	private int currentCustomPercent;				// Tip percentage set by SeekBar
	private EditText tip10EditText, tot10EditText,	// Tip variables display the tip for the percentage
					 tip15EditText, tot15EditText,	// Tot variables display the total for the percentage
					 tip20EditText, tot20EditText,
					 tipCustomEditText, totCustomEditText,
					 billEditText;					// Accepts user input for the bill total 
	private TextView customTipTextView;				// Displays custom tip percentage
	
	String format = "%.02f";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState); // Call Supercass's version
		
		setContentView(R.layout.activity_tip_calc); // Inflate the GUI from the XML
		
		// Check if the app just started, or is being restored from memory.
		if ( savedInstanceState == null ) { // The app just started
			
			currentBillTotal = 0.0; // Set the bill amount to zero
			currentCustomPercent = 18; // Set the custom tip to 18 percent
		}
		else { // The app is being restored from memory, not executed from scratch
			
			// Set the bill amount to saved amount
			currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
			
			// Set the custom tip to saved percent
			currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
		}
		
		initializeUI();
		
		billEditText.addTextChangedListener(billEditTextWatcher);
		
		SeekBar customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
		customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
	}

	private void initializeUI() {
		
		tip10EditText		= (EditText) findViewById(R.id.tip10EditText);
		tot10EditText		= (EditText) findViewById(R.id.total10EditText);
		tip15EditText		= (EditText) findViewById(R.id.tip15EditText);
		tot15EditText		= (EditText) findViewById(R.id.total15EditText);
		tip20EditText		= (EditText) findViewById(R.id.tip20EditText);
		tot20EditText		= (EditText) findViewById(R.id.total20EditText);
		tipCustomEditText	= (EditText) findViewById(R.id.tipCustomEditText);
		totCustomEditText	= (EditText) findViewById(R.id.totalCustomEditText);
		billEditText		= (EditText) findViewById(R.id.billEditText);
		customTipTextView	= (TextView) findViewById(R.id.customTipTextView);
	}
	
	private void updateStandard() {
		
		// --------------- For 10% ---------------
		// Calculate bill total with a ten percent tip
		double tenPcTip = currentBillTotal * 0.1;
		double tenPcTot = currentBillTotal + tenPcTip;
		
		// Set tip and totTenEditText's text to tenPcTip and Tot
		tip10EditText.setText(String.format(format, tenPcTip));
		tot10EditText.setText(String.format(format, tenPcTot));
		
		
		// --------------- For 15% ---------------
		// Calculate bill total with a fifteen percent tip
		double fifPcTip = currentBillTotal * 0.15;
		double fifPcTot = currentBillTotal + fifPcTip;

		// Set tip and totFifteenEditText's text to fifPcTip and Tot
		tip15EditText.setText(String.format(format, fifPcTip));
		tot15EditText.setText(String.format(format, fifPcTot));


		// --------------- For 20% ---------------
		// Calculate bill total with a twenty percent tip
		double twePcTip = currentBillTotal * 0.2;
		double twePcTot = currentBillTotal + twePcTip;

		// Set tip and totTwentyEditText's text to twePcTip and Tot
		tip20EditText.setText(String.format(format, twePcTip));
		tot20EditText.setText(String.format(format, twePcTot));
	}
	
	private void updateCustom() {
		
		// Match the position of the SeekBar
		customTipTextView.setText(currentCustomPercent + "%");
		
		// Calculate custom tip and total amounts
		double cusTipAm = currentBillTotal * currentCustomPercent * 0.01;
		double cusTotAm = currentBillTotal + cusTipAm;
		
		// Display the tip and total bill amounts
		tipCustomEditText.setText(String.format(format, cusTipAm));
		totCustomEditText.setText(String.format(format, cusTotAm));
	}
	
	// Called when the user changes the position of the SeekBar
	private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener() {
		
		@Override
		public void onProgressChanged(SeekBar sb, int prog, boolean fromUser) {
			
			currentCustomPercent = sb.getProgress();
			updateCustom();
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar sb) {}
		
		@Override
		public void onStopTrackingTouch(SeekBar sb) {}
	}; // End OnSeekBarChangeListener
		
	// Event handling object that responds to billEditText's events
	private TextWatcher billEditTextWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			
			try {
				
				currentBillTotal = Double.parseDouble(s.toString());
			}
			catch (NumberFormatException e) {
				
				currentBillTotal = 0.0;
			}
			finally {
				
				updateStandard();
				updateCustom();
			}
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
		
		@Override
		public void afterTextChanged(Editable s) {}
	}; // End billEditTextWatcher
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		super.onSaveInstanceState(outState);
		
		outState.putDouble(BILL_TOTAL, currentBillTotal);
		outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
	} // End onSaveInstanceState

	/*// ---------------------------- Options menu methods ----------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tip_calc, menu);
		return true;
	}*/

}
