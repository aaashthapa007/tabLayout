package com.example.tablayout;


import android.app.ProgressDialog;
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
public class LoginFragment extends Fragment implements View.OnClickListener{
    EditText username, password;
    Button login;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Boolean isLoggedIn=false;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        username=(EditText) view.findViewById(R.id.et_username);
        password=(EditText) view.findViewById(R.id.et_password);
        login=(Button) view.findViewById(R.id.btn_submit);
        login.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                if (validate()){
                    preferences=getActivity().getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                    editor=preferences.edit();
                    String getusername=username.getText().toString();
                    String getpassword=password.getText().toString();
                    String getSharedusername=preferences.getString("USERNAME","");
                    String getSharedpassword=preferences.getString("PASSWORD","");

                    if (getusername.equals(getSharedusername) && getpassword.equals(getSharedpassword)){
                        isLoggedIn=true;
                        editor.putBoolean("isLoggedIn",isLoggedIn).commit();
                        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getActivity(),RecyclerViewActivity.class);
                        //progress bar
                        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
                        progressDialog.setTitle("Online Clothing Shopping App");
                        progressDialog.setMessage("Please Wait");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        //ignore this code just below only
                        //progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        //finish progress bar
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else{

                        Toast.makeText(getActivity(), "Username and Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
                }
        }

    }
    private boolean validate(){
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
