package edu.illinois.cs465.tbbt;

import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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

import java.util.ArrayList;

import edu.illinois.cs465.tbbt.Discover.DiscoverFragment;
import edu.illinois.cs465.tbbt.OrderMemory.Drink;
import edu.illinois.cs465.tbbt.OrderMemory.ShakeDetector;

public class AppActivity extends AppCompatActivity {
    final Fragment fragment_check_in = new CheckInFragment();
    final Fragment fragment_order = new OrderFragment();
    final Fragment fragment_tab = new TabFragment();
    final Fragment fragment_discover = new DiscoverFragment();
    final Fragment fragment_settings = new SettingsFragment();
    final Fragment fragment_empty_tab = new EmptyTabFragment();
    final FragmentManager fm = getSupportFragmentManager();
    BottomNavigationView navigation = null;

    private boolean lightTheme = true;

    // Check in once only
    private boolean checkedIn = false;

    public boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean status) { checkedIn = status; }

    ArrayList<Drink> ready = null;
    ArrayList<Drink> being_made = null;
    ArrayList<Drink> picked_up = null;
    Drink current_drink = null;

    public Drink getCurrent_drink() { return current_drink; }

    public void setCurrent_drink(Drink drink){ current_drink = drink; }

    ArrayList<Drink> getReady(){
        return ready;
    }
    ArrayList<Drink> getBeing_made(){
        return being_made;
    }
    ArrayList<Drink> getPicked_up(){
        return picked_up;
    }

    public void moveSingleDrinkToReady() {
        if (being_made.size() > 0) {
            Drink d = being_made.get(0);
            being_made.remove(0);
            ready.add(d);
        }
    }

    public void placeOrder(Drink drink){
        being_made.add(drink);
    }

    public void pickUpDrinks(){
        while(ready.size() > 0){
            Drink d = ready.get(0);
            ready.remove(0);
            picked_up.add(d);
        }
    }

    public void emptyTab(){
        while(being_made.size() > 0){
            Drink d = being_made.get(0);
            being_made.remove(0);
        }
        while(ready.size() > 0){
            Drink d = ready.get(0);
            ready.remove(0);
        }
        while(picked_up.size() > 0){
            Drink d = picked_up.get(0);
            picked_up.remove(0);
        }
    }

    public BottomNavigationView getNavigation() { return navigation; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ready = new ArrayList<>();
        being_made = new ArrayList<>();
        picked_up = new ArrayList<>();

        // Sets up the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_24dp);

        // Sets up the bottom navigation bar
        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
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
                    if (!checkedIn)
                        fm.beginTransaction().replace(R.id.main_container, fragment_check_in).commit();
                    else
                        fm.beginTransaction().replace(R.id.main_container, new menuFragment()).commit();
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
        switch (item.getItemId()) {
            case R.id.action_settings:
                if (fragment_current != fragment_settings)
                    fm.beginTransaction().replace(R.id.main_container, fragment_settings).addToBackStack(null).commit();
                return true;
            case R.id.action_theme_toggle:
                lightTheme = !lightTheme;
                if (lightTheme) {
                    setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
                } else {
                    setTheme(R.style.TBBTTheme);
                }
                //this.recreate();
                return true;
        }
        // If we got here, the user's action was not recognized, invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    /*public void setActive(Fragment frag) {
        active = frag;
    }*/
}
