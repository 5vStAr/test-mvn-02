package com.maven.app01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	
	private ConnectionPool(){

	}

	private static Connection connection = getConnection();

	private static ConnectionPool instance = null;

	public static ConnectionPool getInstance(){

		if(instance == null){
			instance = new ConnectionPool();
			return instance;
		}
		return instance;
	}

	static Connection getConnection(){
		try{
			try{
				Class.forName("org.postgresql.Driver").newInstance();
			}catch (InstantiationException | IllegalAccessException e){
				e.printStackTrace();
			}
		}catch (ClassNotFoundException e1){
			e1.printStackTrace();
		}

		String userName = "bzhurtljkqgexm";
		String password = "9f3a312550a4ecdc724ebe4259340e96614e87fa368833bb70333e14f5dd0784";
		String dbUrl = "jdbc:postgresql://" + "ec2-54-164-238-108.compute-1.amazonaws.com:5432" + "/de3q7odf6cq72n?sslmode=require";

		try{
			return DriverManager.getConnection(dbUrl, userName, password);
		}catch (SQLException e){
			e.printStackTrace();
		}

		return connection;

	}

}
