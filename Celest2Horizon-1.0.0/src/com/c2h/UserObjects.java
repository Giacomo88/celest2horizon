package com.c2h;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import android.util.Log;

public class UserObjects {

	MyDataStruct MyObjects[];
	int NumberLines;
	
	public UserObjects() throws FileNotFoundException {
			
		MyObjects = new MyDataStruct[1];

		Globals.GetPath();
		String strFile = Globals.strUserPath;
		Log.v("Debug", "Creating userobjects from ("+strFile+")");
		
		//create BufferedReader to read csv file
		BufferedReader br = new BufferedReader( new FileReader(strFile));
		String strLine = "";
		Log.v("Debug", "File opened ("+strFile+")");
		NumberLines = 0;		
		//read comma separated file line by line
		try {
			//Log.v("Debug", "Reading line " + NumberLines);
			while( (strLine = br.readLine()) != null) {
				NumberLines++;
				//Log.v("Debug", "Parsing line");
				String entries[] = strLine.split(",", 5);
				//Log.v("Debug", "Creating user object");
				MyObjects[0] = new MyDataStruct(entries);
			}
		} catch (IOException e) {
			Log.v("Debug", "Error (1) reading user file");
			e.printStackTrace();
		}
		
		MyObjects = (MyDataStruct[])resizeArray(MyObjects, NumberLines);
		NumberLines = 0;
		try {
			br.close();
		} catch (IOException e1) {
			Log.v("Debug", "Error closing user file");
			e1.printStackTrace();
		}
		br = new BufferedReader( new FileReader(strFile));
		
		try {
			while( (strLine = br.readLine()) != null) {
				//lineNumber++;
				String entries[] = strLine.split(",", 5);
				
				MyObjects[NumberLines] = new MyDataStruct(entries);
				/*Log.v("Debug", "ID->"+MyObjects[lineNumber].ID);
				Log.v("Debug", "RA->"+MyObjects[lineNumber].RA);
				Log.v("Debug", "Dec->"+MyObjects[lineNumber].DEC);
				Log.v("Debug", "Desc->"+MyObjects[lineNumber].Description);
				Log.v("Debug", "Mag->"+MyObjects[lineNumber].Magnitude);*/
				NumberLines++;
			}
			
		} catch (IOException e) {
			Log.v("Debug", "Error (2) reading user file");
			e.printStackTrace();
		}
		Log.v("Debug", "MyObect count " + NumberLines);
	}
	
	private static Object resizeArray (Object oldArray, int newSize) {
		   int oldSize = java.lang.reflect.Array.getLength(oldArray);
		   Class elementType = oldArray.getClass().getComponentType();
		   Object newArray = java.lang.reflect.Array.newInstance(
		         elementType,newSize);
		   int preserveLength = Math.min(oldSize,newSize);
		   if (preserveLength > 0)
		      System.arraycopy (oldArray,0,newArray,0,preserveLength);
		   return newArray; 
	}
	
	public String[] GetStrings() {
        Log.v("Debug", "My Objects: Getting strings");
        if( MyObjects.length < 1 )
        	return null;
        
		String ret[] = new String[MyObjects.length];
		for(int i=0; i<MyObjects.length; i++)
			ret[i] = MyObjects[i].ID+" ("+MyObjects[i].Magnitude+")";
		
		return(ret);
	}
	
	String GetRA(int pos)
	{
		return(MyObjects[pos].RA);
	}
	
	String GetDEC(int pos)
	{
		return(MyObjects[pos].DEC);
	}
	
	String GetName(int pos){
		return(MyObjects[pos].Description);
	}
}

