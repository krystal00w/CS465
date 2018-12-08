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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.illinois.cs465.tbbt.OrderMemory.Drink;

public class ConfirmOrderFragment extends Fragment {
    public ConfirmOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        final Drink cur_drink = ((AppActivity)getActivity()).getCurrent_drink();
        Button confirm = v.findViewById(R.id.confirm);
        TextView title = v.findViewById(R.id.name_quantity);
        title.setText(String.valueOf(cur_drink.quantity) + " x " + cur_drink.drinkName);
        TextView size = v.findViewById(R.id.size_confirm);
        size.setText("- " + (cur_drink.doubleShot ? "Double" : "Single"));
        TextView style = v.findViewById(R.id.style_confrim);
        if (!cur_drink.notes.equals(""))
            style.setText("- " + cur_drink.notes);
        else
            style.setText("");
        TextView total = v.findViewById(R.id.total);
        total.setText("Total $" + String.format("%.2f", cur_drink.price));
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((AppActivity)getActivity()).placeOrder(cur_drink);
                BottomNavigationView navigation = ((AppActivity)getActivity()).getNavigation();
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