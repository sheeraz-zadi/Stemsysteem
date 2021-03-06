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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by Kemal on 22-6-2015.
 */
public class LoginActivity extends Activity {

    private EditText emailView;
    private EditText passwordView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_activity);

            // Set up the login form.
            emailView = (EditText) findViewById(R.id.emailEditText);
            passwordView = (EditText) findViewById(R.id.passwordEditText);

            emailView.setText("test@mailinator.com");
            passwordView.setText("123456");

            // Set up the submit button onClick
            findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Validate the SignUp form
                    boolean validationError = false;

                    if (isEmpty(emailView)) {
                        validationError = true;
                        emailView.setError("Please fill in your username");
                    }
                    if (isEmpty(passwordView)) {
                        validationError = true;
                        passwordView.setError("Please fill in your password");
                    }

                    // If there is a validation error, display the error
                    if (validationError) {
                        return;
                    }

                    // Set up a progress dialog
                    final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
                    dlg.setTitle("Please wait.");
                    dlg.setMessage("Logging in. Please wait.");
                    dlg.show();

                    // Call the Parse SignUp method
                    ParseUser.logInInBackground(emailView.getText().toString().toLowerCase(), passwordView.getText().toString(), new LogInCallback() {

                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            dlg.dismiss();
                            if (e != null) {
                                // Show the error message
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                // Start an intent for the dispatch activity
                                saveSharedPreference(emailView);

                                Intent intent = new Intent(LoginActivity.this, DispatchActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }


                        }
                    });

                }
            });
        }

        private boolean isEmpty(EditText emailView) {
            if (emailView.getText().toString().trim().length() > 0) {
                return false;
            }
            else{
                return true;
            }
        }
        public void saveSharedPreference(EditText emailView){
            SharedPreferences myPrefs = LoginActivity.this.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
            SharedPreferences.Editor e = myPrefs.edit();
            e.putString("cEmail", emailView.getText().toString());
            e.commit();
        }

}
