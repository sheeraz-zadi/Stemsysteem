package com.example.stemsysteem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Kemal on 22-6-2015.
 */
public class RegisterActivity extends Activity {
    private EditText emailView;
    private EditText passwordView;
    private EditText passwordAgainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Set up the SignUp form
        emailView = (EditText) findViewById(R.id.emailEditText);
        passwordView = (EditText) findViewById(R.id.passwordEditText);
        passwordAgainView = (EditText) findViewById(R.id.password2EditText);

        // Set up the submit button onClick
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Validate the SignUp form
                boolean validationError = false;

                if (isEmpty(emailView)) {
                    validationError = true;
                    emailView.setError("Please fill in your email");
                    emailView.requestFocus();
                }
                if (isEmpty(passwordView)) {
                    validationError = true;
                    passwordView.setError("Please fill in your password");
                    passwordView.requestFocus();
                }
                if (isEmpty(passwordAgainView)) {
                    validationError = true;
                    passwordAgainView.setError("Please fill in your password");
                    passwordAgainView.requestFocus();
                }
                if (!isMatching(passwordView, passwordAgainView)) {
                    validationError = true;
                    passwordView.setError("Please fill in both same passwords");
                    passwordAgainView.setError("Please fill in both same passwords");
                    passwordView.requestFocus();
                    passwordAgainView.requestFocus();
                }


                // If there is a validation error, display the error
                if (validationError) {
                    return;
                }

                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(RegisterActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Signing up. Please wait.");
                dlg.show();

                // Set up a new Parse user
                ParseUser user = new ParseUser();
                user.setUsername(emailView.getText().toString().toLowerCase());
                user.setEmail(emailView.getText().toString().toLowerCase());
                user.setPassword(passwordView.getText().toString());

                // Call the Parse SignUp method
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            // Show the error message
                            if (e.getCode() == ParseException.EMAIL_TAKEN) {
                                emailView.setError("Email " + emailView.getText().toString() + " already taken");
                                emailView.requestFocus();
                            } else {
                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        } else {
                            // Start an intent for the dispatch activity
                            saveSharedPreference(emailView);

                            Intent intent = new Intent(RegisterActivity.this, DispatchActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(EditText etText1, EditText etText2) {
        if (etText1.getText().toString().equals(etText2.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public void saveSharedPreference(EditText usernameView){
        SharedPreferences myPrefs = RegisterActivity.this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = myPrefs.edit();
        e.putString("cEmail", usernameView.getText().toString());
        e.commit();
    }
}
