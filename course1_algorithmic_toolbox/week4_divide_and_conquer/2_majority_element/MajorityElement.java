import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MajorityElement {

	private static int getMajorityElement(int[] a, int left, int right) {
		if (left + 1 == right) {
			return a[left];
		}

		int mid = (left + right) / 2;
		int majorityCandidateOfFirstHalve = getMajorityElement(a, left, mid);
		int majorityCandidateOfSecondHalve = getMajorityElement(a, mid, right);

		if (majorityCandidateOfFirstHalve != -1) {
			if (count(a, left, right, majorityCandidateOfFirstHalve) > ((right - left) / 2)) {
				return majorityCandidateOfFirstHalve;
			}
		}

		if (majorityCandidateOfSecondHalve != -1) {
			if (count(a, left, right, majorityCandidateOfSecondHalve) > ((right - left) / 2)) {
				return majorityCandidateOfSecondHalve;
			}
		}

		return -1;
	}

	private static int count(int[] a, int left, int right, int num) {
		int count = 0;
		for (int i = left; i < right; i++) {
			if (a[i] == num) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		if (getMajorityElement(a, 0, a.length) != -1) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			try {
				br = new BufferedReader(new InputStreamReader(stream));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}

