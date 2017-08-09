package com.bioshare;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends CustomActivity {
    private Toolbar toolbar;
    private Button btn_send_code;
    private EditText edt_email;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Reset Password");
        actionBar.setTitle("");
        setupuiElement();
    }


    private void setupuiElement() {

        setTouchNClick(R.id.btn_send_code);

        edt_email = (EditText) findViewById(R.id.edt_email);

        btn_send_code = (Button) findViewById(R.id.btn_send_code);
    }

    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_send_code) {
            if (TextUtils.isEmpty(edt_email.getText().toString())) {
                edt_email.setError("Enter Email Id");
                return;
            }
            value = edt_email.getText().toString();
            Intent intent = new Intent(ResetActivity.this,EmailVerification.class);
            intent.putExtra("email", value);
            startActivity(intent);

        }
    }


}
