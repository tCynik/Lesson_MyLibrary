package Storages;
/**
 * Шаблон класса Reader, экземплярами которого заполняется база данных ReaderDataBase
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader extends Manager implements Serializable  {
    static String theBiblioteka = "Библиотека № 13";
    private final String nameReader; // дефалт
    private int number; // номера ранее выданных билетов
    private final int yearBirth;
    private String phoneNumber;
    //////////////////// перепиши на массив с перечислением индексов книг, которые на руках
    //List<Integer> knigiNaRukah = new ArrayList(); // номера книг, которые на руках у юзера
    List<Book> knigiNaRukah = new ArrayList<>(); // книги, которые на руках у читателя

    public Reader(String name, int year, int number, String phone){ // создаем конструктор. Номера билетов определяются новые
        this.nameReader = name;                      // в случае необходимости добавить нов конструктор с
        this.yearBirth = year;                       // принудительным вводом № билета на случай удаления аккаунта
        this.number = number;                              // одного из предыдущих читателей
        this.phoneNumber = phone;
}
/////////   добавить метод удаления читателей: ФИО: "Удален", книги Null, номер билета Null. Если остаются книги,
///////// вывод сообщения: Удаление читателя "ФИО" невозможно: у читателя не возвращены книги: "перечисление книг"
/////////   на будущее сделать отдельную базу для бывших читателей (при удалении)
    public String getNameReader(){
        return nameReader;
    }

    public int getNumber() {
        return number;
    }

    public void dolgiPastLoad(String knigi, List<Book> bazaKnig){ // загружаем долги прошлого времени по книгам
        try {
            String[] knigiArray = knigi.split(", ");
            for (String theKniga : knigiArray) {
                int index = Book.getIndexByName(theKniga, bazaKnig);
                knigiNaRukah.add(bazaKnig.get(index)); // делаем запись в список книг на руках
                Book.bookTake(index, bazaKnig); // делаем запись в БД книг
            }
        } catch (Exception e) { System.out.println("Возникла ошибка при загрузке файла!");}
    }

    void bookTake(int index, List<Book> bazaKnig){
        try {
            String nazv = Book.getNazvanieByIndex(index, bazaKnig);
            String avtor = Book.getAvtor(index, bazaKnig);
            knigiNaRukah.add(bazaKnig.get(index)); // делаем запись в список книг на руках
            Book.bookTake(index, bazaKnig); // делаем запись в БД книг
            System.out.println("Читатель " + nameReader + " взял книгу " + nazv + " авт. " + avtor);

        } catch (Exception e) { System.out.println("Возникла ошибка при чтении базы данных. Новая запись не добавлена.");
        }
    }

    void bookPut(int index, List<Book> bazaKnig){
        try {
            String nazv = Book.getNazvanieByIndex(index, bazaKnig);
            String avtor = Book.getAvtor(index, bazaKnig);
            knigiNaRukah.add(bazaKnig.get(index)); // делаем запись в список книг на руках
            Book.bookTake(index, bazaKnig); // делаем запись в БД книг
            System.out.println("Читатель " + nameReader + " вернул книгу " + nazv + " авт. " + avtor);
        } catch (Exception e) { System.out.println("Возникла ошибка при чтении базы данных. Новая запись не добавлена.");
        }
    }
    //////// перепиши в соотв с замечаниями выше по takeBook()
    public void info(){
        int numOfBooks = knigiNaRukah.size();
        System.out.println("билет №"+ number +", читатель "+nameReader+ " на руках "+numOfBooks+" книг");
    }

//    public static void showAll() { // вывод списка всех читателей
//        List<Reader> bazaReaders = downloadReadersBin();
//        bazaReaders.size();
//        for (Reader theReader : bazaReaders) {
//            int numOfBooks = theReader.knigiNaRukah.size();
//            System.out.println("билет №"+theReader.numberBileta+", читатель "+theReader.nameReader+
//                    " на руках "+numOfBooks+" книг");
//        }
//    }

    public int indexByNumber (int number) {
        int index =0;
        List<Reader> bazaReaders = downloadReadersBin();
        for (Reader theReader: bazaReaders) {
            if (theReader.number == number){
                break;
            } else index++;
        }
        return index;
    }

    public static int indexReaderByNumBileta (int number){ // выбор индекас читател по номеру билета
        int index = 0;
        ReaderDataBase bazaTemp = new ReaderDataBase();
        ReaderDataBase bazaReaders = (ReaderDataBase) downloadBaseBin(bazaTemp);
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
            if (knigiNaRukah.size() > 0) {
                System.out.print("На руках у читателя книги: ");
                String nazvanie;
                for (int i = 0; i < knigiNaRukah.size() - 1; i++) {
                    knigiNaRukah.get(i).getNazvanieByIndex();
                    System.out.print(knigiNaRukah.get(i).getNazvanieByIndex() + ", ");
                }
                nazvanie = knigiNaRukah.get(knigiNaRukah.size() - 1).getNazvanieByIndex();
                System.out.println(nazvanie + "."); // печатаем последнюю книгу, печать кончается точкой
            } else System.out.println("У читателя нет книг на руках");
        } catch (Exception e) {
            System.out.println("Возникла ошибка при чтении базы данных");
        }
    }

    public static List<Reader> bazaReadersDownload(){
        List<Reader> bazaReaders = new ArrayList();
        File bdReaders = new File("BdReaders");
        int count = 0;
        //count += downloadReadersBin().size();
        try { Scanner scan = new Scanner(bdReaders);
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
