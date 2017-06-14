package xyz.tritin.meanders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int menu = 0;

        while (true){
            clearScreen();

            System.out.println("0. Выход");
            System.out.println("***********");
            System.out.println("1. Проверить перестановку");
            System.out.println("2. Сгенерировать меандры");
            System.out.println("3. Информация");

            try {
                System.out.println();
                System.out.print("Введите номер пункта: ");
                menu = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }  catch (NumberFormatException e) {
                System.out.println("Вы ввели неверное значение!");
                continue;
            }


            switch (menu){
                case 0:
                    return;
                case 1:
                    check();
                    break;
                case 2:
                    gen();
                    break;
                case 3:
                    break;
            }
        }

    }

    public static void gen() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer bridges = 0;
        clearScreen();

        try {
            System.out.println("Введите количество мостов (Пример: 1)");
            String line = reader.readLine();
            line = line.replaceAll("\\s+", "");

            try {
                bridges = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неверное значение!");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Generator gen = new Generator(bridges);
        gen.generate();
        System.out.println("...");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void check() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> numbers = new ArrayList<>();

        clearScreen();

        try {
            System.out.println("Введите перестановку (Пример: 1,2,3,4)");
            String line = reader.readLine();
            line = line.replaceAll("\\s+", "");
            String[] permutationString = line.split(",");
            for (String str : permutationString) {
                Integer anInt = 0;
                try {
                    anInt = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели неверную перестановку!");
                    return;
                }
                numbers.add(anInt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Permutation perm = new Permutation(numbers);
        perm.checkForMeander();

        if (perm.isMeander()){
            System.out.println("Это меандр.");
        } else {
            System.out.println("Это не меандр.");
        }

        System.out.println("...");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

