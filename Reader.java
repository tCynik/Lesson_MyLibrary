import java.util.ArrayList;
import java.util.List;

public class Reader implements Printing {
    static String theBiblioteka = "Библиотека № 13";
    private final String nameReader; // дефалт
    private int numberBileta; // номера ранее выданных билетов
    private final int yearBirth;
    private int phoneNumber;
    //////////////////// перепиши на массив с перечислением индексов книг, которые на руках
    List<int> knigiNaRukah = new ArrayList(); // номера книг, которые на руках у юзера

/////////   разобраться с номерами билетов! Добавить статический метод определения №№ билетов новых юзеров:
///////// перебираем все билеты с 1 по массиву читателей и если есть пустой, то берем его, иначе беерм последний++
    public Reader(String name, int year, int number, int phone){ // создаем конструктор. Номера билетов определяются новые
        this.nameReader = name;                      // в случае необходимости добавить нов конструктор с
        this.yearBirth = year;                       // принудительным вводом № билета на случай удаления аккаунта
        this.numberBileta = number;                              // одного из предыдущих читателей
        this.phoneNumber = phone;
}
/////////   добавить метод удаления читателей: ФИО: "Удален", книги Null, номер билета Null. Если остаются книги,
///////// вывод сообщения: Удаление читателя "ФИО" невозможно: у читателя не возвращены книги: "перечисление книг"
/////////   на будущее сделать отдельную базу для бывших читателей (при удалении)


    void zapisDolga(int[] dolgiIndexes){ // записываем читателю его долги по индексам книг из базы
        for (int i = 0; i< dolgiIndexes.length; i++){
            boolean flag = false;
            for (int k = 0; k < knigiNaRukah.size(); k++) { // ищем пустое место в списке книг
                if (Objects.equals(knigiNaRukah.get(k), null)) {
                    knigiNaRukah.set(k, dolgiIndexes[i]);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                knigiNaRukah.add(dolgiIndexes[i]);
            }
        }
    }
    ////////////////////// перепиши в связи с переводом книг в коллекцию
    public void takeBook(Book book){ // на вводе в метод имя книги, далее поиск индекса по имени -
        ///////////////// смотри метод getIndex(имя, базаКниг)
        String nazv = book.getNazvanie();
        knigiNaRukah.add(nazv);
        System.out.println(nameReader+" взял книгу " + book.getNazvanie());
        book.bookTake();
    }

    //////// перепиши в соотв с замечаниями выше по takeBook()
    void retutnBook(Book book){ // вернул книгу - пишем название книги
        String nazv = book.getNazvanie();
        int bookIndex = knigiNaRukah.indexOf(nazv); // ищем индекс книги в списке
        for (int i = bookIndex; i < knigiNaRukah.size()-1; i++ ) { // убираем возвращенну книгу, сдвигая слежующие влево
            knigiNaRukah.set(i, knigiNaRukah.get(i+1));
        }
        knigiNaRukah.set(knigiNaRukah.size()-1, "-=EMPTY=-");
        System.out.println(nameReader+" возвратил книгу " + book.getNazvanie());
        book.bookPut();
    }
    public void print(){ // вывод на печать через единую команду в терминале!
        System.out.println("Справка о клиенте: "+theBiblioteka+". Читатель "+nameReader+ " "+ yearBirth + " г.р., читательский билет №" + numberBileta+", тел.: "+phoneNumber);
        System.out.print("На руках у читателя книги: ");
///////////////////////// перепиши исходя из того, что у читателя хранятся только №№ книг, но не названия
        for (int i=0; i< knigiNaRukah.size(); i++) {
            if (knigiNaRukah.get(i) != "-=EMPTY=-") {
                System.out.print(knigiNaRukah.get(i));
                System.out.print(", ");
            } else System.out.println(".");
        }
        System.out.println();
    }

    public static void biletNumberNew(){ // определяем очередной номер билета

    }
}
