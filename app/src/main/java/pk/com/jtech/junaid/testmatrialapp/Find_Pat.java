package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Junaid on 9/27/2015.
 */
public class Find_Pat extends Fragment {

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String _menu;
    private String mrCode;

    public static int _male;
    public static int _female;

    ArrayList<MrInfo> array_list;
    MrDatasource mMrDatasource;
    //MrAdapter mMrAdapter;
    //ListView mListView;
    RecyclerView mListView;
    PatRecyclerAdapter mMrAdapter;

    EditText editsearch;
    Button findButton;
    MrInfo mrinfo;
    ImageView erImage;

    private Activity activity = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity=getActivity();
        //frag_find_pat //user_pat_frag

        View findpat = inflater.inflate(R.layout.frag_find_pat, container, false);
        //mListView = (ListView) findpat.findViewById(R.id.find_pat_listview);
        mListView = (RecyclerView) findpat.findViewById(R.id.find_pat_listview);
        mListView.setHasFixedSize(true);

        //View rowabc = inflater.inflate(R.layout.row_abc, ViewGroup, false);
        //lbl_app = (TextView) findViewById
        editsearch = (EditText) findpat.findViewById(R.id.find_pat);
        //findButton = (Button) findpat.findViewById(R.id.btn_find);
        init();
            editsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (editsearch.getText().toString().trim().length()==11) {
                        //int mr = Conveditsearch.getText().toString();
                        MrDatasource.mrCode = editsearch.getText().toString();
                        new asyncTask_getData().execute();
                        editsearch.setText(null);
                    }
                }
            });

        return findpat;
    }
    private void init() {
        mMrDatasource = new MrDatasource();

    }

    private void poulate() {
        // Adapter init
        mMrAdapter = new PatRecyclerAdapter(getActivity(), array_list, _male, _female,0);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(mMrAdapter);

        this.registerForContextMenu(mListView);

        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(getActivity(), mListView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        mrinfo = array_list.get(position);
                        mrCode = mrinfo.getMr_code();
                        //String IdAsString = view.getResources().getResourceName(view.getId());
                        VisitActivity.uid = uid;
                        VisitActivity.pas = pas;
                        VisitActivity.ipa = ipa;
                        VisitActivity.mrinfo = mrinfo;

                        InPatMedActivity.uid = uid;
                        InPatMedActivity.pas = pas;
                        InPatMedActivity.ipa = ipa;
                        InPatMedActivity.mrinfo = mrinfo;

                        InvestigationActivity.uid = uid;
                        InvestigationActivity.pas = pas;
                        InvestigationActivity.ipa = ipa;
                        InvestigationActivity.mrinfo = mrinfo;
                        InvestigationActivity.mrCode = mrCode;

                        ProcListActivity.uid = uid;
                        ProcListActivity.pas = pas;
                        ProcListActivity.ipa = ipa;
                        ProcListActivity.mrinfo = mrinfo;

                        //activity.openContextMenu(mListView);
                        _menu = "";

/*
//                      For Emergency Image
                        if(view != null && view.findViewById(R.id.ll_emergency) != null){
                            view.findViewById(R.id.ll_emergency).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (mrinfo.getEr_cnt().equals("0")) {
                                        showMessage("Emergancy Image");
                                    }
                                    else {
                                        showMessage("Emergancy Record..... Inprocess");
                                    }
                                }
                            });
                        }

//                      For others image
                        if(view != null && view.findViewById(R.id.ll_others) != null){
                            view.findViewById(R.id.ll_others).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showMessage("Others image");
                                }
                            });
                        }
                        */

//                      For Out patient Image
                        if(view != null && view.findViewById(R.id.ll_out_patient) != null){
                            view.findViewById(R.id.ll_out_patient).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //showMessage("Out patient Image");

                                    if(mrinfo.getOpd_cnt().equals("0")){
                                        showMessage("Out patient Image");
                                    }
                                    else {
                                        Intent mIntent = new Intent();
                                        mIntent.setClass(activity, VisitActivity.class);
                                        startActivity(mIntent);
                                    }
                                }
                            });
                        }

//                      For in patient image
                        if(view != null && view.findViewById(R.id.ll_in_patient) != null){
                            view.findViewById(R.id.ll_in_patient).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //showMessage("In patient image");

                                    if(mrinfo.getAdm_cnt().equals("0")){
                                        showMessage("In patient Image");
                                    }
                                    else {
                                        Intent mIntent = new Intent();
                                        mIntent.setClass(activity, InPatMedActivity.class);
                                        startActivity(mIntent);
                                    }
                                }
                            });
                        }

//                      For investigation image
                        if(view != null && view.findViewById(R.id.ll_investigation) != null){
                            view.findViewById(R.id.ll_investigation).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //showMessage("Investigation image");

                                    if(mrinfo.getInvest_cnt().equals("0")){
                                        showMessage("Investigation Image");
                                    }
                                    else {
                                        Intent mIntent = new Intent();
                                        mIntent.setClass(activity, InvestigationActivity.class);
                                        startActivity(mIntent);
                                    }
                                }
                            });
                        }

