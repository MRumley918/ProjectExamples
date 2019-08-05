import java.util.HashSet;
import java.util.LinkedHashSet;

public class WaveNode extends Node {
	// Map of messages sent by node(ID)
	private LinkedHashSet<Integer> message = new LinkedHashSet<Integer>();

	// Constructor
	// Params: id-Node ID
	// nei-Set of Neighbours
	public WaveNode(int id, HashSet<Integer> nei) {
		super(id, nei);

	}

	// Updates the neighbours boolean values based on the messages received
	public void recieveMessage() {
		// For all messages
		for (int n : message) {
			// If the number of neighbours is 1 or 0 then break the loop- silent
			// neighbour has shown up
			if (numberOfNeighbours() <= 1)
				break;
			// Check to see if neighbour, then update
			if (getNeighbours().containsKey(n)) {
				getNeighbours().put(n, true);

				System.out.println("Node " + getID() + " recieves message from " + n);
			}
		}
		// If there is more than one neighbour then clear the set
		if (numberOfNeighbours() > 1)
			message.clear();
	}

	// Add a message to the messages set
	// Params: m-ID of node where message comes from
	public void addMessage(int m) {
		message.add(m);
	}

	// Send a message to the silent node (No params needed since no diffusion)
	public void sendMessage() {
		// Get the silent node, cast it to a wave node then send the message
		Node n = getNode(getSilent());
		((WaveNode) n).addMessage(getID());
		System.out.println("Node " + getID() + " sends message to " + getSilent());
		// Set message flag-Node has now sent it's message
		setFlag();
	}

	// Decide method coded based on wave pseudocode
	// If method returns true then node has decided
	public boolean decide() {
		// If the node has no neighbours it hasn't received a message from then
		// the algorithm is done
		if (numberOfNeighbours() == 0) {
			System.out.println("Node " + getID() + " has finished algorithm");
			return false;
		}
		// Receive messages
		recieveMessage();
		// If there is only one neighbour that hasn't been heard from then this
		// will be the silent neighbour
		if (numberOfNeighbours() == 1) {
			// If the node hasn't sent a message
			if (getFlag() == false) {
				// Set the node as a silent neighbour then send the message
				setSilent();
				sendMessage();
			}
			// If the silent node is in the messages
			if (message.contains(getSilent())) {
				// Update decision for silent node
				getNeighbours().put(getSilent(), true);
				System.out.println("Node " + getID() + " recieves message from " + getSilent());
				// Node has decided
				return true;
			}
			// Else it's still waiting
			System.out.println("Node " + getID() + " waiting for message from silent neighbour");
		}
		return false;
	}

}
