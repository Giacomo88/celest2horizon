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

import java.util.Calendar;

import java.lang.Math;
import android.util.Log;

public class Planets{
	
	//public static double pi = 3.14159265359;
	//public static double rads = 0.01745329252;
	
	public class params{
	        double i1;
	        double i2;
	        double o1;
	        double o2;
	        double p1;
	        double p2;
	        double a1;
	        double a2;
	        double e1;
	        double e2;
	        double l1;
	        double l2;
	        
	        public params(double di1, double di2,
			  			  double do1, double do2,
	        			  double dp1, double dp2,
	        			  double da1, double da2,
	        			  double de1, double de2,
	        			  double dl1, double dl2)
	        {
	        	i1 = di1;
	        	i2 = di2;
	        	o1 = do1;
	        	o2 = do2;
	        	p1 = dp1;
	        	p2 = dp2;
	        	a1 = da1;
	        	a2 = da2;
	        	e1 = de1;
	        	e2 = de2;
	        	l1 = dl1;
	        	l2 = dl2;
	        }
 			  
	};

    params planets[] = {   /*Mercury*/
            new params(7.00487, -0.000000178797,
             48.33167, -0.0000033942,
             77.45645, 0.00000436208,
             0.38709893, 0.0000000000180698,
             0.20563069, 0.000000000691855,
             252.25084, 4.092338796),

             /*Venus*/
             new params(3.39471, -0.0000000217507,
             76.68069, -0.0000075815,
             131.53298, -0.000000827439,
             0.72333199, 0.0000000000251882,
             0.00677323, -0.00000000135195,
             181.97973, 1.602130474),

            /*Earth*/
             new params(0.00005, -0.000000356985,
            -11.26064, -0.00013863,
            102.94719, 0.00000911309,
            1.00000011, -1.36893e-12,
            0.01671022, -0.00000000104148,
            100.46435, 0.985609101),

            /*Mars*/
            new params(1.85061, -0.000000193703,
            49.57854, - 7.758700000000001e-06,
            336.04084, 0.00001187,
            1.52366231, -0.000000001977,
            0.09341233, -0.00000000325859,
            355.45332,  0.524033035),

            /*Jupiter*/
            new params(1.3053, -0.0000000315613,
            100.55615, 9.256750000000001e-06,
            14.75385, 0.00000638779,
            5.20336301, 0.0000000166289,
            0.04839266, -0.00000000352635,
            34.40438, 8.308676199999999e-02),

            /*Saturn*/
            new params(2.48446, 0.0000000464674,
             113.71504, -0.0000121,
             92.43194, -0.0000148216,
             9.53707032, -8.255439999999999e-08,
             0.0541506, -0.0000000100649,
             49.94432, 0.033470629),

             /*Uranus*/
             new params(0.76986, -0.0000000158947,
             74.22987999999999, 0.0000127873,
             170.96424, 9.982200000000001e-06,
             19.19126393, 0.0000000416222,
             0.04716771, -0.00000000524298,
             313.23218, 0.011731294),

             /*Neptune*/
             new params(1.76917, -0.0000000276827,
              131.72169, -0.0000011503,
              44.97135, -0.00000642201,
              30.06896348, -0.0000000342768,
              8.585870000000001e-03, 0.000000000688296,
              304.88003, 0.0059810572),

              /*Pluto*/
              new params(17.14175, 8.418889999999999e-08,
              110.30347, -0.0000002839,
              224.06676, -0.00000100578,
              39.48168677, -0.0000000210574,
              0.24880766, 0.00000000177002,
              238.92881, 0.013831834)
          };
    
    

	MyDataStruct myPlanets[];
	
