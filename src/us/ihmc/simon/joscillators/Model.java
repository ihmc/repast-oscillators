package us.ihmc.simon.joscillators;

public interface Model {

	static final int NUMBER_OF_OSCILLATORS = 5;
	
	static final double MEAN_OSCILLATORY_FREQUENCY_IN_RADIANS = Math.PI/4;
	static final double MEAN_OSCILLATORY_FREQUENCY_STD_DEV_IN_RADIANS = Math.PI/16;

	static final double COUPLING_CONSTANT = 5.0;
}
