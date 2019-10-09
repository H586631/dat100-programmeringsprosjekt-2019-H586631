package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // startindex for tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int hr = Integer.parseInt(timestr.substring(TIME_STARTINDEX,13));
		int min = Integer.parseInt(timestr.substring(14,16));
		int sec = Integer.parseInt(timestr.substring(17,19));
		
		int secs =  (hr * 60 * 60) + (min * 60) + sec;
		
		return secs;

		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		double lat = Double.parseDouble(latitudeStr);
		double lon = Double.parseDouble(longitudeStr);
		double elev = Double.parseDouble(elevationStr);
		
		gpspoint = new GPSPoint(toSeconds(timeStr), lat, lon, elev);
		
		return gpspoint;

		 
	    
	}
	
}
