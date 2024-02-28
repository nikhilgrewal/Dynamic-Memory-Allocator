// Class: Height balanced AVL Tree
// Binary Search Tree
//package com.company;
public class AVLTree extends BSTree {

    private AVLTree left, right;     // Children.
    private AVLTree parent;          // Parent pointer.
    private int height;  // The height of the subtree

    public AVLTree() {
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.

    }

    public AVLTree(int address, int size, int key) {
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions.
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    private int maximum(int a,int b){
        if(a>b){
            return a;
        }else{
            return b;
        }
    }
    private int get_height(AVLTree tree){
        if(tree==null){
            return 0;
        }
        return tree.height;
    }
    private int balance_height(AVLTree tree){
        if(tree==null){
            return 0;
        }
        return get_height(tree.left)-get_height(tree.right);
    }

    private AVLTree right_rotate(AVLTree tree){
        AVLTree tree_1 = tree.left;
        AVLTree tree_2 = tree_1.right;
        tree_1.right=tree;
        tree.parent=tree_1;
        tree.left=tree_2;
        tree_2.parent=tree;
        tree.height=1+maximum(get_height(tree.left),get_height(tree.right));
        tree_1.height=1+maximum(get_height(tree_1.left),get_height(tree_1.right));
        return tree_1;
    }

    private AVLTree left_rotate(AVLTree tree){
        AVLTree tree_1=tree.right;
        AVLTree tree_2=tree_1.left;
        tree_1.left=tree;
        tree.parent=tree_1;
        tree.right=tree_2;
        tree_2.parent=tree;
        tree.height=1+maximum(get_height(tree.left),get_height(tree.right));
        tree_1.height=1+maximum(get_height(tree_1.left),get_height(tree_1.right));
        return tree_1;
    }

    public AVLTree Insert(int address, int size, int key)
    {
        AVLTree tree=new AVLTree(address,size,key);
        AVLTree pointer,pointer1;
        pointer=this;
        while(pointer.parent!=null){
            pointer=pointer.parent;
        }
        pointer1=pointer;
        pointer=pointer.right;
        while(pointer!=null){
            pointer1=pointer;
            if((pointer.key<key)||(pointer.key==key&&pointer.address<address)){
                pointer=pointer.right;
            }else if((pointer.key>key)||(pointer.key==key&&pointer.address>address)){
                pointer=pointer.left;
            }
        }
        if(pointer1.parent==null){
            pointer1.right=tree;
            tree.parent=pointer1;
            tree.height=1;
            return tree;
        }else if((pointer1.key<key)||(pointer1.key==key&&pointer1.address<address)){
            pointer1.right=tree;
            tree.parent=pointer1;
            tree.height=1;
        }else if((pointer1.key>key)||(pointer1.key==key&&pointer1.address>address)){
            pointer1.left=tree;
            tree.parent=pointer1;
            tree.height=1;
        }
        AVLTree pointer2=tree;
        while(pointer2.parent.key!=-1){
            if(balance_height(pointer2)>1||balance_height(pointer2)<-1){
                break;
            }
            pointer2=pointer2.parent;
        }
        if(pointer2.parent.key==-1){
            return tree;
        }else{
            if((balance_height(pointer2)>1)&&((pointer2.left.key>key)||(pointer2.left.key==key&&pointer2.address>address))){
                AVLTree pointer3=pointer2.parent;
                if(pointer3.left==pointer2){
                    pointer2=right_rotate(pointer2);
                    pointer3.left=pointer2;
                    pointer2.parent=pointer3;
                }else if(pointer3.right==pointer2){
                    pointer2=right_rotate(pointer2);
                    pointer3.right=pointer2;
                    pointer2.parent=pointer3;
                }
            }else if((balance_height(pointer2)<-1)&&((pointer2.right.key<key)||(pointer2.left.key==key&&pointer2.address<address))){
                AVLTree pointer3=pointer2.parent;
                if(pointer3.left==pointer2){
                    pointer2=left_rotate(pointer2);
                    pointer3.left=pointer2;
                    pointer2.parent=pointer3;
                }else if(pointer3.right==pointer2){
                    pointer2=left_rotate(pointer2);
                    pointer3.right=pointer2;
                    pointer2.parent=pointer3;
                }
            }else if((balance_height(pointer2)>1)&&((pointer2.left.key<key)||(pointer2.left.key==key&&pointer2.address<address))){
                pointer2.left=left_rotate(pointer2.left);
                pointer2.left.parent=pointer2;
                AVLTree pointer3=pointer2.parent;
                if(pointer3.left==pointer2){
                    pointer2=right_rotate(pointer2);
                    pointer3.left=pointer2;
                    pointer2.parent=pointer3;
                }else if(pointer3.right==pointer2){
                    pointer2=right_rotate(pointer2);
                    pointer3.right=pointer2;
                    pointer2.parent=pointer3;
                }
            }else if((balance_height(pointer2)<-1)&&((pointer2.right.key>key)||(pointer2.left.key==key&&pointer2.address>address))){
                pointer2.right=right_rotate(pointer2.right);
                pointer2.right.parent=pointer2;
                AVLTree pointer3=pointer2.parent;
                if(pointer3.left==pointer2){
                    pointer2=left_rotate(pointer2);
                    pointer3.left=pointer2;
                    pointer2.parent=pointer3;
                }else if(pointer3.right==pointer2){
                    pointer2=left_rotate(pointer2);
                    pointer3.right=pointer2;
                    pointer2.parent=pointer3;
                }
            }
        }
        return tree;
    }

    public boolean Delete(Dictionary e)
    {
        AVLTree pointer=this;
        while(pointer.parent!=null){
            pointer=pointer.parent;
        }
        pointer=pointer.right;
        while(pointer!=null&&(pointer.address!=e.address||pointer.key!=e.key||pointer.size!=e.size)){
            if((pointer.key<e.key)||(pointer.key==e.key&&pointer.address<e.address)){
                pointer=pointer.right;
            }else if((pointer.key>e.key)||(pointer.key==e.key&&pointer.address>e.address)){
                pointer=pointer.left;
            }
        }
        AVLTree pointer2;
        if(pointer!=null) {
            pointer2=pointer.parent;
            AVLTree tree=pointer.parent;
            if (pointer.left == null && pointer.right == null) {
                if (tree.left == pointer) {
                    tree.left = null;
                } else if(tree.right==pointer){
                    tree.right = null;
                }
            } else if (pointer.left == null) {
                if (tree.left == pointer) {
                    tree.left = pointer.right;
                    tree.left.parent = tree;
                } else if(tree.right==pointer){
                    tree.right = pointer.right;
                    tree.right.parent = tree;
                }
            } else if (pointer.right == null) {
                if (tree.left == pointer) {
                    tree.left = pointer.left;
                    tree.left.parent = tree;
                } else if(tree.right==pointer){
                    tree.right = pointer.left;
                    tree.right.parent = tree;
                }
            } else{
                AVLTree new_pointer= pointer.getNext();
                AVLTree new_tree=new_pointer.parent;
                if(new_tree.left==new_pointer){
                    new_tree.left=null;
                    pointer.key=new_pointer.key;
                    pointer.address=new_pointer.address;
                    pointer.size=new_pointer.size;
                }if(new_tree.right==new_pointer){
                    new_tree.right=null;
                    pointer.key=new_pointer.key;
                    pointer.address=new_pointer.address;
                    pointer.size=new_pointer.size;
                }
            }
            while(pointer2!=null){
                if(pointer2.parent==null){
                    return true;
                }
                if (pointer2.parent.key == -1) {
                    return true;
                }
                if ((balance_height(pointer2) > 1) && ((pointer2.left.key > key) || (pointer2.left.key == key && pointer2.address > address))) {
                    AVLTree pointer3 = pointer2.parent;
                    if (pointer3.left == pointer2) {
                        pointer2 = right_rotate(pointer2);
                        pointer3.left = pointer2;
                        pointer2.parent = pointer3;
                    } else if (pointer3.right == pointer2) {
                        pointer2 = right_rotate(pointer2);
                        pointer3.right = pointer2;
                        pointer2.parent = pointer3;
                    }
                } else if ((balance_height(pointer2) < -1) && ((pointer2.right.key < key) || (pointer2.left.key == key && pointer2.address < address))) {
                    AVLTree pointer3 = pointer2.parent;
                    if (pointer3.left == pointer2) {
                        pointer2 = left_rotate(pointer2);
                        pointer3.left = pointer2;
                        pointer2.parent = pointer3;
                    } else if (pointer3.right == pointer2) {
                        pointer2 = left_rotate(pointer2);
                        pointer3.right = pointer2;
                        pointer2.parent = pointer3;
                    }
                } else if ((balance_height(pointer2) > 1) && ((pointer2.left.key < key) || (pointer2.left.key == key && pointer2.address < address))) {
                    pointer2.left = left_rotate(pointer2.left);
                    pointer2.left.parent = pointer2;
                    AVLTree pointer3 = pointer2.parent;
                    if (pointer3.left == pointer2) {
                        pointer2 = right_rotate(pointer2);
                        pointer3.left = pointer2;
                        pointer2.parent = pointer3;
                    } else if (pointer3.right == pointer2) {
                        pointer2 = right_rotate(pointer2);
                        pointer3.right = pointer2;
                        pointer2.parent = pointer3;
                    }
                } else if ((balance_height(pointer2) < -1) && ((pointer2.right.key > key) || (pointer2.left.key == key && pointer2.address > address))) {
                    pointer2.right = right_rotate(pointer2.right);
                    pointer2.right.parent = pointer2;
                    AVLTree pointer3 = pointer2.parent;
                    if (pointer3.left == pointer2) {
                        pointer2 = left_rotate(pointer2);
                        pointer3.left = pointer2;
                        pointer2.parent = pointer3;
                    } else if (pointer3.right == pointer2) {
                        pointer2 = left_rotate(pointer2);
                        pointer3.right = pointer2;
                        pointer2.parent = pointer3;
                    }
                }
                pointer2=pointer2.parent;
            }
            return true;
        }
        return false;
    }

    public AVLTree Find(int k, boolean exact)
    {
        if(exact) {
            AVLTree pointer=this;
            while(pointer.parent!=null){
                pointer=pointer.parent;
            }
            pointer=pointer.getFirst();
            if(pointer==null||pointer.key>k){
                //System.out.println(-1);
                return null;
            }
            if(pointer.key==k){
                return pointer;
            }
            while (pointer != null) {
                if (pointer.key ==k){
                    return pointer;
                }
                pointer=pointer.getNext();
            }
        }else{
            AVLTree pointer=this;
            while(pointer.parent!=null){
                pointer=pointer.parent;
            }
            AVLTree pointer1=pointer.getFirst();
            if(pointer1==null){
                return null;
            }
            if(pointer1.key>=k){
                return pointer1;
            }
            if(pointer1.getNext()==null){
                return pointer1;
            }
            while(pointer1!=null&&pointer1.getNext()!=null){
                if(pointer1.key<k&&pointer1.getNext().key>=k){
                    return pointer1.getNext();
                }
                pointer1=pointer1.getNext();
            }
        }
        return null;
    }

    public AVLTree getFirst()
    {
        AVLTree pointer=this;
        while(pointer.parent!=null){
            pointer=pointer.parent;
        }
        if(pointer.right==null){
            return  null;
        }
        if(pointer.right.left==null){
            return pointer.right;
        }
        pointer=pointer.right.left;
        AVLTree pointer1=pointer.right;
        while(pointer!=null){
            pointer1=pointer;
            pointer=pointer.left;
        }
        return pointer1;
    }

    public AVLTree getNext() {
        AVLTree pointer = this;
        AVLTree pointer2 = this;
        while (pointer2.parent != null) {
            pointer2 = pointer2.parent;
        }
        pointer2 = pointer2.right;
        if (pointer.right != null) {
            pointer = pointer.right;
            while (pointer.left != null) {
                pointer = pointer.left;
            }
            return pointer;
        }
        if ((this.key < pointer2.key) || (this.key == pointer2.key && this.address < pointer2.address)) {
            AVLTree pointer1 = pointer.parent;
            while (pointer1 != null && pointer == pointer1.right) {
                pointer = pointer1;
                pointer1 = pointer1.parent;
            }
            return pointer1;
        }
        if ((this.key > pointer2.key) || (this.key == pointer2.key && this.address > pointer2.address)) {
            AVLTree pointer1 = pointer.parent;
            while (pointer1 != null && pointer == pointer1.right) {
                pointer = pointer1;
                pointer1 = pointer1.parent;
            }
            return pointer1;
        }
        return null;
    }

    public boolean sanity()
    {
        AVLTree pointer= this;
        while(pointer.parent!=null){
            pointer=pointer.parent;
        }
        pointer=pointer.getFirst();
        AVLTree pointer2=pointer;
        AVLTree pointer3=pointer;
        AVLTree pointer4=pointer;
        if(pointer4!=null&&pointer4.getNext()!=null&&pointer4.getNext().getNext()!=null){
            AVLTree hare=pointer4.getNext();
            AVLTree tortoise=pointer4.getNext().getNext();
            while(hare!=null&&tortoise!=null) {
                if (hare == tortoise) {
                    return false;
                }
                hare = hare.getNext();
                tortoise = tortoise = getNext().getNext();
            }
        }
        while(pointer3!=null){
            if(pointer3.parent.left!=pointer3&&pointer3.parent.right!=pointer3){
                return false;
            }
            pointer3=pointer3.getNext();
        }
        while (pointer2 != null) {
            if (pointer2.left != null && pointer2.right != null) {
                if (((pointer2.key < pointer2.left.key) || (pointer2.key == pointer2.left.key && pointer2.address < pointer2.left.address)) && ((pointer2.key > pointer2.right.key) || (pointer2.key == pointer2.right.key && pointer2.address > pointer2.right.address))) {
                    return false;
                }
            }
            if (pointer2.left == null && pointer2.right != null) {
                if ((pointer2.key > pointer2.right.key) || (pointer2.key == pointer2.right.key && pointer2.address > pointer2.right.address)) {
                    return false;
                }
            }
            if (pointer2.right == null && pointer2.left != null) {
                if ((pointer2.key < pointer2.left.key) || (pointer2.key == pointer2.left.key && pointer2.address < pointer2.left.address)) {
                    return false;
                }
            }
            pointer2 = pointer2.getNext();
        }
        while(pointer!=null){
            if(balance_height(pointer)>1&&balance_height(pointer)<-1){
                return false;
            }
            pointer=pointer.getNext();
        }
        return true;
    }
}