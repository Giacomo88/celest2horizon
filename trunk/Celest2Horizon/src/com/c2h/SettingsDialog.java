package com.c2h;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SettingsDialog extends Dialog implements android.view.View.OnClickListener {
    Button okButton;
    String lat = "0 0.000";
    String lon = "0 0.000";
    String RA = "0h00";
    String Dec= "0 0";

    EditText myLatitude;

    EditText myLongitude;
       
    EditText myRA;

    EditText myDec;

    EditText myAzOffset;
    
    public SettingsDialog(Context context) {
        super(context);
        /** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /** Design the dialog in main.xml file */
        setContentView(R.layout.settings);
        
        Log.v("Debug", "Settings - getting button");
        okButton = (Button) findViewById(R.id.OkButton);
        okButton.setOnClickListener(this);
        
        Log.v("Debug", "Settings - getting lat/lon");
        myLatitude = (EditText) findViewById(R.id.Lat);
        myLongitude = (EditText) findViewById(R.id.Lon);

        Log.v("Debug", "Settings - getting ra/dec");
        myRA = (EditText) findViewById(R.id.RA);
        myDec = (EditText) findViewById(R.id.Dec);

    }
 
    public void Init(){

    	Log.v("Debug", "Init settings");
    	NumberFormat formatter = new DecimalFormat("0.000");
    	    	
        //String delims = "h";
        //String[] ra_str = textRA.getText().toString().split(delims);
        //delims = " ";
        //String[] dec_str = textDEC.getText().toString().split(delims);
        //String[] lat_str = myLatitude.getText().toString().split(delims);
        //String[] lon_str = myLongitude.getText().toString().split(delims);
       
        myLatitude.setText(formatter.format(Globals.dLatitude));  //lat_str[0]);
        myLongitude.setText(formatter.format(Globals.dLongitude));
        /*myRAHour.setText(ra_str[0]);
        myRAMin.setText(ra_str[1]);
        myDecDeg.setText(dec_str[0]);
        myDecMin.setText(dec_str[1]);*/
    }
    
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
        /** When OK Button is clicked, dismiss the dialog */
        if (v == okButton){
				//textDEC.setText(myDecDeg.getText().toString()+" "+myDecMin.getText().toString());
				//textRA.setText(myRAHour.getText().toString()+"h"+myRAMin.getText().toString());
				//textAzimuthOffset.setText(myAzOffset.getText().toString());
		        //myLatitude.setText(myLatitudeDeg.getText().toString()+" "+myLatitudeMin.getText().toString());
		        //myLongitude.setText(myLongitudeDeg.getText().toString()+" "+myLongitudeMin.getText().toString());
            dismiss();
        }		
	}
 
}
