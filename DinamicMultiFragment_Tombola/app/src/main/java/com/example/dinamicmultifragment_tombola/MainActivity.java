package com.example.dinamicmultifragment_tombola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout l;
    private Button buttonGeneraCartelle;
    private EditText editText;
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

        l = findViewById(R.id.listaCartelle);
        editText = findViewById(R.id.numCartelle);
        buttonGeneraCartelle = findViewById(R.id.buttonGeneraCartelle);
    }



    public void generaCartelle(View view) {
        l.removeAllViews();
        int num_cartelle = Integer.parseInt(editText.getText().toString());
        for(int i = 0; i < num_cartelle; i++) {
            FrameLayout frameLayout = new FrameLayout(getApplicationContext());
            int id = i+1;
            frameLayout.setId(id);
            frameLayout.setPadding(10,10,10,10);
            l.addView(frameLayout);
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //riempimento
        for(int i = 0; i < num_cartelle; i++) {
            Fragment fc = new FragmentCartella();
            ((FragmentCartella)fc).setNumeri(numeri_cartelle[i]);
            ft.add((i+1), fc, null);
        }
        ft.commit();
    }
}