package afip.android.formation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeResultActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_result_layout);
		
		TextView tv=(TextView)findViewById(R.id.tv1000);
		Button btn=(Button)findViewById(R.id.btnDisconn);
		Button btn2=(Button)findViewById(R.id.btnCancel);
		
		Intent intent = getIntent();
		
		String login= intent.getStringExtra("login_extra"); // =>putExtra("login_extra",xxxx.toString())!!!
		Log.i("MC-TESTS", "Login.."+login);
		
		tv.setText("Welcome.."+login);
			
		btn.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
	
		Button btn = (Button)v;	
		Intent intent = new Intent(); 
		
		if(btn.getId()==R.id.btnDisconn){
			intent.putExtra("message", "Bye MC!"); 
			setResult(RESULT_OK, intent);
		}else{
			intent.putExtra("message", "Nothing.."); 
			setResult(RESULT_CANCELED, intent);
		}
		finish();	
	}
}
