package us.ihmc.simon.joscillators;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.Dimensions;
import repast.simphony.space.continuous.ContinuousSpace;
import us.ihmc.simon.joscillators.JOscillatorsBuilder;
import us.ihmc.simon.joscillators.Oscillator;

public class RandomWalker extends Oscillator {

	RandomWalker (double phase, double frequency, ContinuousSpace<Oscillator> space) {
		super(phase, frequency, space);
	}
	
	@ScheduledMethod(start=1, interval=1)
	public void update() {
		move();
	}

	protected void move() {
		Dimensions dims = space.getDimensions();
		double[] location = new double[dims.size()];
		location[0] = RandomHelper.nextDoubleFromTo(0, JOscillatorsBuilder.GUI.RADIOUS * 2);
		location[1] = RandomHelper.nextDoubleFromTo(0, JOscillatorsBuilder.GUI.RADIOUS * 2);
		if (location.length > 2) {
			location[2] = 0.0;
		}
		space.moveTo(this, location);
	}
}
