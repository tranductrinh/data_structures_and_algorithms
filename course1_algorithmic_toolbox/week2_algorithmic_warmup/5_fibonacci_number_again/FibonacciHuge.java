import java.util.Arrays;
import java.util.Scanner;

public class FibonacciHuge {
	private static long getFibonacciHuge(long n, long m) {
		long[] period = pisanoPeriod(n, m);
		int index = (int) (n % period.length);
		return period[index];
	}

	private static long[] pisanoPeriod(long n, long m) {
		if (n < 0) {
			throw new RuntimeException("n can not be a negative number!");
		}

		if (n == 0) {
			return new long[]{0};
		}

		if (n == 1) {
			return new long[]{0, 1};
		}

		long[] period = new long[(int) (m * m)];
		period[0] = 0;
		period[1] = 1;

		int i = 2;
		while (i < m * m) {
			period[i] = (period[i - 1] + period[i - 2]) % m;

			if (period[i] == 1 && period[i - 1] == 0) {
				break;
			}

			i++;
		}

		return Arrays.copyOf(period, i - 1);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long n = scanner.nextLong();
		long m = scanner.nextLong();
		System.out.println(getFibonacciHuge(n, m));
	}
}

