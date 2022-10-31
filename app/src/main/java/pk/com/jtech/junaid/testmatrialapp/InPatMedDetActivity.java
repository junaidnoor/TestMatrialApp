package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class InPatMedDetActivity extends AppCompatActivity {

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String _menu;
    public static MrInfo mrinfo;
    public static InPatMedRec inpatmedrec;

    public static int _male = R.drawable.ic_indus_male;
    public static int _female = R.drawable.ic_indus_female;

    ArrayList<InPatMedRecDet> array_list;
    MrDatasource mMrDatasource;
    //VisitAdapter mVisitAdapter;
    //ListView mListView;

    InPatMedRecDet mInPatMedRec;
    InPatMedDetAdapter mInPatMedAdapter;
    RecyclerView mListView;
    EditText editsearch;
    Toolbar toolbar;
    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView toobarTitle;
    TextView mtitle;

    TextView admphy;
    TextView attendoc;
    TextView dutydoc;

    private Activity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpat_meddet);
        activity = this;
        setupToolbar();
        init();
        new asyncTask_getData().execute();
    }

    private void init() {
        mMrDatasource = new MrDatasource();
        MrDatasource.mrCode = mrinfo.getMr_code();
        MrDatasource.admCode = inpatmedrec.getAdm_no();
        mListView = (RecyclerView) findViewById(R.id.visit_listview);
        mListView.setHasFixedSize(true);

        mpat = (TextView) findViewById(R.id.pat_name);
        msdw = (TextView) findViewById(R.id.pat_sdw);
        mage = (TextView) findViewById(R.id.pat_age);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);
        //mtitle = (TextView) findViewById(R.id.activity_title);

        //admphy = (TextView) findViewById(R.id.row_admiting_physician);
        attendoc = (TextView) findViewById(R.id.row_atten_duty_doctor);
        dutydoc = (TextView) findViewById(R.id.row_duty_doctor);

        mpat.setText(mrinfo.getPat_name()+" [ "+mrinfo.getMr_code()+" ]");
        msdw.setText(mrinfo.getPat_fname());
        mage.setText(mrinfo.getPat_age());

        //admphy.setText(inpatmedrec.getAdmiting_physician());
        attendoc.setText(inpatmedrec.getAttending_duty_doctor());
        dutydoc.setText(inpatmedrec.getDuty_doctor());

        toobarTitle.setText("IP-Medcal Record");
        //mtitle.setText("In Patient");
    }

    private void poulate() {
        // Adapter init
        mInPatMedAdapter = new InPatMedDetAdapter(this, array_list,_male,_female);
        mListView.setLayoutManager(new LinearLayoutManager(this));

        mListView.setAdapter(mInPatMedAdapter);
        this.registerForContextMenu(mListView);
        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(this, mListView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mInPatMedRec = array_list.get(position);

                        InvestigationActivity.uid = uid;
                        InvestigationActivity.pas = pas;
                        InvestigationActivity.ipa = ipa;
                        InvestigationActivity.mrinfo = mrinfo;
                        InvestigationActivity.mrCode = mrinfo.getMr_code();

                        /*Intent mIntent = new Intent();
                        InvestigationActivity.uid = uid;
                        InvestigationActivity.pas = pas;
                        InvestigationActivity.ipa = ipa;
                        mIntent.setClass(InPatMedDetActivity.this, InvestigationActivity.class);
                        InvestigationActivity.mrinfo = mrinfo;
                        InvestigationActivity.mrCode = mrinfo.getMr_code();
                        startActivity(mIntent);*/

                        activity.openContextMenu(mListView);

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        mInPatMedRec = array_list.get(position);

                        InvestigationActivity.uid = uid;
                        InvestigationActivity.pas = pas;
                        InvestigationActivity.ipa = ipa;
                        InvestigationActivity.mrinfo = mrinfo;
                        InvestigationActivity.mrCode = mrinfo.getMr_code();

                        activity.openContextMenu(mListView);

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

    }

    private class asyncTask_getData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(InPatMedDetActivity.this);

        @Override
        protected void onPreExecute() {
            mProgressDialog.setTitle("Please Wait...");
            mProgressDialog.setMessage("Data is loading");
            mProgressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            array_list = mMrDatasource.getInPatMedRecDet(uid, pas, ipa, "D");
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
