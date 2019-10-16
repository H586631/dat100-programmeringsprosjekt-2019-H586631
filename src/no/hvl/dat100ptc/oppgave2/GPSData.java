package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		gpspoints = new GPSPoint[n];
		antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		// punktet settes inn i tabellen i posisjon "antall" og returnerer boolean true
		// om det er plass.
		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			inserted = true;
			antall++;
		}

		return inserted;

	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// bruker metoden convert til å sette inn konvertert GPSpunkt data i et
		// gpspoint-objekt
		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);

		boolean inserted = insertGPS(gpspoint);

		return inserted;

	}

	public void print() {
		
		//skriver ut konvertert GPS data ved hjelp av utvidet for-løkke. 
		System.out.println("====== Konvertert GPS Data - START ======");

		for (GPSPoint gpspoint : gpspoints) {
			System.out.println(gpspoint.toString());
		}

		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
