package pk.com.jtech.junaid.testmatrialapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Junaid on 11/3/2015.
 */
public class ProcListDetailAdapter extends RecyclerView.Adapter<MrListRowHolder> {


    private List<ProcedureListDetail> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;


    public ProcListDetailAdapter(Context context, List<ProcedureListDetail> mrList,int male,int female) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_procedure_list_det, null);
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        ProcedureListDetail medi = mrList.get(i);


        mrListRowHolder.mr_adm_no.setText(Html.fromHtml(medi.getAdm_no()));
        mrListRowHolder.mr_adm_date.setText(Html.fromHtml(medi.getAdm_date()));
        mrListRowHolder.mr_op_date.setText(Html.fromHtml(medi.getOp_date()));
        mrListRowHolder.mr_procedure_name.setText(Html.fromHtml(medi.getProcedure_name()));
        mrListRowHolder.mr_surg.setText(Html.fromHtml(medi.getSurg()));

        mrListRowHolder.mr_anaesth.setText(Html.fromHtml(medi.getAnaesth()));
        mrListRowHolder.mr_surg_asst.setText(Html.fromHtml(medi.getSurg_asst()));
        mrListRowHolder.mr_anaesth_doc.setText(Html.fromHtml(medi.getAnaesth_doc()));
        mrListRowHolder.mr_anaesth_doc_asst.setText(Html.fromHtml(medi.getAnaesth_doc_asst()));
        mrListRowHolder.mr_incision.setText(Html.fromHtml(medi.getIncision()));
        mrListRowHolder.mr_findings.setText(Html.fromHtml(medi.getFindings()));
        mrListRowHolder.mr_procedure_det.setText(Html.fromHtml(medi.getProcedure_det()));
        mrListRowHolder.mr_specimen.setText(Html.fromHtml(medi.getSpecimen()));
        mrListRowHolder.mr_closure.setText(Html.fromHtml(medi.getClosure()));
        mrListRowHolder.mr_others.setText(Html.fromHtml(medi.getOthers()));
    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }
}


