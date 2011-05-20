/*   Celest2Horizon - Converts RA / Dec to Altitude / Azimuth on Droid Phones
    Copyright (C) 2010  F. A. Willis

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.c2h;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.util.*;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;

import android.content.Context;
import android.content.DialogInterface;

import java.util.Calendar;
import android.util.Log;

public class C2H extends Activity {

    EditText textAltitude;
    EditText textAzimuth;
    EditText textScopeAltitude;
    EditText textScopeAzimuth;

    EditText textRA;
    EditText textDEC;

    Button ButtonSync;
    SyncClicked mySyncClicker;
    Spinner  spinner;
    Spinner  spinner_group;
    TextView objectName;

    MyLocationListener thisLocation;
    DobOrientation thisDob;
    MyCount counter;
        
    protected int mPos;
    protected String mSelection;
        Messier myMessiers;
        Stars myStars;
        Planets myPlanets;
        int     objectSet;
        double mst_time;
       
        double hrz_altitude, hrz_azimuth;
        Calendar mst = Calendar.getInstance(TimeZone.getTimeZone("GMT"));


        //MyDataStruct myPlanets[];
        ArrayAdapter<String> adapterMessier;
        ArrayAdapter<String> adapterStars;
        ArrayAdapter<String> adapterPlanets;
        ArrayAdapter<String> adapterGroups;
       
        int globalPos = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Globals.myContext = this;
        
        thisLocation = new MyLocationListener(this);
        thisDob = new DobOrientation(this);

        //textSiderealTime = (EditText) findViewById(R.id.EditTextTimeSidereal);
        textAltitude = (EditText) findViewById(R.id.EditTextAlt);
        textAzimuth = (EditText) findViewById(R.id.EditTextAz);

        textScopeAltitude = (EditText) findViewById(R.id.EditTextScopeAlt);
        textScopeAzimuth = (EditText) findViewById(R.id.EditTextScopeAz);

        textRA = (EditText) findViewById(R.id.EditTextRA);
        textDEC = (EditText) findViewById(R.id.EditTextDEC);

        ButtonSync = (Button)findViewById(R.id.buttonSync);
        mySyncClicker = new SyncClicked();
        ButtonSync.setOnClickListener(mySyncClicker);

        //Globals.dScaleHeading = savedInstanceState.getDouble("ScaleHeading");
        
        objectName = (TextView)findViewById(R.id.TextView09);
                     
        counter = new MyCount(15000, 1000);
        counter.start();
       
        spinner_group= (Spinner) findViewById(R.id.Spinner02);
        String myGroups[] = new String[3];
        myGroups[0] = "Messier";
        myGroups[1] = "Planets";
        myGroups[2] = "Stars";
       
        adapterGroups = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myGroups);
        
        adapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_group.setAdapter(adapterGroups);

        OnItemSelectedListener spinnerListener = new myOnItemSelectedListener(this,this.adapterGroups);
        spinner_group.setOnItemSelectedListener(spinnerListener);
       
        spinner = (Spinner) findViewById(R.id.Spinner01);

        myMessiers = new Messier();
        myPlanets = new Planets();
        myStars = new Stars();
        objectSet = 0;
       
        String[] someStrings;//= new String[110];
       
        someStrings = myMessiers.GetStrings();
       
        adapterMessier = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, someStrings);
        adapterMessier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterMessier);

        spinnerListener = new myOnItemSelectedListener(this, this.adapterMessier);
        spinner.setOnItemSelectedListener(spinnerListener);
       
        String[] somePlanets = myPlanets.GetStrings();       
        adapterPlanets = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, somePlanets);
       
        String[] someStars = myStars.GetStrings();       
        adapterStars = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, someStars);
    }

    @Override
	protected void onResume() {
        super.onResume();
    	Log.v("Debugging", "C2H - On resume");
    	counter.bRunning = true;
    	thisDob.onResume();
    	Globals.OnResume();
    }

    @Override
	protected void onPause() {
        super.onPause();
    	Log.v("Debugging", "C2H - On pause");
    	counter.bRunning = false;
    	thisDob.onPause();
    	Globals.OnPause();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
	// This method is called once the menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		String str = String.format("Menu %d", item.getItemId());
		Log.v("Debug", str);
		switch (item.getItemId()) {
		
		//case R.id.item01:
			/*
			adapterMessier.sort(new Comparator<String>() {
				public int compare(String object1, String object2) {
					return object1.compareTo(object2);
				};
			}
			);
			
			adapterStars.sort(new Comparator<String>() {
				public int compare(String object1, String object2) {
					return object1.compareTo(object2);
				};
			}
			);*/
			
			//break;	
			
		//case R.id.item02:
			/*adapterMessier.sort(new Comparator<String>() {
				public int compare(String object1, String object2) {
					return object2.compareTo(object1);
				};
			});
			
			adapterStars.sort(new Comparator<String>() {
				public int compare(String object1, String object2) {
					return object2.compareTo(object1);
				};
			});*/

			//break;
			
		// We have two menu options
		/*case R.id.SetAZOffset:
			
			AlertDialog.Builder alert = new AlertDialog.Builder(this);  
			  
			alert.setTitle("Azimuth offset");  
			alert.setMessage("Enter azimuth offset in degrees");  
			 
			// Set an EditText view to get user input   
			final EditText input = new EditText(this);  
			alert.setView(input);  
			  
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
			  String value = "Get new offset "+ input.getText().toString();  
			  Log.v("C2HDebugging", value);
			  textAzimuthOffset.setText(input.getText().toString());
			  }  
			});  
			  
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
			  public void onClick(DialogInterface dialog, int whichButton) {  
				  Log.v("C2HDebugging", "Get new offset cancelled");   
			  }  
			});  
			  
			alert.show();  
			break;*/
			
		case R.id.About:
			AboutDialog myAboutBox = new AboutDialog(this);
			myAboutBox.show();
			break;

		case R.id.settings:
			Log.v("Debug", "Settings dialog - creating");
			SettingsDialog mySettingsBox = new SettingsDialog(this);
			Log.v("Debug", "Settings dialog completed");
			
			mySettingsBox.Init();
			mySettingsBox.show();
			Log.v("Debug", "Settings shown");
			break;

		}
		return true;
	}
  
    public class MyCount extends CountDownTimer {
        
    	Boolean bRunning;
    	
    	public MyCount(long millisInFuture, long countDownInterval) {
          super(millisInFuture, countDownInterval);
          bRunning = true;
        }

        @Override
		public void onFinish() {
        	if( bRunning ) {
            	Log.v("Debug", "restarting clock");
        		start();
        	}
        }
       
        @Override
		public void onTick(long millisUntilFinished) {
//         GetLocation();
        	//Log.v("Debug", "Tick...");
        	
           Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
           
           int nHour = now.get(Calendar.HOUR_OF_DAY);
           int nMinutes = now.get(Calendar.MINUTE);
           String txt = String.format("%d - %d", nHour, nMinutes);
               
           String strRA  = textRA.getText().toString();
           String strDEC = textDEC.getText().toString();
           
           coord_to_horizon((Calendar)now.clone(), strRA, strDEC, Globals.dLatitude, Globals.dLongitude);

           txt = String.format("%.2f", hrz_altitude);
           textAltitude.setText(txt);
           txt = String.format("%.2f", hrz_azimuth);
           textAzimuth.setText(txt);
           Globals.dTargetHeading = hrz_azimuth;
           Globals.dTargetPitch = hrz_altitude;
           
           txt = String.format("%.2f", Globals.dDobPitch  - Globals.dDobPitchDelta);
           textScopeAltitude.setText(txt);
           txt = String.format("%.2f", Globals.dDobHeading - Globals.dDobHeadingDelta);
           textScopeAzimuth.setText(txt);
        }
       
     // convert right ascension (hours, minutes) to degrees as real
        public double ra2real( int hr, double min )
        {
            return 15.*(hr + min/60.);
        }

        // convert angle (deg, min) to degrees as real
        public double dms2real( int deg, double min )
        {
            double rv;

            if (deg < 0)
                rv = deg - min/60.;
            else
                rv = deg + min/60.;

            return rv;
        }

       
        public void coord_to_horizon( Calendar utc, String ra_in, String dec_in, double lat_in, double lon_in )
        {
                // inputs like
                // ra => 3h47.0
                // dec => 24 07.0
                // lat => 42 01.2
                // lon => -89 06.2
               //Log.v("Debug", "RA/Dec -> " + ra_in + ", " + dec_in);
               
                String delims = "h";
                String[] ra_str = ra_in.split(delims);
                delims = " ";
                String[] dec_str = dec_in.split(delims);
                //String[] lat_str = lat_in.split(delims);
                //String[] lon_str = lon_in.split(delims);
               
                //if( lat_str.length<2 )
                //        return;
               
                //if( lon_str.length<2 )
                //        return;
               
                if( dec_str.length<2 )
                        return;
               
                if( ra_str.length<2 )
                        return;
               
            double ra  = ra2real ( Integer.parseInt(ra_str[0]) , Double.parseDouble(ra_str[1])  );
            double dec = dms2real( Integer.parseInt(dec_str[0]), Double.parseDouble(dec_str[1]) );
            double lat = lat_in;//dms2real( Integer.parseInt(lat_str[0]), Double.parseDouble(lat_str[1]) );
            double lon = lon_in;//dms2real( Integer.parseInt(lon_str[0]), Double.parseDouble(lon_str[1]) );

            // compute hour angle in degrees
            double ha = GetSiderealTime( utc, lon ) - ra;
            if (ha < 0) ha = ha + 360;

            // convert degrees to radians
            ha  = ha*Math.PI/180;
            dec = dec*Math.PI/180;
            lat = lat*Math.PI/180;

            // compute altitude in radians
            double sin_alt = Math.sin(dec)*Math.sin(lat) + Math.cos(dec)*Math.cos(lat)*Math.cos(ha);
            double alt = Math.asin(sin_alt);
           
            // compute azimuth in radians
            // divide by zero error at poles or if alt = 90 deg
            double cos_az = (Math.sin(dec) - Math.sin(alt)*Math.sin(lat))/(Math.cos(alt)*Math.cos(lat));
            double az  = Math.acos(cos_az);

            // convert radians to degrees
            hrz_altitude = alt*180/Math.PI;
            hrz_azimuth  = az*180/Math.PI;

            // choose hemisphere
            if (Math.sin(ha) > 0)
                hrz_azimuth = 360 - hrz_azimuth;
        }
       
        public double GetSiderealTime(Calendar now, double longitude){
               
                mst = now;
               
            int year   = now.get(Calendar.YEAR);
            int month  = now.get(Calendar.MONTH)+1;
            int day    = now.get(Calendar.DAY_OF_MONTH);
            int hour   = now.get(Calendar.HOUR_OF_DAY);
            int minute = now.get(Calendar.MINUTE);
            int second = now.get(Calendar.SECOND);
            
            if ((month == 1)||(month == 2))
            {
                year  = year - 1;
                month = month + 12;
            }

            double a = Math.floor(year/100);
            double b = 2 - a + Math.floor(a/4);
            double c = Math.floor(365.25*year);
            double d = Math.floor(30.6001*(month + 1));

            // days since J2000.0
            double jd = b + c + d - 730550.5 + day + (hour + minute/60.0 + second/3600.0)/24.0;
           
            // julian centuries since J2000.0
            double jt = jd/36525.0;

            // the mean sidereal time in degrees
            mst_time = 280.46061837 + 360.98564736629*jd + 0.000387933*jt*jt - jt*jt*jt/38710000 + longitude;

            // in degrees modulo 360.0
            if (mst_time > 0.0)
                while (mst_time > 360.0) mst_time = mst_time - 360.0;
            else
                while (mst_time < 0.0)   mst_time = mst_time + 360.0;

            double mst2 = mst_time * 23.93333 / 360.;
            mst.set(Calendar.YEAR, 1962);
            mst.set(Calendar.HOUR_OF_DAY, (int)mst2);
            mst2 = (mst2 - (int)mst2)*60;
            mst.set(Calendar.MINUTE, (int)mst2);
            mst2 = (mst2 - (int)mst2)*60;            
            mst.set(Calendar.SECOND, (int)mst2);
           
                return mst_time;
        }
    };
       
    public class myOnItemSelectedListener implements OnItemSelectedListener {

        /*
         * provide local instances of the mLocalAdapter and the mLocalContext
         */

        ArrayAdapter<String> mLocalAdapter;
        Activity mLocalContext;

        /**
         *  Constructor
         *  @param c - The activity that displays the Spinner.
         *  @param ad - The Adapter view that
         *    controls the Spinner.
         *  Instantiate a new listener object.
         */
        public myOnItemSelectedListener(Activity c, ArrayAdapter<String> ad) {

          this.mLocalContext = c;
          this.mLocalAdapter = ad;

        }

        /**
         * When the user selects an item in the spinner, this method is invoked by the callback
         * chain. Android calls the item selected listener for the spinner, which invokes the
         * onItemSelected method.
         *
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *  android.widget.AdapterView, android.view.View, int, long)
         * @param parent - the AdapterView for this listener
         * @param v - the View for this listener
         * @param pos - the 0-based position of the selection in the mLocalAdapter
         * @param row - the 0-based row number of the selection in the View
         */
        public void onItemSelected(AdapterView<?> parent, View v, int pos, long row) {
        	
        	String str;
                if( parent==spinner ){
                	str = String.format("Parent = (spinner), Objectset (%d), pos (%d), row *%d)", objectSet, pos, row);
                	Log.v("Debug", str);
                	globalPos = pos;
                        if( objectSet==0 ){
                                textRA.setText(myMessiers.GetRA(pos));
                                textDEC.setText(myMessiers.GetDEC(pos));
                                objectName.setText(myMessiers.GetName(pos));                    
                        }
                        
                        if( objectSet==1 ){
                        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                        myPlanets.UpdateRADEC((Calendar)now.clone(), pos);

                                textRA.setText(myPlanets.GetRA(pos));
                                textDEC.setText(myPlanets.GetDEC(pos));
                                objectName.setText(myPlanets.GetName(pos));
                        }
                        
                        if( objectSet==2 ){
                            textRA.setText(myStars.GetRA(pos));
                            textDEC.setText(myStars.GetDEC(pos));
                            objectName.setText(myStars.GetName(pos));                    
                        }
                        
                }
               
                if( parent==spinner_group ){
                	str = String.format("Parent = (spinner_group), Objectset (%d), pos (%d), row *%d)", objectSet, pos, row);
                	Log.v("Debug", str);

                        if( pos == 0 ){
	                        adapterMessier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	                        spinner.setAdapter(adapterMessier);
	                        objectSet = 0;
                        }       
                        
                        if( pos == 1 ){
                            adapterPlanets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapterPlanets);
                            objectSet = 1;
                        }
                        
                        if( pos == 2 ){
	                        adapterStars.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	                        spinner.setAdapter(adapterStars);
	                        objectSet = 2;
                        }       
                }
        }

        /**
         * The definition of OnItemSelectedListener requires an override
         * of onNothingSelected(), even though this implementation does not use it.
         * @param parent - The View for this Listener
         */
        public void onNothingSelected(AdapterView<?> parent) {

            // do nothing

        }
    }
    
    public class AboutDialog extends Dialog implements android.view.View.OnClickListener {
    	    Button okButton;
    	 
    	    public AboutDialog(Context context) {
    	        super(context);
    	        /** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
    	        requestWindowFeature(Window.FEATURE_NO_TITLE);
    	        /** Design the dialog in main.xml file */
    	        setContentView(R.layout.about);
    	        okButton = (Button) findViewById(R.id.OkButton);
    	        okButton.setOnClickListener(this);
    	    }
    	 
    	    public void onClick(View v) {
    	        /** When OK Button is clicked, dismiss the dialog */
    	        if (v == okButton)
    	            dismiss();
    	    }

			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
    	 
    	}
}

