package us.ihmc.simon.joscillators;

import cern.jet.random.Normal;
import repast.simphony.random.RandomHelper;

public class Noise {

	private final Normal normal;
	private final double dbsStrength;

	Noise() {
		this(0.0, 0.0);
	}

	Noise(double mean, double standardDeviation) {
		this(mean, standardDeviation, 1.0);
	}

	Noise(double mean, double standardDeviation, double strenghtInDecibels) {
		this.normal = RandomHelper.createNormal(mean, standardDeviation);
		this.dbsStrength = strenghtInDecibels;
	}

	double get() {
		return dbsStrength * normal.nextDouble();
	}
}
