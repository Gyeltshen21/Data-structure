import java.util.*;

public class Solution<Key extends Comparable<Key>, Value>  {
    private Node root;
    int size = 0;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
  //       public String toString() {

		// return val + " has the key " + key;

		
		//  * return name + " has the key " + key + "\nLeft Child: " + leftChild +
		//  * "\nRight Child: " + rightChild + "\n";
		 

		// }
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        if(size == 0){
        	return true;
        }
        return false;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
      return size;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
    	Node curNode = root;
       if(key == null){
       		throw new IllegalArgumentException("There is not key conatin in the root");
       }
       else{
       		while(curNode.key != key){
       			int cmp = key.compareTo(curNode.key);
       			if(cmp < 0){
       				curNode = curNode.left;
       			}
       			else{
       				curNode = curNode.right;
       			}
       		}
       		return true;
       }
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
    	if(key == null){
	        throw new IllegalArgumentException("argument to get value() is null");
	    }
        Node curNode = root;
        while(curNode.key != key){
        	int cmp = key.compareTo(curNode.key);
        	if(cmp < 0){
        		curNode = curNode.left;
        	}
	        else if(cmp > 0){
	        	curNode = curNode.right;
	        }
        }
        if(curNode.key == key){
        	System.out.println(curNode.val);
        }
        return curNode.val;
    }

    // /**
    //  * Inserts the specified key-value pair into the symbol table, overwriting the old 
    //  * value with the new value if the symbol table already contains the specified key.
    //  * Deletes the specified key (and its associated value) from this symbol table
    //  * if the specified value is {@code null}.
    //  *
    //  * @param  key the key
    //  * @param  val the value
    //  * @throws IllegalArgumentException if {@code key} is {@code null}
    //  */
    public void put(Key key, Value val) {
        Node newNode = new Node(key,val);
        if(root == null){
        	root = newNode;
        }
        else{
        	Node curNode = root;
        	Node parent;
        	while(true){
        		parent = curNode;
        		int cmp = key.compareTo(curNode.key);
        		if(cmp < 0){
        			curNode = curNode.left;
        			if(curNode == null){
        				parent.left = newNode;
        				size = size + 1;
        				return;
        			}
        			else if(curNode.key == key){
        				curNode.val = val;
        				return;
        			}
        		}
        		else if(cmp > 0){
        			curNode = curNode.right;
        			if(curNode == null){
        				parent.right = newNode;
        				size = size + 1;
        				return;
        			}
        			else if(curNode.key == key){
        				curNode.val = val;
        				return;
        			}
        		}
        	}
        }
        size = size + 1;
    }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
       if(isEmpty()){
       	throw new NoSuchElementException("There is no element in the tree");
       }
       else{
       		Node curNode = root;
       		while(curNode.left != null){
       			curNode = curNode.left;
       		}
       		return curNode.key;
       }
    } 

    public Key max() { 
        if(isEmpty()){
       		throw new NoSuchElementException("There is no element in the tree");
       }
       else{
       		Node curNode = root;
       		while(curNode.right != null){
       			curNode = curNode.right;
       		}
       		return curNode.key;
       }
    } 

   

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param  key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
    	Node curNode = root;
    	Node curNode1 = curNode.left;
    	Node curNode2 = curNode.right;
    	int cmp = key.compareTo(curNode.key);
    	int cm = key.compareTo(curNode1.key);
    	int c = key.compareTo(curNode2.key);
    	while(curNode != null && cm < 0 && c > 0){
    		if(cmp < 0){
    			curNode = curNode.left;
    		}
    		else if(cmp > 0){
    			curNode = curNode.right;
    		}
    	}//key and root equals
    	if(cmp == 0){
    		return curNode.key;
    	}
    	//key is greater than left and key is less than right;
    	else if(key == null & cm > 0 && c < 0){
    		return curNode1.key;
    	}
    	//Vice versa
    	else if(key == null & cm < 0 && c > 0){
    		return curNode1.key;
    	}
    	return curNode1.key;
    } 

    // private Node floor(Node x, Key key) {
    //     return null;
       
    // } 

    
    

    // /**
    //  * Return the key in the symbol table whose rank is {@code k}.
    //  * This is the (k+1)st smallest key in the symbol table.
    //  *
    //  * @param  k the order statistic
    //  * @return the key in the symbol table of rank {@code k}
    //  * @throws IllegalArgumentException unless {@code k} is between 0 and
    //  *        <em>n</em>–1
    //  */
    // public Key select(int k) {
        
    // }

    // // Return key of rank k. 
    // private Node select(Node x, int k) {
        
         
    // } 

    

    // /**
    //  * Returns all keys in the symbol table in the given range,
    //  * as an {@code Iterable}.
    //  *
    //  * @param  lo minimum endpoint
    //  * @param  hi maximum endpoint
    //  * @return all keys in the symbol table between {@code lo} 
    //  *         (inclusive) and {@code hi} (inclusive)
    //  * @throws IllegalArgumentException if either {@code lo} or {@code hi}
    //  *         is {@code null}
    //  */
    // public Iterable<Key> keys(Key lo, Key hi) {
     
    // } 

    // private void keys(Node x,Queue<Key> queue, Key lo, Key hi) { 
        
    // }
    public void inOrderTraverseTree(Node focusNode) {

		if (focusNode != null) {

			// Traverse the left node

			inOrderTraverseTree(focusNode.left);

			// Visit the currently focused on node

			System.out.println(focusNode);

			// Traverse the right node

			inOrderTraverseTree(focusNode.right);

		}

	}

   
    /* Run the program by giving the approriate command obtained from
    input files through input.txt files. The output should be displayed
    exactly like the file output.txt shows it to be.*/
  
    public static void main(String[] args) { 
        Solution <String, Integer> theTree = new Solution <String, Integer>();
        theTree.put("ABDUL",1);
        theTree.get("ABDUL");
        theTree.put("HRITHIK",2);
        theTree.put("SAI",3);
        theTree.put("SAMAL",6);
        theTree.get("SAI");
        theTree.put("TASHI",4);
        System.out.println("Size : " + theTree.size());
        System.out.println("Min key : " + theTree.min());
        //System.out.println("Floor key : " + theTree.floor("HRITHIK"));
        theTree.put("CHIMI",5);
        theTree.put("SAMAL",4);
        theTree.get("SAMAL");
        theTree.put("NIMA",7);
        System.out.println("Size : " + theTree.size());
         theTree.put("SONAM",8);
    }
}
