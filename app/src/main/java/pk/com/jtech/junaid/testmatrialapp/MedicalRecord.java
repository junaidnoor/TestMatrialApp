package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 10/1/2015.
 */
public class MedicalRecord {

    private String mr_code;
    private String vist_date;
    private String title;
    private String mrdata;
    private String dis;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMrdata() {
        return mrdata;
    }

    public void setMrdata(String mrdata) {
        this.mrdata = mrdata;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }
}
