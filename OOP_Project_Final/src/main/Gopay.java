package main;

public class Gopay implements payment{
	
	private String name = "Gopay" ;
	@Override
	public void displayPayment() {
		System.out.println("This order will be paid using GoPay");
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}


}
