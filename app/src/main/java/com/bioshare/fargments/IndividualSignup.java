package com.bioshare.fargments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bioshare.MyApp;
import com.bioshare.R;
import com.bioshare.SharedPreferenceHelper;


public class IndividualSignup extends Fragment {

    SharedPreferenceHelper sharedPreferenceHelper;
    EditText mobileNumberEdit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_individual_signup,container,false);
        mobileNumberEdit=(EditText)view.findViewById(R.id.individualMobileEditText);
        mobileNumberEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile =mobileNumberEdit.getText().toString();
                Log.d("fragment","mobile"+mobile);
                MyApp.setSharedPrefString("MOBILE",mobile);
            }
        });
        return view;
    }
}
