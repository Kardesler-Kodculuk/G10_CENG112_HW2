/**
 * Implement a Stack that can grow if needed.
 */
package internals;

@SuppressWarnings("unchecked")
public class ExpandableStack<T> implements IStack<T> {

	private T[] stack;
	private int top;
	
	private final int INCREMENT_SIZE = 5;
	private final int DEFAULT_SIZE = 10;
	private final int MAX_ELEMENT = 10000;
	private boolean initalised;

	public ExpandableStack() {
		T[] tempArray = (T[]) new Object[DEFAULT_SIZE];
		this.stack = tempArray;
		this.initalised = true;
		this.top = -1;
	}
	
	/**
	 * Check the size, if expandable, expand.
	 * @return true if still has empty space or if expandable, false otherwise.
	 */
	private boolean checkSize() {
		if (top + 1 == stack.length) {
			if (stack.length + INCREMENT_SIZE <= MAX_ELEMENT) {
				T[] tempStack = (T[]) new Object[stack.length + INCREMENT_SIZE];
				for (int i = 0; i < stack.length; i++) {
					tempStack[i] = this.stack[i]; // Copy the old stack's elements
				}
				this.stack = tempStack; // Switch stacks "resizing" it.
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Check if initialised.
	 * @throws IllegalAccessError if not initialised
	 * */
	private void isInitalised() {
		if (!this.initalised) {
			throw new IllegalAccessError("Not initalised.");
		}
	} 

	@Override
	public boolean push(T newElement) {
		isInitalised();
		if (newElement != null) {
			boolean appendable = checkSize();
			if (appendable) {
				this.stack[top + 1] = newElement;
				this.top++;
				return true;
			}
		}
		return false;
	}

	@Override
	public T pop() {
		isInitalised();
		if (!this.isEmpty()) {
			T returnObject = this.peek();
			this.stack[top] = null;
			this.top--;
			return returnObject;
		}
		return null;
	}

	@Override
	public T peek() {
		isInitalised();
		if (!this.isEmpty()) {
			return this.stack[top];
		}
		return null;
	}

	@Override
	public boolean empty() {
		isInitalised();
		T[] emptyArray = (T[]) new Object[DEFAULT_SIZE];
		this.stack = emptyArray;
		this.top = -1;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public int getSize() {
		isInitalised();
		return this.top + 1;
	}
}
