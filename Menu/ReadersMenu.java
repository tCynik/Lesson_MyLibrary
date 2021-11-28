package Menu;

import Storages.BookDataBase;
import Storages.Databases;
import Storages.ReaderDataBase;

/**
 * Меню опций действия управления записями читателей и базой данных по читателям
 */

public class ReadersMenu extends Menu {
    String adress = "Главное меню\\Читатели\\_";
    String name = "---=== Меню БД читателей ===---";
    String[] optoins = {"<<help>> ________ показать возможные действия",
                        "<<list>> ________ показать список читателей",
                        "<<reader>> ______ вывести читателя с заданным номером>>", // тут взять/вернуть книгу
            //"<<books>> _______ работа с БД книг",
                        "<<baza>> _____ работа с БД читателей", // тут выгрузка, сохранение базы, и т.д.
                        "<<up>> __________ назад",
                        "<<main>> ________ выход в главное меню",
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
                Databases menu = Databases.downloadBaseBin(new ReaderDataBase());
                menu.printAll();
                //Databases.showAll(Databases.downloadBaseBin(new ReaderDataBase()));
                break;
            case "reader":
                chooseReader(command);
                break;
            case "baza":
                System.out.println("working with the datbase");
                break;
            case "main":
                exitToMain();
                break;
            case "up":
                flag = menuUp();
                break;
            case "exit":
                menuExit();
                break;
            default:
                menuWrongOption();
        }
        return flag;
    }
}
