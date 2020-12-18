public class BinaryTree {
	Node root;
	int size = 0;
	public void addNode(int key, String name) {
		Node newNode = new Node(key,name);
		if(root == null) {
			root = newNode;
		}
		else {
			//Traversing node
			Node curNode = root;
			//future parents
			Node parent;
			while(true) {
				parent = curNode;
				if(key < curNode.key) {
					curNode = curNode.leftChild;
					if(curNode == null) {
						parent.leftChild = newNode;
						size = size + 1;
						return;
					}
					else if(curNode.key == key) {
						curNode.name = name;
						return;
					}
				}
				else {
					curNode = curNode.rightChild;
					if(curNode == null) {
						parent.rightChild = newNode;
						size = size + 1;
						return;
					}
					else if(curNode.key == key) {
						curNode.name = name;
						return;
					}
				}
			}
		}
		size = size + 1;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}
	public void inOrderTraversalNode(Node curNode) {
		if(curNode != null) {
			inOrderTraversalNode(curNode.leftChild);
			System.out.println(curNode);
			inOrderTraversalNode(curNode.rightChild);
		}
	}
	public Node findNode(int key) {
		Node curNode = root;
		while(curNode.key != key) {
			if(key < curNode.key) {
				curNode = curNode.leftChild;
			}
			else {
				curNode = curNode.rightChild;
			}
			if(curNode == null) {
				return null;
			}
		}
		return curNode;
	}
	public boolean removeNode(int key) {
		boolean isItLeftChild = true;
		Node curNode = root;
		Node parent = root;
		while(curNode.key != key) {
			parent = curNode;
			if(key < curNode.key) {
				isItLeftChild = true;
				curNode = curNode.leftChild;
			}
			else {
				isItLeftChild = false;
				curNode = curNode.rightChild;
			}
			if(curNode == null) {
				return false;
			}
		}
		//Left and right child is null
		if(curNode.leftChild == null && curNode.rightChild == null) {
			if(curNode == root) {
				root = null;
			}
			else if(isItLeftChild) {
				parent.leftChild = null;
			}
			else {
				parent.rightChild = null;
			}
		}
		else if(curNode.leftChild == null) {
			if(curNode == root) {
				root = curNode.rightChild;
			}
			else if(isItLeftChild) {
				parent.leftChild = curNode.rightChild;
			}
			else {
				parent.rightChild = curNode.rightChild;
			}
		}
		else if(curNode.rightChild == null) {
			if(curNode == root) {
				root = curNode.leftChild;
			}
			else if(isItLeftChild) {
				parent.leftChild = curNode.leftChild;
			}
			else {
				parent.rightChild = curNode.leftChild;
			}
		}
		else {
			Node replacement = getReplacementNode(curNode);
			if(curNode == root) {
				root = replacement;
			}
			else if(isItLeftChild) {
				parent.leftChild = replacement;
			}
			else {
				parent.rightChild = replacement;
			}
			replacement.leftChild = curNode.leftChild;
		}
		return true;
	}
	public Node getReplacementNode(Node replacedNode) {
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		Node curNode = replacedNode.rightChild;
		// while there is no left child
		while(curNode != null) {
			replacementParent = replacement;
			replacement = curNode;
			curNode = curNode.leftChild;
		}
		//replacement isn't right child
		if(replacement != replacedNode.rightChild) {
			replacementParent.leftChild = replacement.rightChild;
			replacement.leftChild = replacedNode.rightChild;
		}
		return replacement;
	}
	public int floor(int key) {
		Node floor = root;
		while(floor != null) {
			if(floor.key == key) {
				return floor.key;
			}
			else if(key < floor.key) {
				floor = floor.leftChild;
				if(key > floor.leftChild.key && key < floor.rightChild.key){
					return floor.key;
				}
				else if(key > floor.key) {
					floor = floor.rightChild;
				}
				else{
					floor = floor.leftChild;
				}
			}
			else{
				floor = floor.rightChild;
				if(key > floor.leftChild.key && key < floor.rightChild.key){
					return floor.key;
				}
				else if(key < floor.key) {
					floor = floor.leftChild;
				}
				else{
					floor = floor.rightChild;
				}
			}
		}
		return floor.key;
	}
	public static void main(String[] args) {
		BinaryTree theTree = new BinaryTree();
		theTree.addNode(40, "FATHER");
		theTree.addNode(60, "MOTHER");
		theTree.addNode(55, "FIRST BROTHER");
		theTree.addNode(50, "SECOND BROTHER");
		theTree.addNode(45, "THIRD BROTHER");
		theTree.addNode(75, "FORTH BROTHER");
		theTree.addNode(70, "SISTER");
		theTree.addNode(20, "SISTER");
		theTree.addNode(25, "SISTER");
		theTree.addNode(35, "SISTER");
		theTree.addNode(30, "SISTER");
		theTree.addNode(10, "SISTER");
		theTree.addNode(15, "SISTER");
		theTree.addNode(5, "SISTER");
		theTree.addNode(80, "SISTER");
		//theTree.removeNode(25);
		System.out.println("Size :" +theTree.size());
		System.out.println("Empty :" +theTree.isEmpty());
		theTree.inOrderTraversalNode(theTree.root);
		System.out.println("Floor :" + theTree.floor(37));
		System.out.println("Search Node :" + theTree.findNode(5));
	}
}
class Node{
	int key;
	String name;
	Node leftChild;
	Node rightChild;
	public Node(int key, String name) {
		this.key = key;
		this.name = name;
	}
	public String toString() {
		return name + " has a key " + key;
	}
}
