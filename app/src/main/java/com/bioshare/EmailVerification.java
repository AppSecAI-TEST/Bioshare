package com.bioshare;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmailVerification extends CustomActivity {
    private Toolbar toolbar;
    private Button btn_verify_code;
    private EditText edt_code;
    private TextView tv_email_msg;
    private String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Reset Password");
        actionBar.setTitle("");
        Bundle extras = getIntent().getExtras();
        value = extras.getString("email").toString();
        setupuiElement();
    }


    private void setupuiElement() {

        setTouchNClick(R.id.btn_verify_code);

        edt_code = (EditText) findViewById(R.id.edt_code);
        tv_email_msg=(TextView)findViewById(R.id.tv_email_msg);
        final SpannableStringBuilder sb = new SpannableStringBuilder(value);
        tv_email_msg.setText("A code was sent to "+value);

        btn_verify_code = (Button) findViewById(R.id.btn_verify_code);
    }

    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_verify_code) {
            if (TextUtils.isEmpty(edt_code.getText().toString())) {
                edt_code.setError("Enter Email Id");
                return;
            }
            value = edt_code.toString();
            Intent intent = new Intent(EmailVerification.this,HomeActivity.class);
            intent.putExtra("ezy", value);
            startActivity(intent);

        }
    }

}
