package com.example.stemsysteem;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Kemal on 23-6-2015.
 */
public class ParseConnection extends Application {
    @Override
    public void onCreate() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "tbFmh4bgbHhhy8vpv0kmOm4HOIvV8fLM25IjndqD", "5gh5RHT2LD8cz0WTG3f9xugLsUX7ABsmLW6iqQVl");
    }
}
