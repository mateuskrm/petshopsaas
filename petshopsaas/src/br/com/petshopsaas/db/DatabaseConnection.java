package br.com.petshopsaas.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/petshop_saas?useSSL=false&serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	private DatabaseConnection() {
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
