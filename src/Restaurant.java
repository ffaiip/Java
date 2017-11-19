package code;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
@author Kavinthip
*/

public class Restaurant {
	public static RestaurantManager restaurant;

	public static String[] menuArr;
	public static String[] priceStr;
	public static double[] priceDoub;
	public static String current;
	double cash = 0;

	public static String[] rcpt;
	public static ArrayList<Object> allReceipt;

	static String outputFile = "src/data/AllReceipt.txt";
	static Scanner sc = new Scanner(System.in);

	public static String askRestaurantName() {
		Scanner getRestrName = new Scanner(System.in);
		System.out.print("Name of your restaurant : ");
		return getRestrName.nextLine();
	}

	public static String askFile() {
		Scanner getFileName = new Scanner(System.in);
		System.out.print("Your file : ");
		String fileName = getFileName.nextLine();
		return String.format("/data/%s.txt", fileName);
	}

	public static void makeNewRestaurant() {
		String fileLocate = askFile();
		restaurant = new RestaurantManager(fileLocate);

		menuArr = new String[restaurant.getMenuList().size()];
		menuArr = restaurant.getMenuList().toArray(menuArr);

		priceStr = new String[restaurant.getPriceList().size()];
		priceDoub = getDouble(restaurant.getPriceList().toArray(priceStr));
	}
	
	public static double[] getDouble(String[] price) {
		double[] newPrice = new double[price.length];
		for (int i = 0; i < price.length; i++) {
			newPrice[i] = Integer.parseInt(price[i]);
		}
		return newPrice;
	}

	public static String getChoice() {
		Scanner getChoice = new Scanner(System.in);
		System.out.print("Enter your Choice : ");
		return getChoice.nextLine();
	}

	public static int getMenuChoice() {
		System.out.print("Enter the menu number : ");
		int choice = sc.nextInt();
		return choice;
	}

	public static int getQuantity() {
		System.out.print("Enter Quantity : ");
		int quantity = sc.nextInt();
		return quantity;
	}

	public static double getTotal(int[] totalQuantity) {
		double total = 0.0;
		for (int i = 0; i < totalQuantity.length; i++) {
			total += totalQuantity[i] * priceDoub[i];
		}
		return total;
	}

	public static void selectMenu(double total,double[] totalPrice,int[] totalQuantity) {
		int quantity = 0;
		int choice = getMenuChoice();
		if ((choice >= 1) && (choice <= menuArr.length)) {
			quantity = getQuantity();
			System.out.println();
			totalQuantity[choice - 1] += quantity;
			totalPrice[choice - 1] += priceDoub[choice - 1] * quantity;
			total = getTotal(totalQuantity);
		}
	}

	public static double lap(double value, int p) {
		if (p < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, p);
		value = value * factor;
		long val = Math.round(value);
		return (double) val / factor;
	}

	public static void makeReceiptForm(double total, double[] totalPrice, int[] totalQuantity, double cash) {
		int n = 1;
		String space = "|\t";
		rcpt[0] = "\n|======== Menu =========|==== Qty ======|==== Price ====|\n";
		for (int i = 0; i < totalPrice.length; i++) {
			totalPrice[i] = lap(totalPrice[i], 2);
			
			if(menuArr[i].length() <= 6) space = "\t\t\t|\t";
			else if(menuArr[i].length() <= 12) space = "\t\t|\t";
			else if(menuArr[i].length() <= 21) space = "\t|\t";
			
			rcpt[i + 1] = "| " + menuArr[i] + space + totalQuantity[i] + "\t|" + "\t" + totalPrice[i]
					+ "\t|\n";
			n++;
		}
		total = lap(total, 2);
		rcpt[n] = "|=======================================================|\n";
		rcpt[n + 1] = "| Total\t\t\t|" + "\t\t\t" + total + "\t|\n";
		rcpt[n + 2] = "|=======================================================|\n";
		rcpt[n + 3] = "\nCash : \n";
		cash = sc.nextInt();
		rcpt[n + 4] = "Here is your change : " + (cash-total) + printChange(total, cash) + "\n\n";

	}

	public static void printReceipt(int[] totalQuantity) {
		for (int i = 0; i < rcpt.length; i++) {
			if ((i >= 1) && (i <= menuArr.length)) {
				if (totalQuantity[i - 1] != 0) {
					System.out.print(rcpt[i]);
				}
			} else {
				System.out.print(rcpt[i]);
			}
		}
	}
	
	static double printChange(double total, double cash) {
		int[] notes = { 1000, 500, 100, 50, 20 };
		int[] coins = { 10, 5, 2, 1 };
		double change = cash - total;
		for (int i = 0; i < notes.length; i++) {
			if (change >= notes[i]) {
				System.out.printf("%d notes: %.0f\n", notes[i], Math.ceil(change / notes[i]));
				change = change % notes[i];
			}
		}
		for (int f = 0; f < coins.length; f++) {
			if (change >= coins[f]) {
				System.out.printf("%d coins: %.0f\n", coins[f], Math.ceil(change / coins[f]));
				change = change % coins[f];
			}
		}
		return change;
	}

	public static void selectChoice(String choice, double total, double[] totalPrice, int[] totalQuantity, double cash) {
		if (choice.equalsIgnoreCase("O")) {
			selectMenu(total,totalPrice,totalQuantity);
		} else if (choice.equalsIgnoreCase("T")) {
			makeReceiptForm(total, totalPrice, totalQuantity, cash);
			printReceipt(totalQuantity);
		} else if (choice.equalsIgnoreCase("E")) {
			makeReceiptForm(total, totalPrice, totalQuantity, cash);
			printReceipt(totalQuantity);
		} 
	}

