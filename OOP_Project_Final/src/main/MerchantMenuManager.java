package main;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import connectSQL.SQL;

public class MerchantMenuManager {
	
	Scanner scan = new Scanner(System.in);
	SQL sql = new SQL();
	Vector<Menu> menus = sql.view();
	Random rand = new Random();
	RegistrationAccountService acc;
	
	public MerchantMenuManager() {
		int chooseMenu = 0;
		do {
			System.out.println("Merchant Menu Manager");
			System.out.println("1. Input New Menu");
			System.out.println("2. View All Menu");
			System.out.println("3. Remove Menu");
			System.out.println("4. Update Menu");
			System.out.println("5. Exit");
			System.out.println("Choose >> ");
			chooseMenu = scan.nextInt();
			scan.nextLine();
			switch (chooseMenu) {
			case 1:
				newMenu();
				new MerchantMenuManager();
				break;
			case 2:
				ViewMenu();
				break;
			case 3:
				RemoveMenu();
				new MerchantMenuManager();
				break;
			case 4:
				UpdateMenu();
				new MerchantMenuManager();
				break;
			case 5:
				System.exit(0);
			}
			
		} while (chooseMenu!=5);
	}

	public void UpdateMenu() {
		
		if(menus.isEmpty()) {
			System.out.println("There's no Menu yet!");
		}else {
			ViewMenu();
			String updateID = null;
			String newMealName = null;
			String newDescription=null;
			int chooseToUpdate=0;
			boolean findingID=false;
			int newPrice = 0;
			do {
				System.out.println("Choose which ID to remove:  ");
				updateID = scan.nextLine();
				for (int i = 0; i < menus.size(); i++) {
					if(menus.get(i).getMealID().equals(updateID)) {
						chooseToUpdate = i;
						findingID=true;
					}	
				}
			} while (!findingID);
			do {
				System.out.println("Update Meal Name [Must Not Empty]:  ");
				try {
					newMealName = scan.nextLine();
				} catch (Exception e) {
					newMealName = null;
				}
			} while (newMealName.length()<1);
			do {
				System.out.println("Update Meal Price [Must Not Empty]:  ");
				try {
					newPrice = scan.nextInt();
				} catch (Exception e) {
					newPrice=0;
				}
				scan.nextLine();
			} while (newPrice<=0);
			do {
				System.out.println("Update Description  ");
				try {
					newDescription = scan.nextLine();
				} catch (Exception e) {
					newDescription = null;
				}
			} while (newDescription == null);
			sql.update(new Menu(updateID, newMealName, newPrice, newDescription), updateID);
			System.out.println("Menu Updated...");		
		}		
	}
	public void RemoveMenu() {
		if(menus.isEmpty()) {
			System.out.println("There's no Menu yet!");
		}else {
			ViewMenu();
			boolean findingID=false;
			String removeID;
			do {
				System.out.println("Choose which ID to remove:  ");
				removeID = scan.nextLine();
				for (int i = 0; i < menus.size(); i++) {
					if(menus.get(i).getMealID().equals(removeID)) {
						findingID=true;
					}	
				}
			} while (!findingID);
			sql.delete(removeID);
			System.out.println("Menu Deleted!");
		}
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

	public void newMenu() {
		// TODO Auto-generated method stub
		String mealID;
		String mealName;
		Integer Price;
		String Description;
		
		
		mealID = "OR"+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10);
		do {
			System.out.println("Input Menu Name [Must Not Empty]:  ");
			mealName = scan.nextLine();
		} while (mealName.length()<1);
		do {
			System.out.println("Input Price:  ");
			Price = scan.nextInt();
			scan.nextLine();		
		} while (Price<=0);
		do {
			System.out.println("Input Description : ");
			Description = scan.nextLine();
		} while (Description == null);
		
		sql.insert(new Menu(mealID, mealName, Price, Description)); 
		
	}



}
