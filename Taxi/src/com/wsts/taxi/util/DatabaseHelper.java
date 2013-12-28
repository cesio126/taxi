package com.wsts.taxi.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.wsts.taxi.pojo.UsuarioVO;
import com.wsts.taxi.sql.CriarBancoDadosSQL;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "TAXI.db";
	private static final String DATABASE_TABLE = "USUARIOS";
	public Context context;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CriarBancoDadosSQL.criaTabelas);
		db.setTransactionSuccessful();
		onOpen(db);
	}
	
	public void onOpen(SQLiteDatabase db){
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public UsuarioVO select(String userLogin, Context context) throws Exception {
		UsuarioVO usuario = null;
		Cursor cursor = null;

		SQLiteDatabase sqlLite = new DatabaseHelper(context)
				.getReadableDatabase();

		String where = "MATRICULA = ?";

		String argumentos[] = new String[] { userLogin };

		cursor = sqlLite.query(DATABASE_TABLE, null, where, argumentos, null,
				null, null);

		if (cursor != null) {
			cursor.moveToFirst();

			usuario = new UsuarioVO();
			usuario.setMatricula(cursor.getString(cursor.getColumnIndex("MATRICULA")));
		}

		if (cursor != null)
			cursor.close();

		return usuario;
	}
}
