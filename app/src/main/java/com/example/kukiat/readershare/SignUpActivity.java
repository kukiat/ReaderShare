package com.example.kukiat.readershare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kukiat on 11/14/2017 AD.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_activity);
        email = findViewById(R.id.createMailField);
        password = findViewById(R.id.createPassFiled);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.fireBtn).setOnClickListener(this);
    }

    public void signUpClick(View v) throws JSONException {

        String URL = "https://readershare.herokuapp.com/register";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", email.getText().toString());
        jsonBody.put("password", password.getText().toString());

        final String requestBody = jsonBody.toString();
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError err) {

                        Log.d("Error.Response", err.getMessage());
                    }
                }
        ) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.fireBtn){
            createFireBase();
        }
    }
    private boolean validateForm() {
        boolean valid = true;

        String mEmail = email.getText().toString();
        if (TextUtils.isEmpty(mEmail)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String mPassword = password.getText().toString();
        if (TextUtils.isEmpty(mPassword)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
    public void createFireBase(){
        if (!validateForm()) {
            return;
        }
        String mEmail = email.getText().toString();
        String mPassword = password.getText().toString();
        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });



    }
}