package pk.com.jtech.junaid.testmatrialapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MedImageActivity extends AppCompatActivity {

    public static int _male;
    public static int _female;

    // Declaring Your View and Variables
    public static String uid;
    public static String pas;
    public static String ipa;
    public static String para;
    public static MrInfo mrinfo;
    public static GroupList groupList;
    public static TestList testList;
    public static IRS irsList;
    public static GroupDetail  mGroupDetail;

    public static String mUrl;
    Toolbar toolbar;

    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView mirs;
    TextView mtest;
    TextView medate;
    TextView madmo_no;
    TextView toobarTitle;

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_image);
        //setupToolbar();
        init();
        //"http://" + ipa + "/mediImage.aspx"
        view_web(mUrl);
    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void init() {

        myWebView = (WebView) findViewById(R.id.web_view);

        //mpat = (TextView) findViewById(R.id.pat_name);
        //msdw = (TextView) findViewById(R.id.pat_sdw);
        //mage = (TextView) findViewById(R.id.pat_age);

        //mirs = (TextView) findViewById(R.id.txt_irs);
        //mtest = (TextView) findViewById(R.id.txt_test);
        //medate = (TextView) findViewById(R.id.txt_edate);
        //madmo_no = (TextView) findViewById(R.id.txt_adm_no);

        //mtitle = (TextView) findViewById(R.id.activity_title);
        //toobarTitle = (TextView) findViewById(R.id.toolbar_title);

        //mpat.setText(mrinfo.getPat_name() + "  [  " + mrinfo.getMr_code() + "  ]");
        //msdw.setText(mrinfo.getPat_fname());
        //mage.setText(mrinfo.getPat_age());
        //toobarTitle.setText("Result  [  "+mGroupDetail.getGroup_name()+"  ]");
        //mirs.setText(mGroupDetail.getLrs_no());
        //mtest.setText(mGroupDetail.getTest_name());

        //medate.setText(mGroupDetail.getEntry_time());
        //madmo_no.setText(mGroupDetail.getAdm_no());

    }

    public void view_web(String url)
    {
        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        myWebView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(MedImageActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        // Javascript inabled on webview
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setUseWideViewPort(true);


        // Other webview options
        myWebView.getSettings().setLoadsImagesAutomatically(true);


        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        myWebView.setScrollbarFadingEnabled(false);

        //myWebView.getSettings().setBuiltInZoomControls(true);


        /*
         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null);
         */

        //Load url in webview
        myWebView.loadUrl(url);
    }

    private void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
