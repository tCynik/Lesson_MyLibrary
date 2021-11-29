package Storages;
/**
 * Шаблон класса Book. Экземпляры класса - книги.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Book extends BooksAndReaders implements Serializable {
    private int number; // индивидуальный инвентарный номер книги
    private String nazvanie;
    private String avtor;
    private int kolichestvo = 0; // количество книг, которые в библиотеке
    private List<Reader> bookHolders = new ArrayList<>(); // поименованы по читательским билетам держатели книг
    ////////// тут тохраняем ссылки на конеретных читателей (объекты), у кого на руках наша книга

    public Book (int number, String nazvanie, String avtor, int kolichestvo){ // конструктор книги
        this.number = number;
        this.nazvanie = nazvanie;
        this.avtor = avtor;
        this.kolichestvo = kolichestvo;
    }

    public int getNumber() {
        return number;
    }

    public String getNazvanie(){
        return nazvanie;
    }

    public String getNazvanieAndAvtor() {
        String nameBook = nazvanie+" автор "+ avtor;
        return nameBook;
    }

    public void info(){
        int numberBookHolders = 0;
        String stringNumberbookHolders = null;
        System.out.print("Книга инв. № "+ number + " "+ nazvanie+", автор "+avtor+", в библиотеке "
                + kolichestvo+ " экземпляров,");
        try { // проверка есть ли вообще держатели этой книги
            numberBookHolders = bookHolders.size();
            System.out.println(" на руках у "+ numberBookHolders+" читателей: ");
////// выводится адрес размещения книги
            for (int i = 0; i < numberBookHolders - 1; i++) {
                Reader reader = bookHolders.get(i);
                String name = reader.getName();
                int bilet = reader.getNumber();
                System.out.println((i+1) + ". " + name + " читательский билет № "+ bilet +"; ");

            }
            Reader reader = bookHolders.get(numberBookHolders - 1); // выводим последнего
            String name = reader.getName();
            int bilet = reader.getNumber();
            System.out.println( numberBookHolders + ". " + name + " читательский билет № "+ bilet +".");
        } catch (NullPointerException e) { stringNumberbookHolders = "на руках у читателей этой книги нет";
        } catch (IndexOutOfBoundsException e) { stringNumberbookHolders = "на руках у читателей нет";}
    }

    public void printLine() {
        System.out.println("Книга инв. № "+ number + " "+ nazvanie+", автор "+avtor+", в библиотеке "
                + kolichestvo+ " экземпляров, на руках у "+ bookHolders.size() +" читателей.");
    }

    public static int indexBookByNumber(int number){
        int index = 0;
        BookDataBase bazaTemp = new BookDataBase();
        BookDataBase bazaBooks = (BookDataBase) Databases.downloadBaseBin(bazaTemp);
        for (Object theBook: bazaBooks) {
            Book book = (Book) theBook;
            if (book.getNumber() == number){
                break;
            } else index++;
        }
        return index;
    }

    public static void bookTakeHolder(Reader bookHolder, int indexBook) { // записываем ID держателя книги
        BookDataBase bazaTemp = new BookDataBase();
        BookDataBase bazaBooks = (BookDataBase) Databases.downloadBaseBin(bazaTemp);
        Book theKniga = (Book) bazaBooks.get(indexBook);
        theKniga.bookHolders.add(bookHolder); // добавляем в лист копию карточки читателя
        bazaBooks.set(indexBook, theKniga); // изменяем параметр и перезаисываем в нашем массиве
    }

    public void bookTake (Reader reader) {
        bookHolders.add(reader);
    }

//    public static void bookTake (int index, List<Book> bazaKnig) { // меняем количество книг
//        Book theKniga = (Book) bazaKnig.get(index);
//        theKniga.kolichestvo--; //
//        bazaKnig.set(index, theKniga); // изменяем параметр количества и перезаисываем в нашем массиве
//    }

    public static void bookPutHolder(int indexHolder, int indexBook, List<Book> bazaKnig) { // убираем ID держателя книги
        Book theKniga = (Book) bazaKnig.get(indexBook);
        int count = 0;
//        for (int index:theKniga.bookHolders) {
//            if (index == indexBook) {
//                theKniga.bookHolders.remove(count);
//            }
//        }
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