import java.util.*;

public class DotProduct {

	private static long maxDotProduct(int[] a, int[] b) {
		sortDesc(a);
		sortDesc(b);

		long product = 0;
		for (int i = 0; i < a.length; i++) {
			product += ((long) a[i] * b[i]);
		}

		return product;
	}

	private static void sortDesc(int[] numbers) {
		if (numbers == null || numbers.length <= 1) {
			return;
		}

		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] > numbers[i]) {
					int valueTmp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = valueTmp;
				}
			}
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			b[i] = scanner.nextInt();
		}
		System.out.println(maxDotProduct(a, b));
	}
}

