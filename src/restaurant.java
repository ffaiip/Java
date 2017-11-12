package code;

import java.util.ArrayList;
import java.util.Scanner;

@author Kavinthip

public class restaurant {
	
	static RestaurantManager readM = new RestaurantManager();
    static Scanner sc = new Scanner(System.in);
    static Food[] Menu =  new Food[readMenu(readM.readFile()).size()];
    
	static int pizza, chicken, coke;
	static double pricePizza, priceChicken, priceCoke, totalPrice = 0;
	static int cash = 0;
	
	static String[] menu = {"Pizza", "Chicken", "Coke"};
	static int[] quantMenu = {pizza, chicken, coke};
	static double[] priceMenu = {pricePizza, priceChicken, priceCoke};
	static double[] price = {250, 120, 45};
	static String[] menuFunction = { "Total", "Exit"};
	static String[] letter = { "t", "q"};
	
	public static ArrayList<Food> readMenu(String menu){
		Scanner sc = new Scanner(menu);
		String nameFood = " ";
		int priceFood = 0;
		ArrayList<Food> Menu = new ArrayList<Food>();
		
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			nameFood = line.split(";")[0];
			priceFood = Integer.parseInt(line.split(";")[1].trim());
			Menu.add(new Food(nameFood,priceFood));
		}
		return Menu;
	}
	
	static void printMenu() {
		System.out.println("-<-<-<-<- Welcome to SKE Restaurant ->->->->-");
		for (int i = 0; i < Menu.length; i++) {
			System.out.printf("%d.) %-10s %d \t%2s\n", i + 1, Menu[i].getName(), Menu[i].getPrice(), "Bath.");
		}
		System.out.println("\n[t] Total");
		System.out.println("[q] Exit");
	}
	
	static String readString(String prompt){
		System.out.print(prompt);
		return sc.next();
	}
	
	static int readInt(String prompt){
		System.out.print(prompt);
		return sc.nextInt();
	}
	
	static void printTable(double totalPrice2) {
		System.out.println("|====== Menu ======|== Qty ==|== Price ==|");

		for (int i = 0; i < quantMenu.length; i++) {
			if (quantMenu[i] > 0) {
				System.out.printf("| %-16s | %4d %4s %6d %4s\n", menu[i], quantMenu[i], "|", priceMenu[i], "|");
			}
		}

		System.out.println("|==================|=========|===========|");
		System.out.printf("| Total %22s %6d %4s\n", "|", totalPrice2, "|");
		System.out.println("|==================|=========|===========|");
	}
	
	static double printChange(double totalPrice2, int cash) {
		int[] notes = { 1000, 500, 100, 50, 20 };
		int[] coins = { 10, 5, 2, 1 };
		double change = cash - totalPrice2;
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
	
	public static double total(){
		double total = 0;
		for(int i =0;i<Menu.length;i++){
			total = total+Menu[i].getQuantity()*Menu[i].getPrice();
		}
		return total;
	}
	
	public static void printTotal(){
		for(int i =0;i<Menu.length;i++){
			if (Menu[i].getQuantity()*Menu[i].getPrice() > 0) {
				System.out.printf("| %-14s   |   %5d |  %5d    |\n",Menu[i].getName(),Menu[i].getQuantity(),Menu[i].getQuantity()*Menu[i].getPrice());
			}
		}
		
	}

	public static void main(String[] args) {
	
		printMenu();
		
		while (true) {
			String choice = readString("\nEnter you choice : ");
			if (choice.equals("q")) {
				System.out.printf("Total : %d\n", totalPrice);
				System.out.print("Cash : ");
				cash = sc.nextInt();
				System.out.printf("Change : %d\n", cash - totalPrice);
				printChange(totalPrice, cash);
				System.out.print("===== Thank you =====");
				break;
			}
			if (choice.equals("t")) {
				
				printTable(totalPrice);

			} else {
				String quantityStr = readString("Enter Quantity : ");
				int quantity = Integer.parseInt(quantityStr);

				switch (choice) {
				case "1":
					quantMenu[0] += quantity;
					priceMenu[0] += price[0] * quantity;
					totalPrice += price[0] * quantity;
					break;
				case "2":
					quantMenu[1] += quantity;
					priceMenu[1] += price[1] * quantity;
					totalPrice += price[1] * quantity;
					break;
				case "3":
					quantMenu[2] += quantity;
					priceMenu[2] += price[2] * quantity;
					totalPrice += price[2] * quantity;
					break;
				default:
					System.out.println("invalid");
				}

			}
		}
	}

}
