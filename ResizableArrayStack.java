//RESIZABLE ARRAY STACK

import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizableArrayStack<T> implements StackInterface<T>{
    
    private T[] stack; //array of stack entries
    private int topIndex; //index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000; //10,000

    public ResizableArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ResizableArrayStack(int initialCapacity) {
        integrityOK = false;
        checkCapacity(initialCapacity);

        //new array contains null entries so cast is safe
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    }

    
    /** 
     * @param newEntry entry to be added to the stack
     */
    //add entry to top of array
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex+1] = newEntry;
        topIndex++;
    }

    //make sure there's enough room in the array
    private void ensureCapacity() {
        if(topIndex >= stack.length - 1) { //if array full, double size
            int newLength = 2*stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    
    /** 
     * @return T  the entry being removed from stack
     */
    //removing the top entry
    public T pop() {
        checkIntegrity();
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }

    
    /** 
     * @return T  returns the top entry
     */
    public T peek() {
        checkIntegrity();
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack[topIndex];
        }
    }

    
    /** 
     * @return boolean  returns true if empty
     */
    public boolean isEmpty() {
        return topIndex < 0;
    }

    //clears the stack
    public void clear() {
        checkIntegrity();

        //note: remove references to the objects in the stack
        //do not deallocate the array
        while(topIndex > -1) {
            stack[topIndex] = null;
            topIndex--;
        }
    }

    /** 
     * @param size the initial size of the bag
     */
    private void checkCapacity(int size) {
        if (size > MAX_CAPACITY) {
            throw new IllegalStateException(("Attempt to create a bag whose" 
            + "capacity exceeds allowed maximum of " + MAX_CAPACITY));

        }
    }

    private void checkIntegrity() {
        if(!integrityOK) {
            throw new SecurityException("Resizable Array Bag object is corrupt");
        }
    }
}
