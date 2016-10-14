// Assignment #1, Burger Baron
// David Foster & Zeeshan Karim

public class Burger {
	
	private MyStack <String> order;
	private MyStack <String> BaronB;
	private MyStack <String> PlainB;
	
	public Burger(boolean theWorks){
		
		order = new MyStack<String>();
		BaronB = new MyStack<String>();
		PlainB = new MyStack<String>();
		
		// Init reference baron burger
		BaronB.push("Bottom Bun");
		BaronB.push("Ketchup");
		BaronB.push("Mustard");
		BaronB.push("Mushrooms");
		BaronB.push("Beef");
		BaronB.push("Cheddar");
		BaronB.push("Mozzarella");
		BaronB.push("Pepperjack");
		BaronB.push("Onions");
		BaronB.push("Tomato");
		BaronB.push("Lettuce");
		BaronB.push("Baron-Sauce");
		BaronB.push("Mayonnaise");
		BaronB.push("Top Bun");
		BaronB.push("Pickle");

		// Init reference plain burger
		PlainB.push("Bottom Bun");
		PlainB.push("Beef");
		PlainB.push("Top Bun");
		
		// Set order type to baron or plain burger
		if(theWorks){
			order.push("Bottom Bun");
			order.push("Ketchup");
			order.push("Mustard");
			order.push("Mushrooms");
			order.push("Beef");
			order.push("Cheddar");
			order.push("Mozzarella");
			order.push("Pepperjack");
			order.push("Onions");
			order.push("Tomato");
			order.push("Lettuce");
			order.push("Baron-Sauce");
			order.push("Mayonnaise");
			order.push("Top Bun");
			order.push("Pickle");		
		} else {
			order.push("Bottom Bun");
			order.push("Beef");
			order.push("Top Bun");		
		}
	}
	
	public void changePatties(String pattyType){
		
		MyStack <String> temp = new MyStack<String>();
		String buffer;
		
		while (order.size() != 0) {
			buffer = order.pop();
			if (buffer.compareTo("Beef") == 0) {
				temp.push(pattyType);
			} else {
				temp.push(buffer);
			}
		}
		
		while (temp.size() != 0) {
			order.push(temp.pop());
		}			
	}

	public void addPatty() {
		MyStack <String> temp = new MyStack<String>();
		while (order.size() != 0) {
			String peeker = order.peek();
			if(peeker.compareTo("Pepperjack") == 0 ||
					peeker.compareTo("Mozzarella") == 0 ||
					peeker.compareTo("Cheddar") == 0 ||
					peeker.compareTo("Beef") == 0) {
				temp.push("Beef");
				break;
			}
			temp.push(order.pop());
		}
		while (temp.size() != 0) {
			order.push(temp.pop());
		}	
	}
	
	// Done and working!!! <3 zee
	// This can definitely be optimized, but im too lazy
	public void addCategory(String type) {

		MyStack<String> ordertemp = new MyStack<String>();
		MyStack<String> reftemp = new MyStack<String>();
		
		String orderbuff = null;
		String refbuff = null;
		
		if(type.compareTo("Veggies") == 0){
			while(order.size() != 0){
				orderbuff = order.pop();
				do{
					refbuff = BaronB.pop();
					if(orderbuff.compareTo(refbuff) != 0)
						if(refbuff.compareTo("Lettuce") == 0
						|| refbuff.compareTo("Tomato") == 0
						|| refbuff.compareTo("Pickle") == 0
						|| refbuff.compareTo("Onions") == 0
						|| refbuff.compareTo("Mushrooms") == 0)
							ordertemp.push(refbuff);
					reftemp.push(refbuff);
				} while(orderbuff.compareTo(refbuff) != 0);
				ordertemp.push(orderbuff);
			}
			
			while(ordertemp.size() != 0)
				order.push(ordertemp.pop());
			
			while(reftemp.size() != 0)
				BaronB.push(reftemp.pop());
		}
		
		if(type.compareTo("Cheese") == 0){
			while(order.size() != 0){
				orderbuff = order.pop();
				do{
					refbuff = BaronB.pop();
					if(orderbuff.compareTo(refbuff) != 0)
						if(refbuff.compareTo("Cheddar") == 0
						|| refbuff.compareTo("Mozzarella") == 0
						|| refbuff.compareTo("Pepperjack") == 0)
							ordertemp.push(refbuff);
					reftemp.push(refbuff);
				} while(orderbuff.compareTo(refbuff) != 0);
				ordertemp.push(orderbuff);
			}
			
			while(ordertemp.size() != 0)
				order.push(ordertemp.pop());
			
			while(reftemp.size() != 0)
				BaronB.push(reftemp.pop());
		}
		
		//i hate you it said sauces not sauce!!!! literally an hour
		if(type.compareTo("Sauce") == 0){
			while(order.size() != 0){
				orderbuff = order.pop();
				do{
					refbuff = BaronB.pop();
					if(orderbuff.compareTo(refbuff) != 0)
						if(refbuff.compareTo("Ketchup") == 0
						|| refbuff.compareTo("Mustard") == 0
						|| refbuff.compareTo("Mayonnaise") == 0
						|| refbuff.compareTo("Baron-Sauce") == 0)
							ordertemp.push(refbuff);
					reftemp.push(refbuff);
				} while(orderbuff.compareTo(refbuff) != 0);
				ordertemp.push(orderbuff);
			}
			
			while(ordertemp.size() != 0)
				order.push(ordertemp.pop());
			
			while(reftemp.size() != 0)
				BaronB.push(reftemp.pop());
		}
	}
	
	// Done!
	public void removeCategory(String type) {

		if(type.compareTo("Veggies") == 0){
			removeIngredient("Lettuce");
			removeIngredient("Tomato");
			removeIngredient("Onions");
			removeIngredient("Pickle");
			removeIngredient("Mushrooms");
		}
		
		if(type.compareTo("Cheese") == 0){
			removeIngredient("Cheddar");
			removeIngredient("Mozzarella");
			removeIngredient("Pepperjack");
		}
		
		if(type.compareTo("Sauce") == 0){
			removeIngredient("Ketchup");
			removeIngredient("Mustard");
			removeIngredient("Mayonnaise");
			removeIngredient("Baron-Sauce");
		}
	}
	
	public void addIngredient(String type) {
		
		MyStack<String> ordertemp = new MyStack<String>();
		MyStack<String> reftemp = new MyStack<String>();
		
		String orderbuff = null;
		String refbuff = null;
		
		while(order.size() != 0){
			orderbuff = order.pop();
			do{
				refbuff = BaronB.pop();
				if(orderbuff.compareTo(refbuff) != 0)
					if(refbuff.compareTo(type) == 0)
						ordertemp.push(refbuff);
				reftemp.push(refbuff);
			} while(orderbuff.compareTo(refbuff) != 0);
			ordertemp.push(orderbuff);
	}
		while(ordertemp.size() != 0)
			order.push(ordertemp.pop());
		
		while(reftemp.size() != 0)
			BaronB.push(reftemp.pop());
}

	public void removeIngredient(String type) {
		MyStack <String> temp = new MyStack<String>();
		String buffer;
		
		while(order.size() != 0){
			buffer = order.pop();
			if(buffer.compareTo(type) != 0)
				temp.push(buffer);
		}
		while(temp.size() != 0)
			order.push(temp.pop());
	}
		
	public String toString(){
		return order.toString();
	}
}
