package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
                passDrinkToOrder("Jameson", 1.5, 1.5);
            }
        });

        final Button captain_morgan = view.findViewById(R.id.captain_morgan);
        captain_morgan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Captain Morgan", 2.0, 1.5);
            }
        });

        final Button new_amsterdam = view.findViewById(R.id.new_amsterdam);
        new_amsterdam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("New Amsterdam", 2.0, 1.0);
            }
        });

        final Button western_son = view.findViewById(R.id.western_son);
        western_son.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Western Son", 2.0, 1.5);
            }
        });
        return view;
    }

    private void passDrinkToOrder(String drink_name, double price, double upgrade) {
        OrderFragment new_frag = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", drink_name);
        bundle.putDouble("base", price);
        bundle.putDouble("upgrade", upgrade);
        new_frag.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new_frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Shots");
    }
}
