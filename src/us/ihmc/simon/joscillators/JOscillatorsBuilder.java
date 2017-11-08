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

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.StrictBorders;

/**
 * 
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
public class JOscillatorsBuilder implements ContextBuilder<Oscillator> {

	interface GUI {
		static int PADDING = 5;
		static float RADIOUS = 50.0f;
	}

	@Override
	public Context<Oscillator> build(Context<Oscillator> context) {

		context.setId("joscillators");

		// Create space to graphically represent oscillators
		OscillatorAdder adder = new OscillatorAdder(GUI.RADIOUS, GUI.PADDING);
		ContinuousSpaceFactory spaceFactory =  ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Oscillator> space = spaceFactory.createContinuousSpace(
				"space", context, adder, new StrictBorders(), adder.getSpaceWidth(), adder.getSpaceHeight());
		
		final Model m = Model.getModel(space);

		// Added oscillators to context (and therefore to space, via OscillatorAdder)
		for (int i = 0; i < m.numberOfOscillators; i++) {
			context.add (new Kuramoto(OscillatorUtils.nextPhase(), OscillatorUtils.nextFrequency(), space));
		}
	
		context.add(m.pulse);
		
		return context;
	}
}
