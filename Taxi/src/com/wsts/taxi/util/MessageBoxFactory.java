package com.wsts.taxi.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MessageBoxFactory {
    //atributo da classe.
    private AlertDialog alerta;

    public void doisBotoes(final Context c, String titulo, String mensagem) {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        //define o titulo
        builder.setTitle(titulo);
        //define a mensagem
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(c, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(c, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    public void mensagem(final Context c, String titulo, String mensagem) {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        //define o titulo
        builder.setTitle(titulo);
        //define a mensagem
        builder.setMessage(mensagem);
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}
