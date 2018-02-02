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


import org.junit.Test;

import repast.simphony.data2.DataSource;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Adam Dalton	(adalton@ihmc.us)
 *
 */
public class RepositoryDataTest {

	private static final int railsCount = 339;
//	private static final float  railsMean = 1.0f;
	private static final float  railsMean = 1.0f;
	private static final float  meanDelta = 22.5359f;

	@Test
	public void testLoadCsvFile() {
		String path = "misc/data/tags/rails.csv";
		RepositoryData rd = new RepositoryData(path);

		assertEquals(railsCount, rd.count());
		assertEquals(railsMean, rd.getMean(),meanDelta);
	}

	@Test
	public void testLoadBundlerCsvFile() {
		String path = "misc/data/tags/bundler.csv";
		RepositoryData rd = new RepositoryData(path);

		assertEquals(263, rd.count());
	}
	@Test
	public void testLoadBundlerStats() {
		String path = "misc/data/tags/bundler.csv";
		RepositoryData rd = new RepositoryData(path);
		assertEquals(15.43367, rd.getMean(),meanDelta);
	}
	@Test
	public void testFrequencyInRadians() {
		String path = "misc/data/tags/bundler.csv";
		RepositoryData rd = new RepositoryData(path);
		assertEquals(0.3968, rd.getFrequencyInRadians(),meanDelta);
	}
	
}
