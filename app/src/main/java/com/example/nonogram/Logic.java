package com.example.nonogram;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.Arrays;

public class Logic extends BaseAdapter {
    //Создаем элементы классов Context и Resources для реализации отрисовки
    private Context mContext;
    private Resources resources;
    //Задаем количество строк и столбцов
    private final Integer rows = 15, cols = 15;
    //Создаем массив, который хранит ссылки на изображения, соответсвующие значениям в рабочем массиве
    private String[] pictures;
    //Создаем массив, который будет хранить введеные играком значения
    private int[][] numberArray =
                    {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    //Создаем массив, который будет хранить значения, соответсвующие решению
    int[][] solutionArray =
                    {{1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2, 2, 1},
                    {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2},
                    {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
                    {1, 2, 1, 1, 2, 2, 2, 2, 2, 1, 1, 2, 2, 1, 2},
                    {1, 1, 2, 2, 1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1},
                    {1, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 2, 1, 1, 1},
                    {1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 1, 1, 1},
                    {2, 1, 2, 1, 2, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1},
                    {2, 1, 1, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 1},
                    {1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1}};

    //Создаем конструктор класса Logic
    public Logic(Context context){
        mContext = context;
        pictures = new String[cols*rows];
        resources = mContext.getResources();
        board();
    }

    //Приводим массив в начальное состояние (заполняем нулями)
    public void clear(){
        for (int i = 0; i < rows*cols; i++){
                numberArray[getRow(i)][getColumn(i)] = 0;
                pictures[i] = "nothing";
            }
    }

    //Создаем метод, который будет добавлять названия изображений, соответствующие значениям в массиве numberArray
    private void board() {
        for(int i = 0; i < rows; i++){
            for(int j = 0; j< cols; j++){
                if (numberArray[i][j] == 0) pictures[i*15 + j] = "nothing";
                if  (numberArray[i][j] == 1) pictures[i*15 + j] ="empty";
                if  (numberArray[i][j] == 2) pictures[i*15 + j] = "add";
            }

        }
    }

    @Override
    public int getCount() { return cols * rows; }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //Метод, реализующий отрисовку рабочего поля
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //создаем объект типа ImageView, нужен для для отображения изображений
        ImageView imView;

        //присваиваем iView значение
        if(view == null) imView = new ImageView(mContext);
        else imView = (ImageView)view;

        //определяем индификатор по имени файла
        Integer picId = resources.getIdentifier(pictures[i], "drawable", mContext.getPackageName());

        //добавляем во iView то, но нашлось по полученному идентификатору
        imView.setImageResource(picId);

        return imView;
    }

    //Изменяем массив, в зависимости от выбранной кнопки
    public void setNumber(int i, String selectedButton) {
        pictures[i] =  selectedButton;
        if (selectedButton.contains("empty")) numberArray[getRow(i)][getColumn(i)] = 1;
        if (selectedButton.contains("add")) numberArray[getRow(i)][getColumn(i)] = 2;
        if (selectedButton.contains("nothing")) clear();
                notifyDataSetChanged();
            }

    //По порядковому номеру определяем номер строки в рабочем массиве
    public int getRow(int i){
        return i/rows;
    }

    //По порядковому номеру определяем номер строки в рабочем массиве
    public int getColumn(int i){
        return i%cols;
    }

    //Проверка на победу, если игрок верно собрал кроссворд, то массивы должны совпасть
    public boolean isWin(){
        return (Arrays.deepEquals(numberArray, solutionArray));

    }

    //Проверка на наличие ошибок
    public boolean isRight() {
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((numberArray[i][j] != 0) && (numberArray[i][j] != solutionArray[i][j])) k++;
            }
        }
        return (k == 0);
    }

}
