package pk.com.jtech.junaid.testmatrialapp;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;



public class InvestigationActivity extends AppCompatActivity {

    // Declaring Your View and Variables
    public static String uid;
    public static String pas;
    public static String ipa;
    public static String mrCode;
    public static MrInfo mrinfo;

    public static int NOTIFI_ID=0;
    Toolbar toolbar;
    ViewPager pager;
    InvestigationPagerAdapter adapter;
    //SlidingTabLayout tabs;
    TabLayout tabs;
    CharSequence Titles[]={"Group","All Test","I.R.S"};
    int Numboftabs =3;

    ViewGroup viewg;
    TextView mpat;
    TextView msdw;
    TextView mage;
    TextView toobarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigation);

        init();
        setupToolbar();

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        InvestigationPagerAdapter.uid = uid;
        InvestigationPagerAdapter.ipa = ipa;
        InvestigationPagerAdapter.mrCode = mrCode;
        InvestigationPagerAdapter.mrInfo = mrinfo;
        adapter =  new InvestigationPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager1);
        pager.setAdapter(adapter);

        tabs = (TabLayout) findViewById(R.id.tabs1);

        //tabs.setViewPager(pager);
        tabs.setupWithViewPager(pager);

    }

    private void init() {

        //LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View rowView = inflater.inflate(R.layout.tool_bar, viewg, false);

        mpat = (TextView) findViewById(R.id.pat_name);
        msdw = (TextView) findViewById(R.id.pat_sdw);
        mage = (TextView) findViewById(R.id.pat_age);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);

        mpat.setText(mrinfo.getPat_name() + " [ " + mrinfo.getMr_code() + " ]");
        msdw.setText(mrinfo.getPat_fname());
        mage.setText(mrinfo.getPat_age());
        toobarTitle.setText("Investigation View");
    }

    @SuppressLint("SuspiciousIndentation")
    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            toolbar.setTitle("Investigation View");
            setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_investigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
