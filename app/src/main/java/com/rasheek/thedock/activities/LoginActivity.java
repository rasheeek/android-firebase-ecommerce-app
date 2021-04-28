package com.rasheek.thedock.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rasheek.thedock.MainActivity;
import com.rasheek.thedock.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailText, passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.loginEmailText);
        passwordText = findViewById(R.id.loginPasswordText);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser  != null){
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button loginBtn  = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });

        Button loginToSignup = findViewById(R.id.loginToSignupBtn);
        loginToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signupIntent);

            }
        });


    }

   void doLogin(){
    String email = emailText.getText().toString();
    String password = passwordText.getText().toString();

       mAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           // Sign in success, update UI with the signed-in user's information
                           Log.d("TAG", "signInWithEmail:success");
                           FirebaseUser user = mAuth.getCurrentUser();
                           Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                           startActivity(homeIntent);
                           finish();
                         //  updateUI(user);
                       } else {
                           // If sign in fails, display a message to the user.
                           Log.w("TAG", "signInWithEmail:failure", task.getException());
                           Toast.makeText(LoginActivity.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                       //    updateUI(null);
                       }

                       // ...
                   }
               });

    }
}