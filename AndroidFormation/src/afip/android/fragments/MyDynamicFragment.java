package afip.android.fragments;

import afip.android.formation.R;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyDynamicFragment extends Fragment {
	private int num;
	
	public MyDynamicFragment(int num) {
		this.num=num;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	   int layout=(this.num==1)?R.layout.dynamic_fragment_1_layout:R.layout.dynamic_fragment_2_layout;

	   View view = inflater.inflate(layout, container, false); 
	   TextView tv=(TextView)view.findViewById(R.id.tv1title);
		
	  //Recup. Données passées au fragment courant!!! 
	   String title = getArguments().getString("title");
	   tv.setText(title);
		
	   return view;//inflater.inflate(layout, container, false);
	}
}
