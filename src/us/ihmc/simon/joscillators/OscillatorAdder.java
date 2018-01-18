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
import repast.simphony.space.continuous.SimpleCartesianAdder;
import us.ihmc.simon.joscillators.JOscillatorsBuilder.GUI;

/**
 * Added class that adds oscillator agents on a unit circle.
 *
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 */
public class OscillatorAdder extends SimpleCartesianAdder<Oscillator> {

	private final float radius;
	private final float padding;
	
	public OscillatorAdder(float radius, float padding) {
		this.radius = radius;
		this.padding = padding;
	}

	float getRadious() {
		return radius;
	}
	
	public void add(ContinuousSpace<Oscillator> space, Oscillator obj) {
		super.add(space, obj);
		Dimensions dims = space.getDimensions();
		double[] location = new double[dims.size()];
		ContinuosPoint pt = OscillatorUtils.toPoint(obj, radius, padding);
		location[0] = pt.x;
		location[1] = pt.y;
		if (location.length > 2) {
			location[2] = 0.0;
		}
		space.moveTo(obj, location);
	}

	public double getSpaceWidth() {
		return (getRadious() + GUI.PADDING) * 2;
	}
	
	public double getSpaceHeight() {
		// Square embedding the circle of radius "radius"
		return getSpaceWidth();
	}
}
