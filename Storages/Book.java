package Storages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Storages.Manager.downloadBaseBin;

public class Book implements Serializable {
    private int number; // индивидуальный инвентарный номер книги
    String naznachenie; ////////////// неакктуальная фича, удалить
    private String nazvanie;
    private String avtor;
    private int kolichestvo = 0; // количество книг, которые в библиотеке
    private List<Integer> bookHolders; // поименованы по читательским билетам держатели книг
    ////////// тут тохраняем ссылки на конеретных читателей (объекты), у кого на руках наша книга

    public Book (int number, String nazvanie, String avtor, int kolichestvo){ // конструктор книги
        this.number = number;
        naznachenie = "книга";
        this.nazvanie = nazvanie;
        this.avtor = avtor;
        this.kolichestvo = kolichestvo;
    }

    public int getNumber() {
        return number;
    }

    public String getNazvanieByIndex(){
        return nazvanie;
    }

    public String getNameBook() {
        String nameBook = nazvanie+" автор "+ avtor;
        return nameBook;
    }

    public void info(){
        System.out.println("Книга инв. № "+ number + " "+ nazvanie+", автор "+avtor+", в библиотеке "
                + kolichestvo+ " экземпляров, на руках ");
    }

    public static String getNazvanieByIndex(int index, List<Book> bazaKnig){
        String nazvKnigi = bazaKnig.get(index).nazvanie;
        return nazvKnigi;
    }

    public static int getIndexByName(String nameTheBook, List<Book> bazaKnig){
        int index=0; // если не ставить 0, возможно возвращение значения NULL
        for (Book theBook: bazaKnig) {
            if (nameTheBook.equals(theBook.nazvanie)){
                index = bazaKnig.indexOf(theBook);
                break;
            }
        }
        return index;
    }

    public static int indexBookByNumber(int number){
        int index = 0;
        BookDataBase bazaTemp = new BookDataBase();
        BookDataBase bazaBooks = (BookDataBase) downloadBaseBin(bazaTemp);
        for (Object theBook: bazaBooks) {
            Book book = (Book) theBook;
            if (book.getNumber() == number){
                break;
            } else index++;
        }
        return index;
    }
    public static void bookTakeHolder(int indexHolder, int indexBook, List<Book> bazaKnig) { // записываем ID держателя книги
        Book theKniga = (Book) bazaKnig.get(indexBook);
        theKniga.bookHolders.add(indexHolder);
        bazaKnig.set(indexBook, theKniga); // изменяем параметр и перезаисываем в нашем массиве
    }

    public static void bookTake (int index, List<Book> bazaKnig) { // меняем количество книг
        Book theKniga = (Book) bazaKnig.get(index);
        theKniga.kolichestvo--; //
        bazaKnig.set(index, theKniga); // изменяем параметр количества и перезаисываем в нашем массиве
    }

    public static void bookPutHolder(int indexHolder, int indexBook, List<Book> bazaKnig) { // убираем ID держателя книги
        Book theKniga = (Book) bazaKnig.get(indexBook);
        int count = 0;
        for (int index:theKniga.bookHolders) {
            if (index == indexBook) {
                theKniga.bookHolders.remove(count);
            }
        }
        bazaKnig.set(indexBook, theKniga); // изменяем параметр и перезаисываем в нашем массиве
    }

    public static void bookPut (int index, List<Book> bazaKnig) {
        Book theKniga = (Book) bazaKnig.get(index);
        theKniga.kolichestvo++; //
        bazaKnig.set(index, theKniga); // изменяем параметр количества и перезаисываем в нашем массиве
    }


    public static String getAvtor(int index, List<Book> bazaKnig){ // получаем имя автора по индексу книги
        String nameAvtor;
        nameAvtor=bazaKnig.get(index).avtor;
        return nameAvtor;
    }

}