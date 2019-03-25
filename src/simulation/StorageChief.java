/**
 * A class that stores the computer parts.
 */
package simulation;

import internals.*;
import parts.*;

public class StorageChief {

	private IStack<IProduct>[] warehouses;
	private boolean initalised = false; 
	
	
	@SuppressWarnings("unchecked")
	public StorageChief() {
		this.warehouses = new ExpandableStack[5];
		for (int i = 0; i < 5; i++) {
			this.warehouses[i] = new ExpandableStack<IProduct>();
		}
		this.initalised = true;
	}
	
	private void isInitalised() {
		if (!initalised) throw new IllegalAccessError();
	}
	
	/**
	 * Find out which warehouse should the
	 * part go
	 * @param part computer part wished to be classified
	 * @return id of the warehouse in array warehouses. -1 if part not in list.
	 */
	private static int classify(IProduct part) {
		IProduct[] stackID = {new RAM(),
				new CPU(),
				new GraphicsCard(),
				new Motherboard(),
				new Cache()};
		for (int i = 0; i < stackID.length; i++) { // Traverse the array to find which object
			if (part.getType().equals(stackID[i].getType())) { //Shares the same class
				return i; // Which is a parallel array with warehoues.
			}
		} 
		return -1;
	}
	
	/**
	 * Store the item.
	 * @param factory factory object.
	 * @return null if factory empty, product stored if not.
	 */
	public IProduct store(IztechPCFactory factory) {
		isInitalised();
		if (!factory.isEmpty()) {
			IProduct product = factory.getProduct();
			product.setState("stored");
			int warehouseID = classify(product);
			this.warehouses[warehouseID].push(product);
			return product;
		}
		return null;
	}
	
	/**
	 * Take out a product from storage to be sold.
	 * @param productType An INSTINCE of the class wished.
	 * @return product if available, null if not.
	 */
	public IProduct getProduct(IProduct productType) {
		isInitalised();
		int warehouseID = classify(productType);
		if (warehouses[warehouseID].isEmpty()) {
			return null; 
		}
		return warehouses[warehouseID].pop();
		
	}
}
