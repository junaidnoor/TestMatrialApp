package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Junaid on 10/1/2015.
 */
public class FragAllTest extends Fragment {

    public static int _male;
    public static int _female;

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String mrCode;
    public static String _menu;
    public static MrInfo mrinfo;
    //public static GroupList groupList;

    ArrayList<TestList> array_list;
    MrDatasource mMrDatasource;
    //TestAdapter mTestAdapter;
    //GridView mListView;
    TestAdapter1 mTestAdapter;
    RecyclerView mListView;
    EditText editsearch;

    private Activity mActivity = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mActivity=getActivity();
        View allpat = inflater.inflate(R.layout.frag_all_test, container, false);
        mListView = (RecyclerView) allpat.findViewById(R.id.testlist_listview);
        mListView.setHasFixedSize(true);
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
        mTestAdapter = new TestAdapter1(getActivity(), array_list,_male,_female);
        mListView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        int spanCount = 3;
        int spacing = 25;
        boolean includeEdge = false;
        mListView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mListView.setAdapter(mTestAdapter);

        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(getActivity(), mListView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        TestList testList = array_list.get(position);
                        GroupDetailActivity.uid = uid;
                        GroupDetailActivity.pas = pas;
                        GroupDetailActivity.ipa = ipa;
                        GroupDetailActivity.para = "T";
                        GroupDetailActivity.mrinfo = mrinfo;
                        GroupDetailActivity.testList = testList;

                        Intent mIntent = new Intent();
                        mIntent.setClass(mActivity, GroupDetailActivity.class);
                        startActivity(mIntent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        TestList testList = array_list.get(position);
                        GroupDetailActivity.uid = uid;
                        GroupDetailActivity.pas = pas;
                        GroupDetailActivity.ipa = ipa;
                        GroupDetailActivity.para = "T";
                        GroupDetailActivity.mrinfo = mrinfo;
                        GroupDetailActivity.testList = testList;

                        /*Intent mIntent = new Intent();
                        mIntent.setClass(mActivity, GroupDetailActivity.class);
                        startActivity(mIntent);*/
                    }
                }));


        /*
        //----------------------- Simple Cilick -----------------------------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TestList testList = array_list.get(position);
                GroupDetailActivity.uid = uid;
                GroupDetailActivity.pas = pas;
                GroupDetailActivity.ipa = ipa;
                GroupDetailActivity.para = "T";
                GroupDetailActivity.mrinfo = mrinfo;
                GroupDetailActivity.testList = testList;

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
            mProgressDialog.setTitle("Please Wait...");
            mProgressDialog.setMessage("Data is loading");
            mProgressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            array_list = mMrDatasource.getTestList(uid, pas, ipa,"A");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialog.dismiss();
            poulate();
            super.onPostExecute(aVoid);
        }
    }


}