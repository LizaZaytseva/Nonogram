package com.example.nonogram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rules extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }

    //Метод реализует возвращение к начальному окну
    public void exitR(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Метод реализует возвращение к игровому полю
    public void toGame(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }
}