package com.c2h;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

class MyLocationListener implements LocationListener {
	
    public MyLocationListener(Context thisContext) {
    	//Log.v("Debug", "Starting location");
    	LocationManager locMan = (LocationManager) thisContext.getSystemService(Context.LOCATION_SERVICE);
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
    }

    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
	public void onLocationChanged(Location loc) {
		//Log.v("Debug", "Location changed");
		Globals.dLatitude = loc.getLatitude();
		Globals.dLongitude = loc.getLongitude();		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
}
