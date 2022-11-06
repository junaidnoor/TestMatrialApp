package pk.com.jtech.junaid.testmatrialapp;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Junaid on 9/13/2015.
 */
public class MrDatasource {

    public static String mrCode;
    public static String admCode;
    public static String opDate;
    public static String procCode;
    public static String vDate;
    public static String gCode;
    public static String tCode;


    // -------------------- Get M.R Code  -------------------------------
    public ArrayList<Employee> getEmp(String u,String p,String ip,String st) throws IOException {
        ArrayList<Employee> emp = new ArrayList<Employee>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        emp = mJsonParser.parseGetEmp();
        return emp;
    }

    // -------------------- Get M.R Code  -------------------------------
    public ArrayList<MrInfo> getList(String u,String p,String ip,String st) throws IOException {
        ArrayList<MrInfo> mrinfo = new ArrayList<MrInfo>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        mrinfo = mJsonParser.parseMrinfo();
        return mrinfo;
    }

    // -------------------- Get Visit History  -------------------------------
    public ArrayList<VisitDate> getVisit(String u,String p,String ip,String st) throws IOException {
        ArrayList<VisitDate> visitdate = new ArrayList<VisitDate>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        visitdate = mJsonParser.parseVisit();
        return visitdate;
    }

    // -------------------- Get In Patient Med Rec  -------------------------------
    public ArrayList<InPatMedRec> getInPatMedRec(String u,String p,String ip,String st) throws IOException {
        ArrayList<InPatMedRec> inpatmed = new ArrayList<InPatMedRec>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        inpatmed = mJsonParser.parseInPat();
        return inpatmed;
    }

    // -------------------- Get In Patient Med Rec Det  -------------------------------
    public ArrayList<InPatMedRecDet> getInPatMedRecDet(String u,String p,String ip,String st) throws IOException {
        ArrayList<InPatMedRecDet> inpatmed = new ArrayList<InPatMedRecDet>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        JsonParser.admCode=admCode;
        inpatmed = mJsonParser.parseInPatDet();
        return inpatmed;
    }

    // -------------------- Get OP History  -------------------------------
    public ArrayList<ProcedureList> getOpHistory(String u,String p,String ip,String st) throws IOException {
        ArrayList<ProcedureList> proc_list = new ArrayList<ProcedureList>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        proc_list = mJsonParser.parseProcList();
        return proc_list;
    }

    // -------------------- Get OP History  Detail -------------------------------
    public ArrayList<ProcedureListDetail> getOpHistoryDet(String u,String p,String ip,String st) throws IOException {
        ArrayList<ProcedureListDetail> proc_list = new ArrayList<ProcedureListDetail>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        JsonParser.admCode=admCode;
        JsonParser.opDate =opDate;
        JsonParser.procCode=procCode;
        proc_list = mJsonParser.parseProcListDet();
        return proc_list;
    }

    // -------------------- Get Medical Record  -------------------------------
    public ArrayList<MedicalRecord> getMedical(String u,String p,String ip,String st) throws IOException {
        ArrayList<MedicalRecord> visitdate = new ArrayList<MedicalRecord>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        JsonParser.vDate = vDate;
        visitdate = mJsonParser.parseMedical();
        return visitdate;
    }

    // -------------------- Get Group List -------------------------------
    public ArrayList<GroupList> getGroupList(String u,String p,String ip,String st) throws IOException {
        ArrayList<GroupList> groupList = new ArrayList<GroupList>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=this.mrCode;
        groupList = mJsonParser.parseGroupList();
        //return mJsonParser.parseProducts();

        return groupList;
    }

    // -------------------- Get Test List -------------------------------
    public ArrayList<TestList> getTestList(String u,String p,String ip,String st) throws IOException {
        ArrayList<TestList> testLists = new ArrayList<TestList>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        testLists = mJsonParser.parseTestList();
        //return mJsonParser.parseProducts();

        return testLists;
    }

    // -------------------- Get IRS List -------------------------------
    public ArrayList<IRS> getIRSList(String u,String p,String ip,String st) throws IOException {
        ArrayList<IRS> irsLists = new ArrayList<IRS>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        irsLists = mJsonParser.parseIRSList();
        //return mJsonParser.parseProducts();

        return irsLists;
    }

    // -------------------- Get Group Detail -------------------------------
    public ArrayList<GroupDetail> getGroupDetail(String u,String p,String ip,String st) throws IOException {
        ArrayList<GroupDetail> groupDetail = new ArrayList<GroupDetail>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        JsonParser.gCode = gCode;
        JsonParser.tCode = tCode;
        groupDetail = mJsonParser.parseGroupDetail();
        //return mJsonParser.parseProducts();

        return groupDetail;
    }


    // -------------------- Get Normal Result -------------------------------
    public ArrayList<NormalResult> getNormalResult(String u,String p,String ip,String st) throws IOException {
        ArrayList<NormalResult> groupDetail = new ArrayList<NormalResult>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        JsonParser.gCode = gCode;
        JsonParser.tCode = tCode;
        groupDetail = mJsonParser.parseNormalResult();
        //return mJsonParser.parseProducts();

        return groupDetail;
    }

    // -------------------- Get Micro Result Stain -------------------------------
    public ArrayList<MicroResultSatin> getMicroResultStain(String u,String p,String ip,String st) throws IOException {
        ArrayList<MicroResultSatin> groupDetail = new ArrayList<MicroResultSatin>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        JsonParser.gCode = gCode;
        JsonParser.tCode = tCode;
        groupDetail = mJsonParser.parseMicroResultStain();
        //return mJsonParser.parseProducts();

        return groupDetail;
    }

    // -------------------- Get Micro Result Stain -------------------------------
    public ArrayList<MicroResultOrg> getMicroResultOrg(String u,String p,String ip,String st) throws IOException {
        ArrayList<MicroResultOrg> groupDetail = new ArrayList<MicroResultOrg>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        JsonParser.gCode = gCode;
        JsonParser.tCode = tCode;
        groupDetail = mJsonParser.parseMicroResultOrg();
        //return mJsonParser.parseProducts();

        return groupDetail;
    }

    // -------------------- Get Radiology Result -------------------------------
    public ArrayList<RadiologyResult> getRadiologyResult(String u,String p,String ip,String st) throws IOException {
        ArrayList<RadiologyResult> groupDetail = new ArrayList<RadiologyResult>();
        JsonParser mJsonParser = new JsonParser(u,p,ip,st);
        JsonParser.mrCode=mrCode;
        JsonParser.gCode = gCode;
        JsonParser.tCode = tCode;
        groupDetail = mJsonParser.parseRadiologyResult();
        //return mJsonParser.parseProducts();

        return groupDetail;
    }

    // -------------------- Get M.R Code  -------------------------------
    public ArrayList<MrInfo> getList1()
    {
        ArrayList<MrInfo> array_list = new ArrayList<MrInfo>();
        for (int i = 1; i <= 10; i++) {
            MrInfo mrinfo = new MrInfo();
            mrinfo.setMr_code("10100152525");
            mrinfo.setPat_name("Abdul Bari Khan");
            mrinfo.setPat_fname("S/o Abdul Latif Khan");
            mrinfo.setPat_age("25 Year 10 Month");
            mrinfo.setPat_sex("Male");
            array_list.add(mrinfo);

        }
        return array_list;
    }

}
