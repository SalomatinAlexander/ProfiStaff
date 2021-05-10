package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        int id;
        String action;
        String name;
        String soname;
        String otchestvo;
        String date;
        String group;

        Conn.Conn();
        Conn.CreateDB();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите действие");
        System.out.println("Введите: \'Добавить\', \'Удалить\' или \'Вывести список\'" );
        System.out.print("Действие: ");
        action = reader.readLine();


        if(action.equals("Добавить")){
            System.out.println("Введите имя");
            name = reader.readLine();
            System.out.println("Введите фамилию");
            soname = reader.readLine();
            System.out.println("Введите отчество");
            otchestvo = reader.readLine();
            System.out.println("Введите дату рождения в следующем формате: дд.мм.гг");
            date = reader.readLine();
            System.out.println("Введите группу");
            group = reader.readLine();
            Conn.WriteToDB(name, soname, otchestvo, date, group);

        }
        else if(action.equals("Удалить")){
            System.out.println("Введите id для удаления");
            id = Integer.parseInt(reader.readLine());
            Conn.RemoveFromDb(id);
            System.out.println("Ученик с id = "+id+" удален");
        }
        else if(action.equals("Вывести список")){
            Conn.ReadDB();
        }
        else{
            System.out.println("Данные ввыедены некорректно");

        }

            Conn.CloseDB();

    }
}
