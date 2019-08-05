import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

public class Tree {
	// Map of nodes that comprise the tree- Integer -node id, HashSet<Integer>-
	// Neighbours
	private HashMap<Integer, HashSet<Integer>> selected = new HashMap<Integer, HashSet<Integer>>();
	// Random number generator to select processes in algorithm execution
	private static Random rand = new Random();
	// The number of nodes- specification dictates this must be greater than 6
	public final int N = 10;

	// Prints the neighbours of each node- textual representation of the tree
	private void getNeighbours() {
		// Iterates over the keyset of the added nodes and returns the value-
		// the set of neighbours
		for (int n : selected.keySet())
			System.out.println("Neighbours of node " + n + ": " + selected.get(n));
		System.out.println();
	}

	// adds nodes to the selection
	public void addNodes() {
		// Iterates N times
		for (int i = 0; i < N; i++)
			// adds a new node with i as an id and an empty set of neighbours
			selected.put(i, new HashSet<Integer>());

	}

	// Constructs an arbitrary tree- each node is connected to the root node
	public void tree1() {
		// Adds N nodes
		addNodes();
		// connects each node to the zeroth node
		for (int i = 1; i < N; i++) {
			selected.get(i).add(0);
			selected.get(0).add(i);
		}
		// Prints out a textual representation of the tree
		System.out.println("Number of Nodes " + N);
		System.out.println("Arbitrary Tree:\n");
		getNeighbours();
	}

	// Constructs a balanced tree- all nodes have a left and right child(up to
	// the number of nodes)
	public void tree2() {
		// add nodes
		addNodes();
		// for all nodes other than the root node
		for (int i = 1; i < N; i++) {
			// connects nodes in a balanced way
			int j = ((i - 1) / 2);
			selected.get(i).add(j);
			selected.get(j).add(i);
		}
		// Prints out a textual representation of the tree
		System.out.println("Number of Nodes " + N);
		System.out.println("Balanced Binary Tree:\n");
		getNeighbours();
	}

	// Constructs an unbalanced tree- all nodes are pushed to the left
	public void tree3() {
		// Add nodes
		addNodes();
		// Force root node to have 2 children
		selected.get(0).add(1);
		selected.get(0).add(2);
		selected.get(1).add(0);
		selected.get(2).add(0);
		// Push other nodes to the left
		for (int i = 3; i < N; i++) {
			int j = ((i - 3) + (i % 2));
			selected.get(i).add(j);
			selected.get(j).add(i);
		}
		// Print out textual representation of the tree
		System.out.println("Number of Nodes " + N);
		System.out.println("Unbalanced Binary Tree:\n");
		getNeighbours();
	}

	// Run the wave algorithm
	public void wave() {
		// Number of deciding nodes
		int deciding = 0;
		System.out.println("Wave algorithm:\n");
		// Construct nodes according to the map of selected nodes
		ArrayList<WaveNode> tree = new ArrayList<WaveNode>();
		for (int n : selected.keySet())
			tree.add(new WaveNode(n, selected.get(n)));
		// Execute 100N rounds of the algorithm
		for (int i = 1; i < N * 100; i++) {
			// Set of ID's of the Nodes that will run the algorithm this round
			LinkedHashSet<Integer> processes = new LinkedHashSet<Integer>();
			// Generate a new random number each round
			int R = rand.nextInt(N + 1);
			System.out.println(R + " processes");
			// Select random IDs and add them to the set that will run
			while (processes.size() < R) {
				processes.add(rand.nextInt(N));

			}
			System.out.println(processes);
			// For the selected processes
			for (int n : processes) {
				// If the node decides then print the iteration and increase the
				// deciding nodes
				if (tree.get(n).decide()) {
					System.out.println("Node " + tree.get(n).getID() + " decides in " + i + "th iteration");
					deciding++;
				}

			}
			//Algorithm terminates after both nodes that will decide have done so
			if (deciding == 2) {
				System.out.println("Algorithm terminates after " + i + " iterations");
				break;
			}
		}
		System.out.println("Number of processes which decide in wave algorithm: " + deciding);
	}

	// Run the election algorithm
	public void election() {
		// Number of nodes that decide
		int deciding = 0;
		// The elected node(this set should only have one member)
		HashSet<Integer> elected = new HashSet<Integer>();
		System.out.println("Election Algorithm:\n");
		// Construct nodes according to the selected nodes
		ArrayList<ElectionNode> tree = new ArrayList<ElectionNode>();
		for (int n : selected.keySet())
			tree.add(new ElectionNode(n, selected.get(n)));
		// Select a random node in the tree to initiate and start the algorithm
		tree.get(rand.nextInt(N)).sendWake();
		// Execute 100N iterations of the algorithm
		for (int i = 1; i < N * 100; i++) {
			// Set of IDs that will run the algorithm this round
			LinkedHashSet<Integer> processes = new LinkedHashSet<Integer>();
			// Select a new random number each iteration
			int R = rand.nextInt(N + 1);
			System.out.println(R + " processes");
			// Select a random number of node IDs
			while (processes.size() < R) {
				processes.add(rand.nextInt(N));

			}
			System.out.println(processes);
			// For the selected processes
			for (int n : processes) {
				// If the node decides
				if (tree.get(n).decide()) {
					System.out.println("Node " + tree.get(n).getID() + " decides in " + i + "th iteration");
					// Add the minimum node
					elected.add(tree.get(n).getMinimum());
					// Increase deciding nodes
					deciding++;
				}

			}
			//Algorithm terminates when all nodes have decided
			if (deciding == N) {
				System.out.println("Algorithm terminates after " + i + " iterations ");
				break;
			}
		}
		System.out.println("Number of processes which decide in election algorithm: " + deciding);
		System.out.println("Leader(Minimal ID) " + elected + "\n");
	}

}
