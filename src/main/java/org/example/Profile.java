package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Profile {

    public static String[] inputData() throws CountDataEcxeption, NotValidDataException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите данные через пробел: Фамилия\" \"Имя\" \"Отчество\" \"Номер телефона");
        String data = sc.nextLine();
        String[] arrayData = data.split(" ");
        if (arrayData.length < 4 | arrayData.length > 4) {
            throw new CountDataEcxeption("Количество введенных данных не соответствуют заданным требованиям");
        }
        for (int i = 0; i < arrayData.length; i++) {
            if (i != 3) {
                if (arrayData[i] != null) {
                    char[] arr = arrayData[i].toCharArray();
                    for (char c : arr) {
                        if (Character.isDigit(c)) {
                            throw new NotValidDataException("Некорректный ввод " + info(i), info(i));
                        }
                    }
                }
            } else {
                try {
                    double number = Double.parseDouble(arrayData[i]);
                } catch (Exception e) {
                    throw new NotValidDataException("Некорректный ввод " + info(i), info(i));
                }
            }
            sc.close();
        }
        return arrayData;
    }

    public static String info(int codeError) {
        switch (codeError) {
            case 0 -> {
                return "Фамилии";
            }
            case 1 -> {
                return "Имени";
            }
            case 2 -> {
                return "Отчества";
            }
            case 3 -> {
                return "Номера телефона";
            }
        }
        return null;
    }

    public static void writerFile(String[] data) throws IOException {
        try(FileWriter fileWriter = new FileWriter(data[0] + ".txt", true);){
            for(String word : data){
                fileWriter.flush();
                fileWriter.write(word);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }


    }

    public static void main(String[] args) {
        try {
            String[] data = inputData();
            writerFile(data);
        } catch (CountDataEcxeption | NotValidDataException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

