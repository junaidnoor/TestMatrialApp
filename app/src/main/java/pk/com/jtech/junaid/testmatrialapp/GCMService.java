package pk.com.jtech.junaid.testmatrialapp;

/**
 * Created by Junaid on 8/22/2015.
 */
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Junaid on 5/17/2015.
 */
public class GCMService extends IntentService {
    public static String user;
    public static String ipa;
    String strSubject = "";
    String uName = "";
    String myMsg="";
    SQLiteDatabase mSQLiteDatabase;

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GCMService() {
        super("GCMService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        new asyncTask_notification().execute();
    }

    //------------------------ Get Responce httpPost -----------------------------
    private String httpPost(String url) {
        //String url = "http://demo.xorsat.org/xorfood/api/add_order.php";
        String result = "";
        HttpClient mHttpClient = new DefaultHttpClient();
        HttpPost mHttpPost = new HttpPost(url);
        try {
            ArrayList<NameValuePair> mListNameValuePair = new ArrayList<NameValuePair>();
            mListNameValuePair.add(new BasicNameValuePair("Uid", user));
            //mListNameValuePair.add(new BasicNameValuePair("pocode", "this.pCode"));
            mHttpPost.setEntity(new UrlEncodedFormEntity(mListNameValuePair));
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
            result = EntityUtils.toString(mHttpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // ------------------------ AsyncTask -----------------------------
    private class asyncTask_notification extends AsyncTask<Void, Void, Void>
    {
        public String isval;
        public String ischk;
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            isval = httpPost("http://"+ipa+"/poNotification.aspx");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            myMsg = isval.toString();
            ischk = myMsg.substring(0, 1);
            //showMessage(ischk);
            if (ischk.equals("0"))
            {
                ischk="";
            }
            else
            {
                sendNotification(myMsg);
                //showMessage("ischk");
            }
            super.onPostExecute(aVoid);
        }
    }

    // -------------------------- Notification ------------------------------------
    private void sendNotification(String msg) {
        Uri uri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        MainActivity.uid = user;
        MainActivity.NOTIFI_ID=NOTIFICATION_ID;
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_lau)
                .setContentTitle(strSubject)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setSound(uri).setContentText(msg);
        mBuilder.setContentIntent(contentIntent);
        //mBuilder.setAutoCancel(true);
        //mBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

