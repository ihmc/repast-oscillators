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
 */

package us.ihmc.simon.joscillators;

import repast.simphony.engine.watcher.Watch;
import repast.simphony.engine.watcher.WatcherTriggerSchedule;
import repast.simphony.space.continuous.ContinuousSpace;
import simphony.util.messages.MessageCenter;

/**
 * 
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
public class Kuramoto extends Oscillator {

	MessageCenter logger = MessageCenter.getMessageCenter(Kuramoto.class);

	public Kuramoto (double phase, double frequency, ContinuousSpace<Oscillator> space) {
		super(phase, frequency, space);
	}

	@Watch(watcheeClassName = "us.ihmc.simon.joscillators.Pulse",
			watcheeFieldNames = "fired",
			whenToTrigger = WatcherTriggerSchedule.IMMEDIATE)
	public void update() {
		final Model m = Model.getModel(space);
		final double currentPhase = phase;
		final MeanField meanField = m.pulse.getCurrentMeanField();
		
		double dthdt = m.couplingConstant * meanField.r * Math.sin(meanField.psi - currentPhase);
		double newPhase =
				currentPhase + dthdt * (1/m.samplingFrequencyInHz) +
				m.noise.get() +
				m.pulse.get(this);

		logger.info("transitioning phase: " + phase + " -> " + newPhase);
		phase = newPhase;

		move();
	}
}
