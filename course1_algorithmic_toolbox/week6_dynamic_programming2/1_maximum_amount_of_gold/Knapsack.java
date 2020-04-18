import java.util.Scanner;

public class Knapsack {

	static int optimalWeight(int W, int[] weights) {
		int[][] table = new int[weights.length + 1][W + 1];

		for (int i = 1; i <= weights.length; i++) {
			for (int w = 1; w <= W; w++) {
				table[i][w] = table[i - 1][w];

				if (weights[i - 1] <= w) {
					int value = table[i - 1][w - weights[i - 1]] + weights[i - 1];
					if (table[i][w] < value) {
						table[i][w] = value;
					}
				}
			}
		}

		return table[weights.length][W];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int W, n;
		W = scanner.nextInt();
		n = scanner.nextInt();

		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			weights[i] = scanner.nextInt();
		}

		System.out.println(optimalWeight(W, weights));
	}
}

