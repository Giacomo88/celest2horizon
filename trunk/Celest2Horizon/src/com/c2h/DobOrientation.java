package com.c2h;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.EditText;
import java.lang.Math;

public class DobOrientation implements SensorEventListener {
	
	    Sensor mAccelerometer;
	    Sensor mMagneticField;
		EditText txtHeading;
		EditText txtPitch;
		SensorManager mSensorManager;

	    float[] accelerometerValues = null;
		float[] geomagneticMatrix = null;
		//double[] dHdg;
		//double[] dPitch;
		//int nIndex;
		int nCountDown;
		
		boolean sensorReady = false;
		
	    public DobOrientation(Context thisContect) {
	    	//dHdg = new double[100];
	    	//dPitch = new double[100];
	    	//for(int i=0; i<100; i++)
	    	//	dHdg[i] = dPitch[i] = 0.;
	    	//nIndex = 0;
	    	nCountDown = 100;
			 
			mSensorManager = (SensorManager)thisContect.getSystemService("sensor");
			mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
			
	        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	        mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
	       // Log.v("Debug", "Orientation created");
	    }

	    public void onResume() {
	        //super.onResume();
	    	//Log.v("Debugging", "DOB - On resume");

	        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	        mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
	    }

	    public void onPause() {
	       // super.onPause();
	    	//Log.v("Debugging", "DOB - On pause");
	        mSensorManager.unregisterListener(this);

	    }
	    
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }

	    public void onSensorChanged(SensorEvent event) {

	    	//Log.v("Debug", "Orientation changed");
			switch (event.sensor.getType()) {
	        case Sensor.TYPE_ACCELEROMETER:
	            accelerometerValues = event.values.clone();
	        case Sensor.TYPE_MAGNETIC_FIELD:
	            geomagneticMatrix = event.values.clone();
	            sensorReady = true;
	            break;
	        default:
	            break;
	    }   

	    if (geomagneticMatrix != null && accelerometerValues != null && sensorReady) {
	        sensorReady = false;

	        float[] R = new float[16];
	        float[] I = new float[16];

	        if( !SensorManager.getRotationMatrix(R, I, accelerometerValues, geomagneticMatrix) ) {
	        	//Log.v("Debug", "Get rotation failed");
	        	return;
	        }

	        float[] actual_orientation = new float[3];
	        SensorManager.getOrientation(R, actual_orientation);

	        actual_orientation[0] = (float) Math.toDegrees(actual_orientation[0]);
	        actual_orientation[1] = (float) Math.toDegrees(actual_orientation[1]);
	        actual_orientation[2] = (float) Math.toDegrees(actual_orientation[2]);

	        if( actual_orientation[0] < 0 )
	        	actual_orientation[0] += 360.;
	        
	        actual_orientation[1] = -actual_orientation[1];
	        
	       /* dHdg[nIndex] = actual_orientation[0];
	        dPitch[nIndex] = actual_orientation[1];
	        nIndex++;
	        if( nIndex>99 )
	        	nIndex = 0;
	        
	        double dHdgSum = 0;
	        double dPitchSum = 0;
	        
	        for(int i=0; i<100; i++)
	        {
	        	dHdgSum += dHdg[i];
	        	dPitchSum += dPitch[i];
	        }*/
	        
	        if( Globals.dDobHeading - actual_orientation[0] > 180 )
	        	actual_orientation[0] += 360;
	        
	        if( Globals.dDobHeading - actual_orientation[0] < -180 )
	        	actual_orientation[0] -= 360;
	        
	        //out.println("Hdg " + actual_orientation[0] + "\n");

	        double dScale = Globals.dScaleHeading;
	        if( Math.abs(Globals.dDobHeading - actual_orientation[0])>5 )
	        	nCountDown = 100;
	        
	        if( nCountDown>0 )
	        	dScale = 0.8;
	        
	        Globals.dDobHeading = Globals.dDobHeading * dScale + actual_orientation[0] * (1-dScale);//dHdgSum / 100.;
	        
	        dScale = Globals.dScalePitch;
	        if( Math.abs(Globals.dDobPitch - actual_orientation[1])>5 )
	        	nCountDown = 100;
	        
	        if( nCountDown>0 )
	        	dScale = 0.8;                        	        
	        
	        Globals.dDobPitch   = Globals.dDobPitch * dScale + actual_orientation[1] * (1-dScale);//dPitchSum / 100.;
	        
	        if( Globals.dDobHeading > 360 )
	        	Globals.dDobHeading -= 360;
	        
	        if( Globals.dDobHeading < 0 )
	        	Globals.dDobHeading += 360;
	        
	        nCountDown--;
	        if( nCountDown < 0 )
	        	nCountDown = 0;
	       // Log.d("Debug", "Orientation (" + actual_orientation[0] + ", " + actual_orientation[1] + ", " + actual_orientation[2] + ")");
	    }

    }
}
