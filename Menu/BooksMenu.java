package Menu;

import Storages.BookDataBase;
import Storages.Databases;

public class BooksMenu extends Menu {
    String adress = "Главное меню\\Книги\\_";
    String name = "---=== Меню БД книг ===---";
    String[] optoins = {
            "<<help>> ________ показать возможные действия",
            "<<list>> ________ показать список книг",
            "<<book #>> ______ вывести кингу с заданным номером>>", // тут взять/вернуть книгу
            "<<baza>> ________ работа с БД книг", // тут выгрузка, сохранение базы, и т.д.
            "<<up>> __________ назад",
            "<<main>> ________ выход в главное меню",
            "<<exit>> ________ выход из программы"};

    public BooksMenu(){
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
                System.out.println("Список книг:");
                Databases menu = Databases.downloadBaseBin(new BookDataBase());
                menu.printAll();
                //menu.printBase();

                //CommonDatabaseMethods.showAll(CommonDatabaseMethods.downloadBaseBin(new BookDataBase()));
                ////// убрать срань, сделать прямую ссылку на метод в суперклассе
                ////// повторить в меню читателей
                ////// убить лишний метод
                break;
            case "book":
                chooseBook(command);
                break;
            case "view":
                System.out.println("view readers");
                break;
            case "up":
                flag = menuUp();
                break;
            case "main":
                exitToMain();
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
