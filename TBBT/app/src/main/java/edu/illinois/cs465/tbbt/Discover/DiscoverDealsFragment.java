package edu.illinois.cs465.tbbt.Discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import edu.illinois.cs465.tbbt.R;

public class DiscoverDealsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_discover_deals, container, false);

        RelativeLayout legends_deal1 = (RelativeLayout) v.findViewById(R.id.legends_deal1);
        legends_deal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayBar("legends");
            }
        });

        RelativeLayout legends_deal2 = (RelativeLayout) v.findViewById(R.id.legends_deal2);
        legends_deal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayBar("legends");
            }
        });

        RelativeLayout lion_deal = (RelativeLayout) v.findViewById(R.id.lion_deal);
        lion_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayBar("lion");
            }
        });
        return v;
    }

    private void displayBar(String s){
        BarPreviewFragment new_frag = new BarPreviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("bar", s);
        new_frag.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new_frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}