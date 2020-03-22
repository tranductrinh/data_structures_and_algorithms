import java.util.Random;
import java.util.Scanner;

public class PointsAndSegments {

	private static Random random = new Random();

	private static int[] fastCountSegments(Segment[] segments, int[] points) {
		int[] cnt = new int[points.length];

		TypedSegment[] allSegments = new TypedSegment[points.length + segments.length * 2];

		int i = 0;
		for (Segment segment : segments) {
			allSegments[i++] = new TypedSegment(segment.start, 'l');
			allSegments[i++] = new TypedSegment(segment.end, 'r');
		}

		i = 0;
		for (int j = 0; j < points.length; j++) {
			allSegments[segments.length * 2 + (i++)] = new PointSegment(points[j], j);
		}

		sortSegmentStartAsc(allSegments, 0, allSegments.length - 1);

		PointSegment pivot = null;
		int count = 0;
		for (TypedSegment segment : allSegments) {
			if (segment.type == 'l') {
				count++;
			} else if (segment.type == 'r') {
				if (pivot != null && pivot.start != segment.start) {
					pivot = null;
				}
				if (count > 0) {
					count--;
				}
			} else if (segment.type == 'p') {
				pivot = (PointSegment) segment;
				cnt[pivot.index] = count;
			}
		}

		return cnt;
	}

	private static void sortSegmentStartAsc(TypedSegment[] segments, int left, int right) {
		if (left >= right) {
			return;
		}
		int randomPivot = random.nextInt(right - left + 1) + left;
		swap(segments, left, randomPivot);
		int[] pivotIndexes = partition3ByStartAsc(segments, left, right);
		sortSegmentStartAsc(segments, left, pivotIndexes[0] - 1);
		sortSegmentStartAsc(segments, pivotIndexes[1] + 1, right);
	}

	private static int[] partition3ByStartAsc(TypedSegment[] segments, int left, int right) {
		TypedSegment pivot = segments[left];

		int m1 = left, m2 = m1;
		for (int i = left + 1; i <= right; i++) {
			if (segments[i].start < pivot.start
					|| (segments[i].start == pivot.start && pivot.type == 'p' && segments[i].type == 'l')
					|| (segments[i].start == pivot.start && pivot.type == 'r' && segments[i].type == 'p')
					|| (segments[i].start == pivot.start && pivot.type == 'r' && segments[i].type == 'l')) {
				m1++;
				m2 = m1;
				swap(segments, i, m1);
			}

			if (segments[i].start == pivot.start && segments[i].type == pivot.type) {
				m2++;
				swap(segments, i, m2);
			}
		}

		if (segments[m2].start == pivot.start && segments[m2].type == pivot.type) {
			swap(segments, left, m1);
		} else {
			swap(segments, left, m2);
		}

		return new int[]{m1, m2};

	}

	private static void swap(TypedSegment[] segments, final int i, int j) {
		TypedSegment tmp = segments[i];
		segments[i] = segments[j];
		segments[j] = tmp;
	}

	private static int[] naiveCountSegments(Segment[] segments, int[] points) {
		int[] cnt = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < segments.length; j++) {
				if (segments[j].start <= points[i] && points[i] <= segments[j].end) {
					cnt[i]++;
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m;
		n = scanner.nextInt();
		m = scanner.nextInt();
		Segment[] segments = new Segment[n];
		int[] points = new int[m];
		for (int i = 0; i < n; i++) {
			segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());

		}
		for (int i = 0; i < m; i++) {
			points[i] = scanner.nextInt();
		}

		int[] cnt = fastCountSegments(segments, points);
		for (int x : cnt) {
			System.out.print(x + " ");
		}
	}

	private static class AbstractSegment {
		int start;

		AbstractSegment(int start) {
			this.start = start;
		}

	}

	private static class Segment extends AbstractSegment {
		int end;

		Segment(int start, int end) {
			super(start);
			this.end = end;
		}

		@Override
		public String toString() {
			return "(" + start + "," + end + ")";
		}
	}

	private static class TypedSegment extends AbstractSegment {
		char type;

		TypedSegment(int start, char type) {
			super(start);
			this.type = type;
		}

		@Override
		public String toString() {
			return "(" + start + "," + type + ")";
		}
	}

	private static class PointSegment extends TypedSegment {
		int index;

		PointSegment(int start, int index) {
			super(start, 'p');
			this.index = index;
		}
	}

}

