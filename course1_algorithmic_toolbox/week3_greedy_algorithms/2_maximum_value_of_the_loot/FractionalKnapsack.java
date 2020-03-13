import java.util.Scanner;

public class FractionalKnapsack {

	private static double getOptimalValue(int capacity, int[] values, int[] weights) {
		double value = 0;
		sortValuableDesc(values, weights);

		for (int i = 0; i < values.length; i++) {
			int maxWeightCanTake = Math.min(weights[i], capacity);
			value += (maxWeightCanTake * ((double) values[i] / weights[i]));

			capacity -= maxWeightCanTake;

			if (capacity == 0) {
				break;
			}
		}

		return value;
	}

	/**
	 * Sort both given values and weights arrays at the same time, the most valuable item is the first
	 *
	 * @param values
	 * @param weights
	 */
	private static void sortValuableDesc(int[] values, int[] weights) {
		if (values == null || values.length <= 1) {
			return;
		}

		for (int i = 0; i < values.length - 1; i++) {
			for (int j = i + 1; j < values.length; j++) {
				if ((double) values[j] / weights[j] > (double) values[i] / weights[i]) {
					int valueTmp = values[i];
					int weightTmp = weights[i];
					values[i] = values[j];
					weights[i] = weights[j];
					values[j] = valueTmp;
					weights[j] = weightTmp;
				}
			}
		}

	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int capacity = scanner.nextInt();
		int[] values = new int[n];
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			values[i] = scanner.nextInt();
			weights[i] = scanner.nextInt();
		}
		System.out.println(getOptimalValue(capacity, values, weights));
	}
} 
