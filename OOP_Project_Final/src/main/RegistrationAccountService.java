package main;

import java.util.Scanner;

public class RegistrationAccountService {
	
	private String userName;
	private String email;
	private String phoneNumber;
	private String Address;
	
	public RegistrationAccountService(String userName, String email, String phoneNumber, String address) {
		super();
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		Address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	public void display(){
		System.out.println("Name : " + getUserName());
		System.out.println("Email : " + getEmail());
		System.out.println("Phone Number : " + getPhoneNumber());
		System.out.println("Address : " + getAddress());
	}
	
	public void accountedit() {
		Scanner scanStr = new Scanner(System.in);
		Scanner scanInt = new Scanner(System.in);
		
		int chooseEdit;
		String userName;
		String phoneNumber;
		String address;
		do {
			display();
			System.out.println("1. Edit Name");
			System.out.println("2. Edit phoneNumber");
			System.out.println("3. Edit Address");
			System.out.println("4. Exit");
			chooseEdit = scanInt.nextInt();
			if(chooseEdit==1) {
				do {
					System.out.println("Input userName[4-32 character]: ");
					userName = scanStr.nextLine();
				} while (userName.length()<4||userName.length()>32);
				setUserName(userName);
			}else if(chooseEdit==2) {
				do {
					System.out.println("Input phoneNumber[numberOnly]: ");
					phoneNumber = scanStr.nextLine();
				} while (validateNumber(phoneNumber));
			}else if(chooseEdit==3) {
				do {
					System.out.println("Input Address[4 - 50 character]: ");
					address = scanStr.nextLine();
				} while (address.length()<4||address.length()>50);
			}
		}while(chooseEdit!=4);
		
	}
	
	public boolean validateNumber (String phoneNumber) {
		String numberRegex = "[0-9]+";
		if(!phoneNumber.matches(numberRegex)) {
			return true;
		}else {
			return false;
		}
		
	}
}
