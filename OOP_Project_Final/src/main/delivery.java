package main;

public abstract class delivery {

	private String name;
	private Integer cost;
	
	public String getDeliveryName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public Integer getCost() {
		return cost;
	}
	
	public void setCost(Integer newCost) {
		cost = newCost;
	}
	
	public void displayDelivery() {
		System.out.println("Order will use " + getDeliveryName() + ", cost "+ getCost());
	}
}
