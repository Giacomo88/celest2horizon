package com.c2h;

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
