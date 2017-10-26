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

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import us.ihmc.simon.joscillators.Oscillator;
import us.ihmc.simon.joscillators.OscillatorUtils;

/**
 * 
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
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
