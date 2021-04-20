package shopping;


import java.text.DecimalFormat;

/**
* This class defines a grocery item to be used in ShoppingBag
* @author Julian Romero, Steven Nguyen
*/
public class GroceryItem {
	private String name;
	private double price;
	private boolean taxable;
	
	/**
	 * Constructs a new grocery item with a given name, price, and taxable
	 * @param _name		the name of the new grocery item
	 * @param _price	the price of the new grocery item
	 * @param _taxable	if the new grocery item is taxable
	 */
	public GroceryItem(String _name, double _price, boolean _taxable) {
		name = _name;
		price = _price;
		taxable = _taxable;
	}
	
	/**
	 * Gets the name of this item
	 * @return		the String name of this grocery item
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the price of this item
	 * @return		the double price of this grocery item
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Gets if this item is taxable
	 * @return		the boolean taxable of this grocery item
	 */
	public boolean getTaxable() {
		return this.taxable;
	}
	
	/**
	 * Checks if this grocery item is equivalent to another object
	 * @param obj	the object to compare to this grocery item
	 * @return		true if equivalent
	 */
	public boolean equals(Object obj) {
		if (obj instanceof GroceryItem) {
			GroceryItem item = (GroceryItem) obj;
			return
				(item.getName().equals(this.name)) &&
				(item.getPrice() == this.price) &&
				(item.getTaxable() == this.taxable);
		} else {
			return false;
		}
	}
	
	/**
	 * Converts this.taxable to a string to be used in toString()
	 * @return "taxable" if this grocery item is taxable and "tax free" otherwise
	 */
	private String getTaxableString() {
		if (this.taxable == true) {
			return "taxable";
		} else {
			return "tax free";
		}
	}
	
	/**
	 * Returns a string representation of a grocery item
	 * Format: "itemName: $xx.xx : [tax free, taxable]"
	 * @return		the string representation
	 */
	public String toString() {
		String priceFormat = "$##.##";
		DecimalFormat formattedPrice = new DecimalFormat(priceFormat);
		String taxableString = getTaxableString();

		return this.name + ": " + formattedPrice.format(this.price) + " : " + taxableString;  
	}
}
	/**
	 * Tests the GroceryItem class's functions
	 * @param args	command line arguments, not used
	 * @return		nothing
	 */