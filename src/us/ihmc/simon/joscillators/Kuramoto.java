/**
 * This file is part of Repast Oscillators.
 *
 * Repast Oscillators is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Repast Oscillators is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Repast Oscillators. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */

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
		final Model m = Model.getModel();
		final double currentPhase = phase;
		double c = m.couplingConstant / m.numberOfOscillators;
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
