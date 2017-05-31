package afip.android.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_COMMENTS = "comments";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "comment";

	private static final String DATABASE_NAME = "commments.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table "
		      + TABLE_COMMENTS + "(" + 
			  COLUMN_ID   + " integer primary key autoincrement, " + 
		      COLUMN_COMMENT     + " text not null);";
	  
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {	
		database.execSQL(DATABASE_CREATE); //Création Base Données!!!
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldversion, int newversion) {
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
		onCreate(database);
	}

}
