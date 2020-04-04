import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PrimitiveCalculator {

	private static List<Integer> fastOptimalSequence(int n) {
		int[] minNumOps = new int[n + 1];
		minNumOps[0] = 0;
		minNumOps[1] = 1;

		for (int number = 2; number <= n; number++) {
			int numOpsDivide2 = number % 2 == 0 ? minNumOps[number / 2] : Integer.MAX_VALUE;
			int numOpsDivide3 = number % 3 == 0 ? minNumOps[number / 3] : Integer.MAX_VALUE;
			int numOpsAdd1 = minNumOps[number - 1];
			minNumOps[number] = Math.min(Math.min(numOpsDivide2, numOpsDivide3), numOpsAdd1) + 1;
		}

		return greedyOptimalSequence(n, minNumOps);
	}

	private static List<Integer> greedyOptimalSequence(int n, int[] minNumOps) {
		List<Integer> sequence = new ArrayList<>();
		while (n > 1) {
			sequence.add(n);
			int numOpsDivide2 = n % 2 == 0 ? minNumOps[n / 2] : Integer.MAX_VALUE;
			int numOpsDivide3 = n % 3 == 0 ? minNumOps[n / 3] : Integer.MAX_VALUE;
			int numOpsAdd1 = minNumOps[n - 1];

			int min = Math.min(Math.min(numOpsDivide2, numOpsDivide3), numOpsAdd1);

			if (min == numOpsDivide2) {
				n = n / 2;
			} else if (min == numOpsDivide3) {
				n = n / 3;
			} else {
				n = n - 1;
			}
		}

		sequence.add(1);

		Collections.reverse(sequence);
		return sequence;
	}

	private static List<Integer> naiveOptimalSequence(int n) {
		if (n == 1) {
			return Collections.singletonList(1);
		}

		List<Node> nodes = new ArrayList<>();
		nodes.add(new Node(1));

		Node found = null;

		while (found == null) {
			List<Node> childNodes = new ArrayList<>();
			for (Node currentNode : nodes) {
				if (currentNode.value * 2 == n) {
					found = new Node(currentNode, currentNode.value * 2);
					break;
				} else if (currentNode.value * 3 == n) {
					found = new Node(currentNode, currentNode.value * 3);
					break;
				} else if (currentNode.value + 1 == n) {
					found = new Node(currentNode, currentNode.value + 1);
					break;
				} else {
					childNodes.add(new Node(currentNode, currentNode.value * 2));
					childNodes.add(new Node(currentNode, currentNode.value * 3));
					childNodes.add(new Node(currentNode, currentNode.value + 1));
				}
			}
			nodes = childNodes;
		}

		List<Integer> sequence = new ArrayList<>();
		while (found != null) {
			sequence.add(found.value);
			found = found.parent;
		}

		Collections.reverse(sequence);
		return sequence;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<Integer> sequence = fastOptimalSequence(n);
		System.out.println(sequence.size() - 1);
		for (Integer x : sequence) {
			System.out.print(x + " ");
		}

//		stressTest();
//		singleTest();
	}

	private static class Node {

		private Node parent;
		private int value;

		public Node(int value) {
			this.parent = null;
			this.value = value;
		}

		public Node(Node parent, int value) {
			this.parent = parent;
			this.value = value;
		}
	}

	// TEST
	private static void stressTest() {
		System.out.println("*****************************************");
		System.out.println("************** STRESS TEST **************");
		System.out.println("*****************************************");

		int testCase = 1000;
		int count = 0;
		for (int i = 0; i < testCase; i++) {
			Random random = new Random();
			int n = random.nextInt(1000) + 1;
			List<Integer> naiveResult = naiveOptimalSequence(n);
			List<Integer> fastResult = fastOptimalSequence(n);

			if (!naiveResult.toString().equals(fastResult.toString()) && naiveResult.size() != fastResult.size()) {
				System.out.println("--------- Failed test case: " + i + "-th ---------");
				System.out.println("Naive result: " + naiveResult);
				System.out.println("Fast result: " + fastResult);
				count++;
			}
		}

		System.out.println("*****************************************");
		if (count > 0) {
			System.out.println("STRESS TEST: NOT OK " + count + "/" + testCase);
		} else {
			System.out.println("STRESS TEST: OK");
		}
		System.out.println("*****************************************");
	}

	private static void singleTest() {
		System.out.println("*****************************************");
		System.out.println("************** SINGLE TEST **************");
		System.out.println("*****************************************");

		int n = 96234;
		boolean pass = true;

		List<Integer> naiveResult = naiveOptimalSequence(n);
		List<Integer> fastResult = fastOptimalSequence(n);

		if (!naiveResult.toString().equals(fastResult.toString()) && naiveResult.size() != fastResult.size()) {
			pass = false;
			System.out.println("--------- Failed single test case ---------");
			System.out.println("Naive result: " + naiveResult);
			System.out.println("Fast result: " + fastResult);
		}

		System.out.println("*****************************************");
		if (!pass) {
			System.out.println("SINGLE TEST: NOT OK");
		} else {
			System.out.println("SINGLE TEST: OK");
		}
		System.out.println("*****************************************");
	}
}

