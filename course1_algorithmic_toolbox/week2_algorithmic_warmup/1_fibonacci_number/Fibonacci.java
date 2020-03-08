import java.util.Scanner;

public class Fibonacci {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		System.out.println(calculateFibonacci(n));
	}

	private static long calculateFibonacci(int n) {
		if (n < 0) {
			throw new RuntimeException("n can not be a negative number!");
		}

		if (n <= 1) {
			return n;
		}

		long previous = 0, current = 1, temp;
		for (int i = 2; i <= n; i++) {
			temp = previous;
			previous = current;
			current = current + temp;
		}

		return current;

	}

}
