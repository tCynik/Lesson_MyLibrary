package Storages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book implements Serializable {
    private int invNumber; // индивидуальный инвентарный номер книги
    String naznachenie; ////////////// неакктуальная фича, удалить
    private String nazvanie;
    private String avtor;
    private int kolichestvo = 0; // количество книг, которые в библиотеке
    private List<Integer> bookHolders; // поименованы по читательским билетам держатели книг
    ////////// тут тохраняем ссылки на конеретных читателей (объекты), у кого на руках наша книга

    public Book (int invNumber, String nazvanie, String avtor, int kolichestvo){ // конструктор книги
        this.invNumber = invNumber;
        naznachenie = "книга";
        this.nazvanie = nazvanie;
        this.avtor = avtor;
        this.kolichestvo = kolichestvo;
    }

    public String getNazvanie(){
        return nazvanie;
    }

    public static void showAll(){ // метод вывода списка всех книг
        List<Book> bazaKnig = downloadBooksBin();
        System.out.println("Книги в библиотеке:");
        int num = 1;
        for (Book theBook: bazaKnig) {
            System.out.print(num+" ");
            theBook.print(theBook.getNazvanie(num-1, bazaKnig), bazaKnig);
            num++;
        }
    }

    public void print (String nazvanie, List<Book> bazaKnig){ // вывод на печать через единую команду в терминале!
        int indexBook = getIndex(nazvanie, bazaKnig);
        Book theBook = (Book) bazaKnig.get(indexBook);
        System.out.println("Справка об издании: "+naznachenie + " "+ nazvanie+", автор "+avtor+", в библиотеке "
                + kolichestvo+ " экземпляров, на руках ");
    }

    public void info(){
        System.out.println("Справка об издании: "+naznachenie + " "+ nazvanie+", автор "+avtor+", в библиотеке "
                + kolichestvo+ " экземпляров, на руках ");
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

    public static String getAvtor(int index, List<Book> bazaKnig){ // получаем имя автора по индексу книги
        String nameAvtor;
        nameAvtor=bazaKnig.get(index).avtor;
        return nameAvtor;
    }

    ////////////////////////////// пилим вывод инфо по книге. На вводе: название, база

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
            bazaKnig.add(new Book(1, dannie[0], dannie[1], skolkoKnig)); // добавляем параметры в ArrayList
        }
        return bazaKnig;
    }

    public static void uploadBooksBin (List<Book> bazaKnig) { // Writing the book's database into the books.bin
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("books.bin"))) {
            obj.writeObject(bazaKnig);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> downloadBooksBin () {
        List<Book> bazaKnig = new ArrayList();
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream("books.bin"))) {
            bazaKnig = (List<Book>)obj.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bazaKnig;
    }
}