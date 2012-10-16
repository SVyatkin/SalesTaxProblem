package com.decorator;

public class ImportTaxDecorator extends TaxDecorator {

	private Item itemToDecorate;

	final double rate = 0.05;

	public ImportTaxDecorator(Item itemtoDecorate) {
		super(itemtoDecorate);
		this.itemToDecorate = itemtoDecorate;
	}

	@Override
	double getRate() {
		return this.rate;
	}
	
	public boolean isImported() {
		return itemToDecorate.isImported();
	}

	public String getName() {
		return itemToDecorate.getName();
	}

	public double getInitPrice() {
		return itemToDecorate.getInitPrice();
	}


	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		} else if (obj instanceof Item) {
			return (((Item) obj).hashCode() == this.hashCode());

		} else
			return false;
	}

	@Override
	public boolean isExempt() {
		return itemToDecorate.isExempt();
	}
}