package com.example.myapplication;

/**
 * This class provides a linked implementation of a deque.
 * @author Logan Beaver 
 * @author Josiah Cherbony 
 * @version Fall 2021
 */
public class Deque<E> implements Cloneable {

    /** A reference to the first node in the linked list. */
    private Node<E> head;
    
    /** The number of values in this deque. */
    private int  size;


    /**
     * Initializes a new empty deque.
     */
    public Deque() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Appends the specified value on the &quot;right end&quot; of this deque.
     *
     * @param value The value to add to the &quot;right end&quot; of this deque.
     * @throws IllegalArgumentException
     *
     * @postcondition If the size of this deque before the append operation was
     *                zero (i.e., if there were previously no elements in this
     *                deque), the newly appended element becomes both the first
     *                and last element in this deque. Otherwise, the newly
     *                appended element becomes the last element in this deque
     *                and all previous elements in the deque remain unchanged.
     *
     */
    public void addLast(E value) {
        if(value == null)
            throw new IllegalArgumentException();
        
        Node<E> newNode = new Node<E>(value, null);//A new node with the value is created 
        
        if (size == 0)//If the deque has no elements 
            head = newNode;//The new node is made the head. 
        else {
            Node<E> node = head;//We get the head
            while(node.next != null)//And loop through the linked list until we reach a node who's next is null
                node = node.next;//This node is the current tail of the list 
            node.next = newNode;//The tail points to the new node, making the new node the new tail
        }
        size++;//The size is increased 
    }

    /**
     * Prepends the specified value on the &quot;left end&quot; of this deque.
     *
     * @param value The value to add to the &quot;left end&quot; of this deque.
     *
     * @postcondition If the size of this deque before the append operation was
     *                zero (i.e., if there were previously no elements in this
     *                deque), the newly prepended element becomes both the first
     *                and last element in this deque. Otherwise, the newly
     *                appended element becomes the first element in this deque
     *                and all previous elements in the deque remain unchanged.
     */
    public void addFirst(E value){
        if(value == null)
            throw new IllegalArgumentException();
        if(size == 0)//If the deque has no elements 
            head = new Node<E>(value, null);//We make a new node with our inputed value and make it the head
        else
            head = new Node<E>(value, head);//We make the new node point to the current head, making our new node
        									//the new head 
        size++;//The size is increased 
    }

    /**
     * Removes the first element from this deque.
     * 
     * @precondition the size of this deque must be greater than zero.
     *
     * @throws IllegalStateException if this deque is empty.
     *
     * @postcondition After removeFirst() returns, this deque contains all of
     *                the elements it previously had, with the exception of
     *                the first element.  If there was previously only one
     *                element in the deque, then this deque is empty when this
     *                method returns.
     */
    public E removeFirst(){
        if(head == null)
            throw new IllegalStateException();
        E returnValue = head.value;//We get the head's value 
        head = head.next;//We make the head's next the new head
        size--;//The size is decremented 
        
        return returnValue;
    }

    /**
     * Removes the first element from this deque.
     * 
     * @precondition the size of this deque must be greater than zero.
     *
     * @throws IllegalStateException if this deque is empty.
     *
     * @postcondition After removeLast() returns, this deque contains all of the
     *                elements it previously had, with the exception of the last
     *                element.  If there was previously only one element in the
     *                deque, then this deque is empty when this method returns.
     */
    public E removeLast() {
    	if(head == null)
    		throw new IllegalStateException("The list is empty");
    	Node<E> node = head;
    	for (int i = size; i > 2; i--) // While the size of the deque is greater then 2
    		node = node.next;//Set the node to the one thats next in the deque
    	E returnValue = node.next.value;//Give the variable the data from the next node to return
    	node.next = null;//Set the end nodes next to null 
    	size--;

    	return returnValue;
    }

    /**
     * Returns a copy of the first element in this deque.
     *
     * @precondition the size of this deque must be greater than zero.
     *
     * @throws IllegalStateException if this deque is empty.
     *
     * @return a copy of the first element in this deque.
     *
     * @postcondition This method does not modify the abstract state of this
     *                deque.
     */
    public E peekFirst(){
        if(head == null)
            throw new IllegalStateException("The list is empty");
        return head.value;

    }

    /**
     * Returns a copy of the last element in this deque.
     *
     * @precondition the size of this deque must be greater than zero.
     *
     * @throws IllegalStateException if this deque is empty.
     *
     * @return a copy of the last element in this deque.
     *
     * @postcondition This method does not modify the abstract state of this
     *                deque.
     */
    public E peekLast(){
        if(head == null)
            throw new IllegalStateException("The list is empty");
        Node<E> node = head;//The node is set to the head
        while(node.next != null)//We loop through the deque until we reach the tail 
            node = node.next;
        return node.value;
    }

    /**
     * Returns the number of elements in this deque.
     *
     * @return the number of elements in this deque.
     *
     * @postcondition This method does not modify the abstract state of this
     *                deque.
     */
    public int size() {
        return size;
    }


    /**
     * Returns a String representation of this deque.  The format of the
     * String representation is: &lt;first, next, next, last&gt;.
     *
     * @return A String representation of this deque.
     *
     * @postcondition This method does not modify the abstract state of this
     *                deque.
     */
    @Override
    public String toString() {
        String message = "";

        if (size > 0) {
        	message += head.value.toString();

            for (Node<E> i = head.next; i != null; i = i.next) {
            	message += "\n";
            	message += i.value;
            	
            }
        }
        return message;
    }


    /**
     * Determines if this deque is equal to the given object.
     *
     * @param other The object with which to compare this deque.
     *
     * @return true if other object is a GenericLinkedDeque with the same
     * abstract state as this object; false otherwise.
     *
     * @precondition data types for &lt;E&ft; have a working equals method.
     * @postcondition This method does not modify the abstract state of this
     *                deque.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        boolean equal = (other == this);

        if (!equal && other instanceof Deque) {
        	Deque<E> o = (Deque<E>) other;

            equal = (this.size == o.size);

            for (Node<E> i = head, j = o.head; equal && i != null;
                                                      i = i.next, j = j.next) {
                equal = (i.value.equals(j.value));
            }
        }

        return equal;
    }


    /**
     * Creates a &quot;deep copy&quot; of this GenericLinkedDeque.
     *
     * @return a copy of this GenericLinkedDeque.
     *
     * @postcondition The returned object and this object can be manipulated
     *                independently of one another.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Deque<E> clone() {
        try {
        	Deque<E> copy = (Deque<E>) super.clone();

            if (head != null) {
                copy.head = new Node<E>(head.value, null); 

                Node<E> last = copy.head;
                for (Node<E> i = head.next; i != null; i = i.next) {
                    last.next = new Node<E>(i.value, null);
                    last = last.next;
                }
            }

            return copy;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * This class represents a node in the linked deque implementation.
     *
     * @author Dr. Dalton
     * @author Dr. Kreahling
     * @version March 8, 2011, March 29, 2012
     */
    private static class Node<E> {
        /** The value at this node's location. */
        private E   value;

        /** A reference to the next node in the linked list. */
        private Node<E> next;

        /**
         * Constructs a new Node given a value and a pointer to the next
         * node in the linked list.
         *
         * @param value The value this node should store.
         * @param next  A reference to the next node in the list, or null if
         *              this node is the last node.
         */
        public Node(E value, Node<E> next) {
            this.value = value;
            this.next  = next;
        }
    }// end Node class
}

