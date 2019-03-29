package internals;

public interface IStack<T> {
	/**
	 * Add new element to the top of the stack.
	 * @param newElement - Element to push
	 * @return true if successful, false if not.
	 */
	public boolean push(T newElement);
	/**
	 * Take out the item at top of stack.
	 * @return last item if successful, null otherwise
	 */
	public T pop();
	/**
	 * Return the last item without deleting it
	 * @return last item if possible, otherwise null.
	 */
	public T peek();
	/**
	 * Delete all items in the stack.
	 * @return the item if successful, null if empty., 
	 */
	public boolean empty();
	/**
	 * @return true if empty, false, otherwise.
	 */
	public boolean isEmpty();
	
	/**
	 * Get the current size of the stack
	 * @return the current size.
	 */
	public int getSize();
}
