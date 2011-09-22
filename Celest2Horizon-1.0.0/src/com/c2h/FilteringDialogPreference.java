package com.c2h;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

public class FilteringDialogPreference extends DialogPreference {

	public FilteringDialogPreference(Context context, AttributeSet attrs) {
		super(context, attrs);

		setDialogLayoutResource(R.layout.locationpreference);

	}

	public FilteringDialogPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}


}