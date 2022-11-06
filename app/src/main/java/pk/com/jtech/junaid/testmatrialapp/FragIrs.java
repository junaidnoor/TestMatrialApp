package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Junaid on 10/4/2015.
 */
public class FragIrs extends Fragment {

    public static int _male;
    public static int _female;

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String mrCode;
    public static String _menu;
    public static MrInfo mrinfo;
    //public static GroupList groupList;

    ArrayList<IRS> array_list;
    MrDatasource mMrDatasource;
    //IRSAdapter mIRSAdapter;
    //GridView mListView;
    IRSAdapter1 mIRSAdapter;
    RecyclerView mListView;

    private Activity mActivity = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //mrCode="10150077301";
        mActivity=getActivity();
        View allpat = inflater.inflate(R.layout.frag_irs, container, false);
        mListView = (RecyclerView) allpat.findViewById(R.id.irs_listview);
        init();
        new asyncTask_getData().execute();

        return allpat;
    }

    @Override
    public void onAttach(Activity activity) {
        mActivity = activity;
        super.onAttach(activity);
    }

    private void init() {
        MrDatasource.mrCode = mrCode;
        mMrDatasource = new MrDatasource();

    }

    private void poulate() {
        // Adapter init
        mIRSAdapter = new IRSAdapter1(getActivity(), array_list,_male,_female);
        mListView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        int spanCount = 3;
        int spacing = 25;
        boolean includeEdge = false;
        mListView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mListView.setAdapter(mIRSAdapter);

        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(getActivity(), mListView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        IRS irsList = array_list.get(position);
                        GroupDetailActivity.uid = uid;
                        GroupDetailActivity.pas = pas;
                        GroupDetailActivity.ipa = ipa;
                        GroupDetailActivity.para = "I";
                        GroupDetailActivity.mrinfo = mrinfo;
                        GroupDetailActivity.irsList = irsList;

                        Intent mIntent = new Intent();
                        mIntent.setClass(mActivity, GroupDetailActivity.class);
                        startActivity(mIntent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        IRS irsList = array_list.get(position);
                        GroupDetailActivity.uid = uid;
                        GroupDetailActivity.pas = pas;
                        GroupDetailActivity.ipa = ipa;
                        GroupDetailActivity.para = "I";
                        GroupDetailActivity.mrinfo = mrinfo;
                        GroupDetailActivity.irsList = irsList;

                        //Intent mIntent = new Intent();
                        //mIntent.setClass(mActivity, GroupDetailActivity.class);
                        //startActivity(mIntent);
                    }
                }));


        /*
        //----------------------- Simple Cilick -----------------------------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                IRS irsList = array_list.get(position);
                GroupDetailActivity.uid = uid;
                GroupDetailActivity.pas = pas;
                GroupDetailActivity.ipa = ipa;
                GroupDetailActivity.para = "I";
                GroupDetailActivity.mrinfo = mrinfo;
                GroupDetailActivity.irsList = irsList;

                Intent mIntent = new Intent();
                mIntent.setClass(mActivity, GroupDetailActivity.class);
                startActivity(mIntent);

            }
        });
        //----------------------- Simple Cilick -----------------------------
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
                array_list = mMrDatasource.getIRSList(uid, pas, ipa, "A");
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

    private void showMessage(String message)
    {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }
}
