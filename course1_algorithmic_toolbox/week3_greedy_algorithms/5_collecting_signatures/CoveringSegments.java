import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Comparator.naturalOrder;

public class CoveringSegments {

	private static int[] optimalPoints(Segment[] segments) {
		if (segments.length == 0) {
			return new int[]{};
		}

		if (segments.length == 1) {
			return new int[]{segments[0].end};
		}

		sortSegmentByEndAsc(segments);

		List<Integer> points = new ArrayList<>();
		Segment currentSegment = segments[0];
		int lastConvergedEnd = currentSegment.end;
		for (int i = 1; i < segments.length; i++) {
			if (segments[i].start <= lastConvergedEnd && lastConvergedEnd <= segments[i].end) {
				lastConvergedEnd = Math.min(segments[i].end, lastConvergedEnd);
				continue;
			}

			points.add(lastConvergedEnd);
			currentSegment = segments[i];
			lastConvergedEnd = currentSegment.end;
		}

		if (currentSegment.start <= lastConvergedEnd && lastConvergedEnd <= currentSegment.end) {
			points.add(lastConvergedEnd);
		} else {
			points.add(segments[segments.length - 1].end);
		}

		return points.stream().sorted(naturalOrder()).mapToInt(value -> value).toArray();
	}

	private static void sortSegmentByEndAsc(Segment[] segments) {
		for (int i = 0; i < segments.length - 1; i++) {
			for (int j = i + 1; j < segments.length; j++) {
				if (segments[i].end > segments[j].end) {
					swap(segments, i, j);
				}
			}
		}
	}

	private static void swap(Segment[] segments, int i, int j) {
		Segment tmp = new Segment(segments[i].start, segments[i].end);
		segments[i] = segments[j];
		segments[j] = tmp;
	}

	private static class Segment {
		int start, end;

		Segment(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Segment[] segments = new Segment[n];
		for (int i = 0; i < n; i++) {
			int start, end;
			start = scanner.nextInt();
			end = scanner.nextInt();
			segments[i] = new Segment(start, end);
		}
		int[] points = optimalPoints(segments);
		System.out.println(points.length);
		for (int point : points) {
			System.out.print(point + " ");
		}

	}

}

