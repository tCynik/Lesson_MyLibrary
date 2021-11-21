import Menu.MainMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import Storages.*;

/**
 * разобраться с передачей методов между пакетами и модиф. доступа
 * использовать единые методы для чтения базы
 */

//// исправить ошибки при записи/чтении bin
public class Start {
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        ReaderDataBase readersData = (ReaderDataBase) Manager.downloadBaseTxt(new ReaderDataBase());
        Manager.uploadBaseBin(readersData);
        ReaderDataBase readersData2 = (ReaderDataBase) Manager.downloadBaseBin(new ReaderDataBase());


//        List<Storages.Book> bazaKnig = new ArrayList();
//        bazaKnig = Storages.Book.bazaKnigDownload(); // массив книг берем из файла TXT
//        Storages.Book.uploadBooksBin(bazaKnig);
//        //bazaKnig = Book.downloadBooksBin();
//
//        List<Storages.Reader> bazaReaders = new ArrayList();
//        bazaReaders = Storages.Reader.bazaReadersDownload();
//        //bazaReaders = Reader.downloadReadersBin(); // загрузка читателей из бинарного файла
//        bazaReaders.get(2).dolgiPastLoad("Мурзилка, Довод, Хоббит", bazaKnig);
//
//        Storages.Reader.uploadReadersBin(bazaReaders); // базу по читателям пишем в бинарный файл
//
//        // main menu
//        Menu.MainMenu menu = new Menu.MainMenu();
//        System.out.println("Программа <<Билиотекарь>> запущена. Введите команду.");
//        menu.showMenuName();
//        menuCycle(menu);
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

    public static void menuCycle(MainMenu menu) {
        boolean flag = true;
        while (flag) {
            menu.showMenuAdress();
            String[] command;
            command = menu.menuInput();
            flag = menu.menuSorting(command);
        }
    }
}

