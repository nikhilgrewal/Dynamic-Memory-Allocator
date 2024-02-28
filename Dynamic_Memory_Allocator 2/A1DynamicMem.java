// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).
//package com.company;
public class A1DynamicMem extends DynamicMem {

    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

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
                freeBlk.Insert(result+blockSize,size-blockSize,size-blockSize);
                A1List fgh=new A1List(result,size,size);
                freeBlk.Delete(fgh);
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
            Dictionary d=allocBlk.Find(startAddr,true);
            freeBlk.Insert(startAddr,allocBlk.Find(startAddr,true).size,allocBlk.Find(startAddr,true).size);
            allocBlk.Delete(allocBlk.Find(startAddr,true));
            return 0;
        }
        return -1;
    }
}