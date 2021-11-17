package Storages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader implements Serializable {
    static String theBiblioteka = "Библиотека № 13";
    private final String nameReader; // дефалт
    private int numberBileta; // номера ранее выданных билетов
    private final int yearBirth;
    private String phoneNumber;
    //////////////////// перепиши на массив с перечислением индексов книг, которые на руках
    List<Integer> knigiNaRukah = new ArrayList(); // номера книг, которые на руках у юзера

    public Reader(String name, int year, int number, String phone){ // создаем конструктор. Номера билетов определяются новые
        this.nameReader = name;                      // в случае необходимости добавить нов конструктор с
        this.yearBirth = year;                       // принудительным вводом № билета на случай удаления аккаунта
        this.numberBileta = number;                              // одного из предыдущих читателей
        this.phoneNumber = phone;
}
/////////   добавить метод удаления читателей: ФИО: "Удален", книги Null, номер билета Null. Если остаются книги,
///////// вывод сообщения: Удаление читателя "ФИО" невозможно: у читателя не возвращены книги: "перечисление книг"
/////////   на будущее сделать отдельную базу для бывших читателей (при удалении)

    public void dolgiPastLoad(String knigi, List<Book> bazaKnig){ // загружаем долги прошлого времени по книгам
        try {
            String[] knigiArray = knigi.split(", ");
            for (String theKniga : knigiArray) {
                int index = Book.getIndex(theKniga, bazaKnig);
                knigiNaRukah.add(index); // делаем запись в список книг на руках
                Book.bookTake(index, bazaKnig); // делаем запись в БД книг
            }
        } catch (Exception e) { System.out.println("Возникла ошибка при загрузке файла!");}
    }

    void bookTake(int index, List<Book> bazaKnig){
        try {
            String nazv = Book.getNazvanie(index, bazaKnig);
            String avtor = Book.getAvtor(index, bazaKnig);
            knigiNaRukah.add(index); // делаем запись в список книг на руках
            Book.bookTake(index, bazaKnig); // делаем запись в БД книг
            System.out.println("Читатель " + nameReader + " взял книгу " + nazv + " авт. " + avtor);

        } catch (Exception e) { System.out.println("Возникла ошибка при чтении базы данных. Новая запись не добавлена.");
        }
    }

    void bookPut(int numKnigi, List<Book> bazaKnig){
        try {
            String nazv = Book.getNazvanie(numKnigi, bazaKnig);
            String avtor = Book.getAvtor(numKnigi, bazaKnig);
            knigiNaRukah.add(numKnigi); // делаем запись в список книг на руках
            Book.bookTake(numKnigi, bazaKnig); // делаем запись в БД книг
            System.out.println("Читатель " + nameReader + " вернул книгу " + nazv + " авт. " + avtor);
        } catch (Exception e) { System.out.println("Возникла ошибка при чтении базы данных. Новая запись не добавлена.");
        }
    }


    //////// перепиши в соотв с замечаниями выше по takeBook()
    /*
    void retutnBook(Book book){ // вернул книгу - пишем название книги
        String nazv = book.getNazvanie();
        int bookIndex = knigiNaRukah.indexOf(nazv); // ищем индекс книги в списке
        for (int i = bookIndex; i < knigiNaRukah.size()-1; i++ ) { // убираем возвращенну книгу, сдвигая слежующие влево
            knigiNaRukah.set(i, knigiNaRukah.get(i+1));
        }
        //knigiNaRukah.set(knigiNaRukah.size()-1, "-=EMPTY=-");
        System.out.println(nameReader+" возвратил книгу " + book.getNazvanie());
        book.bookPut();
    }

     */

    public static void allReaders(List<Reader> bazaReaders) { // вывод списка всех читателей
        bazaReaders.size();
        for (Reader theReader : bazaReaders) {
            int numOfBooks = theReader.knigiNaRukah.size();
            System.out.println("билет №"+theReader.numberBileta+", читатель "+theReader.nameReader+
                    " на руках "+numOfBooks+" книг");
        }

    }

    public void printReader(List<Book> bazaKnig){ // вывод на печать конкретного читателя
        try {
            System.out.println("Справка о клиенте: " + theBiblioteka + ". Читатель " + nameReader + " " + yearBirth +
                    " г.р., читательский билет №" + numberBileta + ", тел.: " + phoneNumber);
            if (knigiNaRukah.size() > 0) {
                System.out.print("На руках у читателя книги: ");
                String nazvanie;
                for (int i = 0; i < knigiNaRukah.size() - 1; i++) {
                    nazvanie = Book.getNazvanie(knigiNaRukah.get(i), bazaKnig);
                    System.out.print(nazvanie + ", ");
                }
                nazvanie = Book.getNazvanie(knigiNaRukah.get(knigiNaRukah.size() - 1), bazaKnig);
                System.out.println(nazvanie + "."); // печатаем последнюю книгу
            } else System.out.println("У читателя нет книг на руках");
        } catch (Exception e) {
            System.out.println("Возникла ошибка при чтении базы данных");
        }
    }

    public static List<Reader> bazaReadersDownload(){
        List<Reader> bazaReaders = new ArrayList();
        File bdReaders = new File("BdReaders");
        Scanner scan = null;
        try { scan = new Scanner(bdReaders);
        int count = 0;
        while (scan.hasNextLine()){
            String theLine = scan.nextLine();
            String[] pole = theLine.split(", ");
            int yearBirth = Integer.parseInt(pole[1]);
            count++;
            int numberBileta = count;
            bazaReaders.add(new Reader(pole[0], yearBirth, numberBileta, pole[2] ));
        }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. База данных по читателям пуста.");
        //} catch (ParseException e) {
        //    System.out.println("Ошибка ввода информации - неверные данные.");
        }
return bazaReaders;
    }

    public static void uploadReadersBin (List<Reader> bazaReaders){ // uploading the readers as the Objects into file.bin
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("readers.bin"))) {
            obj.writeObject(bazaReaders); // записываем целиком объект класса List
        } catch (FileNotFoundException e) {
            System.out.println("Файл выгрузки БД читателей не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода при выгрузке БД читателей");
        }
    }

    public static List<Reader> downloadReadersBin (){
        List bazaReaders = new <Reader> ArrayList();
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream("readers.bin"))){
            bazaReaders=(List<Reader>)obj.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при загрузке БД читателей: файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке БД читателей: ошибка ввода-вывода");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке БД читателей: Не найден класс");
        }
        return bazaReaders;
    }
}
