package com.example.kukiat.readershare;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by aiy on 11/13/17.
 */

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmailField;
    private EditText mPasswordField;

    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_activity);
        Log.d("oncreate", "createUserWithEmail:success");
        mEmailField = findViewById(R.id.createMailField);
        mPasswordField = findViewById(R.id.createPassFiled);

        findViewById(R.id.createBtn).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
    private void createAccount(){
        Log.d("create", "createUserWithEmail:success");
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        Log.d("create2", "createUserWithEmail:success");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                            startActivity(new Intent(Account.this, MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(SignupActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });

        Log.d("create3", "createUserWithEmail:success");


    }
    @Override
    public void onClick(View view) {
        int i = view.getId();
            Log.d("Click", "createUserWithEmail:success");
        if (i == R.id.createBtn){
            createAccount();
        }
    }
}
