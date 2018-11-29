package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class CheckInFragment extends Fragment {
    public CheckInFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_in, container, false);
    }

    // On click of the checked button in the Check-In screen, go to OrderFragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button showOrderFragment=(Button)getView().findViewById(R.id.checked);
        showOrderFragment.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                menuFragment menuFrag = new menuFragment();
                fragmentTransaction.replace(R.id.main_container, menuFrag);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                ((MainActivity) getActivity()).setCheckedIn(true);
            }
        });

    }

    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Check In");
    }
}