package Menu;

import Storages.Databases;
import Storages.Manager;
import Storages.Reader;
import Storages.ReaderDataBase;

import java.util.List;
import java.util.Scanner;

public class Menu {
    String menuAdress;
    String menuName;
    String[] menuOptions;

    public Menu(){}

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
        for (String theLine: menuOptions ) {
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
        return true;}

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
        }
        else chooseReaderNumber(command[1]);
    }

    public void chooseReaderNumber(String numString) {
        int num = Integer.parseInt(String.valueOf(numString));
        num = Storages.Reader.indexReaderByNumBileta(num); // выбираем индекс по номеру билета
        Databases bazaReaders = Manager.downloadBaseBin(new ReaderDataBase()); // загружаем базу
        Storages.Reader theReader = (Reader) bazaReaders.get(num); // выбираем конкретную запись
        // выбираем конкретного читателя - заходим в его меню.
        ReaderOptionsMenu menu = new ReaderOptionsMenu(theReader);
        menu.showMenuName();
        menuCycle(menu);
    }

    public void enterBookNumber(String[] command) {
        if (command.length < 2) {
            System.out.print("Выберите номер книги_");
            Scanner scan = new Scanner(System.in);
            String num = scan.nextLine();
            chooseNumber(num);
        }
        else chooseNumber(command[1]);
    }

    public void chooseNumber(String numString) { // по номеру в команде выбираем конкретную запись
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

}
