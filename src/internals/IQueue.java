package internals;

public interface IQueue<T> 
{
	/**
	 * Adds entry to the queue (to the last)
	 * @param newEntry - Name of the entry
	 */
	public void enQueue(T newEntry);
	/**
	 * Removes front entry from the queue
	 * @return Entry if removed, null if the queue is empty
	 */
	public T deQueue();
	/**
	 * Gets front entry from the queue
	 * @return Entry if the queue not empty, null if not
	 */
	public T getFront();
	/**
	 * Checks the queue is empty or not
	 * @return true if the queue is empty, false if not
	 */
	public boolean isEmpty();
	/**
	 * Clears the whole queue
	 */
	public void clear();
}
