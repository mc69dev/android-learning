package afip.android.web;

import afip.android.formation.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	WebView webview;
	
	//<head><script> function test() {alert("Un Toast (Alert) créé en Javascript!");}</script></head>
	String WEB_PAGE_CONTENT="<body style='background-color:#FEDDBA;font-weight:bolder;'>Ma Page Web (Tests)<br/><input type='button' value='Cliquez!(js)' onClick=\"alert('Un toast cr&eacute;&eacute; en Javascript!')\" /></body>";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_2_layout);
		
		/*
		WebView wv=(WebView)findViewById(R.id.web_view);
		wv.loadUrl("http://www.afip-formations.com");*/
		
		
		
		webview=(WebView)findViewById(R.id.web_view);
		/*
		 * paramétrage
		*/
		WebSettings settings = webview.getSettings();
		settings.setBuiltInZoomControls(true); //activer zoom
		settings.setJavaScriptEnabled(true);	//..javascript
				
		webview.setWebViewClient(new WebViewClient());
		webview.setWebChromeClient(new WebChromeClient());
 
		/*
		 * Choix site..
		 */
		Spinner combo =(Spinner)findViewById(R.id.combo_sites);
		combo.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {

				Toast.makeText(parent.getContext(),	"OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
				switch (position) {
					case 0:
						webview.loadUrl("http://www.google.fr");
						break;
					case 1:
						webview.loadUrl("http://www.yahoo.fr");
						break;
					case 2:
						webview.loadUrl("http://www.afip-formations.com");
						break;	
					default:
						//webview.loadData(WEB_PAGE_CONTENT,"text/html", "utf-8");
						webview.loadUrl("file:///android_asset/page_tests.html");
						//webview.loadUrl("file:///android_asset/pract_recommend_section1_pic2.png");
						break;
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}

		});

	}

	/*
	 * Gestion du bouton retour!
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) { 
			//Si bouton retour simuler le comportement du bouton retour d’un navigateur web
			webview.goBack(); 
			return true; 
		} 
		return super.onKeyDown(keyCode, event); 
	}


	
}
