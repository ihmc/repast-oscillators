package us.ihmc.simon.joscillators;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import junit.framework.TestCase;
import us.ihmc.simon.joscillators.Kuramoto;
import us.ihmc.simon.joscillators.Oscillator;
import us.ihmc.simon.joscillators.OscillatorUtils;

public class KuramotoTest extends TestCase {

	public void testUpdate() {

		final Collection<Oscillator> oscillators = new HashSet<>();
		double frequency =  OscillatorUtils.nextFrequency();
		for (int i = 0; i < 5; i++) {
			oscillators.add(new Kuramoto(OscillatorUtils.nextRadian(), frequency, null));
		}
		
		long time = 0;
		for (int i = 0; i < 1000; i++) {
			Iterator<Oscillator> iter = oscillators.iterator();
			while (iter.hasNext()) {
				Oscillator o = iter.next();
				if (i > 0) {
					//o.update();
				}
				System.out.println(o);
			}
			final long currTime = time;
			double avg = Math.toDegrees(oscillators.stream().mapToDouble(o -> (o.frequency) + o.phase).average().getAsDouble());
			double max = Math.toDegrees(oscillators.stream().mapToDouble(o -> (o.frequency) + o.phase).max().getAsDouble());
			double min = Math.toDegrees(oscillators.stream().mapToDouble(o -> (o.frequency) + o.phase).min().getAsDouble());
			System.out.println(avg + " " + (max - min));
			time += 10;
			System.out.println("-----------------");
		}
	}
}