	public static void makeOrder(String restrName,double total,double[] totalPrice,int[] totalQuantity) {
		Scanner scanSuggest = new Scanner(System.in);
		String choice = "";

		totalQuantity = new int[priceDoub.length];
		totalPrice = new double[priceDoub.length];
		rcpt = new String[5 + menuArr.length];

		do {
			choice = getChoice();
			selectChoice(choice,total,totalPrice,totalQuantity, total);
			total = getTotal(totalQuantity);
			if ((choice.equalsIgnoreCase("Bill"))) {
				break;
			}
		} while (!choice.equalsIgnoreCase("E"));

		System.out.print("Your suggestion : ");
		String suggestion = scanSuggest.nextLine();
		RestaurantReceipt receipt = new RestaurantReceipt(restrName, rcpt, suggestion);
		allReceipt.add(receipt);
		System.out.println("===== Thank you =====");

	}

	public static void menuSuggestion(String restName) {
		System.out.printf("\n<<+++++++Welcome to %s Restaurant+++++++>>\n", restName);
		System.out.println("[O]rder");
		for (int i = 0, j = 1; i < menuArr.length; i++, j++) {
			System.out.printf("\t%d.) %-25s%.0f\t%3s.\n", j, menuArr[i], priceDoub[i], current);
		}
		System.out.print("[T]otal price\n[E]xit\n");
	}

	public static void saveAllReceiptInText() {
		OutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't open output file " + outputFile);
		}
		printReceipt(out);
	}

	public static void printReceipt(OutputStream out) {
		PrintStream printOut = new PrintStream(out);
		for (int i = 0; i < allReceipt.size(); i++) {
			Object myReceiptObj = allReceipt.get(i);
			String myRestName = ((RestaurantReceipt) myReceiptObj).getRestaurantName();;
			String[] myReceipt = ((RestaurantReceipt) myReceiptObj).getReciept();
			String myComment = ((RestaurantReceipt) myReceiptObj).getComment();
			
			for (int j = 0; j < myReceipt.length; j++) {
				printOut.print(myReceipt[j]);
			}
			printOut.println("Comment : " + myComment);
			printOut.println("=======================================================");
		}
	}

	public static void checkIn(String choiceManage) {
		Scanner scanChoiceManage = new Scanner(System.in);
		while (menuArr.length == 0) {
			System.out.println("This is an empty file please press [R] to create your restaurant again");
			System.out.print("[R]estaurant or [E]xit : ");
			choiceManage = scanChoiceManage.nextLine();
			System.out.println();
			if (choiceManage.equalsIgnoreCase("R")) {
				makeNewRestaurant();
			} else if (choiceManage.equalsIgnoreCase("E"))
				break;
		}
	}

	public static void editMenu() {
		Scanner getMenuName = new Scanner(System.in);
		ArrayList<String> newMenu = restaurant.getMenuList();
		ArrayList<String> newPrice = restaurant.getPriceList();

		System.out.print("[A]dd or [D]elete menu : ");
		String edit = sc.next();
		if (edit.equalsIgnoreCase("A")) {
			System.out.print("Menu name : ");
			String newMenuName = getMenuName.nextLine();
			if (!newMenu.contains(newMenuName)) {
				newMenu.add(newMenuName);
				System.out.print("Menu Price : ");
				String newMenuPrice = sc.next();
				newPrice.add(newMenuPrice);
			} else if (newMenu.contains(newMenuName)) {
				System.out.print("This menu is added already\n");
			}
		} else if (edit.equalsIgnoreCase("D")) {
			for (int i = 0, j = 1; i < menuArr.length; i++, j++) {
				System.out.printf("\t%d.) %-25s%.0f\t%3s.\n", j, menuArr[i], priceDoub[i], current);
			}
			System.out.print("Choose the number of menu to delete : ");
			int menuChoice = sc.nextInt();
			newMenu.remove(menuChoice - 1);
			newPrice.remove(menuChoice - 1);
		}
		restaurant.setMenuList(newMenu);
		menuArr = new String[restaurant.getMenuList().size()];
		menuArr = restaurant.getMenuList().toArray(menuArr);

		restaurant.setPriceList(newPrice);
		priceStr = new String[restaurant.getPriceList().size()];
		priceDoub = getDouble(restaurant.getPriceList().toArray(priceStr));
	}

	public static void main(String[] args) {
		Scanner getCharac = new Scanner(System.in);
		String charac = "" , choiceManage = "";
		double total = 0.0;
		int[] totalQuantity = null;
		double[] totalPrice = null;
		allReceipt = new ArrayList<Object>();
		do {
			makeNewRestaurant();
			if (menuArr.length == 0) {
				checkIn(choiceManage);
			}
			if (choiceManage.equalsIgnoreCase("E"))
				break;

			String restrName = askRestaurantName();
			System.out.print("Please input current: ");
			current = sc.next();

			do {
				menuSuggestion(restrName);
				makeOrder(restrName,total,totalPrice,totalQuantity);
				do {
					System.out.print("\n[N]ew order [R]estaurant [E]dit menu or [Q]uit : ");
					charac = getCharac.nextLine();
					System.out.println();
					if (charac.equalsIgnoreCase("E")) {
						editMenu();
					} else if (charac.equalsIgnoreCase("Q")) {
						saveAllReceiptInText();
						break;
					}
				} while (charac.equalsIgnoreCase("E"));
			} while (charac.equalsIgnoreCase("N"));
		} while (charac.equalsIgnoreCase("R"));

	}

}
