package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 9/20/2015.
 */
import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
//import android.widget.ImageView;
//import android.widget.TextView;

//import com.squareup.picasso.Picasso;



public class PatRecyclerAdapter extends RecyclerView.Adapter<MrListRowHolder> {


    private List<MrInfo> mrList;

    private Context mContext;
    public static int _male;
    public static int _female;
    private int _frag;

    View v;

    private List<MrInfo> mrinfoList = null;
    private ArrayList<MrInfo> arraylist;


    public PatRecyclerAdapter(Context context, List<MrInfo> mrList,int male,int female,int frag) {
        this.mrList = mrList;
        this.mContext = context;
        _male = male;
        _female = female;
        _frag = frag;
        this.mrinfoList = mrList;
        this.arraylist = new ArrayList<MrInfo>();
        this.arraylist.addAll(mrinfoList);
    }

    @Override
    public MrListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if (_frag == 0)
        {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_pat, null);
            //v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_patient, null);
        }
        else if (_frag == 1)
        {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_abc, null);
            //v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_pat, null);
        }
        else if (_frag == 2)
        {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_admission, null);
            //v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_pat, null);
        }
        MrListRowHolder mh = new MrListRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(MrListRowHolder mrListRowHolder, int i) {
        MrInfo mMrInfo = mrList.get(i);

        /*Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(mrListRowHolder.thumbnail);
        */
        mrListRowHolder.mrinfo_mr.setText(Html.fromHtml(mMrInfo.getMr_code()));
        mrListRowHolder.mrinfo_pname.setText(Html.fromHtml(mMrInfo.getPat_name()));
        mrListRowHolder.mrinfo_fname.setText(Html.fromHtml(mMrInfo.getPat_fname()));
        mrListRowHolder.mrinfo_age.setText(Html.fromHtml(mMrInfo.getPat_age()));

        mrListRowHolder.mr_f_vist.setText(Html.fromHtml(mMrInfo.getPat_f_vist()));
        mrListRowHolder.mr_pat_type.setText(Html.fromHtml(mMrInfo.getPat_type()));

       if (_frag == 0) {
            // use row_pat layout
            //mrListRowHolder.mr_er_cnt.setText(Html.fromHtml(mMrInfo.getEr_cnt()));
            mrListRowHolder.mr_opd_cnt.setText(Html.fromHtml(mMrInfo.getOpd_cnt()));
            mrListRowHolder.mr_adm_cnt.setText(Html.fromHtml(mMrInfo.getAdm_cnt()));
            mrListRowHolder.mr_procedure_cnt.setText(Html.fromHtml(mMrInfo.getProcedure_cnt()));
            mrListRowHolder.mr_invest_cnt.setText(Html.fromHtml(mMrInfo.getInvest_cnt()));
        }


        if (_frag != 0) {
            mrListRowHolder.mr_fv_counter.setText(Html.fromHtml(mMrInfo.getPat_fv_counter()));
            mrListRowHolder.mr_pre_admission.setText(Html.fromHtml(mMrInfo.getPre_admission()));

            mrListRowHolder.mr_adm_date.setText(Html.fromHtml(mMrInfo.getPat_adm_date()));
            mrListRowHolder.mr_admi_no.setText(Html.fromHtml(mMrInfo.getPat_admi_no()));
            mrListRowHolder.mr_admiting_physician.setText(Html.fromHtml(mMrInfo.getAdmiting_physician()));
            mrListRowHolder.mr_cur_op_note.setText(Html.fromHtml(mMrInfo.getCur_op_note()));
            mrListRowHolder.mr_pre_op_note.setText(Html.fromHtml(mMrInfo.getPre_op_note()));
        }
        if (_frag == 1)
        {
            mrListRowHolder.mr_app.setText(Html.fromHtml(mMrInfo.getApp_time()));
            mrListRowHolder.mr_rem.setText(Html.fromHtml(mMrInfo.getApp_remarks()));
        }
        if (_frag == 2)
        {
            //mrListRowHolder.mr_admiting_bed.setText(Html.fromHtml(mMrInfo.getAdmiting_bed()));
            //mrListRowHolder.mr_adminting_ward.setText(Html.fromHtml(mMrInfo.getAdminting_ward()));
            mrListRowHolder.mr_curr_bed.setText(Html.fromHtml(mMrInfo.getCurr_bed()));
            mrListRowHolder.mr_curr_ward.setText(Html.fromHtml(mMrInfo.getCurr_ward()));
        }

        if(mMrInfo.getPat_sex().equals("Male"))
        {
            mrListRowHolder.mrinfo_gender_image.setImageResource(mMrInfo.getGenderImgReosurce());
        }
        else
        {
            mrListRowHolder.mrinfo_gender_image.setImageResource(mMrInfo.getGenderImgReosurce());
        }
    }

    @Override
    public int getItemCount() {
        return (null != mrList ? mrList.size() : 0);
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mrinfoList.clear();
        if (charText.length() == 0) {
            mrinfoList.addAll(arraylist);
        } else {
            for (MrInfo wp : arraylist) {
                if (wp.getPat_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mrinfoList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

