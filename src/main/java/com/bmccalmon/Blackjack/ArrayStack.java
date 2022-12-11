package com.bmccalmon.Blackjack;

public class ArrayStack<T> implements StackInterface<T> {

	  // The array that stores the elements in the stack.
	  private T[] elements;

	  // The index of the top element in the stack.
	  private int top = -1;

	  // The initial capacity of the stack.
	  private static final int INITIAL_CAPACITY = 16;

	  public ArrayStack() {
	    this(INITIAL_CAPACITY);
	  }

	  @SuppressWarnings("unchecked")
	  public ArrayStack(int initialCapacity) {
	    // Create a new array with the given initial capacity.
	    elements = (T[]) new Object[initialCapacity];
	  }

	  @Override
	  public void push(T element) {
	    // Make sure the stack has enough capacity.
	    if (top == elements.length - 1) {
	      grow();
	    }

	    // Add the new element to the top of the stack.
	    elements[++top] = element;
	  }

	  @Override
	  public T pop() {
	    if (isEmpty()) {
	      return null;
	    }

	    // Remove the top element from the stack.
	    T element = elements[top];
	    elements[top--] = null;
	    return element;
	  }

	  @Override
	  public T peek() {
	    if (isEmpty()) {
	      return null;
	    }

	    // Return the top element of the stack.
	    return elements[top];
	  }

	  @Override
	  public int size() {
	    return top + 1;
	  }

	  @Override
	  public boolean isEmpty() {
	    return size() == 0;
	  }

	  // Increases the capacity of the stack.
	  private void grow() {
	    // Create a new array with double the size.
	    T[] newElements = (T[]) new Object[elements.length * 2];

	    // Copy the old elements into the new array.
	    for (int i = 0; i < elements.length; i++) {
	      newElements[i] = elements[i];
	    }

	    // Replace the old array with the new one.
	    elements = newElements;
	  }
	}
