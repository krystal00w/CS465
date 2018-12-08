package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import edu.illinois.cs465.tbbt.OrderMemory.Drink;


public class TabFragment extends Fragment {
    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;

        if (!((AppActivity)getActivity()).getCheckedIn()){
            view = inflater.inflate(R.layout.fragment_not_checked_in, container, false);
        }

        else if (((AppActivity)getActivity()).getReady().size() == 0 && ((AppActivity)getActivity()).getBeing_made().size() == 0 && ((AppActivity)getActivity()).getPicked_up().size() == 0){
            view = inflater.inflate(R.layout.fragment_empty_tab, container, false);
            final Button close_tab = view.findViewById(R.id.close_tab);
            close_tab.setVisibility(View.VISIBLE);
            close_tab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    ((AppActivity)getActivity()).setCheckedIn(false);
                    TabFragment new_frag = new TabFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new_frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        else {
            view = inflater.inflate(R.layout.fragment_tab, container, false);
            ArrayList<Drink> ready = ((AppActivity)getActivity()).getReady();
            ArrayList<Drink> being_made = ((AppActivity)getActivity()).getBeing_made();
            ArrayList<Drink> picked_up = ((AppActivity)getActivity()).getPicked_up();

            ListView picked_up_list = (ListView)view.findViewById(R.id.completed_list);
            String[] completed_drinks = new String[picked_up.size()];
            if(picked_up.size()==0){
                completed_drinks = new String[1];
                completed_drinks[0] = "No drinks are currently completed.";
            }
            double sub_total = 0.00;
            for(int i=0; i<picked_up.size(); i++){
                Drink drink = picked_up.get(i);
                String name = drink.drinkName;
                int quantity = drink.quantity;
                double price = drink.price;
                boolean doubleShot = drink.doubleShot;
                String dbl = (doubleShot ? " (double)" : "");
                String notes = (drink.notes.length()==0 ? "" : "- "+drink.notes);
                sub_total+= (price * (doubleShot ? 2 : 1) * quantity);
                String format = "%s%s\n";
                String order = String.format(format, (name + dbl + " x" + quantity), ("\t\t\t$"+String.format("%.2f", price)));
                completed_drinks[i] = order + "\t\t\t" + notes;
                //completed_drinks[i] = name + dbl +" x" + quantity + "\t\t\t$" + String.format("%.2f", price) + "\n\t\t\t- " + drink.notes;
            }
            ArrayAdapter<String> completed_drinks_adapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    completed_drinks
            );

            ListView ready_list = (ListView)view.findViewById(R.id.ready_list);
            String[] ready_drinks = new String[ready.size()];
            if(ready.size()==0){
                ready_drinks = new String[1];
                ready_drinks[0] = "No drinks are currently ready.";
            }
            for(int i=0; i<ready.size(); i++){
                Drink drink = ready.get(i);
                String name = drink.drinkName;
                int quantity = drink.quantity;
                double price = drink.price;
                boolean doubleShot = drink.doubleShot;
                String dbl = (doubleShot ? " (double)" : "");
                String notes = (drink.notes.length()==0 ? "" : "- "+drink.notes);
                String format = "%s%s\n";
                String order = String.format(format, (name + dbl + " x" + quantity), ("\t\t\t$"+String.format("%.2f", price)));
                ready_drinks[i] = order + "\t\t\t" + notes;
                //ready_drinks[i] = name + dbl + " x" + quantity + "\t\t\t$" + String.format("%.2f", price) + "\n\t\t\t- " + drink.notes;
            }
            ArrayAdapter<String> ready_drinks_adapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    ready_drinks
            );

            ListView being_made_list = (ListView)view.findViewById(R.id.in_progress_list);

            String[] in_progress_drinks = new String[being_made.size()];
            if(being_made.size()==0){
                in_progress_drinks = new String[1];
                in_progress_drinks[0] = "No drinks are currently in progress.";
            }
            for(int i=0; i<being_made.size(); i++){
                Drink drink = being_made.get(i);
                String name = drink.drinkName;
                int quantity = drink.quantity;
                double price = drink.price;
                boolean doubleShot = drink.doubleShot;
                String dbl = (doubleShot ? " (double)" : "");
                String notes = (drink.notes.length()==0 ? "" : "- "+drink.notes);
                String format = "%s%s\n";
                String order = String.format(format, (name + dbl + " x" + quantity), ("\t\t\t$"+String.format("%.2f", price)));
                in_progress_drinks[i] = order + "\t\t\t" + notes;
                //in_progress_drinks[i] = name + dbl + " x" + quantity + "\t\t\t$" + String.format("%.2f", price) + "\n\t\t\t- " + drink.notes;
            }

            ArrayAdapter<String> in_progress_drinks_adapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    in_progress_drinks
            );

            picked_up_list.setAdapter(completed_drinks_adapter);
            ready_list.setAdapter(ready_drinks_adapter);
            being_made_list.setAdapter(in_progress_drinks_adapter);

            if (((AppActivity)getActivity()).getReady().size() > 0) {
                final Button pick_up_button = view.findViewById(R.id.pick_up_button);
                pick_up_button.setVisibility(View.VISIBLE);
                pick_up_button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        PickupFragment new_frag = new PickupFragment();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new_frag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
            }

            else if (((AppActivity)getActivity()).getReady().size() == 0 && ((AppActivity)getActivity()).getBeing_made().size() == 0) {
                final Button pay_subtotal_button = view.findViewById(R.id.pay_subtotal);
                pay_subtotal_button.setVisibility(View.VISIBLE);
                pay_subtotal_button.setText("Pay Subtotal $" + String.format("%.2f", sub_total));
                pay_subtotal_button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        ((AppActivity) getActivity()).emptyTab();
                        TabFragment new_frag = new TabFragment();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new_frag);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
            }
        }


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.text_tab);
        //getActivity().getActionBar().setTitle(R.string.text_tab);
    }

}
