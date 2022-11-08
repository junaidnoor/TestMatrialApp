package pk.com.jtech.junaid.testmatrialapp;

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

public class ProcListActivity extends AppCompatActivity {

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String _menu;
    public static MrInfo mrinfo;

    public static int _male = R.drawable.ic_indus_male;
    public static int _female = R.drawable.ic_indus_female;

    ArrayList<ProcedureList> array_list;
    MrDatasource mMrDatasource;
    //VisitAdapter mVisitAdapter;
    //ListView mListView;

    ProcedureList mProcedureList;
    ProcListAdapter mVisitAdapter;
    RecyclerView mListView;
    EditText editsearch;
    Toolbar toolbar;
    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView toobarTitle;
    TextView mtitle;

    private Activity activity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proc_list);

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
        mtitle = (TextView) findViewById(R.id.activity_title);

        mpat.setText(mrinfo.getPat_name()+" [ "+mrinfo.getMr_code()+" ]");
        msdw.setText(mrinfo.getPat_fname());
        mage.setText(mrinfo.getPat_age());
        toobarTitle.setText("Operative Note");
        mtitle.setText("Procedure List");
    }

    private void poulate() {
        // Adapter init
        mVisitAdapter = new ProcListAdapter(this, array_list,_male,_female);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        mListView.setAdapter(mVisitAdapter);

        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(this, mListView,new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        mProcedureList = array_list.get(position);

                        Intent mIntent = new Intent();
                        ProcDetailActivity.uid = uid;
                        ProcDetailActivity.pas = pas;
                        ProcDetailActivity.ipa = ipa;
                        mIntent.setClass(ProcListActivity.this, ProcDetailActivity.class);
                        ProcDetailActivity.mrinfo = mrinfo;
                        ProcDetailActivity.proc_list = mProcedureList;
                        startActivity(mIntent);

                    }

                    @Override
                    public void onItemLongClick(View view, int position)
                    {
                        mProcedureList = array_list.get(position);

                        Intent mIntent = new Intent();
                        ProcDetailActivity.uid = uid;
                        ProcDetailActivity.pas = pas;
                        ProcDetailActivity.ipa = ipa;
                        mIntent.setClass(ProcListActivity.this, ProcDetailActivity.class);
                        ProcDetailActivity.mrinfo = mrinfo;
                        ProcDetailActivity.proc_list = mProcedureList;
                        startActivity(mIntent);
                    }
                }));
        /*
        mListView.addOnItemTouchListener(
                new RecyclerItemClickListener1(this, new RecyclerItemClickListener1.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
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
                })
        );
        */

        /*
        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                mVisitAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });*/

    }

    private class asyncTask_getData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(ProcListActivity.this);

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
                array_list = mMrDatasource.getOpHistory(uid, pas,ipa,"PL");
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
