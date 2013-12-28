package com.wsts.taxi.sql;

public class CriarBancoDadosSQL {
             
	public static final String criaTabelas =

             "   DROP TABLE IF EXISTS TAXI.USUARIOS;\n" +

             "   CREATE TABLE IF NOT EXISTS TAXI.USUARIOS(\n" +
             "     ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
             "     SENHA VARCHAR(50),\n" +
             "     MATRICULA VARCHAR(50),\n" +
             "     ATIVO BOOL\n" +
             "   );";
}
