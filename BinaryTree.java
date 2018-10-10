/**
* Implements binary trees.
*
* @author Nicholas R. Howe
* @author Elizabeth Muirhead
* @version November 2017
*/


public class BinaryTree<E> {

	/** The value at this node */
	private E data;

	/** Left child of this node */
	private BinaryTree<E> left;

	/** Right child of this node */
	private BinaryTree<E> right;

	/** This constructor creates a leaf node */
	public BinaryTree(E data) {
		this.data = data;
		left = right = null;
	}
	
	
	/** This constructor creates a branch node */
	public BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right) {
		this.data = data;
		this.right = right;
		this.left = left;
	}
	
	/** This constructor creates a deep copy of the entire tree structure */
	public BinaryTree(BinaryTree<E> tree) {
		this.data = tree.data;
		this.left = (tree.left == null)? null:(new BinaryTree<E>(tree.left));
		this.right = (tree.right == null)? null:(new BinaryTree<E>(tree.right));
	}
	
	/** Accessor for node data */
	public E getData() { // FILL IN RETURN TYPE
		return data;
	}

	/** Accessor for left child */
	public BinaryTree<E> getLeft() { // FILL IN RETURN TYPE
		return left;
	}

	/** Accessor for right child */
	public BinaryTree<E> getRight() { // FILL IN RETURN TYPE
		return right;
	}

	/** Manipulator for node data */
	public void setData(E data) { // FILL IN ARGUMENT TYPE
		this.data = data;
	}
	
	/** Manipulator for left child */
	public void setLeft(BinaryTree<E> left) { // FILL IN ARGUMENT TYPE
		this.left = left;
	}

	/** Manipulator for right child */
	public void setRight(BinaryTree<E> right) { // FILL IN ARGUMENT TYPE
		this.right = right;
	}
	
	/** Determines whether a tree is empty */
	public static boolean isEmpty(BinaryTree node) {
		return (node == null);
	}
	
	/** Determines whether this tree is a leaf */
	public boolean isLeaf() {
		if (left == null && right == null){
			return true;
		}

		else {
			return false;
		}

	}

	/** Determines whether this tree is a branch */
	public boolean isBranch() {
		if (left != null || right != null){
			return true;
		}
		else {
			return false;
		}
	}

	
	/**
	* Indents by the specified number of spaces
	*
	* @param n Number of spaces
	*/

	private static void indent(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(" ");
		}
	}
	
	/** Prints the tree nodes with indentation indicating level */
		public void print() {
			System.out.print("Root: ");
			printIndented(0);
		}

		/**
		 * Prints the tree with indentation corresponding to level.
		 * Basically a fancy version of preorder.
		 * Not intended to be called directly; use print() method above
		 *
		 * @param depth Level of this node, for indentation
		 */
		private void printIndented(int depth) {
			System.out.println(data);
			depth++;
	
			if (left != null) {
				indent(2*depth);
				System.out.print("Left: ");
				left.printIndented(depth);
			}
			if (right != null){
				indent(2*depth);
				System.out.print("Right: ");
				right.printIndented(depth);
			}
		}
	}