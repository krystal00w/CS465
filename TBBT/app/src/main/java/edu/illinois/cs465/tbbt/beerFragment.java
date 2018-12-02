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
                passDrinkToOrder("Budweiser");
            }
        });

        final Button heineken = view.findViewById(R.id.heineken);
        heineken.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Heineken");
            }
        });

        final Button guiness = view.findViewById(R.id.guiness);
        guiness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Guiness");
            }
        });

        final Button blue_moon = view.findViewById(R.id.blue_moon);
        blue_moon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Blue Moon");
            }
        });

        final Button riggs = view.findViewById(R.id.riggs_hefeweizen);
        riggs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Rigg's Hefeweizen");
            }
        });

        return view;
    }

    private void passDrinkToOrder(String drink_name) {
        /**if (((MainActivity) getActivity()).getStage() == 0){
            ((MainActivity) getActivity()).setDrinkOneName(drink_name);
        }
        else if (((MainActivity) getActivity()).getStage() == 1){
            ((MainActivity) getActivity()).setDrinkTwoName(drink_name);
        }**/
        OrderFragment new_frag = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", drink_name);
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
