package com.example.nonogram;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class Logic extends BaseAdapter{

    //Создаем элементы классов Context и Resources для реализации отрисовки
    private Context context;
    private Resources res;

    //Создаем лист, который хранит ссылки на изображения, соответсвующие значениям в рабочем массиве
    private ArrayList<String> pic;

    //Задаем кол-во строк и столбцов
    int rows = 15;
    int cols = 15;

    //Создаем массив, который будет хранить введеные играком значения
    private int[][] numberArray = new int[cols][rows];

    //Создаем массив, который будет хранить значения, соответсвующие решению
    int[][] solutionArray = {{1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1},
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
        this.context = context;
        pic = new ArrayList<>(cols*rows);
        res = context.getResources();
        clear();
        board();
    }

    //Приводим массив в начальное состояние (заполняем нулями)
    public void clear(){
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                numberArray[i][i]=0;
            }
        }
    }

    //Создаем метод, который будет добавлять изображения, соответсвующие значениям в массиве numberArray
    private void board(){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if (numberArray[i][j] == 0) pic.add("nothing");
                if (numberArray[i][j] == 1) pic.add("empty");
                if (numberArray[i][j] == 2) pic.add("add");
            }
        }
    }

    @Override
    public int getCount() {
        return rows*cols;
    }

    @Override
    public Object getItem(int i) {
        return numberArray[getR(i)][getC(i)];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //Метод, реализующий отрисовку рабочего поля
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //создаем объект типа ImageView, нужен для для отображения изображений
        ImageView iView;

        //если компонент вью, который поступает в метод, пустой, то мы ивью - новый имейджвью
        //по сути, если до этого отрисовки не было, то ивью - новый имеджвью, а если была, но присваиваем актуальное значение
        if (view == null) iView = new ImageView(context);
        else iView = (ImageView)view;
        //определяем индификатор по имени файла, указывая позицию в листе пик, папку, где хранится и что-то еще:)))
        int picId = res.getIdentifier(pic.get(i), "drawable", context.getPackageName());
        //добавляем во ивью то, что нашли по этому идентификатору
        iView.setImageResource(picId);
        //возвращаем ивью
        return iView;
    }

    //По порядковому номеру определяем номер строки в рабочем массиве
    public int getR(int i){
        return i/15;
    }

    //По порядковому номеру определяем номер столбца в рабочем массиве
    public int getC(int i){
        return i%15;
    }

    //Проверяем, соответсвует ли рабочий массив массиву с решением. Если да, то игрок выиграл
    public boolean win(){
        return (solutionArray == numberArray);
    }

    //Изменяем массив, в зависимости от выбранной кнопки
    public void setNumber(int i, String selectedB){
        pic.set(i, selectedB);
        if (selectedB.contains("empty")) numberArray[getR(i)][getC(i)] = 1;
        if (selectedB.contains("add")) numberArray[getR(i)][getC(i)] = 2;
        if (selectedB.contains("clear")) numberArray[getR(i)][getC(i)] = 0;
        notifyDataSetChanged();
    }
}
