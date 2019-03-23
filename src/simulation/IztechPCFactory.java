package simulation;

import internals.ArrayQueue;
import parts.IProduct;

public class IztechPCFactory {
	
	ArrayQueue<IProduct> factoryLine = new ArrayQueue<IProduct>();
	
	public IztechPCFactory() {
		
	}
	
	/**
	 * Removes product from factory line (first product in the line)
	 * @return product
	 */
	public IProduct getProduct() {
		return factoryLine.deQueue();
	}
	
	/**
	 * Checks the factory if its empty or not
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		if(factoryLine.isEmpty()) {
			return true;
		}
		else {return false;}
	}
	
	/**
	 * Produces product (adds to the end of the line)
	 * @param part - name of the part
	 */
	public void producePart(IProduct part) {
		factoryLine.enQueue(part);
	}
	
	
	
}
