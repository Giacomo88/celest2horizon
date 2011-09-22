package com.c2h;

import com.lamerman.FileDialog;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener, OnPreferenceClickListener, OnClickListener {

	String strLongitude;
	private EditTextPreference mUserObjectPreference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        addPreferencesFromResource(R.xml.preferences);
	        
	        mUserObjectPreference = (EditTextPreference)getPreferenceScreen().findPreference("UserObjectPath");
	        mUserObjectPreference.setOnPreferenceClickListener(this);
	        mUserObjectPreference.getEditText().setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
	    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

	}	
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		Log.d("Debug", "Pref changed -> "+key +" ");
		SetSummaries();
	}
	
	@Override
	public boolean onPreferenceClick(Preference preference) {

        return false;
	}
	
    public synchronized void onActivityResult(final int requestCode,
            int resultCode, final Intent data) {
    	
            if (resultCode == Activity.RESULT_OK) {

                        Globals.strUserPath = data.getStringExtra(FileDialog.RESULT_PATH);

                        mUserObjectPreference.getEditText().setText(Globals.strUserPath);
                        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("UserObjectPath", Globals.strUserPath);
                        editor.commit();
                        
                        SetSummaries();
            } else if (resultCode == Activity.RESULT_CANCELED) {
            	Log.v("FD", "Request cancelled");
            }
    }
    
	void SetSummaries() {

		
		mUserObjectPreference.setSummary("User object at "+Globals.strUserPath);
	}

	@Override
	public void onClick(View v) {
		Log.d("Debug", "Creating fd activity -> "+Globals.strUserPath);
		Intent fileIntent = new Intent(this, FileDialog.class);
		String strStartPath = Globals.strUserPath;
		strStartPath = strStartPath.substring(0, strStartPath.lastIndexOf("/")) + "/";
		fileIntent.putExtra(FileDialog.START_PATH, strStartPath);
		
		fileIntent.putExtra(FileDialog.LOADSAVE, "LOAD");
		fileIntent.putExtra("PATHONLY", "false");
		Log.d("Debug", "Starting fd activity");
		startActivityForResult(fileIntent, FileDialog.REQUEST_LOAD);
		Log.d("Debug", "fd activity started");
		
	}
}
