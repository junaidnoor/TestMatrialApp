package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 9/30/2015.
 */
public class VisitDate {

    private String mr_code;
    private String vist_date;
    private String doctor_name;
    private String specialty;
    private String visit_sum;

    private String bp_sis;      //V.VITAL_BP_SIS,
    private String bp_dia;      //V.VITAL_DYS,
    private String temp;        //V.VITAL_TEMP,
    private String pulse;       //V.VITAL_PULSE,
    private String res_rate;    //V.VITAL_RES_RATE


    public String getMr_code() {
        return mr_code;
    }

    public void setMr_code(String mr_code) {
        this.mr_code = mr_code;
    }

    public String getVist_date() {
        return vist_date;
    }

    public void setVist_date(String vist_date) {
        this.vist_date = vist_date;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getVisit_sum() {
        return visit_sum;
    }

    public void setVisit_sum(String visit_sum) {
        this.visit_sum = visit_sum;
    }

    public String getBp_sis() {
        return bp_sis;
    }

    public void setBp_sis(String bp_sis) {
        this.bp_sis = bp_sis;
    }

    public String getBp_dia() {
        return bp_dia;
    }

    public void setBp_dia(String bp_dia) {
        this.bp_dia = bp_dia;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getRes_rate() {
        return res_rate;
    }

    public void setRes_rate(String res_rate) {
        this.res_rate = res_rate;
    }
}
