// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List
//package com.company;
public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node

    public A1List(int address, int size, int key) {
        super(address, size, key);
    }

    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel

        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List dict = new A1List(address,size,key);
        if(this.next!=null) {
            // when it this.next1!=null than this means that our pointer is not on the tailSentinal that implies
            // we can insert a node after the given pointer node
            dict.next = this.next;
            this.next = dict;
            dict.prev = this;
            dict.next.prev = dict;
        }else{
            // when it this.next1==null than this means that our pointer is on the tailSentinal that implies
            // we can insert a node before the given pointer
            dict.prev=this.prev;
            this.prev=dict;
            dict.next=this;
            dict.prev.next=dict;
        }
        // return our node of Dll that we insert in it
        return dict;
    }

    public boolean Delete(Dictionary d)
    {
        // here we do a step before start our process i.e. first we take our pointer on the headSentinel
        A1List new_dict=this;
        while(new_dict.prev!=null){
            new_dict=new_dict.prev;
        }
        A1List Current_dict = new_dict;
        // now our process starts and it search for the element that we wanted to delete from Dll
        while(Current_dict.next!=null){
            // here our dll have contains a dictionary which contains the key address and size so for delete element
            // we want to match all the thimgs and then we delete the node
            if(Current_dict.address==d.address&&Current_dict.size==d.size&&Current_dict.key==d.key){
                Current_dict.prev.next=Current_dict.next;
                Current_dict.next.prev=Current_dict.prev;
                // if we got the element and delete it the function prints ture that means we succesfully delete the element
                return true;
            }
            Current_dict = Current_dict.next;
        }
        // if we don't get the element or not able to delete it
        return false;
    }

    public A1List Find(int k, boolean exact)
    {
        // here we do a step before start our process i.e. first we take our pointer on the headSentinel
        A1List new_dict=this;
        while(new_dict.prev!=null){
            new_dict=new_dict.prev;
        }
        // here we have two condition for it if exact is true
        if(exact==true){
            A1List Current_dict=new_dict.next;
            // here we search for the key which we wanted to find out here we search for the node
            // whose key is exact same with the given k
            while(Current_dict.next!=null){
                if(Current_dict.key==k){
                    // if we got our key than print the node
                    return Current_dict;
                }
                Current_dict = Current_dict.next;
            }
            // if we don't got it than we print null
            return null;
        }
        // this condition is run when our exact is false
        else{
            A1List Current_dict=new_dict.next;
            // here we search for the key which we wanted to find out here we search for the node
            // whose key is greater than or equal with the given k
            while(Current_dict.next!=null){
                if(Current_dict.key>=k){
                    // if we got our key than print the node
                    return Current_dict;
                }
                Current_dict=Current_dict.next;
            }
        }
        // if we got our key than print the node
        return null;
    }

    public A1List getFirst()
    {
        A1List head= new A1List(-1,-1,-1);
        A1List new_point=this;
        //here we see that if our pointer is on the head sentinal
        if(new_point==head){
            // shift ouur pointer to the next element i.e first element
            new_point=new_point.next;
            if(new_point==head){
                // if next element in not in the Dll than return null
                return null;
            }
        }
        // when our pointer is not on the headsentinel than we take back it to the first element
        else{
            while(new_point.prev!=head){
                new_point=new_point.prev;
            }
        }
        // if we got our answer than print the first element of the Dll
        return new_point;
    }

    public A1List getNext()
    {
        A1List tail=new A1List(-1,-1,-1);
        // in this we just print the next elment of the pointer given to us
        if(this.next==null||this.next==tail){
            // when our next pointer is null or tailsentinel than we print null
            return null;
        }
        // return next element
        return this.next;
    }

    public boolean sanity()
    {
        A1List dict1,dict2,dict3,dict4;
        dict1=this;
        dict2=this;
        dict3=this;
        dict4=this;
        while((dict1!=null&&dict2!=null&&dict2.next!=null)||(dict3!=null&&dict4!=null&&dict4.prev!=null)){
            if(dict1!=null&&dict2!=null){
                dict1=dict1.next;
                dict2=dict2.next.next;
            }if(dict3!=null&&dict4!=null){
                dict3=dict3.prev;
                dict4=dict4.prev.prev;
            }
            if((dict1==dict2)||(dict3==dict4)){
                return false;
            }
        }
        A1List pointer =this;
        while(pointer.prev!=null){
            pointer=pointer.prev;
        }
        A1List head=new A1List(-1,-1,-1);
        if(pointer!=head){
            return false;
        }
        while(pointer.next!=null){
            if(pointer.next.prev!=pointer){
                return false;
            }
            pointer=pointer.next;
        }
        if(pointer!=head){
            return false;
        }
        return true;
    }
}