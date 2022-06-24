package main;

public class deliveryFactory {

	public delivery makeDelivery (int newDeliveryType) {
		delivery newDelivery = null;
		if(newDeliveryType == 1) {
			return new Grab();
		} else if (newDeliveryType == 2) {
			return new Gojek();
		} else if (newDeliveryType == 3) {
			return new Shopee();
		} else return null;
	}

}
