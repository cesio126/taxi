package com.wsts.taxi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.wsts.taxi.sql.CriarBancoDadosSQL;

public class PostgreFactory {
	public static Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://pghost:5432/pgdatabase"
				+ "?sslfactory=org.postgresql.ssl.NonValidatingFactory"
				+ "&ssl=true";
		Connection conn = DriverManager.getConnection(url, "pguser", "pgpass");

		return conn;
	}
	
	public static void criarBase() throws Exception {
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		st.execute(CriarBancoDadosSQL.criaTabelas) ;
		st.close();
	}
}
