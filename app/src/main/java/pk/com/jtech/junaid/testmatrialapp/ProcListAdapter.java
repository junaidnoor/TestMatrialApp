package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Junaid on 11/3/2015.
 */
public class ProcListAdapter extends RecyclerView.Adapter<MrListRowHolder> {


    private List<ProcedureList> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;


    public ProcListAdapter(Context context, List<ProcedureList> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_procedure_list, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        ProcedureList medi = mrList.get(i);


        mrListRowHolder.mr_adm_no.setText(Html.fromHtml(medi.getAdm_no()));
        mrListRowHolder.mr_op_date.setText(Html.fromHtml(medi.getOp_date()));
        mrListRowHolder.mr_procedure.setText(Html.fromHtml(medi.getProcedure()));
        mrListRowHolder.mr_surg.setText(Html.fromHtml(medi.getSurg()));

    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }
}

