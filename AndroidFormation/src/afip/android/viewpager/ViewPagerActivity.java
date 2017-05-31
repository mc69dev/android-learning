package afip.android.viewpager;

import afip.android.formation.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerActivity extends FragmentActivity {

	ViewPager viewpager;
	PagerAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity_layout);
				
		// définition adapter.
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		
		viewpager=(ViewPager)findViewById(R.id.pager);
		viewpager.setAdapter(adapter);
		
		
		//new DepthPageTransformer()
		//viewpager.setPageTransformer(true,new ZoomOutPageTransformer());
	}
	
	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {
			return MySlideFragment.Instance(index);
		}

		@Override
		public int getCount() {
			return 3;
		}
		
	}
	
	public static class MySlideFragment extends Fragment{
		public static MySlideFragment Instance(int num){
			MySlideFragment fragment = new MySlideFragment();

			Bundle args= new Bundle();
			args.putInt("num", num);
			fragment.setArguments(args);					
				
			return fragment;
		}
		
		public MySlideFragment() {}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {			
			View view = inflater.inflate(R.layout.slide_fragment_layout, container, false);
			
			TextView tv=(TextView)view.findViewById(R.id.tvSlide);
			ImageView img=(ImageView)view.findViewById(R.id.imgSlide);
			
			int num = getArguments().getInt("num");
			
			switch (num) {
				case 0:
					tv.setText("Froyo 2.2.x");
					img.setImageResource(R.drawable.froyo);
					break;
				case 1:
					tv.setText("Gingerbread 2.3.x");
					img.setImageResource(R.drawable.gingerbread);
					break;	
				default:
					tv.setText("Honeycomb 3.x");
					img.setImageResource(R.drawable.honeycomb);
					break;
			}

			return view;
		}
	}
}
