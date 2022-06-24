package main;

public class ShopeePay implements payment{

	private String name = "ShopeePay" ;
	
	@Override
	public void displayPayment() {
		System.out.println("This order will be paid using ShopeePay");
		
	}

	@Override
	public String getName() {
		return name;
	}


}
