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
            knigiNaRukah.add(index); // делаем запись в список книг на руках
            Book.bookTake(index, bazaKnig); // делаем запись в БД книг
        }
    }

    void bookTake(int index, List<Book> bazaKnig){
        String nazv = Book.getNazvanie(index, bazaKnig);
        String avtor = Book.getAvtor(index, bazaKnig);
        knigiNaRukah.add(index); // делаем запись в список книг на руках
        Book.bookTake(index, bazaKnig); // делаем запись в БД книг
        System.out.println("Читатель "+nameReader+" взял книгу "+nazv+" авт. "+avtor);
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
    public void printReader( List<Book> bazaKnig){ // вывод на печать через единую команду в терминале!
        //Reader theReader = bazaReaders.get(readerNumber);
        System.out.println("Справка о клиенте: "+theBiblioteka+". Читатель "+nameReader+ " "+yearBirth +
                " г.р., читательский билет №" + numberBileta+", тел.: "+ phoneNumber);
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
    }

    public static List<Reader> BazaReadersDownload(){
        List<Reader> bazaReaders = new ArrayList();
        File bdReaders = new File("C:\\Users\\Admin\\IdeaProjects\\LsnBiblioteka\\src\\BdReaders");
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
        }
return bazaReaders;
    }
}
