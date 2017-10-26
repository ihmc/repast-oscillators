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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

import junit.framework.TestCase;
import us.ihmc.simon.joscillators.Kuramoto;
import us.ihmc.simon.joscillators.Oscillator;
import us.ihmc.simon.joscillators.OscillatorUtils;

/**
 * 
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
public class KuramotoTest extends TestCase {

	@Test
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
