package Menu;

import java.io.Reader;
import java.util.List;
import java.util.Scanner;

public class Menu {
    String menuAdress;
    String menuName;
    String[] menuOptions;

    public Menu(){}

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

    public void listReaders () {
        Storages.Reader.allReaders();
    }

    public void chooseReader () {
        System.out.print("Введите номер читателя_");
        Scanner scan = new Scanner(System.in);
        int numString = scan.nextInt();
        int num = Integer.parseInt(String.valueOf(numString));
        List<Storages.Reader> bazaReaders = Storages.Reader.downloadReadersBin();
        Storages.Reader theReader = bazaReaders.get(num);
//////// сейчас будет костыль. Переписать Reader и Book так чтобы не передавать туда базу каждый раз - нехай тянут сами
        List<Storages.Book> bazaKnig = Storages.Book.downloadBooksBin();
        theReader.printReader(bazaKnig);
    }

    public void chooseReaderNum (String numString) {
        int num = Integer.parseInt(String.valueOf(numString));
        List<Storages.Reader> bazaReaders = Storages.Reader.downloadReadersBin();
        Storages.Reader theReader = bazaReaders.get(num);
//////// сейчас будет костыль. Переписать Reader и Book так чтобы не передавать туда базу каждый раз - нехай тянут сами
        List<Storages.Book> bazaKnig = Storages.Book.downloadBooksBin();
        theReader.printReader(bazaKnig);
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
