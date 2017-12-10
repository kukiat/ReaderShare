package com.example.kukiat.readershare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by kukiat on 10/12/2017 AD.
 */

public class EditProfileActivity extends AppCompatActivity {
    private EditText vEditName;
    private EditText vEditPass;
    private Button vBtnSave;
    FirebaseUser user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        vEditName = (EditText) findViewById(R.id.reviewer_name_edit);
        vEditPass = (EditText) findViewById(R.id.reviewer_pass_edit);
    }

    public void onSave(View v) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            if (!validateForm()) {
                return;
            }
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(vEditName.getText().toString())
                    .build();
            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
        }else {
            Intent intent = new Intent(getBaseContext(), LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private boolean validateForm() {
        boolean valid = true;

        String name = vEditName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            vEditName.setError("Required.");
            valid = false;
        } else {
            vEditName.setError(null);
        }

        return valid;
    }


}
