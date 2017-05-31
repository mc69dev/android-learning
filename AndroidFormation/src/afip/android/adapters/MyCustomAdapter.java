package afip.android.adapters;

import java.util.ArrayList;
import java.util.List;

import afip.android.formation.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomAdapter extends BaseAdapter {

	Context context;
	static List<AndroidVersion> liste= new ArrayList<AndroidVersion>();
	
	public static void init(){
		liste.add(new AndroidVersion("Cupcake 1.5"," (API 3) : avril  2009."));
		liste.add(new AndroidVersion("Donut 1.6"," (API 4) : septembre 2009."));
		liste.add(new AndroidVersion("Eclair 2.0/2.1","(API 7) : octobre 2009.")); 
		liste.add(new AndroidVersion("Froyo 2.2.x","(API 8) : mai 2010.")); 
		liste.add(new AndroidVersion("Gingerbread 2.3.x ","(API 10) : décembre 2010.")); 
		liste.add(new AndroidVersion("Honeycomb 3.x"," (API 11 ­ 12 ­ 13) : février 2011.")); 
		liste.add(new AndroidVersion("Ice Cream Sandwich 4.0.x"," (API 14-15)  :  octobre  2011.")); 
		liste.add(new AndroidVersion("Jelly Bean 4.1.x"," (API 16) : juin 2012."));
		liste.add(new AndroidVersion("KitKat 4.4.x"," (API 17 et +) : septembre 2013."));
		
	}

	public MyCustomAdapter(Context context) {
		this.context=context;
		if(liste.size()==0)
			init();
	}

	@Override
	public int getCount() {
		return liste.size();
	}

	@Override
	public Object getItem(int position) {
		return liste.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		
		if(v==null){		
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_layout,parent,false);
		}

		AndroidVersion elem= liste.get(position);

		TextView tv1=(TextView)v.findViewById(R.id.title);
		TextView tv2=(TextView)v.findViewById(R.id.description);
		
		tv1.setText(elem.getTitle());
		tv2.setText(elem.getDesc());

		tv1.setTextColor(position%2==0?Color.RED:Color.BLUE);
		
		ImageView img=(ImageView)v.findViewById(R.id.icon);

		img.setImageResource(icons[position]);
		return v;
	}
	static int[] icons= new int[]{R.drawable.actionbar_logo, R.drawable.actionbar_logo, 
		R.drawable.actionbar_logo, R.drawable.froyo, R.drawable.gingerbread, 
		R.drawable.honeycomb, R.drawable.ics, R.drawable.jb, R.drawable.kitkat};
}
