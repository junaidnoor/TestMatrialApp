package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 8/23/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Junaid on 5/17/2015.
 */
public class JDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "investigation.db";


    public JDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.openOrCreateDatabase(DATABASE_NAME, null);
        db.execSQL(InvestigationQry.SQL_CREATE_USER);
        db.execSQL(InvestigationQry.SQL_CREATE_THEME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(InvestigationQry.SQL_DROP_USER);
        db.execSQL(InvestigationQry.SQL_DROP_THEME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
        //super.onDowngrade(db, oldVersion, newVersion);
    }
}
