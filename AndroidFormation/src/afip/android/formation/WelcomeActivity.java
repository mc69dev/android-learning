package afip.android.formation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_layout);
		

		TextView tv=(TextView)findViewById(R.id.tv);

		Intent intent = getIntent();
		String login= intent.getStringExtra("login_extra"); // =>putExtra("login_extra",xxxx.toString())!!!
		Log.i("MC-TESTS", "Login.."+login);
		
		tv.setText("Welcome.."+login);
	}

	
}
