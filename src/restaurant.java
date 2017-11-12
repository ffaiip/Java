package code;

import java.util.ArrayList;
import java.util.Scanner;

public class restaurant {
	
	static RestaurantManager readM = new RestaurantManager();
    static Scanner sc = new Scanner(System.in);
    static Food[] Menu =  new Food[readMenu(readM.readFile()).size()];
    
	static double total = 0, cash = 0;
	
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
	
	static void printTable() {
		System.out.println("|====== Menu ======|== Qty ==|== Price ==|");

		for (int i = 0; i < Menu.length; i++) {
			if (Menu[i].getQuantity()*Menu[i].getPrice() > 0) {
				System.out.printf("| %-16s | %4d %4s %6d %4s\n", Menu[i].getName(), Menu[i].getQuantity(), "|", Menu[i].getQuantity()*Menu[i].getPrice(), "|");
			}
		}

		System.out.println("|==================|=========|===========|");
		System.out.printf("| Total %22s %6d %4s\n", "|", total(), "|");
		System.out.println("|==================|=========|===========|");
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
	
	public static double total(){
		total = 0;
		for(int i =0;i<Menu.length;i++){
			total = total+Menu[i].getQuantity()*Menu[i].getPrice();
		}
		return total;
	}
	

	public static void main(String[] args) {
	    
		Menu = readMenu(readM.readFile()).toArray(Menu);
		printMenu();
		
		while (true) {
			String choiceStr = readString("\nEnter you choice : ");
			int choice = Integer.parseInt(choiceStr);
			total = total();
			if (choiceStr.equals("q")) {
				System.out.printf("Total : %d\n", total);
				System.out.print("Cash : ");
				cash = sc.nextDouble();
				System.out.printf("Change : %d\n", cash - total);
				printChange(total, cash);
				System.out.print("===== Thank you =====");
				break;
			}
			if (choiceStr.equals("t")) {
				printTable();

			} else {
				String quantityStr = readString("Enter Quantity : ");
				int quantity = Integer.parseInt(quantityStr);
				Menu[choice - 1].addQuantity(quantity);

			}

		}
	}

}
