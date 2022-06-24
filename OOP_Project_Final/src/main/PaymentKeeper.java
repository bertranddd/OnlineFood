package main;

public class PaymentKeeper {

	private payment GoPay;
	private payment ShopeePay;
	
	public PaymentKeeper() {
		GoPay = new Gopay();
		ShopeePay = new ShopeePay();
	}
	public String getShopeePay() {
		return ShopeePay.getName();
	}
	
	public String getGoPay() {
		return GoPay.getName();
	}
	
	public void ShopeePayOrder(){
		ShopeePay.displayPayment();
	}
	
	public void GoPayOrder() {
		GoPay.displayPayment();
	}
	

}
