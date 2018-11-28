package edu.illinois.cs465.tbbt.Discover;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import edu.illinois.cs465.tbbt.MainActivity;
import edu.illinois.cs465.tbbt.R;

public class BarPreviewFragment extends Fragment {
    String bar = null;
    public BarPreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = null;
        //((MainActivity)getActivity()).setActive(this);
        bar = getArguments().getString("bar");
        switch (bar) {
            case "legends":
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.legends_name);
                v = inflater.inflate(R.layout.legends_preview, container, false);
                break;
            case "lion":
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.lion_name);
                v = inflater.inflate(R.layout.lion_preview, container, false);
                break;
            case "murphys":
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.murphys_name);
                v = inflater.inflate(R.layout.murphys_preview, container, false);
                break;
            default:
                v = inflater.inflate(R.layout.fragment_bar_preview, container, false);
                break;
        }
        SeekBar seek = (SeekBar) v.findViewById(R.id.seek);
        seek.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return v;
    }
}
