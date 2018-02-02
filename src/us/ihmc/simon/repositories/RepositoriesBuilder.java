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

import java.math.BigInteger;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.continuous.ContinuousSpace;
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

		
		String[] repositories = {"ffi", "arel", "tilt", "mail"};
//		String[] repositories = {"mail"};
		
		for (int i = 0; i < repositories.length; i++) {
			String repository = repositories[i];
			String path = "misc/data/commits/" + repository + ".csv";
			RepositoryData data = new RepositoryData(path);
			data.trimToCoupling(new BigInteger("1451538000000"));
			data.truncateToCoupling(new BigInteger("1483246800000"));
			SimulatedRepository simRepo = new SimulatedRepository(space, "Sim-" + repository, data);
			context.add(simRepo);
			
			HistoricalRepository histRepo = new HistoricalRepository(space, "Hist-" + repository, data);
			context.add(histRepo);

			NaturalRepository nr = new NaturalRepository(space, "Nat-" + repository, data);
			context.add(nr);
		}
	
		return context;
	}
}
