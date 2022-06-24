package connectSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import main.Menu;

public class SQL {
	
	Connect con = Connect.getConnection();
	
	public void insert(Menu menus){
		String insertQuery = String.format("INSERT INTO menu VALUES('%s' , '%s' , '%d' , '%s')", menus.getMealID(),
				menus.getMealName(), menus.getPrice(), menus.getDescription());
		con.executeUpdate(insertQuery);
	}
	
	public void delete(String mealId) {
		String deleteQuery = String.format("DELETE FROM menu WHERE mealId = '%s' ", mealId);
		con.executeUpdate(deleteQuery);
	}
	
	public void update(Menu menus, String mealId) {
		String updateQuery = String.format("UPDATE menu SET mealId = '%s', "
				+ "mealName = '%s', "
				+ "price = '%d', "
				+ "description = '%s' "
				+ "WHERE mealId = '%s' ", menus.getMealID(), menus.getMealName(), menus.getPrice(), 
				menus.getDescription(), mealId);
		con.executeUpdate(updateQuery);
	}
	
	public Vector<Menu> view(){
		Vector<Menu> menus = new Vector<>();
		String query = String.format("SELECT * FROM menu");
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				Menu menu = new Menu(rs.getString("mealId"), rs.getString("mealName"),rs.getInt("price"),
						rs.getString("description"));
				menus.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menus;
	}
}
