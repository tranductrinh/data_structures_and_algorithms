import java.util.Arrays;
import java.util.Scanner;

public class SubsetSum {

	private static int partition2(int[] A) {
		int sum = Arrays.stream(A).sum();

		boolean[][] table = new boolean[A.length + 1][sum + 1];

		for (int i = 0; i <= A.length; i++) {
			table[i][0] = true;
		}

		for (int i = 1; i <= A.length; i++) {
			for (int s = 1; s <= sum / 2; s++) {
				table[i][s] = s >= A[i - 1] ? table[i - 1][s - A[i - 1]] || table[i - 1][s] : table[i - 1][s];
			}
		}

		return table[A.length][sum / 2] ? 1 : 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		System.out.println(partition2(A));
	}
}

