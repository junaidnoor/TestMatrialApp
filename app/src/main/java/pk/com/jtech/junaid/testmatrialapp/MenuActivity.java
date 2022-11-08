package pk.com.jtech.junaid.testmatrialapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String eName;

    //private int mNavItemId;
    //private Activity activity = null;

    TextView mtitle;
    //TextView lbl_app;
    //TextView row_app;
    //TextView lbl_rem;
    TextView mname;
    TextView toobarTitle;

    ArrayList<Employee> array_list;
    MrDatasource mMrDatasource;
    //VisitAdapter mVisitAdapter;
    //ListView mListView;

    //Employee mEmployee;

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mtitle = findViewById(R.id.activity_title);
        toobarTitle = findViewById(R.id.toolbar_title);
        toobarTitle.setText("E-Medical Record");
        mname = findViewById(R.id.name);
        mMrDatasource = new MrDatasource();
        // Initializing Toolbar and setting it as the actionbar
        setupToolbar();
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Initializing NavigationView
        mNavigationView = findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        // This method will trigger on item Click of navigation menu
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {

            /*
            //Checking if the item is in checked state or not, if not make it in checked state
            if(menuItem.isChecked()) menuItem.setChecked(false);
            else menuItem.setChecked(true);
            */

            Menu m = mNavigationView.getMenu();
            for (int i=0;i<m.size();i++) {
                MenuItem mi = m.getItem(i);
                if (!(mi.getItemId() == menuItem.getItemId())) {
                    mi.setCheckable(false);
                    //mi.setChecked(false);
                }
            }
            menuItem.setCheckable(true);
            menuItem.setChecked(true);

            //Closing drawer on item click
            mDrawerLayout.closeDrawers();

            //Check to see which item was being clicked and perform appropriate action
            switch (menuItem.getItemId()){

                //Replacing the main content with ContentFragment Which is our Inbox View;
                case R.id.home:
                    mtitle.setText("Home");
                    Fragment mFind_Pat = new Find_Pat();
                    Find_Pat.uid = uid;
                    Find_Pat.ipa = ipa;

                    androidx.fragment.app.FragmentTransaction mFind_PatTransaction = getSupportFragmentManager().beginTransaction();
                    mFind_PatTransaction.replace(R.id.frame, mFind_Pat, null);
                    mFind_PatTransaction.commit();
                    return true;

                case R.id.appointment:
                    mtitle.setText("Appointment");
                    Fragment mFragToDay = new FragToDay();
                    FragToDay.uid = uid;
                    FragToDay.ipa = ipa;
                    androidx.fragment.app.FragmentTransaction mFragToDayTransaction = getSupportFragmentManager().beginTransaction();
                    mFragToDayTransaction.replace(R.id.frame, mFragToDay, null);
                    mFragToDayTransaction.commit();
                    return true;
                case R.id.followup:
                    //Toast.makeText(getApplicationContext(),"Oup Patient / Follow-Up Selected",Toast.LENGTH_SHORT).show();
                    mtitle.setText("Follow-Up");
                    Fragment mFragAllPatient = new FragAllPatient();
                    FragAllPatient.uid = uid;
                    FragAllPatient.ipa = ipa;
                    androidx.fragment.app.FragmentTransaction mFragAllPatientTransaction = getSupportFragmentManager().beginTransaction();
                    mFragAllPatientTransaction.replace(R.id.frame, mFragAllPatient, null);
                    mFragAllPatientTransaction.commit();
                    return true;
                case R.id.admited:
                    mtitle.setText("Admited");
                    Fragment mFragAdmission = new FragAdmission();
                    FragAdmission.uid = uid;
                    FragAdmission.ipa = ipa;
                    FragAdmission.frag=2;
                    FragAdmission.stat="A";
                    androidx.fragment.app.FragmentTransaction mFragAdmissionTransaction = getSupportFragmentManager().beginTransaction();
                    mFragAdmissionTransaction.replace(R.id.frame, mFragAdmission, null);
                    mFragAdmissionTransaction.commit();
                    return true;
                case R.id.daycare:
                    mtitle.setText("Daycare");
                    Fragment mFragDayCare = new FragAdmission();
                    FragAdmission.uid = uid;
                    FragAdmission.ipa = ipa;
                    FragAdmission.frag=2;
                    FragAdmission.stat="D";
                    androidx.fragment.app.FragmentTransaction mFragDayCareTransaction = getSupportFragmentManager().beginTransaction();
                    mFragDayCareTransaction.replace(R.id.frame, mFragDayCare, null);
                    mFragDayCareTransaction.commit();
                    return true;
                case R.id.icu:
                    mtitle.setText("I.C,U");
                    Fragment mFragICU = new FragAdmission();
                    FragAdmission.uid = uid;
                    FragAdmission.ipa = ipa;
                    FragAdmission.frag=2;
                    FragAdmission.stat="I";
                    androidx.fragment.app.FragmentTransaction mFragICUTransaction = getSupportFragmentManager().beginTransaction();
                    mFragICUTransaction.replace(R.id.frame, mFragICU, null);
                    mFragICUTransaction.commit();
                    return true;
                case R.id.ccu:
                    mtitle.setText("C.C,U");
                    Fragment mFragCCU = new FragAdmission();
                    FragAdmission.uid = uid;
                    FragAdmission.ipa = ipa;
                    FragAdmission.frag=2;
                    FragAdmission.stat="C";
                    androidx.fragment.app.FragmentTransaction mFragCCUTransaction = getSupportFragmentManager().beginTransaction();
                    mFragCCUTransaction.replace(R.id.frame, mFragCCU, null);
                    mFragCCUTransaction.commit();
                    return true;
                case R.id.theme:
                    mtitle.setText("Themes");
                    return true;
                case R.id.logout:
                    Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                    LoginActivity.ipa = ipa;
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.exit:
                    //finish();
                    System.exit(0);
                    finishAffinity();

                    return true;
                default:
                    Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                    return true;

            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        mDrawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        //noinspection deprecation
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        Fragment find_pat = new Find_Pat();
        Find_Pat.uid = uid;
        Find_Pat.ipa = ipa;
        mtitle.setText("Home");
        androidx.fragment.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, find_pat, null);
        fragmentTransaction.commit();
        //poulate();
        //new asyncTask_getData().execute();
    }

    private void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        if(toolbar != null)
            toolbar.setTitle("E-Medical Record");
        setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

       if ((keyCode == KeyEvent.KEYCODE_BACK)) {
           return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    //--------------------------- Log Out Setion ---------------------------//

    //private void showMessage(String message)
    //{
    //    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    //}

    private void poulate() {

        mname.setText(eName);
    }
}
