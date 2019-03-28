package internals;

public class NewCircularQueue<T> implements IQueue<T> {

	private final static int DEFAULT_SIZE = 10;
	private final static int MAX_SIZE = 10000;
	T[] queue;
	private int frontIndex = 0;
	private int backIndex = -1;
	
	
	
	public NewCircularQueue() {
		this(DEFAULT_SIZE);
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
		else if((backIndex + 2) % queue.length == frontIndex % queue.length) 
		{
			@SuppressWarnings("unchecked")
			T[] newQueue = (T[]) new Object[queue.length + 10];
			for(int i = 0; i < queue.length; i++) 
			{
				newQueue[i] = queue[i];
			}
			queue = newQueue;
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
			if(queue[(frontIndex + 1) % queue.length] != null)
			{
				frontIndex = (frontIndex + 1) % queue.length;
			}
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

}
