package internals;

public class ArrayQueue<T> implements IQueue<T> {
	
	private int frontIndex = 0;
	private int backIndex = -1;
//	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 10000;
	private T[] queue;
	
	
	/**
	 * Constructs a queue with default capacity of 10
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		queue = (T[]) new Object[DEFAULT_CAPACITY];
//		initialized = true;
	}
	
	/*
	 * I think this method is not necessary actually pointless unless we have a static method?
	 */
//	private void checkInitialization() {
//		if(!initialized)
//		{
//			throw new IllegalStateException("Not initialized");
//		}
//	}

	/**
	 * Expands the size of the queue when the queue is full
	 */
	private void ensureCapacity() {
		if((backIndex + 1) % queue.length == frontIndex && queue.length <= MAX_CAPACITY)
		{
			@SuppressWarnings("unchecked")
			T[] newArray = (T[]) new Object[queue.length + 10];
			for(int i = 0; i < queue.length; i++)
			{
				newArray[i] = queue[i];
			}
			queue = newArray;
		}
		else
		{
			throw new IllegalStateException("Reached max capacity");
		}
	}
	
	@Override
	public void enQueue(T newEntry) {
//		checkInitialization();
		if(queue.length == backIndex)
		{
			ensureCapacity();
		}
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	}

	@Override
	public T deQueue() {
//		checkInitialization();
		if(!isEmpty()) {
			T tempEntry = (T) queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return tempEntry;
		}
		return null;
	}

	@Override
	public T getFront() {
		return queue[frontIndex];
	}

	@Override
	public boolean isEmpty() {
		if(queue[frontIndex] == null) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		for(int i = 0; i < queue.length; i++) {
			queue[i] = null;
		}
	}

}
