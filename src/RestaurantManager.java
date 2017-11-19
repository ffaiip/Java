package code;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
@author Kavinthip
*/

public class RestaurantManager {

	private ArrayList<String> menuLst = new ArrayList<String>();
	private ArrayList<String> priceLst = new ArrayList<String>();

	static ClassLoader loader = RestaurantManager.class.getClassLoader();
	static Scanner sc = new Scanner(System.in);

	public RestaurantManager(String filename) {
		loadFile(filename);
	}

	public ArrayList<String> getMenuList() {
		return menuLst;
	}

	public void setMenuList(ArrayList<String> menuList) {
		this.menuLst = menuList;
	}

	public ArrayList<String> getPriceList() {
		return priceLst;
	}

	public void setPriceList(ArrayList<String> priceList) {
		this.priceLst = priceList;
	}

	public String menuName(String menu) {
		for (int i = 0; i < menu.length(); i++) {
			char Charac = menu.charAt(i);
			if (menu.charAt(0) == ';') {
				break;
			} else if (Charac == ';') {
				String newMenu = menu.substring(0, i);
				return newMenu;
			}
		}
		return null;
	}

	public String menuPrice(String menu) {
		for (int i = 0; i < menu.length(); i++) {
			char Charac = menu.charAt(i);
			if (Charac == ';') {
				if (Charac == menu.charAt(menu.length() - 1)) {
					menuLst.remove(menuLst.size() - 1);
					break;
				} else {
					String price = menu.substring(i + 1, menu.length());
					return price;
				}
			}
		}
		return null;
	}

	public void addMenuAndPrice(String line) {
		String menu = line.trim();
		String newMenu = menuName(menu);
		if (newMenu != null) {
			this.menuLst.add(newMenu);
			String price = menuPrice(menu);
			if (price != null) {
				this.priceLst.add(price);
			}
		}
	}

	public void loadFile(String filename) {
		InputStream in = loader.getResourceAsStream(filename);

		if (in == null) {
			System.out.println("No such file " + filename);
			return;
		}
		Scanner scan = new Scanner(in);
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.startsWith("//")) {
				continue;
			} else {
				addMenuAndPrice(line);
			}
		}
		scan.close();
	}
}
