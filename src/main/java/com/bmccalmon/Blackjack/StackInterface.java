package com.bmccalmon.Blackjack;

public interface StackInterface<T> {

	  /**
	   * Adds the given element to the top of the stack.
	   *
	   * @param element the element to add to the top of the stack.
	   */
	  void push(T element);

	  /**
	   * Removes the top element of the stack and returns it.
	   *
	   * @return the top element of the stack.
	   */
	  T pop();

	  /**
	   * Returns the top element of the stack without removing it.
	   *
	   * @return the top element of the stack.
	   */
	  T peek();

	  /**
	   * Returns the number of elements in the stack.
	   *
	   * @return the number of elements in the stack.
	   */
	  int size();

	  /**
	   * Returns true if the stack is empty and false otherwise.
	   *
	   * @return true if the stack is empty, false otherwise.
	   */
	  boolean isEmpty();
	}
