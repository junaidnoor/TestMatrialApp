package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Junaid on 11/4/2015.
 */
public class InPatMedAdapter extends RecyclerView.Adapter<MrListRowHolder> {


    private List<InPatMedRec> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;


    public InPatMedAdapter(Context context, List<InPatMedRec> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_inpat, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        InPatMedRec medi = mrList.get(i);


        mrListRowHolder.mr_adm_no.setText(Html.fromHtml(medi.getAdm_no()));
        mrListRowHolder.mr_adm_date.setText(Html.fromHtml(medi.getAdm_date()));
        mrListRowHolder.mr_admiting_physician.setText(Html.fromHtml(medi.getAdmiting_physician()));
        mrListRowHolder.mr_specialty.setText(Html.fromHtml(medi.getSpecialty()));
        mrListRowHolder.mr_fianal_diag.setText(Html.fromHtml(medi.getFianal_diag()));
        mrListRowHolder.mr_discharge_date.setText(Html.fromHtml(medi.getDischarge_date()));

    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }
}

