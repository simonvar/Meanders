import xyz.tritin.meanders.GenerationSystem;
import xyz.tritin.meanders.Generator1G;
import xyz.tritin.meanders.Generator2G;
import xyz.tritin.meanders.Permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

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
        GenerationSystem gen = new GenerationSystem();

        switch (menu) {
            case 0:
                return;
            case 1:
            case 2:
                try {
                    do {
                        System.out.println("Введите количество мостов (Натуральное число)");
                        String line = reader.readLine();
                        line = line.replaceAll("\\s+", "");

                        try {
                            bridges = Integer.parseInt(line);
                        } catch (NumberFormatException e) {
                            System.out.println("Вы ввели неверное значение!");
                            return;
                        }
                    } while (bridges <= 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("\nПодождите...\n");
                gen = new GenerationSystem();
                if (menu == 1) {
                    gen.setMeandersGenerator(new Generator1G());
                } else {
                    gen.setMeandersGenerator(new Generator2G());
                }
                gen.performGenerate(bridges);
                break;
        }

        gen.printCount();
        gen.printTime();

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
                break;
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

