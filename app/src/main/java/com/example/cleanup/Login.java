package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button login;
    TextView register;
    EditText loginemail,loginpassword;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button)findViewById(R.id.login);
        register = (TextView)findViewById(R.id.gotoregister);
        loginemail = (EditText)findViewById(R.id.loginemail);
        loginpassword = (EditText)findViewById(R.id.loginpassword);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),Home.class));
//                overridePendingTransition(0,0);
                loginUser();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
                overridePendingTransition(0,0);
            }
        });
    }
    public void onBackPressed() {

        Intent intent = new Intent(Login.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }
    private void loginUser(){
        String Email = loginemail.getText().toString();
        String Pass = loginpassword.getText().toString();
            if(TextUtils.isEmpty(Email)){
                loginemail.setError("This Field is empty!");
                loginemail.requestFocus();
            }else if(TextUtils.isEmpty(Pass)){
                loginpassword.setError("This Field is empty!");
                loginpassword.requestFocus();
            }else{
                mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Successfully Login!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                            overridePendingTransition(0,0);
                        }else{
                            Toast.makeText(Login.this, "Login Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
    }
}