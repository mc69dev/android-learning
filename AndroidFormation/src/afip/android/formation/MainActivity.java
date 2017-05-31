package afip.android.formation;

import afip.android.fragments.DynamicFragmentActivity;
import afip.android.fragments.SimpleFragmentActivity;
import afip.android.lists.ListView2Activity;
import afip.android.lists.ListViewActivity;
import afip.android.prefs.ConfigurationActivity;
import afip.android.sqlite.MySQLiteActivity;
import afip.android.viewpager.ViewPagerActivity;
import afip.android.web.WebViewActivity;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int REQUEST_WELCOME2_CODE = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_layout);
				
		final EditText login=(EditText)findViewById(R.id.edtLogin);
		final EditText passw=(EditText)findViewById(R.id.edtPassword);

		Button btn=(Button)findViewById(R.id.btnLogin);
				
		btn.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Log.i("MC-TESTS", "Login.."+login.getText());
				
				/*
				Intent intent= new Intent(getApplicationContext(),WelcomeActivity.class);	
				
				intent.putExtra("login_extra", login.getText().toString());
				intent.putExtra("password_extra", passw.getText().toString());

				startActivity(intent);
				*/
				
				Intent intent= new Intent(getApplicationContext(),WelcomeResultActivity.class);	
				intent.putExtra("login_extra", login.getText().toString());
				intent.putExtra("password_extra", passw.getText().toString());
				startActivityForResult(intent, REQUEST_WELCOME2_CODE);
				
				

			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==REQUEST_WELCOME2_CODE)
			if(resultCode==RESULT_OK)
				Message("Déconnection :"+data.getStringExtra("message"));
			else
				Message("Canceled :"+data.getStringExtra("message"));

		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent =null;
		
		int id=item.getItemId();
		if(id==R.id.actionCalc){
			intent = new Intent(this, CalculActivity.class);			
			intent.putExtra("nom","mc"); //envoi variable "nom"
		}else if(id==R.id.actionListView){
			intent = new Intent(this, ListViewActivity.class);		
		}else if(id==R.id.actionListView2){
			intent = new Intent(this, ListView2Activity.class);		
		}else if(id==R.id.actionListViewCustom){
			intent = new Intent(this, CustomAdapterActivity.class);		
		}else if(id==R.id.actionFragmentFixe){
			intent = new Intent(this, SimpleFragmentActivity.class);		
		}else if(id==R.id.actionFragmentDynamic){
			intent = new Intent(this, DynamicFragmentActivity.class);		
		}else if(id==R.id.actionViewPager){
			intent = new Intent(this, ViewPagerActivity.class);		
		}
		else if(id==R.id.actionConfig){
			intent = new Intent(this, ConfigurationActivity.class);		
		}
		else if(id==R.id.actionWebView){
			intent = new Intent(this, WebViewActivity.class);		
		}else if(id==R.id.actionDatabase){
			intent = new Intent(this, MySQLiteActivity.class);		
		}
		
		if(intent!=null){
			startActivity(intent);
		}
		return true;
	}
	
	private void Message(String mess){
		Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
	}
	
	public void onSearch(MenuItem item){
		Message("Click sur Option Menu! "+item.getItemId());
	}
	
	public void OpenDialog(View v){
		int id=v.getId();
		if(id==R.id.btnDialog1){
			
			Builder builder = new Builder(this);
			builder.setMessage("Ceci est un message..!");
			
			builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "Ok..", Toast.LENGTH_SHORT).show();
					dialog.cancel();
				}
			}).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "Annuler..", Toast.LENGTH_SHORT).show();
					dialog.cancel();
				}
			}); 
			builder.create().show();
						
		}else if(id==R.id.btnDialog2){
			Dialog dialog= new Dialog(this);
			dialog.setContentView(R.layout.dialog_login_layout);
			dialog.setTitle("Login..");
			
			OnClickListener listener = new OnClickListener() {			
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "btn.."+v.getId(), Toast.LENGTH_SHORT).show();
					
				}
			};
			Button dialogButtonOk = (Button) dialog.findViewById(R.id.btndialogOK);
			Button dialogButtonCancel = (Button) dialog.findViewById(R.id.btndialogCancel);
			dialogButtonOk.setOnClickListener(listener);
			dialogButtonCancel.setOnClickListener(listener);
			
			dialog.show();
		}else{
			//Prefs!
			SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
			boolean wifi_ok= sp.getBoolean("swc_key", false);
			boolean conn_auto= sp.getBoolean("chb_key", false);
			String reseau_pref= sp.getString("edt_key", "?");
			
			Message("Prefs. : wifi = "+wifi_ok+ " connection auto = "+conn_auto+" réseau = "+reseau_pref);
		}
	}

}
