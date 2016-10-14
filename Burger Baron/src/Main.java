// Assignment #1, Burger Baron
// David Foster & Zeeshan Karim

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	static int ordernumber = 0;

	public static void main(String[] args) throws FileNotFoundException {

		File order = new File("src/customer.txt");
		if (order != null) {
			FileReader fr = new FileReader(order);
			BufferedReader br = new BufferedReader(fr);
			try {
				while (br.ready()) {
					String orderline = br.readLine();
					parseLine(orderline);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//testMyStack();
		//testBurger();
	}

	private static void parseLine(String line) {
		int i;
		boolean baron = false;
		boolean changepatty = false;
		int pattynumber = 1;
		String pattytype = "Beef";
		Burger theBurger;
		String[] oStrings = line.split(" ");

		//fine
		for (i = 0; i < oStrings.length - 1; i++) {
			if (oStrings[i].equalsIgnoreCase("Baron")) {
				baron = true;
			} else if (oStrings[i].equalsIgnoreCase("Chicken")) {
				changepatty = true;
				pattytype = "Chicken";
			} else if (oStrings[i].equalsIgnoreCase("Veggie")) {
				changepatty = true;
				pattytype = "Veggie";
			} else if (oStrings[i].equalsIgnoreCase("Double")) {

				pattynumber = 2;
			} else if (oStrings[i].equalsIgnoreCase("Triple")) {

				pattynumber = 3;
			}
		}

		theBurger = new Burger(baron);
		i = 0;
		while (i < oStrings.length) {
			if (baron) {
				if (oStrings[i].equalsIgnoreCase("no")) {
					i++;
					while (i < oStrings.length) {
						if (oStrings[i].equalsIgnoreCase("but")) {
							i++;
							while (i < oStrings.length) {
								theBurger.addIngredient(oStrings[i]);
								i++;
							}
							break;
						}
						if (oStrings[i].equalsIgnoreCase("Cheese")
								|| oStrings[i].equalsIgnoreCase("Sauce")
								|| oStrings[i].equalsIgnoreCase("Veggies")) {
							theBurger.removeCategory(oStrings[i]);
						} else {
							theBurger.removeIngredient(oStrings[i]);
						}
						i++;
					}				
				} else if (oStrings[i].equalsIgnoreCase("but")) {
					i++;
					while (i < oStrings.length) {
						theBurger.addIngredient(oStrings[i]);
						i++;
					}
				}
			} else {
				if (oStrings[i].equalsIgnoreCase("with")) {
					while (i < oStrings.length) {
						if (oStrings[i].equalsIgnoreCase("but")) {
							i++;
							if (oStrings[i].equalsIgnoreCase("no")) i++;
							while (i < oStrings.length) {
								theBurger.removeIngredient(oStrings[i]);
								i++;
							}
							break;
						}
						if (oStrings[i].equalsIgnoreCase("Cheese")
								|| oStrings[i].equalsIgnoreCase("Sauce")
								|| oStrings[i].equalsIgnoreCase("Veggies")) {
							theBurger.addCategory(oStrings[i]);
						} else {
							theBurger.addIngredient(oStrings[i]);
						}
						i++;
					}
				} else if (oStrings[i].equalsIgnoreCase("but")) {
					i++;
					if (oStrings[i].equalsIgnoreCase("no")) i++;
					while (i < oStrings.length) {
						theBurger.removeIngredient(oStrings[i]);
						i++;
					}
				}
			}
			i++;
		}
		
		if (pattynumber == 2) {
			theBurger.addPatty();
		} else if (pattynumber == 3) {
			theBurger.addPatty();
			theBurger.addPatty();
		}
		
		if (changepatty) {
			theBurger.changePatties(pattytype);
		}
		
		
		System.out.println("Order #" +ordernumber+ "  "+line);
		System.out.println(theBurger.toString());
		ordernumber++;
	}

	private static void testMyStack() {
		MyStack <String> test = new MyStack<String>();
		test.push("WITCH!");
		test.push("A");
		test.push("SHE'S");
		System.out.println(test.toString());
		System.out.println("Size:  " + test.size());
		System.out.println("Peek:  " + test.peek());
		if(!test.isEmpty())
			System.out.println("NOT EMPTY");
		System.out.println(test.pop());
		System.out.println(test.pop());
		System.out.println(test.pop());
		if(test.isEmpty())
			System.out.println("EMPTY");
	}

	private static void testBurger() {
		Burger testBurger = new Burger(true);
		testBurger.toString();
		testBurger.removeCategory("Sauce");
		testBurger.removeIngredient("Lettuce");
		testBurger.addPatty();
		testBurger.changePatties("Chicken");
		testBurger.toString();
		System.out.println(testBurger.toString());
		testBurger = new Burger(false);
		testBurger.addCategory("Sauce");
		testBurger.addIngredient("Tomato");
		testBurger.removeIngredient("Ketchup");
		testBurger.addPatty();
		testBurger.addPatty();
		testBurger.changePatties("Veggie");
		System.out.println(testBurger.toString());	
	}

}
