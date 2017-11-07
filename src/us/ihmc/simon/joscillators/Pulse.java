package us.ihmc.simon.joscillators;

class Pulse {

	private final double dbsStrength;

	Pulse(double strenghtInDecibels) {
		dbsStrength = strenghtInDecibels;
	}

	double get(Oscillator oscillator) {
		return dbsStrength * oscillator.strength * (-Math.sin(oscillator.phase));
	}
}
