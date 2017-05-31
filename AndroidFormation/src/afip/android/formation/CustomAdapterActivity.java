package afip.android.formation;

import afip.android.adapters.AndroidVersion;
import afip.android.adapters.MyCustomAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CustomAdapterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_custom_activity_layout);
		
		setTitle("ListActivity Custom Adapter..");
		
		ListView liste =(ListView)findViewById(R.id.malisteview);		

		MyCustomAdapter adapter = new MyCustomAdapter(this);
		liste.setAdapter(adapter); 
				
		liste.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
				
				AndroidVersion selectedItem = (AndroidVersion)adapter.getItemAtPosition(position); 
				Toast.makeText(getApplicationContext(), "Clic : "+selectedItem.getTitle(), Toast.LENGTH_LONG).show();
								
			}			
		});
		
	}

	
}
