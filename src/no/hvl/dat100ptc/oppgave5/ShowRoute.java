package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 700;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);

		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {

		double ystep;

		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));

		return ystep;

	}

	public void showRouteMap(int ybase) {

		double[] lats = GPSUtils.getLatitudes(gpspoints);
		double[] lons = GPSUtils.getLongitudes(gpspoints);
		
		//finner breddegrad for punktet som tilsvarer ybase
		double min = GPSUtils.findMin(lats);
		
		//tegner punkt og linjer mellom punktene
		//bruker xstep og ystep for å finne ut hvor mange pixler mellom hvert punkt
		for (int i = 0; i < gpspoints.length; i++) {
			pause(50);
			double lon1 = MARGIN + (lons[i] * xstep() - lons[0] * xstep());
			double lat1 = ybase - (lats[i] * ystep() - min * ystep());

			if (i < gpspoints.length - 1) {
				double lon2 = MARGIN + (lons[i + 1] * xstep() - lons[0] * xstep());
				double lat2 = ybase - (lats[i + 1] * ystep() - min * ystep());
				setColor(0, 255, 0);
				drawLine((int) lon1, (int) lat1, (int) lon2, (int) lat2);
			}
			setColor(0, 255, 0);
			fillCircle((int) lon1, (int) lat1, 2);
		}

	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 17);

		// sikkert en bedre måte å gjøre det på
		drawString("Total Time     :" + GPSUtils.formatTime(gpscomputer.totalTime()), TEXTDISTANCE, TEXTDISTANCE);
		drawString("Total Distance :" + GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000.0) + " km",
				TEXTDISTANCE, TEXTDISTANCE * 2);
		drawString("Total Elevation:" + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", TEXTDISTANCE,
				TEXTDISTANCE * 3);
		drawString("Max Speed      :" + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t", TEXTDISTANCE,
				TEXTDISTANCE * 4);
		drawString("Average speed  :" + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t", TEXTDISTANCE,
				TEXTDISTANCE * 5);
		drawString("Energy:        :" + GPSUtils.formatDouble(gpscomputer.totalKcal(80.0)) + " kcal", TEXTDISTANCE,
				TEXTDISTANCE * 6);

	}

	public void playRoute(int ybase) {

		// TODO - START

		// TODO - SLUTT
	}

}
