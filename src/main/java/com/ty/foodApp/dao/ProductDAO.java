package com.ty.foodApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ty.foodApp.dto.Product;

public class ProductDAO {

	private static String url = "jdbc:postgresql://localhost:5432/shop";
	private static String user = "postgres";
	private static String password = "root";
	private static Connection con;
	private static PreparedStatement pstmt; 
	private static Product food = new Product();
	
	public ProductDAO() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Product addProduct(Product f) {
		try {
			String qry = "insert into food values(?,?,?,?)";
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, f.getProduct_id());
			pstmt.setString(2, f.getProduct_name());
			pstmt.setInt(3, f.getProduct_qty());
			pstmt.setDouble(4, f.getProduct_price());
			
			int num = pstmt.executeUpdate();
			
			if(num != 0) {
			    return f;
			}
			
		}catch(SQLException e){
			return null;
		}
		return null;
	}
	
	public Product updateProduct(int id, double Price) {
		try {
			PreparedStatement pstmt = con.prepareStatement("Update food set food_price = ? where food_id = ?");
		    pstmt.setDouble(1, food.getProduct_price());
		    pstmt.setInt(2, food.getProduct_id());
		    int num = pstmt.executeUpdate();
		    if(num != 0) {
			    return food;
			}
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Product getProductById(int id){
		try {
			pstmt = con.prepareStatement("select * from food where food_id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				food.setProduct_id(rs.getInt(1));
				food.setProduct_name(rs.getString(2));
				food.setProduct_qty(rs.getInt(3));
				food.setProduct_price(rs.getDouble(4));
			}
			if(rs != null) {
				return food;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public List<Product> getAllProducts(){
		List<Product> items = new ArrayList<Product>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from food");
			
			while(rs.next()) {
				food.setProduct_id(rs.getInt(1));
				food.setProduct_name(rs.getString(2));
				food.setProduct_qty(rs.getInt(3));
				food.setProduct_price(rs.getDouble(4));
				items.add(food);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public boolean removeProductById(int id) {		
		try {
		    pstmt = con.prepareStatement("delete from food where food_id = ?");
			pstmt.setInt(1, id);
			int num = pstmt.executeUpdate();
			if (num != 0) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
}
