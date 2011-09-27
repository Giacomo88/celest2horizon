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
		Log.d("Debug", "Creating userobjects from ("+strFile+")");
		
		//create BufferedReader to read csv file
		BufferedReader br = new BufferedReader( new FileReader(strFile));
		String strLine = "";
		Log.d("Debug", "File opened ("+strFile+")");
		NumberLines = 0;		
		//read comma separated file line by line
		try {
			//Log.d("Debug", "Reading line " + NumberLines);
			while( (strLine = br.readLine()) != null) {
				NumberLines++;
				//Log.d("Debug", "Parsing line");
				String entries[] = strLine.split(",", 5);
				//Log.d("Debug", "Creating user object");
				MyObjects[0] = new MyDataStruct(entries);
			}
		} catch (IOException e) {
			Log.d("Debug", "Error (1) reading user file");
			e.printStackTrace();
		}
		
		MyObjects = (MyDataStruct[])resizeArray(MyObjects, NumberLines);
		NumberLines = 0;
		try {
			br.close();
		} catch (IOException e1) {
			Log.d("Debug", "Error closing user file");
			e1.printStackTrace();
		}
		br = new BufferedReader( new FileReader(strFile));
		
		try {
			while( (strLine = br.readLine()) != null) {
				//lineNumber++;
				String entries[] = strLine.split(",", 5);
				
				MyObjects[NumberLines] = new MyDataStruct(entries);
				/*Log.d("Debug", "ID->"+MyObjects[lineNumber].ID);
				Log.d("Debug", "RA->"+MyObjects[lineNumber].RA);
				Log.d("Debug", "Dec->"+MyObjects[lineNumber].DEC);
				Log.d("Debug", "Desc->"+MyObjects[lineNumber].Description);
				Log.d("Debug", "Mag->"+MyObjects[lineNumber].Magnitude);*/
				NumberLines++;
			}
			
		} catch (IOException e) {
			Log.d("Debug", "Error (2) reading user file");
			e.printStackTrace();
		}
		Log.d("Debug", "MyObect count " + NumberLines);
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
        Log.d("Debug", "My Objects: Getting strings");
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

