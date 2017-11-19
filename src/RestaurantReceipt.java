package code;

/*
@author Kavinthip
*/

public class RestaurantReceipt {
	private String[] receipt;
	private String comment;
	private String restaurantName;

	public RestaurantReceipt(String restaurantName, String[] receipt, String comment) {
		this.restaurantName = restaurantName;
		this.receipt = receipt;
		this.comment = comment;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String[] getReciept() {
		return receipt;
	}

	public String getComment() {
		return comment;
	}
}
