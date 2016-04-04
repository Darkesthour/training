package by.epamlab.bean;

import by.epamlab.annotation.Equal;
import by.epamlab.annotation.Equal.EqualType;

public class DiscountPurchase extends Purchase {
	
	@Equal(compareBy=EqualType.VALUE)
	private int discount;

	public DiscountPurchase() {}

	public DiscountPurchase(String name, int cost, int number, int discount) {
		super(name, cost, number);
		this.discount = discount;
	}
	
	@Override
	public String fieldsToString() {
		final String DELIMITER = "; ";
		
		return super.fieldsToString() + DELIMITER + discount;
	}
	
	@Override
	public String toString() {
		final String HEAD = "(";
		final String TAIL = ")";
		
		return this.getClass().getSimpleName() + HEAD + fieldsToString() + TAIL;
	}

}
