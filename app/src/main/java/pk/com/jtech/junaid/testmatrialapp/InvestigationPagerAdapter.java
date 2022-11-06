package pk.com.jtech.junaid.testmatrialapp;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Junaid on 10/1/2015.
 */
public class InvestigationPagerAdapter extends FragmentStatePagerAdapter {

    public static String uid;
    public static String pas;
    public static String ipa;
    public static String mrCode;
    public static MrInfo mrInfo;

    public static int mrC;

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public InvestigationPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        mrC = 0;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:

                //Fragement for Android Tab

                FragAllGroup.uid = uid;
                FragAllGroup.ipa = ipa;
                FragAllGroup.mrCode = mrCode;
                FragAllGroup.mrinfo = mrInfo;
                FragAllGroup mFragAllGroup = new FragAllGroup();
                return mFragAllGroup;

                //return new Help_Pat();

            case 1:
                mrC = 2;
                //Fragment for Ios Tab

                FragAllTest.uid = uid;
                FragAllTest.ipa = ipa;
                FragAllTest.mrCode = mrCode;
                FragAllTest.mrinfo = mrInfo;
                FragAllTest mFragAllTest = new FragAllTest();
                return mFragAllTest;

                //return new Help_Pat();
            case 2:
                mrC = 3;
                //Fragment for Ios Tab

                FragIrs.uid = uid;
                FragIrs.ipa = ipa;
                FragIrs.mrCode = mrCode;
                FragIrs.mrinfo = mrInfo;
                FragIrs mFragIrs = new FragIrs();
                return mFragIrs;

                //return new Help_Pat();
        }
        return null;

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
