import java.util.HashMap;
import java.util.HashSet;

public abstract class Node {

	// Node ID
	private int ID;
	// Message flag- has the node sent a message?(initially false)
	private boolean mFlag = false;
	// ID of silent node(initially null)
	private Integer silent = null;
	// Map of all nodes in the tree
	private static HashMap<Integer, Node> nodesInTree = new HashMap<Integer, Node>();
	// Map of node's neighbours
	private HashMap<Integer, Boolean> neighbours = new HashMap<Integer, Boolean>();

	// Constructor
	// Params: id-Node ID
	// nei-Set of neighbours
	public Node(int id, HashSet<Integer> nei) {
		// Node must have neighbours
		if (nei == null || nei.size() == 0)
			throw new IllegalArgumentException("Node must have neighbours");
		this.ID = id;
		for (int i : nei)
			// Initial decision will be false since the node won't have received
			// a message
			this.neighbours.put(i, false);
		// Add this node to the tree
		nodesInTree.put(ID, this);
	}

	// Clears the tree
	public static void clearTree() {
		nodesInTree.clear();
	}

	// Returns a node from the tree
	// Params: id-ID of node
	public static Node getNode(int id) {
		return nodesInTree.get(id);
	}

	// Set the message flag to true
	public void setFlag() {
		mFlag = true;
	}

	// Return the status of the message flag
	public boolean getFlag() {
		return mFlag;
	}

	// Return the node's ID
	public int getID() {
		return ID;
	}

	// Assigns the silent node
	public void setSilent() {
		for (int n : neighbours.keySet()) {
			if (!neighbours.get(n)) {
				// Silent node is set as first node where decision is false-
				// must ensure numberOfNeighbours returns 1 to confirm this as a
				// silent node
				silent = n;
				break;
			}
		}
	}

	// Return silent node
	public int getSilent() {
		return silent;
	}

	// Return HashMap of neighbours
	public HashMap<Integer, Boolean> getNeighbours() {
		return neighbours;
	}

	// Return the number of neighbours that haven't recieved a message
	public int numberOfNeighbours() {
		int i = 0;
		for (int n : neighbours.keySet()) {
			if (!neighbours.get(n))
				i++;
		}
		// If i=0 then the algorithm will have finished
		// If i=1 then a silent node is found
		return i;
	}

	// Each node requires a decision method
	abstract boolean decide();

}
