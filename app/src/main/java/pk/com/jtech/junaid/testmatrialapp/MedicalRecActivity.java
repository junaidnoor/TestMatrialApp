package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
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


public class MedicalRecActivity extends AppCompatActivity {

    public static int _male;
    public static int _female;

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String _menu;
    public static MrInfo mrinfo;
    public static VisitDate visitdate;
    //public static MedicalRecord medi;

    ArrayList<MedicalRecord> array_list;
    MrDatasource mMrDatasource;
    //MedRecAdapter mMedRecAdapter;
    //ListView mListView;
    MedRecAdapter1 mMedRecAdapter;
    RecyclerView mListView;
    MedicalRecord mMedicalRecord;
    EditText editsearch;
    Toolbar toolbar;
    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView mbp;
    TextView mdia;
    TextView mtemp;
    TextView mpulse;
    TextView mres;
    TextView toobarTitle;

    private Activity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_rec);
        activity = this;
        setupToolbar();
        init();
        new asyncTask_getData().execute();
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

    private void init() {

        mMrDatasource = new MrDatasource();
        MrDatasource.mrCode = mrinfo.getMr_code();
        MrDatasource.vDate = visitdate.getVist_date();
        //mListView = (ListView) findViewById(R.id.medical_listview);
        mListView = (RecyclerView) findViewById(R.id.medical_listview);
        mListView.setHasFixedSize(true);

        mpat = (TextView) findViewById(R.id.pat_name);
        msdw = (TextView) findViewById(R.id.pat_sdw);
        mage = (TextView) findViewById(R.id.pat_age);
        mbp = (TextView) findViewById(R.id.pat_bp);
        mdia = (TextView) findViewById(R.id.pat_dia);
        mtemp = (TextView) findViewById(R.id.pat_temp);
        mpulse = (TextView) findViewById(R.id.pat_pulse);
        mres = (TextView) findViewById(R.id.pat_res);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);

        mpat.setText(mrinfo.getPat_name()+" [ "+mrinfo.getMr_code()+" ]");
        msdw.setText(mrinfo.getPat_age());
        mage.setText("Visit Date [ "+visitdate.getVist_date()+" ]");
        mbp.setText("[ "+visitdate.getBp_sis()+" ]");
        mdia.setText("[ "+visitdate.getBp_dia()+" ]");
        mtemp.setText("[ "+visitdate.getTemp()+" ]");
        mpulse.setText("[ "+visitdate.getPulse()+" ]");
        mres.setText("[ "+visitdate.getRes_rate()+" ]");
        toobarTitle.setText("Medical Record");

    }

    private void poulate() {
        // Adapter init
        mMedRecAdapter = new MedRecAdapter1(this, array_list,_male,_female);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(mMedRecAdapter);

        this.registerForContextMenu(mListView);

        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(this, mListView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mMedicalRecord = array_list.get(position);

                        InvestigationActivity.uid = uid;
                        InvestigationActivity.pas = pas;
                        InvestigationActivity.ipa = ipa;
                        InvestigationActivity.mrinfo = mrinfo;
                        InvestigationActivity.mrCode = mrinfo.getMr_code();

                        activity.openContextMenu(mListView);

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        mMedicalRecord = array_list.get(position);

                        InvestigationActivity.uid = uid;
                        InvestigationActivity.pas = pas;
                        InvestigationActivity.ipa = ipa;
                        InvestigationActivity.mrinfo = mrinfo;
                        InvestigationActivity.mrCode = mrinfo.getMr_code();

                        activity.openContextMenu(mListView);

                    }
                }));
    }

    private class asyncTask_getData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(MedicalRecActivity.this);

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
                array_list = mMrDatasource.getMedical(uid, pas, ipa, "A");
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
        getMenuInflater().inflate(R.menu.menu_medical_rec, menu);
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
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("View [ " + mrinfo.getMr_code() + " ]");//.setHeaderIcon(R.drawable.cancel4);
        menu.setHeaderIcon(R.drawable.ic_view);
        menu.add(0, v.getId(), 0, "Investigation");
        menu.add(Menu.NONE, 0, 0, "Back");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Back"){
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Investigation"){
            Intent mIntent = new Intent();
            mIntent.setClass(activity, InvestigationActivity.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else {return false;}
        return true;
    }
}
