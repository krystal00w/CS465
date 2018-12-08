package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import android.widget.ListView;

import edu.illinois.cs465.tbbt.OrderMemory.Drink;

public class PickupFragment extends Fragment {

    public final static String DATA_TYPE = "DataType";
//    private ArrayList<String> drinks = new ArrayList<>();


    public PickupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        // pass arguments to a string array [drinks]
//        if (getArguments() != null){
//            drinks = getArguments().getStringArrayList(AppActivity.LISTVIEW_DATA);
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pickup, container, false);

        ListView listView = (ListView) view.findViewById(R.id.ready_list);
        ArrayList<Drink> ready = ((AppActivity)getActivity()).getReady();

        String[] ready_drinks = new String[ready.size()];
        for(int i = 0; i < ready.size(); i++){
            Drink drink = ready.get(i);
            String name = drink.drinkName;
            int quantity = drink.quantity;
            ready_drinks[i] = name + "\t\t\tx" + quantity;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.pickup_list_view,
                ready_drinks
        );
        listView.setAdapter(adapter);


        final Button pickup = view.findViewById(R.id.pickup);
        pickup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((AppActivity)getActivity()).pickUpDrinks();
                TabFragment new_frag = new TabFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new_frag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Pick Up");
    }
}