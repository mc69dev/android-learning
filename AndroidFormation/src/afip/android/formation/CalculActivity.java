package afip.android.formation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CalculActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calcul_layout);
		
		
		Button plus=(Button) findViewById(R.id.btnPlus);
		Button moins=(Button) findViewById(R.id.btnMoins);
		Button multi=(Button) findViewById(R.id.btnMulti);
		Button div=(Button) findViewById(R.id.btnDiv);
		
		OnClickListener l= new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		};
	
		plus.setOnClickListener(this);
		moins.setOnClickListener(this);
		multi.setOnClickListener(this);
		div.setOnClickListener(this);
		
		
		//recup Intent courant
		Intent intent = getIntent();
		if(intent!=null){
			String data=intent.getStringExtra("nom");
			Message("Réception donnée : "+data);
		}
		
	}
	private void Message(String mess){
		Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onClick(View v) {
		if (v instanceof Button) {
			Button btn = (Button) v;
			
			int id=btn.getId();
			switch (id) {
			case R.id.btnPlus:
				Toast.makeText(this, "plus..", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btnMoins:
				Toast.makeText(getApplicationContext(), "moins..", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btnMulti:
				Toast.makeText(getApplicationContext(), "multiplication..", Toast.LENGTH_SHORT).show();
				break;
			case R.id.btnDiv:
				Toast.makeText(getApplicationContext(), "division..", Toast.LENGTH_SHORT).show();
				break;	
			default:
				Toast.makeText(getApplicationContext(), "inconnue!", Toast.LENGTH_SHORT).show();
				break;
			}
		}		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calcul, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
