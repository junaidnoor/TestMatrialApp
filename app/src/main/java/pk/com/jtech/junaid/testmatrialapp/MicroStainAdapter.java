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
 * Created by Junaid on 11/14/2015.
 */
public class MicroStainAdapter extends RecyclerView.Adapter<MrListRowHolder> {

    private List<MicroResultSatin> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;

    private List<MicroResultSatin> mrinfoList = null;
    private ArrayList<MicroResultSatin> arraylist;

    public MicroStainAdapter(Context context, List<MicroResultSatin> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;

        this.mrinfoList = mrList;
        this.arraylist = new ArrayList<MicroResultSatin>();
        this.arraylist.addAll(mrinfoList);
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_micro_result1, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        MicroResultSatin medi = mrList.get(i);

        mrListRowHolder.mr_stain.setText(Html.fromHtml(medi.getStain()));
        mrListRowHolder.mr_stain_value.setText(Html.fromHtml(medi.getStain_value()));

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


