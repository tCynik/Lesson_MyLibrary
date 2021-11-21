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

    public Object fillFields (int count, String theLine) { //// в каждом классе БД переопределить метод в зависимости от структуры каждого класса
    Object object = new Object();
    return object;
    }

    public void printBase(Object object){
    }

}
