package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
                passDrinkToOrder("Long Island Iced Tea", 2.5, 1.5);
            }
        });

        final Button moscow_mule = view.findViewById(R.id.moscow_mule);
        moscow_mule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Moscow Mule", 2.5, 1.0);
            }
        });

        final Button margarita = view.findViewById(R.id.margarita);
        margarita.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                passDrinkToOrder("Margarita", 2.0, 1.5);
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Cocktails");
    }
}
