package com.wsts.taxi.util;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wsts.taxi.sql.CriarBancoDadosSQL;



public class OpenHelper extends SQLiteOpenHelper {
	public OpenHelper(Context context, String basePath, String DATABASE_NAME, int DATABASE_VERSION) {
		super(context, basePath + "/" +  DATABASE_NAME, null, DATABASE_VERSION );
		this.onOpen(getReadableDatabase());
		/*
		File DATABASE = new File(basePath + "/" +  DATABASE_NAME);
		if (!DATABASE.getParentFile().exists()) {
			DATABASE.mkdir();
		}else{
			DATABASE.getParentFile().canRead();
			DATABASE.getParentFile().canWrite();
		}*/
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = CriarBancoDadosSQL.criaTabelas;
		db.execSQL(sql);
		db.setTransactionSuccessful();
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion, String TABLE_NAME) {
		Log.w("Example",
				"Upgrading database, this will drop tables and recreate.");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
