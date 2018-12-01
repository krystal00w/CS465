package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TabFragment extends Fragment {
    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_tab, container, false);

        ((TextView)view.findViewById(R.id.drink_ready_name)).setText(((MainActivity)getActivity()).getDrinkOneName());
        ((TextView)view.findViewById(R.id.in_progress_name)).setText(((MainActivity)getActivity()).getDrinkTwoName());
        ((TextView)view.findViewById(R.id.completed_name)).setText(((MainActivity)getActivity()).getDrinkTwoName());

        ListView listView1 = (ListView)view.findViewById(R.id.completed_list);
        String[] completed_drinks = {"Drink 2  x1  $2.99"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
            getActivity(),
            android.R.layout.simple_list_item_1,
            completed_drinks
        );

        ListView listView2 = (ListView)view.findViewById(R.id.ready_list);
        String[] ready_drinks = {"Drink 1  x1  $2.99", "Drink 4 x1 $2.99"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                ready_drinks
        );

        ListView listView3 = (ListView)view.findViewById(R.id.in_progress_list);
        String[] in_progress_drinks = {"2m Drink 3  x1  $2.99"};

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                in_progress_drinks
        );

        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);
        listView3.setAdapter(adapter3);

        final Button pick_up_button = view.findViewById(R.id.pick_up_button);
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

        return view;

        /*if (((MainActivity)getActivity()).getStage() == -1){
            view = inflater.inflate(R.layout.fragment_not_checked_in, container, false);
        }

        if (((MainActivity)getActivity()).getStage() == 0){
            view = inflater.inflate(R.layout.fragment_empty_tab, container, false);
            final Button close_tab = view.findViewById(R.id.close_tab);
            close_tab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    ((MainActivity)getActivity()).setCheckedIn(false);
                    TabFragment new_frag = new TabFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new_frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        else if (((MainActivity)getActivity()).getStage() == 1){
            view = inflater.inflate(R.layout.fragment_tab1, container, false);
            ((TextView)view.findViewById(R.id.in_prog)).setText(((MainActivity)getActivity()).getDrinkOneName());
        }
        else if (((MainActivity)getActivity()).getStage() == 2){
            view = inflater.inflate(R.layout.fragment_tab2, container, false);
            ((TextView)view.findViewById(R.id.rdy)).setText(((MainActivity)getActivity()).getDrinkOneName());
            ((TextView)view.findViewById(R.id.in_prog)).setText(((MainActivity)getActivity()).getDrinkTwoName());
            final Button pick_up_button = view.findViewById(R.id.pick_up_button);
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
        else if (((MainActivity)getActivity()).getStage() == 3){
            view = inflater.inflate(R.layout.fragment_tab3, container, false);
            ((TextView)view.findViewById(R.id.cmpl_1)).setText(((MainActivity)getActivity()).getDrinkOneName());
            ((TextView)view.findViewById(R.id.cmpl_2)).setText(((MainActivity)getActivity()).getDrinkTwoName());
            final Button pay_subtotal_button = view.findViewById(R.id.pay_subtotal);
            pay_subtotal_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    ((MainActivity)getActivity()).resetStage();
                    TabFragment new_frag = new TabFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new_frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        // Inflate the layout for this fragment
        return view;*/
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.text_tab);
        //getActivity().getActionBar().setTitle(R.string.text_tab);
    }
}
