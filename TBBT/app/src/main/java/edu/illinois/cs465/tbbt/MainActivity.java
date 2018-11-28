package edu.illinois.cs465.tbbt;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import edu.illinois.cs465.tbbt.Discover.DiscoverFragment;

public class MainActivity extends AppCompatActivity {
    final Fragment fragment_order = new OrderFragment();
    final Fragment fragment_tab = new TabFragment();
    final Fragment fragment_discover = new DiscoverFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets up the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Sets up the bottom navigation bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, fragment_discover).hide(fragment_discover).commit();
        fm.beginTransaction().add(R.id.main_container, fragment_tab).hide(fragment_tab).commit();
        fm.beginTransaction().add(R.id.main_container,fragment_order).commit();
    }

    // Bottom navigation bar fragment selection
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_order:
                    fm.beginTransaction().hide(active).show(fragment_order).commit();
                    active = fragment_order;
                    return true;

                case R.id.navigation_tab:
                    fm.beginTransaction().hide(active).show(fragment_tab).commit();
                    active = fragment_tab;
                    return true;

                case R.id.navigation_discover:
                    fm.beginTransaction().hide(active).show(fragment_discover).commit();
                    active = fragment_discover;
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Fragment fragment_settings = new SettingsFragment();
            fm.beginTransaction().replace(R.id.main_container, fragment_settings).addToBackStack(null).commit();
            return true;
        }

        // If we got here, the user's action was not recognized, invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }

    public void setActive(Fragment frag) {
        active = frag;
    }
}
