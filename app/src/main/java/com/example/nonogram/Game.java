package com.example.nonogram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class Game extends Activity implements View.OnClickListener{

    private String selectedB = "add";
    private Button addB, emptyB, clearB, solutionB;
    private Button exitGameB;
    private GridView gameBoard;
    private Logic logic;
    //Изначально выбранной кнопкой считается

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Создаем объект класса Logic
        logic = new Logic(this);
        //Соотносим GridView с тем, что описано в файле xml
        gameBoard = (GridView) findViewById(R.id.gridView);
        //Задаем кол-во столбцов в GridView
        gameBoard.setNumColumns(15);
        gameBoard.setHorizontalSpacing(1);
        gameBoard.setVerticalSpacing(1);
        //Определяем, что Grid View незаблокированный элемент
        gameBoard.setEnabled(true);
        //Подключаем адаптер к классу Logic
        gameBoard.setAdapter(logic);
        //Подключаем метод, который будет отслеживать нажатие на элементы GridView
        gameBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Используем метод, изменяющий массив по выбранной кнопке и позиции в GridView
                logic.setNumber(i, selectedB);
                //Проверяем, выиграл ли игрок на данном шаге
                if (logic.isWin()) {
                    Toast.makeText(getApplicationContext(), "Поздравляю, вы выиграли!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Соотносим определенные кнопки с теми, что прописаны в файле xml
        addB = (Button) findViewById(R.id.add);
        exitGameB = (Button) findViewById(R.id.exitGame);
        emptyB = (Button) findViewById(R.id.empty);
        clearB = (Button) findViewById(R.id.clear);
        solutionB = (Button) findViewById(R.id.solution);

        //Подключаем метод, который будет отслеживать нажатие на кнопки
        addB.setOnClickListener(this);
        exitGameB.setOnClickListener(this);
        emptyB.setOnClickListener(this);
        clearB.setOnClickListener(this);
        solutionB.setOnClickListener(this);
    }

    //Метод, обрабатывающий нажатие кнопок
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add:
                selectedB = "add";
                break;
            case R.id.empty:
                selectedB = "empty";
                break;
            case R.id.exitGame:
                Intent intentE = new Intent(this, MainActivity.class);
                startActivity(intentE);
                break;
            case R.id.clear:
                selectedB = "nothing";
                break;
            case R.id.solution:
                if (logic.isRight()) {
                    Toast.makeText(getApplicationContext(), "Ошибок нет!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "В вашем решении есть ошибка(и)!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}