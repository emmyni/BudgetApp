package com.example.budgetapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    Button mButton;
    Context mContext;
    EditText inputEmail, inputPassword, inputConfirmPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        inputEmail = (EditText)view.findViewById(R.id.et_email);
        inputPassword = (EditText)view.findViewById(R.id.et_password);
        inputConfirmPassword = (EditText)view.findViewById(R.id.et_repassword);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mContext = getContext();

        mButton = (Button)view.findViewById(R.id.btn_register);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PerformAuth();
            }

        });
        return view;
    }

    private void PerformAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (!email.matches(emailPattern)) {
            inputEmail.setError("Enter correct email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("Enter proper password");
        } else if (!password.equals(confirmPassword)) {
            inputConfirmPassword.setError("Passwords don't match");
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        SendUserToNextActivity();
                        Toast.makeText(mContext, "Registration Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void SendUserToNextActivity() {
        Intent intent = new Intent(mContext, CalendarPage.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("uid", mUser.getUid());
        mContext.startActivity(intent);
    }

}
