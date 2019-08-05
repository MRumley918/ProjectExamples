import java.util.HashSet;
import java.util.LinkedHashMap;

public class ElectionNode extends Node {
	// Is the node awake(received a wake message from all neighbours)
	private boolean wakeFlag = false;
	// Has the node sent it's wake message?
	private boolean sendFlag = false;
	// The number of wake messages received
	private int WM = 0;
	// Set of ID's of received wake messages
	private HashSet<Integer> wakeMessages = new HashSet<Integer>();
	// Minimum ID
	private int minimum;
	// Map of messages sent by node(ID,Minimum)
	private LinkedHashMap<Integer, Integer> message = new LinkedHashMap<Integer, Integer>();

	// Constructor
	public ElectionNode(int id, HashSet<Integer> nei) {
		super(id, nei);
		// Minimum ID at outset is this node- only ID the node is aware of at
		// this point
		minimum = id;
	}

	// Return the minimum ID
	public int getMinimum() {
		return minimum;
	}

	// Adds an ID to the set of wake messages
	// Params: w-ID to add
	private void receiveWake(int w) {
		wakeMessages.add(w);
	}

	// Send a wake to a specific node from this node
	// Params: id- ID of node to send wake to
	private void addWake(int id) {
		Node n = getNode(id);
		// Cast the node to an Election Node and recieve a wake from the ID of
		// this node
		((ElectionNode) n).receiveWake(getID());
		System.out.println("Node " + getID() + " sends wake up message to " + id);
	}

	// Send a wake message to all neighbours of this node
	public void sendWake() {
		// set the send flag- the node has sent its wake up call
		sendFlag = true;
		System.out.println("Node " + getID() + " wakes up, sends message to all neighbours");
		// send wake to all neighbours
		for (int n : getNeighbours().keySet())
			addWake(n);
	}

	// add the message to the message list
	// Params: id-ID of node being sent from
	// min-Minimum ID of node
	private void addMessage(int id, int min) {
		message.put(id, min);
	}

	// updates the neighbour's boolean values based on the messages received
	public void receiveMessage() {
		// for all IDs in the message set
		for (int n : message.keySet()) {
			// If the number of neighbours is 1 or 0 then break from the loop-
			// silent neighbour has arrived
			if (numberOfNeighbours() <= 1)
				break;
			// Check to ensure the node is a neighbour of this node
			if (getNeighbours().containsKey(n)) {
				// Update minimum
				minimum = Math.min(message.get(n), minimum);
				// Update corresponding value in neighbours set
				getNeighbours().put(n, true);
				System.out.println("Node " + getID() + " recieves message from " + n);
			}
		}
		if (numberOfNeighbours() > 1)
			message.clear();
	}

	// Sends a message to a specified node from this node
	// Params: id-ID of node to send message to
	public void sendMessage(int id) {
		// Gets a node from the given ID
		Node n = getNode(id);
		// Cast to an election node then send the message
		((ElectionNode) n).addMessage(getID(), minimum);
		System.out.println("Node " + getID() + " sends message to " + id);
		// Set the message flag- this node has now sent its message
		setFlag();
	}

	// Decide method coded according to election pseudocode
	// If method returns true then node has decided
	public boolean decide() {
		// If the node has no neighbours it hasn't received a message from then
		// the algorithm is done
		if (numberOfNeighbours() == 0) {
			System.out.println("Node " + getID() + " has finished algorithm");
			return false;
		}
		// If the node is not awake
		if (!wakeFlag) {
			// For all the wake messages
			for (int n : wakeMessages)
				if (getNeighbours().containsKey(n))
					// Keep a running total of the wake messages from neighbours
					WM++;
			wakeMessages.clear();
			// If the node hasn't sent its wake up message then do so now
			if (WM > 0 && sendFlag == false)
				sendWake();
			// If there are wake messages from all neighbours then the node is
			// now awake
			if (WM >= getNeighbours().size()) {
				System.out.println(
						"Node " + getID() + " recieves messages from all neighbours and starts wave algorithm");
				wakeFlag = true;
			}
		}
		// If the node is awake
		if (wakeFlag) {
			// Receive messages
			receiveMessage();
			// If there is only one neighbour that hasn't been heard from then
			// this will be the silent neighbour
			if (numberOfNeighbours() == 1) {
				// Has the node already sent its message?
				if (getFlag() == false) {
					// This node is the silent node
					setSilent();
					// Send a message to the silent node
					sendMessage(getSilent());
				}
				// If the node has received a message from the silent neighbour
				if (message.containsKey(getSilent())) {
					// Update minimum
					minimum = Math.min(minimum, message.get(getSilent()));
					// Update decision in neighbours for the silent node
					getNeighbours().put(getSilent(), true);
					System.out.println("Node " + getID() + " recieves message from " + getSilent());
					// If the ID is the minimum then the node is elected, else
					// it isn't
					if (getID() == minimum)
						System.out.println("Node " + getID() + " elected");
					else
						System.out.println("Node " + getID() + " not elected");
					// Send message to other nodes- diffusion
					for (int n : getNeighbours().keySet())
						if (n != getSilent())
							sendMessage(n);
					// Node has decided
					return true;
				}
				// Else still waiting
				System.out.println("Node " + getID() + " waiting for message from silent neighbour");
			}
		}
		return false;
	}

}
