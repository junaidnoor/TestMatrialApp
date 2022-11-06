package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Junaid on 10/23/2015.
 */
public class GroupDetailAdapter1 extends RecyclerView.Adapter<MrListRowHolder> {

    private List<GroupDetail> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;

    private List<GroupDetail> mrinfoList = null;
    private ArrayList<GroupDetail> arraylist;

    public GroupDetailAdapter1(Context context, List<GroupDetail> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;

        this.mrinfoList = mrList;
        this.arraylist = new ArrayList<GroupDetail>();
        this.arraylist.addAll(mrinfoList);
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_group_detail, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        GroupDetail medi = mrList.get(i);
        //MedicalRecord medi = getItem(position);

        /*Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(mrListRowHolder.thumbnail);
        */
        mrListRowHolder.mr_test_name.setText(Html.fromHtml(medi.getTest_name()));
        mrListRowHolder.mr_lrs.setText(Html.fromHtml(medi.getLrs_no()));
        mrListRowHolder.mr_entery_time.setText(Html.fromHtml(medi.getEntry_time()));

    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mrinfoList.clear();
        if (charText.length() == 0) {
            mrinfoList.addAll(arraylist);
        } else {
            for (GroupDetail wp : arraylist) {
                if (wp.getTest_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mrinfoList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
