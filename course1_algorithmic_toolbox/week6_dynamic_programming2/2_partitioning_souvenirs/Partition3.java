import java.util.Arrays;
import java.util.Scanner;

public class Partition3 {

	private static int partition3(int[] A) {
		int sum = Arrays.stream(A).sum();

		if ((sum % 3) != 0) {
			return 0;
		}

		boolean[][][] table = new boolean[A.length + 1][A.length + 1][(sum / 3) + 1];

		for (int i = 0; i <= A.length; i++) {
			table[i][0][0] = true;
		}

		for (int i = 0; i <= A.length; i++) {
			table[0][i][0] = true;
		}

		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= A.length; j++) {
				for (int s = 1; s <= sum / 3; s++) {
					boolean val = false;
					if (s >= A[i - 1]) {
						val = table[i - 1][j][s - A[i - 1]];
					}

					if (s >= A[j - 1]) {
						val = val || table[i][j - 1][s - A[j - 1]];
					}

					val = val || table[i - 1][j - 1][s];

					table[i][j][s] = val;
				}
			}

		}

		return table[A.length][A.length][sum / 3] ? 1 : 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		System.out.println(partition3(A));

//		singleTest();
	}

	// TESTING
	private static void singleTest() {
		System.out.println("*****************************************");
		System.out.println("************** SINGLE TEST **************");
		System.out.println("*****************************************");

		int[] A = new int[]{3, 3, 3, 3};
		System.out.println("Sample 1: " + partition3(A));

		A = new int[]{40};
		System.out.println("Sample 2: " + partition3(A));

		A = new int[]{17, 59, 34, 57, 17, 23, 67, 1, 18, 2, 59};
		System.out.println("Sample 3: " + partition3(A));

		A = new int[]{1, 2, 3, 4, 5, 5, 7, 7, 8, 10, 12, 19, 25};
		System.out.println("Sample 4: " + partition3(A));
	}
}

