/*
   Brendon Baughn
   Version: 2.3
   
   For this to work, your input must be the entire name of the product,
   capitalization does not matter
*/

import java.util.Scanner;
public class VendingMachine {

	public static void list() {
		System.out.println("Name              Quantity Price");
		System.out.println("----------------- -------- -----");
	}
	public static void products(String[] products, String[] quant, String[] prices) {
		int i;
		for (i = 0; i < products.length; ++i) {
			System.out.printf("%-23s %2s %5s\n", products[i], quant[i], prices[i]);
		}
	}
	public static String userChoice(Scanner in, String msg) {
		System.out.println();
		System.out.print(msg);
		String choice = in.nextLine();
		String answer = choice.toUpperCase().substring(0, 1);
		if (choice.isEmpty() || !(answer.equals("Y") || answer.equals("N"))) {
			System.out.println("ERROR! You MUST enter a [Y]es or [N]o!");
			System.out.print(msg);
			choice = in.nextLine();
			answer = choice.toUpperCase().substring(0, 1);
		}
		return answer;
	}
	public static String[] selection(Scanner in, String msg, String[] products, String[] price, String[] quantity) {
		System.out.print(msg);
		String input = in.nextLine();
		if (input.isEmpty()) {
			System.out.println("ERROR! You must enter a selection!");
			System.out.print(msg);
			input = in.nextLine();
		}

		for (int i = 0; i < products.length; ++i) {
			int quant = Integer.parseInt(quantity[i]);
			if (products[i].toLowerCase().equals(input.toLowerCase())) {
				System.out.println("That will be $" + price[i]);
				System.out.print("Do you accept to the price? [Y/N]: ");
				String acceptance = in.nextLine().toUpperCase().substring(0, 1);
				if (!(acceptance.equals("Y") || acceptance.equals("N"))) {
					System.out.println("ERROR! You did not input a [Y]es or [N]o!");
					System.out.print("Do you accept to the price? [Y/N]: ");
					acceptance = in.nextLine().toUpperCase().substring(0, 1);
				} else if (quant <= 0) {
					System.out.println("Sorry, this is out of order!");
					System.out.print("Please make a new selection: ");
					input = in.nextLine();
					if (input.isEmpty()) {
						System.out.println("ERROR! You must enter a selection!");
						System.out.print(msg);
						input = in.nextLine();
					}
					i = 0;
				} else if (acceptance.equals("Y")) {
					System.out.println("Thank you for your purchase!");
					quant = Integer.parseInt(quantity[i]);
					quant = quant - 1;
					quantity[i] = Integer.toString(quant);
				} else {
					System.out.println();
					System.out.println("Come back again!");
				}
			} else if (!(products[i].toLowerCase().equals(input.toLowerCase())) && i == products.length - 1) {
				System.out.println();
				System.out.println("You did not choose an offered product");
				System.out.println("Here is the menu:");
			}
		}
		return quantity;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		list();
		String[] myProducts = {"Pepsi", "Diet Pepsi", "Mountain Dew", "Fanta", "Fanta Zero"};
		String[] quantities = {"4", "5", "7", "10", "2"};
		String[] prices = {"1.5", "2", "1", "3", "2.5"};
		products(myProducts, quantities, prices);

		String msg = "Would you like to make a choice? [Y/N]: ";
		while (userChoice(in, msg).equals("Y")) {
			String msg2 = "Make your selection: ";
			String[] input2 = selection(in, msg2, myProducts, prices, quantities);
			quantities = input2;
			System.out.println();
			list();
			products(myProducts, quantities, prices);
			msg = "Would you like to make another choice? [Y/N]: ";
		}
		System.out.println();
		System.out.println("Goodbye!");
		in.close();
	}

}
