/*   Celest2Horizon - Converts RA / Dec to Altitude / Azimuth on Droid Phones
    Copyright (C) 2010  F. A. Willis

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.c2h;

import android.util.Log;

public class Stars {
	
	public class MyDataStruct{
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
	}
	
	MyDataStruct myStars[];
	
	public Stars(){
		myStars = new MyDataStruct[70];

		myStars[0] = new MyDataStruct("Achernar","1h37.7","-57 14","Achernar","0.45");
		myStars[1] = new MyDataStruct("Acrux","12h26.6","-63 6","Acrux","0.77");
		myStars[2] = new MyDataStruct("Adhara","6h58.6","-28 58","Adhara","1.5");
		myStars[3] = new MyDataStruct("Alcyone","3h47.5","24 6","Alcyone","2.85");
		myStars[4] = new MyDataStruct("Aldebaran","4h35.9","16 31","Aldebaran","0.85");
		myStars[5] = new MyDataStruct("Alderamin","21h18.6","62 35","Alderamin","2.44");
		myStars[6] = new MyDataStruct("Algenib","0h13.2","15 11","Algenib","2.83");
		myStars[7] = new MyDataStruct("Algieba","10h20.0","19 51","Algieba","2.01");
		myStars[8] = new MyDataStruct("Algol","3h8.2","40 57","Algol","2.09");
		myStars[9] = new MyDataStruct("Alhena","6h37.7","16 24","Alhena","1.93");
		myStars[10] = new MyDataStruct("Alioth","12h54.0","55 58","Alioth","1.76");
		myStars[11] = new MyDataStruct("Alkaid","13h47.5","49 19","Alkaid","1.85");
		myStars[12] = new MyDataStruct("Alnair","22h8.2","-46 58","Alnair","1.74");
		myStars[13] = new MyDataStruct("Alnath","5h26.3","28 36","Alnath","1.65");
		myStars[14] = new MyDataStruct("Alnilam","5h36.2","-1 12","Alnilam","1.69");
		myStars[15] = new MyDataStruct("Alnitak","5h40.8","-1 57","Alnitak","1.74");
		myStars[16] = new MyDataStruct("Alphard","9h27.6","-8 40","Alphard","1.99");
		myStars[17] = new MyDataStruct("Alphekka","15h34.7","26 43","Alphekka","2.22");
		myStars[18] = new MyDataStruct("Alpheratz","0h8.4","29 5","Alpheratz","2.07");
		myStars[19] = new MyDataStruct("Altair","19h50.8","8 52","Altair","0.77");
		myStars[20] = new MyDataStruct("Ankaa","0h26.3","-42 18","Ankaa","2.4");
		myStars[21] = new MyDataStruct("Antares","16h29.4","-26 26","Antares","1.06");
		myStars[22] = new MyDataStruct("Arcturus","14h15.7","19 11","Arcturus","-0.1");
		myStars[23] = new MyDataStruct("Arneb","5h32.7","-17 49","Arneb","2.58");
		myStars[24] = new MyDataStruct("Bellatrix","5h25.1","6 21","Bellatrix","1.64");
		myStars[25] = new MyDataStruct("Betelgeuse","5h55.2","7 24","Betelgeuse","0.45");
		myStars[26] = new MyDataStruct("Canopus","6h24.0","-52 42","Canopus","-0.62");
		myStars[27] = new MyDataStruct("Capella","5h16.7","45 60","Capella","0.71");
		myStars[28] = new MyDataStruct("Caph","0h9.2","59 9","Caph","2.27");
		myStars[29] = new MyDataStruct("Castor","7h34.6","31 53","Castor","1.94");
		myStars[30] = new MyDataStruct("Deneb","20h41.4","45 17","Deneb","1.25");
		myStars[31] = new MyDataStruct("Denebola","11h49.1","14 34","Denebola","2.14");
		myStars[32] = new MyDataStruct("Diphda","0h43.6","-17 59","Diphda","2.04");
		myStars[33] = new MyDataStruct("Dubhe","11h3.7","61 45","Dubhe","1.81");
		myStars[34] = new MyDataStruct("Enif","21h44.2","9 53","Enif","2.38");
		myStars[35] = new MyDataStruct("Etamin","17h56.6","51 29","Etamin","2.24");
		myStars[36] = new MyDataStruct("Fomalhaut","22h57.6","-29 37","Fomalhaut","1.16");
		myStars[37] = new MyDataStruct("Hadar","14h3.8","-60 22","Hadar","0.61");
		myStars[38] = new MyDataStruct("Hamal","2h7.2","23 28","Hamal","2");
		myStars[39] = new MyDataStruct("Izar","14h45.0","27 4","Izar","2.35");
		myStars[40] = new MyDataStruct("Kaus Australis","18h24.2","-34 23","Kaus Australis","1.79");
		myStars[41] = new MyDataStruct("Kochab","14h50.7","74 9","Kochab","2.07");
		myStars[42] = new MyDataStruct("Markab","23h4.8","15 12","Markab","2.49");
		myStars[43] = new MyDataStruct("Menkar","3h2.3","4 5","Menkar","2.54");
		myStars[44] = new MyDataStruct("Merak","11h1.8","56 23","Merak","2.37");
		myStars[45] = new MyDataStruct("Mirach","1h9.7","35 37","Mirach","2.05");
		myStars[46] = new MyDataStruct("Mirphak","3h24.3","49 52","Mirphak","1.79");
		myStars[47] = new MyDataStruct("Mizar","13h23.9","54 56","Mizar","2.25");
		myStars[48] = new MyDataStruct("Nihal","5h28.2","-20 46","Nihal","2.81");
		myStars[49] = new MyDataStruct("Nunki","18h55.3","-26 18","Nunki","2.05");
		myStars[50] = new MyDataStruct("Phad","11h53.8","53 42","Phad","2.41");
		myStars[51] = new MyDataStruct("Polaris","2h31.8","89 16","Polaris","1.97");
		myStars[52] = new MyDataStruct("Pollux","7h45.3","28 2","Pollux","1.14");
		myStars[53] = new MyDataStruct("Procyon","7h39.3","5 14","Procyon","0.38");
		myStars[54] = new MyDataStruct("Rasalgethi","17h14.6","14 23","Rasalgethi","2.78");
		myStars[55] = new MyDataStruct("Rasalhague","17h34.9","12 34","Rasalhague","2.08");
		myStars[56] = new MyDataStruct("Regulus","10h8.4","11 58","Regulus","1.36");
		myStars[57] = new MyDataStruct("Rigel","5h14.5","-8 12","Rigel","0.18");
		myStars[58] = new MyDataStruct("Rigel Kentaurus A","14h39.7","-60 50","Rigel Kentaurus A","0.01");
		myStars[59] = new MyDataStruct("Rigel Kentaurus B","14h39.7","-60 50","Rigel Kentaurus B","1.34");
		myStars[60] = new MyDataStruct("Saiph","5h47.8","-9 40","Saiph","2.07");
		myStars[61] = new MyDataStruct("Scheat","23h3.8","28 5","Scheat","2.44");
		myStars[62] = new MyDataStruct("Shaula","17h33.6","-37 6","Shaula","1.62");
		myStars[63] = new MyDataStruct("Shedir","0h40.5","56 32","Shedir","2.24");
		myStars[64] = new MyDataStruct("Sirius","6h45.2","-16 43","Sirius","-1.43");
		myStars[65] = new MyDataStruct("Spica","13h25.2","-11 10","Spica","0.98");
		myStars[66] = new MyDataStruct("Tarazed","19h46.3","10 37","Tarazed","2.72");
		myStars[67] = new MyDataStruct("Unukalhai","15h44.3","6 26","Unukalhai","2.64");
		myStars[68] = new MyDataStruct("Vega","18h36.9","38 47","Vega","0.03");
		myStars[69] = new MyDataStruct("Vindemiatrix","13h2.2","10 58","Vindemiatrix","2.85");
	}
	
	String[] GetStrings()
	{
		
        Log.v("C2HDebugging", "MyStars: Getting strings");
		String ret[] = new String[70];
		for(int i=0; i<70; i++)
			ret[i] = myStars[i].ID+" ("+myStars[i].Magnitude+")";
		
		return(ret);
	}
	
	String GetRA(int pos)
	{
		return(myStars[pos].RA);
	}
	
	String GetDEC(int pos)
	{
		return(myStars[pos].DEC);
	}
	
	String GetName(int pos){
		return(myStars[pos].Description);
	}
}
