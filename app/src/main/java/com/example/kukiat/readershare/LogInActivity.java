package com.example.kukiat.readershare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by kukiat on 11/13/2017 AD.
 */

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEmailField;
    private EditText mPasswordField;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mEmailField = findViewById(R.id.email);
        mPasswordField = findViewById(R.id.password);

        findViewById(R.id.signInBtn).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if ( currentUser != null ) {
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
//            Log.d("onstart", currentUser.toString());
        }

    }

    private void startSignIn() {
        Log.d("signIN1", "signInWithEmail:success");
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        Log.d("signIN2", email + password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("signIN3", "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            startActivity(new Intent(LogInActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.d("signIN4", "signInWithEmail:success");
                        }

                        // ...
                    }
                });
    }

    public void goSignUp(View v) {
        Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.signInBtn){
            startSignIn();
        }
    }
}
