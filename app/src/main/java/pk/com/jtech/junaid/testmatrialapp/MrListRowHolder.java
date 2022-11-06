package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 9/20/2015.
 */
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class MrListRowHolder extends RecyclerView.ViewHolder {

        //implements View.OnClickListener, View.OnLongClickListener {

    //private ItemClickListener clickListener;
//row_group_imageView
    protected TextView mrinfo_mr;
    protected TextView mrinfo_pname;
    protected TextView mrinfo_fname;
    protected TextView mrinfo_age;
    protected ImageView mrinfo_gender_image;

    protected TextView mr_f_vist;
    protected TextView mr_fv_counter;
    protected TextView mr_pat_type;
    protected TextView mr_pre_admission;
    protected TextView mr_adm_date;
    protected TextView mr_admi_no;
    protected TextView mr_admiting_physician;
    protected TextView mr_cur_op_note;
    protected TextView mr_pre_op_note;

    protected TextView mr_visit_date;
    protected TextView mr_doctor;
    protected TextView mr_specialty;

    protected TextView mrinfo_title;
    protected TextView mrinfo_mrdata;
    protected TextView mrinfo_mrdata2;
    protected TextView mrinfo_mrdata3;

    protected ImageView mr_group_imageView;
    protected TextView mr_result;
    protected TextView mr_group_count;

    protected ImageView mr_test_imageView;
    protected TextView mr_test_result;
    protected TextView mr_test_count;

    protected TextView mr_irsno;
    protected TextView mr_irs_test_count;

    protected TextView mr_test_name;
    protected TextView mr_lrs;
    protected TextView mr_entery_time;

    protected TextView mr_app;
    protected TextView mr_rem;

    protected TextView mr_curr_bed;
    protected TextView mr_curr_ward;

    protected TextView mr_adm_no;
    protected TextView mr_op_date;
    protected TextView mr_procedure;
    protected TextView mr_surg;

    protected TextView mr_procedure_name;
    protected TextView mr_anaesth;
    protected TextView mr_surg_asst;
    protected TextView mr_anaesth_doc;
    protected TextView mr_anaesth_doc_asst;
    protected TextView mr_incision;
    protected TextView mr_procedure_det;
    protected TextView mr_specimen;
    protected TextView mr_closure;
    protected TextView mr_others;
    protected TextView mr_findings;

    protected TextView mr_fianal_diag;
    protected TextView mr_discharge_date;

    //protected TextView mrinfo_mr;
    //protected TextView mr_irsno;
    //protected TextView mr_adm_no;
    //protected TextView mr_result;
    protected TextView mr_parameter;
    protected TextView mr_normal_value;

    protected TextView mr_detail;
    protected TextView mr_conclusion;
    protected TextView mr_heading;

    protected TextView mr_stain;
    protected TextView mr_stain_value;

    //protected TextView mr_parameter;;
    protected TextView mr_org1;
    protected TextView mr_org2;
    protected TextView mr_org3;

    protected TextView mr_adm_cnt;
    protected TextView mr_procedure_cnt;
    protected TextView mr_er_cnt;
    protected TextView mr_opd_cnt;
    protected TextView mr_invest_cnt;

    protected TextView title;

    public MrListRowHolder(View view) {
        super(view);

        //view.setTag(itemView);
        //view.setOnClickListener(this);
        //view.setOnLongClickListener(this);

        //---------------------- Find & Follow Up ---------------------------
        this.mrinfo_mr = (TextView) view.findViewById(R.id.row_mrinfo_mr);
        this.mrinfo_pname = (TextView) view.findViewById(R.id.row_mrinfo_pname);
        this.mrinfo_fname = (TextView) view.findViewById(R.id.row_mrinfo_fname);
        this.mrinfo_age = (TextView) view.findViewById(R.id.row_mrinfo_age);
        this.mrinfo_gender_image = (ImageView) view.findViewById(R.id.row_mrinfo_gender_image);
        this.mr_f_vist = (TextView) view.findViewById(R.id.row_f_vist);
        this.mr_fv_counter = (TextView) view.findViewById(R.id.row_fv_counter);
        this.mr_pat_type = (TextView) view.findViewById(R.id.row_pat_type);
        this.mr_pre_admission = (TextView) view.findViewById(R.id.row_pre_admission);
        this.mr_adm_date = (TextView) view.findViewById(R.id.row_adm_date);
        this.mr_admi_no = (TextView) view.findViewById(R.id.row_admi_no);
        this.mr_admiting_physician = (TextView) view.findViewById(R.id.row_admiting_physician);
        this.mr_cur_op_note = (TextView) view.findViewById(R.id.row_cur_op_note);
        this.mr_pre_op_note = (TextView) view.findViewById(R.id.row_pre_op_note);


        //this.mr_er_cnt = (TextView) view.findViewById(R.id.row_er_cnt);
        this.mr_opd_cnt = (TextView) view.findViewById(R.id.row_opd_cnt);
        this.mr_adm_cnt = (TextView) view.findViewById(R.id.row_adm_cnt);
        this.mr_procedure_cnt = (TextView) view.findViewById(R.id.row_procedure_cnt);
        this.mr_invest_cnt = (TextView) view.findViewById(R.id.row_invest_cnt);

        //---------------------- Find & Follow Up ---------------------------

        //-------------------- Admitited & Day Care -------------------------
        //this.mr_admiting_bed = (TextView) view.findViewById(R.id.row_admiting_bed);
        //this.mr_adminting_ward = (TextView) view.findViewById(R.id.row_adminting_ward);
        this.mr_curr_bed = (TextView) view.findViewById(R.id.row_curr_bed);
        this.mr_curr_ward = (TextView) view.findViewById(R.id.row_curr_ward);
        //-------------------- Admitited & Day Care -------------------------

        //---------------------- Appointment ---------------------------
        this.mr_app = (TextView) view.findViewById(R.id.row_appoint);
        this.mr_rem = (TextView) view.findViewById(R.id.row_app_remarks);
        //---------------------- Appointment ---------------------------

        //---------------------- Patient Vist ---------------------------
        this.mr_visit_date = (TextView) view.findViewById(R.id.row_visit_date);
        this.mr_doctor = (TextView) view.findViewById(R.id.row_doctor);
        this.mr_specialty = (TextView) view.findViewById(R.id.row_specialty);
        //---------------------- Patient Vist ---------------------------

        //---------------------- Medical Record ---------------------------
        this.mrinfo_title = (TextView) view.findViewById(R.id.row_title);
        this.mrinfo_mrdata = (TextView) view.findViewById(R.id.row_mrdata);
        this.mrinfo_mrdata2 = (TextView) view.findViewById(R.id.row_mrdata2);
        this.mrinfo_mrdata3 = (TextView) view.findViewById(R.id.row_mrdata3);
        //---------------------- Medical Record ---------------------------

        //---------------------- Group ---------------------------
        this.mr_group_imageView = (ImageView) view.findViewById(R.id.row_group_imageView);
        this.mr_result = (TextView) view.findViewById(R.id.row_result);
        this.mr_group_count = (TextView) view.findViewById(R.id.roe_group_count);
        //---------------------- Group ---------------------------

        //---------------------- Test ---------------------------
        this.mr_test_imageView = (ImageView) view.findViewById(R.id.row_test_imageView);
        this.mr_test_result = (TextView) view.findViewById(R.id.row_test_result);
        this.mr_test_count = (TextView) view.findViewById(R.id.roe_test_count);
        //---------------------- Test ---------------------------

        //---------------------- I.R.S ---------------------------
        this.mr_irsno = (TextView) view.findViewById(R.id.row_irsno);
        this.mr_irs_test_count = (TextView) view.findViewById(R.id.row_test_count);
        //---------------------- I.R.S ---------------------------

        //---------------------- Group Detail ---------------------------
        this.mr_test_name = (TextView) view.findViewById(R.id.row_test_name);
        this.mr_lrs = (TextView) view.findViewById(R.id.row_lrs);
        this.mr_entery_time = (TextView) view.findViewById(R.id.row_entery_time);
        //---------------------- Group Detail ---------------------------

        //---------------------- Patient Op Note ---------------------------
        this.mr_adm_no = (TextView) view.findViewById(R.id.row_adm_no);
        this.mr_op_date = (TextView) view.findViewById(R.id.row_op_date);
        this.mr_procedure = (TextView) view.findViewById(R.id.row_procedure);
        this.mr_surg = (TextView) view.findViewById(R.id.row_surg);
        //---------------------- Patient Op Note ---------------------------

        //---------------------- Patient Op Note Detail ---------------------------

        this.mr_procedure_name = (TextView) view.findViewById(R.id.row_procedure_name);
        this.mr_anaesth = (TextView) view.findViewById(R.id.row_anaesth);
        this.mr_surg_asst = (TextView) view.findViewById(R.id.row_surg_asst);
        this.mr_anaesth_doc = (TextView) view.findViewById(R.id.row_anaesth_doc);
        this.mr_anaesth_doc_asst = (TextView) view.findViewById(R.id.row_anaesth_doc_asst);
        this.mr_incision = (TextView) view.findViewById(R.id.row_incision);
        this.mr_findings = (TextView) view.findViewById(R.id.row_findings);
        this.mr_procedure_det = (TextView) view.findViewById(R.id.row_procedure_det);
        this.mr_specimen = (TextView) view.findViewById(R.id.row_specimen);
        this.mr_closure = (TextView) view.findViewById(R.id.row_closure);
        this.mr_others = (TextView) view.findViewById(R.id.row_others);
        //---------------------- Patient Op Note Detail ---------------------------

        //---------------------- In Patient Med Rec ---------------------------
        //this.mr_adm_no = (TextView) view.findViewById(R.id.row_adm_no);
        //this.mr_adm_date = (TextView) view.findViewById(R.id.row_adm_date);
        //this.mr_admiting_physician = (TextView) view.findViewById(R.id.row_admiting_physician);
        //this.mr_specialty = (TextView) view.findViewById(R.id.row_specialty);
        this.mr_fianal_diag = (TextView) view.findViewById(R.id.row_fianal_diag);
        this.mr_discharge_date = (TextView) view.findViewById(R.id.row_discharge_date);

        //---------------------- In Patient Med Rec ---------------------------

        //---------------------- Normal Result ---------------------------
        //this.mrinfo_mr = (TextView) view.findViewById(R.id.row_mrinfo_mr);
        //this.mr_irsno = (TextView) view.findViewById(R.id.row_irsno);
        //this.mr_adm_no = (TextView) view.findViewById(R.id.row_adm_no);
        //this.mr_result = (TextView) view.findViewById(R.id.row_result);

        this.mr_parameter = (TextView) view.findViewById(R.id.row_parameter);
        this.mr_normal_value = (TextView) view.findViewById(R.id.row_normal_value);
        //this.mr_remarks = (TextView) view.findViewById(R.id.row_remarks);

        //---------------------- Normal Result ---------------------------

        //---------------------- Micro Result Stain ---------------------------
        this.mr_stain = (TextView) view.findViewById(R.id.row_stain);
        this.mr_stain_value = (TextView) view.findViewById(R.id.row_stain_value);
        //---------------------- Micro Result Stain ---------------------------

        //---------------------- Micro Result Org ---------------------------
        //this.mr_parameter = (TextView) view.findViewById(R.id.row_parameter);
        this.mr_org1 = (TextView) view.findViewById(R.id.row_org1);
        this.mr_org2 = (TextView) view.findViewById(R.id.row_org2);
        this.mr_org3 = (TextView) view.findViewById(R.id.row_org3);
        //---------------------- Micro Result Org ---------------------------


        //---------------------- Radiology Result ---------------------------

        this.mr_detail = (TextView) view.findViewById(R.id.row_detail);
        this.mr_conclusion = (TextView) view.findViewById(R.id.row_conclusion);
        this.mr_heading = (TextView) view.findViewById(R.id.row_heading);

        //---------------------- Radiology Result ---------------------------


    }

    /*
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        clickListener.onClick(view, getPosition(), false);
    }
    @Override
    public boolean onLongClick(View view) {
        clickListener.onClick(view, getPosition(), true);
        return true;
    }
    */
}