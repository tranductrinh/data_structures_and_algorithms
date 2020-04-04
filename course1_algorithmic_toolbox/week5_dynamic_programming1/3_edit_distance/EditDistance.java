import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class EditDistance {

	public static int editDistance(String str1, String str2) {
		int n = str1.length();
		int m = str2.length();
		return memorize(str1, str2)[n][m];
	}

	private static int[][] memorize(String str1, String str2) {
		int n = str1.length();
		int m = str2.length();

		int[][] d = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			d[i][0] = i;
		}

		for (int j = 0; j <= m; j++) {
			d[0][j] = j;
		}

		for (int j = 1; j <= m; j++) {
			for (int i = 1; i <= n; i++) {
				int insertion = d[i][j - 1] + 1;
				int deletion = d[i - 1][j] + 1;

				int mismatch = Integer.MAX_VALUE, match = Integer.MAX_VALUE;
				if (str1.charAt(i - 1) != str2.charAt(j - 1)) {
					mismatch = d[i - 1][j - 1] + 1;
				} else {
					match = d[i - 1][j - 1];
				}

				d[i][j] = Math.min(Math.min(insertion, deletion), Math.min(mismatch, match));

			}
		}

		return d;
	}

	private static void outputAlignment(int[][] d, String str1, String str2) {
		int i = d.length - 1;
		int j = d[0].length - 1;

		List<Character> first = new ArrayList<>();
		List<Character> second = new ArrayList<>();

		while (i > 0 || j > 0) {
			if (i > 0 && d[i][j] == d[i - 1][j] + 1) { // deletion
				first.add(str1.charAt(i - 1));
				second.add('-');
				i = i - 1;
			} else if (j > 0 && d[i][j] == d[i][j - 1] + 1) { // insertion
				first.add('-');
				second.add(str2.charAt(j - 1));
				j = j - 1;
			} else { // miss match or match
				first.add(str1.charAt(i - 1));
				second.add(str2.charAt(j - 1));
				i = i - 1;
				j = j - 1;
			}
		}

		Collections.reverse(first);
		Collections.reverse(second);

		System.out.println(first);
		System.out.println(second);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str1 = scan.next();
		String str2 = scan.next();

		System.out.println(editDistance(str1, str2));
	}

}
