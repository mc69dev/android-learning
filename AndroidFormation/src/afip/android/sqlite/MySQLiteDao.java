package afip.android.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MySQLiteDao {

	// Champs de la base de données
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_COMMENT };

	public MySQLiteDao(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	//Tools Mapping
	private Comment cursorToComment(Cursor cursor) {
		Comment comment = new Comment();
		comment.setId(cursor.getLong(0));
		comment.setComment(cursor.getString(1));
		return comment;
	}

	//Opération CRUD (Create, Retreave, Update et Delete)!!
	public List<Comment> getAll(){
		List<Comment> comments = new ArrayList<Comment>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Comment comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		// assurez-vous de la fermeture du curseur
		cursor.close();
		return comments;
	}
	public Comment create(String comment) {
		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.COLUMN_COMMENT, comment);

		long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,values);

		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);

		cursor.moveToFirst();
		Comment newComment = cursorToComment(cursor);
		cursor.close();

		return newComment;
	}
	public void delete(Comment comment) {
		long id = comment.getId();
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
	public long update(Comment comment) {
		ContentValues values = new ContentValues();
		
		values.put(MySQLiteHelper.COLUMN_COMMENT, comment.getComment());
		
		long result =0;
		try {
			result = database.update(MySQLiteHelper.TABLE_COMMENTS, values, MySQLiteHelper.COLUMN_ID+"= ?",	new String[] { String.valueOf(comment.getId())});
		} catch (Exception e) {
			Log.i("mc","===========>"+e);
		}
		
		return result;
	}


}
