package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 8/23/2015.
 */

import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Junaid on 5/12/2015.
 */
public class JsonParser {

    public static String mrCode;
    public static String vDate;
    public static String gCode;
    public static String tCode;

    public static String admCode;
    public static String opDate;
    public static String procCode;

    private String jsonUname;
    private String jsonPass;
    private String eCode;
    private String jsonIpa;
    private String stat;

    private String _mrCode;
    private String _vDate;
    private String _gCode;
    private String _tCode;
    private String _admCode;
    private String _opDate;
    private String _procCode;

    int bitmap001 = R.drawable.ic_001;
    int bitmap002 = R.drawable.ic_002;
    int bitmap003 = R.drawable.ic_003;
    int bitmap004 = R.drawable.ic_004;
    int bitmap005 = R.drawable.ic_005;
    int bitmap006 = R.drawable.ic_006;
    int bitmap007 = R.drawable.ic_007;
    int bitmap008 = R.drawable.ic_008;
    int bitmap009 = R.drawable.ic_009;
    int bitmap010 = R.drawable.ic_010;
    int bitmap011 = R.drawable.ic_011;
    int bitmap012 = R.drawable.ic_012;

    int bitmap_male = R.drawable.ic_indus_male;
    int bitmap_female = R.drawable.ic_indus_female;

    public JsonParser(String jsonUname, String jsonPass,String jsonIpa,String jsonStat) {
        this.jsonUname = jsonUname;
        this.jsonPass = jsonPass;
        this.jsonIpa = jsonIpa;
        this.stat = jsonStat;
    }

