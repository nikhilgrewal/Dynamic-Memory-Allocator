// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java
//package com.company;
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
        BSTree tree=new BSTree(address,size,key);
        BSTree pointer=this;
        while(pointer.parent!=null){
            pointer=pointer.parent;
        }
        BSTree pointer1=pointer;
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
        }else if((pointer1.key<key)||(pointer1.key==key&&pointer1.address<address)){
            pointer1.right=tree;
            tree.parent=pointer1;
        }else if((pointer1.key>key)||(pointer1.key==key&&pointer1.address>address)){
            pointer1.left=tree;
            tree.parent=pointer1;
        }
        return tree;
    }

    public boolean Delete(Dictionary e)
    {
        BSTree pointer=this;
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
        if(pointer!=null) {
            BSTree tree=pointer.parent;;
            if (pointer.left == null && pointer.right == null) {
                if (tree.left == pointer) {
                    tree.left = null;
                    return true;
                } else if(tree.right==pointer){
                    tree.right = null;
                    return true;
                }
            } else if (pointer.left == null) {
                if (tree.left == pointer) {
                    tree.left = pointer.right;
                    tree.left.parent = tree;
                    return true;
                } else if(tree.right==pointer){
                    tree.right = pointer.right;
                    tree.right.parent = tree;
                    return true;
                }
            } else if (pointer.right == null) {
                if (tree.left == pointer) {
                    tree.left = pointer.left;
                    tree.left.parent = tree;
                    return true;
                } else if(tree.right==pointer){
                    tree.right = pointer.left;
                    tree.right.parent = tree;
                    return true;
                }
            } else{
                BSTree new_pointer= pointer.getNext();
                BSTree new_tree=new_pointer.parent;
                if(new_tree.left==new_pointer){
                    new_tree.left=null;
                    pointer.key=new_pointer.key;
                    pointer.address=new_pointer.address;
                    pointer.size=new_pointer.size;
                    return true;
                }if(new_tree.right==new_pointer){
                    new_tree.right=null;
                    pointer.key=new_pointer.key;
                    pointer.address=new_pointer.address;
                    pointer.size=new_pointer.size;
                    return true;
                }
            }
        }
        return false;
    }

    public BSTree Find(int key, boolean exact)
    {
        if(exact) {
            BSTree pointer=this;
            while(pointer.parent!=null){
                pointer=pointer.parent;
            }
            pointer=pointer.getFirst();
            if(pointer==null||pointer.key>key){
                return null;
            }
            if(pointer.key==key){
                return pointer;
            }
            while (pointer != null) {
                if (pointer.key ==key){
                    return pointer;
                }
                pointer=pointer.getNext();
            }
        }else{
            BSTree pointer=this;
            while(pointer.parent!=null){
                pointer=pointer.parent;
            }
            BSTree pointer1=pointer.getFirst();
            //return pointer1;
            if(pointer1==null){
                return null;
            }
            if(pointer1.key>=key){
                return pointer1;
            }
            if(pointer1.getNext()==null){
                return pointer1;
            }
            while(pointer1!=null&&pointer1.getNext()!=null){
                if(pointer1.key<key&&pointer1.getNext().key>=key){
                    return pointer1.getNext();
                }
                pointer1=pointer1.getNext();
            }
        }
        return null;
    }

    public BSTree getFirst()
    {
        BSTree pointer=this;
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
        BSTree pointer1=pointer.right;
        while(pointer!=null){
            pointer1=pointer;
            pointer=pointer.left;
        }
        return pointer1;
    }

    public BSTree getNext()
    {
        BSTree pointer=this;
        BSTree pointer2=this;
        while(pointer2.parent!=null){
            pointer2=pointer2.parent;
        }
        pointer2=pointer2.right;
        if(pointer.right!=null){
            pointer=pointer.right;
            while(pointer.left!=null){
                pointer=pointer.left;
            }
            return pointer;
        }
        if((this.key<pointer2.key)||(this.key==pointer2.key&&this.address<pointer2.address)) {
            BSTree pointer1 = pointer.parent;
            while (pointer1 != null && pointer == pointer1.right) {
                pointer = pointer1;
                pointer1 = pointer1.parent;
            }
            return pointer1;
        }
        if((this.key>pointer2.key)||(this.key==pointer2.key&&this.address>pointer2.address)){
            BSTree pointer1 = pointer.parent;
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
        return false;
    }
}