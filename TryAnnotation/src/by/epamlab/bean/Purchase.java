package by.epamlab.bean;

import by.epamlab.annotation.Equal;
import by.epamlab.annotation.Equal.EqualType;

public class Purchase {
	
	@Equal(compareBy=EqualType.REFERENCE)
	private String name;
	
	@Equal(compareBy=EqualType.VALUE)
	private int cost;
	
	private int number;

	public Purchase() {}

	public Purchase(String name, int cost, int number) {
		super();
		this.name = name;
		this.cost = cost;
		this.number = number;
	}
	
	protected String fieldsToString() {
		final String DELIMITER = "; ";
		
		return name + DELIMITER + cost + DELIMITER + number;
	}
	
	@Override
	public String toString() {
		final String HEAD = "(";
		final String TAIL = ")";
		
		return this.getClass().getSimpleName() + HEAD + fieldsToString() + TAIL;
	}

}
