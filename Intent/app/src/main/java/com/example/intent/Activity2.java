package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends Activity {

    private Button changeActivity;
    private Intent openMainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity2);

        changeActivity = (Button) findViewById(R.id.buttonActivity2);
        openMainActivity = new Intent(this, MainActivity.class);

        changeActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(openMainActivity);
            }
        });
    }
}
