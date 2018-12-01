package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TabFragment extends Fragment {
    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if (!((MainActivity)getActivity()).getCheckedIn()){
            view = inflater.inflate(R.layout.fragment_not_checked_in, container, false);
        }

        else if (((MainActivity)getActivity()).getReady().size() == 0 && ((MainActivity)getActivity()).getBeing_made().size() == 0 && ((MainActivity)getActivity()).getPicked_up().size() == 0){
            view = inflater.inflate(R.layout.fragment_empty_tab, container, false);
            final Button close_tab = view.findViewById(R.id.close_tab);
            close_tab.setVisibility(View.VISIBLE);
            close_tab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    ((MainActivity)getActivity()).setCheckedIn(false);
                    TabFragment new_frag = new TabFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new_frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        else {
            view = inflater.inflate(R.layout.fragment_tab, container, false);
            if (((MainActivity)getActivity()).getReady().size() > 0){
                final Button pick_up_button = view.findViewById(R.id.pick_up_button);
                pick_up_button.setVisibility(View.VISIBLE);
                pick_up_button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        PickupFragment new_frag = new PickupFragment();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new_frag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
            }
            else if (((MainActivity)getActivity()).getReady().size() == 0 && ((MainActivity)getActivity()).getBeing_made().size() == 0){
                final Button pay_subtotal_button = view.findViewById(R.id.pay_subtotal);
                pay_subtotal_button.setVisibility(View.VISIBLE);
                pay_subtotal_button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        ((MainActivity)getActivity()).emptyTab();
                        TabFragment new_frag = new TabFragment();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new_frag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
            }
        }


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.text_tab);
        //getActivity().getActionBar().setTitle(R.string.text_tab);
    }
}
