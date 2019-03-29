package simulation;

import internals.IQueue;
import internals.NewCircularQueue;
import parts.*;

public class IztechPCFactory {
	
	IQueue<IProduct> factoryLine = new NewCircularQueue<IProduct>();
	
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
	
	/**
	 * It returns an array with itemCounts
	 * index 0 -> "RAM" count
	 * index 1 -> "CPU" count
	 * index 2 -> "Graphics Card"
	 * index 3 -> "Motherboard"
	 * index 4 -> "Cache"
	 * @return an array with itemCounts
	 */
	public int[] itemCounts() {
		IProduct[] queue;
		queue = factoryLine.getArray();
		int[] countArray;
		countArray = new int[5];
		for(int i = 0; i < factoryLine.getLength(); i++)
		{
			String part = queue[i].getType();
			switch(part) 
			{
			case "RAM": // TODO if there is an error you should check this 
				countArray[0]++;
				break;
			case "CPU":
				countArray[1]++;
				break;
			case "Graphics Card":
				countArray[2]++;
				break;
			case "Motherboard":
				countArray[3]++;
				break;
			case "Cache":
				countArray[4]++;
				break;
			}
		}
		return countArray;
	}
	
	
	
}
