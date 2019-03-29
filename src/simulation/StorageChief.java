/**
 * A class that stores the computer parts.
 */
package simulation;

import internals.*;
import parts.*;

public class StorageChief {

	private IStack<IProduct>[] warehouses;
	private boolean initalised = false; 
	private int[] warehouseCounts;
	
	@SuppressWarnings("unchecked")
	public StorageChief() {
		this.warehouses = new ExpandableStack[5];
		for (int i = 0; i < 5; i++) {
			this.warehouses[i] = new ExpandableStack<IProduct>();
		}
		this.warehouseCounts = new int[5]; 
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
	
	/**
	 * Initilise the warehouse counts.
	 * Number of arrays shall be five, five shall be the number
	 * Thy should array, and the number of arraying shall be five,
	 * Six thou shall not array, neither array thou to four, excepting
	 * That thou then proceed to five.
	 */
	public void initiliseWarehouseCounts() {
		// RAM, CPU, Graphics Card, Motherboard, Cache
		for (int i = 0; i < 5; i++) {
			warehouseCounts[i] = warehouses[i].getSize();
		}
	}
	
	/**
	 * Return the warehouses' counts.
	 * @param warehouseType - Type of the warehouse whose count shall be returned.
	 * @return The number of elements inside Stack.
	 */
	public int getWarehouseCount(String warehouseType) {
		switch(warehouseType) {
		case "RAM":
			return warehouseCounts[0];
		case "CPU":
			return warehouseCounts[1];
		case "Graphics Card":
			return warehouseCounts[2];
		case "Motherboard":
			return warehouseCounts[3];
		case "Cache":
			return warehouseCounts[4];
		default:
			return -1;
		}
	}
}
