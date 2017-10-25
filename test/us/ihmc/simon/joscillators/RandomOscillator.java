package us.ihmc.simon.joscillators;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import us.ihmc.simon.joscillators.Oscillator;
import us.ihmc.simon.joscillators.OscillatorUtils;

public class RandomOscillator extends Oscillator {

	RandomOscillator (double phase, double frequency, ContinuousSpace<Oscillator> space) {
		super(phase, frequency, space);
	}

	@ScheduledMethod(start=1, interval=1)
	public void update() {
		phase = OscillatorUtils.nextPhase();
		move();
	}
}
