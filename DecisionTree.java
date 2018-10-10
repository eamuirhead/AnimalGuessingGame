import java.io.*;
import java.util.*;
import java.lang.*;

/**
* builds a binary tree of questions that were passed in in a file
* @author: Elizabeth Muirhead
* @version: November 2017
*/


public class DecisionTree {
	
	// var tree is the BinaryTree that will be built in class
	private BinaryTree<String> tree;
	
	/**
	 * constructor for decision tree
	 * builds the tree from a file
	 */
	public DecisionTree(String fname){
		// reads the file
		BufferedReader br = null; 
		// keeps track of line
		String currentLine = null;
		// top of tree
		BinaryTree<String> topNode = null; 
		// keeps track of location in tree
		BinaryTree<String> currentNode = null; 

		// opens the file
		try {
			br = new BufferedReader(new FileReader(fname));
		}
		catch (IOException e){
			System.err.printf("Problem reading file " + fname + "\n");
			System.exit(-1);
		}

		//Read first line
		try{
			currentLine = br.readLine();
		}
		catch (IOException e) {
			System.err.printf("Problem reading input\n");
			System.exit(-1);
		}

		// keeps reading lines until done
		while(currentLine != null){
			// splits line at space
			String[] tempList = currentLine.split(" "); 
			
			// lenth of "yynn.." sequence
			int len = tempList[0].length();
			currentNode = topNode;

			// if the "yynn.." sequence is not empty
			if (len > 0){
				// traverse all nodes except last (up to insertion point)
				for ( int i = 0; i<len-1; i++){
					// current Y or N
					char elem = tempList[0].charAt(i);
		
					if (elem == 'y' || elem == 'Y'){
						currentNode = currentNode.getLeft();
					}
					else if (elem == 'n' || elem == 'N'){
						currentNode = currentNode.getRight();
					}
					else {
						System.err.printf("Problem with data in input file\n");
						System.exit(-1);
					}
				}
			}
			// gets question or answer from line
			String question = currentLine.substring(len +1);
			
			// sets first line to top node
			if (len == 0) {
				topNode = tree = new BinaryTree<String>(question);
			}
			
			 // sets answer/question to appropriate node
			else {
				// elem = last letter in "yynn.. sequence"
				char elem = tempList[0].charAt(len-1);
				
				// if elem = y, it’s a left leaf
				if (elem == 'y' || elem =='Y'){
					currentNode.setLeft(new BinaryTree<String>(question));
				}
				 // if elem = n, it’s a right leaf
				else if (elem == 'n' || elem == 'N'){
					currentNode.setRight(new BinaryTree<String>(question));
				}
				else {
					System.err.printf("Problem with data in input file\n");
					System.exit(-1);
				}
			}
	
			//Read next line
			try{
				currentLine = br.readLine();
			}
			catch (IOException e) {
				System.err.printf("Problem reading input\n");
				System.exit(-1);
			}
		}
	}
	
	
	/** returns the BinaryTree that was built
		allows methods in BinaryTree class to be used */
	public BinaryTree<String> getTree(){
		return tree;
	}
}
