package Menu;

import java.io.Reader;
import java.util.List;
import java.util.Scanner;

public class ReadersMenu extends Menu {
    String adress = "Главное меню\\Читатели\\_";
    String name = "---=== Меню БД читателей ===---";
    String[] optoins = {"<<help>> ________ показать возможные действия",
                        "<<list>> ________ показать список читателей",
                        "<<reader>> ______ вывести читателя с заданным номером>>", // тут взять/вернуть книгу
            //"<<books>> _______ работа с БД книг",
                        "<<baza>> _____ работа с БД читателей", // тут выгрузка, сохранение базы, и т.д.
                        "<<up>> __________ выход в главное меню",
                        "<<exit>> ________ выход из программы"};

    public ReadersMenu(){
        this.menuAdress = adress;
        this.menuName = name;
        this.menuOptions = optoins;
    }

    public boolean menuSorting (String[] command){
        boolean flag = true;
        switch (command[0]) {
            case "help":
                showMenuOptions();
                break;
            case "list":
                System.out.println("Список читателей библиотеки:");
                listReaders();
                break;
            case "reader":
                chooseReader(command);
                break;
            case "view":
                //viewReaders();
                System.out.println("view readers");
                break;
            case "up":
                flag = menuUp();
                break;
            case "exit":
                menuExit();
            default:
                menuWrongOption();
        }
        return flag;
    }
}
