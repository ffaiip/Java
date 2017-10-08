import java.util.Scanner;

public class restaurantArray {
	static int pizza = 0, chicken = 0, coke = 0;
	static int pricePizza = 0, priceChicken = 0, priceCoke = 0, totalPrice = 0;
	static int cash = 0;
	
	static String[] menu = {"Pizza", "Chicken", "Coke"};
	static int[] quantMenu = {pizza, chicken, coke};
	static int[] priceMenu = {pricePizza, priceChicken, priceCoke};
	static int[] price = {250, 120, 45};
	
	static Scanner sc = new Scanner(System.in);
	
	
	
	static void printWelcome(){
		System.out.println("--------- Welcome to SKE Restaurant ---------");
		System.out.printf("1.) Pizza %20d %2s\n", 250, "Baht.");
		System.out.printf("2.) Chickens %17d %2s\n", 120, "Baht.");
		System.out.printf("3.) Coke %21d %2s\n", 45, "Baht.");
		System.out.println("4.) Total");
		System.out.println("5.) Exit");
	}
	
	static int readInt(String prompt){
		System.out.print(prompt);
		return sc.nextInt();
	}
	
	static void printTable(int totalPrice){
		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
		if(quantMenu[0]>0){
			System.out.printf("| %s %12s %4d %4s %6d %4s\n", menu[0], "|", quantMenu[0], "|", priceMenu[0], "|");
		}
		if(quantMenu[1]>0){
			System.out.printf("| %s %10s %4d %4s %6d %4s\n", menu[1], "|", quantMenu[1], "|", priceMenu[1], "|");
			
		}
		if(quantMenu[2]>0){
			System.out.printf("| %s %13s %4d %4s %6d %4s\n", menu[2], "|", quantMenu[2], "|", priceMenu[2], "|");
		}
		System.out.println("+------------------+---------+-----------+");
		System.out.printf("| Total %22s %6d %4s\n", "|", totalPrice, "|");
		System.out.println("+------------------+---------+-----------+");
	}
	
	static int printChange(int totalPrice,int cash){
		int change = cash-totalPrice;
		if(change>=1000){
			System.out.printf("1000 notes: %.0f\n", Math.ceil(change/1000));
			change = change%1000;
		}
		if(change>=500){
			System.out.printf("500 notes: %.0f\n", Math.ceil(change/500));
			change = change%500;
		}
		if(change>=100){
			System.out.printf("100 notes: %.0f\n", Math.ceil(change/100));
			change = change%100;
		}
		if(change>=50){
			System.out.printf("50 notes: %.0f\n", Math.ceil(change/50));
			change = change%50;
		}
		if(change>=20){
			System.out.printf("20 notes: %.0f\n", Math.ceil(change/20));
			change = change%20;
		}
		if(change>=10){
			System.out.printf("10 coins: %.0f\n", Math.ceil(change/10));
			change = change%10;
		}
		if(change>=5){
			 System.out.printf("5 coins: %.0f\n", Math.ceil(change/5));
			 change = change%5;
		 }
		 if(change>=2){
			 System.out.printf("2 coins: %.0f\n", Math.ceil(change/2));
			 change = change%2;
		 }
		 if(change>=1){
			 System.out.printf("1 coins: %.0f\n", Math.ceil(change/1));
		 }
		 return change;
	}
	

	public static void main(String[] args) {
	
		printWelcome();
		while (true) {
			int choice = readInt("\nEnter you choice : ");
			if (choice == 5) {
				System.out.printf("Total : %d\n",totalPrice);
				System.out.print("Cash : ");
				cash = sc.nextInt();
				System.out.printf("Change : %d\n",cash - totalPrice);
				printChange(totalPrice, cash);
				System.out.print("===== Thank you =====");
				break;
			}
			if (choice == 4) {
				printTable(totalPrice);

			} else {
				int quantity = readInt("Enter Quantity : ");
				switch(choice){
				case 1 :
					quantMenu[0] += quantity;
					priceMenu[0] += price[0]*quantity;
					totalPrice += price[0]*quantity;
					break;
				case 2 :
					quantMenu[1] += quantity;
					priceMenu[1] += price[1]*quantity;
					totalPrice += price[1]*quantity;
					break;
				case 3 :
					quantMenu[2] += quantity;
					priceMenu[2] += price[2]*quantity;
					totalPrice += price[2]*quantity;
					break;
				}
				
			}
		}
	}
}
