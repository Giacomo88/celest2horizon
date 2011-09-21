package com.c2h;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class SkyView extends View {

	// X/Y in degrees
	// width of map = 2*width in degrees
	
	double dCenterX = 0, dCenterY = 0, dWidth = 1;
	
	public SkyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		canvas.drawText("SkyView", 10, 50, paint);

	}
}
