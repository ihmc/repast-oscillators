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
