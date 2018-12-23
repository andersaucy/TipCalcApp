package com.tiptip.andersaucy.tipcalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);

        if (getIntent().hasExtra("com.tiptip.andersaucy.tipcalculator.ADD")){

            EditText addText = (EditText) findViewById(R.id.addText);
            addText.setKeyListener(null);
            addText.setTextColor(Color.rgb(133, 187, 101));

            String text = getIntent().getExtras().getString("com.tiptip.andersaucy.tipcalculator.ADD");
            final double addend = Double.parseDouble(text);
            addText.setText(dollarFormat.format(addend));

            Button addBtn = (Button) findViewById(R.id.addBtn);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText a1Text = (EditText) findViewById(R.id.a1);
                    EditText a2Text = (EditText) findViewById(R.id.a2);
                    EditText a3Text = (EditText) findViewById(R.id.a3);
                    EditText a4Text = (EditText) findViewById(R.id.a4);
                    TextView paymentText = (TextView) findViewById(R.id.payAmount);

                    final String[] inputVal = {a1Text.getText().toString(),
                                                a2Text.getText().toString(),
                                                a3Text.getText().toString(),
                                                a4Text.getText().toString()};
                    double sum = 0.00;
                    for (int i = 0; i < inputVal.length; i++){
                        if (!inputVal[i].isEmpty()){
                            sum += Double.parseDouble(inputVal[i]);
                        }
                    }
                    double payment = sum + addend;
                    paymentText.setTextColor(Color.rgb(133, 187, 101));
                    paymentText.setText(dollarFormat.format(payment));
                }
            });
        }
    }

}
