package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;

public class MicroResultActivity extends AppCompatActivity {

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

    MrDatasource mMrDatasource;

    ArrayList<MicroResultSatin> array_list_stain;
    ArrayList<MicroResultOrg> array_list_org;

    MicroStainAdapter mMicroStainAdapter;
    RecyclerView mListViewStain;;

    MicroOrgAdapter mMicroOrgAdapter;
    RecyclerView mListViewOrg;;

    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView mirs;
    TextView mtest;
    TextView medate;
    TextView madmo_no;
    TextView toobarTitle;

    TextView morg1;
    TextView morg2;
    TextView morg3;
    //EditText editsearch;

    private Activity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        activity = this;
        setupToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, mGroupDetail.getRemarks()
                        , Snackbar.LENGTH_LONG)
                        .setAction("Remarks", null).show();
            }
        });
        init();
        new asyncTask_getData().execute();
    }

    private void init() {
        //editsearch = (EditText) findViewById(R.id.search);
        mMrDatasource = new MrDatasource();
        MrDatasource.mrCode = mrinfo.getMr_code();
        MrDatasource.gCode = mGroupDetail.getLrs_no();
        MrDatasource.tCode = mGroupDetail.getTest_code();

        mListViewStain = (RecyclerView) findViewById(R.id.zn_stain);
        mListViewStain.setHasFixedSize(true);

        mListViewOrg = (RecyclerView) findViewById(R.id.org_listview);
        mListViewOrg.setHasFixedSize(true);

        mpat = (TextView) findViewById(R.id.pat_name);
        msdw = (TextView) findViewById(R.id.pat_sdw);
        mage = (TextView) findViewById(R.id.pat_age);

        mirs = (TextView) findViewById(R.id.txt_irs);
        mtest = (TextView) findViewById(R.id.txt_test);
        medate = (TextView) findViewById(R.id.txt_edate);
        madmo_no = (TextView) findViewById(R.id.txt_adm_no);
        //mtitle = (TextView) findViewById(R.id.activity_title);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);

        morg1 = (TextView) findViewById(R.id.lbl_org1);
        morg2 = (TextView) findViewById(R.id.lbl_org2);
        morg3 = (TextView) findViewById(R.id.lbl_org3);

        mpat.setText(mrinfo.getPat_name() + "  [  " + mrinfo.getMr_code() + "  ]");
        msdw.setText(mrinfo.getPat_fname());
        mage.setText(mrinfo.getPat_age());
        toobarTitle.setText("Result  [  "+mGroupDetail.getGroup_name()+"  ]");
        mirs.setText(mGroupDetail.getLrs_no());
        mtest.setText(mGroupDetail.getTest_name());

        medate.setText(mGroupDetail.getEntry_time());
        madmo_no.setText(mGroupDetail.getAdm_no());

        morg1.setText(mGroupDetail.getOrg1());
    }

    private void poulate() {
        // Adapter init
        mMicroStainAdapter = new MicroStainAdapter(this, array_list_stain,_male,_female);
        mListViewStain.setLayoutManager(new LinearLayoutManager(this));
        mListViewStain.setAdapter(mMicroStainAdapter);

        mMicroOrgAdapter = new MicroOrgAdapter(this, array_list_org,_male,_female);
        mListViewOrg.setLayoutManager(new LinearLayoutManager(this));
        mListViewOrg.setAdapter(mMicroOrgAdapter);

        this.registerForContextMenu(mListViewOrg);


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
        ProgressDialog mProgressDialog = new ProgressDialog(MicroResultActivity.this);

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
                array_list_stain = mMrDatasource.getMicroResultStain(uid, pas, ipa, "Z");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                array_list_org = mMrDatasource.getMicroResultOrg(uid, pas, ipa, "O");
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
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        //System.out.println("...on create context menu...");
        String a = mGroupDetail.getRemarks();
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Remarks");//.setHeaderIcon(R.drawable.cancel4);
        menu.setHeaderIcon(R.drawable.ic_view);
        menu.add(0, v.getId(), 0, a);
        menu.add(Menu.NONE, 0, 0, "Back");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Back"){
            activity.closeContextMenu();
        }
        else {return false;}
        return true;
    }

    public void onClick_org(View view)
    {
        activity.openContextMenu(mListViewOrg);
    }
}
