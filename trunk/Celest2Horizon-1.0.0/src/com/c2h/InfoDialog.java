package com.c2h;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.EditText;

public class InfoDialog extends Dialog {
 
    public InfoDialog(Context context) {
        super(context);
        /** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /** Design the dialog in main.xml file */
        setContentView(R.layout.info);

        EditText info = (EditText) findViewById(R.id.editTextInfo);
        String strInfo = "Lat -> "+Double.toString(Globals.dLatitude)+"\r\n";
        strInfo += "Lon -> "+Double.toString(Globals.dLongitude)+"\r\n";
        
        strInfo += "Az Sync  -> "+Double.toString(Globals.dDobHeadingDelta)+"\r\n";
        strInfo += "Alt Sync -> "+Double.toString(Globals.dDobPitchDelta)+"\r\n";
        
        info.setText(strInfo);
    }
}