//                      For procedure image
                        if(view != null && view.findViewById(R.id.ll_procedure) != null){
                            view.findViewById(R.id.ll_procedure).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //showMessage("Procedure image");

                                    if(mrinfo.getProcedure_cnt().equals("0")){
                                        showMessage("Procedure Image");
                                    }
                                    else {
                                        Intent mIntent = new Intent();
                                        mIntent.setClass(activity, ProcListActivity.class);
                                        startActivity(mIntent);
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        mrinfo = array_list.get(position);
                        mrCode = mrinfo.getMr_code();

                        VisitActivity.uid = uid;
                        VisitActivity.pas = pas;
                        VisitActivity.ipa = ipa;
                        VisitActivity.mrinfo = mrinfo;

                        InPatMedActivity.uid = uid;
                        InPatMedActivity.pas = pas;
                        InPatMedActivity.ipa = ipa;
                        InPatMedActivity.mrinfo = mrinfo;

                        InvestigationActivity.uid = uid;
                        InvestigationActivity.pas = pas;
                        InvestigationActivity.ipa = ipa;
                        InvestigationActivity.mrinfo = mrinfo;
                        InvestigationActivity.mrCode = mrCode;

                        ProcListActivity.uid = uid;
                        ProcListActivity.pas = pas;
                        ProcListActivity.ipa = ipa;
                        ProcListActivity.mrinfo = mrinfo;

                        //activity.openContextMenu(mListView);
                        _menu = "";
/*
//                      For Emergency Image
                        if(view != null && view.findViewById(R.id.ll_emergency) != null){
                            view.findViewById(R.id.ll_emergency).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (mrinfo.getEr_cnt().equals("0")) {
                                        showMessage("Emergancy Image");
                                    }
                                    else {
                                        showMessage("Emergancy Record..... Inprocess");
                                    }
                                }
                            });
                        }

//                      For others image
                        if(view != null && view.findViewById(R.id.ll_others) != null){
                            view.findViewById(R.id.ll_others).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showMessage("Others image");
                                }
                            });
                        }
                        */

//                      For Out patient Image
                        if(view != null && view.findViewById(R.id.ll_out_patient) != null){
                            view.findViewById(R.id.ll_out_patient).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //showMessage("Out patient Image");

                                    if(mrinfo.getOpd_cnt().equals("0")){
                                        showMessage("Out patient Image");
                                    }
                                    else {
                                        Intent mIntent = new Intent();
                                        mIntent.setClass(activity, VisitActivity.class);
                                        startActivity(mIntent);
                                    }
                                }
                            });
                        }

//                      For in patient image
                        if(view != null && view.findViewById(R.id.ll_in_patient) != null){
                            view.findViewById(R.id.ll_in_patient).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //showMessage("In patient image");

                                    if(mrinfo.getAdm_cnt().equals("0")){
                                        showMessage("In patient Image");
                                    }
                                    else {
                                        Intent mIntent = new Intent();
                                        mIntent.setClass(activity, InPatMedActivity.class);
                                        startActivity(mIntent);
                                    }
                                }
                            });
                        }

//                      For investigation image
                        if(view != null && view.findViewById(R.id.ll_investigation) != null){
                            view.findViewById(R.id.ll_investigation).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //showMessage("Investigation image");

                                    if(mrinfo.getInvest_cnt().equals("0")){
                                        showMessage("Investigation Image");
                                    }
                                    else {
                                        Intent mIntent = new Intent();
                                        mIntent.setClass(activity, InvestigationActivity.class);
                                        startActivity(mIntent);
                                    }
                                }
                            });
                        }

