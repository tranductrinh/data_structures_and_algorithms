import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.pow;

public class MaxPairwiseProduct {

	/**
	 * Enter something like
	 *
	 * 5
	 * 1 2 3 4 5
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scanner.nextInt();
		}
		System.out.println(getMaxPairwiseProduct(numbers));
	}

	private static long getMaxPairwiseProduct(int[] numbers) {
		int length = numbers.length;

		if (length < 2) {
			throw new RuntimeException("Length is too short!");
		}

		if (length > 2 * pow(10, 5)) {
			throw new RuntimeException("Length is too long!");
		}

		int biggestNumber = 0;
		int secondBiggestNumber = 0;
		if (numbers[0] >= numbers[1]) {
			biggestNumber = numbers[0];
			secondBiggestNumber = numbers[1];
		} else {
			biggestNumber = numbers[1];
			secondBiggestNumber = numbers[0];
		}

		for (int i = 2; i < length; i++) {
			if (numbers[i] >= biggestNumber) {
				secondBiggestNumber = biggestNumber;
				biggestNumber = numbers[i];
			} else if (numbers[i] >= secondBiggestNumber) {
				secondBiggestNumber = numbers[i];
			}
		}

		return (long) biggestNumber * (long) secondBiggestNumber;
	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			try {
				br = new BufferedReader(new
						InputStreamReader(stream));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

}
