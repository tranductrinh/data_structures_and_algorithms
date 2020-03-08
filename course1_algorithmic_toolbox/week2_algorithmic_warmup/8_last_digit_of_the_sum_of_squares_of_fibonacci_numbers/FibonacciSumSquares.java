import java.util.Scanner;

public class FibonacciSumSquares {

	private static long getFibonacciSumSquares(long n) {
		if (n <= 1) {
			return n;
		}

		int[] firstSixtyOfLastDigits = firstSixtyLastDigits();
		long quotient = n / 60;
		int remain = (int) (n % 60);

		return (quotient * sumSquares(firstSixtyOfLastDigits) + sumSquaresUntil(firstSixtyOfLastDigits, remain)) % 10;
	}

	/**
	 * Sun squares of an array until the given index
	 *
	 * @param digits
	 * @param until
	 * @return
	 */
	private static int sumSquaresUntil(int[] digits, int until) {
		if (until >= digits.length) {
			throw new RuntimeException("i can not be greater than or equal " + digits.length);
		}

		int sum = 0;
		for (int i = 0; i <= until; i++) {
			sum += digits[i] * digits[i];
		}

		return sum;
	}

	/**
	 * Sum squares of an array
	 *
	 * @param digits
	 * @return
	 */
	private static int sumSquares(int[] digits) {
		int sum = 0;
		for (int digit : digits) {
			sum += digit * digit;
		}

		return sum;
	}

	/**
	 * The last digits repeat every 60 numbers.
	 *
	 * @return
	 */
	private static int[] firstSixtyLastDigits() {
		int[] digits = new int[60];
		digits[0] = 0;
		digits[1] = 1;

		for (int i = 2; i < 60; i++) {
            digits[i] = (digits[i - 1] + digits[i - 2]) % 10;
		}

		return digits;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long n = scanner.nextLong();
		long s = getFibonacciSumSquares(n);
		System.out.println(s);
	}
}

