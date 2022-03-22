//LINKED STACK

import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in a chain of nodes
 */

 public final class LinkedStack<T> implements StackInterface<T> {

     private Node topNode; //references top node in chain

     public LinkedStack() {
         topNode = null;
     }

     
     /** 
      * @param newEntry  the item to be added to the stack
      */
     //implementations of stack operations

     //adds a new entry to the top of the stack
     public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
     }

     
     /** 
      * @return T the entry that is being removed
      */
     //removes the entry at the top of the stack
     public T pop() {
         T top = peek(); //Might throw EmptyStackException
         //assume topNode != null
         topNode = topNode.getNextNode();

         return top;
     }

     
     /** 
      * @return T the top entry in the stack
      */
     public T peek() {
         if (isEmpty()) {
            throw new EmptyStackException();
         } else {
             return topNode.getData();
         }
     }

     
     /** 
      * @return boolean True if the stack is empty
      */
     public boolean isEmpty() {
         return topNode == null;
     }

     //emptys the stack
     public void clear() {
         topNode = null;
     }


    private class Node {
        private T data; // entry in stack
        private Node next; //link to next node

        private Node(T d, Node n) {
            data = d;
            next = n;
        }

        private T getData() { return data; }
    
        private Node getNextNode() { return next; }
    }//end Node
 }//end LinkedStack