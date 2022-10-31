package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class LoginActivity extends Activity {

    EditText mEdituname;
    EditText mEditpass;
    public static String ipa;
    public String checkIn;
    public String myMsg;

    ArrayList<Employee> array_list;
    MrDatasource mMrDatasource;
    Employee mEmployee;
    private String uId;
    private String pas;
    //public static final int NOTIFICATION_ID = 1;
    //private NotificationManager mNotificationManager;
    //NotificationCompat.Builder builder;

    SQLiteDatabase mSQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mMrDatasource = new MrDatasource();
        JDbHelper mJDbHelper = new JDbHelper(this);
        mSQLiteDatabase = mJDbHelper.getWritableDatabase();

        mEdituname = (EditText)findViewById(R.id.username);
        mEditpass = (EditText)findViewById(R.id.password);
        delete();

        //mEditTextName = (EditText)findViewById(R.id.order_editText_name);
        //mEditTextPhone = (EditText)findViewById(R.id.order_editText_phone);
    }

    @Override
    protected void onPause() {
        //new asyncTask_notification().execute();
        super.onPause();
    }

    public void onClick_login(View view)
    {
        if (isValidate()) {
            //delete();
            uId=mEdituname.getText().toString().toUpperCase();
            pas=mEditpass.getText().toString().toLowerCase();
            new asyncTask_isValidate().execute();
        }
    }

    private boolean isValidate()
    {
        if (mEdituname.getText().toString().equalsIgnoreCase(""))
        {
            showMessage("Please give your User Name");
            return false;
        }else if (mEditpass.getText().toString().equalsIgnoreCase(""))
        {
            showMessage("Please give your Password");
            return false;
        }
        return true;
    }
    private void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private class asyncTask_isValidate extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(LoginActivity.this);

        public String isval;
        @Override
        protected void onPreExecute() {
            mProgressDialog.setTitle("Please Wait...");
            mProgressDialog.setMessage("Data is loading");
            mProgressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //isval = httpPost("http://" + ipa + "/isValidate.aspx");
            array_list = mMrDatasource.getEmp(uId, pas, ipa, "A");
            //"+ipa+"
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialog.dismiss();
            mEmployee = array_list.get(0);
            //checkIn = isval.toString();
            isval = mEmployee.getIsvalid();
            //showMessage(isval);
            checkIn = "N";
            //showMessage(checkIn + "  ipa ="+ ipa +" User="+ mEdituname.getText().toString().toUpperCase()+" isval="+isval);
            if(isval.equals("Y"))
            //if(mEmployee.getIsvalid().equals("Y"))
            {

                insert();
                /*
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                MainActivity.uid = mEdituname.getText().toString().toUpperCase();
                MainActivity.pas = mEditpass.getText().toString().toLowerCase();
                MainActivity.ipa = ipa;
                */

                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                MenuActivity.uid = mEdituname.getText().toString().toUpperCase();
                MenuActivity.pas = mEditpass.getText().toString().toLowerCase();
                MenuActivity.ipa = ipa;
                MenuActivity.eName=mEmployee.getEmp_name();
                startActivity(intent);
                finish();

            }
            else
            {
                showMessage("Invalid user name or password...");
            }
            super.onPostExecute(aVoid);
        }
    }

    //------------------------ DataBase Section ----------------------

    public long insert() {
        ContentValues mContentValues = new ContentValues();
        try{

            mContentValues.put(InvestigationQry.ProductEntry.COLUMN_NAME_USER_NAME, mEdituname.getText().toString().toUpperCase());
            mContentValues.put(InvestigationQry.ProductEntry.COLUMN_NAME_E_NAME, mEmployee.getEmp_name());
            //mContentValues.put(InvestigationQry.ProductEntry.COLUMN_NAME_E_NAME, "Junaid");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        long newId = mSQLiteDatabase.insert(InvestigationQry.ProductEntry.TABLE_NAME_USER, null, mContentValues);
        return newId;
    }

    public void delete() {
        //mSQLiteDatabase.delete(ProductContract.ProductEntry.TABLE_NAME, null, null);
        mSQLiteDatabase.execSQL(InvestigationQry.SQL_DELETE_ALL_USER);

    }

    /*private String httpPost(String url) {
        //String url = "http://demo.xorsat.org/xorfood/api/add_order.php";
        String result = "";
        HttpClient mHttpClient = new DefaultHttpClient();
        HttpPost mHttpPost = new HttpPost(url);
        try {
            ArrayList<NameValuePair> mListNameValuePair = new ArrayList<NameValuePair>();
            mListNameValuePair.add(new BasicNameValuePair("Uid", mEdituname.getText().toString().toUpperCase()));
            mListNameValuePair.add(new BasicNameValuePair("pass", mEditpass.getText().toString().toLowerCase()));
            mListNameValuePair.add(new BasicNameValuePair("appid", "0001"));
            mHttpPost.setEntity(new UrlEncodedFormEntity(mListNameValuePair));
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
            result = EntityUtils.toString(mHttpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();

        }
        return result;
    }*/
}
