package us.ihmc.simon.joscillators;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.StrictBorders;
import us.ihmc.simon.joscillators.RandomWalker;

public class JOscillatorsBuilder implements ContextBuilder<Oscillator> {

	interface GUI {
		static int PADDING = 5;
		static float RADIOUS = 50.0f;
	}

	@Override
	public Context<Oscillator> build(Context<Oscillator> context) {
		context.setId("joscillators");

		OscillatorAdder adder = new OscillatorAdder(GUI.RADIOUS, GUI.PADDING);
		ContinuousSpaceFactory spaceFactory =  ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Oscillator> space = spaceFactory.createContinuousSpace(
				"space", context, adder, new StrictBorders(), adder.getSpaceWidth(), adder.getSpaceHeight());

		for (int i = 0; i < Model.NUMBER_OF_OSCILLATORS; i++) {
			context.add (new Kuramoto(OscillatorUtils.nextPhase(), OscillatorUtils.nextFrequency(), space));
		}
		
		return context;
	}
}
