package afip.android.lists;

import afip.android.formation.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListView2Activity extends Activity {
	private String[] data = {"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich","Jelly Bean"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity2_layout);
		
		ListView liste=(ListView)findViewById(R.id.maliste);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  //contexte
				android.R.layout.simple_list_item_1, //  layout appliqué à chaque ligne
				data // données de la liste
		);
		
		liste.setAdapter(adapter); 

	}

	
}
