package simulation;

import internals.ArrayQueue;
import parts.*;

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
		String typeOfObject = part.getType();
		switch(typeOfObject) {
		case "RAM":
			part = new RAM();
			break;
		case "CPU":
			part = new CPU();
			break;
		case "Graphics Card":
			part = new GraphicsCard();
			break;
		case "Motherboard":
			part = new Motherboard();
			break;
		case "Cache":
			part = new Cache();
			break;
		default:
			System.out.println("Error."); //TODO: Fix This!
		}
		part.setState("manufactured");
		factoryLine.enQueue(part);
	}
	
	
	
}
