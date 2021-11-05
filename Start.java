import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class Start {
    public static void main (String[] args) throws FileNotFoundException {
        List<Book> bazaKnig = Book.bazaKnigDownload(); // массив книг берем из файла

        ///////////////// читателей реализовать так же через коллекцию.
        ///////////////// там же читателям присваивать String через запятую с долгами книг, и дальше записывать долги
        ///////////////// читателям добавить список книг которые на руках
        // класс читатель - 2 потомка подкласса - взрослый или ребенок. Взрослому № телефона, ребенку - № класса
        //int lastNumBileta = 1; // peremennaya nachala scheta biletov - потом допилить вызов конструктора через цикл с ном билета++
        Reader reader1 = new Reader("Иван Иванов", 1991, 1,494036); // создать отдельный класс с базой данных
        reader1.dolgiPastLoad("Мурзилка, Довод, Хоббит", bazaKnig);

        Reader reader2 = new Reader("Семен Васильев", 1979,2, 493722);
        reader2.dolgiPastLoad("1984", bazaKnig);

        //reader1.print(bazaKnig); // отсылка к интерфейсу
        //reader2.print(bazaKnig);

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

// выводим список книг из нашей библиотеки - временно закомментим
        allBooks(bazaKnig); //
    }

    //////////// вызов списка всех книг
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

