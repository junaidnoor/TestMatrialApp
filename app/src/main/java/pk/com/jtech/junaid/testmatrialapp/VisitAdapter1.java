package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Junaid on 10/24/2015.
 */
public class VisitAdapter1 extends RecyclerView.Adapter<MrListRowHolder> {


    private List<VisitDate> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;


    public VisitAdapter1(Context context, List<VisitDate> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_visit, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        VisitDate medi = mrList.get(i);
        //MedicalRecord medi = getItem(position);

        /*Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(mrListRowHolder.thumbnail);
        */
        mrListRowHolder.mr_visit_date.setText(Html.fromHtml(medi.getVist_date()));
        mrListRowHolder.mr_doctor.setText(Html.fromHtml(medi.getDoctor_name()));
        mrListRowHolder.mr_specialty.setText(Html.fromHtml(medi.getSpecialty()));

    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }
}

