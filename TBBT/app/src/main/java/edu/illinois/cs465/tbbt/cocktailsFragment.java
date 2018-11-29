package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class cocktailsFragment extends Fragment {
    public cocktailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cocktails, container, false);

        final Button long_island = view.findViewById(R.id.long_island);
        long_island.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Long Island Iced Tea");
            }
        });

        final Button moscow_mule = view.findViewById(R.id.moscow_mule);
        moscow_mule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Moscow Mule");
            }
        });

        final Button margarita = view.findViewById(R.id.margarita);
        margarita.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Margarita");
            }
        });

        return view;
    }

    private void passDrinkToOrder(String drink_name) {
        ((MainActivity) getActivity()).setDrinkOneName(drink_name);
        OrderFragment new_frag = new OrderFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new_frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
