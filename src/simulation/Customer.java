package simulation;

import parts.IProduct;

public class Customer {
	private int[] itemCounts;

	public Customer() {
		this.itemCounts = new int[5];
		this.itemCounts[0] = 0;
		this.itemCounts[1] = 0;
		this.itemCounts[2] = 0;
		this.itemCounts[3] = 0;
		this.itemCounts[4] = 0;
	}

	/**
	 * Customer buys item from storage
	 * @param part - name of the part
	 * @param storage - name of the storage
	 * @return true if success, false if not
	 */
	public boolean buy(IProduct part, StorageChief storage) {
		IProduct demandedPart = storage.getProduct(part);
		if(demandedPart == null) {
			return false;
		}
		demandedPart.setState("sold");
		setItemCounts(part);
		return true;
	}
	
	/**
	 * Set the count of item
	 * @param part - part whose type's count of item shall be increamented.
	 */
	private void setItemCounts(IProduct part) {
		switch(part.getType()) {
		case "RAM":
			itemCounts[0]++;
			break;
		case "CPU":
			itemCounts[1]++;
			break;
		case "Graphics Card":
			itemCounts[2]++;
			break;
		case "Motherboard":
			itemCounts[3]++;
			break;
		case "Cache":
			itemCounts[4]++;
			break;
		default:
			break;
		}
	}
	
	/**
	 * Return the count of items
	 * @param itemType - Type of item whose counts shall be returned.
	 * @return The items' count.
	 */
	public int getItemCounts(String itemType) {
		switch(itemType) {
		case "RAM":
			return itemCounts[0];
		case "CPU":
			return itemCounts[1];
		case "Graphics Card":
			return itemCounts[2];
		case "Motherboard":
			return itemCounts[3];
		case "Cache":
			return itemCounts[4];
		default:
			return -1;
		}
	}
}
