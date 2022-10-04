package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleanup.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class HomeSecond extends AppCompatActivity {
    BottomNavigationView bnv;
    ImageView backhome;

    TextView building_number;
    TextView building_name;
    TextView schedule;
    TextView notes;
    String userid;
    private ImageView imageview;
    private TextView uploadimg;
    private Button doneupload;
    private Uri mImageUri;
    private Bitmap image;
    private static final int PICK_IMAGE_REQUEST = 101;
    private static final int CAM2_REQUEST = 102;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    FirebaseAuth mAuth;
    String room_number1,building_name1,id1,notes1,schedule1;
    private byte bb[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_second);
        building_number = findViewById(R.id.building_number);
        building_name = findViewById(R.id.building_name);
        notes = findViewById(R.id.notes);
        schedule = findViewById(R.id.schedule);
        imageview = findViewById(R.id.imageview);
        uploadimg = findViewById(R.id.uploadimg);
        doneupload = (Button)findViewById(R.id.doneupload);
        mAuth = FirebaseAuth.getInstance();
        userid = mAuth.getCurrentUser().getUid();
        room_number1 = getIntent().getStringExtra("ROOM_NUMBER");
        building_name1 = getIntent().getStringExtra("BUILDING_NAME");
        id1 = getIntent().getStringExtra("ID");
        notes1 = getIntent().getStringExtra("NOTES");
        schedule1 = getIntent().getStringExtra("SCHEDULE");
        building_number.setText(room_number1);
        building_name.setText(building_name1);
        notes.setText(notes1);
        schedule.setText(schedule1);


        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermission();
            }
        });
        doneupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStorageRef = FirebaseStorage.getInstance().getReference("rooms/"+room_number1+"/"+userid);
                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(HomeSecond.this, "in progress!", Toast.LENGTH_SHORT).show();
                }else{
                    uploadImg();
                }
            }
        });


        bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        backhome = (ImageView) findViewById(R.id.backhome);

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Home.class));
                overridePendingTransition(0,0);
            }
        });
        bnv.setSelectedItemId(R.id.home);
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
    }

    public void onBackPressed() {

        Intent intent = new Intent(HomeSecond.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }

    private void askCameraPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},PICK_IMAGE_REQUEST);
        }else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission required!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAM2_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM2_REQUEST) {
            image = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, bytes);
            bb = bytes.toByteArray();
            imageview.setImageBitmap(image);

        }
    }
    private void uploadImg(){
        if(bb != null){
//        StorageReference fileReferance = mStorageRef.child(System.currentTimeMillis()
//                + "." + getFileExtension(mImageUri));

            mUploadTask = mStorageRef.putBytes(bb)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(HomeSecond.this, "Image Uploaded!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                            overridePendingTransition(0,0);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(HomeSecond.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                        double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
//                        Toast.makeText(HomeSecond.this, "Success: " + progress, Toast.LENGTH_SHORT).show();
//                    }
//                });
        }else{
            Toast.makeText(HomeSecond.this, "Empty File!", Toast.LENGTH_SHORT).show();
        }
    }
}