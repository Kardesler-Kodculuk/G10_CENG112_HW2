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
}
