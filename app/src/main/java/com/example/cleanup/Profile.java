package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleanup.model.RoomModel;
import com.example.cleanup.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.Collections;

public class Profile extends AppCompatActivity {
    BottomNavigationView bnv;
    EditText fullnames,password,email;
    TextView hfullname,hemail;
    Button update;
    FirebaseAuth mAuth;
    String userid;
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullnames = findViewById(R.id.fullname);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        hfullname = findViewById(R.id.headfullname);
        hemail = findViewById(R.id.heademail);
        update = findViewById(R.id.save);
        logout = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bnv.setSelectedItemId(R.id.profile);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.events:
                        startActivity(new Intent(getApplicationContext(),Events.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(Profile.this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton("Yes", null)
                        .setNegativeButton("Cancel", null)
                        .show();

                Button positivebutton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positivebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAuth.signOut();
                        startActivity(new Intent(getApplicationContext(),LogoutProgressBar.class));
                        overridePendingTransition(0,0);
                    }
                });
            }
        });
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fullnamel = fullnames.getText().toString();
                    String passwordl = password.getText().toString();
                    //
                    String uuiidd = mAuth.getCurrentUser().getUid();
                    DocumentReference docRef = db.collection("users").document(uuiidd);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                UserModel room = document.toObject(UserModel.class);
                                String pass = room.getPass();
                                String fullnameee = room.getFullname();

                                if(passwordl.equals(pass)){
                                    ///update sa Firestore start
                                    String uuiiddss = mAuth.getCurrentUser().getUid();
                                    db.collection("users").document(uuiiddss)
                                            .update(
                                                    "fullname", fullnamel
                                                    //"pass", passwordl
                                            )
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(Profile.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getApplicationContext(),Home.class));
                                                    overridePendingTransition(0,0);
                                                }
                                            });
                                    ///update sa Firestore end
                                }else if(fullnamel.equals(fullnameee)){

                                    String uuiiddssgg = mAuth.getCurrentUser().getUid();
                                    db.collection("users").document(uuiiddssgg)
                                            .update(
                                                    //"fullname", fullnamel,
                                                    "pass", passwordl
                                            )
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(Profile.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getApplicationContext(),Home.class));
                                                    overridePendingTransition(0,0);
                                                }
                                            });

                                    mAuth.getCurrentUser().updatePassword(passwordl).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Profile.this, "Successfully Updated in Auth!", Toast.LENGTH_SHORT).show();
                                        }

                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Profile.this, "Please Re-Login your Account and Change your Password again in Profile!", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(),Login.class));
                                            overridePendingTransition(0,0);
                                        }
                                    });
                                }else{
                                    String uuiiddssgg = mAuth.getCurrentUser().getUid();
                                    db.collection("users").document(uuiiddssgg)
                                            .update(
                                                    "fullname", fullnamel,
                                                    "pass", passwordl
                                            )
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(Profile.this, "Successfully Updated!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getApplicationContext(),Home.class));
                                                    overridePendingTransition(0,0);
                                                }
                                            });

                                    mAuth.getCurrentUser().updatePassword(passwordl).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Profile.this, "Successfully Updated in Auth!", Toast.LENGTH_SHORT).show();
                                        }

                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Profile.this, "Please Re-Login your Account and Change your Password again in Profile!", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(),Login.class));
                                            overridePendingTransition(0,0);
                                        }
                                    });
                                }

                            } else {
                                Toast.makeText(Profile.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //


                }
            });

        String uuiidd = mAuth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("users").document(uuiidd);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    UserModel room = document.toObject(UserModel.class);
                    String fname = room.getFullname();
                    String emaill = room.getEmail();
                    String pass = room.getPass();
                    hfullname.setText(fname);
                    hemail.setText(emaill);
                    fullnames.setText(fname);
                    email.setText(emaill);
                    password.setText(pass);
                } else {
                    Toast.makeText(Profile.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        DocumentReference docRef = db.collection("users").document(uuiidd);
//        Source source = Source.CACHE;
//        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    UserModel room = document.toObject(UserModel.class);
//                    String fname = room.getFullname();
//                    String emaill = room.getEmail();
//                    String pass = room.getPass();
//                    fullnames.setText(fname);
//                    email.setText(emaill);
//                    password.setText(pass);
//                } else {
//                    Toast.makeText(Profile.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    public void onBackPressed() {

        Intent intent = new Intent(Profile.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }
}