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

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;

/**
 * 
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
public class Model {

	interface Defaults {
		static final int NUMBER_OF_OSCILLATORS = 5;

		static final double MEAN_OSCILLATORY_FREQUENCY_IN_RADIANS = Math.PI/4;
		static final double MEAN_OSCILLATORY_FREQUENCY_STD_DEV_IN_RADIANS = Math.PI/16;

		static final double COUPLING_CONSTANT = 5.0;
	}

	private static Model INSTANCE = null;
	public final int numberOfOscillators;
	public final double couplingConstant;
	public final double samplingFrequencyInHz = 2048.0;
	public final Noise noise;
	public final Pulse pulse;

	private Model(int numberOfOscillators, double couplingConstant, Noise noise, Pulse pulse) {
		this.numberOfOscillators = numberOfOscillators;
		this.couplingConstant = couplingConstant;
		this.noise = noise;
		this.pulse = pulse;
	}

	static Model getModel(ContinuousSpace<Oscillator> space) {
		if (INSTANCE == null) {
			Parameters params = RunEnvironment.getInstance().getParameters();
			int numberOfOscillators = params.getInteger("numberOfOscillators");
			double couplingConstant = params.getDouble("couplingConstant");

			double samplingFreq = params.getDouble("samplingFrequency");
			double pulseFreq = params.getDouble("pulseFrequency");
			double noiseMean = 0; // params.getDouble("noiseMean");
			double noiseStdDev = 1/samplingFreq; // params.getDouble("noiseStdDev");
			double noiseStrength = params.getDouble("noiseStrength");

			INSTANCE = new Model (numberOfOscillators, couplingConstant,
					new Noise(noiseMean, noiseStdDev, noiseStrength),
					new Pulse(1, space));
		}
		return INSTANCE;
	}
}
