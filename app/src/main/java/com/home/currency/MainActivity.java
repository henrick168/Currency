package com.home.currency;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int TYPE_JP = 10;
    private static final int TYPE_US = 20;
    private float usd = 30.9f;
    private float jpy = 0.27f;
    private EditText edNTD;
    private TextView tvJP;
    private TextView tvUS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        edNTD = findViewById(R.id.etNTD);
        tvJP = findViewById(R.id.tvJP);
        tvUS = findViewById(R.id.tvUS);
        findViewById(R.id.btnGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset textView
                tvJP.setText(getString(R.string.text_none));
                tvUS.setText(getString(R.string.text_none));

                if (".".equals(edNTD.getText().toString()) ||
                        TextUtils.isEmpty(edNTD.getText().toString().trim())) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.text_problem)
                            .setMessage(R.string.text_please_enter_ntd)
                            .setPositiveButton(R.string.text_ok, null)
                            .show();
                } else {
                    float resultJP = getResult(TYPE_JP);
                    float resultUS = getResult(TYPE_US);
                    tvJP.setText(String.valueOf(resultJP));
                    tvUS.setText(String.valueOf(resultUS));
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.text_result)
                            .setMessage(String.format("%s %.4f", getString(R.string.text_usd_is), resultUS))
                            .setPositiveButton(R.string.text_ok, null)
                            .show();
                }
            }
        });
    }

    private float getResult(int Type) {
        float result = 0f;
        switch (Type) {
            case TYPE_JP:
                result = Float.parseFloat(edNTD.getText().toString()) / jpy;
                break;
            case TYPE_US:
                result = Float.parseFloat(edNTD.getText().toString()) / usd;
                break;
        }
        return result;
    }
}
