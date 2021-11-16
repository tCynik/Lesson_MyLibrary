package Menu;

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

    public String menuInput() {
        String command= null;
        Scanner scan = new Scanner(System.in);
        command = scan.nextLine();
        return command;
    }

    public boolean menuSorting(String command) {
        return true;}

    public void menuCycle(Menu menu) {
        boolean flag = true;
        while (flag) {
            menu.showMenuAdress();
            String command = menu.menuInput();
            flag = menu.menuSorting(command);
        }
        showMenuName();
    }

        public void menuWrongOption() {
        System.out.println("Неверная команда! Повторите ввод");
    }

    public void menuExit() {
        System.out.println("Вы уверены что хотите выйти?");
        System.out.print("Для выхода нажмите <<y>>, для отмены любую клавишу _");
        if (menuInput().equals("y")) {
            System.out.println("Работа программы завершена. Всего хорошего!");
            System.exit(0);
        }
    }

    public boolean menuUp() {
        return false;
    }


}
