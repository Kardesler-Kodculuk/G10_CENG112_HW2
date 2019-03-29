package internals;

public class NewCircularQueue<T> implements IQueue<T> {

	private final static int DEFAULT_SIZE = 10;
	private final static int MAX_SIZE = 10000;
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	
	
	
	public NewCircularQueue() {
		this(DEFAULT_SIZE);
		frontIndex = 0;
		backIndex = -1;
	}
	
	@SuppressWarnings("unchecked")
	public NewCircularQueue(int size) {
		queue = (T[]) new Object[size];
	}

	private void ensureCapacity() {
		if(queue.length == MAX_SIZE)
		{
			throw new IllegalStateException("Reached max size");
		}
		else if((backIndex + 2) % queue.length == frontIndex % queue.length) // if close to full 
		{
			@SuppressWarnings("unchecked")
			T[] newQueue = (T[]) new Object[queue.length + 10]; 
			for(int i = 0; i < queue.length; i++) // moving entries to the expanded queue
			{
				newQueue[i] = queue[frontIndex % queue.length];
				frontIndex++;
			}

			queue = newQueue;
			frontIndex = 0; // setting frontIndex for expanded queue
			backIndex = queue.length - 12; // setting backIndex for expanded queue
		}
	}
	
	
	@Override
	public void enQueue(T newEntry) {
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;

		
	}

	@Override
	public T deQueue() {
		if(!isEmpty()) 
		{
			T removedItem = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return removedItem;
		}
		else
		{
			return null;
		}
	}

	@Override
	public T getFront() {
		T frontEntry = queue[frontIndex];
		return frontEntry;
	}

	@Override
	public boolean isEmpty() {
		if(queue[frontIndex] == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void clear() {

		while(queue[frontIndex] != null)
		{
			deQueue();
		}
	}
	
	@Override
	public int getLength() {
		int tempLength = queue.length;
		return tempLength;
	}
	
	@Override
	public T[] getArray() {
		T[] tempArray = queue;
		return tempArray;
	}

}
