package pk.com.jtech.junaid.testmatrialapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class MediOne extends AppCompatActivity {
    Button btn_open_file;

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String tcode;
    public static String irs;
    public static MrInfo mrinfo;
    public static GroupList groupList;
    public static TestList testList;
    public static IRS irsList;
    public static GroupDetail  mGroupDetail;
    public static String Url1;

    public String url_dicom /*= "http://199.199.1.35:82/2016/XRay/Feb-16/1993971_188_1.dcm"*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        btn_open_file = (Button) findViewById(R.id.btn_open_file);


        new LongOperation().execute("", MediOne.this);



       /* btn_open_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new LongOperation().execute(url_dicom,MediOne.this);
                File dicomPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + File.separator + "DicomSeries" + File.separator + "1.dcm");
                OpenDcmInExternalApp(dicomPath);
            }
        });*/

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println();
    }

    public void OpenDcmInExternalApp(File fileNamePath){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(fileNamePath), "application/dcm");
        startActivity(intent);
    }


    private class LongOperation extends AsyncTask<Object, Integer, Void> {
        private ProgressDialog pDialog;
        File dicomPathNew;

        LongOperation(){
            pDialog = new ProgressDialog(MediOne.this);
            dicomPathNew = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + File.separator + "DicomNew");
        }

        @Override
        protected Void doInBackground(Object... params) {
//       downloadFile((String)params[0],dicomPathNew,pDialog);

            int count;
            String root = Environment.getExternalStorageDirectory().toString();

            if(!dicomPathNew.exists()){
                dicomPathNew.mkdirs();
            }

            try {
                System.out.println("Downloading");
                String a = httpPost(Url1);
                String temp = httpPost(Url1);

                URL url = new URL(a);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                String fileName = "/2879337_183_1.dcm";
                OutputStream output = new FileOutputStream(dicomPathNew + fileName);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // writing data to file
                    if (lenghtOfFile > 0) // only if total length is known
                        publishProgress((int) (total * 100 / lenghtOfFile));
                    output.write(data, 0, count);
                }
                String filePath = dicomPathNew + fileName;
                onDownloadComplete(pDialog , filePath);

                output.flush();
                output.close();
                input.close();

            }catch (Exception ex){
                ex.printStackTrace();
                pDialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MediOne.this, "Cannot connect to server", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(pDialog);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            pDialog.setIndeterminate(false);
            pDialog.setMax(100);
            pDialog.setProgress(progress[0]);

        }
    }

    public void onDownloadComplete(ProgressDialog pDialog,String dicomPathNew){
        pDialog.dismiss();
        boolean installed = appInstalledOrNot("com.samsung.mno.team1.dicomreader");
        if(!installed){
            try {
                installApk();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            OpenDcmInExternalApp(new File(dicomPathNew));
        }

    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    public void installApk() throws Exception{

        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;

        try {
            in = assetManager.open("com.samsung.mno.team1.dicomreader.apk");
            File apkFile = new File(getPathToDownloads().getPath() + File.separator + "dicomreader.apk");
            apkFile.createNewFile();

            if(apkFile.exists()){
                System.out.println();
            }
            out = new FileOutputStream(apkFile);

            byte[] buffer = new byte[1024];

            int read;
            while((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            in.close();
            in = null;

            out.flush();
            out.close();
            out = null;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(apkFile.getPath())), "application/vnd.android.package-archive");
            startActivityForResult(intent,101);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public File getPathToDownloads(){
        String folderName = "DicomApk";

        File path_to_downloads =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + File.separator + folderName);

        if(!path_to_downloads.isDirectory() && !path_to_downloads.exists()) {
            path_to_downloads.mkdir();
        }
        File storageDir = getExternalFilesDir(path_to_downloads.toString());
        return storageDir;
    }

    public void showProgressDialog(ProgressDialog pDialog){

        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    //------------------------ HTTP --------------------------------------
    private String httpPost(String url) {
        //String url = "http://demo.xorsat.org/xorfood/api/add_order.php";
        String result = "";
        HttpClient mHttpClient = new DefaultHttpClient();
        HttpPost mHttpPost = new HttpPost(url);
        try {
            ArrayList<NameValuePair> mListNameValuePair = new ArrayList<NameValuePair>();
            //mListNameValuePair.add(new BasicNameValuePair("Uid", uid));
            //mListNameValuePair.add(new BasicNameValuePair("pass", pas));
            mListNameValuePair.add(new BasicNameValuePair("lrs", irs));
            mListNameValuePair.add(new BasicNameValuePair("testCode", tcode));
            /*mListNameValuePair.add(new BasicNameValuePair("AdmCode", this.admCode));
            mListNameValuePair.add(new BasicNameValuePair("OpDate", this.opDate));
            mListNameValuePair.add(new BasicNameValuePair("ProcCode", this.procCode));
            mListNameValuePair.add(new BasicNameValuePair("Widget", this.stat));
            mListNameValuePair.add(new BasicNameValuePair("VisitDate", this.vDate));
            mListNameValuePair.add(new BasicNameValuePair("GroupCode", this.gCode));
            mListNameValuePair.add(new BasicNameValuePair("TestCode", this.tCode));*/

            //Widget
            mHttpPost.setEntity(new UrlEncodedFormEntity(mListNameValuePair));
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
            result = EntityUtils.toString(mHttpResponse.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
