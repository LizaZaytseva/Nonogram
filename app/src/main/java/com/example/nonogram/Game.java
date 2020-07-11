package com.example.nonogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity implements View.OnClickListener{

    private String selectedB = "empty";
    private Button addB, rulesB, emptyB, clearB;
    private ImageButton exitGameB;
    private GridView gameBoard;
    private Logic logic;
    //Изначально выбранной кнопкой считается "Отметить пустую клетку"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameBoard = (GridView) findViewById(R.id.gridView);
        gameBoard.setNumColumns(15);
        gameBoard.setHorizontalSpacing(2);
        gameBoard.setEnabled(true);

        logic = new Logic(this);
        gameBoard.setAdapter(logic);

        gameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                logic.setNumber(i, selectedB);
                if (logic.win()) {
                    Toast.makeText(getApplicationContext(), "win", Toast.LENGTH_LONG).show();
                }
            }
        });

        addB = (Button) findViewById(R.id.add);
        rulesB = (Button) findViewById(R.id.rulesG);
        exitGameB = (ImageButton) findViewById(R.id.exitGame);
        emptyB = (Button) findViewById(R.id.empty);
        clearB = (Button) findViewById(R.id.clear);

        addB.setOnClickListener(this);
        rulesB.setOnClickListener(this);
        exitGameB.setOnClickListener(this);
        emptyB.setOnClickListener(this);
        clearB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add:
                selectedB = "add";
                break;
            case R.id.empty:
                selectedB = "empty";
                break;
            case R.id.rulesG:
                Intent intent = new Intent(this, Rules.class);
                startActivity(intent);
                break;
            case R.id.exitGame:
                Intent intentE = new Intent(this, MainActivity.class);
                startActivity(intentE);
                break;
            case R.id.clear:
                selectedB = "clear";
                break;
        }
    }
}