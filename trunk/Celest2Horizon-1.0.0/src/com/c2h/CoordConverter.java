package com.c2h;


public class CoordConverter {

	public double RAString2Double(String ra_in) {
		String delims = "h";
		String[] ra_str = ra_in.split(delims);
		if( ra_str.length<2 )
	        return 0;
		
		return ra2real(Integer.parseInt(ra_str[0]) , Double.parseDouble(ra_str[1]));
	}	
	
	public double DecString2Double(String dec_in) {
		String delims = " ";
		String[] dec_str = dec_in.split(delims);
		if( dec_str.length<2 )
	        return 0;
		
		return dms2real(Integer.parseInt(dec_str[0]) , Double.parseDouble(dec_str[1]));
	}
	
    // convert right ascension (hours, minutes) to degrees as real
    public double ra2real( int hr, double min )
    {
        return 15.*(hr + min/60.);
    }
    
    // convert angle (deg, min) to degrees as real
    public double dms2real( int deg, double min )
    {
        double rv;

        if (deg < 0)
            rv = deg - min/60.;
        else
            rv = deg + min/60.;

        return rv;
    }
    
}
