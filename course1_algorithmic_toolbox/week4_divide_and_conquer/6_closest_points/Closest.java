import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Closest {

	private static BufferedReader reader;
	private static PrintWriter writer;
	private static StringTokenizer tokenizer = new StringTokenizer("");

	private static double fastMinimalDistance(Point[] points) {
		Arrays.sort(points, (p1, p2) -> (int) (p1.x - p2.x));
		return minimalDistance(points, 0, points.length);
	}

	private static double minimalDistance(Point[] points, int left, int right) {
		if (left + 2 >= right) {
			return naiveMinimalDistance(points, left, right);
		}

		int middle = (right + left) / 2;

		double d = Math.min(
				minimalDistance(points, left, middle),
				minimalDistance(points, middle, right)
		);

		if (d == 0) {
			return 0;
		}

		Point[] stripPoints = stripPoints(points, d, left, middle, right);

		Arrays.sort(stripPoints, (p1, p2) -> (int) (p1.y - p2.y));

		int len = stripPoints.length;

		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < Math.min(i + 6, len); j++) {
				d = Math.min(distance(stripPoints[i], stripPoints[j]), d);
			}
		}

		return d;
	}

	private static Point[] stripPoints(Point[] points, double d, int left, int middle, int right) {
		List<Point> stripPoints = new ArrayList<>();

		long splitter = points[middle].x;

		// Keep only point who x-distance is in center partition
		for (int i = left; i < right; i++) {
			if (points[i].x >= splitter - d && points[i].x <= splitter + d) {
				stripPoints.add(points[i]);
			}
		}

		Point[] res = new Point[stripPoints.size()];
		return stripPoints.toArray(res);
	}

	private static double naiveMinimalDistance(Point[] points, int left, int right) {
		double ans = Double.POSITIVE_INFINITY;

		for (int i = left; i < right - 1; i++) {
			for (int j = i + 1; j < right; j++) {
				double distance = distance(points[i], points[j]);
				if (distance == 0) {
					return 0;
				} else {
					if (ans >= distance) {
						ans = distance;
					}
				}
			}
		}

		return ans;
	}

	private static double distance(Point x, Point y) {
		return Math.sqrt(Math.pow((x.x - y.x), 2) + Math.pow((x.y - y.y), 2));
	}

	public static void main(String[] args) throws Exception {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(System.out);
		int n = nextInt();

		Point[] points = new Point[n];

		for (int i = 0; i < n; i++) {
			points[i] = new Point(nextInt(), nextInt());
		}

		System.out.println(fastMinimalDistance(points));
		writer.close();

//		stressTest();
//		singleTest();

	}

	private static String next() {
		while (!tokenizer.hasMoreTokens()) {
			String w = null;
			try {
				w = reader.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (w == null)
				return null;
			tokenizer = new StringTokenizer(w);
		}
		return tokenizer.nextToken();
	}

	private static int nextInt() {
		return Integer.parseInt(next());
	}

	private static class Point {
		long x, y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}

	// TESTING
	private static void stressTest() {
		System.out.println("*****************************************");
		System.out.println("************** Stress test **************");
		System.out.println("*****************************************");

		int testCase = 1000;
		int count = 0;
		for (int i = 0; i < testCase; i++) {
			Random random = new Random();
			int n = random.nextInt(100) + 2;
			Point[] points = new Point[n];
			for (int j = 0; j < n; j++) {
				points[j] = new Point(
						random.nextInt(1000000),
						random.nextInt(1000000)
				);
			}

			Point[] backupPoints = Arrays.copyOf(points, points.length);

			double naiveResult = naiveMinimalDistance(points, 0, points.length);
			double fastResult = fastMinimalDistance(points);

			if (naiveResult != fastResult) {
				System.out.println("--------- Failed test case: " + i + "-th ---------");
				System.out.println("Given " + backupPoints.length + " points: " + Arrays.toString(backupPoints));
				System.out.println("Naive result: " + naiveResult);
				System.out.println("Fast result: " + fastResult);
				count++;
			}
		}

		System.out.println("*****************************************");
		if (count > 0) {
			System.out.println("NOT OK " + count + "/" + testCase);
		} else {
			System.out.println("OK");
		}
		System.out.println("*****************************************");
	}

	private static void singleTest() {
		int n = 4;
		Point[] points = new Point[n];
		points[0] = new Point(2, 6);
		points[1] = new Point(5, 4);
		points[2] = new Point(6, 6);
		points[3] = new Point(6, 1);

		Point[] backupPoints = Arrays.copyOf(points, points.length);

		double naiveResult = naiveMinimalDistance(points, 0, points.length);
		double fastResult = fastMinimalDistance(points);

		if (naiveResult != fastResult) {
			System.out.println("--------- Failed single case ---------");
			System.out.println("Given " + backupPoints.length + " points: " + Arrays.toString(backupPoints));
			System.out.println("Naive result: " + naiveResult);
			System.out.println("Fast result: " + fastResult);
		}
	}

}