	public Planets(){
    //create planet array
    myPlanets = new MyDataStruct[9];
    myPlanets[0] = new MyDataStruct("Mercury","0h00.0","00 00","Planet","1");
    myPlanets[1] = new MyDataStruct("Venus","0h00.0","00 00","Planet","1");
    myPlanets[2] = new MyDataStruct("Earth","0h00.0","00 00","Planet","1");
    myPlanets[3] = new MyDataStruct("Mars","0h00.0","00 00","Planet","1");
    myPlanets[4] = new MyDataStruct("Jupiter","0h00.0","00 00","Planet","1");
    myPlanets[5] = new MyDataStruct("Saturn","0h00.0","00 00","Planet","1");
    myPlanets[6] = new MyDataStruct("Uranus","0h00.0","00 00","Planet","1");
    myPlanets[7] = new MyDataStruct("Neptune","0h00.0","00 00","Planet","1");
    myPlanets[8] = new MyDataStruct("Pluto","0h00.0","00 00","Planet","1");
  }
	
	
	String[] GetStrings()
	{
		String ret[] = new String[9];
		for(int i=0; i<9; i++)
			ret[i] = myPlanets[i].ID;
		
		return(ret);
	}
	
	String GetName(int pos){
		return(myPlanets[pos].Description);
	}
	
	String GetRA(int pos)
	{
		return(myPlanets[pos].RA);
	}
	
	String GetDEC(int pos)
	{
		return(myPlanets[pos].DEC);
	}
	
