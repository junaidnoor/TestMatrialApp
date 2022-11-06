package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Junaid on 11/7/2015.
 */
public class InPatMedDetAdapter extends RecyclerView.Adapter<MrListRowHolder> {


    private List<InPatMedRecDet> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;


    public InPatMedDetAdapter(Context context, List<InPatMedRecDet> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_inpat_medcal, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        InPatMedRecDet medi = mrList.get(i);
        //MedicalRecord medi = getItem(position);

        /*Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(mrListRowHolder.thumbnail);
        */
        mrListRowHolder.mrinfo_title.setText(Html.fromHtml(medi.getTitle()));
        mrListRowHolder.mrinfo_mrdata.setText(Html.fromHtml(medi.getMrdata()));
        mrListRowHolder.mrinfo_mrdata2.setText(Html.fromHtml(medi.getMrdata2()));
        mrListRowHolder.mrinfo_mrdata3.setText(Html.fromHtml(medi.getMrdata3()));
    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }
}
