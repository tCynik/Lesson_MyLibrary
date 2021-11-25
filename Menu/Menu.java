package Menu;
/**
 * Корневой класс структуры меню. Является главным хранилищем методов, выполняемых блоком программы Меню.
 * задает общие методы для всех разделов и ярусов меню, все ветки и ярусы меню наследуются отсюда.
 * методы, относящиеся к частному случаю, вызяваются в нужном подменю, обрабатываются там, и возвращают результат
 */

import Storages.*;

import java.util.Scanner;

import static Storages.Book.indexBookByNumber;
import static Storages.CommonDatabaseMethods.uploadBaseBin;

public class Menu {
    String menuAdress;
    String menuName;
    String[] menuOptions;

    public Menu() {
    }

    public Menu(String command) {
    }

    public void showMenuAdress() {
        System.out.print(menuAdress);
    }

    public void showMenuName() {
        System.out.println(menuName);
    }

    public void showMenuOptions() { // show the menu options in current position
        System.out.println("Список команд:");
        for (String theLine : menuOptions) {
            System.out.println(theLine);
        }
    }

    public static String[] menuInput() {
        Scanner scan = new Scanner(System.in);
        String commandLine = scan.nextLine();
        commandLine = commandLine.toLowerCase();
        commandLine = commandLine.trim();
        String[] command = commandLine.split(" ");
        return command;
    }

    public boolean menuSorting(String[] command) { // исполнение метода зависит от состава ветки, переопределяется в ветке.
        return true;
    }

    public void menuCycle(Menu menu) {
        boolean flag = true;
        while (flag) {
            menu.showMenuAdress();
            String[] command = menu.menuInput();
            flag = menu.menuSorting(command);
        }
        showMenuName();
    }

    public void chooseReader(String[] command) {
        if (command.length < 2) {
            System.out.print("Выберите номер читателя_");
            Scanner scan = new Scanner(System.in);
            String num = scan.nextLine();
            chooseReaderNumber(num);
        } else chooseReaderNumber(command[1]);

    }

    private void chooseReaderNumber(String numString) {
        int num = Integer.parseInt(String.valueOf(numString));
        num = Storages.Reader.getIndexByNumBileta(num); // выбираем индекс по номеру билета
        Databases bazaReaders = CommonDatabaseMethods.downloadBaseBin(new ReaderDataBase()); // загружаем базу
        Storages.Reader theReader = (Reader) bazaReaders.get(num); // выбираем конкретную запись
        // выбираем конкретного читателя - заходим в его меню.
        ReaderOptionsMenu menu = new ReaderOptionsMenu(theReader);
        menu.showMenuName();
        menuCycle(menu);
    }

    public void chooseBook(String[] command) {
        if (command.length < 2) {
            System.out.print("Выберите номер книги_");
            Scanner scan = new Scanner(System.in);
            String num = scan.nextLine();
            chooseBookNumber(num);
        } else chooseBookNumber(command[1]);
    }

    private void chooseBookNumber(String numString) {
        int num = Integer.parseInt(String.valueOf(numString));
        num = indexBookByNumber(num); // выбираем индекс по номеру книги
        Databases bazaBooks = CommonDatabaseMethods.downloadBaseBin(new BookDataBase()); // загружаем базу
        Storages.Book theBook = (Book) bazaBooks.get(num); // выбираем конкретную запись
        // выбираем конкретную книгу - заходим в ее меню.
        BookOptionsMenu menu = new BookOptionsMenu(theBook);
        menu.showMenuName();
        menuCycle(menu);
    }

    public static void menuWrongOption() {
        System.out.println("Неверная команда! Повторите ввод.");
        System.out.println(" Для просмотра доступных команд введите <<help>>");
    }

    public void menuExit() {
        System.out.println("Вы уверены что хотите выйти?");
        System.out.print("Для выхода нажмите <<y>>, для отмены любую клавишу _");
        String[] command = menuInput();
        if (command[0].equals("y")) {
            System.out.println("Работа программы завершена. Всего хорошего!");
            System.exit(0);
        }
    }

    public boolean menuUp() {
        return false;
    }

    public void main() {
        MainMenu menu = new MainMenu();
        menu.showMenuName();
        menu.menuCycle(menu);
    }

    public void takeBook(Reader reader) { // запись взятия книги из библиотеки
        chooseBookWhileTaking.takeBook(reader);
    }

    public void putBook () {
        System.out.println("Выберите № книги, которую возвращает читатель");
/**
 * введена команда <<put>> в меню читателя
 * предлагаем выбрать нужную книгу (ввод) можно вызвать help со списком книг
 * сортировка по валидности (введена цифра в интервале выбора) с возможностью выхода
 * делаем соотв записи в базе, выводим сообщении об успешном возврате книги
 */
    }

    ///////////// загрузка долгов
}

 class chooseBookWhileTaking { // класс для реализации методов выбора книги при ее взятии или возвращении
    protected static void takeBook(Reader reader) {
        Databases bazaBooks = CommonDatabaseMethods.downloadBaseBin(new BookDataBase()); // загружаем базу
        Databases bazaReaders = CommonDatabaseMethods.downloadBaseBin(new ReaderDataBase()); // загружаем базу

        boolean flag = true;
        while(flag) {
            System.out.print("Введите номер книги. <<list>> - вывод списка книг _"); ////// прикрутить list
            String[] command = Menu.menuInput();
            flag = sortingWhileChoosingBook(command[0], reader, bazaBooks, bazaReaders);
        }
    }

    protected static boolean sortingWhileChoosingBook(String command, Reader reader, Databases bazaBooks, Databases bazaReaders) {
        boolean flag = true;
        switch (command) {
            case "list":
                CommonDatabaseMethods.showAll(bazaBooks);
                break;
            case "help":
                CommonDatabaseMethods.showAll(new BookDataBase());
                break;
            case "exit":
                Menu menu = new Menu();
                menu.menuExit();
                break;
            default:
                int bookNumber = 0;
                try {
                    bookNumber = Integer.parseInt(command);
                } catch (NumberFormatException e) { wrongOptoin(); break;
                }
                try {
                    Storages.Book book = (Book) bazaBooks.get(indexBookByNumber(bookNumber));
                    makeTheRecords(reader, book, bazaBooks, bazaReaders);
                } catch (IndexOutOfBoundsException e) {wrongOptoin(); break;}
                flag = false;
        }
        return flag;
    }

    protected static void wrongOptoin() {
        System.out.println("Неверная команда, проверьте ввод");
     }
    protected static void makeTheRecords(Reader reader, Book book, Databases bazaBooks, Databases bazaReaders) {
        reader.bookTake(book); // добавляем запись в список книг на руках
        book.bookTake(reader); // добавляем запись в карточку книги

        bazaBooks.set(bazaBooks.getIndexObject(book), book); // меняем обьект книги в массиве БД
        uploadBaseBin(bazaBooks); // сохраняем БД
        bazaReaders.set(bazaReaders.getIndexObject(reader), reader); // заменили запись в базе на обновленный обьект
        uploadBaseBin(bazaReaders); // сохраняем базу

        System.out.println("Читатель "+ reader.getName() +" взял книгу "+book.getNazvanieAndAvtor());

    }
}
