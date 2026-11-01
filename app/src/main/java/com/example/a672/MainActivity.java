package com.example.a672;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int nTaps;
    TextView textTop;
    TextView textBotton;
    ImageView pig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textTop = findViewById(R.id.textTop);
        textBotton = findViewById(R.id.textBotton);
        pig = findViewById(R.id.pig);
        loadPigs();
        updateTextBotton();
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePigs();
    }

    public void tap(View view) {
        nTaps++;
        textBotton.setText("вы потрогали " +nTaps+ end());
    }
    void updateTextBotton(){
        nTaps++;
        updateTextBotton();
    }
    String end() {
        if (nTaps%10>1 && nTaps%10<5 && (nTaps/10%10)!=1) {
            return "раза";
        }
        return "раз";

    }
    public void savePigs(){
        SharedPreferences prefs = getSharedPreferences("PiggyPrefs", Context.MODE_PRIVATE);
        prefs.edit().putInt("num taps", nTaps).apply();
    }
    public void loadPigs(){
        SharedPreferences prefs = getSharedPreferences("PiggyPrefs", Context.MODE_PRIVATE);
        nTaps = prefs.getInt("num taps", nTaps);
    }
}