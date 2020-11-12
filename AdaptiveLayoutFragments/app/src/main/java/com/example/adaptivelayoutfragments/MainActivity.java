package com.example.adaptivelayoutfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.adaptivelayoutfragments.fragment.FragmentContact;

public class MainActivity extends AppCompatActivity implements FragmentContact.OnFragmentEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.containerFragments) != null) {
            getSupportFragmentManager().beginTransaction().add(R.id.containerFragments, new FragmentContact()).commit();
        }
    }

    @Override
    public void selectContact(Contact c) {

    }
}