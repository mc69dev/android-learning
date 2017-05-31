package afip.android.sqlite;

import java.util.List;
import java.util.Random;

import afip.android.formation.MainActivity;
import afip.android.formation.R;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MySQLiteActivity extends ListActivity {
	MySQLiteDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_sqlite);

		//Dao
		dao= new MySQLiteDao(this);
		dao.open();

		List<Comment> values= dao.getAll();
		ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
				android.R.layout.simple_list_item_1, values);

		
				
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
				
		Comment comment =(Comment)l.getAdapter().getItem(position);
		//Toast.makeText(this,comment.getId()+" "+comment.getComment(), Toast.LENGTH_SHORT).show();
		showInputDialog(comment);
	}
	protected void showInputDialog(Comment comment) {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.update_database_layout, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(promptView);


		final Comment newcomment= comment;
		final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
		editText.setText(comment.getComment());

		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				
				newcomment.setId(newcomment.getId());
				newcomment.setComment(editText.getText().toString());
				dao.update(newcomment);
				
				ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
				adapter.notifyDataSetChanged();
			}
		})
		.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}
	
	
	
	
	public void dbAction(View view) {
		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
		
		Comment comment = null;
		
		switch (view.getId()) {
			case R.id.add:
				String[] comments = new String[] { "Super!!!", "Cool!!", "Pas Mal!" };
				int nextInt = new Random().nextInt(3);
				// enregistrer le nouveau commentaire dans la base de données
				comment = dao.create(comments[nextInt]);
				adapter.add(comment);
				break;
			case R.id.delete:
				if (getListAdapter().getCount() > 0) {
					comment = (Comment) getListAdapter().getItem(0);
					dao.delete(comment);
					adapter.remove(comment);
				}
				break;
		}
		adapter.notifyDataSetChanged();
	}

}
