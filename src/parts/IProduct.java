/** Interface for the product ADT. */
package parts;

public interface IProduct {
	/** Determine if the product is produced.
	 * @return true if manifactured, false, otherwise.
	 */
	public boolean isManufactured();
	/** Determine if the item is stored.
	 * @return true if stored, false if not.
	 */
	public boolean isStored();
	/** Determine if the object is solde
	 * @return true if sold, false, otherwise.
	 */
	public boolean isSold();
	/**
	 * Get the product type of the product
	 * @return product type as a string
	 */
	public String getType();
	/**
	 * Set the state of the object.
	 */
	public boolean setState(String state);
}
