import java.util.Scanner;

public class Change {

	private static int getChange(int m) {
		int num = 0;
		int[] denominations = {10, 5, 1};

		for (int denomination : denominations) {
			int coin = 0;
			while ((coin + 1) * denomination <= m) {
				coin++;
			}

			m = m - coin * denomination;
			num += coin;

			if (m == 0) {
				break;
			}

		}

		return num;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		System.out.println(getChange(m));

	}
}

