package pk.com.jtech.junaid.testmatrialapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;



public class VisitActivity extends AppCompatActivity {

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String _menu;
    public static MrInfo mrinfo;
    public static VisitDate visitdate;

    public static int _male = R.drawable.ic_indus_male;
    public static int _female = R.drawable.ic_indus_female;

    ArrayList<VisitDate> array_list;
    MrDatasource mMrDatasource;
    //VisitAdapter mVisitAdapter;
    //ListView mListView;

    VisitAdapter1 mVisitAdapter;
    RecyclerView mListView;
    EditText editsearch;
    Toolbar toolbar;
    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView toobarTitle;

    private Activity activity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);

        setupToolbar();
        init();
        new asyncTask_getData().execute();
    }

    private void init() {
        mMrDatasource = new MrDatasource();
        MrDatasource.mrCode = mrinfo.getMr_code();
        //mListView = (ListView) findViewById(R.id.visit_listview);
        mListView = (RecyclerView) findViewById(R.id.visit_listview);
        mListView.setHasFixedSize(true);

        mpat = (TextView) findViewById(R.id.pat_name);
        msdw = (TextView) findViewById(R.id.pat_sdw);
        mage = (TextView) findViewById(R.id.pat_age);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);

        mpat.setText(mrinfo.getPat_name()+" [ "+mrinfo.getMr_code()+" ]");
        msdw.setText(mrinfo.getPat_fname());
        mage.setText(mrinfo.getPat_age());
        toobarTitle.setText("Medical Record");
    }

    private void poulate() {
        // Adapter init
        mVisitAdapter = new VisitAdapter1(this, array_list,_male,_female);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, R.drawable.divider);
        //mListView.addItemDecoration(itemDecoration);
        mListView.setAdapter(mVisitAdapter);

        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(this, mListView,new RecyclerItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                visitdate = array_list.get(position);

                Intent mIntent = new Intent();
                MedicalRecActivity.uid = uid;
                MedicalRecActivity.pas = pas;
                MedicalRecActivity.ipa = ipa;
                mIntent.setClass(VisitActivity.this, MedicalRecActivity.class);
                MedicalRecActivity.mrinfo = mrinfo;
                MedicalRecActivity.visitdate = visitdate;
                startActivity(mIntent);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                visitdate = array_list.get(position);

                Intent mIntent = new Intent();
                MedicalRecActivity.uid = uid;
                MedicalRecActivity.pas = pas;
                MedicalRecActivity.ipa = ipa;
                mIntent.setClass(VisitActivity.this, MedicalRecActivity.class);
                MedicalRecActivity.mrinfo = mrinfo;
                MedicalRecActivity.visitdate = visitdate;
                startActivity(mIntent);
            }
        }));

    }

    private class asyncTask_getData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(VisitActivity.this);

        @Override
        protected void onPreExecute() {
            mProgressDialog.setTitle("Please Wait...");
            mProgressDialog.setMessage("Data is loading");
            mProgressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                array_list = mMrDatasource.getVisit(uid, pas,ipa,"A");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialog.dismiss();
            poulate();
            super.onPostExecute(aVoid);
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            toolbar.setTitle("Medical Record");
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
