package com.example.dna.service;

public class Utl {
	
	public static double round(double nmb, int dec) {
		double bas = Math.pow(10,dec);
		double ntg = Math.floor(nmb);
		double rsl = Math.round((nmb-ntg)*bas);
		rsl = (rsl/bas)+ntg;
		return(rsl);
	}

}
