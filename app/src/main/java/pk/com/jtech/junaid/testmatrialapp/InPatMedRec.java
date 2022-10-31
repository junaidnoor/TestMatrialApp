package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 11/4/2015.
 */
public class InPatMedRec {

    private String mr_code;
    private String adm_no;
    private String adm_date;
    private String admiting_physician;
    private String specialty;
    private String fianal_diag;
    private String discharge_date;
    private String attending_duty_doctor;
    private String duty_doctor;

    public String getAttending_duty_doctor() {
        return attending_duty_doctor;
    }

    public void setAttending_duty_doctor(String attending_duty_doctor) {
        this.attending_duty_doctor = attending_duty_doctor;
    }

    public String getDuty_doctor() {
        return duty_doctor;
    }

    public void setDuty_doctor(String duty_doctor) {
        this.duty_doctor = duty_doctor;
    }

    public String getMr_code() {
        return mr_code;
    }

    public void setMr_code(String mr_code) {
        this.mr_code = mr_code;
    }

    public String getAdm_no() {
        return adm_no;
    }

    public void setAdm_no(String adm_no) {
        this.adm_no = adm_no;
    }

    public String getAdm_date() {
        return adm_date;
    }

    public void setAdm_date(String adm_date) {
        this.adm_date = adm_date;
    }

    public String getAdmiting_physician() {
        return admiting_physician;
    }

    public void setAdmiting_physician(String admiting_physician) {
        this.admiting_physician = admiting_physician;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getFianal_diag() {
        return fianal_diag;
    }

    public void setFianal_diag(String fianal_diag) {
        this.fianal_diag = fianal_diag;
    }

    public String getDischarge_date() {
        return discharge_date;
    }

    public void setDischarge_date(String discharge_date) {
        this.discharge_date = discharge_date;
    }
}
