package main;

public class CustomerPurchaseHistory {
	
	String nameMenu;
	Integer Price;
	String delivery;
	String payment;
	Integer total;
	
	public CustomerPurchaseHistory(String nameMenu, Integer price, String delivery, String payment) {
		super();
		this.nameMenu = nameMenu;
		Price = price;
		this.delivery = delivery;
		this.payment = payment;
		if(delivery.equals("Grab")) {
			total=price+10000;
		}else if(delivery.equals("Gojek")) {
			total=price+9000;
		}else if(delivery.equals("Shopee")) {
			total=price+7500;
		}
		
	}



}
