package com.iot.smart1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private EditText mEmail , mPass;
    private TextView mTextView;
    private Button signInBtn;
   private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.email_edit);
        mPass = findViewById(R.id.password_edit);
        signInBtn = findViewById(R.id.btn_login);
        mTextView = findViewById(R.id.textView5);

        mAuth = FirebaseAuth.getInstance();
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this , Register.class));
            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }
    private void loginUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(email , pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this , ActivityHome.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                mPass.setError("Empty Fields Are not Allowed");
            }
        }else if(email.isEmpty()){
            mEmail.setError("Empty Fields Are not Allowed");
        }else{
            mEmail.setError("Pleas Enter Correct Email");
        }
    }

}