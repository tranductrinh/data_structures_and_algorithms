import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DifferentSummands {

	private static List<Integer> optimalSummands(int n) {
		Set<Integer> summands = new HashSet<>();

		long sum = 0;

		for (int i = 1; i <= n; i++) {
			int left = (int) (n - (sum + i));

			if (left == i || summands.contains(left)) {
				continue;
			}

			if (left < 0) {
				break;
			}

			summands.add(i);
			sum += i;
		}

		return new ArrayList<>(summands);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<Integer> summands = optimalSummands(n);
		System.out.println(summands.size());
		for (Integer summand : summands) {
			System.out.print(summand + " ");
		}
	}
}

