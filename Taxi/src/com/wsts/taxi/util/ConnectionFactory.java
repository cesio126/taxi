package com.wsts.taxi.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import android.os.Environment;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteCursorDriver;

import com.wsts.taxi.sql.CriarBancoDadosSQL;

public class ConnectionFactory {

	/**
	 * 
	 * @author wsts
	 */
	private static Connection conn = null;
	private static String driver = "android.database.sqlite";
	private static String user = "";
	private static String senha = "";

	private static final int BUFFER = 1 * 1024 * 1024;
	private static File root = Environment.getExternalStorageDirectory();
	private static final File DATABASE = new File(root + System.getProperty("file.separator") + "taxi.sqlite");
	private static String url = DATABASE.getPath();

	public static Connection getConnection() throws Exception {
		 Connection conn = null;

		   Class.forName(driver).newInstance();
		   conn = DriverManager.getConnection("jdbc:sqlite:"
					+ url);
		return conn;
	}

	public static void checkDatabase() throws Exception {
		if (!DATABASE.exists()) {
			createNewDatabase();
		}
	}

	public static void createNewDatabase() throws Exception {
		if (!DATABASE.getParentFile().exists()) {
			DATABASE.mkdir();
		}else{
			DATABASE.getParentFile().setWritable(true);
			DATABASE.getParentFile().setReadable(true);
		}
		if (root.canWrite()) {
			if (DATABASE.createNewFile()) {
				if (!DATABASE.exists()) {
					throw new Exception(
							"Erro ao gravar o arquivo de banco de dados.");
				}
			}
		}
		
		Connection conn = ConnectionFactory.getConnection();
		Statement s = conn.createStatement();
		s.execute(CriarBancoDadosSQL.criaTabelas);
	}

	public static void removeDataBase() throws Exception {
		DATABASE.delete();
	}

	public static void backupDatabase(File arquivoBkp) throws Exception {

		// Verificações iniciais
		if (!DATABASE.exists()) {
			throw new Exception(
					"Não foi possível fazer backup porquê o arquivo de dados não foi localizado!");
		}
		if (!arquivoBkp.isDirectory()
				&& !arquivoBkp.getName().toLowerCase().endsWith(".db")) {
			arquivoBkp = new File(arquivoBkp.getPath() + ".db");
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(new FileInputStream(DATABASE), BUFFER);
			bos = new BufferedOutputStream(new FileOutputStream(arquivoBkp),
					BUFFER);

			int byteLido;
			while ((byteLido = bis.read()) != -1) {
				bos.write(byteLido);
			}

		} finally {
			if (bos != null) {
				bos.flush();
				bos.close();
			}
			if (bis != null) {
				bis.close();
			}
		}

	}

	public static void recoverBackupDatabase(File arquivoBkp) throws Exception {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(new FileInputStream(arquivoBkp),
					BUFFER);
			bos = new BufferedOutputStream(new FileOutputStream(DATABASE),
					BUFFER);

			int byteLido;
			while ((byteLido = bis.read()) != -1) {
				bos.write(byteLido);
			}

		} finally {
			if (bos != null) {
				bos.flush();
				bos.close();
			}
			if (bis != null) {
				bis.close();
			}
		}

	}

	public Connection getConexao() throws SQLException {

		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, user, senha);

			System.out.println("Conexão realizada com sucesso.");

		} catch (ClassNotFoundException ex) {
			System.err.print(ex.getMessage());
		}

		return conn;
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}// fim da classe
