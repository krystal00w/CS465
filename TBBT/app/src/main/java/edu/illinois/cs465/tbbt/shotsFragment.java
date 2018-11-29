package edu.illinois.cs465.tbbt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class shotsFragment extends Fragment {
    public shotsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shots, container, false);

        final Button jameson = view.findViewById(R.id.jameson);
        jameson.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Jameson");
            }
        });

        final Button captain_morgan = view.findViewById(R.id.captain_morgan);
        captain_morgan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Captain Morgan");
            }
        });

        final Button new_amsterdam = view.findViewById(R.id.new_amsterdam);
        new_amsterdam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("New Amsterdam");
            }
        });

        final Button western_son = view.findViewById(R.id.western_son);
        western_son.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Western Son");
            }
        });
        return view;
    }

    private void passDrinkToOrder(String drink_name) {
        ((MainActivity) getActivity()).setDrinkName(drink_name);
        OrderFragment new_frag = new OrderFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new_frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
