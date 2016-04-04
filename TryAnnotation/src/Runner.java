import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import by.epamlab.bean.*;
import by.epamlab.util.Analyzer;

public class Runner {

	public static void main(String[] args) {
		
		Purchase[] purchases = {
			new Purchase("bear", 17500, 5),
			new Purchase("bear", 17500, 3),
			new Purchase("milk", 3450, 1),
			new Purchase("tomato", 23500, 1),
			new DiscountPurchase("nuts", 28900, 1, 500),
			new DiscountPurchase("apple", 12450, 2, 0),
			new DiscountPurchase("apple", 12450, 10, 0),
			new Purchase("tomato", 23500, 5)
		};
		Analyzer analyzer = new Analyzer();
		
		try {
			Method m = analyzer.getClass().getDeclaredMethod("compareObjects", Object.class, Object.class);
			m.setAccessible(true);
			
			for(int i = 0, numPurchases = purchases.length; i < numPurchases; i++) {
				for(int j = i + 1; j < numPurchases; j++) {
					m.invoke(analyzer, purchases[i], purchases[j]);
				}
			}
		}
		catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println(e);
		}
	}

}
