// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        return null;
    }

    public boolean Delete(Dictionary e)
    {
        public boolean Delete(Dictionary e)
        {
            if (root == null)
            {
                return root;
            }
            if (root.key < e.key)
            {
                root.right = Delete(root.right, e.key);
                return root;
            }
            else if (root.key > e.key)
            {
                root.left = Delete(root.left, e.key);
                return root;
            }
            if (root.right == null)
            {
                BSTree dummy_1 = root.left;
                Delete root;
                return dummy_1;
            }
            else if (root.left == null)
            {
                BSTree dummy_2 = root.right;
                Delete root;
                return dummy_2;
            }
            else
            {
                BSTree dummy_3 = root;
                BSTree dummy_4 = root.right;
                while (dummy_4.left != null)
                {
                    dummy_3 = dummy_4;
                    dummy_4 = dummy_4.left;
                }
                if (dummy_3 != root)
                {
                    dummy_3.left = dummy_4.right;
                }
                else
                {
                    dummy_3.right = dummy_4.right;
                }
                root.key = dummy_4.key;
                delete dummy_4;
                return root;
            }
        }
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        return null;
    }

    public BSTree getFirst()
    { 
        return null;
    }

    public BSTree getNext()
    { 
        return null;
    }

    public boolean sanity()
    { 
        return false;
    }

}


 


