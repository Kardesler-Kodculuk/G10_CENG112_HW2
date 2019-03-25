package simulation;

import parts.IProduct;

public class Customer {
	
	public Customer() {
		
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
		return true;
	}
}
