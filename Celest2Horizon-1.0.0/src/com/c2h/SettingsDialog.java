package com.c2h;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

//public class SettingsDialog extends Dialog implements android.view.View.OnClickListener {
public class SettingsDialog extends Dialog implements OnClickListener {
	Button okButton;
    Button cancelButton;
    Button buttonClearSync;
    
    String lat = "0 0.000";
    String lon = "0 0.000";
    String RA = "0h00";
    String Dec= "0 0";

    EditText myLatitude;
    EditText myLongitude;
    EditText editTextFilterAz;
    EditText editTextFilterAlt;
    EditText editTextUserObjs;
    
    EditText statAverage;
    EditText statSigma;
    
    public SettingsDialog(Context context) {
        super(context);
        /** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /** Design the dialog in main.xml file */
        setContentView(R.layout.settings);
        
        Log.v("Debug", "Settings - getting button");
        okButton = (Button) findViewById(R.id.OkButton);
        okButton.setOnClickListener(this);
        
        cancelButton = (Button) findViewById(R.id.CancelButton);
        cancelButton.setOnClickListener(this);
        
        buttonClearSync = (Button) findViewById(R.id.buttonClearSync);
        buttonClearSync.setOnClickListener(this);       
        
        Log.v("Debug", "Settings - getting lat/lon");
        myLatitude = (EditText) findViewById(R.id.Lat);
        myLongitude = (EditText) findViewById(R.id.Lon);
        editTextFilterAz = (EditText) findViewById(R.id.editTextFilterAz);
        editTextFilterAlt = (EditText) findViewById(R.id.editTextFilterAlt);
        editTextUserObjs = (EditText) findViewById(R.id.editTextUserObjs);
        
        statAverage = (EditText) findViewById(R.id.editTextAverage);
        statSigma = (EditText) findViewById(R.id.editTextSigma);
    }
 
    public void Init(){

    	Log.v("Debug", "Init settings");
    	NumberFormat formatter = new DecimalFormat("0.000");
       
        myLatitude.setText(formatter.format(Globals.dLatitude));  //lat_str[0]);
        myLongitude.setText(formatter.format(Globals.dLongitude));
        editTextFilterAz.setText(formatter.format(Globals.dScaleHeading));
        editTextFilterAlt.setText(formatter.format(Globals.dScalePitch));  
        editTextUserObjs.setText(Globals.strUserPath);
        statAverage.setText(formatter.format(Globals.dAverage));
        statSigma.setText(formatter.format(Globals.dSigma));        
    }
    
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
        /** When OK Button is clicked, dismiss the dialog */
		String str = String.format("Menu %d", v.getId());
		Log.v("Debug", str);
		
        if (v == okButton){
        	Globals.dLatitude = Double.valueOf(myLatitude.getText().toString());
        	Globals.dLongitude = Double.valueOf(myLongitude.getText().toString());
        	Globals.dScaleHeading = Double.valueOf(editTextFilterAz.getText().toString());
        	Globals.dScalePitch = Double.valueOf(editTextFilterAlt.getText().toString());
        	Globals.strUserPath = editTextUserObjs.getText().toString();
        	dismiss();
        }
        
        if (v == cancelButton){
        	dismiss();
        }
        
        if( v == buttonClearSync )
        {
        	Globals.dDobHeadingDelta = Globals.dDobPitchDelta = 0;
        }
	}
 
}
