import java.util.Scanner;

@author Kavinthip

public class restaurantArray {
	static int pizza = 0, chicken = 0, coke = 0;
	static int pricePizza = 0, priceChicken = 0, priceCoke = 0, totalPrice = 0;
	static int cash = 0;
	
	static String[] menu = {"Pizza", "Chicken", "Coke"};
	static int[] quantMenu = {pizza, chicken, coke};
	static int[] priceMenu = {pricePizza, priceChicken, priceCoke};
	static int[] price = {250, 120, 45};
	static String[] menuFunction = { "Total", "Exit"};
	static String[] letter = { "t", "q"};
	
	static Scanner sc = new Scanner(System.in);
	
	static void printMenu(String[] menu, int[] price){
		System.out.println("--------- Welcome to SKE Restaurant ---------");
		for(int i = 0; i<menu.length; i++){
			System.out.printf("%d.) %-10s %d \t%2s\n",i+1 ,menu[i], price[i], "Bath.");
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
	
	static void printTable(int totalPrice){
		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
		
		for (int i = 0; i < quantMenu.length; i++) {
			if (quantMenu[i] > 0) {
				System.out.printf("| %-16s | %4d %4s %6d %4s\n", menu[i], quantMenu[i], "|", priceMenu[i], "|");
			}
		}
		
		System.out.println("+------------------+---------+-----------+");
		System.out.printf("| Total %22s %6d %4s\n", "|", totalPrice, "|");
		System.out.println("+------------------+---------+-----------+");
	}
	
	static int printChange(int totalPrice, int cash) {
		int[] notes = { 1000, 500, 100, 50, 20 };
		int[] coins = { 10, 5, 2, 1 };
		int change = cash - totalPrice;
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

	public static void main(String[] args) {
	
		printMenu(menu, price);
		
		while (true) {
			String choice = readString("\nEnter you choice : ");
			if (choice.equals("q")) {
				System.out.printf("Total : %d\n",totalPrice);
				System.out.print("Cash : ");
				cash = sc.nextInt();
				System.out.printf("Change : %d\n",cash - totalPrice);
				printChange(totalPrice, cash);
				System.out.print("===== Thank you =====");
				break;
			}
			if (choice.equals("t")) {
				printTable(totalPrice);

			} else {
				String quantityStr = readString("Enter Quantity : ");
				int quantity = Integer.parseInt(quantityStr);
				
				switch(choice){
				case "1" :
					quantMenu[0] += quantity;
					priceMenu[0] += price[0]*quantity;
					totalPrice += price[0]*quantity;
					break;
				case "2" :
					quantMenu[1] += quantity;
					priceMenu[1] += price[1]*quantity;
					totalPrice += price[1]*quantity;
					break;
				case "3" :
					quantMenu[2] += quantity;
					priceMenu[2] += price[2]*quantity;
					totalPrice += price[2]*quantity;
					break;
				default :
					System.out.println("invalid");
				}
				
			}
		}
	}

}
