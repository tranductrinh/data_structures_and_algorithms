import java.util.*;

public class LCM {
	private static long lcm(int a, int b) {
		return (long) a * b / gcd(a, b);
	}

	private static int gcd(int a, int b) {
		if (a == b) {
			return a;
		}

		if (a == 0) {
			return b;
		}

		if (b == 0) {
			return a;
		}

		if (a > b) {
			return gcd(b, a % b);
		} else {
			return gcd(b % a, a);
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		System.out.println(lcm(a, b));
	}
}
