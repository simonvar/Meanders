package xyz.tritin.meanders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int menu = 0;

        while (true) {
            clearScreen();

            System.out.println("0. Выход");
            System.out.println("**********************************");
            System.out.println("1. Проверить перестановку");
            System.out.println("2. Сгенерировать меандры");
//            System.out.println("3. Сравнение алгоритмов генерации");

            try {
                System.out.println();
                System.out.print("Введите номер пункта: ");
                menu = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели неверное значение!");
                continue;
            }


            switch (menu) {
                case 0:
                    return;
                case 1:
                    check();
                    break;
                case 2:
                    gen();
                    break;
            }
        }

    }

    public static void gen() {
        clearScreen();

        System.out.println("Выберете алгоритм (младше - быстрее)");
        System.out.println("0. Назад");
        System.out.println("**********************************");
        System.out.println("1. Алгоритм 1-го поколения");
        System.out.println("2. Алгоритм 2-го поколения");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int menu = -1;

        try {
            System.out.println();
            System.out.print("Введите номер пункта: ");
            menu = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели неверное значение!");
        }

        Integer bridges = 0;
        Generator gen = new Generator();

        switch (menu) {
            case 0:
                return;
            case 1:
            case 2:
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

                System.out.println();
                gen = new Generator(bridges);
                if (menu == 1) {
                    gen.generate1G();
                } else {
                    gen.generate2G();
                }
                break;
        }

        System.out.println();
        System.out.println("0. Продолжить");
        System.out.println("1. Вывести на экран");
        System.out.println("2. Сохранить в файл");

        menu = -1;

        System.out.println();
        System.out.print("Введите номер пункта: ");

        try {
            menu = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели неверное значение!");
        }

        switch (menu) {
            case 0:
                return;
            case 1:
                gen.out();
            case 2:
                try {
                    PrintWriter writer = new PrintWriter(bridges + "-meanders.txt", "UTF-8");
                    for (Permutation permut : gen.getMeanders()) {
                        for (Integer num : permut.getNumbers()) {
                            writer.print(num + " ");
                        }
                        writer.println();
                    }
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("Успешно записано файл \"" + bridges + "-meanders.txt\".");
                break;
        }

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

        if (perm.isMeander()) {
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

