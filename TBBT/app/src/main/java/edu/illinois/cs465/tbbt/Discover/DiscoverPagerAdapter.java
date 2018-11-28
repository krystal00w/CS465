package edu.illinois.cs465.tbbt.Discover;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DiscoverPagerAdapter extends FragmentPagerAdapter {

    int numberOfTabs;

    public DiscoverPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DiscoverBarsFragment tab1 = new DiscoverBarsFragment();
                return tab1;
            case 1:
                DiscoverDealsFragment tab2 = new DiscoverDealsFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}

