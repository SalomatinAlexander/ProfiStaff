package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static ArrayList<File> rez = new ArrayList<>();
    //C://Users//79295//ProfiStaff

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите путь к директории: ");
        String url = " ";
        String allTxt = "";

        try {
            url = reader.readLine();
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        if (url.isEmpty()) {
            System.out.println("Строка пуста");
        }
        else {
        File dir = new File(url);
        FileIsDir(dir);
        StringBuilder builder = new StringBuilder();
        ArrayList<File> sort = sortedFile(rez);
        for (int i = 0; i < sort.size(); i++) {
            try {
                allTxt = UniteFile(sort.get(i));
                builder.append(allTxt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(builder);
    }
    }

    private static ArrayList<File> FileIsDir(File file) {
        if (file.isDirectory()) {
            for (File item : file.listFiles()) {

                if (item.isDirectory()) { FileIsDir(item); }
                    if (getFileExtension(item.getName())) { rez.add(item); }
                    if(!file.isFile()&&!file.isDirectory()){

                    }
            }
        }else {System.out.println("Путь введен некорректно");}
        return rez;
    }

    private static Boolean getFileExtension(String mystr) {
        String extension = "";
        if (mystr.contains("."))
            extension = mystr.substring(mystr.lastIndexOf("."));
        if(extension.equals(".txt")){
            return true;
        } else {
            return false;
        }
    }

    private static ArrayList<File> sortedFile(ArrayList<File> arrayName) {
        for (int j = 0; j < arrayName.size(); j++) {
            for (int i = j + 1; i < arrayName.size(); i++) {
                if (arrayName.get(i).getName().compareTo(arrayName.get(j).getName()) < 0) {
                    File k = arrayName.get(j);
                    arrayName.set(j, arrayName.get(i));
                    arrayName.set(i, k);
                }
            }
        }
        return arrayName;
    }
    private static String UniteFile(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        FileReader fin;
        try {
            //System.out.println(file.getPath());
            fin = new FileReader(file.getPath());
            int c;
            while((c=fin.read())!=-1){
                builder.append((char)c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

        return builder.toString();

    }

}


