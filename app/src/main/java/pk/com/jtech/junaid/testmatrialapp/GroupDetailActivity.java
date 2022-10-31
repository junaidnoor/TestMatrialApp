package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Locale;


public class GroupDetailActivity extends ActionBarActivity {

    public static int _male;
    public static int _female;

    // Declaring Your View and Variables
    public static String uid;
    public static String pas;
    public static String ipa;
    public static String para;
    public static String mrCode;
    public static MrInfo mrinfo;
    public static GroupList groupList;
    public static TestList testList;
    public static IRS irsList;

    public static int NOTIFI_ID=0;
    Toolbar toolbar;

    ArrayList<GroupDetail> array_list;
    MrDatasource mMrDatasource;
    //GroupDetailAdapter mGroupDetailAdapter;
    //ListView mListView;
    GroupDetailAdapter1 mGroupDetailAdapter;
    RecyclerView mListView;;
    GroupDetail mGroupDetail;
    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView mtitle;
    TextView toobarTitle;
    EditText editsearch;

    private Activity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        activity = this;
        setupToolbar();
        init();
        new asyncTask_getData().execute();
    }

    private void init() {
        editsearch = (EditText) findViewById(R.id.search);
        mMrDatasource = new MrDatasource();
        MrDatasource.mrCode = mrinfo.getMr_code();

        //mListView = (ListView) findViewById(R.id.group_detail_listview);
        mListView = (RecyclerView) findViewById(R.id.group_detail_listview);
        mListView.setHasFixedSize(true);

        mpat = (TextView) findViewById(R.id.pat_name);
        msdw = (TextView) findViewById(R.id.pat_sdw);
        mage = (TextView) findViewById(R.id.pat_age);
        mtitle = (TextView) findViewById(R.id.activity_title);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);

        mpat.setText(mrinfo.getPat_name() + " [ " + mrinfo.getMr_code() + " ]");
        msdw.setText(mrinfo.getPat_fname());
        mage.setText(mrinfo.getPat_age());
        toobarTitle.setText("Investigation View");

        if (para.equals("G"))
        {
            mtitle.setText(groupList.getGroup_name());
            MrDatasource.gCode = groupList.getGroup_code();
        }
        if (para.equals("T"))
        {
            mtitle.setText(testList.getTest_name());
            MrDatasource.gCode = testList.getTest_code();
        }
        if (para.equals("I"))
        {
            mtitle.setText(irsList.getIrs());
            MrDatasource.gCode = irsList.getIrs();
        }
    }

    private void poulate() {
        // Adapter init
        mGroupDetailAdapter = new GroupDetailAdapter1(this, array_list,_male,_female);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(mGroupDetailAdapter);

        this.registerForContextMenu(mListView);

        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(this, mListView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        new DownloadImage().execute();

                        mGroupDetail = array_list.get(position);
                        //002,003,006,007,004
                        if (mGroupDetail.getGroup_code().equals("001")) {
                            Intent mIntent = new Intent();
                            MicroResultActivity.uid = uid;
                            MicroResultActivity.pas = pas;
                            MicroResultActivity.ipa = ipa;
                            mIntent.setClass(GroupDetailActivity.this, MicroResultActivity.class);
                            MicroResultActivity.mrinfo = mrinfo;
                            MicroResultActivity.mGroupDetail = mGroupDetail;
                            startActivity(mIntent);
                        }
                        if (mGroupDetail.getGroup_code().equals("002") ||
                                mGroupDetail.getGroup_code().equals("003") ||
                                mGroupDetail.getGroup_code().equals("004") ||
                                mGroupDetail.getGroup_code().equals("011") ||
                                mGroupDetail.getGroup_code().equals("007")
                                ) {
                            Intent mIntent = new Intent();
                            NormalResultActivity.uid = uid;
                            NormalResultActivity.pas = pas;
                            NormalResultActivity.ipa = ipa;
                            mIntent.setClass(GroupDetailActivity.this, NormalResultActivity.class);
                            NormalResultActivity.mrinfo = mrinfo;
                            NormalResultActivity.mGroupDetail = mGroupDetail;
                            startActivity(mIntent);
                        }

                        if (mGroupDetail.getGroup_code().equals("008") ||
                                mGroupDetail.getGroup_code().equals("009")) {

                            MedImageActivity.uid = uid;
                            MedImageActivity.pas = pas;
                            MedImageActivity.ipa = ipa;
                            MedImageActivity.mrinfo = mrinfo;
                            MedImageActivity.mGroupDetail = mGroupDetail;
                            //MedImageActivity.mUrl="http://192.168.0.137:88/viewers/mobile/ViewPage.aspx?input=http://192.168.0.137:88/images/cta-100.dcm";
                            MedImageActivity.mUrl = "http://" + ipa + "/DummyDicom.aspx?lrs=" + mGroupDetail.getLrs_no() + "&testCode=" + mGroupDetail.getTest_code();
                            //MedImageActivity.mUrl="http://" + ipa + "/DummyDicom.aspx";

                            //----------------------- New Mediactivity --------------------------//
                            MediOne.uid = uid;
                            MediOne.pas = pas;
                            MediOne.ipa = ipa;
                            MediOne.irs= mGroupDetail.getLrs_no();
                            MediOne.tcode= mGroupDetail.getTest_code();

                            MediOne.mrinfo = mrinfo;
                            MediOne.mGroupDetail = mGroupDetail;
                            MediOne.Url1 = "http://" + ipa + "/DummyDicom2.aspx";

                            //----------------------- New Mediactivity --------------------------//
                            //dicomImage.aspx //DummyDicom
                            //http://192.168.0.137:88/viewers/mobile/ViewPage.aspx?input=http://192.168.0.137:88/images/cta-100.dcm

                            RadiologyResultActivity.uid = uid;
                            RadiologyResultActivity.pas = pas;
                            RadiologyResultActivity.ipa = ipa;
                            RadiologyResultActivity.mrinfo = mrinfo;
                            RadiologyResultActivity.mGroupDetail = mGroupDetail;

                            activity.openContextMenu(mListView);
                        }

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        mGroupDetail = array_list.get(position);

                        if (mGroupDetail.getGroup_code().equals("001")) {
                            Intent mIntent = new Intent();
                            MicroResultActivity.uid = uid;
                            MicroResultActivity.pas = pas;
                            MicroResultActivity.ipa = ipa;
                            mIntent.setClass(GroupDetailActivity.this, MicroResultActivity.class);
                            MicroResultActivity.mrinfo = mrinfo;
                            MicroResultActivity.mGroupDetail = mGroupDetail;
                            startActivity(mIntent);
                        }
                        if (mGroupDetail.getGroup_code().equals("002") ||
                                mGroupDetail.getGroup_code().equals("003") ||
                                mGroupDetail.getGroup_code().equals("004") ||
                                mGroupDetail.getGroup_code().equals("006") ||
                                mGroupDetail.getGroup_code().equals("007")
                                ) {
                            Intent mIntent = new Intent();
                            NormalResultActivity.uid = uid;
                            NormalResultActivity.pas = pas;
                            NormalResultActivity.ipa = ipa;
                            mIntent.setClass(GroupDetailActivity.this, NormalResultActivity.class);
                            NormalResultActivity.mrinfo = mrinfo;
                            NormalResultActivity.mGroupDetail = mGroupDetail;
                            startActivity(mIntent);
                        }

                        if (mGroupDetail.getGroup_code().equals("008") ||
                                mGroupDetail.getGroup_code().equals("009")) {

                            MedImageActivity.uid = uid;
                            MedImageActivity.pas = pas;
                            MedImageActivity.ipa = ipa;
                            MedImageActivity.mrinfo = mrinfo;
                            MedImageActivity.mGroupDetail = mGroupDetail;
                            MedImageActivity.mUrl = "http://" + ipa + "/DummyDicom.aspx?lrs=" + mGroupDetail.getLrs_no() + "&testCode=" + mGroupDetail.getTest_code();
                            //MedImageActivity.mUrl="http://" + ipa + "/DummyDicom.aspx";


                            //----------------------- New Mediactivity --------------------------//
                            MediOne.uid = uid;
                            MediOne.pas = pas;
                            MediOne.ipa = ipa;
                            MediOne.irs= mGroupDetail.getLrs_no();
                            MediOne.tcode= mGroupDetail.getTest_code();

                            MediOne.mrinfo = mrinfo;
                            MediOne.mGroupDetail = mGroupDetail;
                            MediOne.Url1 = "http://" + ipa + "/DummyDicom2.aspx";
                            //----------------------- New Mediactivity --------------------------//


                            RadiologyResultActivity.uid = uid;
                            RadiologyResultActivity.pas = pas;
                            RadiologyResultActivity.ipa = ipa;
                            RadiologyResultActivity.mrinfo = mrinfo;
                            RadiologyResultActivity.mGroupDetail = mGroupDetail;

                            activity.openContextMenu(mListView);
                        }
                    }

                }));
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

        // -------------------- Capture Text in EditText -----------------------------
        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                mGroupDetailAdapter.filter(text);
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
        });
        // -------------------- Capture Text in EditText -----------------------------
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
        ProgressDialog mProgressDialog = new ProgressDialog(GroupDetailActivity.this);

        @Override
        protected void onPreExecute() {
            mProgressDialog.setTitle("Please Wait...");
            mProgressDialog.setMessage("Data is loading");
            mProgressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            array_list = mMrDatasource.getGroupDetail(uid, pas,ipa,para);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        //System.out.println("...on create context menu...");
        String repId=mGroupDetail.getReport_id();
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("View Result");//.setHeaderIcon(R.drawable.cancel4);
        menu.setHeaderIcon(R.drawable.ic_view);

        if (repId.equals("N"))

        {
            menu.add(0, v.getId(), 0, "Image");
            menu.add(Menu.NONE, 0, 0, "Back");
        }
        else {
            menu.add(0, v.getId(), 0, "Report");
            menu.add(0, v.getId(), 0, "Image");
            menu.add(Menu.NONE, 0, 0, "Back");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Back"){
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Report"){
            Intent mIntent = new Intent();
            mIntent.setClass(GroupDetailActivity.this, RadiologyResultActivity.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Image"){
            Intent mIntent = new Intent();
//            mIntent.setClass(GroupDetailActivity.this, MedImageActivity.class);
            mIntent.setClass(GroupDetailActivity.this, MediOne.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else {return false;}
        return true;
    }
}
