package pk.com.jtech.junaid.testmatrialapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Junaid on 9/19/2015.
 */
public class MDSTPagerAdapter extends FragmentStatePagerAdapter {

    public static String uid;
    public static String pas;
    public static String ipa;

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public MDSTPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                //Fragement for Android Tab
                FragAllPatient mFragAllPatient = new FragAllPatient();
                FragAllPatient.uid = uid;
                FragAllPatient.ipa = ipa;
                return mFragAllPatient;

            case 1:
                //Fragment for Ios Tab
                return new Help_Pat();

            case 2:
                //-------Fragment for Windows Tab
                Find_Pat mFind_Pat = new Find_Pat();
                Find_Pat.uid = uid;
                Find_Pat.ipa = ipa;
                return mFind_Pat;
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
