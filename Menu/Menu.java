package Menu;
/**
 * Корневой класс структуры меню. Является главным хранилищем методов, выполняемых блоком программы Меню.
 * задает общие методы для всех разделов и ярусов меню, все ветки и ярусы меню наследуются отсюда.
 * методы, относящиеся к частному случаю, вызяваются в нужном подменю, обрабатываются там, и возвращают результат
 */

import Storages.*;

import java.util.Scanner;

import static Storages.Book.indexBookByNumber;
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
        num = indexBookByNumber(num); // выбираем индекс по номеру книги
        Databases bazaBooks = Manager.downloadBaseBin(new BookDataBase()); // загружаем базу
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

    public static void takeBook(Reader reader) { // запись взятия книги из библиотеки
        chooseBook.takeBook(reader);
    }
}

 class chooseBook{ // класс для реализации методов выбора книги при ее взятии или возвращении
    protected static void takeBook(Reader reader) {
        Databases bazaBooks = Manager.downloadBaseBin(new BookDataBase()); // загружаем базу
        Databases bazaReaders = Manager.downloadBaseBin(new ReaderDataBase()); // загружаем базу
        int bookNumber =0;

        boolean flag = true;
        while(flag) {
            System.out.print("Введите номер книги. <<list>> - вывод списка книг_"); ////// прикрутить list
            String[] command = Menu.menuInput();
            sort(command[0], reader, bazaBooks, bazaReaders);
        }
/** вар. 1
 * далее пытаемся определить 1 часть введенного как цифру
 *  ЕСЛИ определяется - изучаем цифру
 *      ЕСЛИ цифра не длиннее списка, выбираем книгу, ИНАЧЕ выдаем ошибку и повторный ввод
 *  ЕСЛИ не определяется - изучаем слово
 *      ЕСЛИ слово из списка команд, делаем команду иначе выдаем ошибку и повторный ввод
 *
 * вар.2
 * запускаем сортировку
 *      сначала отбиваем все известные команды
 *      потом безем цифры от 1 до bazaBooks.size(), их принимаем в качестве агрумента для выбора книги
 *      остальное бросаем в ошибку
  */
//            try {
//                bookNumber = Integer.parseInt(command[0]); // проверяем, превращается ли команда в цифры
//                if (bookNumber > (bazaBooks.size() - 1) | bookNumber == 0) {
//                    wrongOptoin();
//                }
//            } catch (IndexOutOfBoundsException e) { // если не превращается, пропускаем через сортировку команд
//                flag = sorting(command[0]);
//                if (flag == false)
//                    continue;
//            } catch (NumberFormatException e) {
//                flag = sorting(command[0]);
//                if (flag == false)
//                    continue;
//            }
    }

    protected static boolean sort (String command, Reader reader, Databases bazaBooks, Databases bazaReaders) {
        boolean flag = true;
        switch (command) {
            case "list":
                Manager.showAll(bazaBooks);
                break;
            case "help":
                Manager.showAll(new BookDataBase());
                break;
            case "exit":
                flag = false;
                break;
            default:
                int bookNumber = Integer.parseInt(command);
                makeTheRecords(reader, bookNumber, bazaBooks, bazaReaders);
        }
        return flag;
    }

    protected static boolean sorting (String command) {
        boolean flag = true;
        switch (command) {
            case "help":
                Manager.showAll(new BookDataBase());
                break;
            case "exit":
                flag = false;
                break;
            default:
                wrongOptoin();
        }
        return flag;
    }
    protected static void wrongOptoin() {
        System.out.println("Неверная команда, првоерьте ввод");
     }
    protected static void makeTheRecords(Reader reader, int number, Databases bazaBooks, Databases bazaReaders) {
//////// в этом методе ошибка NullPointerException, найти и обезвредить
        Storages.Book book = (Book) bazaBooks.get(indexBookByNumber(number)); // выбираем конкретную запись

        reader.bookTake(book); // добавляем запись в список книг на руках
        book.bookTake(reader); // добавляем запись в карточку книги

        bazaBooks.set(book.getNumber(), book); // меняем обьект книги в массиве БД
        uploadBaseBin(bazaBooks); // сохраняем БД
        bazaReaders.set(reader.getNumber(), reader); // заменили запись в базе на обновленный обьект
        uploadBaseBin(bazaReaders); // сохраняем базу

        System.out.println("Читатель "+ reader.getNameReader() +" взял книгу "+ book.getNameBook());
    }
}
