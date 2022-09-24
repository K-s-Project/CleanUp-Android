package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {
    Button register;
    TextView login;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText fname,email,pass,cpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button)findViewById(R.id.register);
        login = (TextView)findViewById(R.id.gotologin);
        fname = (EditText)findViewById(R.id.fullname);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        cpass = (EditText)findViewById(R.id.cpassword);
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                overridePendingTransition(0,0);
            }
        });
    }
    public void onBackPressed() {

        Intent intent = new Intent(Register.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }
    private void createUser(){

        String Fullname = fname.getText().toString();
        String Email = email.getText().toString();
        String Pass = pass.getText().toString();
        String Cpass = cpass.getText().toString();
        if(Pass.equals(Cpass)){
            if(TextUtils.isEmpty(Email)){
                email.setError("This Field is empty!");
                email.requestFocus();
            }else if(TextUtils.isEmpty(Pass)){
                pass.setError("This Field is empty!");
                pass.requestFocus();
            }else if(TextUtils.isEmpty(Cpass)){
                cpass.setError("This Field is empty!");
                cpass.requestFocus();
            }else{

                mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String userid = mAuth.getCurrentUser().getUid();
                            RegClass regClass = new RegClass();
                            regClass.setUserid(userid);
                            regClass.setFullname(Fullname);
                            regClass.setEmail(Email);
                            regClass.setPass(Pass);
                            db.collection("users").document(userid).set(regClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Login.class));
                                        overridePendingTransition(0,0);
                                    }else{
                                        Toast.makeText(Register.this, "Register Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            //Toast.makeText(Register.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(Register.this, "Register Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();



            }
        }else{
            Toast.makeText(this, "Please check your password!", Toast.LENGTH_SHORT).show();
        }
    }
}