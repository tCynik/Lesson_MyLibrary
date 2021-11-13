import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ---=== The main commands in the programm ===---
 * create the list of the Readers from TXT (first start option) ___________ Reader.bazareadersDownload();
 * create the list of the Books from TXT (first start option) _____________ Book.bazaKnigDownload();
 * download any reader's information from the past using __________________ bazaReaders.get('#Reader').dolgiPastLoad("nameOfBook",");
 * upload total reader's information ito the prog. permanent memory _______ Reader.uploadReadersBin(bazaReaders);
 * download  total reader's information from the prog. permanent memory ___ Reader.downloadReadersBin();
 * download any books information from the TXT (first start option)
 * upload total books information ito the prog. permanent memory
 * download  total books information from the prog. permanent memory
 */

//// исправить ошибки при записи/чтении bin
public class Start {
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        List<Book> bazaKnig = new ArrayList();
        //bazaKnig = Book.bazaKnigDownload(); // массив книг берем из файла TXT
        //Book.uploadBooksBin(bazaKnig);
        bazaKnig = Book.downloadBooksBin();

        List<Reader> bazaReaders = new ArrayList();
        //bazaReaders = Reader.bazaReadersDownload();
        bazaReaders = Reader.downloadReadersBin(); // загрузка читателей из бинарного файла
        //bazaReaders.get(2).dolgiPastLoad("Мурзилка, Довод, Хоббит", bazaKnig);

        Reader.uploadReadersBin(bazaReaders); // базу по читателям пишем в бинарный файл

        // main menu
        System.out.println("Программа <<Билиотекарь>> запущена. Введите команду.");
        boolean flag = true;
        while (flag) {
            System.out.println("команда <<help>> - помощь");
            System.out.print("_");
            String command;
            Scanner scan = new Scanner(System.in);
            command = scan.nextLine();
            switch (command) {
                case "help":
                    Commands.help();
                    break;
                case "books":
                    allBooks(bazaKnig);
                    break;
                case "savebooks":
                    Book.uploadBooksBin(bazaKnig);
                    System.out.println("база книг в картотеке сохранена");
                    break;
                case "readers":
                    Reader.allReaders(bazaReaders);
                    System.out.println("Выберите читателя по номеру билета");
                    System.out.print("_");
                    Scanner scanRead = new Scanner (System.in);
                    int num = scan.nextInt();
                    bazaReaders.get(num-1).printReader(bazaKnig);
                    break;
                case "savereaders":
                    Reader.uploadReadersBin(bazaReaders);
                    System.out.println("база читателей в картотеке сохранена");
                    break;
                case "exit":
                    System.out.println("Программа закрывается, хорошего дня");
                    flag = false;
                    break;
                default:
                    System.out.println("Введена неверная команда, проверьте ввод");
            }
        }
        

        //Reader.uploadReadersBin(bazaReaders); // базу по читателям пишем в бинарный файл

        //bazaReaders.get(2).bookTake(4, bazaKnig);
        //bazaReaders = Reader.bazaReadersDownload(); // загрузка базы читателей из TXT
        //bazaReaders.get(2).dolgiPastLoad("Мурзилка, Довод, Хоббит", bazaKnig);






        ///////////////// В читателях книги на руках и в книгах - у кого на руках: данные ссылочного типа на конкретный
        /////////////////     экземпляр книги/читателя
        // класс читатель - 2 потомка подкласса - взрослый или ребенок. Взрослому № телефона, ребенку - № класса

        //////////// допили операции возврата и взятия книги:
        //////////// раньше все книги хранились как отдельные объекты, теперь как коллекция
        //////////// В исходных пишем название, по названию ищем №, название записываем в сообщение о взятии,
        //////////// по № вносим изменения в количество
        // берем книгу читателем 1 книгу русский язык
        //reader1.takeBook(rusyaz); // на вводе в метод имя обьекта класса Book
        //reader2.retutnBook(dovod);

///////////////     реализовать функцию добавления новой книги
/////////////// для чего в классе Book создать метод, принимающий на вход параметры книги, и возвращающий
/////////////// объект книгу, записанную в нашу коллекцию. ТАКОЕ ВОЗМОЖНО?????!!!

    }
    // вызов списка всех книг
    public static void allBooks(List<Book> bazaKnig){ // метод вывода списка всех книг
        System.out.println("Книги в библиотеке:");
        int num = 1;
        for (Book theBook: bazaKnig) {
            System.out.print(num+" ");
            theBook.print(theBook.getNazvanie(num-1, bazaKnig), bazaKnig);
            num++;
        }
    }

    /*
    public static void dolgiInput(Reader reader, String dolgChit, List<Book> bazaKnig){ // метод интеграции старых долгов читателя
        String[] slova; // массив с именами книг долгов
        String razdel = ", ";
        slova = dolgChit.split(razdel);
        int[] dolgiIndexes = new int[slova.length]; // массив под индексы книг у читателя по старым долгам
        for (int i =0; i < slova.length; i++){
            dolgiIndexes[i] = Book.getIndex(slova[i], bazaKnig); // получаем индекс книги из метода в Book
        }
        reader.zapisDolga(dolgiIndexes); // записываем читателю его долги по индексам книг из базы
    }

     */

}

