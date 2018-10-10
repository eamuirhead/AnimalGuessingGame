import java.lang.*;
import java.util.*;
import java.io.*;

/**
* Class that plays animal guessing game
*
* @author Elizabeth Muirhead
* @version CSC 112, November 2017
*/

public class AnimalGuess{
	
	// class fields
	private static DecisionTree animalDecisionTree;
	private static BinaryTree<String> position;
	private static BinaryTree<String> newNode = null;
	private static String builder = "";

	/** main uses DecisionTree to make the tree, plays the game, and writes the file */
	public static void main(String[] args) {
		
		// checks if user input a valid file 
		if (args.length != 1) {
			System.err.printf("\n\nUsage: AnimalGuess <input file>\n\n\n");
			System.exit(-1);
		} 
		
		// if the input is valid, it starts the game
		else {
			animalDecisionTree = new DecisionTree(args[0]);
			playGame();
			writeGameFile(args[0]);
		}
	}
	
	
	/** runs the game: accesses appropriate node and diplays it,
	reads user input, adds new nodes to tree based on user input */
	private static void playGame(){
		
		// keeps track of spot in tree
		position = animalDecisionTree.getTree();
		System.out.println();

		// start of game, prints first few lines
		System.out.println("Think of an animal.");
		System.out.println("I’ll try to guess it.");
		System.out.println(position.getData());

		// when you’re not at a leaf
		while (!position.isLeaf()){
			
			// reads user input
			Scanner reader = new Scanner(System.in); 
			String s = reader.nextLine();
			
			// checks if user entered "no"
			if (s.equals("no") || s.equals("No")){ 
				// accesses next node
				System.out.println(position.getRight().getData()); 
				// moves position
				position = position.getRight(); 
			}
			// else =, same as above, but for "yes"
			else{ 
				System.out.println(position.getLeft().getData());
				position = position.getLeft();
			}
		}
		
		// after printing a leaf, the computer asks if its correct
		System.out.println("Did I guess it?");
		Scanner finalRead = new Scanner(System.in);
		String finalS = finalRead.nextLine();

		// if leaf is incorrect and user thought of unique animal
		if (finalS.equals("no") || finalS.equals("No")){ 
			System.out.println(position.isLeaf());
			// gets animal from user
			System.out.println("What animal were you thinking of?"); 
			String animal = finalRead.nextLine();
	
			// gets question from user
			System.out.println("What is a question that will distinguish it from a"+position.getData()+"?"); 
			String question = finalRead.nextLine();
			// gets answer to question from user
			System.out.println("Will the answer to that question be yes or no for your animal?"); 
			String answer = finalRead.nextLine();

			// based on answer to question, adds new info to tree
			if (answer.equals("yes") || answer.equals("Yes")){
				// new node for user’s animal
				BinaryTree<String> newNode1 = new BinaryTree<String>(animal);
				//moves old leaf to node opposite user’s animal
				BinaryTree<String> newNode2 = new BinaryTree<String>(position); 
				
				// moves user’s question to old leaf
				position.setLeft(newNode1);
				position.setRight(newNode2);
				position.setData(question); 
			}

			// same this on opposite side
			else { 
				BinaryTree<String> newNode1 = new BinaryTree<String>(animal);
				BinaryTree<String> newNode2 = new BinaryTree<String>(position);
				position.setLeft(newNode2);
				position.setRight(newNode1);
				position.setData(question);
			}
		}

		// if the computer guesses the animal
		else{
			System.out.println("Hooray, I got it!");
			System.out.println();
		}

		// asks user if they want to play again
		System.out.println("Do you want to play again - yes or no?");
		Scanner replay = new Scanner(System.in);
		String replayString = replay.nextLine();
		
		// either replay or exit method
		if (replayString.equals("yes") || replayString.equals("Yes")){
			playGame();
		}
	}

	
	/** writes the contents of the tree to the file
	overwrites the original file */
	private static void writeGameFile(String fname){
		
		// holds the "yynn.." sequence
		String builder = "";
		// buffer for writing to file
		PrintWriter outbuffer = null; 
		try {
			outbuffer = new PrintWriter(new FileWriter(fname));
		}
		catch (IOException e){
			System.err.printf("Problem writing file " + fname + "\n");
			System.exit(-1);
		}
		
		// calls printNode to print all lines in file
		printNodes(animalDecisionTree.getTree(), builder, outbuffer);
		outbuffer.close();
	}

	
	/** recusively moves through every path of nodes */
	private static void printNodes(BinaryTree<String> btPointer, String builder, PrintWriter outstream){
		// if the tree isn’t null
		if (btPointer != null) { 
			// prints the data to file
			outstream.print(builder+" " + btPointer.getData() + "\n"); 
		
			// gets left and adds "Y"	
			printNodes(btPointer.getLeft(), builder.concat("Y"), outstream); 
			// gets right and adds "N"
			printNodes(btPointer.getRight(), builder.concat("N"), outstream); 
		}
	}
}
