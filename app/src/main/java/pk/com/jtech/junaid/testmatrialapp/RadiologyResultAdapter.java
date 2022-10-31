package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junaid on 12/3/2015.
 */
public class RadiologyResultAdapter extends RecyclerView.Adapter<MrListRowHolder> {

    private List<RadiologyResult> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;

    private List<RadiologyResult> mrinfoList = null;
    private ArrayList<RadiologyResult> arraylist;

    public RadiologyResultAdapter(Context context, List<RadiologyResult> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;

        this.mrinfoList = mrList;
        this.arraylist = new ArrayList<RadiologyResult>();
        this.arraylist.addAll(mrinfoList);
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_radiology, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        RadiologyResult medi = mrList.get(i);

        mrListRowHolder.mr_detail.setText(Html.fromHtml(medi.getDetail()));
        mrListRowHolder.mr_conclusion.setText(Html.fromHtml(medi.getConclusion()));
        mrListRowHolder.mr_heading.setText(Html.fromHtml(medi.getHeading()));

    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }

    // Filter Class
    /*public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mrinfoList.clear();
        if (charText.length() == 0) {
            mrinfoList.addAll(arraylist);
        } else {
            for (NormalResult wp : arraylist) {
                if (wp.getParameter().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mrinfoList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }*/
}

