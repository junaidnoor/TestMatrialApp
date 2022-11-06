package pk.com.jtech.junaid.testmatrialapp;


//import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Junaid on 9/19/2015.
 */
public class All_Pat extends Fragment {

    public static int _male;
    public static int _female;

    public static String uid;
    public static String pas;
    public static String ipa;
    private String mrCode;
    EditText editsearch;

    //ArrayList<MrInfo> array_list;
    private List<MrInfo> array_list = new ArrayList<MrInfo>();
    MrDatasource mMrDatasource;
    //ListView mListView;

    RecyclerView mRecyclerView;

    PatRecyclerAdapter mMrAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View allpat = inflater.inflate(R.layout.all_pat_frag, container, false);
        //((TextView)allpat.findViewById(R.id.textView)).setText("All Patient");
        mRecyclerView = (RecyclerView) allpat.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        init();
        new asyncTask_getData().execute();
        return allpat;
    }
    private void init() {
        mMrDatasource = new MrDatasource();
    }

    private void poulate() {
        // Adapter init
        mMrAdapter = new PatRecyclerAdapter(getActivity(), array_list,_male,_female,0);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), R.drawable.divider);
        //mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(mMrAdapter);

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
            try {
                array_list = mMrDatasource.getList("","","","");
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
}