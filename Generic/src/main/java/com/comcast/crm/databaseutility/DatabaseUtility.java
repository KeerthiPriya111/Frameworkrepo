package com.comcast.crm.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection conn;
public void getDbConnection(String url, String username, String password) throws SQLException {
	try {
	Driver  driver = new Driver();
	DriverManager.registerDriver(driver);
	DriverManager.getConnection(url,username,password);
	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	}catch (Exception e) {		
	}
}
public void closedbconnection() throws SQLException {
	try {
	conn.close();
}catch(Exception e) {	
}
}
public ResultSet executeselectQuery(String Query)  {
	ResultSet exe = null;
	try {
	Statement sta = conn.createStatement();
	 exe =sta.executeQuery(Query);
	}catch (Exception e) {		
	}
	return exe;
}

public int executenonselectQuery(String Query) {
	int exe = 0;
	try {
		Statement sta = conn.createStatement();
		 exe =sta.executeUpdate(Query);
		}catch (Exception e) {		
		}
		return exe;
	}
}
