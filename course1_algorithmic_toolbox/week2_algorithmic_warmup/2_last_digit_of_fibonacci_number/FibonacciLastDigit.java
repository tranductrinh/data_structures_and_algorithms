import java.util.Scanner;

public class FibonacciLastDigit {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long c = getFibonacciLastDigit(n);
		System.out.println(c);
	}

	/**
	 * Fibonacci numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, ...
	 *
	 * Their last digits: 0, 1, 1, 2, 3, 5, 8, 3, 1, 4, 5, 9, 4, 3, 7, 0, 7, ...
	 *
	 * @param n
	 * @return
	 */
	private static long getFibonacciLastDigit(int n) {
		if (n < 0) {
			throw new RuntimeException("n can not be a negative number!");
		}

		if (n <= 1) {
			return n;
		}

		int previousDigit = 0, currentDigit = 1, temp;
		for (int i = 2; i <= n; i++) {
			temp = previousDigit;
			previousDigit = currentDigit;
			currentDigit = (currentDigit + temp) % 10;
		}

		return currentDigit;
	}

}

