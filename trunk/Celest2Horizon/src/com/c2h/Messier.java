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

public class Messier{
	
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
	
	MyDataStruct myMessier[];
	
	public Messier(){
	    myMessier = new MyDataStruct[110];
	
	    myMessier[0] = new MyDataStruct("M1","05h34.5","22 01","Crab, supernova remnant","1");
	    myMessier[1] = new MyDataStruct("M2","21h33.5","-00 49","many faint stars","7");
	    myMessier[2] = new MyDataStruct("M3","13h42.2","28 23","excellant globular","7");
	    myMessier[3] = new MyDataStruct("M4","16h23.6","-26 32","beautiful star chains","6");
	    myMessier[4] = new MyDataStruct("M5","15h18.6","02 05","fine cluster","7");
	    myMessier[5] = new MyDataStruct("M6","17h40.1","-32 13","best at low power","6");
	    myMessier[6] = new MyDataStruct("M7","17h53.9","-34 49","good in binocs or finder","5");
	    myMessier[7] = new MyDataStruct("M8","18h03.8","-24 23","Lagoon, neb + cluster","6");
	    myMessier[8] = new MyDataStruct("M9","17h19.2","-18 31","compact","8");
	    myMessier[9] = new MyDataStruct("M10","16h57.1","-04 06","rich, M12 3 deg NW","7");
	    myMessier[10] = new MyDataStruct("M11","18h51.1","-06 16","very rich and compressed","6");
	    myMessier[11] = new MyDataStruct("M12","16h47.2","-01 57","loose for a globular","7");
	    myMessier[12] = new MyDataStruct("M13","16h41.7","36 28","Hercules Cl","6");
	    myMessier[13] = new MyDataStruct("M14","17h37.6","-03 15","needed to resolve","9");
	    myMessier[14] = new MyDataStruct("M15","21h30.0","12 10","rich, compact","7");
	    myMessier[15] = new MyDataStruct("M16","18h18.8","-13 47","Eagle Neb + cluster","7");
	    myMessier[16] = new MyDataStruct("M17","18h20.8","-16 11","Swan, use UHC filter","7");
	    myMessier[17] = new MyDataStruct("M18","18h19.9","-17 08","sparse cluster","7");
	    myMessier[18] = new MyDataStruct("M19","17h02.6","-26 16","oblate","8");
	    myMessier[19] = new MyDataStruct("M20","18h02.6","-23 02","Trifid, dark lanes","5");
	    myMessier[20] = new MyDataStruct("M21","18h04.6","-22 30","sparse cluster","7");
	    myMessier[21] = new MyDataStruct("M22","18h36.4","-23 54","spectacular","6");
	    myMessier[22] = new MyDataStruct("M23","17h56.8","-19 01","bright, loose","7");
	    myMessier[23] = new MyDataStruct("M24","18h16.9","-18 30","Small Star Cloud","5");
	    myMessier[24] = new MyDataStruct("M25","18h31.6","-19 15","bright but sparse","6");
	    myMessier[25] = new MyDataStruct("M26","18h45.2","-09 24","bright, coarse cluster","8");
	    myMessier[26] = new MyDataStruct("M27","19h59.6","22 43","Dumbbell, many * invl","8");
	    myMessier[27] = new MyDataStruct("M28","18h24.5","-24 52","compact globular","8");
	    myMessier[28] = new MyDataStruct("M29","20h23.9","38 32","small and poor","7");
	    myMessier[29] = new MyDataStruct("M30","21h40.4","-23 11","elliptical in shape","8");
	    myMessier[30] = new MyDataStruct("M31","00h41.8","41 16","Great Andromeda Galaxy","4");
	    myMessier[31] = new MyDataStruct("M32","00h42.8","40 52","close companion to M31","9");
	    myMessier[32] = new MyDataStruct("M33","01h33.9","30 39","large, diffuse","6");
	    myMessier[33] = new MyDataStruct("M34","02h42.0","42 47","best at low power","6");
	    myMessier[34] = new MyDataStruct("M35","06h08.9","24 20","NGC 2158 1/2 deg SW","6");
	    myMessier[35] = new MyDataStruct("M36","05h36.1","34 08","bright but scattered","6");
	    myMessier[36] = new MyDataStruct("M37","05h52.4","32 33","very rich, about 500*","6");
	    myMessier[37] = new MyDataStruct("M38","05h28.7","35 50","shaped like Greek Pi-?","7");
	    myMessier[38] = new MyDataStruct("M39","21h32.2","48 26","scattered, use low power","5");
	    myMessier[39] = new MyDataStruct("M40","12h22.4","58 05","DS Winnecke","9");
	    myMessier[40] = new MyDataStruct("M41","06h47.0","-20 44","150*, orange * in centr","6");
	    myMessier[41] = new MyDataStruct("M42","05h35.4","-05 27","Orion Neb, magnificent","6");
	    myMessier[42] = new MyDataStruct("M43","05h35.6","-05 16","detached part of M42","9");
	    myMessier[43] = new MyDataStruct("M44","08h40.1","19 59","Beehive, 200* to mag 14","4");
	    myMessier[44] = new MyDataStruct("M45","03h47.0","24 07","Pleiades, look for neby","2");
	    myMessier[45] = new MyDataStruct("M46","07h41.8","-14 49","Pl neb NGC 2438 at edge","7");
	    myMessier[46] = new MyDataStruct("M47","07h36.6","-14 30","coarse cluster","5");
	    myMessier[47] = new MyDataStruct("M48","08h13.8","-05 48","scattered group","6");
	    myMessier[48] = new MyDataStruct("M49","12h29.8","08 00","bright elliptical","9");
	    myMessier[49] = new MyDataStruct("M50","07h03.2","-08 20","between Sirius-Procyon","6");
	    myMessier[50] = new MyDataStruct("M51","13h30.0","47 11","Whirlpool, interacting","9");
	    myMessier[51] = new MyDataStruct("M52","23h24.2","61 35","200* mags 9...","8");
	    myMessier[52] = new MyDataStruct("M53","13h12.9","18 10","1 deg NE of Alpha Comae","8");
	    myMessier[53] = new MyDataStruct("M54","18h55.1","-30 29","not easily resolved","8");
	    myMessier[54] = new MyDataStruct("M55","19h40.0","-30 58","bright, loose globular","6");
	    myMessier[55] = new MyDataStruct("M56","19h16.6","30 11","in very rich star field","9");
	    myMessier[56] = new MyDataStruct("M57","18h53.6","33 02","Ring, 15 mag central*","9");
	    myMessier[57] = new MyDataStruct("M58","12h37.7","11 49","barred spiral","10");
	    myMessier[58] = new MyDataStruct("M59","12h42.0","11 39","pair with M60","10");
	    myMessier[59] = new MyDataStruct("M60","12h43.7","11 33","bright elliptical","10");
	    myMessier[60] = new MyDataStruct("M61","12h21.9","04 28","two-armed spiral","10");
	    myMessier[61] = new MyDataStruct("M62","17h01.2","-30 07","in rich field","7");
	    myMessier[62] = new MyDataStruct("M63","13h15.8","42 02","elongated in PA 105 deg","9");
	    myMessier[63] = new MyDataStruct("M64","12h56.7","21 41","Blackeye, dark marking","8");
	    myMessier[64] = new MyDataStruct("M65","11h18.9","13 05","elongated in PA 174 deg","10");
	    myMessier[65] = new MyDataStruct("M66","11h20.2","12 59","M66 + NGC 3628 nearby","9");
	    myMessier[66] = new MyDataStruct("M67","08h50.4","11 49","dark spot near center","7");
	    myMessier[67] = new MyDataStruct("M68","12h39.5","-26 45","needed to resolve","9");
	    myMessier[68] = new MyDataStruct("M69","18h31.4","-32 21","small, poor","8");
	    myMessier[69] = new MyDataStruct("M70","18h43.2","-32 18","small, 2 deg E of M69","8");
	    myMessier[70] = new MyDataStruct("M71","19h53.8","18 47","loose globular","8");
	    myMessier[71] = new MyDataStruct("M72","20h53.5","-12 32","3 deg from NGC 7009","9");
	    myMessier[72] = new MyDataStruct("M73","20h59.0","-12 38","4 stars; an asterism","9");
	    myMessier[73] = new MyDataStruct("M74","01h36.7","15 47","low surface brightness","10");
	    myMessier[74] = new MyDataStruct("M75","20h06.1","-21 55","small","9");
	    myMessier[75] = new MyDataStruct("M76","01h42.4","51 34","Little Dumbbell, tough","11");
	    myMessier[76] = new MyDataStruct("M77","02h42.7","-00 02","brightest in group","10");
	    myMessier[77] = new MyDataStruct("M78","05h46.7","00 03","reflection neb","8");
	    myMessier[78] = new MyDataStruct("M79","05h24.5","-24 33","needed to resolve","8");
	    myMessier[79] = new MyDataStruct("M80","16h17.0","-22 59","very compressed","8");
	    myMessier[80] = new MyDataStruct("M81","09h55.6","69 04","M82 1/2 deg N","7");
	    myMessier[81] = new MyDataStruct("M82","09h55.8","69 41","high power for detail","9");
	    myMessier[82] = new MyDataStruct("M83","13h37.0","-29 52","large and diffuse","8");
	    myMessier[83] = new MyDataStruct("M84","12h25.1","12 53","M86 + many NGC's nearby","10");
	    myMessier[84] = new MyDataStruct("M85","12h25.5","18 12","bright elliptical shape","10");
	    myMessier[85] = new MyDataStruct("M86","12h26.2","12 57","center of Virgo Cluster","10");
	    myMessier[86] = new MyDataStruct("M87","12h30.8","12 24","bright elliptical","10");
	    myMessier[87] = new MyDataStruct("M88","12h32.1","14 26","multi-arm spiral","10");
	    myMessier[88] = new MyDataStruct("M89","12h35.7","12 33","resembles M87","11");
	    myMessier[89] = new MyDataStruct("M90","12h36.8","13 10","bright spiral","10");
	    myMessier[90] = new MyDataStruct("M91","12h35.5","14 30","NGC 4571 @ 27'","11");
	    myMessier[91] = new MyDataStruct("M92","17h17.1","43 08","nice glob, overlooked","7");
	    myMessier[92] = new MyDataStruct("M93","07h44.6","-23 52","bright and rich","6");
	    myMessier[93] = new MyDataStruct("M94","12h50.9","41 08","bright nucleus","9");
	    myMessier[94] = new MyDataStruct("M95","10h44.0","11 42","bright barred spiral","10");
	    myMessier[95] = new MyDataStruct("M96","10h46.8","11 49","M95 in same field","10");
	    myMessier[96] = new MyDataStruct("M97","11h14.8","55 01","Owl, look for eyes","11");
	    myMessier[97] = new MyDataStruct("M98","12h13.9","14 55","* 6 Comae 30' west","10");
	    myMessier[98] = new MyDataStruct("M99","12h18.9","14 26","face-on spiral","10");
	    myMessier[99] = new MyDataStruct("M100","12h23.0","15 50","face-on, many supernovae","10");
	    myMessier[100] = new MyDataStruct("M101","14h03.2","54 21","excellant in dark skies","8");
	    myMessier[101] = new MyDataStruct("M102","15h06.5","55 46","M102=M101, NGC 5866 used","12");
	    myMessier[102] = new MyDataStruct("M103","01h33.2","60 42","40* mags 10...","7");
	    myMessier[103] = new MyDataStruct("M104","12h40.0","-11 37","Sombrero, dust lane","8");
	    myMessier[104] = new MyDataStruct("M105","10h47.8","12 35","near M95 and M96","10");
	    myMessier[105] = new MyDataStruct("M106","12h18.9","47 19","large, bright spiral","9");
	    myMessier[106] = new MyDataStruct("M107","16h32.5","-13 03","small glob","9");
	    myMessier[107] = new MyDataStruct("M108","11h11.5","55 40","edge-on, near M97","10");
	    myMessier[108] = new MyDataStruct("M109","11h57.6","53 23","barred spiral","10");
	    myMessier[109] = new MyDataStruct("M110","00h40.4","41 41","companion to M 31","8");
	}
	
	String[] GetStrings()
	{
		
        Log.v("Debug", "MyMessiers: Getting strings");
		String ret[] = new String[110];
		for(int i=0; i<110; i++)
			ret[i] = myMessier[i].ID + " : " + myMessier[i].Description + " (" + myMessier[i].Magnitude + ")";
		
        Log.v("Debug", "MyMessiers: returning strings");

		return(ret);
	}
	
	String[] GetRevStrings()
	{
		
        Log.v("Debug", "MyMessiers: Getting strings");
		String ret[] = new String[110];
		for(int i=109; i>=0; i--)
			ret[i] = myMessier[i].ID + " (" + myMessier[i].Magnitude + ")";
		
        Log.v("Debug", "MyMessiers: returning strings");

		return(ret);
	}

	String GetRA(int pos)
	{
		return(myMessier[pos].RA);
	}
	
	String GetDEC(int pos)
	{
		return(myMessier[pos].DEC);
	}
	
	String GetName(int pos){
		return(myMessier[pos].Description);
	}
	
	String GetMagnitude(int pos){
		return(myMessier[pos].Magnitude);
	}

}


