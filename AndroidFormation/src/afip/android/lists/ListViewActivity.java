package afip.android.lists;

import afip.android.formation.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListViewActivity extends ListActivity {
	private String[] data = {"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich","Jelly Bean"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_layout);
		
		ArrayAdapter adapter =  new ArrayAdapter(this,  //contexte
				android.R.layout.simple_list_item_1, //  layout appliqué à chaque ligne
				data // données de la liste
		);
		
		setListAdapter(adapter);
	}

	
}
