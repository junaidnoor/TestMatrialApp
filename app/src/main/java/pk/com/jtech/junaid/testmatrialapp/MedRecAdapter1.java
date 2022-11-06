package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Junaid on 10/20/2015.
 */
public class MedRecAdapter1 extends RecyclerView.Adapter<MrListRowHolder> {


    private List<MedicalRecord> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;


    public MedRecAdapter1(Context context, List<MedicalRecord> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_medical1, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        MedicalRecord medi = mrList.get(i);
        //MedicalRecord medi = getItem(position);

        mrListRowHolder.mrinfo_title.setText(Html.fromHtml(medi.getTitle()));
        mrListRowHolder.mrinfo_mrdata.setText(Html.fromHtml(medi.getMrdata()));

    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }
}


