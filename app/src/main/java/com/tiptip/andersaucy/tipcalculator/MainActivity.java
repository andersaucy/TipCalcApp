package com.tiptip.andersaucy.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final double[] percentage = new double[1];
        RadioGroup tipSelect = (RadioGroup) findViewById(R.id.tipSelect);
        tipSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.fifteen:
                        percentage[0] = .15;
                        break;
                    case R.id.eighteen:
                        percentage[0] = .18;
                        break;
                }
            }
        });

        Button calcBtn = (Button) findViewById(R.id.calulate_button);
        final TextView instruction = (TextView) findViewById(R.id.instruction);
        final Button next = (Button) findViewById(R.id.secondActivityBtn);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText peopleText = (EditText) findViewById(R.id.people);
                EditText subtotalText = (EditText) findViewById(R.id.subtotal);
                EditText taxText = (EditText) findViewById(R.id.tax);
                TextView resultText = (TextView) findViewById(R.id.resultTextView);

                NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);

                double people = Double.parseDouble(peopleText.getText().toString());
                double subtotal = Double.parseDouble(subtotalText.getText().toString());
                double tax = Double.parseDouble(taxText.getText().toString());
                double result = ((subtotal * percentage[0]) + tax)/people;
                resultText.setText(dollarFormat.format(result));
                instruction.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        });

    }
}
