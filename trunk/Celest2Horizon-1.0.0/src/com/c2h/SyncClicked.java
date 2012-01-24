package com.c2h;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SyncClicked implements OnClickListener {

    @Override
    public void onClick(View v) {
    	Globals.dDobHeadingDelta = (Globals.dDobHeading - Globals.dTargetHeading);
    	Globals.dDobPitchDelta   = (Globals.dDobPitch - Globals.dTargetPitch);
    	Log.d("Debug", "Syncing " + Globals.dDobHeading + "," + Globals.dTargetHeading);
    	Log.d("Debug", "Delta " + Globals.dDobHeadingDelta);
    }
}