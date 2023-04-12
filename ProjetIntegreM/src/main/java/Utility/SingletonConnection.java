package Utility;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class SingletonConnection {
    private static Connection connection; 
	
	static{
		try {  
                    
                    	String dbURL = "jdbc:mysql://localhost:3306/projetintegre";
				//Connection conn = DriverManager.getConnection(dbURL);
				String user = "root";
				String pass = "";
				
				 
					connection  = DriverManager.getConnection(dbURL, user, pass);
                                        Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp4DB","root","Mud92942");
                        System.out.println("youpi!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        public static Connection getConnection(){
		return connection;
	}

}