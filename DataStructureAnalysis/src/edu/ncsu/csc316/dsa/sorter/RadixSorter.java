package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author mhgausi
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Sorts data using object IDs with counting sort algorithm; overrides default sort method
	 * @param data array of generic objects to be sorted
	 */
	@Override
	public void sort(E[] data) {
		int max = 0;
		for (int i = 0; i <= data.length - 1; i++) {
			max = Math.max(max, data[i].getId());
		}
		int maxDigits = (int) Math.ceil(Math.log10(max + 1));
		int p = 1;
		for (int j = 1; j <= maxDigits; j++) {
			int[] newArray = new int[10];
			for (int k = 0; k <= data.length - 1; k++) {
				newArray[data[k].getId() / p % 10]++;
			}
			for (int m = 1; m <= 9; m++) {
				newArray[m] = newArray[m - 1] + newArray[m];
			}
			@SuppressWarnings("unchecked")
			E[] f = (E[]) new Identifiable[data.length];
			for (int i = data.length - 1; i >= 0; i--) {
				f[newArray[data[i].getId() / p % 10] - 1] = data[i];
				newArray[data[i].getId() / p % 10]--;
			}
			for (int i = 0; i <= data.length - 1; i++) {
				data[i] = f[i];
			}
			p *= 10;
		}
	}
}
