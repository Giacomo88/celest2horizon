package com.c2h;

import android.util.Log;

public class Stats {

	double[] dValues;
	int nIndex;
	double dAverage;
	double dSigma;
	int nCount;
	
	public Stats() {
		nCount = 50;
		dValues = new double[nCount];
		
		Reset();
	}
	
	public void Reset() {
		for(int i=0; i<nCount; i++)
			dValues[i] = 0;
		
		nIndex = 0;
		dAverage = 0;
		dSigma = 0;
	}
	
	public void Add(double X) {
		dValues[nIndex++] = X;
		Log.d("Stat", "Adding " + X);
		if( nIndex == nCount ) {
			double dSum = 0;
			for(int i=0; i<nCount; i++ )
				dSum += dValues[i];
			
			dAverage = dSum / nCount;
			dSigma = 0;
			
			Log.d("Stat", "Avg " + dAverage + " Sum " + dSum);
			for(int i=0; i<nCount; i++) {
				dSigma += ((dValues[i] - dAverage)*(dValues[i] - dAverage));
			}
			dSigma = Math.sqrt(dSigma / nCount);
			
			Globals.dAverage = dAverage;
			Globals.dSigma = dSigma;
			
			Reset();
		}
	}
}