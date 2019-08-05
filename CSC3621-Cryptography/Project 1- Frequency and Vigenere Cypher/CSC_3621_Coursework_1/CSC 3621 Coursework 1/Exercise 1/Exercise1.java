import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
 * Author: Michael Rumley
 * Last Modified: 18/10/17
 * Purpose: Decrypt a text file by frequency 
 * 
 */
public class Exercise1 {
	// read in a text file
	public static String readFile(String file) {
		// empty string
		String content = "";
		try {
			// string is all bytes in a file
			content = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return the content string
		return content;
	}

	// method to count the number of each character in string and return as a
	// hashmap(key letter, value occurrences of letter)
	public static LinkedHashMap<Character, Integer> characterCount(String input) {
		// empty hashmap of a character and an integer
		HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
		// puts each letter in the hashmap and sets its initial count to 0(other
		// characters(.,?/ etc) don't count
		charCount.put('a', 0);
		charCount.put('b', 0);
		charCount.put('c', 0);
		charCount.put('d', 0);
		charCount.put('e', 0);
		charCount.put('f', 0);
		charCount.put('g', 0);
		charCount.put('h', 0);
		charCount.put('i', 0);
		charCount.put('j', 0);
		charCount.put('k', 0);
		charCount.put('l', 0);
		charCount.put('m', 0);
		charCount.put('n', 0);
		charCount.put('o', 0);
		charCount.put('p', 0);
		charCount.put('q', 0);
		charCount.put('r', 0);
		charCount.put('s', 0);
		charCount.put('t', 0);
		charCount.put('u', 0);
		charCount.put('v', 0);
		charCount.put('w', 0);
		charCount.put('x', 0);
		charCount.put('y', 0);
		charCount.put('z', 0);
		// converts the input string to an array of characters
		char[] strArray = input.toCharArray();
		// for each character in the array
		for (char c : strArray) {
			// if the character is in the hashmap(i.e. if its one of the 26
			// letters) then the count is increased by 1
			if (charCount.containsKey(c)) {
				charCount.put(c, charCount.get(c) + 1);
			}

		}
		// a new hashmap is created, and then the first hashmap is sorted into
		// this new one with the largest value first
		LinkedHashMap<Character, Integer> sortedCharCount = new LinkedHashMap<>();
		charCount.entrySet().stream().sorted(Map.Entry.<Character, Integer> comparingByValue().reversed())
				.forEachOrdered(x -> sortedCharCount.put(x.getKey(), x.getValue()));
		// this hashmap is returned
		return sortedCharCount;

	}

	// initial decryption by substitution
	private static String decrypt(String input, String cypher) {
		// a new hashmap is created for both the input and the cypher
		HashMap<Character, Integer> inputCount = characterCount(input);
		HashMap<Character, Integer> cypherCount = characterCount(cypher);
		// a list of the keys is made (these will be ordered by occurrence from
		// the hashmap)
		List<Character> inputCharsList = new ArrayList<Character>(inputCount.keySet());
		List<Character> cypherCharsList = new ArrayList<Character>(cypherCount.keySet());
		// the cyphertext is converted to an array of characters
		char[] decrypted = cypher.toCharArray();
		// the lists are converted to character arrays
		char[] inputChar = new char[inputCharsList.size()];
		char[] cypherChar = new char[cypherCharsList.size()];
		inputChar = charListToArray(inputCharsList);
		cypherChar = charListToArray(cypherCharsList);
		// for each letter in the cyphertext
		for (int x = 0; x < cypher.length(); x++) {
			// for each letter in the alphabet
			for (int i = 0; i < cypherChar.length; i++) {
				// if the letter in the text is the same as one in the
				// cyphertext array i.e this if loop should always be entered
				if (decrypted[x] == cypherChar[i]) {
					// the letter in the text is substituted with the one at the
					// same position in the input array
					decrypted[x] = inputChar[i];
					break;
				}
			}
		}
		// the result is converted back to a string and returned
		String result = new String(decrypted);
		return result;
	}

	// method to convert a list of chars to an array of chars (used above)
	public static char[] charListToArray(List<Character> List) {
		// empty array that is the size of the list
		char[] charArray = new char[List.size()];
		// for each element in the list
		for (int i = 0; i < List.size(); i++) {
			// the value in the array is the value in the list
			charArray[i] = List.get(i);
		}
		// the array is returned
		return charArray;
	}

	// new keyboard is created for use in the tuning method
	static Scanner keyboard = new Scanner(System.in);

	// method to swap characters manually
	private static void tuning(String s) {
		// this method runs forever
		while (true) {
			System.out.println("Enter character to swap");
			// old character is the first character input to the console
			char oldChar = keyboard.next().charAt(0);
			// if the character is a / then the program stops
			if (oldChar == '/') {
				break;
			}
			System.out.println("Swap " + oldChar + " to?");
			// new character is the first character input to the console
			char newChar = keyboard.next().charAt(0);
			// if the character is a / then the program stops
			if (newChar == '/') {
				break;
			}
			// the characters are replaced in the string, using uppercase
			// letters to make sure only the desired instances are changed
			s = s.replace(oldChar, Character.toUpperCase(newChar));
			s = s.replace(newChar, Character.toUpperCase(oldChar));
			// everything converted back to lowercase
			s = s.toLowerCase();
			// new version of the text is returned
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		// textfile is read in and converted to lower case
		String str = readFile("textfile.txt");
		String input = str.toLowerCase();
		// cyphertext is read in
		String cyphertext = readFile("cyphertext.txt");
		System.out.println(characterCount(input));
		System.out.println(characterCount(cyphertext));
		// the text is decrypted by substitution cypher
		String decryptedTxt = decrypt(input, cyphertext);
		System.out.println(decryptedTxt);
		// the text is manually tuned as needed
		tuning(decryptedTxt);

	}

}