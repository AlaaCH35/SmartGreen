package com.iot.smart1;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ActivityHome extends AppCompatActivity {
    private Button SignOutBtn;
    private Button btn;
    private TextView hum;
    private TextView tem;
    private TextView soil;
    private TextView intensity;
    private EditText soilValue;
    private EditText intensity_value;
    private EditText humValue;
    private EditText temValue;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference mReference;
    private DatabaseReference mReference1;
    private DatabaseReference mReference2;
    private DatabaseReference mReference3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        SignOutBtn = findViewById(R.id.signout_btn);
        btn=findViewById(R.id.btn1);
        mAuth = FirebaseAuth.getInstance();
        hum=findViewById((R.id.hum));
        humValue=findViewById(R.id.input1);

        //humValue=findViewById(R.id.input1);
        tem=findViewById((R.id.tem));
        temValue=findViewById((R.id.input));
        soil=findViewById((R.id.soil));
        soilValue=findViewById((R.id.input2));
        intensity=findViewById((R.id.intensity));
        intensity_value=findViewById(R.id.input3);
        // Read from the database
        db=FirebaseDatabase.getInstance();
        mReference=db.getReference("DHT11/Humidity");
        mReference1=db.getReference("DHT11/Temperature");
        mReference2=db.getReference("MOISTER/SM");
        mReference3=db.getReference("LDR/LIGHT INTENSITY");


        Log.i("TAG","dataSnapshot.toString()");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("TAG",snapshot.getValue(String.class));
                humValue.setText(snapshot.getValue(String.class));
               // temValue.setText(snapshot.getValue((String.class)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG","error "+error);

            }

        });

        mReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("TAG",snapshot.getValue(String.class));

                 temValue.setText(snapshot.getValue((String.class)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG","error "+error);

            }

        });
        mReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("TAG",snapshot.getValue(String.class));

                soilValue.setText(snapshot.getValue((String.class)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG","error "+error);

            }

        });
        mReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("TAG",snapshot.getValue(String.class));

                intensity_value.setText(snapshot.getValue((String.class)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG","error "+error);

            }

        });




        SignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(ActivityHome.this, MainActivity.class));
                finish();

            }
        });

    }
}
