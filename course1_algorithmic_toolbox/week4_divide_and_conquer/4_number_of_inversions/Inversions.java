import java.util.Scanner;

public class Inversions {

	private static long getNumberOfInversionsSlow(int[] numbers) {
		long numberOfInversions = 0;
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] > numbers[j]) {
					numberOfInversions++;
				}
			}
		}
		return numberOfInversions;
	}

	private static long getNumberOfInversions(int[] numbers, int[] resultSortedNumbers, int left, int right) {
		long numberOfInversions = 0;

		if (left + 1 >= right) {
			return numberOfInversions;
		}

		int middle = (left + right) / 2;
		numberOfInversions += getNumberOfInversions(numbers, resultSortedNumbers, left, middle);
		numberOfInversions += getNumberOfInversions(numbers, resultSortedNumbers, middle, right);
		numberOfInversions += merge(numbers, resultSortedNumbers, left, middle, right);

		return numberOfInversions;
	}

	private static long merge(int[] numbers, int[] resultSortedNumbers, int left, int middle, int right) {
		long numberOfInversions = 0;

		int i = left;

		int iFirstHalve = left;
		int iSecondHalve = middle;

		while (iFirstHalve < middle || iSecondHalve < right) {
			int b = iFirstHalve == middle ? 0 : numbers[iFirstHalve];
			int c = iSecondHalve == right ? 0 : numbers[iSecondHalve];

			if (b != 0) {
				if (c != 0 && b > c) {
					numberOfInversions += (middle - iFirstHalve);
					resultSortedNumbers[i++] = c;
					iSecondHalve++;
				} else {
					resultSortedNumbers[i++] = b;
					iFirstHalve++;
				}
			} else {
				resultSortedNumbers[i++] = c;
				iSecondHalve++;
			}

		}

		for (i = left; i < right; i++) {
			numbers[i] = resultSortedNumbers[i];
		}

		return numberOfInversions;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scanner.nextInt();
		}

		int[] resultSortedNumbers = new int[n];

		System.out.println(getNumberOfInversions(numbers, resultSortedNumbers, 0, numbers.length));
	}
}