//                      For procedure image
                        if(view != null && view.findViewById(R.id.ll_procedure) != null){
                            view.findViewById(R.id.ll_procedure).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //showMessage("Procedure image");

                                    if(mrinfo.getProcedure_cnt().equals("0")){
                                        showMessage("Procedure Image");
                                    }
                                    else {
                                        Intent mIntent = new Intent();
                                        mIntent.setClass(activity, ProcListActivity.class);
                                        startActivity(mIntent);
                                    }
                                }
                            });
                        }
                    }

                }));

        /*
        //------------------------ Simple Click -----------------------------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mrinfo = array_list.get(position);
                mrCode = mrinfo.getMr_code();

                VisitActivity.uid = uid;
                VisitActivity.pas = pas;
                VisitActivity.ipa = ipa;
                VisitActivity.mrinfo = mrinfo;

                InvestigationActivity.uid = uid;
                InvestigationActivity.pas = pas;
                InvestigationActivity.ipa = ipa;
                InvestigationActivity.mrinfo = mrinfo;
                InvestigationActivity.mrCode = mrCode;

                activity.openContextMenu(mListView);
                _menu = "";

            }
        });
        //------------------------ Simple Click -----------------------------
        */

    }

    private class asyncTask_getData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            //mProgressDialog.setTitle("Please Wait...");
            //mProgressDialog.setMessage("Data is loading");
            //mProgressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                array_list = mMrDatasource.getList(uid,pas,ipa,"S");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            //showMessage("uid "+uid +"  ipa ="+ipa+"  text ="+editsearch.getText().toString());
            mProgressDialog.dismiss();
            poulate();
            super.onPostExecute(aVoid);
        }
    }

    private void showMessage(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        //System.out.println("...on create context menu...");
        String preAdm=mrinfo.getPre_admission();
        String preOp=mrinfo.getPre_op_note();

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("View [ " + mrCode + " ]");//.setHeaderIcon(R.drawable.cancel4);
        menu.setHeaderIcon(R.drawable.ic_view);
        if(preAdm.substring(0, 1).equals("Y"))
        {
            menu.add(0, v.getId(), 0, "Medical Record (IP)");
        }
        menu.add(0, v.getId(), 0, "Medical Record (OP)");
        menu.add(0, v.getId(), 0, "Investigation");
        if(preOp.substring(0, 1).equals("Y"))
        {
            menu.add(0, v.getId(), 0, "Opreative Note");
        }
        menu.add(Menu.NONE, 0, 0, "Back");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Yes"){

            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Back"){
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Medical Record (OP)"){
            Intent mIntent = new Intent();
            mIntent.setClass(activity, VisitActivity.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Medical Record (IP)"){
            Intent mIntent = new Intent();
            mIntent.setClass(activity, InPatMedActivity.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Investigation"){
            Intent mIntent = new Intent();
            mIntent.setClass(activity, InvestigationActivity.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Opreative Note"){
            Intent mIntent = new Intent();
            mIntent.setClass(activity, ProcListActivity.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else {return false;}
        return true;
    }
}

/*
public class Find_Pat extends Fragment {

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String _menu;
    private String mrCode;

    public static int _male;
    public static int _female;

    ArrayList<MrInfo> array_list;
    MrDatasource mMrDatasource;
    MrAdapter mMrAdapter;
    ListView mListView;
    EditText editsearch;
    Button findButton;

    private Activity activity = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity=getActivity();
        View findpat = inflater.inflate(R.layout.frag_find_pat, container, false);
        mListView = (ListView) findpat.findViewById(R.id.find_pat_listview);
        editsearch = (EditText) findpat.findViewById(R.id.find_pat);
        //findButton = (Button) findpat.findViewById(R.id.btn_find);
        init();
            editsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (editsearch.getText().toString().trim().length()==11) {
                        //int mr = Conveditsearch.getText().toString();
                        MrDatasource.mrCode = editsearch.getText().toString();
                        new asyncTask_getData().execute();
                        editsearch.setText(null);
                    }
                }
            });

        return findpat;
    }
    private void init() {
        mMrDatasource = new MrDatasource();

    }

    private void poulate() {
        // Adapter init
        mMrAdapter = new MrAdapter(getActivity(), array_list, _male, _female);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(mMrAdapter);

        this.registerForContextMenu(mListView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MrInfo mrinfo = array_list.get(position);
                mrCode = mrinfo.getMr_code();

                VisitActivity.uid = uid;
                VisitActivity.pas = pas;
                VisitActivity.ipa = ipa;
                VisitActivity.mrinfo = mrinfo;

                InvestigationActivity.uid = uid;
                InvestigationActivity.pas = pas;
                InvestigationActivity.ipa = ipa;
                InvestigationActivity.mrinfo = mrinfo;
                InvestigationActivity.mrCode = mrCode;

                activity.openContextMenu(mListView);
                _menu = "";

            }
        });

    }

    private class asyncTask_getData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            mProgressDialog.setTitle("Please Wait...");
            mProgressDialog.setMessage("Data is loading");
            mProgressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            array_list = mMrDatasource.getList(uid,pas,ipa,"S");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            //showMessage(editsearch.getText().toString());
            mProgressDialog.dismiss();
            poulate();
            super.onPostExecute(aVoid);
        }
    }

    private void showMessage(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        //System.out.println("...on create context menu...");
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("View [ "+ mrCode+" ]");//.setHeaderIcon(R.drawable.cancel4);
        menu.setHeaderIcon(R.drawable.ic_view);
        menu.add(0, v.getId(), 0, "Medical Record");
        menu.add(0, v.getId(), 0, "Investigation");
        menu.add(0, v.getId(), 0, "Opreative Note");
        menu.add(Menu.NONE, 0, 0, "Back");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Yes"){

            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Back"){
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Medical Record"){
            Intent mIntent = new Intent();
            mIntent.setClass(activity, VisitActivity.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Investigation"){
            Intent mIntent = new Intent();
            mIntent.setClass(activity, InvestigationActivity.class);
            startActivity(mIntent);
            activity.closeContextMenu();
        }
        else if(item.getTitle()=="Opreative Note"){
            activity.closeContextMenu();
        }
        else {return false;}
        return true;
    }
}
*/