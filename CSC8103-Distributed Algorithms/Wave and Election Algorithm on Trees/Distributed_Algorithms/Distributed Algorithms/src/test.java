import java.io.FileNotFoundException;
import java.io.PrintStream;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// UNCOMMENT TO SEND OUTPUT TO TEXT FILE OTHERWISE OUTPUTS TO CONSOLE
		 PrintStream out = new PrintStream("UnbalancedElection.txt" );
		 System.setOut(out);
		Tree t = new Tree();

		// UNCOMMENT TO CONSTRUCT DIFFERENT TYPES OF TREE
		// ARBITRARY TREE
		// t.tree1();
		// BALANCED BINARY TREE
		// t.tree2();
		// UNBALANCED BINARY TREE
		 t.tree3();

		// UNCOMMENT TO CHANGE ALGORITHM
		// WAVE ALGORITHM
		// t.wave();
		// ELECTION ALGORITHM
		 t.election();

	}

}
