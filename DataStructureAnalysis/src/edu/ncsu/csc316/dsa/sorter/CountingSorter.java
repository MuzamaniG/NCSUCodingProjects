package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author mhgausi
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	
	/**
	 * Sorts data using object IDs with counting sort algorithm; overrides default sort method
	 * @param data array of generic objects to be sorted
	 */
	@Override
	public void sort(E[] data) {
		
		int min = data[0].getId();
		int max = data[0].getId();
		for (int i = 0; i <= data.length - 1; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		int range = max - min + 1;
		int[] count = new int[range];
		for (int j = 0; j <= data.length - 1; j++) {
			count[data[j].getId() - min]++;
		}
		for (int k = 1; k <= range - 1; k++) {
			count[k] = count[k - 1] + count[k];
		}
		@SuppressWarnings("unchecked")
		E[] newData = (E[]) new Identifiable[data.length];
		for (int x = data.length - 1; x >= 0; x--) {
			newData[count[data[x].getId() - min] - 1] = data[x];
			count[data[x].getId() - min] = count[data[x].getId() - min] - 1;
		}
        for (int y = 0; y < data.length; y++) {
            data[y] = newData[y];
        }
	}
}
