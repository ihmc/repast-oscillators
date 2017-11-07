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

import repast.simphony.space.Dimensions;
import repast.simphony.space.continuous.ContinuousSpace;

/**
 * 
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
public abstract class Oscillator {

	protected final double strength;
	protected double phase;
	protected final double frequency;
	protected final ContinuousSpace<Oscillator> space;

	/**
	 * 
	 * @param phase - instantaneous phase (cycles)
	 * @param frequency - angular frequency in Hertz (cycles per second)
	 * @param model
	 */
	Oscillator (double phase, double frequency, ContinuousSpace<Oscillator> space) {
		this.strength = 0;
		this.phase = phase;
		this.frequency = frequency;
		this.space = space;
	}

	double getAngle() {
		return getAngle(0);
	}

	double getAngle(long time) {
		return (frequency * time) + phase;
	}

	public double getPhase() {
		return phase;
	}

	protected void move() {
		Dimensions dims = space.getDimensions();
		double[] location = new double[dims.size()];
		ContinuosPoint pt = OscillatorUtils.toPoint(this);
		location[0] = pt.x;
		location[1] = pt.y;
		if (location.length > 2) {
			location[2] = 0.0;
		}
		space.moveTo(this, location);
	}

	public String toString() {
		return new StringBuilder(Double.toString(frequency))
				.append(", ").append(phase)
				.append(" (").append(Math.toDegrees(frequency+phase)).append(")")
				.toString();
	}
}
