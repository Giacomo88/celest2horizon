package com.c2h;

import android.util.Log;

public class HighestObjs {
	
	MyDataStruct myClosestObjs[];
	
	public HighestObjs(){
		myClosestObjs = new MyDataStruct[110];

		for(int i=0; i<110; i++)
			myClosestObjs[i] = new MyDataStruct("C1","0h0.0","0 0","---","0");
	}
	
	String[] GetStrings()
	{
		String ret[] = new String[110];
		for(int i=0; i<110; i++)
			ret[i] = myClosestObjs[i].ID + " " + myClosestObjs[i].Description + " (" + myClosestObjs[i].Magnitude+")";
		
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
	
	void Add(MyDataStruct thisData) {
		for(int i=0; i<109; i++)
			myClosestObjs[i] = myClosestObjs[i+1];
		myClosestObjs[109] = thisData;
	}
	
	int FindString(String stringIn) {
				
		for(int i=0; i<110; i++) {
			String toCompare = myClosestObjs[i].ID + " " + myClosestObjs[i].Description + " (" + myClosestObjs[i].Magnitude+")";
			if( stringIn.equals(toCompare) )
				return i;
		}
		
		return -1;
	}
	
	void Sort() {
		int i, j;
		MyDataStruct t;
		  for(i = 0; i < 110; i++) {
			  for(j = 1; j < (110-i); j++){
				  if(myClosestObjs[j-1].dAltitude < myClosestObjs[j].dAltitude ) {
					  t = myClosestObjs[j-1];
					  myClosestObjs[j-1]=myClosestObjs[j];
					  myClosestObjs[j]=t;
				  }
			  }
		  }
	}
	
}
