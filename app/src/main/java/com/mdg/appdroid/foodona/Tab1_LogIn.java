package com.mdg.appdroid.foodona;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ktubuntu on 26/1/18.
 */

public class Tab1_LogIn extends Fragment implements View.OnClickListener{


    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private ProgressDialog progressDialog;
    public EditText EMail, PW;
    public Button signin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.tab1_sign_in,container,false);

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getActivity(), CharityHotelList.class));


        }
        EMail = (EditText)v.findViewById(R.id.id_editEmail_signInActivity);
        PW = (EditText)v.findViewById(R.id.id_editPassword_signInActivity);
        signin = (Button)v.findViewById(R.id.id_signInButton_SignInActivity);
        progressDialog = new ProgressDialog(getActivity());
        signin.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == signin){
            userLogin();
        }

    }

    private void userLogin(){
        String email = EMail.getText().toString().trim();
        String password = PW.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(getActivity(),"Please enter E-mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(getActivity(),"Please enter Password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Logging in Please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){

                            startActivity(new Intent(getActivity(),CharityHotelList.class));
                        }

                        else{
                            Toast.makeText(getActivity(), "Incorrect Email or Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
