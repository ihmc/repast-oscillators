package us.ihmc.simon.joscillators;

import repast.simphony.random.RandomHelper;

public class OscillatorUtils {

	static double nextAngle() {		
		return RandomHelper.nextDoubleFromTo(0.0, 360.0);
	}

	static double nextRadian() {
		return Math.toRadians(nextAngle());
	}

	static double nextFrequency() {
		return Model.MEAN_OSCILLATORY_FREQUENCY_IN_RADIANS +
				RandomHelper.nextDouble() *
				Model.MEAN_OSCILLATORY_FREQUENCY_STD_DEV_IN_RADIANS;
	}
	
	static double nextPhase() {
		return RandomHelper.nextDouble() * 2 * Math.PI;
	}

	static ContinuosPoint toPoint(Oscillator o) {
		return toPoint(o, JOscillatorsBuilder.GUI.RADIOUS, JOscillatorsBuilder.GUI.PADDING);
	}

	static ContinuosPoint toPoint(Oscillator o, float radious, float padding) {
		return new ContinuosPoint (
				radious + padding + (radious * Math.cos(o.getAngle())),
				radious + padding + (radious * Math.sin(o.getAngle()))
		); 
	}
}
