package com.c2h;

import android.util.Log;

public class ClosestObjs {
	
	MyDataStruct myClosestObjs[];
	
	public ClosestObjs(){
		myClosestObjs = new MyDataStruct[10];

		myClosestObjs[0] = new MyDataStruct("C1","0h0.0","0 0","---","0");
		myClosestObjs[1] = new MyDataStruct("C2","0h0.0","0 0","---","0");
		myClosestObjs[2] = new MyDataStruct("C3","0h0.0","0 0","---","0");
		myClosestObjs[3] = new MyDataStruct("C4","0h0.0","0 0","---","0");
		myClosestObjs[4] = new MyDataStruct("C5","0h0.0","0 0","---","0");
		myClosestObjs[5] = new MyDataStruct("C6","0h0.0","0 0","---","0");
		myClosestObjs[6] = new MyDataStruct("C7","0h0.0","0 0","---","0");
		myClosestObjs[7] = new MyDataStruct("C8","0h0.0","0 0","---","0");
		myClosestObjs[8] = new MyDataStruct("C9","0h0.0","0 0","---","0");
		myClosestObjs[9] = new MyDataStruct("C10","0h0.0","0 0","---","0");
	}
	
	String[] GetStrings()
	{
		
        Log.v("Debug", "MyClosestObjs: Getting strings");
		String ret[] = new String[10];
		for(int i=0; i<10; i++)
			ret[i] = myClosestObjs[i].ID + myClosestObjs[i].Description + " (" + myClosestObjs[i].Magnitude+")";
		
		return(ret);
	}
	
	String GetRA(int pos)
	{
		return(myClosestObjs[pos].RA);
	}
	
	String GetDEC(int pos)
	{
		return(myClosestObjs[pos].DEC);
	}
	
	String GetName(int pos){
		return(myClosestObjs[pos].Description);
	}
	
	public void Add(MyDataStruct thisData) {
		for(int i=0; i<9; i++)
			myClosestObjs[i] = myClosestObjs[i+1];
		myClosestObjs[9] = thisData;
		
	}
}
