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

public interface Model {

	static final int NUMBER_OF_OSCILLATORS = 5;
	
	static final double MEAN_OSCILLATORY_FREQUENCY_IN_RADIANS = Math.PI/4;
	static final double MEAN_OSCILLATORY_FREQUENCY_STD_DEV_IN_RADIANS = Math.PI/16;

	static final double COUPLING_CONSTANT = 5.0;
}
