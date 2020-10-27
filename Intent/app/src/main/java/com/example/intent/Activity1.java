package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends Activity {

    private Button changeActivity;
    private Intent openActivity2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity1);

        changeActivity = (Button) findViewById(R.id.buttonActivity1);
        openActivity2 = new Intent(this, Activity2.class);

        changeActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(openActivity2);
            }
        });
    }
}
