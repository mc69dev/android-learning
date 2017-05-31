package afip.android.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import afip.android.formation.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class DynamicFragmentActivity extends FragmentActivity {

	private final MyDynamicFragment fragment1 = new MyDynamicFragment(1);
	private final MyDynamicFragment fragment2 = new MyDynamicFragment(2);
	Bundle args;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_fragment);
		setTitle("Fragments..");
		
		args= new Bundle();
		loadFragment(fragment1);
	}
		
	private void loadFragment(Fragment fragment){
		
		FragmentManager fm = getSupportFragmentManager(); 		// Gestionnaire fragments
		FragmentTransaction ft = fm.beginTransaction();			// Début transaction
	
		ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right); // Ajout animation de transition
				
		//Passage Données au Fragment!!
		try {
			args.putString("title", "Fragment.."+getNow());
			fragment.setArguments(args);	
		} catch (Exception e) {}
					
		
		ft.replace(R.id.fragmentplaceholder, fragment); // Remplacer fragment actuel par un autre
		ft.addToBackStack(null); 						// Revenir au fragment précedent sur bouton précedent.
		
		ft.commit();		
	}
	
	private String getNow(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a",Locale.FRENCH);
		return sdf.format(c.getTime());
	}
	
	public void slideFragment(View v){
		if(v.getId()==R.id.btnfragment1)
			loadFragment(fragment2);
		else
			loadFragment(fragment1);
	}
}
