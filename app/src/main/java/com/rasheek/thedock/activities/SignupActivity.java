package com.rasheek.thedock.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
 Button signupBtn;
 EditText emailText, passwordText, nameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupBtn = findViewById(R.id.signupBtn);
        emailText = findViewById(R.id.signupEmailText);
        passwordText = findViewById(R.id.signupPasswordText);
        nameText = findViewById(R.id.signupNameText);
        mAuth = FirebaseAuth.getInstance();

        Button signupToLogin = findViewById(R.id.signupToLoginBtn);
        signupToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    void signUp(){
       String email = emailText.getText().toString();
       String password = passwordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent homeIntent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(homeIntent);
                            finish();
                      //      updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                     //       updateUI(null);
                        }

                        // ...
                    }
                });
    }
}