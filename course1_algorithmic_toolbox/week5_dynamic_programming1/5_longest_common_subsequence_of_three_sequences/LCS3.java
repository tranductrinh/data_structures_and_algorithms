import java.util.Scanner;

public class LCS3 {

	private static int lcs3(int[] a, int[] b, int[] c) {
		int[][][] d = memorize(a, b, c);
		return countMatch(d, a, b, c);
	}

	private static int[][][] memorize(int[] a, int[] b, int[] c) {
		int n = a.length;
		int m = b.length;
		int h = c.length;

		int[][][] d = new int[n + 1][m + 1][h + 1];

		for (int k = 1; k <= h; k++) {
			for (int j = 1; j <= m; j++) {
				for (int i = 1; i <= n; i++) {
					if (a[i - 1] == b[j - 1] && b[j - 1] == c[k - 1]) {
						d[i][j][k] = d[i - 1][j - 1][k - 1] + 1;
					} else {
						d[i][j][k] = Math.max(d[i - 1][j][k], Math.max(d[i][j - 1][k], d[i][j][k - 1]));
					}
				}
			}
		}

		return d;
	}

	private static int countMatch(int[][][] d, int[] a, int[] b, int[] c) {
		int count = 0;
		int i = d.length - 1;
		int j = d[0].length - 1;
		int k = d[0][0].length - 1;

		while (i > 0 && j > 0 && k > 0) {
			if (a[i - 1] == b[j - 1] && b[j - 1] == c[k - 1]) {
				count++;
				i = i - 1;
				j = j - 1;
				k = k - 1;
			} else {
				int max = Math.max(d[i - 1][j][k], Math.max(d[i][j - 1][k], d[i][j][k - 1]));
				if (max == d[i - 1][j][k]) {
					i = i - 1;
				} else if (max == d[i][j - 1][k]) {
					j = j - 1;
				} else {
					k = k - 1;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int an = scanner.nextInt();
		int[] a = new int[an];
		for (int i = 0; i < an; i++) {
			a[i] = scanner.nextInt();
		}

		int bn = scanner.nextInt();
		int[] b = new int[bn];
		for (int i = 0; i < bn; i++) {
			b[i] = scanner.nextInt();
		}

		int cn = scanner.nextInt();
		int[] c = new int[cn];
		for (int i = 0; i < cn; i++) {
			c[i] = scanner.nextInt();
		}

		System.out.println(lcs3(a, b, c));

//		singleTest();
	}

	// TESTING
	private static void singleTest() {
		System.out.println("*****************************************");
		System.out.println("************** SINGLE TEST **************");
		System.out.println("*****************************************");

		int[] a = new int[]{1, 2, 3};
		int[] b = new int[]{2, 1, 3};
		int[] c = new int[]{1, 3, 5};

		System.out.println("Sample 1: " + lcs3(a, b, c));

		a = new int[]{8, 3, 2, 1, 7};
		b = new int[]{8, 2, 1, 3, 8, 10, 7};
		c = new int[]{6, 8, 3, 1, 4, 7};

		System.out.println("Sample 2: " + lcs3(a, b, c));
	}
}

