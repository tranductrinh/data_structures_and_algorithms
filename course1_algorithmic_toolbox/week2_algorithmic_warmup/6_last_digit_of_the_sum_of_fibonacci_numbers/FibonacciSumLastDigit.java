import java.util.Scanner;

public class FibonacciSumLastDigit {

	/**
	 * Get Fibonacci sum of last digits from F0 to Fn
	 *
	 * @param n
	 * @return
	 */
	private static long getFibonacciSum(long n) {
		if (n <= 1) {
			return n;
		}

		int[] firstSixtyOfLastDigits = firstSixtyLastDigits();
		long quotient = n / 60;
		int remain = (int) (n % 60);

		return (quotient * sum(firstSixtyOfLastDigits) + sumUntil(firstSixtyOfLastDigits, remain)) % 10;

	}

	/**
	 * Sun of an array until the given index
	 * @param digits
	 * @param until
	 * @return
	 */
	private static int sumUntil(int[] digits, int until) {
		if (until >= digits.length) {
			throw new RuntimeException("i can not be greater than or equal " + digits.length);
		}

		int sum = 0;
		for (int i = 0; i <= until; i++) {
			sum += digits[i];
		}

		return sum;
	}

	/**
	 * Sum of an array
	 *
	 * @param digits
	 * @return
	 */
	private static int sum(int[] digits) {
		int sum = 0;
		for (int digit : digits) {
			sum += digit;
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
		long s = getFibonacciSum(n);
		System.out.println(s);
	}
}

