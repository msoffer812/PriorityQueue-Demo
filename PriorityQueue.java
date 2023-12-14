/**
 * Priority queue to hold the shoppers.
 * Implements "Node" class to hold the insertion order of any generic object, which
 * can be used for prioritizing
 */
package shoppingHW;



@SuppressWarnings("rawtypes")
public class PriorityQueue<T extends Comparable>
{
	 private Node[] array;				/* Holds the elements - didn't realize I could use the JCF so used an array, not dynamic */
	 private int size;						/* Size of the queue for O(1) calculation*/
	 private int elementsInsertedCount;		/* For assigning a shopper an insertion order when it's put into the array for comparisons*/

	 /**
	  * Object to put element in,
	  * and save insertion order
	  * @author mbrso
	  *
	  */
	 @SuppressWarnings("hiding")
	private class Node<T>{
		 int insertionOrder;
		 T value;
		 
		 public Node(int insertionOrder, T value) {
			 this.insertionOrder = insertionOrder;
			 this.value = value;
		 }
	 }
		public PriorityQueue(int size) {
	        this.array = new Node[size];
	        this.size = 0;
	        this.elementsInsertedCount = 0;
	    }
		
		/**
		 * Add an element to the structure, maintaining its order
		 * @param element
		 * @see heapifyUp()
		 */
	    public void enqueue(T element) {
	    	/**
	    	 * If the queue is maxed out already, throw the exception
	    	 */
	    	if(size == array.length)
	    	{
	    		throw new IndexOutOfBoundsException("Queue is full");
	    	}else
	    	{
	    		array[size] = new Node<T>(elementsInsertedCount, element);	/* Assign the last available index to the element */
	    		size++;					/* Increment the size, since a new element has been added */
	    		elementsInsertedCount++;
		        heapifyUp();			/* Call the method that restructures the array, keeping it's sorted order */
	    	}
	    }
	    
	    /**
	     * 
	     * @return the element of most priority in the queue, then reorder
	     * to maintain the structure
	     * @see heapifyDown()
	     */
	    public T dequeue() {
	    	/*
	    	 * If there's no element to return, throw an exception
	    	 */
	        if (isEmpty()) {
	            throw new IllegalStateException("Priority queue is empty");
	        }
	        @SuppressWarnings("unchecked")
			T highestPriorityElement = (T) array[0].value; /* The highest priority element is always the first item in the array */
	        int lastIndex = size - 1;			 /* The last index that contains queue contents in the array */
	        array[0] = array[lastIndex];		 /* Assign the last element in the array to the now empty hole
	         											where the highest priority element was, then we'll call the method to reorder*/
	        size--;								 /* Decrement the  size, since you've removed an element */
	        heapifyDown(); 
	        array[lastIndex] = null;			 /* Not strictly necessary but it feels wrong to have unused elements in the array */
	        return highestPriorityElement;
	    }

	    /**
	     * 
	     * @return boolean if the queue is empty or not
	     */
	    public boolean isEmpty() {
	        return size == 0;
	    }
	    
	    /**
	     * @return boolean if the queue is full
	     */
	    public boolean isFull() {
	    	return size == array.length;
	    }
	    /**
	     * 
	     * @return size of the queue(amount of elements)
	     */
	    public int size()
	    {
	    	return this.size;
	    }
	    /**
	     * We've put the new element we've just added to the bottom of the queue,
	     * now we push it up to the right place in the priorityQueue by swapping it with parents 
	     * that are greater than it until it's reached its right place
	     */
	    @SuppressWarnings("unchecked")
		private void heapifyUp() {
	        int currentIndex = size - 1; /* We're starting the element at the end of the queue */
	        
	        while (currentIndex > 0) {
	            int parentIndex = getParent(currentIndex);
	            
	            /*
	             * If the currentIndex is greater in priority to its parents, then switch the two.
	             * Then change the currentIndex to reflect the position of the element being moved up.
	             */
	            if (((T) array[currentIndex].value).compareTo(array[parentIndex].value) > 0) {
	                swap(currentIndex, parentIndex);
	                currentIndex = parentIndex; 
	            }
	            /*
	             * Otherwise, we leave it as is and end the loop
	             */
	            else {
	                break; 
	            }
	        }
	    }
	    
