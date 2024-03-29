package Menu;

/**
 * Корневое главное меню.
 *
 */
public class MainMenu extends Menu{
    String adress = "Главное меню\\_";
    String name = "---=== Главное меню ===---";
    String[] optoins = {"<<help>> ________ показать возможные действия",
                            "<<books>> _______ работа с БД книг",
                            "<<readers>> _____ работа с БД читателей",

                            "<<exit>> ________ выход из программы"};

    public MainMenu(){
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
            case "readers":
                Menu newMenu = new ReadersMenu();
                newMenu.showMenuName();
                menuCycle(newMenu);
                break;
            case "books":
                newMenu = new BooksMenu();
                newMenu.showMenuName();
                menuCycle(newMenu);
                break;
            case "reader":
                chooseReader(command);
                break;
            case "book":
                chooseBookToStartBookMenu(command);
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
