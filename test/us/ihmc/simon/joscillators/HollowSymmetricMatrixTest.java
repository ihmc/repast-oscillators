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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * 
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
public class HollowSymmetricMatrixTest {

	private static final float val01 = 0.11234f;
	private static final float val02 = 0.21234f;
	private static final float val12 = 1.21234f;
	private static final float delta = 0.000001f;

	@Test
	public void testAdd() {
		HollowSymmetricMatrix m = new HollowSymmetricMatrix(3);
		m.add(0, 1, val01);
		m.add(0, 2, val02);
		m.add(1, 2, val12);
	}

	@Test
	public void testAddIllegalArgumentException() {
		final float val = 3.4f;
		try {
			HollowSymmetricMatrix m = new HollowSymmetricMatrix(3);
			for (int i = 0; i < m.getWidth(); i++) {
				m.add(i, i, val);
				fail("Expected " + IllegalArgumentException.class.getSimpleName()
						+ " at " + (i+1) + "-th iteration");
			}
		}
		catch (IllegalArgumentException ex) {}
	}

	@Test
	public void testAddIndexOutOfBoundsException() {
		float value = 3.4f;
		try {
			new HollowSymmetricMatrix(3).add(-1, 0, value);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).add(3, 0, value);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).add(0, -1, value);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).add(0, 3, value);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).add(-1, -1, value);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).add(3, 3, value);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
	}

	@Test
	public void testGet() {
		HollowSymmetricMatrix m = new HollowSymmetricMatrix(3);
		m.add(0, 1, val01);
		m.add(0, 2, val02);
		m.add(1, 2, val12);
		
		assertEquals (m.get(0, 1), val01, delta);
		assertEquals (m.get(1, 0), val01, delta);

		assertEquals (m.get(0, 2), val02, delta);
		assertEquals (m.get(2, 0), val02, delta);

		assertEquals (m.get(1, 2), val12, delta);
		assertEquals (m.get(2, 1), val12, delta);
	}

	@Test
	public void testGetIndexOutOfBoundsException() {
		try {
			new HollowSymmetricMatrix(3).get(-1, 0);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).get(3, 0);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).get(0, -1);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).get(0, 3);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).get(-1, -1);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
		try {
			new HollowSymmetricMatrix(3).get(3, 3);
			fail("Expected " + IndexOutOfBoundsException.class.getSimpleName());
		}
		catch (IndexOutOfBoundsException ex) {}
	}
}
