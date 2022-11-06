package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
//import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
//import android.text.format.Formatter;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
//import java.util.Arrays;
import java.util.Calendar;


public class HomeActivity extends Activity {

    SQLiteDatabase mSQLiteDatabase;
    JDbHelper mJDbHelper;

    private String ipa;
    EditText one,two,three;
    public String checkIn;
    private String eName;

    //public static boolean isService = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ipa ="172.16.5.137:874";
        mJDbHelper = new JDbHelper(this);
        mSQLiteDatabase = mJDbHelper.getWritableDatabase();

        //mJDbHelper.onUpgrade(mSQLiteDatabase,1,1);
        try {
            new asyncTask_get_user().execute();

            Calendar cur_cal = Calendar.getInstance();
            cur_cal.setTimeInMillis(System.currentTimeMillis());
            cur_cal.add(Calendar.MINUTE, 15);

        } catch (NullPointerException e) {
            throw new IllegalStateException("A book has a null property", e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
/*
    private void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
*/
    //------------------------- Get User Name --------------------------
    private String getUname()
    {
        String uName;
        Cursor mCursor = mSQLiteDatabase.rawQuery(InvestigationQry.SQL_SELECT_ALL_USER, null);
        //showMessage(ipa+" checkIn ="+mCursor);
        if(mCursor.getCount()==0)
        {
            uName ="0";
        }
        else {
            mCursor.moveToFirst();
            do {
                uName = mCursor.getString(mCursor.getColumnIndexOrThrow(InvestigationQry.ProductEntry.COLUMN_NAME_USER_NAME));

            } while (mCursor.moveToNext());
        }
        return uName;
    }

    //------------------------- Get User Name --------------------------
    private String getEmployee()
    {
        String uName;
        Cursor mCursor = mSQLiteDatabase.rawQuery(InvestigationQry.SQL_SELECT_ALL_USER, null);
        //showMessage(ipa+" checkIn ="+mCursor);
        if(mCursor.getCount()==0)
        {
            uName ="Dummy User";
        }
        else {
            mCursor.moveToFirst();
            do {
                uName = mCursor.getString(mCursor.getColumnIndexOrThrow(InvestigationQry.ProductEntry.COLUMN_NAME_E_NAME));

            } while (mCursor.moveToNext());
        }
        return uName;
    }


    // ------------------------------- asyncTask -----------------------------

    private class asyncTask_get_user extends AsyncTask<Void, Void, Void>
    {
        public String isval;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(300);
                isval = getUname();
                eName = getEmployee();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            checkIn = isval.toString();
            //showMessage(ipa+" checkIn ="+checkIn);
            GCMReceiver.user= checkIn;
            GCMReceiver.ipa = ipa;
            //BackgrondSer.user = checkIn;
            if(checkIn.equals("0"))
            {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                LoginActivity.ipa = ipa;
                startActivity(intent);
                finish();
            }
            else
            {
                //Main22Activity
                //Intent intent = new Intent(HomeActivity.this, Main22Activity.class);
                Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
                MenuActivity.uid = checkIn;
                MenuActivity.ipa = ipa;
                MenuActivity.eName = getEmployee();
                startActivity(intent);
                finish();
            }
            super.onPostExecute(aVoid);
        }
    }


    public void apkInstall(){
        boolean installed = appInstalledOrNot("com.samsung.mno.team1.dicomreader");

        if(!installed){
            try {
                installApk();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHanlder(this));
            //ipa ="10.20.10.101:85";
            //ipa ="192.158.1.121:85";
            //ipa ="192.158.1.113:85";
            //ipa ="192.158.1.111:85";
            //ipa ="192.158.1.110:85";
            //ipa ="192.158.1.109:85";
            //ipa ="192.158.1.115:85";

            //ipa ="192.168.210.80:85";
            //ipa ="192.168.2.147:85";
            //ipa ="192.168.1.103:85";
            //ipa ="192.168.1.227:85";
            //ipa ="192.168.0.118:88";
            ipa ="172.16.5.137:874";
            //ipa ="125.209.80.130:6086";



            //ipa ="192.168.2.147:85";
            //ipa = getIPAddress()+":85";
            mJDbHelper = new JDbHelper(this);
            mSQLiteDatabase = mJDbHelper.getWritableDatabase();

            //mJDbHelper.onUpgrade(mSQLiteDatabase,1,1);
            try {
                new asyncTask_get_user().execute();

                Calendar cur_cal = Calendar.getInstance();
                cur_cal.setTimeInMillis(System.currentTimeMillis());
                cur_cal.add(Calendar.MINUTE, 15);

            } catch (NullPointerException e) {
                throw new IllegalStateException("A book has a null property", e);
            }
        }
    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    public void installApk() throws Exception{

        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;

        try {
            in = assetManager.open("com.samsung.mno.team1.dicomreader.apk");
            File apkFile = new File(getPathToDownloads().getPath() + File.separator + "dicomreader.apk");
            apkFile.createNewFile();

            if(apkFile.exists()){
                System.out.println();
            }
            out = new FileOutputStream(apkFile);

            byte[] buffer = new byte[1024];

            int read;
            while((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            in.close();
            in = null;

            out.flush();
            out.close();
            out = null;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(apkFile.getPath())), "application/vnd.android.package-archive");
            startActivityForResult(intent,101);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public File getPathToDownloads(){
        String folderName = "DicomApk";

        File path_to_downloads =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + File.separator + folderName);

        if(!path_to_downloads.isDirectory() && !path_to_downloads.exists()) {
            path_to_downloads.mkdir();
        }
        File storageDir = getExternalFilesDir(path_to_downloads.toString());
        return storageDir;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println();


        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHanlder(this));
        //ipa ="10.20.10.101:85";
        //ipa ="192.158.1.121:85";
        //ipa ="192.158.1.113:85";
        //ipa ="192.158.1.111:85";
        //ipa ="192.158.1.110:85";
        //ipa ="192.158.1.109:85";
        //ipa ="192.158.1.115:85";

        //ipa ="192.168.210.80:85";
        //ipa ="192.168.2.147:85";
        //ipa ="192.168.0.108:85";
        //ipa ="192.168.1.227:85";
        //ipa ="192.168.0.133:88";
        ipa ="172.16.5.137:874";


        //ipa ="192.168.2.147:85";
        //ipa = getIPAddress()+":85";
        mJDbHelper = new JDbHelper(this);
        mSQLiteDatabase = mJDbHelper.getWritableDatabase();

        //mJDbHelper.onUpgrade(mSQLiteDatabase,1,1);
        try {
            new asyncTask_get_user().execute();

            Calendar cur_cal = Calendar.getInstance();
            cur_cal.setTimeInMillis(System.currentTimeMillis());
            cur_cal.add(Calendar.MINUTE, 15);

        } catch (NullPointerException e) {
            throw new IllegalStateException("A book has a null property", e);
        }
    }
}