package us.ihmc.simon.joscillators;

import java.util.stream.StreamSupport;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import simphony.util.messages.MessageCenter;

public class Kuramoto extends Oscillator {

	MessageCenter logger = MessageCenter.getMessageCenter(Kuramoto.class);

	Kuramoto (double phase, double frequency, ContinuousSpace<Oscillator> space) {
		super(phase, frequency, space);
	}

	@ScheduledMethod(start=1, interval=1)
	public void update() {		
		final double currentPhase = phase;
		double c = Model.COUPLING_CONSTANT / Model.NUMBER_OF_OSCILLATORS;
		// Get all oscillators from "space" and compute sum
		double sum = c * StreamSupport.stream(space.getObjects().spliterator(), false)
				.mapToDouble(o -> Math.sin(o.phase - currentPhase))
				.sum();

		double newPhase = frequency + sum;
		logger.info("transitioning phase: " + phase + " -> " + newPhase);
		phase = newPhase;

		move();
	}
}
