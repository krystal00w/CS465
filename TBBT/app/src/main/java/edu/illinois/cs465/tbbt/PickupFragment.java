package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class PickupFragment extends Fragment {
    public PickupFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pickup, container, false);
    }

    // On click of the pickup button in the Pick-Up screen, go back to TabFragment
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button showTabFragment=(Button)getView().findViewById(R.id.pickup);
        showTabFragment.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                TabFragment tabFragment = new TabFragment();
                fragmentTransaction.replace(R.id.tab, tabFragment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }
}