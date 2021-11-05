import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book implements Printing {
    String naznachenie;
    private String nazvanie;
    private String avtor;
    private int kolichestvo = 0;
    ////////// у каждой книги д.б. поле - массив с указанием № читательского билета тех, у кого на руках

    public Book (String nazvanie, String avtor, int kolichestvo){ // конструктор книги
        naznachenie = "книга";
        this.nazvanie = nazvanie;
        this.avtor = avtor;
        this.kolichestvo = kolichestvo;
    }
    void bookTake () {
        kolichestvo--;
    }

    void bookPut () {
        kolichestvo++;
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

    public String getAvtor(){
        return avtor;
    }

    public void print (){ // вывод на печать через единую команду в терминале!
        System.out.println("Справка об издании: "+naznachenie + " "+ nazvanie+", автор "+avtor+", в библиотеке " + kolichestvo+ " экземпляров");
    }

    public static List<Book> bazaKnigDownload() throws FileNotFoundException {// метод для подгрузки БД книг из файла
        List<Book> bazaKnig = new ArrayList();
        File bdBooks = new File("C:\\Users\\Admin\\IdeaProjects\\LsnBiblioteka\\src\\BdBooks");
        Scanner scan1 = new Scanner(bdBooks);
        while(scan1.hasNextLine()){
            String theLine = scan1.nextLine();
            String[] dannie = theLine.split(", ");
            int skolkoKnig =Integer.parseInt(dannie[2]);
            bazaKnig.add(new Book(dannie[0], dannie[1], skolkoKnig));
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