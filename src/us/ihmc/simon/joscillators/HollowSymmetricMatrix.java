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

/**
 * Symmetric matrix with 0s on the diagonal.
 *
 * @author Giacomo Benincasa	(gbenincasa@ihmc.us)
 *
 */
public class HollowSymmetricMatrix {

	private final int maxIndex;
	float[] elements;
	
	HollowSymmetricMatrix (int m) {
		if (m <= 0) {
			throw new IllegalArgumentException("Matrix size must be greater that 0.");
		}
		maxIndex = m - 1;
		int size = firstNSum(m);
		elements = new float[size];
	}

	int getWidth() {
		return maxIndex + 1;
	}
	
	int getHeight() {
		return getWidth();
	}

	void add(int m, int n, float val) {
		checkIndexes(m, n);
		if ((m == n) && (val < 0.0 || val > 0.0)) {
			throw new IllegalArgumentException("Elements on the diagonal must be 0.");
		}
		if (m > n) {
			int tmp = m;
			m = n;
			n = tmp;
		}
		elements[toIndex(m, n)] = val;
	}

	float get(int m, int n) {
		checkIndexes(m, n);
		if (m == n) {
			return 0.0f;
		}
		if (m > n) {
			int tmp = m;
			m = n;
			n = tmp;
		}
		return elements[toIndex(m, n)];
	}

	private void checkIndexes(int...idxes) {
		for (int idx : idxes) {
		    if ((idx < 0) || (idx > maxIndex)) {
			    throw new IndexOutOfBoundsException();
		    }
		}
	}

	private static int toIndex(int m, int n) {
		return (int) (Math.pow(2, m) + n - 2);
	}

	private static int firstNSum(int n) {
		return n * (n - 1) / 2;
	}
}
