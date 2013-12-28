package com.wsts.taxi.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;

import com.wsts.taxi.sql.CriarBancoDadosSQL;

public class DataBaseFactory {

	private static final String DATABASE_NAME = "TAXI.db";
	private static final int DATABASE_VERSION = 1;
	public static String TABLE_NAME = "USUARIOS";
	public static final String WHERE = null;

	private Context context;
	private SQLiteDatabase db;

	private SQLiteStatement insertStmt;
	private SQLiteStatement updateStmt;
	private SQLiteStatement queryStmt;
	private static String basePath = Environment.getExternalStorageDirectory().getAbsolutePath();
	private static final String INSERT = "insert into " + TABLE_NAME
			+ "(name) values (?)";
	private static final String UPDATE = "update " + TABLE_NAME
			+ "set name = ?";
	private static String query = "";
	private static String[] parametros = null;

	public DataBaseFactory(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context, basePath, DATABASE_NAME, DATABASE_VERSION);
		this.db = openHelper.getReadableDatabase();
		// String caminho = openHelper.getReadableDatabase().getPath();
		// this.insertStmt = this.db.compileStatement(INSERT);
		// this.updateStmt = this.db.compileStatement(UPDATE);
	}

	public long excuteQuery(int total, String[] campos) {
		for (int i = 0; i < total; i++) {
			this.insertStmt.bindString(i, campos[i]);
		}
		return this.insertStmt.executeInsert();
	}

	public long insert(String name) {
		this.insertStmt.bindString(1, name);
		return this.insertStmt.executeInsert();
	}

	public long update(String name) {
		this.updateStmt.bindString(1, name);
		return this.updateStmt.executeUpdateDelete();
	}

	public void deleteAll() {
		this.db.delete(TABLE_NAME, null, null);
	}

	public List<String> selectAll(String[] campos) {
		List<String> list = new ArrayList<String>();
		Cursor cursor = this.db.query(TABLE_NAME, campos, WHERE, null, null,
				null, null);
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public List<String> selectOne(String[] campos) {
		List<String> list = new ArrayList<String>();
		Cursor cursor = this.db.query(TABLE_NAME, campos, WHERE, null, null,
				null, null);
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}
}
