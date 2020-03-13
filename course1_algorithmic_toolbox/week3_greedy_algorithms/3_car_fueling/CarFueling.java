import java.util.Scanner;

public class CarFueling {

	private static int computeMinRefills(int dist, int tank, int[] stops) {
		if (dist <= tank) {
			return 0;
		}

		int numOfRefilling = 0;
		int lastRefillingStop = 0;
		for (int i = 0; i < stops.length; i++) {
			int currentStop = stops[i];
			int nextStop;

			if (i + 1 == stops.length) {
				nextStop = dist;
			} else {
				nextStop = stops[i + 1];
			}

			if (nextStop - currentStop > tank) {
				return -1;
			}

			if (currentStop - lastRefillingStop <= tank && nextStop - lastRefillingStop <= tank) {
				continue;
			}

			lastRefillingStop = currentStop;
			numOfRefilling++;
		}

		return numOfRefilling;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int dist = scanner.nextInt();
		int tank = scanner.nextInt();
		int n = scanner.nextInt();
		int stops[] = new int[n];
		for (int i = 0; i < n; i++) {
			stops[i] = scanner.nextInt();
		}

		System.out.println(computeMinRefills(dist, tank, stops));
	}
}
