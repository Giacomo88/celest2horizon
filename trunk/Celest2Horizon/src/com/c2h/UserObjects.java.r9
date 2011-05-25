package com.c2h;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.c2h.Messier.MyDataStruct;

import android.util.Log;

public class UserObjects {

	MyDataStruct MyObjects[];
	int NumberLines;
	
	public class MyDataStruct {
		String ID;
		String RA;
		String DEC;
		String Description;
		String Magnitude;
			
		public MyDataStruct(String s1, String s2, String s3, String s4, String s5){
			ID=s1; 
			RA=s2;
			DEC=s3;
			Description=s4;
			Magnitude=s5;
		}
		
		public MyDataStruct(String s[]) {
			ID=s[0];
			RA=s[1];
			DEC=s[2];
			Description=s[3];
			Magnitude=s[4];
		}

	}
	
	public UserObjects() throws FileNotFoundException {
			
		Log.v("Debug", "Creating userobjects");
		MyObjects = new MyDataStruct[1];
		
		String strFile = "//sdcard//Celest2Horizon//UserObjects.csv";
		
		//create BufferedReader to read csv file
		BufferedReader br = new BufferedReader( new FileReader(strFile));
		String strLine = "";

		NumberLines = 0;		
		//read comma separated file line by line
		try {
			while( (strLine = br.readLine()) != null) {
				NumberLines++;
				String entries[] = strLine.split(",", 5);
				
				MyObjects[0] = new MyDataStruct(entries);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MyObjects = (MyDataStruct[])resizeArray(MyObjects, NumberLines);
		NumberLines = 0;
		try {
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
	
	String[] GetStrings()
	{
        Log.v("Debug", "My Objects: Getting strings");
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
 