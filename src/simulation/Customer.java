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
		if(storage.getProduct(part) == null) { // TODO If there is an error check this
			return false;
		}
		storage.getProduct(part);
		return true;
	}
}
