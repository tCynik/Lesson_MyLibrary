package Menu;

import Storages.BookDataBase;
import Storages.CommonDatabaseMethods;

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
                CommonDatabaseMethods.showAll(CommonDatabaseMethods.downloadBaseBin(new BookDataBase()));
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
                main();
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
