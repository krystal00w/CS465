package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class beerFragment extends Fragment {
    public beerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_beer, container, false);

        final Button budweiser = view.findViewById(R.id.budweiser);
        budweiser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Budweiser", 1.5, 1.0);
            }
        });

        final Button heineken = view.findViewById(R.id.heineken);
        heineken.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Heineken", 2.0, 1.0);
            }
        });

        final Button guiness = view.findViewById(R.id.guiness);
        guiness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Guiness", 2.0, 1.0);
            }
        });

        final Button blue_moon = view.findViewById(R.id.blue_moon);
        blue_moon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Blue Moon", 2.0, 1.5);
            }
        });

        final Button riggs = view.findViewById(R.id.riggs_hefeweizen);
        riggs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Rigg's Hefeweizen", 2.0, 1.5);
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Beer");
    }
}
