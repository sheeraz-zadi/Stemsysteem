package com.example.stemsysteem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;

/**
 * Created by Kemal on 22-6-2015.
 */
public class DispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else {
            // Start an intent for the logged out activity
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
