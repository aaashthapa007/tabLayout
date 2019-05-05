package com.example.tablayout;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    EditText fname,lname,email,phone,username, password;
    RadioGroup gender;
    Button register;
    RadioButton male,female;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String getGender;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        fname=(EditText) view.findViewById(R.id.et_firstname);
        lname=(EditText) view.findViewById(R.id.et_lastname);
        email=(EditText) view.findViewById(R.id.et_email);
        phone=(EditText) view.findViewById(R.id.et_phone);
        username=(EditText) view.findViewById(R.id.et_username);
        password=(EditText) view.findViewById(R.id.et_password);
        register=(Button) view.findViewById(R.id.btn_submitregister);
        male=(RadioButton)view.findViewById(R.id.male);
        female=(RadioButton)view.findViewById(R.id.female);
        gender=(RadioGroup)view.findViewById(R.id.radio_group);
        register.setOnClickListener(this);
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.male){
                    getGender=male.getText().toString();
                }
                else if(checkedId==R.id.female){
                    getGender=female.getText().toString();
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submitregister:
                if (validate()){
                    preferences=getActivity().getSharedPreferences("Userinfo",Context.MODE_PRIVATE);
                    editor=preferences.edit();
                    String getfname=fname.getText().toString();
                    String getlname=lname.getText().toString();
                    String getemail=email.getText().toString();
                    String getphone=phone.getText().toString();
                    String getusername=username.getText().toString();
                    String getpassword=password.getText().toString();

                    editor.putString("FNAME",getfname).commit();
                    editor.putString("LNAME",getlname).commit();
                    editor.putString("EMAIL",getemail).commit();
                    editor.putString("PHONE",getphone).commit();
                    editor.putString("GENDER",getGender).commit();
                    editor.putString("USERNAME",getusername).commit();
                    editor.putString("PASSWORD",getpassword).commit();
                    Toast.makeText(getActivity(), "User Details Saved", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
                }
        }

    }
    private boolean validate(){
        if(TextUtils.isEmpty(fname.getText().toString())){
            fname.setError("Enter First name");
            fname.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(lname.getText().toString())){
            lname.setError("Enter Last name");
            lname.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("Enter Email");
            email.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(phone.getText().toString())){
            phone.setError("Enter Phone");
            phone.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(username.getText().toString())){
            username.setError("Enter Username");
            username.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password.getText().toString())){
            password.setError("Enter password");
            password.requestFocus();
            return false;
        }

        return true;
    }
}
