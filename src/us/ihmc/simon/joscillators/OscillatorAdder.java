package us.ihmc.simon.joscillators;

import repast.simphony.space.Dimensions;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.SimpleCartesianAdder;
import us.ihmc.simon.joscillators.JOscillatorsBuilder.GUI;

public class OscillatorAdder extends SimpleCartesianAdder<Oscillator> {

	private final float radius;
	private final float padding;
	
	OscillatorAdder(float radius, float padding) {
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

	double getSpaceWidth() {
		return (getRadious() + GUI.PADDING) * 2;
	}
	
	double getSpaceHeight() {
		// Square embedding the circle of radius "radius"
		return getSpaceWidth();
	}
}
