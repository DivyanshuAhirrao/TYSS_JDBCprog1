package com.ty.foodApp.dao;

import java.util.Scanner;
import com.ty.foodApp.dto.Product;

public class Driver {
	
	static Scanner sc = new Scanner(System.in);
	static ProductDAO fd = new ProductDAO();
	

	public static void main(String[] args) {
		
		boolean flag = true;
		while(flag) {
			System.out.println("Enter the Choice : ");
			System.out.println("---------------------------------");
			System.out.println("1) Add Product");
			System.out.println("2) Update Product By Id");
			System.out.println("3) Get All Products details");
			System.out.println("4) Get Product details by Id");
			System.out.println("5) Remove Product Item");
			System.out.println("6) Exit");
			int ch = sc.nextInt();
			
			switch (ch) {
			case 1:{
				validation();
			}break;
            case 2:{
				updater();
			}break;
            case 3:{
            	printAllRows();
            }break;
            case 4:{
            	printRow();
            }break;
            case 5:{
            	remove();
            }break;
            case 6: {
            	System.out.println("Thank you");
            	flag= false;
            }break;
            
			default:
				System.out.println("Invalid input ! Try Again !!");
			}	
		}
		
	}
	
	static void validation(){
	    Product obj = fd.addProduct(foodObject());
		if (obj != null) {
		    System.out.println("Produt inserted successfully !!");
		}
		else {
			System.out.println("Duplicate product");
		}
	}
	
	private static Product foodObject() {
		System.out.println("Enter the Food Id");
		int fid = sc.nextInt();
		System.out.println("Enter the Food Name");
		String fname = sc.next();
		System.out.println("Enter the Food Quantity");
		int fqty = sc.nextInt();
		System.out.println("Enter the Food Price");
		double fprice = sc.nextDouble();
		Product obj= new Product(fid,fname,fqty,fprice);	
		return obj;
		
	}
	private static void updater(){
		System.out.println("Enter the Food Id");
		int fid = sc.nextInt();
		System.out.println("Enter the Food Price to be Updated");
		double fprice = sc.nextDouble();
		fd.updateProduct(fid, fprice);
		
	}
	
	private static void printAllRows() {
		System.out.println(fd.getAllProducts());
	}
	
	private static void printRow() {
		System.out.println(fd.getProductById(remover()));
	}
	
	private static int remover(){
		System.out.println("Enter the Product Id");
		int fid = sc.nextInt();
		return fid;
	}
	private static void remove(){
		if (fd.removeProductById(remover()) == true) {
			System.out.println("Product Removed SuccessFully !!");
		}
	}
	
	
	
	
}
