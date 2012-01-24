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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.io.FileNotFoundException;
import java.util.*;

import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.content.Intent;
import java.util.Calendar;

/* http://code.google.com/p/android-file-dialog/ */
import com.lamerman.FileDialog;

public class C2H extends Activity implements OnClickListener {

	private static final int REQUEST_SAVE = 0;
	private static final int REQUEST_LOAD = 0;
	EditText textAltitude;
    EditText textAzimuth;
    EditText textScopeAltitude;
    EditText textScopeAzimuth;

    EditText textRA;
    EditText textDEC;

    Button ButtonSync;
    Button ButtonMakeHighest;
    Button ButtonInfoView;
    
    SyncClicked mySyncClicker;
    Spinner  spinner;
    Spinner  spinner_group;
    int objectSet;

    TextView objectName;

    MyLocationListener thisLocation;
    DobOrientation thisDob;
    MyCount counter;
    Stats myStats;
    
    protected int mPos;
    protected String mSelection;
        Messier myMessiers;
        Stars myStars;
        Planets myPlanets;
        UserObjects myObjects;
        NearestObjs myHighestObjs;
        
        //Calendar mst = Calendar.getInstance(TimeZone.getTimeZone("GMT"));


        //MyDataStruct myPlanets[];
        ArrayAdapter<String> adapterMessier;
        ArrayAdapter<String> adapterStars;
        ArrayAdapter<String> adapterPlanets;
        ArrayAdapter<String> adapterUserObjects;
        ArrayAdapter<String> adapterHighestObjects;
        ArrayAdapter<String> adapterGroups;
       
        int globalPos = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);

        Globals.myContext = this;
        myStats = new Stats();
        
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

        
        ButtonMakeHighest = (Button)findViewById(R.id.buttonSortHighest);
        ButtonMakeHighest.setOnClickListener(this);
        
        ButtonInfoView = (Button)findViewById(R.id.buttonInfo);
        ButtonInfoView.setOnClickListener(this);
        
        objectName = (TextView)findViewById(R.id.TextView09);
                     
        counter = new MyCount(15000, 1000);
        counter.start();
       
        spinner_group= (Spinner) findViewById(R.id.Spinner02);
        String myGroups[] = new String[5];
        myGroups[0] = "Messier";
        myGroups[1] = "Planets";
        myGroups[2] = "Stars";
        myGroups[3] = "User";
        myGroups[4] = "Highest";
        
