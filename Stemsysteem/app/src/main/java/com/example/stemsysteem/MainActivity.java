package com.example.stemsysteem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "irzDix9ljPYtHQr9yh1wDwfnJZgBFnmzIuQAqLpq", "2LMlvc7xK9TxEPch1XlnEdgOvoA8M7QnQUldFvFO");

        // Login onClick
        final Button bLogin = (Button) findViewById(R.id.loginButton);
        bLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        // SignUp onClick
        final Button bRegister = (Button) findViewById(R.id.registerButton);
        bRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }

    public void onBackPressed() {
        //do nothing
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
