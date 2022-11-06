package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junaid on 11/11/2015.
 */
public class NormalResultAdapter extends RecyclerView.Adapter<MrListRowHolder> {

    private List<NormalResult> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;

    private List<NormalResult> mrinfoList = null;
    private ArrayList<NormalResult> arraylist;

    public NormalResultAdapter(Context context, List<NormalResult> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;

        this.mrinfoList = mrList;
        this.arraylist = new ArrayList<NormalResult>();
        this.arraylist.addAll(mrinfoList);
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_normal_reslult, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        NormalResult medi = mrList.get(i);

        mrListRowHolder.mr_parameter.setText(Html.fromHtml(medi.getParameter()));
        mrListRowHolder.mr_result.setText(Html.fromHtml(medi.getResult()));
        mrListRowHolder.mr_normal_value.setText(Html.fromHtml(medi.getNormal_value()));

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

