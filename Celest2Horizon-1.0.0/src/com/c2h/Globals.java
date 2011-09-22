package com.c2h;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Globals {
	
	static Context myContext;
	
    static double dLatitude = 0;
    static double dLongitude = 0;
    
    static double dPrefLatitude = 0;
    static double dPrefLongitude = 0;    

    static double dDobHeading = 0;
    static double dDobPitch = 0;
    
    static double dTargetHeading = 0;
    static double dTargetPitch = 0;    
    
    static double dDobHeadingDelta = 0;
    static double dDobPitchDelta = 0;
    
    static double dScaleHeading = 0.99;
    static double dScalePitch = 0.99;
    
    static double dAverage;
    static double dSigma;
    
    static Boolean bUseGPSLocation;
    
    static String strUserPath = null;
    
    static double hrz_altitude, hrz_azimuth;
    
    static double mst_time;
    
    static public void GetPath() {
        SharedPreferences settings = myContext.getSharedPreferences("Celest2Horizon", 0);
        strUserPath = settings.getString("UserObjPath", "/sdcard/Celest2Horizon/UserObjects.csv");    	
    }
    
    static public void OnResume() {
    	
    	SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(myContext);
        dScaleHeading = Float.valueOf(settings.getString("ScaleHeading", "0.99"));        
        dScalePitch = Float.valueOf(settings.getString("ScalePitch", "0.99"));
        //dDobHeadingDelta = Float.valueOf(settings.getString("DobHeadingDelta", "0"));
        //dDobPitchDelta = Float.valueOf(settings.getString("DobPitchDelta", "0"));
        
        bUseGPSLocation = settings.getBoolean("UseGPSLocation", true);
        dPrefLatitude = Float.valueOf(settings.getString("Latitude", "40."));
        dPrefLongitude = Float.valueOf(settings.getString("Longitude", "-89."));
        
        if( !bUseGPSLocation ) {
        	dLatitude = dPrefLatitude;
        	dLongitude = dPrefLongitude;
        }
        
        strUserPath = settings.getString("UserObjPath", "//sdcard//UserObjects.csv");
        
    	Log.d("Debug", "Globals - on resume: UseGPS "+bUseGPSLocation+", PrefLat "+dPrefLatitude);
    }
    
    static public void OnPause() {
    	
    	Log.d("Debug", "Globals - on pause");

    	SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(myContext);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("ScaleHeading", Double.toString(dScaleHeading));
        editor.putString("ScalePitch", Double.toString(dScalePitch));
        //editor.putString("DobHeadingDelta", Double.toString(dDobHeadingDelta));
        //editor.putString("DobPitchDelta", Double.toString(dDobPitchDelta));
        
        editor.putString("UserObjPath", strUserPath);
        editor.commit();
    }
    
    static public void coord_to_horizon( Calendar utc, String ra_in, String dec_in, double lat_in, double lon_in )
    {
            // inputs like
            // ra => 3h47.0
            // dec => 24 07.0
            // lat => 42 01.2
            // lon => -89 06.2

    	CoordConverter myConverter = new CoordConverter();
    	
    	double ra = myConverter.RAString2Double(ra_in);
    	double dec = myConverter.DecString2Double(dec_in);
    	
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
   
    static public double GetSiderealTime(Calendar now, double longitude){
           
        Calendar mst = now;
           
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
}