	void UpdateRADEC(Calendar now, int index)
	{
	    int y=now.get(Calendar.YEAR);
	    int m=now.get(Calendar.MONTH)+1;
	    int D=now.get(Calendar.DAY_OF_MONTH);
	    double UT=now.get(Calendar.HOUR_OF_DAY) + (now.get(Calendar.MINUTE)/60.);
	    
	    String deb = String.format("Update RADEC - Date->%04d - %02d - %02d - %f", y,m,D,UT);
	    Log.d("Debug", deb);
	    int d1 = (m+9)/12 + y;
	    int d2 = 7*d1 / 4;
	    int d3 = 275*m/9;

	    double d = 367*y - d2 + d3 + (D) - 730531.5;
	    d += UT/24.;
	    
	    //double ie = Math.toRadians(planets[2].i1 + planets[2].i2 * d);
	    //double oe = Math.toRadians(planets[2].o1 + planets[2].o2 * d);
	    double pe = Math.toRadians(planets[2].p1 + planets[2].p2 * d);
	    double ae = planets[2].a1 + planets[2].a2 * d;
	    double ee = planets[2].e1 + planets[2].e2 * d;
	    double le = FNrange(Math.toRadians(planets[2].l1 + planets[2].l2 * d));
	    
	    double ip,op,pp,ap,ep,lp;
	    
	    //for(int index = 0; index< 9; index++)
	    //{
	    	deb = String.format("Making planet %d", index);
	        Log.d("C2HDebugging", deb);
		    ip = Math.toRadians(planets[index].i1 + planets[index].i2 * d);
		    op = Math.toRadians(planets[index].o1 + planets[index].o2 * d);
		    pp = Math.toRadians(planets[index].p1 + planets[index].p2 * d);
		    ap = planets[index].a1 + planets[index].a2 * d;
		    ep = planets[index].e1 + planets[index].e2 * d;
		    lp = FNrange(Math.toRadians(planets[index].l1 + planets[index].l2 * d));
	    
		    Log.d("C2HDebugging", "Calling FNrange");
		    double me = FNrange(le - pe);
		    Log.d("C2HDebugging", "Calling FNkep");
		    double ve = FNkep(me, ee, 8/*12*/);
		    Log.d("C2HDebugging", "Calling cos");

		    double re = ae * (1 - ee * ee) / (1 + ee * Math.cos(ve));
		    double xe = re * Math.cos(ve + pe);
		    double ye = re * Math.sin(ve + pe);
		    //double ze = 0;
		    //
		    //   and position of planet in its orbit
		    //
		    Log.d("C2HDebugging", "Calling FNrange 2");

		    double mp = FNrange(lp - pp);
		    Log.d("C2HDebugging", "Calling FNkep 2 2");
		    
		    double vp = FNkep(mp, ep, 8/*12*/);
		    double rp = ap * (1 - ep * ep) / (1 + ep * Math.cos(vp));
		    //
		    //  heliocentric rectangular coordinates of planet
		    //
		    double xh = rp * (Math.cos(op) * Math.cos(vp + pp - op) - Math.sin(op) * Math.sin(vp + pp - op) * Math.cos(ip));
		    double yh = rp * (Math.sin(op) * Math.cos(vp + pp - op) + Math.cos(op) * Math.sin(vp + pp - op) * Math.cos(ip));
		    double zh = rp * (Math.sin(vp + pp - op) * Math.sin(ip));
		    //
		    //   convert to geocentric rectangular coordinates
		    //
		    double xg = xh - xe;
		    double yg = yh - ye;
		    double zg = zh;
		    //
		    //   rotate around x axis from ecliptic to equatorial coords
		    //
		    double ecl = Math.toRadians(23.429292); //value for J2000.0 frame
		    double xeq = xg;
		    double yeq = yg * Math.cos(ecl) - zg * Math.sin(ecl);
		    double zeq = yg * Math.sin(ecl) + zg * Math.cos(ecl);
		    //
		    //   find the RA and DEC from the rectangular equatorial coords
		    //
		    Log.d("C2HDebugging", "Calling atan2");

		    double ra = Math.atan2(yeq, xeq);
		    double dec = Math.atan(zeq / Math.sqrt(xeq * xeq + yeq * yeq));
		    Log.d("C2HDebugging", "Calling sqrt");
		    double rvec = Math.sqrt(zeq*zeq + xeq*xeq + yeq*yeq );
		    //printf("RA->%f\tDec->%f\n", ra*12/pi, dec*180/pi);
		    Log.d("C2HDebugging", "correcting ra");
		    ra *= 12 / Math.PI;
		    if( ra<0 )
		        ra += 24.;
	
		    int raHour = (int)ra;
		    double raMin = (ra - raHour)*60;
		    Log.d("C2HDebugging", "discreting dec");
		    dec *= 180/Math.PI;
		    int decDeg = (int)dec;
		    double decMin = Math.abs((dec - decDeg)*60);
		    
		    myPlanets[index].RA = String.format("%02dh%.1f", raHour, raMin);
		    myPlanets[index].DEC = String.format("%02d %02f", decDeg, decMin);
	        if( dec<0 && myPlanets[index].DEC.charAt(0) != '-' )
	        	 myPlanets[index].DEC = "-" + myPlanets[index].DEC;
	        
	        Log.d("C2HDebugging", myPlanets[index].RA + " " + myPlanets[index].DEC + " " + Double.toString(rvec));
	       
	    //}
	}
	
	double FNrange (double x){
	    double b = x / (2 * Math.PI);
	    double a = 2 * Math.PI * (b - FNipart(b));
	    if( a < 0 )
	        a = 2 * Math.PI + a;

	    return(a);
	}
	
	int FNipart (double x)
	{
	     //SGN(x) * INT(ABS(x))
	     int y=(int)x;
	     return(y);
	}

	double FNkep (double m, double ecc, double eps)
	{
	//
	//   returns the true anomaly given
	//   m - the mean anomaly in radians
	//   ecc - the eccentricity of the orbit
	//   eps - the convergence paramter (8 or 9 is usually fine
	//   12 or 14 for very accurate work)
	//
	    double e = m;
	    double delta = .05;
	    while( Math.abs(delta) >= Math.pow(10, -eps) )
	    {
	          delta = e - ecc * Math.sin(e) - m;
	          e = e - delta / (1 - ecc * Math.cos(e));
	    }
	    
	    double v = 2 * Math.atan(Math.pow((1 + ecc) / (1 - ecc) , .5) * Math.tan(.5 * e));
	    if( v < 0 )
	        v = v + 2*Math.PI;

	    return(v);
	}

}
