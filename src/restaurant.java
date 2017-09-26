import java.util.Scanner;

public class restaurant {
	static int pizza = 0, chicken = 0, coke = 0;
	static int pricePizza = 0, priceChicken = 0, priceCoke = 0, totalPrice = 0;
	static int cash = 0;
	
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
	
	static void printTable(int pizza, int pricePizza, int chicken, int priceChicken, int coke, int priceCoke, int totalPrice){
		System.out.println("+------ Menu ------+-- Qty --+-- Price --+");
		if(pizza>0){
			System.out.printf("| Pizza %12s %4d %4s %6d %4s\n", "|", pizza, "|", pricePizza, "|");
		}
		if(chicken>0){
			System.out.printf("| Chicken %10s %4d %4s %6d %4s\n", "|", chicken, "|", priceChicken, "|");
			
		}
		if(coke>0){
			System.out.printf("| Coke %13s %4d %4s %6d %4s\n", "|", coke, "|", priceCoke, "|");
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
				printTable(pizza, pricePizza, chicken, priceChicken, coke, priceCoke, totalPrice);

			} else {
				int quantity = readInt("Enter Quantity : ");
				switch(choice){
				case 1 :
					pizza += quantity;
					pricePizza += 250*quantity;
					totalPrice += 250*quantity;
					break;
				case 2 :
					chicken += quantity;
					priceChicken += 120*quantity;
					totalPrice += 120*quantity;
					break;
				case 3 :
					coke += quantity;
					priceCoke += 45*quantity;
					totalPrice += 45*quantity;
					break;
				}
				
			}
		}
	}

}
