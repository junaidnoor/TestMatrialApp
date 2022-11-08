package pk.com.jtech.junaid.testmatrialapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class NormalResultActivity extends AppCompatActivity {

    public static int _male;
    public static int _female;

    // Declaring Your View and Variables
    public static String uid;
    public static String pas;
    public static String ipa;
    public static String para;
    public static MrInfo mrinfo;
    public static GroupList groupList;
    public static TestList testList;
    public static IRS irsList;
    public static GroupDetail  mGroupDetail;

    public static int NOTIFI_ID=0;
    Toolbar toolbar;

    ArrayList<NormalResult> array_list;
    MrDatasource mMrDatasource;
    //GroupDetailAdapter mGroupDetailAdapter;
    //ListView mListView;
    NormalResultAdapter mNormalResultAdapter;
    RecyclerView mListView;;

    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView mirs;
    TextView mtest;
    TextView medate;
    TextView madmo_no;
    TextView toobarTitle;
    //EditText editsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_result);
        setupToolbar();
        init();
        new asyncTask_getData().execute();
    }

    private void init() {
        //editsearch = (EditText) findViewById(R.id.search);
        mMrDatasource = new MrDatasource();
        MrDatasource.mrCode = mrinfo.getMr_code();
        MrDatasource.gCode = mGroupDetail.getLrs_no();
        MrDatasource.tCode = mGroupDetail.getTest_code();
        //mListView = (ListView) findViewById(R.id.group_detail_listview);
        mListView = (RecyclerView) findViewById(R.id.visit_listview);
        mListView.setHasFixedSize(true);

        mpat = (TextView) findViewById(R.id.pat_name);
        msdw = (TextView) findViewById(R.id.pat_sdw);
        mage = (TextView) findViewById(R.id.pat_age);

        mirs = (TextView) findViewById(R.id.txt_irs);
        mtest = (TextView) findViewById(R.id.txt_test);
        medate = (TextView) findViewById(R.id.txt_edate);
        madmo_no = (TextView) findViewById(R.id.txt_adm_no);
        //mtitle = (TextView) findViewById(R.id.activity_title);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);

        mpat.setText(mrinfo.getPat_name() + "  [  " + mrinfo.getMr_code() + "  ]");
        msdw.setText(mrinfo.getPat_fname());
        mage.setText(mrinfo.getPat_age());
        toobarTitle.setText("Result  [  "+mGroupDetail.getGroup_name()+"  ]");
        mirs.setText(mGroupDetail.getLrs_no());
        mtest.setText(mGroupDetail.getTest_name());

        medate.setText(mGroupDetail.getEntry_time());
        madmo_no.setText(mGroupDetail.getAdm_no());

    }

    private void poulate() {
        // Adapter init
        mNormalResultAdapter = new NormalResultAdapter(this, array_list,_male,_female);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(mNormalResultAdapter);


        /*
        // ----------------- Simple List view -----------------------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GroupDetail groupDetail = array_list.get(position);

            }
        });
        // ----------------- Simple List view -----------------------
        */

    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            toolbar.setTitle("Investigation View");
        setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }



    private class asyncTask_getData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(NormalResultActivity.this);

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
                array_list = mMrDatasource.getNormalResult(uid, pas, ipa, "N");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_detail, menu);
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
