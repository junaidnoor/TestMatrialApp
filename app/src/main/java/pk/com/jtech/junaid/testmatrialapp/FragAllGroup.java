package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Junaid on 10/1/2015.
 */
public class FragAllGroup extends Fragment {

    public static int _male;
    public static int _female;

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String mrCode;
    public static String _menu;
    public static MrInfo mrinfo;
    //public static GroupList groupList;

    ArrayList<GroupList> array_list;
    MrDatasource mMrDatasource;
    //GroupAdapter mGroupAdapter;
    //GridView mListView;
    GroupAdapter1 mGroupAdapter;
    RecyclerView mListView;
    //ListView mListView;

    TextView mpat;
    TextView msdw;
    TextView mage;

    private Activity mActivity = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //mrCode="10150077301";
        mActivity=getActivity();
        //View allpat = inflater.inflate(R.layout.frag_all_group, container, false);
        //mListView = (GridView) allpat.findViewById(R.id.grouptlist_listview);

        View allpat = inflater.inflate(R.layout.frag_all_group, container, false);
        mListView = (RecyclerView) allpat.findViewById(R.id.grouptlist_listview);
        mListView.setHasFixedSize(true);
        //View allpat = inflater.inflate(R.layout.all_pat_frag, container, false);
        //mListView = (RecyclerView) allpat.findViewById(R.id.recycler_view);
        init();
        new asyncTask_getData().execute();

        return allpat;
    }
    private void init() {
        MrDatasource.mrCode = mrCode;
        mMrDatasource = new MrDatasource();

    }

    @Override
    public void onAttach(Activity activity) {
        mActivity = activity;
        super.onAttach(activity);
    }

    private void poulate() {
        // Adapter init
        mGroupAdapter = new GroupAdapter1(getActivity(), array_list,_male,_female);
        //mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        int spanCount = 3;
        int spacing = 25;
        boolean includeEdge = false;
        mListView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mListView.setAdapter(mGroupAdapter);

        mListView.addOnItemTouchListener
                (new RecyclerItemClickListener(getActivity(), mListView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        GroupList groupList = array_list.get(position);
                        GroupDetailActivity.uid = uid;
                        GroupDetailActivity.pas = pas;
                        GroupDetailActivity.ipa = ipa;
                        GroupDetailActivity.para = "G";
                        GroupDetailActivity.mrinfo = mrinfo;
                        GroupDetailActivity.groupList = groupList;

                        Intent mIntent = new Intent();
                        mIntent.setClass(mActivity, GroupDetailActivity.class);
                        startActivity(mIntent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        GroupList groupList = array_list.get(position);
                        GroupDetailActivity.uid = uid;
                        GroupDetailActivity.pas = pas;
                        GroupDetailActivity.ipa = ipa;
                        GroupDetailActivity.para = "G";
                        GroupDetailActivity.mrinfo = mrinfo;
                        GroupDetailActivity.groupList = groupList;

                        /*Intent mIntent = new Intent();
                        mIntent.setClass(mActivity, GroupDetailActivity.class);
                        startActivity(mIntent);*/
                    }
                }));

        /*
        //-------------------------- Hiding Action Bar ---------------------------------
        //activity.getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

                if (absListView.getId() == mListView.getId()) {
                    final int currentFirstVisibleItem = mListView.getFirstVisiblePosition();

                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        // getSherlockActivity().getSupportActionBar().hide();
                        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        // getSherlockActivity().getSupportActionBar().show();
                        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }

            }
        });
        //-------------------------- Hiding Action Bar ---------------------------------
        */

        /*
        // --------------------------- Onclick Event -----------------------------------
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GroupList groupList = array_list.get(position);
                GroupDetailActivity.uid = uid;
                GroupDetailActivity.pas = pas;
                GroupDetailActivity.ipa = ipa;
                GroupDetailActivity.para = "G";
                GroupDetailActivity.mrinfo = mrinfo;
                GroupDetailActivity.groupList = groupList;

                Intent mIntent = new Intent();
                mIntent.setClass(mActivity, GroupDetailActivity.class);
                startActivity(mIntent);


            }
        });
        // --------------------------- Onclick Event -----------------------------------
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
                array_list = mMrDatasource.getGroupList(uid, pas,ipa,"A");
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

