package Storages;
/**
 * Шаблон класса Reader, экземплярами которого заполняется база данных ReaderDataBase
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader extends BooksAndReaders implements Serializable  {
    static String theBiblioteka = "Библиотека № 13";
    private final String nameReader; // дефалт
    private int number; // номера ранее выданных билетов
    private final int yearBirth;
    private String phoneNumber;
    private List<Book> knigiNaRukah = new ArrayList<>(); // книги, которые на руках у читателя

    public Reader(String name, int year, int number, String phone){ // создаем конструктор. Номера билетов определяются новые
        this.nameReader = name;                      // в случае необходимости добавить нов конструктор с
        this.yearBirth = year;                       // принудительным вводом № билета на случай удаления аккаунта
        this.number = number;                              // одного из предыдущих читателей
        this.phoneNumber = phone;
}
/////////   добавить метод удаления читателей: ФИО: "Удален", книги Null, номер билета Null. Если остаются книги,
///////// вывод сообщения: Удаление читателя "ФИО" невозможно: у читателя не возвращены книги: "перечисление книг"
/////////   на будущее сделать отдельную базу для бывших читателей (при удалении)
    public String getName(){
        return nameReader;
    }

    public int getNumber() {
        return number;
    }

////////// в связи с рефакторингом методов, связанных со взятием книг, адаптировать фичу.
//    public void dolgiPastLoad(String knigi, List<Book> bazaKnig){ // загружаем долги прошлого времени по книгам
//        try {
//            String[] knigiArray = knigi.split(", ");
//            for (String theKniga : knigiArray) {
//                int index = Book.getIndexByName(theKniga, bazaKnig);
//                knigiNaRukah.add(bazaKnig.get(index)); // делаем запись в список книг на руках
//                Book.bookTake(index, bazaKnig); // делаем запись в БД книг
//            }
//        } catch (Exception e) { System.out.println("Возникла ошибка при загрузке файла!");}
//    }

    public void bookTake (Book book){
        knigiNaRukah.add(book);
    }

    public static int getIndexByNumBileta(int number){ // выбор индекас читател по номеру билета
        int index = 0;
        ReaderDataBase bazaTemp = new ReaderDataBase();
        ReaderDataBase bazaReaders = (ReaderDataBase) Databases.downloadBaseBin(bazaTemp);
        for (Object theReader: bazaReaders) {
            Reader reader = (Reader) theReader;
            if (reader.getNumber() == number){
                break;
            } else index++;
        }
        return index;
    }

    public void printReader(){ // вывод на печать конкретного читателя
        try {
            System.out.println("Справка о клиенте: " + theBiblioteka + ". Читатель " + nameReader + " " + yearBirth +
                    " г.р., читательский билет №" + number + ", тел.: " + phoneNumber);
            /////// тут поставить метод вывода списка книг у читателя. Если книг нет - так и пишем
            int skolkoKnig = knigiNaRukah.size();
            if (skolkoKnig > 0) {
                System.out.print("На руках у читателя книги: ");
                String nazvanie;
                for (int i = 0; i < skolkoKnig - 1; i++) {
                    knigiNaRukah.get(i).getNazvanie();
                    System.out.print((i+1)+ ". " + knigiNaRukah.get(i).getNazvanie() + ", ");
                }
                nazvanie = knigiNaRukah.get(skolkoKnig - 1).getNazvanie();
                System.out.println((skolkoKnig-1) + ". " + nazvanie + "."); // печатаем последнюю книгу, печать кончается точкой
            } else System.out.println("У читателя нет книг на руках");
        } catch (Exception e) {
            System.out.println("Возникла ошибка при чтении базы данных");
        }
    }

    public void printLine() {
        System.out.println("Читатель " + nameReader + ", читательский билет №" + number + ", на руках "
                + knigiNaRukah.size() + " книг");
    }

}
