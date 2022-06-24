package main;

import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;
import connectSQL.SQL;


public class Main {
	Scanner scanInt = new Scanner(System.in);
	Scanner scanStr = new Scanner(System.in);
	SQL sql = new SQL();
	Vector<Menu> menus = sql.view();
	Vector<CustomerPurchaseHistory> history = new Vector<>();
	MerchantMenuManager merchant = null;
	/*
	 * 
	 * design pattern factory
	 * factory ini digunakan untuk khusus membuat object object yaitu delivery yang ditentukan langsung oleh user
	 * pada program ini yang kami buat adalah class deliveryFactory
	 * dimana, ketika memanggil method pada class ini, akan membuat object baru sesuai dengan parameter yang diberikan
	 * 
	 * design pattern facade
	 * dengan adanya pattern facade ini, programmer lebih mudah untuk menyediakan interface yang telah disederhanakan
	 * kepada user untuk mengakses sistem dan dapat memudahkan programmer untuk melakukan update kedepannya.
	 * pada program ini yang kami buat adalah class PaymentKeeper
	 * dimana, ketika memanggil method pada class ini, akan menjadi perantara dengan class payment. sehingga tidak 
	 * secara langsung mengakses class payment tersebut.
	 */
	
	RegistrationAccountService acc = null;
	
	public Main() {
		
		int chooseLogin;
		int choose;
		do {
		loginMenu();
		chooseLogin = scanInt.nextInt();
		if(chooseLogin==1) {
			validateRegister();
			do {
				menuCustomerDisplay();
				choose = scanInt.nextInt();
				
				switch (choose) {
				case 1:
					makeOrder();
					break;
				case 2:
					acc.accountedit();
					break;
				case 3:
					history();
					break;
				}
			}while(choose!=4);
		} else if(chooseLogin==2) {
			merchant = new MerchantMenuManager();
		}
			else if(chooseLogin==3) {
			System.exit(0);
			}
		}while(chooseLogin!=3);
	}
	
	
	private void makeOrder() {
		String nameMenu;
		Integer priceMenu;
		ViewMenu();
		int inputOrderOption = 0;
		System.out.print("Select menu you want to order : ");
		inputOrderOption = scanInt.nextInt();
		nameMenu = menus.get(inputOrderOption-1).getMealName();
		priceMenu = menus.get(inputOrderOption-1).getPrice();
		String delivery = deliveryService();
		String payment = paymentService();
		history.add(new CustomerPurchaseHistory(nameMenu, priceMenu, delivery, payment));
	}
	
	
	public void ViewMenu() {
		if(menus.isEmpty()) {
			System.out.println("No Menu");
		}else {
			System.out.println("List of Menu");
			
			for(int i=0;i<83;i++)System.out.print("=");
            System.out.println();
            int j = 1;
				for(Menu m : menus) {
					System.out.printf("| %2s.",j);
	                j++;
					 System.out.println(String.format("| %-5s | %-20s | %-10d | %-30s |" , m.getMealID(),
							 m.getMealName(), m.getPrice(), m.getDescription()));
			
		}
				for(int i=0;i<83;i++)System.out.print("=");
	            System.out.println();
		
	}
}	
	
	private String deliveryService() {
		deliveryFactory deliveries = new deliveryFactory();
		delivery theDelivery = null;
		
		int inputServiceOption = 0;
		System.out.println("Delivery Service Option ");
		System.out.println("1. Grab");
		System.out.println("2. Gojek");
		System.out.println("3. Shopee");
		System.out.print("Select service (1-3) >> ");
		inputServiceOption = scanInt.nextInt();
		
		if(inputServiceOption!=0) {
			int typeOfDelivery = inputServiceOption;
			theDelivery = deliveries.makeDelivery(typeOfDelivery);
			theDelivery.displayDelivery();
			scanStr.nextLine();
		}
		
		return theDelivery.getDeliveryName();
	}
	
	
	private String paymentService() {
		PaymentKeeper pk = new PaymentKeeper();
		
		int inputServiceOption = 0;
		do {
			System.out.println("Payment Method Option ");
			System.out.println("1. ShopeePay");
			System.out.println("2. Gopay");
			System.out.print("Select Method (1-2) >> ");
			inputServiceOption = scanInt.nextInt();
		}while(inputServiceOption < 1 || inputServiceOption > 2);
		if(inputServiceOption==1) {
			 pk.ShopeePayOrder();
			 return pk.getShopeePay();
		}else {
			 pk.GoPayOrder();
			 return pk.getGoPay();
		}
		
	}

	
	private void history() {
		if(history.isEmpty()) {
			System.out.println("no History!");
		}
		else {		
			System.out.println("History Order");
			 for(CustomerPurchaseHistory hst : history) {
		            System.out.println("All Transaction List");
		            for(int i=0;i<90;i++)System.out.print("=");
		            System.out.println();
		            System.out.println(String.format("| %-20s | %-16d | %-20s | %-10s | %-10d |",
		                   hst.nameMenu,  hst.Price,hst.delivery, hst.payment , hst.total));
		        }
			
		}
	}

	public void validateRegister() {
		String userName;
		String email;
		String phoneNumber;
		String address;
		do {
			System.out.println("Input userName[4-32 character]: ");
			userName = scanStr.nextLine();
		} while (userName.length()<4||userName.length()>32);
		do {
			System.out.println("Input email: ");
			email = scanStr.nextLine();
		} while (validateEmail(email));
		do {
			System.out.println("Input phoneNumber[numberOnly]: ");
			phoneNumber = scanStr.nextLine();
		} while (validateNumber(phoneNumber));
		do {
			System.out.println("Input Address[4 - 50 character]: ");
			address = scanStr.nextLine();
		} while (address.length()<4||address.length()>50);
		
		acc = new RegistrationAccountService(userName, email, phoneNumber, address);
	}

	public void loginMenu() {
		System.out.println("Online Food Service");
		System.out.println("1. Login as Customer");
		System.out.println("2. Login as Merchant");
		System.out.println("3. Exit");
	}
	
	public boolean validateNumber (String phoneNumber) {
		String numberRegex = "[0-9]+";
		if(!phoneNumber.matches(numberRegex)) {
			return true;
		}else {
			return false;
		}
		
	}

	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
	
	public void menuCustomerDisplay() {
		System.out.println("1. Order Menu");
		System.out.println("2. Account");
		System.out.println("3. History");
		System.out.println("4. Exit");
	}

	public void menuMerchantDisplay() {
		System.out.println("1. Merchant Menu");
		System.out.println("2. Account");
		System.out.println("3. Exit");
	}
	
	public boolean validateEmail (String email) {
		String emailRegex =  "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		if(pattern.matcher(email).matches()) {
			return false;
		}else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		new Main();

	}

}