	    /**
	     * 
	     * @param childIndex
	     * @return it's "Parent" index in the queue, that is, the index of the element that should
	     * be greater than it in priority
	     */
	    private int getParent(int childIndex)
	    {
	    	return (childIndex - 1) / 2;
	    }
	    
	    /**
	     * Move the element reached from the bottom to put into the hole
	     * left by the dequeued element to it's proper place, hence
	     * restoring the heap structure of the queue
	     * 
	     * @see getChild(currentIndex);
	     */
	    private void heapifyDown() {
	        int currentIndex = 0;
	        
	        /*
	         * Loop continuously,
	         * seeing if there are any children of a higher priority that should be
	         * swapped with the current element,
	         * and if there are then swap them and shift the current index to reflect the new
	         * location of the element. Otherwise, The method returns the old index, and the loop breaks,
	         * as the heap structure is all right again
	         */
	        while (true) {
	        	int smallestPriorityIndex = currentIndex;
	        	smallestPriorityIndex = getChild(smallestPriorityIndex);

	            if (smallestPriorityIndex != currentIndex) {
	                swap(currentIndex, smallestPriorityIndex);
	                currentIndex = smallestPriorityIndex;
	            } else {
	                break; 
	            }
	        }
	    }
	    
	    /**
	     * 
	     * @param currentIndex
	     * @return the index of greatest priority - 
	     * either the parent, or one of the children.
	     * Whichever one will be swapped.
	     */
	    @SuppressWarnings("unchecked")
		private int getChild(int currentIndex)
	    {
	    	int leftChildIndex = 2 * currentIndex + 1;		/* The left child of the current index */
            int rightChildIndex = 2 * currentIndex + 2;		/* The right child of the current index */
	    	 
            /*
             * We move from left to right, checking if either child exists, is greater than the parent,
             * and if so, assign it as the index that should be swapped with the parent.
             */
            if(indexIsNotOutOfBounds(leftChildIndex)) {
	            if (newIndexPriorityIsGreaterThanOldIndexPriority(leftChildIndex, currentIndex)) {
	            		currentIndex = leftChildIndex;
	            } else if(((T) array[leftChildIndex].value).compareTo(array[currentIndex].value) == 0 && 
	            		newIndexWasInsertedBeforeOldIndex(leftChildIndex, currentIndex)) {
	        			currentIndex = leftChildIndex;
	        	} 	
            }
            if(indexIsNotOutOfBounds(rightChildIndex)) {
	            if (newIndexPriorityIsGreaterThanOldIndexPriority(rightChildIndex, currentIndex)) {
	            		currentIndex = rightChildIndex;
	            } else if(((T) array[rightChildIndex].value).compareTo(array[currentIndex].value) == 0 && 
	            		newIndexWasInsertedBeforeOldIndex(rightChildIndex, currentIndex)) {
	        			currentIndex = rightChildIndex;
	        	} 	
            }
            return currentIndex;
	    }
	    
	    /**
	     * Helps with code readability,
	     * For abstraction purposes
	     * @param childIndex
	     * @param currIndex
	     * @return if new index was inserted before old one
	     */
	    private boolean newIndexWasInsertedBeforeOldIndex(int childIndex, int currIndex) {
	    	return array[childIndex].insertionOrder < array[currIndex].insertionOrder;
	    }
	    /**
	     * Helps with code readability,
	     * For abstraction purposes
	     * @param childIndex
	     * @param currIndex
	     * @return exactly what it says
	     */
	    @SuppressWarnings("unchecked")
		private boolean newIndexPriorityIsGreaterThanOldIndexPriority(int childIndex, int currIndex) {
	    	return ((T) array[childIndex].value).compareTo(array[currIndex].value) > 0;
	    }
	    /**
	     * Helps with code readability,
	     * For abstraction purposes
	     * @param index
	     * @return
	     */
	    private boolean indexIsNotOutOfBounds(int index) {
	    	return index < size;
	    }
	    /*
	     * swap the values of the given indices of an array, useful for resorting
	     */
	    private void swap(int i, int j) {
	    	Node temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	    }
	}

