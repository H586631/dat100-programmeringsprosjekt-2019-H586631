package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	public double totalDistance() {

		double distance = 0;

		// beregner total distanse ved å legge sammen distansen mellom alle punkt i
		// løypen
		for (int i = 0; i < gpspoints.length - 1; i++) {

			distance = distance + GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}

		return distance;

	}

	public double totalElevation() {

		double elevation = 0;

		// legger sammen høydedifferansen mellom punktene, gitt at det er en positiv
		// høydeforskjell
		for (int i = 0; i < gpspoints.length - 1; i++) {
			if (gpspoints[i + 1].getElevation() > gpspoints[i].getElevation()) {
				elevation += (gpspoints[i + 1].getElevation() - gpspoints[i].getElevation());
			}
		}

		return elevation;

	}

	public int totalTime() {

		int time = 0;

		// legger sammen tidsdifferansen mellom punktene i tabellen
		for (int i = 0; i < gpspoints.length - 1; i++) {
			time += (gpspoints[i + 1].getTime() - gpspoints[i].getTime());
		}

		return time;

	}

	public double[] speeds() {
		// oppretter en ny tabell for å lagre hastigheter
		double[] speeds = new double[gpspoints.length - 1];

		// lagrer hastigheter i tabellen ved hjelp av .speed-metoden.
		for (int i = 0; i < speeds.length; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}

		return speeds;

	}

	public double maxSpeed() {

		double maxspeed = 0;
		double[] speed = speeds();

		// bruker findMax på tabellen som har lagret hastigheter
		maxspeed = GPSUtils.findMax(speed);
		return maxspeed;

	}

	public double averageSpeed() {

		double average = 0;
		double distance = totalDistance();
		double time = totalTime();

		// beregner snittfart ved hjelp av total distanse og total tid.
		average = (distance / time) * 3.6;

		return average;

	}

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;

		// if-setninger for å finne aktuell met-verdi
		if (speedmph <= 10) {
			met = 4.0;
		} else if (speedmph > 10 && speedmph <= 12) {
			met = 6.0;
		} else if (speedmph > 12 && speedmph <= 14) {
			met = 8.0;
		} else if (speedmph > 14 && speedmph <= 16) {
			met = 10.0;
		} else if (speedmph > 16 && speedmph <= 20) {
			met = 12.0;
		} else {
			met = 16.0;
		}

		// beregner kaloribruk basert på aktuell met, tid og vekt
		kcal = weight * met * (secs / 3600.0);
		return kcal;

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// beregner total kaloribruk
		totalkcal = kcal(weight, totalTime(), averageSpeed());
		return totalkcal;

	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		// skriver ut alle nøkkeltall

		System.out.println("==============================================");

		System.out.println("Total Time     :" + GPSUtils.formatTime(totalTime()));
		System.out.println("Total Distance :" + GPSUtils.formatDouble(totalDistance() / 1000.0) + " km");
		System.out.println("Total Elevation:" + GPSUtils.formatDouble(totalElevation()) + " m");
		System.out.println("Max Speed      :" + GPSUtils.formatDouble(maxSpeed()) + " km/t");
		System.out.println("Average speed  :" + GPSUtils.formatDouble(averageSpeed()) + " km/t");
		System.out.println("Energy:        :" + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");

		System.out.println("==============================================");

	}

}
