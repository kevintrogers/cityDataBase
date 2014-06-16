import java.sql.*;

import javax.swing.*;
/*
 * Kevin Rogers
 * CS130
 * Homework 10
 */

public class citySorter {

	public static void main(String[] args) {
		String choice;
		int option;
		
		String DB_URL = "jdbc:derby:CityDB";
		

		do{
			choice = JOptionPane.showInputDialog("Which action would you like to perform?\n" +
												"1) Get an alphabetized list of cities\n" +
												"2) Get A list of cities sorted by population high to low\n" +
												"3) Get A list of cities sorted by population low to high\n" +
												"4) Get the total population of all cities in the list\n" +
												"5) Get the average population of cities on the list\n" +
												"6) Get the city with the highest population\n" +
												"7) Get the city with the lowest population\n");
			
			option = Integer.parseInt(choice);
			if (choice == null)
				System.exit(0);
	
	
			switch (option){
			case 1:
				getCityList(DB_URL);
				break;
			case 2:
				getBigCityList(DB_URL);
				break;
			case 3:
				getSmallCityList(DB_URL);
				break;
			case 4:
				getPopulationTotal(DB_URL);
				break;
			case 5:
				getPopulationAverage(DB_URL);
				break;
			case 6:
				getHighPopulation(DB_URL);
				break;
			case 7:
				getLowPopulation(DB_URL);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Thank you for using our program");
				System.exit(0);
			}//switch
		}while (option >0 && option <8);
	}
	

	static void getCityList(String url) {
		try{
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT * FROM City ORDER BY CityName";
			ResultSet result = stmt.executeQuery(sqlStatement);
			
			while (result.next()){
				System.out.printf("%25s %12.0f\n", result.getString("CityName"), result.getDouble("Population"));
			}
			conn.close();
		}//try
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
		}
	}

	private static void getBigCityList(String url) {
		try{
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT * FROM City ORDER BY Population DESC";
			ResultSet result = stmt.executeQuery(sqlStatement);
			
			while (result.next()){
				System.out.printf("%25s %12.0f\n", result.getString("CityName"), result.getDouble("Population"));
			}
			conn.close();
		}//try
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
		}
	}
	private static void getSmallCityList(String url) {
		try{
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT * FROM City ORDER BY Population";
			ResultSet result = stmt.executeQuery(sqlStatement);
			
			while (result.next()){
				System.out.printf("%25s %12.0f\n", result.getString("CityName"), result.getDouble("Population"));
			}
			conn.close();
		}//try
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
		}
	}
	private static void getPopulationTotal(String url) {
		try{
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT SUM(Population) FROM City";;
			ResultSet result = stmt.executeQuery(sqlStatement);
			
			while (result.next()){
				double sum = result.getDouble(1);
				System.out.printf("The total population of all of these cities is %12.0f\n", sum);				
			}
			conn.close();
		}//try
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
		}
	}
	private static void getPopulationAverage(String url) {
		try{
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT AVG(Population) FROM City";
			ResultSet result = stmt.executeQuery(sqlStatement);
			
			while (result.next()){
				double avg= result.getDouble(1);
				System.out.printf("The average population of these cities is %12.0f\n", avg);
			}
			conn.close();
		}//try
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
		}
	}
	private static void getHighPopulation(String url) {
		try{
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT MAX(Population) FROM City";
			ResultSet result = stmt.executeQuery(sqlStatement);
			
			while (result.next()){
				double max = result.getDouble(1);
				System.out.printf("The city with the largest population has a total population of  %12.0f\n", max);
			}
			
			conn.close();
		}//try
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
		}
	}
	private static void getLowPopulation(String url) {
		try{
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT MIN(Population) FROM City";
			ResultSet result = stmt.executeQuery(sqlStatement);
			
			while (result.next()){
				double min = result.getDouble(1);
				System.out.printf("The city with the lowest population has a total population of  %12.0f\n", min);
			}
			conn.close();
		}//try
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
		}
	}
}