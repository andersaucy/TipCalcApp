package com.tiptip.andersaucy.tipcalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.tiptip.andersaucy.tipcalculator.ADD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final double[] values = new double[2];
        final NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);
        RadioGroup tipSelect = (RadioGroup) findViewById(R.id.tipSelect);
        tipSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.fifteen:
                        values[0] = .15;
                        break;
                    case R.id.eighteen:
                        values[0] = .18;
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

                double people, subtotal, tax, result;
                final String peopleString = peopleText.getText().toString();
                final String subtotalString = subtotalText.getText().toString();
                final String taxString = taxText.getText().toString();

                if (peopleString.isEmpty() || subtotalString.isEmpty() || taxString.isEmpty() || !(values[0]>0)){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_input),
                            Toast.LENGTH_LONG).show();
                }
                else{
                    people = Double.parseDouble(peopleString);
                    subtotal = Double.parseDouble(subtotalString);
                    tax = Double.parseDouble(taxString);

                    result = ((subtotal * values[0]) + tax)/people;
                    values[1] = result;
                    resultText.setTextColor(Color.rgb(133, 187, 101));
                    resultText.setText(dollarFormat.format(result));
                    instruction.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                String message = Double.toString(values[1]);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

    }

}
