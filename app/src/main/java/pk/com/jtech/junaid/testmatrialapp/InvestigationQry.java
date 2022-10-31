package pk.com.jtech.junaid.testmatrialapp;

import android.provider.BaseColumns;

/**
 * Created by Junaid on 8/23/2015.
 */
public class InvestigationQry {
    public InvestigationQry() {

    }

    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME_USER = "tbl_user";
        public static final String TABLE_NAME_THEME = "tbl_theme";
        public static final String COLUMN_NAME_USER_NAME = "user_name";
        public static final String COLUMN_NAME_E_NAME = "e_name";
        public static final String COLUMN_NAME_THEME_NAME = "theme_name";
    }

    private static final String TEXT_TYPE = " TEXT ";
    private static final String COMMA_SEP = " , ";

    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + ProductEntry.TABLE_NAME_USER + " (" + ProductEntry._ID
                    + " INTEGER PRIMARY KEY,"
                    + ProductEntry.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP
                    + ProductEntry.COLUMN_NAME_E_NAME + TEXT_TYPE +  ")";

    public static final String SQL_CREATE_THEME =
            "CREATE TABLE " + ProductEntry.TABLE_NAME_THEME + " (" + ProductEntry._ID
                    + " INTEGER PRIMARY KEY," + ProductEntry.COLUMN_NAME_THEME_NAME + TEXT_TYPE + ")";

    public static final String SQL_DROP_USER = "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME_USER;
    public static final String SQL_SELECT_ALL_USER = "SELECT * FROM " + ProductEntry.TABLE_NAME_USER;
    //public static final String SQL_SELECT_ALL_USER = "SELECT " + ProductEntry.COLUMN_NAME_USER_NAME + " FROM " + ProductEntry.TABLE_NAME_USER;
    //public static final String SQL_SELECT_ALL_EMP = "SELECT " + ProductEntry.COLUMN_NAME_E_NAME + " FROM " + ProductEntry.TABLE_NAME_USER;
    public static final String SQL_DELETE_ALL_USER = "DELETE FROM " + ProductEntry.TABLE_NAME_USER;
    public static final String SQL_SELECT_COUNT_USER = "SELECT COUNT(*)Un FROM " + ProductEntry.TABLE_NAME_USER;

    public static final String SQL_DROP_THEME = "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME_THEME;
    public static final String SQL_SELECT_ALL_THEME = "SELECT * FROM " + ProductEntry.TABLE_NAME_THEME;
    public static final String SQL_DELETE_ALL_THENE = "DELETE FROM " + ProductEntry.TABLE_NAME_THEME;
    public static final String SQL_SELECT_COUNT_THEME = "SELECT COUNT(*)Un FROM " + ProductEntry.TABLE_NAME_THEME;
}