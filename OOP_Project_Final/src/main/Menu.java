package main;

public class Menu {
		private String mealID;
		private String mealName;
		private Integer Price;
		private String Description;

		public Menu(String mealID, String mealName, Integer price, String description) {
			super();
			this.mealID = mealID;
			this.mealName = mealName;
			Description = description;
			Price = price;
		}

		public Integer getPrice() {
			return Price;
		}

		public void setPrice(Integer price) {
			Price = price;
		}
		
		public String getMealID() {
			return mealID;
		}

		public void setMealID(String mealID) {
			this.mealID = mealID;
		}

		public String getMealName() {
			return mealName;
		}

		public void setMealName(String mealName) {
			this.mealName = mealName;
		}

		public String getDescription() {
			return Description;
		}

		public void setDescription(String description) {
			Description = description;
		}
		
		public void displayMenu() {
			System.out.println("You order " + getMealName() + ", cost "+ getPrice());
		}
}
