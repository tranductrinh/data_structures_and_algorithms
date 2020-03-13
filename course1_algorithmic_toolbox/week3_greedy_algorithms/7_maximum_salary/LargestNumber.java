import java.util.Scanner;

public class LargestNumber {

	private static String largestNumber(String[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (isGreaterOrEqual(a[j], a[i])) {
					swap(a, i, j);
				}
			}
		}

		StringBuilder num = new StringBuilder();
		for (String s : a) {
			num.append(s);
		}

		return num.toString();
	}

	private static void swap(String[] a, int i, int j) {
		String tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static boolean isGreaterOrEqual(String x, String y) {
		return Integer.parseInt(x + y) >= Integer.parseInt(y + x);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		String[] a = new String[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.next();
		}
		System.out.println(largestNumber(a));
	}
}

