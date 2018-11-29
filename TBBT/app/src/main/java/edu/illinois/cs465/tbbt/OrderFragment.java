package edu.illinois.cs465.tbbt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        Button submit = v.findViewById(R.id.submit_order);
        final TextView test = v.findViewById(R.id.test_text);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**String q = ((EditText)v.findViewById(R.id.quantity_text)).getEditableText().toString();
                String style = ((EditText)v.findViewById(R.id.style_text)).getEditableText().toString();
                int radioButtonId = ((RadioGroup) v.findViewById(R.id.radio)).getCheckedRadioButtonId();
                String radio_text = (String)((RadioButton)((RadioGroup) v.findViewById(R.id.radio)).findViewById(radioButtonId)).getText();
                String string_test = q + "x " + radio_text + " " + style;**/
                test.setText("Order Submitted");
            }
        });
        return v;
    }
}
