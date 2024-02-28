// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.
//package com.company;
public class A2DynamicMem extends A1DynamicMem {

    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees.
    // No changes should be required in the A1DynamicMem functions.
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees.
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two.
    public int Allocate(int blockSize) {
        // if blocksize is less than 0 or 0 than we return -1 because size of any memory is not less than or equal to 0
        if(blockSize<=0){
            return -1;
        }
        if(freeBlk.Find(blockSize,false)!=null){
            // first we find the element whose block size is in the free block or not if it existes than
            if(freeBlk.Find(blockSize,false).key>blockSize){
                // if found block size is greater than the blocksize than perform
                // Insert the block of blocksize on the allocate memory
                // split the block in two in free block and the dlete the block which is onsert in the allocate
                allocBlk.Insert(freeBlk.Find(blockSize,false).address,blockSize,freeBlk.Find(blockSize,false).address);
                int result=freeBlk.Find(blockSize,false).address;
                int size=freeBlk.Find(blockSize,false).size;
                Dictionary dict=freeBlk.Find(blockSize,false);
                freeBlk.Insert(result+blockSize,size-blockSize,size-blockSize);
                freeBlk.Delete(dict);
                return result;
            }else if(freeBlk.Find(blockSize,false).key==blockSize){
                // if we found blocksize is exactly equals to the founded size od node of dll
                // insert the block in allocate
                // delte the block in the free
                allocBlk.Insert(freeBlk.Find(blockSize,false).address,blockSize,freeBlk.Find(blockSize,false).address);
                int result=freeBlk.Find(blockSize,false).address;
                freeBlk.Delete(freeBlk.Find(blockSize,false));
                return result;
            }
            return -1;
        }
        return -1;
    }

    public int Free(int startAddr) {
        // fi startaddr is less than 0 that is a exception so in this case return -1
        if(startAddr<0){
            return -1;
        }
        if(allocBlk.Find(startAddr,true)!=null){
            // here first we find the block in allocate memory block and delete it from the allocate block
            // insert it on the free block memory
            freeBlk.Insert(startAddr,allocBlk.Find(startAddr,true).size,allocBlk.Find(startAddr,true).size);
            allocBlk.Delete(allocBlk.Find(startAddr,true));
            return 0;
        }
        return -1;
    }
    
    public void Defragment() {
        Dictionary tree;
        if(type==2){
            tree=new BSTree();
        }else{
            if(type==3) {
                tree = new AVLTree();
            }else{
                return;
            }
        }
        if(freeBlk.getFirst()==null){
            return;
        }else{
            Dictionary dict=freeBlk.getFirst();
            //System.out.println(dict.getNext().key);
            while(dict!=null){
                tree.Insert(dict.address,dict.size,dict.address);
                freeBlk.Delete(dict);
                dict=freeBlk.getFirst();
            }
            Dictionary dict_tree=tree.getFirst();
            if(tree.getFirst().getNext()==null){
                freeBlk.Insert(dict_tree.address,dict_tree.size,dict_tree.size);
                return;
            }else{
                while(dict_tree!=null&&dict_tree.getNext()!=null){
                    if(dict_tree.key+dict_tree.size==dict_tree.getNext().key){
                        Dictionary new_dict_tree = dict_tree.getNext();
                        int adres=dict_tree.key;
                        int siz=dict_tree.size+new_dict_tree.size;
                        int ky=dict_tree.key;
                        tree.Delete(dict_tree);
                        tree.Delete(new_dict_tree);
                        tree.Insert(adres,siz,ky);
                        dict_tree=tree.getFirst();
                    }else{
                        dict_tree=dict_tree.getNext();
                    }
                }
                Dictionary new_dict=tree.getFirst();
                while(new_dict!=null){
                    freeBlk.Insert(new_dict.address, new_dict.size, new_dict.size);
                    new_dict=new_dict.getNext();
                }
                return;
            }
        }
    }
}