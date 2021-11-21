package Storages;
/**
 * подпрограмма - менеджер управления базами данных
 * содержит в себе методы для работы с базами данных - обьектами суперкласса Databases(ArrayList)
 * вынесена из класса start из-за сложности с доступом к методам класса в папке src из вложенных папок :(
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    public int indexByNum(int num) { // получение индекса элемента через индивидуальный номер
        int index=0;
        return index;
    }

    public static Databases downloadBaseTxt (Databases blancBase) {
        Databases database = blancBase;
        File dataFile = new File(blancBase.getBaseAdressTxt());
        int count = 1;
        try { Scanner scan = new Scanner(dataFile);
            while (scan.hasNextLine()){
                String theLine = scan.nextLine();
                database.add(blancBase.fillFields(count, theLine));
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл базы данных "+blancBase.toString()+" не найден.");
        }
        System.out.println("импорт базы данных "+blancBase.toString()+" завершен успешно");
        return database;
    }

    public static void uploadBaseBin (Databases database){
        String adress = database.getBaseAdressBin();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(adress))) {
            out.writeObject(database);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода базы данных " + database.toString()) ;
        }
    System.out.println("выгрузка базы данных "+database.toString()+" завершена успешно");
    }

    public static Databases downloadBaseBin (Databases blankDatabase){
        Databases database = blankDatabase;
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(database.getBaseAdressBin()))){
            database=(Databases)obj.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при загрузке БД "+database.toString()+": файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке БД "+database.toString()+": ошибка ввода-вывода");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке БД "+database.toString()+": Не найден класс");
        }
        System.out.println("загрузка базы данных "+database.toString()+" завершена успешно");
        return database;
    }

//    public int indexByNumber (int theNumber, ArrayList<Manager> list) {
//        for (Manager manager : list) {
//
//        }
//
//        int index=0;
//        return index;
//    }
    public static void showAll (Databases dataBase) { // метод печати всего содержимого вызывается для обьекта суперкласса Databases;
        dataBase.printBase();
    }

}
