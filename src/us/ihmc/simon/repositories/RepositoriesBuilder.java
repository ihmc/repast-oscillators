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

package us.ihmc.simon.repositories;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.continuous.SimpleCartesianAdder;
import repast.simphony.space.continuous.StrictBorders;

/**
 * 
 * @author Adam Dalton	(adalton@ihmc.us)
 *
 */
public class RepositoriesBuilder implements ContextBuilder<Repository> {

	@Override
	public Context<Repository> build(Context<Repository> context) {

		context.setId("joscillators");
		
		SimpleCartesianAdder<Repository> adder = new SimpleCartesianAdder<Repository>();

		// Create space to graphically represent oscillators

		ContinuousSpaceFactory spaceFactory =  ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Repository> space = spaceFactory.createContinuousSpace(
				"space", context, adder, new StrictBorders(), 200, 200);

		Repository rails = new Repository(space, "Rails", 0);
		Repository ruby = new Repository(space, "Ruby", Math.PI);
		context.add(rails);
		context.add(ruby);
		space.moveTo(rails, 100,100);
		space.moveTo(ruby, 100,100);
	
		return context;
	}
}
