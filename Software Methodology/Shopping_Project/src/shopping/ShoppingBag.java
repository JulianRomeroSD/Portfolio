package shopping;
/**
This is a shopping bag class that takes grocery items as input, has a capacity of
five initially, and doubles its capacity by 5 when the bag has reached its capacity.
This class has nine methods and is capable of adding, finding, and removing 
specific items. As well as being able to grow the bag, clear the bag, and calculate
total sale price and tax. The class can also tell you the number of items in the bag
and can print what is currently in the bag.
@author Julian Romero, Steven Nguyen
*/


public class ShoppingBag {
	
		private GroceryItem[] bag; // array-based implementation of the bag
		private int size; // number of items currently in the bag
		
		/**
		*This constructor sets every instance of the ShoppingBag to size 0
		*and the array length to size 5.
		*/
		public ShoppingBag() {
			this.size = 0;
			this.bag = new GroceryItem[5];
		}
		
		/**
		*Finds the item in the list and returns the index in the bag
		*returns -1 if the item doesn't appear in the bag
		*@param obj	name of the item to be found
		*@return 	the int found returns index otherwise returns -1
		*/
		private int find(GroceryItem item) { 
			for(int i = 0; i < this.bag.length; i++) {
				if(this.bag[i] == null) {
					continue;
				}
				if(this.bag[i].equals(item)) {
					return i;
				}
				
			}
			return -1;
			
		} 
		
		/**
		*Grows the item by 5 if the bag goes past its respective capacity
		*/
		private void grow() { 
				GroceryItem[] newBag = new GroceryItem[this.bag.length+5]; 
				System.arraycopy(this.bag, 0, newBag, 0, this.bag.length);
				this.bag = newBag;
			
		}
		
		/**
		*Adds an item to the bag in the next available spot if the bag goes past its
		*capacity the grow methods is called to increase the capacity to the bag
		*@param 	obj Grocery item to be added
		*/
		public void add(GroceryItem item) {
			this.size++;
			if(this.size > this.bag.length) {
				this.grow();
			}
			for(int i = 0; i < this.bag.length; i++) {
				if(this.bag[i] == null) {
					this.bag[i] = item;
					break;
				}
			}
			
		}
		
		/**
		*Finds the item in the list using the find helper methods. 
		*If found returns true and removes by setting the index found to the last element 
		*in the list, and sets the last element to null. Otherwise returns 
		*false and do nothing
		*@param obj		name of the item to be removed
		*@return 	if the boolean if item is in the bag return true otherwise return false
		*/
		public boolean remove(GroceryItem item) {
			int index = this.find(item);
			if (index >= 0) {
				this.size--;
				this.bag[index] = this.bag[bag.length-1];
				this.bag[this.bag.length-1] = null;
				return true;
			}
			else{
				return false;
			
			}
			

		}
		
		/**
		*Calculates the total price of each item in the bag 
		*@return 	Returns the double total sale price of the bag 
		*/
		public double salesPrice() {
			double totalSale = 0;
			for(int i = 0; i < bag.length; i++) {
				if(this.bag[i] == null) {
					continue;
				}
				else {
					totalSale += this.bag[i].getPrice();
				}
			}
			return totalSale;
		}
		
		/**
		*Calculates the total sale tax of each item in the bag 
		*@return 	Returns the double total sale tax price of the bag 
		*/
		public double salesTax() { 
			double totalSaleTax = 0;
			for(int i = 0; i < this.bag.length; i++) {
				if(this.bag[i] == null) {
					continue;
				}
				if(this.bag[i].getTaxable()==true) {
					totalSaleTax += (this.bag[i].getPrice() * 0.06625);
				}
				else {
					continue;
				}
			}
			
			return totalSaleTax;
		}
		
		/**
		*Prints the item name first, the sale price second, 
		*and if the item is taxable last 
		*/
		public void print() { 
			  for (GroceryItem element: this.bag) {
				  if(element == null) {
					  continue;
				  }
		            System.out.println(element);
		            
		        }
		}
		
		/**
		*This method checks the amount of items in the bag by 
		*returning the size. To give the amount of items in the bag.
		*@return 	Returns int amount of items in the bag
		*/
		public int getItemCount() {
			return this.size;
		}
		
		/**
		*clears all items in the bag by assigning a new 
		*bag to the instance of bag, and reseting the size instance to 0 
		*/
		public void clear() {
			GroceryItem[] newBag = new GroceryItem[5]; 
			this.bag = newBag;
			this.size = 0;
		}
		
	/**
	*Testbed for implementing the test cases in test document.
	*This testbed tests the following methods: 
	*add, remove, grow, and saleTax
	* @param args	command line arguments, not used
	*/	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GroceryItem apple = new GroceryItem("Apple" , 2.12 , true);
		GroceryItem skirt = new GroceryItem("Skirt" , 10.05 , false);
		GroceryItem puppy = new GroceryItem("Puppy" , 500.00 , true);
		GroceryItem pencil = new GroceryItem("Pencil" , 1.00 , true);
		
		ShoppingBag x = new ShoppingBag();
		
		//case 1: test for add
		System.out.println("Test Case1: case1");
		x.add(apple);
		System.out.println("number of items: " + x.getItemCount());
		x.print();
		System.out.println("Test Case1: case2");
		x.add(apple);
		System.out.println("number of items: " + x.getItemCount());
		x.print();
		
		System.out.println("Test Case1: case3");
		x.add(skirt);
		System.out.println("number of items: " + x.getItemCount());
		x.print();
		
		//case 2: test for remove
		System.out.println("Test Case2: case1");
		System.out.println(x.remove(skirt));
		System.out.println("number of items: " + x.getItemCount());
		x.print();
		System.out.println("Test Case2: case2");
		System.out.println(x.remove(puppy));
		System.out.println("number of items: " + x.getItemCount());
		x.print();
		System.out.println("Test Case2: case3");
		System.out.println(x.remove(apple));
		System.out.println(x.remove(apple));
		System.out.println("number of items: " + x.getItemCount());
		x.print();
		
		//case 3: test for grow()
		System.out.println("Test Case3: case1");
		x.add(apple);
		x.add(apple);
		x.add(apple);
		x.add(apple);
		x.add(apple);
		x.print();
		System.out.println("number of items: " + x.getItemCount());
		System.out.println("array_length: " + x.bag.length);
		System.out.println("Test Case3: case2");
		x.add(pencil);
		x.print();
		System.out.println("number of items: " + x.getItemCount());
		System.out.println("array_length: " + x.bag.length);
		System.out.println("Test Case3: case3");
		x.add(apple);
		x.add(apple);
		x.add(apple);
		x.add(apple);
		x.add(apple);
		x.print();
		System.out.println("number of items: " + x.getItemCount());
		System.out.println("array_length: " + x.bag.length);
		
		//case 4: test for salesTax()
		
		System.out.println("Test Case4: case1");
		System.out.printf("sales_Tax: $%.2f", x.salesTax());
		System.out.println();
		System.out.println("Test Case4: case2");
		x.add(skirt);
		System.out.printf("sales_Tax: $%.2f", x.salesTax());
		
	}
}

