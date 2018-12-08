package edu.illinois.cs465.tbbt;

import android.os.Bundle;
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

import edu.illinois.cs465.tbbt.OrderMemory.Drink;

public class OrderFragment extends Fragment {
    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_order, container, false);
        final String name = this.getArguments().getString("name");
        TextView drink_title = v.findViewById(R.id.drink_title);
        TextView price = v.findViewById(R.id.price);
        final double base_price = this.getArguments().getDouble("base");
        final double double_price = this.getArguments().getDouble("upgrade");
        drink_title.setText(name);
        price.setText(String.format("$ " + "%.2f", base_price));
        RadioButton upgrade = v.findViewById(R.id.radio_double);
        upgrade.setText("double (+$" + String.format("%.2f", double_price) + ")");

        final Button placeOrder = v.findViewById(R.id.submit_order);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText quantity = v.findViewById(R.id.quantity_text);
                int q = Integer.parseInt(quantity.getEditableText().toString());
                EditText style = v.findViewById(R.id.style_text);
                String s = style.getEditableText().toString();
                RadioGroup rbg = v.findViewById(R.id.radio);
                int radioButtonID = rbg.getCheckedRadioButtonId();
                RadioButton radioButton = rbg.findViewById(radioButtonID);
                String choice  = radioButton.getText().toString();
                boolean c = !choice.equals("single");
                Drink order = new Drink(name, q, c, s, (base_price + (c ? double_price : 0)) * q);
                ((AppActivity)getActivity()).setCurrent_drink(order);
                /* BottomNavigationView navigation = ((MainActivity)getActivity()).getNavigation();
                navigation.setSelectedItemId(R.id.navigation_tab); */

                ConfirmOrderFragment new_frag = new ConfirmOrderFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new_frag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

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
