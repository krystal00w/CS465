package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return v;
    }
}
