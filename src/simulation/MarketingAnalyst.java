package simulation;

import parts.IProduct;

public class MarketingAnalyst<T> {

	public MarketingAnalyst() {
	}
	
	/**
	 * Orders to the IztechPCFactory for produce
	 * @param part - name of the part
	 */
	public static void orderPart(IProduct part, IztechPCFactory factory) {
		factory.producePart(part);
	
	}

}
