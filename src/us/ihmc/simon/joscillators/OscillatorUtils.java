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

import java.util.Collection;

import repast.simphony.random.RandomHelper;

/**
 * 
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
class OscillatorUtils {

	static double nextAngle() {		
		return RandomHelper.nextDoubleFromTo(0.0, 360.0);
	}

	static double nextRadian() {
		return Math.toRadians(nextAngle());
	}

	static double nextFrequency() {
		return Model.Defaults.MEAN_OSCILLATORY_FREQUENCY_IN_RADIANS +
				RandomHelper.nextDouble() *
				Model.Defaults.MEAN_OSCILLATORY_FREQUENCY_STD_DEV_IN_RADIANS;
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
				radious + padding + (radious * Math.sin(o.getAngle()))); 
	}

	static MeanField getMeanField(Iterable<Oscillator> oscillators) {
		int counter = 0;
		double sumSin = 0.0;
		double sumCos = 0.0;
		for (Oscillator o : oscillators) {
			sumSin += Math.sin(o.phase);
			sumCos += Math.cos(o.phase);
			counter++;
		}
		sumSin /= counter;
		sumCos /= counter;

		double psi = module(Math.atan2(sumSin, sumCos), 2*Math.PI);
		double r = Math.sqrt(Math.pow(sumSin, 2) + Math.pow(sumCos, 2));
		return new MeanField (psi, r);
	}

	static double getPhaseCoherence(Collection<Oscillator> oscillators) {
		// TODO: missing "i"
		double psi = getMeanField(oscillators).psi;
		double denominator = 1 / (Math.pow(Math.E, psi) * oscillators.size());
		double sum = oscillators.stream().mapToDouble(o -> Math.pow(Math.E, o.phase)).sum();
		return sum / denominator;
	}

	private static double module(double x, double y) {
		return ((x % y) + y) % y;
	}
}