    // --------------------------- IRS List ----------------------------------
    public ArrayList<Employee> parseGetEmp() throws IOException {
        //this.mrCode = mr_code;
        ArrayList<Employee> array_list = new ArrayList<Employee>();

        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://" + jsonIpa + "/getEmployee.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String eCode = mJSONObject.getString("EMP_CODE");
                String eName = mJSONObject.getString("E_NAME");
                String eIsval = mJSONObject.getString("ISVAL");

                Employee emp = new Employee();
                emp.setEmp_code(eCode);
                emp.setEmp_name(eName);
                emp.setIsvalid(eIsval);

                array_list.add(emp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- Patient List ----------------------------------
    public ArrayList<MrInfo> parseMrinfo() throws IOException {

        ArrayList<MrInfo> array_list = new ArrayList<MrInfo>();

        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/mrInfo.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String patName = mJSONObject.getString("PAT_NAME");
                String patfName = mJSONObject.getString("PAT_SDW");
                String patAge = mJSONObject.getString("PAT_AGE");
                String patSex = mJSONObject.getString("PAT_SEX");

                String patFvisit = mJSONObject.getString("PAT_F_VISIT");
                String patFcounter = mJSONObject.getString("PAT_FV_COUNTER");
                String patType = mJSONObject.getString("PAT_TYPE");
                String patPreAdmission = mJSONObject.getString("PRE_ADMISSION");
                String patAdmDate = mJSONObject.getString("PAT_ADM_DATE");
                String patAdmiNo = mJSONObject.getString("PAT_ADMI_NO");
                String admitingPhysician = mJSONObject.getString("ADMITING_PHYSICIAN");
                String curOpNote = mJSONObject.getString("CUR_OP_NOTE");
                String preOpNote = mJSONObject.getString("PRE_OP_NOTE");

                String appTime = mJSONObject.getString("APP_TIME");
                String appointRemarks = mJSONObject.getString("APPOINT_REMARKS");

                String admitingBed = mJSONObject.getString("ADMITING_BED");
                String admintingWard = mJSONObject.getString("ADMINTING_WARD");
                String currBed = mJSONObject.getString("CURR_BED");
                String currWard = mJSONObject.getString("CURR_WARD");

                // ---------------------- new Code but block ------------------------
                String mAdm_cnt = mJSONObject.getString("ADM_CNT");
                String mProcedure_cnt = mJSONObject.getString("PROCEDURE_CNT");
                String mEr_cnt = mJSONObject.getString("ER_CNT");
                String mOpd_cnt = mJSONObject.getString("OPD_CNT");
                String mInvest_cnt = mJSONObject.getString("INVEST_CNT");
                // ---------------------- new Code but block ------------------------

                String strDesc ="Test Application Approved Purchase Order";

                MrInfo mrinfo = new MrInfo();
                mrinfo.setMr_code(mrCode);
                mrinfo.setPat_name(patName);
                mrinfo.setPat_fname(patfName);
                mrinfo.setPat_age(patAge);
                mrinfo.setPat_sex(patSex);
                mrinfo.setPat_f_vist(patFvisit);
                mrinfo.setPat_fv_counter(patFcounter);
                mrinfo.setPat_type(patType);
                mrinfo.setPre_admission(patPreAdmission);
                mrinfo.setPat_adm_date(patAdmDate);
                mrinfo.setPat_admi_no(patAdmiNo);
                mrinfo.setAdmiting_physician(admitingPhysician);
                mrinfo.setCur_op_note(curOpNote);
                mrinfo.setPre_op_note(preOpNote);
                mrinfo.setApp_time(appTime);
                mrinfo.setApp_remarks(appointRemarks);

                mrinfo.setAdmiting_bed(admitingBed);
                mrinfo.setAdminting_ward(admintingWard);
                mrinfo.setCurr_bed(currBed);
                mrinfo.setCurr_ward(currWard);
                // ---------------------- new Code but block ------------------------
                mrinfo.setAdm_cnt(mAdm_cnt);
                mrinfo.setProcedure_cnt(mProcedure_cnt);
                mrinfo.setEr_cnt(mEr_cnt);
                mrinfo.setOpd_cnt(mOpd_cnt);
                mrinfo.setInvest_cnt(mInvest_cnt);
                // ---------------------- new Code but block ------------------------

                if (patSex.equals("Male"))
                {
                    mrinfo.setGenderImgReosurce(bitmap_male);
                }
                else
                {
                    mrinfo.setGenderImgReosurce(bitmap_female);
                }
                array_list.add(mrinfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- Visit History ----------------------------------
    public ArrayList<VisitDate> parseVisit() throws IOException {

        ArrayList<VisitDate> array_list = new ArrayList<VisitDate>();

        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/visitDate.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String visitDate = mJSONObject.getString("VISIT_DATE");
                String doctorName = mJSONObject.getString("D_NAME");
                String mspecialty = mJSONObject.getString("SPECIALTY");
                String mbp_sis = mJSONObject.getString("VITAL_BP_SIS");
                String mbp_dia = mJSONObject.getString("VITAL_DYS");
                String mtemp = mJSONObject.getString("VITAL_TEMP");
                String mpulse = mJSONObject.getString("VITAL_PULSE");
                String mres_rate = mJSONObject.getString("VITAL_RES_RATE");

                VisitDate visitdate = new VisitDate();
                visitdate.setMr_code(mrCode);
                visitdate.setVist_date(visitDate);
                visitdate.setDoctor_name(doctorName);
                visitdate.setSpecialty(mspecialty);
                visitdate.setBp_sis(mbp_sis);
                visitdate.setBp_dia(mbp_dia);
                visitdate.setTemp(mtemp);
                visitdate.setPulse(mpulse);
                visitdate.setRes_rate(mres_rate);

                array_list.add(visitdate);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- Medical Record ----------------------------------
    public ArrayList<MedicalRecord> parseMedical() throws IOException {

        ArrayList<MedicalRecord> array_list = new ArrayList<MedicalRecord>();

        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/medicalRec.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String visitDate = mJSONObject.getString("VISIT_DATE");
                String title = mJSONObject.getString("TITLE");
                String mrData = mJSONObject.getString("MR_DATA");

                MedicalRecord medi = new MedicalRecord();
                medi.setMr_code(mrCode);
                medi.setVist_date(visitDate);
                medi.setTitle(title);
                medi.setMrdata(mrData);

                array_list.add(medi);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- Visit History ----------------------------------
    public ArrayList<InPatMedRec> parseInPat() throws IOException {

        ArrayList<InPatMedRec> array_list = new ArrayList<InPatMedRec>();

        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/in_pat_med_rec.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String admNo = mJSONObject.getString("ADM_NO");
                String admDate = mJSONObject.getString("ADM_DATE");
                String admitingPhysician = mJSONObject.getString("ADMITING_PHYSICIAN");
                String mspecialty = mJSONObject.getString("SPECIALTY");
                String discDate = mJSONObject.getString("DISCHARGE_DATE");
                String fianalDiag = mJSONObject.getString("FIANAL_DIAG");
                String attendingDutyDoctor = mJSONObject.getString("ATTENDING_DUTY_DOCTOR");
                String dutyDoctor = mJSONObject.getString("DUTY_DOCTOR");


                InPatMedRec mInPatMedRec = new InPatMedRec();
                mInPatMedRec.setMr_code(mrCode);
                mInPatMedRec.setAdm_no(admNo);
                mInPatMedRec.setAdm_date(admDate);
                mInPatMedRec.setAdmiting_physician(admitingPhysician);
                mInPatMedRec.setSpecialty(mspecialty);
                mInPatMedRec.setDischarge_date(discDate);
                mInPatMedRec.setFianal_diag(fianalDiag);
                mInPatMedRec.setAttending_duty_doctor(attendingDutyDoctor);
                mInPatMedRec.setDuty_doctor(dutyDoctor);

                array_list.add(mInPatMedRec);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- Medical Record ----------------------------------
    public ArrayList<InPatMedRecDet> parseInPatDet() throws IOException {

        ArrayList<InPatMedRecDet> array_list = new ArrayList<InPatMedRecDet>();

        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/in_pat_med_rec.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String title = mJSONObject.getString("TITLE");
                String mrData = mJSONObject.getString("MR_DATA");
                String mrData2 = mJSONObject.getString("MR_DATA2");
                String mrData3 = mJSONObject.getString("MR_DATA3");

                InPatMedRecDet medi = new InPatMedRecDet();
                medi.setMr_code(mrCode);
                medi.setTitle(title);
                medi.setMrdata(mrData);
                //medi.setTitle2(title2);
                medi.setMrdata2(mrData2);
                medi.setMrdata3(mrData3);

                array_list.add(medi);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }


    // --------------------------- Procedure List ----------------------------------
    public ArrayList<ProcedureList> parseProcList() throws IOException {

        ArrayList<ProcedureList> array_list = new ArrayList<ProcedureList>();

        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/procList.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String mAdm_no = mJSONObject.getString("ADM_NO");
                String mOP_DATE = mJSONObject.getString("OP_DATE");
                String mPROCEDURE = mJSONObject.getString("PROCEDURE");
                String mPORCEDURE_CODE = mJSONObject.getString("PORCEDURE_CODE");
                String mSURG = mJSONObject.getString("SURG");
                String mSURG_CODE = mJSONObject.getString("SURG_CODE");


                ProcedureList medi = new ProcedureList();
                medi.setMr_code(mrCode);
                medi.setAdm_no(mAdm_no);
                medi.setOp_date(mOP_DATE);
                medi.setProcedure(mPROCEDURE);
                medi.setPorcedure_code(mPORCEDURE_CODE);
                medi.setSurg(mSURG);
                medi.setSurg_code(mSURG_CODE);

                array_list.add(medi);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- Procedure List Detail----------------------------------
    public ArrayList<ProcedureListDetail> parseProcListDet() throws IOException {

        ArrayList<ProcedureListDetail> array_list = new ArrayList<ProcedureListDetail>();

        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/procList.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String mAdm_no = mJSONObject.getString("ADM_NO");
                String mADM_DATE = mJSONObject.getString("ADM_DATE");
                String mPROCEDURE_NAME = mJSONObject.getString("PROCEDURE_NAME");
                String mOP_DATE = mJSONObject.getString("OP_DATE");
                String mANAESTH = mJSONObject.getString("ANAESTH");
                String mSURG = mJSONObject.getString("SURG");
                String mSURG_ASST = mJSONObject.getString("SURG_ASST");

                String mANAESTH_DOC = mJSONObject.getString("ANAESTH_DOC");
                String mANAESTH_DOC_ASST = mJSONObject.getString("ANAESTH_DOC_ASST");
                String mINCISION = mJSONObject.getString("INCISION");
                String mFINDINGS = mJSONObject.getString("FINDINGS");
                String mPROCEDURE_DET = mJSONObject.getString("PROCEDURE_DET");

                String mSPECIMEN = mJSONObject.getString("SPECIMEN");
                String mCLOSURE = mJSONObject.getString("CLOSURE");
                String mOTHERS = mJSONObject.getString("OTHERS");

                ProcedureListDetail medi = new ProcedureListDetail();

                medi.setMr_code(mrCode);
                medi.setAdm_no(mAdm_no);
                medi.setAdm_date(mADM_DATE);
                medi.setProcedure_name(mPROCEDURE_NAME);
                medi.setOp_date(mOP_DATE);
                medi.setAnaesth(mANAESTH);
                medi.setSurg(mSURG);
                medi.setSurg_asst(mSURG_ASST);

                medi.setAnaesth_doc(mANAESTH_DOC);
                medi.setAnaesth_doc_asst(mANAESTH_DOC_ASST);
                medi.setIncision(mINCISION);
                medi.setFindings(mFINDINGS);
                medi.setProcedure_det(mPROCEDURE_DET);

                medi.setSpecimen(mSPECIMEN);
                medi.setClosure(mCLOSURE);
                medi.setOthers(mOTHERS);

                array_list.add(medi);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- Group List ----------------------------------
    public ArrayList<GroupList> parseGroupList() throws IOException {
        //this.mrCode = mr_code;
        ArrayList<GroupList> array_list = new ArrayList<GroupList>();


        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/groupList.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String patGname = mJSONObject.getString("GROUP_NAME");
                String patGcode = mJSONObject.getString("GROUP_CODE");
                String patTcount = mJSONObject.getString("TEST_COUNT");

                GroupList groupList = new GroupList();
                groupList.setMr_code(mrCode);
                groupList.setGroup_code(patGcode);
                groupList.setGroup_name(patGname);

                if (Integer.parseInt(patTcount)>99) {
                    groupList.setTest_count("99+");
                }
                else
                {
                    groupList.setTest_count(patTcount);
                }

                if(patGcode.equals("001"))
                {
                    groupList.setImgReosurce(bitmap001);
                }
                else if (patGcode.equals("002"))
                {
                    groupList.setImgReosurce(bitmap002);
                }
                else if (patGcode.equals("003"))
                {
                    groupList.setImgReosurce(bitmap003);
                }
                else if (patGcode.equals("004"))
                {
                    groupList.setImgReosurce(bitmap004);
                }
                else if (patGcode.equals("005"))
                {
                    groupList.setImgReosurce(bitmap005);
                }
                else if (patGcode.equals("006"))
                {
                    groupList.setImgReosurce(bitmap006);
                }
                else if (patGcode.equals("007"))
                {
                    groupList.setImgReosurce(bitmap007);
                }
                else if (patGcode.equals("008"))
                {
                    groupList.setImgReosurce(bitmap008);
                }
                else if (patGcode.equals("009"))
                {
                    groupList.setImgReosurce(bitmap009);
                }
                else if (patGcode.equals("010"))
                {
                    groupList.setImgReosurce(bitmap010);
                }
                else if (patGcode.equals("011"))
                {
                    groupList.setImgReosurce(bitmap011);
                }
                else if (patGcode.equals("012"))
                {
                    groupList.setImgReosurce(bitmap012);
                }

                array_list.add(groupList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- Test List ----------------------------------
    public ArrayList<TestList> parseTestList() throws IOException {
        //this.mrCode = mr_code;
        ArrayList<TestList> array_list = new ArrayList<TestList>();


        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://"+ jsonIpa +"/testList.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String patGname = mJSONObject.getString("GROUP_NAME");
                String patGcode = mJSONObject.getString("GROUP_CODE");
                String patTcount = mJSONObject.getString("TEST_COUNT");
                String patTestCode = mJSONObject.getString("TEST_CODE");
                String patTestName = mJSONObject.getString("TEST_NAME");

                TestList testList = new TestList();
                testList.setMr_code(mrCode);
                testList.setGroup_code(patGcode);
                testList.setGroup_name(patGname);
                testList.setTest_code(patTestCode);
                testList.setTest_name(patTestName);

                if (Integer.parseInt(patTcount)>99) {
                    testList.setTest_count("99+");
                }
                else
                {
                    testList.setTest_count(patTcount);
                }

                if(patGcode.equals("001"))
                {
                    testList.setImgReosurce(bitmap001);
                }
                else if (patGcode.equals("002"))
                {
                    testList.setImgReosurce(bitmap002);
                }
                else if (patGcode.equals("003"))
                {
                    testList.setImgReosurce(bitmap003);
                }
                else if (patGcode.equals("004"))
                {
                    testList.setImgReosurce(bitmap004);
                }
                else if (patGcode.equals("005"))
                {
                    testList.setImgReosurce(bitmap005);
                }
                else if (patGcode.equals("006"))
                {
                    testList.setImgReosurce(bitmap006);
                }
                else if (patGcode.equals("007"))
                {
                    testList.setImgReosurce(bitmap007);
                }
                else if (patGcode.equals("008"))
                {
                    testList.setImgReosurce(bitmap008);
                }
                else if (patGcode.equals("009"))
                {
                    testList.setImgReosurce(bitmap009);
                }
                else if (patGcode.equals("010"))
                {
                    testList.setImgReosurce(bitmap010);
                }
                else if (patGcode.equals("011"))
                {
                    testList.setImgReosurce(bitmap011);
                }
                else if (patGcode.equals("012"))
                {
                    testList.setImgReosurce(bitmap012);
                }

                array_list.add(testList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

    // --------------------------- IRS List ----------------------------------
    public ArrayList<IRS> parseIRSList() throws IOException {
        //this.mrCode = mr_code;
        ArrayList<IRS> array_list = new ArrayList<IRS>();


        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://" + jsonIpa + "/irsList.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String patIrs = mJSONObject.getString("LRS_NO");
                String patTcount = mJSONObject.getString("TEST_COUNT");

                IRS irs = new IRS();
                irs.setMr_code(mrCode);
                irs.setIrs(patIrs);
                if (Integer.parseInt(patTcount)>99) {
                    irs.setTest_count("99+");
                }
                else
                {
                    irs.setTest_count(patTcount);
                }

                array_list.add(irs);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }

        // --------------------------- Group Detail ----------------------------------
    public ArrayList<GroupDetail> parseGroupDetail() throws IOException {
        //this.mrCode = mr_code;
        ArrayList<GroupDetail> array_list = new ArrayList<GroupDetail>();


        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://" + jsonIpa + "/groupDetail.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String admNo = mJSONObject.getString("ADM_NO");
                String patIrs = mJSONObject.getString("LRS_NO");
                String entryTime = mJSONObject.getString("E_TIME");
                String testCode = mJSONObject.getString("TEST_CODE");
                String testName = mJSONObject.getString("TEST_NAME");
                String groupName = mJSONObject.getString("GROUP_NAME");
                String org1 = mJSONObject.getString("ORG1");
                String org2 = mJSONObject.getString("ORG2");
                String org3 = mJSONObject.getString("ORG3");
                String remarks = mJSONObject.getString("REMARKS");
                String groupCode = mJSONObject.getString("GROUP_CODE");
                String reportId = mJSONObject.getString("REPORT_ID");
//REPORT_ID
                String strDesc ="Test Application Approved Purchase Order";

                GroupDetail groupdetail = new GroupDetail();
                groupdetail.setMr_code(mrCode);
                groupdetail.setAdm_no(admNo);
                groupdetail.setLrs_no(patIrs);
                groupdetail.setEntry_time(entryTime);
                groupdetail.setTest_code(testCode);
                groupdetail.setTest_name(testName);
                groupdetail.setGroup_name(groupName);
                groupdetail.setOrg1(org1);
                groupdetail.setOrg2(org2);
                groupdetail.setOrg3(org3);
                groupdetail.setRemarks(remarks);
                groupdetail.setGroup_code(groupCode);
                groupdetail.setReport_id(reportId);

                array_list.add(groupdetail);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }


    // --------------------------- Normal Result ----------------------------------
    public ArrayList<NormalResult> parseNormalResult() throws IOException {
        //this.mrCode = mr_code;
        ArrayList<NormalResult> array_list = new ArrayList<NormalResult>();


        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://" + jsonIpa + "/normalResult.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mrCode = mJSONObject.getString("MR_CODE");
                String patIrs = mJSONObject.getString("LRS_NO");
                String adm_no = mJSONObject.getString("ADM_NO");
                String parameter = mJSONObject.getString("PARAMETER");
                String normalValue = mJSONObject.getString("NORMAL_VALUE");

                String result = mJSONObject.getString("RESULT");
                String remarks = mJSONObject.getString("REMARKS");

                String strDesc ="Test Application Approved Purchase Order";

                NormalResult normal_result = new NormalResult();
                normal_result.setMr_code(mrCode);
                normal_result.setLrs_no(patIrs);
                normal_result.setAdm_no(adm_no);
                normal_result.setParameter(parameter);
                normal_result.setNormal_value(normalValue);
                normal_result.setResult(result);
                normal_result.setRemarks(remarks);

                array_list.add(normal_result);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }


    // --------------------------- Microbiology Result Stain----------------------------------
    public ArrayList<MicroResultSatin> parseMicroResultStain() throws IOException {
        ArrayList<MicroResultSatin> array_list = new ArrayList<MicroResultSatin>();


        String strJson = httpPost("http://" + jsonIpa + "/microResult.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String stain = mJSONObject.getString("STAIN");
                String stainValue = mJSONObject.getString("STAIN_VALUE");

                MicroResultSatin micro_result = new MicroResultSatin();
                micro_result.setStain(stain);
                micro_result.setStain_value(stainValue);

                array_list.add(micro_result);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }


    // --------------------------- Microbiology Result Ogganism----------------------------------
    public ArrayList<MicroResultOrg> parseMicroResultOrg() throws IOException {
        ArrayList<MicroResultOrg> array_list = new ArrayList<MicroResultOrg>();

        String strJson = httpPost("http://" + jsonIpa + "/microResult.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);

                String parameter = mJSONObject.getString("PARAMETER");
                String org1 = mJSONObject.getString("ORG_1");
                String org2 = mJSONObject.getString("ORG_2");
                String org3 = mJSONObject.getString("ORG_3");

                MicroResultOrg micro_result = new MicroResultOrg();
                micro_result.setParameter(parameter);
                micro_result.setOrg1(org1);
                micro_result.setOrg2(org2);
                micro_result.setOrg3(org3);

                array_list.add(micro_result);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }


    // --------------------------- Normal Result ----------------------------------
    public ArrayList<RadiologyResult> parseRadiologyResult() throws IOException {
        //this.mrCode = mr_code;
        ArrayList<RadiologyResult> array_list = new ArrayList<RadiologyResult>();


        //String strJson = httpPost("http://192.168.0.103:85/unApprovPO.aspx");
        String strJson = httpPost("http://" + jsonIpa + "/normalResult.aspx");
        try {
            JSONArray mJSONArray = new JSONArray(strJson);
            for (int i = 0; i <= mJSONArray.length(); i++) {
                JSONObject mJSONObject = mJSONArray.getJSONObject(i);
                String mDetail = mJSONObject.getString("DETAIL");
                String mConclusion = mJSONObject.getString("CONCLUSION");
                String mHeading = mJSONObject.getString("HEADING");

                String strDesc ="Test Application Approved Purchase Order";

                RadiologyResult radiology_result = new RadiologyResult();

                radiology_result.setDetail(mDetail);
                radiology_result.setConclusion(mConclusion);
                radiology_result.setHeading(mHeading);

                array_list.add(radiology_result);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array_list;
    }


    // --------------------------- HttpPost & httpget ----------------------------
    private String httpGet(String url) {
        String result = "";
        HttpClient mHttpClient = new DefaultHttpClient();
        HttpGet mHttpGet = new HttpGet(url);
        try {
            HttpResponse mHttpResponse = mHttpClient.execute
                    (mHttpGet);
            result = EntityUtils.toString(mHttpResponse.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    private String httpPost(String url) {
        //String url = "http://demo.xorsat.org/xorfood/api/add_order.php";
        String result = "";
        HttpClient mHttpClient = new DefaultHttpClient();
        HttpPost mHttpPost = new HttpPost(url);
        try {
            ArrayList<NameValuePair> mListNameValuePair = new ArrayList<NameValuePair>();
            mListNameValuePair.add(new BasicNameValuePair("Uid", this.jsonUname));
            mListNameValuePair.add(new BasicNameValuePair("pass", this.jsonPass));
            mListNameValuePair.add(new BasicNameValuePair("ecode", this.eCode));
            mListNameValuePair.add(new BasicNameValuePair("MrCode", this.mrCode));
            mListNameValuePair.add(new BasicNameValuePair("AdmCode", this.admCode));
            mListNameValuePair.add(new BasicNameValuePair("OpDate", this.opDate));
            mListNameValuePair.add(new BasicNameValuePair("ProcCode", this.procCode));
            mListNameValuePair.add(new BasicNameValuePair("Widget", this.stat));
            mListNameValuePair.add(new BasicNameValuePair("VisitDate", this.vDate));
            mListNameValuePair.add(new BasicNameValuePair("GroupCode", this.gCode));
            mListNameValuePair.add(new BasicNameValuePair("TestCode", this.tCode));
            mListNameValuePair.add(new BasicNameValuePair("appid", "0001"));
            //Widget
            mHttpPost.setEntity(new UrlEncodedFormEntity(mListNameValuePair));
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
            result = EntityUtils.toString(mHttpResponse.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
     */

    private String httpPost(String url) throws IOException {
        //"http://demo.xorsat.org/xorfood/api/add_order.php"
        StringBuilder sb = new StringBuilder();
        String result = "";
        URL murl = new URL(url);
        HttpURLConnection httpsURLConnection = (HttpURLConnection)murl.openConnection();
        //httpsURLConnection.setReadTimeout(15000);
        //httpsURLConnection.setConnectTimeout(20000);
        //httpsURLConnection.setRequestMethod("POST");
        //httpsURLConnection.setRequestProperty("Content-Type", "application/json");
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setDoOutput(true);

        OutputStream outputStream = httpsURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(getQuery(putPreamer()));
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        httpsURLConnection.connect();
        //int mStatus = httpsURLConnection.getResponseCode();
        try
        {
            InputStream in = new BufferedInputStream(httpsURLConnection.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            while ((inputLine = bin.readLine()) != null){
                sb.append(inputLine);
            }
        }
        finally
        {
            httpsURLConnection.disconnect();
        }
        result = sb.toString();
        //Widget
        //mHttpPost.setEntity(new UrlEncodedFormEntity(mListNameValuePair));
        //HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
        //result = EntityUtils.toString(mHttpResponse.getEntity());

        return result;
    }
    private String getQuery(ArrayList<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));

        }
        return result.toString();
    }
    private ArrayList<NameValuePair> putPreamer(){

        _mrCode = this.mrCode;
        _vDate = this.vDate;
        _gCode = this.gCode;
        _tCode = this.tCode;
        _admCode = this.admCode;
        _opDate = this.opDate;
        _procCode = this.procCode;

        if (this.jsonUname==null){
            this.jsonUname="a";
        }
        if (this.jsonPass==null){
            this.jsonPass="a";
        }
        if (this.stat==null){
            this.stat="a";
        }
        if (this.eCode==null){
            this.eCode="a";
        }
        if (_mrCode==null){
            _mrCode="a";
        }
        if (_vDate==null){
            _vDate="a";
        }
        if (_gCode==null){
            _gCode="a";
        }
        if (_tCode==null){
            _tCode="a";
        }
        if (_admCode==null){
            _admCode="a";
        }
        if (_opDate==null){
            _opDate="a";
        }
        if (_procCode==null){
            _procCode="a";
        }

        ArrayList<NameValuePair> mListNameValuePair = new ArrayList<NameValuePair>();
        mListNameValuePair.add(new BasicNameValuePair("Uid", this.jsonUname));
        mListNameValuePair.add(new BasicNameValuePair("pass", this.jsonPass));
        mListNameValuePair.add(new BasicNameValuePair("Widget", this.stat));
        mListNameValuePair.add(new BasicNameValuePair("appid", "0001"));
        mListNameValuePair.add(new BasicNameValuePair("ecode", this.eCode));
        mListNameValuePair.add(new BasicNameValuePair("MrCode", _mrCode));
        mListNameValuePair.add(new BasicNameValuePair("VisitDate", _vDate));
        mListNameValuePair.add(new BasicNameValuePair("GroupCode", _gCode));
        mListNameValuePair.add(new BasicNameValuePair("TestCode", _tCode));
        mListNameValuePair.add(new BasicNameValuePair("AdmCode", _admCode));
        mListNameValuePair.add(new BasicNameValuePair("OpDate", _opDate));
        mListNameValuePair.add(new BasicNameValuePair("ProcCode", _procCode));

        return mListNameValuePair;
    }

}

