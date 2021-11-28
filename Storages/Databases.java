package Storages;
/**
 * Материнский класс типа ArrayList, от которого наследуются классы БД книг и читателей.
 * Представляет собой бланк динамического массива, каждая ячейка которого - карточка читателя или книги.
 * Определяет основные методы, такие как вывод основной информации по текущему классу БД (имя БД) и адрес файла хранения
 * определяет метод toString() для представления текущей БД в сообщениях
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Databases extends ArrayList { // родительский класс, задающий методы всех наших баз данных
    protected String baseAdressTxt; //адрес дистрибутива с базой данных
    protected String baseAdressBin; // адрес бинарного файла - хранилища базы данных
    protected String localBaseName; // наименование базы данных

    public String getBaseAdressTxt() {
        return baseAdressTxt;
    }

    public String getBaseAdressBin() {
        return baseAdressBin;
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
        return database;
    }

    @Override
    public String toString() {
        return localBaseName;
    }

    public Object fillFields (int count, String theLine) { // Заполнение полей обьекта через конструктор при чтении из txt
    Object object = new Object();
    return object;
    }

    public void printBase(){
        for (int i = 0; i<size(); i++) {
            Object object = get(i);
            printLine(object);
        }
    }

    public void printLine (Object object) {}

    public void printAll(){
        int count = size();
        for (int i = 0; i < size(); i++) {
            BooksAndReaders booksAndReaders = (BooksAndReaders) get(i);
            booksAndReaders.printLine();
        }
    }

    public int getIndexByObject (BooksAndReaders object) {
        int index;
        for (index =0; index < size(); index++){
            if (get(index).equals(object)) break;
        }
        return index;
    }

}
