import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
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

    void dolgiPastLoad(String knigi, List<Book> bazaKnig){ // загружаем долги прошлого времени по книгам
        String[] knigiArray = knigi.split(", ");
        for (String theKniga: knigiArray) {
            int index = Book.getIndex(theKniga, bazaKnig);
            bookRecTake(index, bazaKnig);
        }
    }

    void bookTake(String theKniga, List<Book> bazaKnig){
        int index = Book.getIndex(theKniga, bazaKnig);
        bookRecTake(index, bazaKnig);
        System.out.println("Читатель взял книгу "+theKniga);
    }

    private void bookRecTake(int index, List<Book> bazaKnig){
        // делаем запись в БД читателей - список книг на руках
        knigiNaRukah.add(index);

        ///////////////// делаем запись в БД книг - количество и список у кого на руках
        Book.bookTake(index, bazaKnig);
        // запись -1 к текущей книге

    }

    ////////////////////// перепиши в связи с переводом книг в коллекцию
    /*
    public void takeBook(String bookName, List<Book> bazaknig){ // на вводе в метод имя книги, далее поиск индекса по имени -
        ///////////////// смотри метод getIndex(имя, базаКниг)
        String nazv = book.getNazvanie();
        knigiNaRukah.add(nazv);
        System.out.println(nameReader+" взял книгу " + book.getNazvanie());
        book.bookTake();
    }

     */

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
    public static void printReader(int readerNumber, List<Reader> bazaReaders, List<Book> bazaKnig){ // вывод на печать через единую команду в терминале!
        Reader theReader = bazaReaders.get(readerNumber);
        System.out.println("Справка о клиенте: "+theBiblioteka+". Читатель "+theReader.nameReader+ " "+
                theReader.yearBirth + " г.р., читательский билет №" + theReader.numberBileta+", тел.: "+
                theReader.phoneNumber);
        if (theReader.knigiNaRukah.size() > 0) {
            System.out.print("На руках у читателя книги: ");
            String nazvanie;
            for (int i = 0; i < theReader.knigiNaRukah.size() - 1; i++) {
                nazvanie = Book.getNazvanie(theReader.knigiNaRukah.get(i), bazaKnig);
                System.out.print(nazvanie + ", ");
            }
            nazvanie = Book.getNazvanie(theReader.knigiNaRukah.get(theReader.knigiNaRukah.size() - 1), bazaKnig);
            System.out.println(nazvanie + "."); // печатаем последнюю книгу
        } else System.out.println("У читателя нет книг на руках");
    }

    public static void biletNumberNew(){ // определяем очередной номер билета

    }

    /////////// переопределить исключение в виде ошибки "Файл не найден, база данных пользователей в приложении пуста"
    public static List<Reader> BazaReadersDownload() throws FileNotFoundException {
        List<Reader> bazaReaders = new ArrayList();
        File bdReaders = new File("C:\\Users\\Admin\\IdeaProjects\\LsnBiblioteka\\src\\BdReaders");
        Scanner scan = new Scanner(bdReaders);
        int count = 0;
        while (scan.hasNextLine()){
            String theLine = scan.nextLine();
            String[] pole = theLine.split(", ");
            int yearBirth = Integer.parseInt(pole[1]);
            count++;
            int numberBileta = count;
            bazaReaders.add(new Reader(pole[0], yearBirth, numberBileta, pole[2] ));
        }
        return bazaReaders;
    }
}
