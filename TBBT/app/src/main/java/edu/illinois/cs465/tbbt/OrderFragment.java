package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class OrderFragment extends Fragment {
    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        TextView drink_title = v.findViewById(R.id.drink_title);
        if (((MainActivity)getActivity()).getStage() == 1) {
            drink_title.setText(((MainActivity)getActivity()).getDrinkOneName());
        }
        else if (((MainActivity)getActivity()).getStage() == 2) {
            drink_title.setText(((MainActivity)getActivity()).getDrinkTwoName());
        }

        final Button placeOrder = v.findViewById(R.id.submit_order);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                BottomNavigationView navigation = ((MainActivity)getActivity()).getNavigation();
                navigation.setSelectedItemId(R.id.navigation_tab);

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.text_order);
    }
}
