package us.ihmc.simon.joscillators;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.continuous.ContinuousSpace;

class CumulativePhase {
	final double mean;
	final double divergence;
	
	private static double _currCount = -1.0;
	private static CumulativePhase _currCumulativePhase = null;
	
	CumulativePhase(double mean, double divergence) {
		this.mean = mean;
		this.divergence = divergence;
	}
	
	static CumulativePhase getCurrentPhase(ContinuousSpace<Oscillator> space) {
		double count = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		if (_currCumulativePhase == null || _currCount < count) {
			_currCumulativePhase = OscillatorUtils.getCumulativePhase(space.getObjects());
			_currCount = count;
		}
		return _currCumulativePhase;
	}
}
