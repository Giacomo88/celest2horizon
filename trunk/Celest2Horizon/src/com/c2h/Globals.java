package com.c2h;

import android.content.Context;
import android.content.SharedPreferences;

public class Globals {
	
	static Context myContext;
	
    static double dLatitude = 0;
    static double dLongitude = 0;

    static double dDobHeading = 0;
    static double dDobPitch = 0;
    
    static double dTargetHeading = 0;
    static double dTargetPitch = 0;    
    
    static double dDobHeadingDelta = 0;
    static double dDobPitchDelta = 0;
    
    static double dScaleHeading = 0;
    static double dScalePitch = 0;
    
    static public void OnResume() {
        SharedPreferences settings = myContext.getSharedPreferences("Celest2Horizon", 0);
        dScaleHeading = settings.getFloat("ScaleHeading", (float) 0.99);        
        dScalePitch = settings.getFloat("ScalePitch", (float) 0.99);
        dDobHeadingDelta = settings.getFloat("DobHeadingDelta", (float) 0);
        dDobPitchDelta = settings.getFloat("DobPitchDelta", (float) 0);
    }
    
    static public void OnPause() {
        SharedPreferences settings = myContext.getSharedPreferences("Celest2Horizon", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("ScaleHeading", (float) dScaleHeading);
        editor.putFloat("ScalePitch", (float) dScalePitch);
        editor.putFloat("DobHeadingDelta", (float) dDobHeadingDelta);
        editor.putFloat("DobPitchDelta", (float) dDobPitchDelta);
        editor.commit();
    }

}
