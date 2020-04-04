import java.util.Scanner;

public class ChangeDP {
	private static int getChange(int money) {
		int[] minNumCoins = new int[money + 1];
		int[] coins = {1, 3, 4};

		for (int m = 1; m <= money; m++) {
			minNumCoins[m] = Integer.MAX_VALUE;
			for (int coin : coins) {
				if (m >= coin) {
					int numCoins = minNumCoins[m - coin] + 1;
					if (numCoins < minNumCoins[m]) {
						minNumCoins[m] = numCoins;
					}
				}
			}
		}
		return minNumCoins[money];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		System.out.println(getChange(m));

	}
}

