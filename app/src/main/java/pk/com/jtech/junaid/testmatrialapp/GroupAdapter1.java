package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Junaid on 10/26/2015.
 */
public class GroupAdapter1 extends RecyclerView.Adapter<MrListRowHolder> {


    private List<GroupList> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;


    public GroupAdapter1(Context context, List<GroupList> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_group, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        GroupList medi = mrList.get(i);
        //MedicalRecord medi = getItem(position);

        /*Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(mrListRowHolder.thumbnail);
        */
        mrListRowHolder.mr_group_imageView.setImageResource(medi.getImgReosurce());
        mrListRowHolder.mr_result.setText(Html.fromHtml(medi.getGroup_name()));
        mrListRowHolder.mr_group_count.setText(Html.fromHtml(medi.getTest_count()));

    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }
}

