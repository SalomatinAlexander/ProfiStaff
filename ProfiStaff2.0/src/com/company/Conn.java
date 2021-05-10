package com.company;

import java.sql.*;

import static javax.management.openmbean.SimpleType.STRING;

public class Conn {
        public static Connection conn;
        public static Statement statmt;
        public static ResultSet resSet;

        // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
        public static void Conn() throws ClassNotFoundException, SQLException
        {
            conn = null;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:TEST1.s3db");

            System.out.println("База Подключена!");
        }

        // --------Создание таблицы--------
        public static void CreateDB() throws ClassNotFoundException, SQLException
        {
            statmt = conn.createStatement();
            statmt.execute("CREATE TABLE if not exists 'students' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name', 'soname', 'otchestvo', 'date', 'group' INT);");

            System.out.println("Таблица создана или уже существует.");
            resSet = statmt.executeQuery("SELECT * FROM students");
        }

        // --------Заполнение таблицы--------
    public static void WriteToDB(String name,
            String soname, String otchestvo,
            String date, String group) throws SQLException
    {
        String query = "INSERT INTO 'students' ('name', 'soname', 'otchestvo', 'date', 'group') VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, name);
        preparedStmt.setString (2, soname);
        preparedStmt.setString  (3, otchestvo);
        preparedStmt.setString(4, date);
        preparedStmt.setString(5, group);

        preparedStmt.execute();
    }
    public static void RemoveFromDb(int id){
        try {
            statmt.execute("DELETE FROM 'students' WHERE id = "+id+"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

        // -------- Вывод таблицы--------
        public static void ReadDB() throws ClassNotFoundException, SQLException
        {
            resSet = statmt.executeQuery("SELECT * FROM students");

            while(resSet.next())
            {
                int id = resSet.getInt("id");
                String  name = resSet.getString("name");
                String  soname = resSet.getString("soname");
                String  otchestvo = resSet.getString("soname");
                String  date = resSet.getString("date");
                String  group = resSet.getString("group");
                System.out.println( "ID = " + id );
                System.out.println( "Name = " + name );
                System.out.println( "Soname = " + soname );
                System.out.println( "Otchestvo = " + otchestvo );
                System.out.println( "Date = " + date );
                System.out.println( "Group = " + group );
            }

            System.out.println("Таблица выведена");
        }

        // --------Закрытие--------
        public static void CloseDB() throws ClassNotFoundException, SQLException
        {
            conn.close();
            statmt.close();
            resSet.close();

            System.out.println("Соединения закрыты");
        }

}
