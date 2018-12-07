package edu.illinois.cs465.tbbt;

/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class beerRecyclerViewAdapter extends RecyclerView.Adapter<beerRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "beerRecyclerViewAdapter";

    public static String[] mDataSet;
    private static FragmentActivity activity_fragment;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View v) {

            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    Log.d(TAG, mDataSet[0]);

                    FragmentTransaction ft = (activity_fragment).getSupportFragmentManager().beginTransaction();
                    passDrinkToOrder(ft, mDataSet[getAdapterPosition()]);
                }
            });
            textView = (TextView) v.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public beerRecyclerViewAdapter(String[] dataSet, FragmentActivity fa) {
        Log.d(TAG, "Beer Recycler Created");
        mDataSet = dataSet;
        this.activity_fragment = fa;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_menu_item, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataSet[position]);
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    private static void passDrinkToOrder(FragmentTransaction ft, String drink_name) {
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
        ft.replace(R.id.main_container, new_frag).addToBackStack(null).commit();
    }
}