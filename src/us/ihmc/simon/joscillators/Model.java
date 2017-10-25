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

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;

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

	private Model(int numberOfOscillators, double couplingConstant) {
		this.numberOfOscillators = numberOfOscillators;
		this.couplingConstant = couplingConstant;
	}

	static Model getModel() {
		if (INSTANCE == null) {
			Parameters params = RunEnvironment.getInstance().getParameters();
			int numberOfOscillators = params.getInteger("numberOfOscillators");
			double couplingConstant = params.getDouble("couplingConstant");
			INSTANCE = new Model (numberOfOscillators, couplingConstant);
		}
		return INSTANCE;
	}
}
