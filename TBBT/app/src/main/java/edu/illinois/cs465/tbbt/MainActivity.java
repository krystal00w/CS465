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
    final Fragment fragment_check_in = new CheckInFragment();
    final Fragment fragment_order = new OrderFragment();
    final Fragment fragment_tab = new TabFragment();
    final Fragment fragment_discover = new DiscoverFragment();
    final Fragment fragment_settings = new SettingsFragment();
    final Fragment fragment_empty_tab = new EmptyTabFragment();
    final FragmentManager fm = getSupportFragmentManager();

    private String drinkOneName = "blah1";
    private String drinkTwoName = "blah2";

    private boolean paid = false;

    private int stage = 0;

    public String getDrinkOneName() {
        return drinkOneName;
    }

    public void setPaid() { paid = true; }
    public boolean getPaid() { return paid; }

    public void setDrinkOneName(String new_name) {
        drinkOneName = new_name;
        stage = 1;
        return;
    }

    public String getDrinkTwoName() {
        return drinkTwoName;
    }

    public void setDrinkTwoName(String new_name) {
        drinkTwoName = new_name;
        stage = 2;
        return;
    }

    public void incStage() {
        stage = 3;
        return;
    }

    public void incStage2() {
        stage = 4;
        return;
    }

    public int getStage() { return stage; }

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

        toolbar.setTitle(R.string.text_order);

        fm.beginTransaction().replace(R.id.main_container,fragment_check_in).commit();
    }

    // Bottom navigation bar fragment selection
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_order:
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fm.beginTransaction().replace(R.id.main_container, fragment_check_in).commit();
                    return true;

                case R.id.navigation_tab:
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fm.beginTransaction().replace(R.id.main_container, fragment_tab).commit();
                    return true;

                case R.id.navigation_discover:
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fm.beginTransaction().replace(R.id.main_container, fragment_discover).commit();
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
        Fragment fragment_current = fm.findFragmentById(R.id.main_container);
        if (item.getItemId() == R.id.action_settings && fragment_current != fragment_settings) {
            fm.beginTransaction().replace(R.id.main_container, fragment_settings).addToBackStack(null).commit();
            return true;
        }

        // If we got here, the user's action was not recognized, invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }

    /*public void setActive(Fragment frag) {
        active = frag;
    }*/
}
