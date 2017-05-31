package afip.android.prefs;

import afip.android.formation.R;
import afip.android.formation.R.xml;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class ConfigurationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction().replace(android.R.id.content,
				new PreferencesFragment()).commit();
	}
	
	public static class PreferencesFragment extends PreferenceFragment{
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.configuration);
		}
	}

}
