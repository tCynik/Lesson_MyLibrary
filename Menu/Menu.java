package Menu;
/**
 * Корневой класс структуры меню.
 * задает общие методы для всех разделов и ярусов меню.
 * методы, относящиеся к частному случаю, вызяваются в нужном подменю, обрабатываются там, и возвращают результат
 */

import Storages.*;

import java.util.Scanner;

import static Storages.Manager.uploadBaseBin;

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

    public String[] menuInput() {
        Scanner scan = new Scanner(System.in);
        String commandLine = scan.nextLine();
        commandLine = commandLine.toLowerCase();
        commandLine = commandLine.trim();
        String[] command = commandLine.split(" ");
        return command;
    }

    public boolean menuSorting(String[] command) {
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
        num = Storages.Reader.indexReaderByNumBileta(num); // выбираем индекс по номеру билета
        Databases bazaReaders = Manager.downloadBaseBin(new ReaderDataBase()); // загружаем базу
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
        num = Storages.Book.indexBookByNumber(num); // выбираем индекс по номеру книги
        Databases bazaBooks = Manager.downloadBaseBin(new BookDataBase()); // загружаем базу
        Storages.Book theBook = (Book) bazaBooks.get(num); // выбираем конкретную запись
        // выбираем конкретную книгу - заходим в ее меню.
        BookOptionsMenu menu = new BookOptionsMenu(theBook);
        menu.showMenuName();
        menuCycle(menu);
    }

    public void menuWrongOption() {
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

    public static void takeBook(Reader reader) { // запись взятия книги из библиотеки
        Databases bazaBooks = Manager.downloadBaseBin(new BookDataBase()); // загружаем базу
        Databases bazaReaders = Manager.downloadBaseBin(new ReaderDataBase()); // загружаем базу
        int bookNumber =0;

        boolean flag = true;
        while(flag) {
            System.out.print("Введите номер книги. <<list>> - вывод списка книг_"); ////// прикрутить list
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();
            command = command.toLowerCase().trim();

            switch (command) {
                case "list":
                    Manager.showAll(new BookDataBase());
                    break;
                case "exit":
                    flag = false;
                default:
                    try {
                        bookNumber = Integer.parseInt(command); // проверяем, превращается ли команда в цифры
                        if (bookNumber > (bazaBooks.size()-1)) {
                            System.out.println("недопустимое число, повторите ввод. <<exit>> - выход");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("неверный номер, повторите ввод. <<exit>> - выход");;
                    }        }
        }
        if (bookNumber !=0 ) {
            Storages.Book book = (Book) bazaBooks.get(bookNumber); // выбираем конкретную запись

            reader.bookTake(book); // добавляем запись в список книг на руках
            book.bookTake(reader);

            bazaBooks.set(book.getNumber(), book);
            uploadBaseBin(bazaBooks);
            bazaReaders.set(reader.getNumber(), reader); // заменили запись в базе на обневленный обьект
            uploadBaseBin(bazaReaders); // сохраняем базу
        }

    }
}
