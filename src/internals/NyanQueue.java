package internals;

public class NyanQueue<T> implements IQueue<T> {
	
	private T[] queue;
	private final int DEFAULT_CAPACITY = 10;
	private final int MAX_CAPACITY = 10000;
	private int frontIndex;
	private int backIndex;

	@SuppressWarnings("unchecked")
	public NyanQueue() {
		this.frontIndex = 0;
		this.backIndex = 0;
		queue = (T[]) new Object[DEFAULT_CAPACITY];
	}

	private void ensureCapacity() {
		if (backIndex + 1 == queue.length) {
			T[] tempArray = (T[]) new Object[queue.length + 10];
			for (int i = 0; i < queue.length; i++) {
				tempArray[i] = queue[i];
			}
			this.queue = tempArray;
		}
	}

	@Override
	public void enQueue(T newEntry) {
		ensureCapacity();
		this.queue[this.backIndex] = newEntry;
		this.backIndex++;
	}

	@Override
	public T deQueue() {
		if (!isEmpty()) {
			T item = this.queue[this.frontIndex];
			this.queue[this.frontIndex] = null;
			this.frontIndex++;
			return item;
		}
		return null;
	}

	@Override
	public T getFront() {
		return this.queue[frontIndex];
	}

	@Override
	public boolean isEmpty() {
		return this.frontIndex == this.backIndex;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		queue = (T[]) new Object[DEFAULT_CAPACITY];
	}

}
