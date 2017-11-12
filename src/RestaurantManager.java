package code;

import java.io.InputStream;
import java.util.Scanner;

@author Kavinthip

public class RestaurantManager {

	static final String MENU_FILE = "code/menu.txt";

	public static String readFile() {
		ClassLoader loader = RestaurantManager.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(MENU_FILE);

		Scanner sc = new Scanner(in);

		String menu = "";
		while (sc.hasNextLine()) {
			menu += sc.nextLine() + "\n";
		}
		return menu;
	}
}
