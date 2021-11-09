import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book  {
    String naznachenie;
    private String nazvanie;
    private String avtor;
    private transient int kolichestvo = 0;
    private List<Integer> bookHolders; // поименованы по читательским билетам держатели книг
    ////////// прикрутить для каждой книги поле - массив с указанием № читательского билета тех, у кого на руках

    public Book (String nazvanie, String avtor, int kolichestvo){ // конструктор книги
        naznachenie = "книга";
        this.nazvanie = nazvanie;
        this.avtor = avtor;
        this.kolichestvo = kolichestvo;
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

    int skolkoKnig () {
        return kolichestvo;
    }

    public static String getNazvanie(int index, List<Book> bazaKnig){
        String nazvKnigi = bazaKnig.get(index).nazvanie;
        return nazvKnigi;
    }

    public static int getIndex(String nameTheBook, List<Book> bazaKnig){
        int index=0; // если не ставить 0, возможно возвращение значения NULL
        for (Book theBook: bazaKnig) {
            if (nameTheBook.equals(theBook.nazvanie)){
                index = bazaKnig.indexOf(theBook);
                break;
            }
        }
        return index;
    }

    public static String getAvtor(int index, List<Book> bazaKnig){
        String nameAvtor;
        nameAvtor=bazaKnig.get(index).avtor;
        return nameAvtor;
    }

////////////////////////////// пилим вывод инфо по книге. На вводе: название, база
    public void print (String nazvanie, List<Book> bazaKnig){ // вывод на печать через единую команду в терминале!
        int indexBook = getIndex(nazvanie, bazaKnig);
        Book theBook = (Book) bazaKnig.get(indexBook);
        System.out.println("Справка об издании: "+naznachenie + " "+ nazvanie+", автор "+avtor+", в библиотеке "
                + kolichestvo+ " экземпляров, на руках ");
        //int howMuchBooks = 0;
        //for (:        прокручивсем всех читателей через массив читателей
        //     ) {      у каждого читателя перебираем список книг на руках в поисках нужного indexBook
                   //// если indexBook подходящий, прибавляем единицу к howMuchBooks

        //}
    }

    public static List<Book> bazaKnigDownload() {// метод для подгрузки БД книг из файла
        List<Book> bazaKnig = new ArrayList();
        File bdBooks = new File("BdBooks");
        Scanner scan1 = null;
        try { scan1 = new Scanner(bdBooks);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл базы данных не обнаружен, библиотека пуста");
        }
        while(scan1.hasNextLine()){
            String theLine = scan1.nextLine(); // записываем построчно содержимое файла
            String[] dannie = theLine.split(", "); // делим запятой строчку на отдельные параметры
            int skolkoKnig =Integer.parseInt(dannie[2]); // переводим в int информацию о количестве книг эого экземпляра
            bazaKnig.add(new Book(dannie[0], dannie[1], skolkoKnig)); // добавляем параметры в ArrayList
        }
        return bazaKnig;
    }

}

class VzrosBook extends Book {
    public VzrosBook (String nazvanie, String avtor, int kolichestvo){
        super(nazvanie, avtor, kolichestvo);
        naznachenie = "том";
    }
}

class DetsBook extends Book {
    public DetsBook (String nazvanie, String avtor, int kolichestvo){
        super(nazvanie, avtor, kolichestvo);
        naznachenie = "учебник";
    }


}