package com.c2h;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.Button;


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
