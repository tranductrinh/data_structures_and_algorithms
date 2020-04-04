import java.util.Scanner;

public class LCS2 {

	private static int lcs2(int[] a, int[] b) {
		int[][] d = memorize(a, b);
		return countMatch(d, a, b);
	}

	private static int[][] memorize(int[] a, int[] b) {
		int n = a.length;
		int m = b.length;

		int[][] d = new int[n + 1][m + 1];

		for (int j = 1; j <= m; j++) {
			for (int i = 1; i <= n; i++) {
				if (a[i - 1] == b[j - 1]) {
					d[i][j] = d[i - 1][j - 1] + 1;
				} else {
					d[i][j] = Math.max(d[i][j - 1], d[i - 1][j]);
				}

			}
		}

		return d;
	}

	private static int countMatch(int[][] d, int[] a, int[] b) {
		int count = 0;
		int i = d.length - 1;
		int j = d[0].length - 1;

		while (i > 0 && j > 0) {
			if (a[i - 1] == b[j - 1]) {
				count++;
				i = i - 1;
				j = j - 1;
			} else if (d[i - 1][j] > d[i][j - 1]) {
				i = i - 1;
			} else {
				j = j - 1;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		int m = scanner.nextInt();
		int[] b = new int[m];
		for (int i = 0; i < m; i++) {
			b[i] = scanner.nextInt();
		}

		System.out.println(lcs2(a, b));

//		singleTest();
	}

	// TESTING
	private static void singleTest() {
		System.out.println("*****************************************");
		System.out.println("************** SINGLE TEST **************");
		System.out.println("*****************************************");

		int[] a = new int[]{2, 7, 5};
		int[] b = new int[]{2, 5};

		System.out.println("Sample 1: " + lcs2(a, b));

		a = new int[]{7};
		b = new int[]{1, 2, 3, 4};

		System.out.println("Sample 2: " + lcs2(a, b));

		a = new int[]{2, 7, 8, 3};
		b = new int[]{5, 2, 8, 7};

		System.out.println("Sample 3: " + lcs2(a, b));

		a = new int[]{1, 2, 3};
		b = new int[]{3, 2, 1};

		System.out.println("Sample 4: " + lcs2(a, b));
	}
}

