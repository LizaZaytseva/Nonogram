package com.example.nonogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Определяем кнопки и заголовок на экране
    Button rulesB;
    Button playB;
    Button exitB;
    TextView mainTitle;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getItems();

    }

    //
    public void getItems(){
        rulesB = (Button) findViewById(R.id.rules);
        playB = (Button) findViewById(R.id.play);
        exitB = (Button) findViewById(R.id.exit);
        mainTitle = (TextView) findViewById(R.id.mainTitle);
    }

    //реализуем переход в окно Rules при нажатии кнопки "правила игры"
    public void gameRules(View view) {
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    //реализуем переход в окно Game при нажатии кнопки "играть"
    public void play(View view) {
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    //реализуем выход из приложения при нажатии кнопки "выход"
    public void exit(View view) {
        finish();
    }
}