//        adapterGroups = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myGroups);
        adapterGroups = new ArrayAdapter<String>(this, R.layout.myspinnerlayout, myGroups);
        adapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_group.setAdapter(adapterGroups);

        OnItemSelectedListener spinnerListener = new myOnItemSelectedListener(this,this.adapterGroups);
        spinner_group.setOnItemSelectedListener(spinnerListener);
       
        spinner = (Spinner) findViewById(R.id.Spinner01);

        myMessiers = new Messier();
        myPlanets = new Planets();
        myStars = new Stars();
        myHighestObjs = new NearestObjs();
        myHighestObjs.SortHighest();
        
        try {
			myObjects = new UserObjects();
		} catch (FileNotFoundException e1) {
			Log.d("Debug", "File not found");
			e1.printStackTrace();
		}
       
        String[] someStrings;//= new String[110];
       
        someStrings = myMessiers.GetStrings();
       
        adapterMessier = new ArrayAdapter<String>(this, R.layout.myspinnerlayout, someStrings);
        adapterMessier.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterMessier);

        spinnerListener = new myOnItemSelectedListener(this, this.adapterMessier);
        spinner.setOnItemSelectedListener(spinnerListener);
       
        String[] somePlanets = myPlanets.GetStrings();       
        adapterPlanets = new ArrayAdapter<String>(this, R.layout.myspinnerlayout, somePlanets);
       
        String[] someStars = myStars.GetStrings();       
        adapterStars = new ArrayAdapter<String>(this, R.layout.myspinnerlayout, someStars);

        String[] someHighestObjs = myHighestObjs.GetStrings();       
        adapterHighestObjects = new ArrayAdapter<String>(this, R.layout.myspinnerlayout, someHighestObjs);

        Log.d("Debug", "Getting obj strings");
        String[] someObjs;
        if( myObjects != null )
        {
	        someObjs = myObjects.GetStrings();
        }
        else
        {
        	someObjs = new String[1];
        	someObjs[0] = ("No user objects defined");
        }
	        Log.d("Debug", "Got obj strings");
	        if( someObjs != null )
	        	adapterUserObjects = new ArrayAdapter<String>(this, R.layout.myspinnerlayout, someObjs);
	    
        Log.d("Debug", "On create finished");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("objectSet", objectSet);
        outState.putInt("globalPos", globalPos);
        
        super.onSaveInstanceState(outState);
    	Log.d("Debug", "On save instance -> "+globalPos);
   }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	objectSet = savedInstanceState.getInt("objectSet");
    	globalPos = savedInstanceState.getInt("globalPos");

    	spinner.setSelection(globalPos);
    	
        super.onRestoreInstanceState(savedInstanceState);
    	Log.d("Debug", "On restore instance -> "+globalPos);
    }
    
    @Override
	protected void onResume() {
        super.onResume();
    	Log.d("Debug", "C2H - On resume");
    	counter.bRunning = true;
    	thisDob.onResume();
    	Globals.OnResume();
    }

    @Override
	protected void onPause() {
        super.onPause();
    	Log.d("Debug", "C2H - On pause");
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
		Log.d("Debug", str);
		switch (item.getItemId()) {
			
		case R.id.About:
			AboutDialog myAboutBox = new AboutDialog(this);
			myAboutBox.show();
			break;

		case R.id.settings:
			/*Log.d("Debug", "Settings dialog - creating");
			SettingsDialog mySettingsBox = new SettingsDialog(this);
			Log.d("Debug", "Settings dialog completed");
			
			mySettingsBox.Init();
			mySettingsBox.show();*/
			Log.d("Debug", "Settings shown");
            Intent settingsActivity = new Intent(getBaseContext(), Preferences.class);
            startActivity(settingsActivity);
            Log.d("Debug", "Pref activity started");
            
			break;

		case R.id.user:			
    		Intent fileIntent = new Intent(C2H.this, FileDialog.class);
			fileIntent.putExtra(FileDialog.START_PATH, "/sdcard");
    		startActivityForResult(fileIntent, REQUEST_SAVE);
			break;
		}
		return true;
	}
  
    @Override
	public synchronized void onActivityResult(final int requestCode,
            int resultCode, final Intent data) {

            if (resultCode == Activity.RESULT_OK) {

                    if (requestCode == REQUEST_SAVE) {
                            Log.d("FD", "Saving...");
                    } else if (requestCode == REQUEST_LOAD) {
                    	Log.d("FD", "Loading...");
                    }
                    
                    String filePath = data.getStringExtra(FileDialog.RESULT_PATH);
                    Log.d("FD", "File "+filePath);
                    Globals.strUserPath = filePath;
                    
            } else if (resultCode == Activity.RESULT_CANCELED) {
            	Log.d("FD", "Request cancelled");
                    //Logger.getLogger(AccelerationChartRun.class.getName()).log(
                    //                Level.WARNING, "file not selected");
            }

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
            	Log.d("Debug", "restarting clock");
        		start();
        	}
        }
       
        @Override
    	public void onTick(long millisUntilFinished) {
        	
           Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
           
           int nHour = now.get(Calendar.HOUR_OF_DAY);
           int nMinutes = now.get(Calendar.MINUTE);
           String txt = String.format("%d - %d", nHour, nMinutes);
               
           String strRA  = textRA.getText().toString();
           String strDEC = textDEC.getText().toString();
           
           Globals.coord_to_horizon((Calendar)now.clone(), strRA, strDEC, Globals.dLatitude, Globals.dLongitude);

           txt = String.format("%.2f", Globals.hrz_altitude);
           textAltitude.setText(txt);
           txt = String.format("%.2f", Globals.hrz_azimuth);
           textAzimuth.setText(txt);
           Globals.dTargetHeading = Globals.hrz_azimuth;
           Globals.dTargetPitch = Globals.hrz_altitude;
           
           txt = String.format("%.2f", Globals.dDobPitch  - Globals.dDobPitchDelta);
           textScopeAltitude.setText(txt);
           txt = String.format("%.2f", Globals.dDobHeading - Globals.dDobHeadingDelta);
           textScopeAzimuth.setText(txt);
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
                	Log.d("Debug", str);
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

                        if( objectSet==3 ){
                        	if( myObjects == null )
                        	{
	                            textRA.setText("0h0");
	                            textDEC.setText("0");
	                            objectName.setText("None");                             		
                        	}
                        	else
                        	{
	                            textRA.setText(myObjects.GetRA(pos));
	                            textDEC.setText(myObjects.GetDEC(pos));
	                            objectName.setText(myObjects.GetName(pos));     
                        	}
                        }

                        if( objectSet==4 ){
                            textRA.setText(myHighestObjs.GetRA(pos));
                            textDEC.setText(myHighestObjs.GetDEC(pos));
                            objectName.setText(myHighestObjs.GetName(pos));                    
                        }
                }

                if( parent==spinner_group ){
                	str = String.format("Parent = (spinner_group), Objectset (%d), pos (%d), row *%d)", objectSet, pos, row);
                	Log.d("Debug", str);

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

                        if( pos == 3 ){
                        	adapterUserObjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	                        spinner.setAdapter(adapterUserObjects);
	                        objectSet = 3;
                        }
                        
                        if( pos == 4 ){
                        	adapterHighestObjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	                        spinner.setAdapter(adapterHighestObjects);
	                        objectSet = 4;
                        }
                        spinner.setSelection(globalPos, true);
                        
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
    
    @Override
    public void onClick(View v) {
    	
    	switch(v.getId())
    	{
	    	case R.id.buttonSortHighest:
	    		
	    		myHighestObjs = new NearestObjs();
	    		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	    		//"C1","0h0.0","0 0","---","0"
	    		for(int i=0; i<110; i++)
	    		{
	    			//String strLabel = "Sort"+Integer.toString(i);
	    			//String strAz = "0h"+Integer.toString(i);
		    		//MyDataStruct ds = new MyDataStruct(strLabel, strAz, "0 0", "+++", "1");
		    		
	    			Globals.coord_to_horizon((Calendar)now.clone(), 
		    						  myMessiers.myMessier[i].RA, myMessiers.myMessier[i].DEC,
		    						  Globals.dLatitude, Globals.dLongitude);
	    			
		    		myMessiers.myMessier[i].dAltitude = Globals.hrz_altitude;
		    		myMessiers.myMessier[i].dAzimuth = Globals.hrz_azimuth;

		    		myHighestObjs.Add(myMessiers.myMessier[i]);
	    		}
	    		
	    		myHighestObjs.SortHighest();
	    		
		        String[] someHighestObjs = myHighestObjs.GetStrings();    

		        adapterHighestObjects = new ArrayAdapter<String>(this, R.layout.myspinnerlayout, someHighestObjs);
		        
            	adapterHighestObjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapterHighestObjects);
                objectSet = 4;
                
		        break;
		        
			case R.id.buttonInfo:
				Log.d("Debug", "Showing info dialog");
				InfoDialog myInfoBox = new InfoDialog(this);
				myInfoBox.show();
				break;	
    	}
    	
    }
}

