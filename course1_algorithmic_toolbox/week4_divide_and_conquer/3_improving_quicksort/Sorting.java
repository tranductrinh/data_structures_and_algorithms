import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Sorting {

	private static Random random = new Random();

	private static int[] partition3(int[] numbers, int left, int right) {
		int pivot = numbers[left];

		int m1 = left, m2 = m1;
		for (int i = left + 1; i <= right; i++) {
			if (numbers[i] < pivot) {
				m1++;
				m2 = m1;
				swap(numbers, i, m1);
			}

			if (numbers[i] == pivot) {
				m2++;
				swap(numbers, i, m2);
			}
		}

		if (numbers[m2] == pivot) {
			swap(numbers, left, m1);
		} else {
			swap(numbers, left, m2);
		}

		return new int[]{m1, m2};

	}

	private static void randomizedQuickSort3(int[] numbers, int left, int right) {
		if (left >= right) {
			return;
		}
		int randomPivot = random.nextInt(right - left + 1) + left;
		swap(numbers, left, randomPivot);
		int[] pivotIndexes = partition3(numbers, left, right);
		randomizedQuickSort3(numbers, left, pivotIndexes[0] - 1);
		randomizedQuickSort3(numbers, pivotIndexes[1] + 1, right);
	}

	private static int partition2(int[] numbers, int left, int right) {
		int pivot = numbers[left];

		int j = left;
		for (int i = left + 1; i <= right; i++) {
			if (numbers[i] <= pivot) {
				j++;
				swap(numbers, i, j);
			}
		}

		swap(numbers, left, j);

		return j;
	}

	private static void randomizedQuickSort2(int[] numbers, int left, int right) {
		if (left >= right) {
			return;
		}
		int randomPivot = random.nextInt(right - left + 1) + left;
		swap(numbers, left, randomPivot);
		int m = partition2(numbers, left, right);
		randomizedQuickSort2(numbers, left, m - 1);
		randomizedQuickSort2(numbers, m + 1, right);
	}

	private static void swap(int[] a, final int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		randomizedQuickSort3(a, 0, n - 1);

		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
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

