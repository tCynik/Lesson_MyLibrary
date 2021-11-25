package Storages;
/**
 * Материнский класс типа ArrayList, от которого наследуются классы БД книг и читателей.
 * Представляет собой бланк динамического массива, каждая ячейка которого - карточка читателя или книги.
 * Определяет основные методы, такие как вывод основной информации по текущему классу БД (имя БД) и адрес файла хранения
 * определяет метод toString() для представления текущей БД в сообщениях
 */

import java.util.ArrayList;

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

    public int getIndexObject (CommonDatabaseMethods manager) {
        int number = manager.getNumber();
        int index;
        for (index =0; index < size(); index++) {
            CommonDatabaseMethods object = (CommonDatabaseMethods) get(index);
            if (object.getNumber() == number) {
                break;
            }
        }
        return index;
    }

}
