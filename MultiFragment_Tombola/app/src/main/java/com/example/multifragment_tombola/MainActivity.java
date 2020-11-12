package com.example.multifragment_tombola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int NUM_CARTELLE = 4;
    public static int[][] numeri_cartelle = {
            {84, 8,	60,	80,	6, 83, 72, 19, 63, 18, 10, 56, 71, 17, 35},
            {14, 78,55,	22,	2, 88, 39, 6, 36, 82, 24, 66, 2, 33, 79},
            {62, 78, 72,49,	80, 41,	5, 82, 28, 3, 61, 55, 58, 20, 89},
            {11, 64, 76,54,	83, 69,	89,	26,	74,	9, 41, 32, 49, 11, 34},
            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
            {16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        LinearLayout l = findViewById(R.id.listaCartelle);

        for(int i = 0; i < NUM_CARTELLE; i++) {
            Fragment fc = new FragmentCartella();
            ((FragmentCartella)fc).setNumeri(numeri_cartelle[i]);
            String str_id = "cartella" + i;
            int id = getResources().getIdentifier(str_id, "id", getPackageName());
            ft.add(id, fc, null);
        }

        ft.commit();
    }
}