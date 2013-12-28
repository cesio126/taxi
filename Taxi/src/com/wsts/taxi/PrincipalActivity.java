package com.wsts.taxi;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wsts.taxi.pojo.UsuarioVO;
import com.wsts.taxi.util.DataBaseFactory;
import com.wsts.taxi.util.DatabaseHelper;
import com.wsts.taxi.util.MessageBoxFactory;

public class PrincipalActivity extends Activity implements OnClickListener {
	protected final Context PrincipalActivity = this;
	private TextView matricula;
	private DataBaseFactory  dh;
	public List<String> lString = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_principal);
        
        try {
        	dh = new DataBaseFactory(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Button botao = (Button) findViewById(R.id.cmdEntrar);		
		botao.setOnClickListener(this);
		
        super.onCreate(savedInstanceState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.principal, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_edit:
	            Toast.makeText(this, "MENU EDIT", Toast.LENGTH_LONG).show();
	            return true;
	        case R.id.menu_delete:
	            Toast.makeText(this, "MENU DELETE", Toast.LENGTH_LONG).show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    public void onClick(View v) {
    	matricula = (TextView) findViewById(R.id.txtMatricula);
    	try {
			List<String> lString = dh.selectOne(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	MessageBoxFactory mensagem = new MessageBoxFactory();
    	mensagem.mensagem(PrincipalActivity, "Atenção", matricula.getText().toString());
    }
}
