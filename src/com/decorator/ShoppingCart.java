package com.decorator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingCart {

	private final Map<Item, Integer> itemMap = new HashMap<Item, Integer>();
	
	DecimalFormat df = new DecimalFormat("###.00");

	public void put (Item item, int count){
		if (item.isImported()) item = new ImportTaxDecorator(item);
		if (!item.isExempt()) item = new SalesTaxDecorator(item);
		Integer i = this.itemMap.get(item); 
		if ( i!= null) count += i;
		this.itemMap.put(item, count);
	}
	
	public void remove (Item item) {
		this.itemMap.remove(item);
	}
	
	public void clear () {
		this.itemMap.clear();
	}
	
	public Set<Item> getItems() {
		return itemMap.keySet();
	}
	
	public int getQuantity(Item item){
		return itemMap.get(item);	
	}
	
	public double getTaxtotal() {
		double taxtotal = 0;
		for (Item item : itemMap.keySet()){		
			double subTotal = item.getPrice() * getQuantity(item);
			double subInitTotal = item.getInitPrice() * getQuantity(item);
			taxtotal += subTotal - subInitTotal;
		}
		return taxtotal;
	}

	public double getTotal() {
		double total = 0;
		for (Item item : itemMap.keySet()){		
			double subTotal = item.getPrice() * getQuantity(item);
			total += subTotal;
		}
		return Util.roundPrice(total);
	}

	public void printOrderInput() {
		System.out.println("Order input: ");
		for ( Item item : itemMap.keySet() ){
			System.out.println(itemMap.get(item) + " " + item.getName() + " at " + df.format(item.getInitPrice()));
		}	
		System.out.println();
	}
	
	public void printOrderResults() {	
		double taxtotal = 0;
		double total = 0;
		System.out.println("Order results: ");
		Set<Item> taxedItems = itemMap.keySet();
		for (Item item : taxedItems){		
			double subTotal = item.getPrice() * getQuantity(item);
			double subInitTotal = item.getInitPrice() * getQuantity(item);
			taxtotal += subTotal - subInitTotal;
			total += subTotal;
			System.out.println(getQuantity(item) + " " + item.getName() + ": " + df.format(subTotal));
		}
		total = Util.roundPrice(total);
		System.out.println("Sales Taxes: "+df.format(taxtotal));
		System.out.println("Total: "+df.format(total));
		System.out.println();
	}
	
	public static void main(String[] args)
	{
	    // Check how many arguments were passed in
	    if(args.length == 0)
	    {
	        System.out.println("Proper Usage is: java -jar salestax filename(s)");
	        System.out.println("example: java -jar salestax in1.txt in2.txt");
	        System.exit(0);
	    }
	    for (String fileName: args) Util.getFromFile(fileName);
	}

}
