package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	private static int TIME_STARTINDEX = 11;

	public static int toSeconds(String timestr) {

		// henter ut variabler basert på plassering i timetr ved hjelp av .substring
		int hr = Integer.parseInt(timestr.substring(TIME_STARTINDEX, 13));
		int min = Integer.parseInt(timestr.substring(14, 16));
		int sec = Integer.parseInt(timestr.substring(17, 19));

		// beregner totale sekunder
		int secs = (hr * 60 * 60) + (min * 60) + sec;

		return secs;

	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;

		double lat = Double.parseDouble(latitudeStr);
		double lon = Double.parseDouble(longitudeStr);
		double elev = Double.parseDouble(elevationStr);

		// legger inn konverterte variabler i gpspoint-objektet.
		gpspoint = new GPSPoint(toSeconds(timeStr), lat, lon, elev);

		return gpspoint;

	}

}
