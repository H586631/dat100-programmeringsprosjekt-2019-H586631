package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {

	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;

	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length - 1; // number of data points

		makeWindow("Speed profile", 2 * MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);

		showSpeedProfile(MARGIN + BARHEIGHT, N);
	}

	public void showSpeedProfile(int ybase, int N) {

		// timescaling styrer hvor fort diagrammet visualiseres
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));

		double[] hastigheter = gpscomputer.speeds();

		int xslutt = 0;
		
		//tegner linjer som illustrerer hastigheten i mellom punktene
		//beregner også hvor diagrammet slutter for å brukes senere
		for (int i = 0; i < N; i++) {
			int hastighet = (int) hastigheter[i];
			setColor(0, 0, 255);
			drawLine(MARGIN + i * 2, ybase, MARGIN + i * 2, ybase - hastighet);
			pause(timescaling);
			xslutt = MARGIN + i * 2;
		}
		
		//finner yverdien til snittfart linjen
		int y = ybase - (int) gpscomputer.averageSpeed();

		setColor(0, 255, 0);
		drawLine(MARGIN, y, xslutt, y);

	}
}
