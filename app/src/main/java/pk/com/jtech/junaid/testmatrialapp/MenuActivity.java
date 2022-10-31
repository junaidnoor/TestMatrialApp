package pk.com.jtech.junaid.testmatrialapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;


public class MenuActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String eName;

    private int mNavItemId;
    private Activity activity = null;

    TextView mtitle;
    TextView lbl_app;
    TextView row_app;
    TextView lbl_rem;
    TextView mname;
    TextView toobarTitle;

    ArrayList<Employee> array_list;
    MrDatasource mMrDatasource;
    //VisitAdapter mVisitAdapter;
    //ListView mListView;

    Employee mEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mtitle = (TextView) findViewById(R.id.activity_title);
        toobarTitle = (TextView) findViewById(R.id.toolbar_title);
        toobarTitle.setText("E-Medical Record");
        mname = (TextView) findViewById(R.id.name);
        mMrDatasource = new MrDatasource();
        // Initializing Toolbar and setting it as the actionbar
        setupToolbar();
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Initializing NavigationView
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

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
                        android.support.v4.app.FragmentTransaction mFind_PatTransaction = getSupportFragmentManager().beginTransaction();
                        mFind_PatTransaction.replace(R.id.frame, mFind_Pat, null);
                        mFind_PatTransaction.commit();
                        return true;

                    case R.id.appointment:
                        mtitle.setText("Appointment");
                        Fragment mFragToDay = new FragToDay();
                        FragToDay.uid = uid;
                        FragToDay.ipa = ipa;
                        android.support.v4.app.FragmentTransaction mFragToDayTransaction = getSupportFragmentManager().beginTransaction();
                        mFragToDayTransaction.replace(R.id.frame, mFragToDay, null);
                        mFragToDayTransaction.commit();
                        return true;
                    case R.id.followup:
                        //Toast.makeText(getApplicationContext(),"Oup Patient / Follow-Up Selected",Toast.LENGTH_SHORT).show();
                        mtitle.setText("Follow-Up");
                        Fragment mFragAllPatient = new FragAllPatient();
                        FragAllPatient.uid = uid;
                        FragAllPatient.ipa = ipa;
                        android.support.v4.app.FragmentTransaction mFragAllPatientTransaction = getSupportFragmentManager().beginTransaction();
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
                        android.support.v4.app.FragmentTransaction mFragAdmissionTransaction = getSupportFragmentManager().beginTransaction();
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
                        android.support.v4.app.FragmentTransaction mFragDayCareTransaction = getSupportFragmentManager().beginTransaction();
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
                        android.support.v4.app.FragmentTransaction mFragICUTransaction = getSupportFragmentManager().beginTransaction();
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
                        android.support.v4.app.FragmentTransaction mFragCCUTransaction = getSupportFragmentManager().beginTransaction();
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
                        finish();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
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
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        Fragment find_pat = new Find_Pat();
        Find_Pat.uid = uid;
        Find_Pat.ipa = ipa;
        mtitle.setText("Home");
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, find_pat, null);
        fragmentTransaction.commit();
        poulate();
        //new asyncTask_getData().execute();
    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null)
            toolbar.setTitle("E-Medical Record");
        setSupportActionBar(toolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
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

    private void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void poulate() {
        /*
        mEmployee = array_list.get(0);
        mname.setText(mEmployee.getEmp_name());
        */
        mname.setText(eName);
    }

    private class asyncTask_getData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog mProgressDialog = new ProgressDialog(MenuActivity.this);

        @Override
        protected void onPreExecute() {
            mProgressDialog.setTitle("Please Wait...");
            mProgressDialog.setMessage("Data is loading");
            mProgressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            array_list = mMrDatasource.getEmp(uid, pas, ipa, "A");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialog.dismiss();
            poulate();
            super.onPostExecute(aVoid);
        }
    }
}
