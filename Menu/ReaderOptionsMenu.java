package Menu;

/**
 * меню опций действия с конкретной записью читателя
 */
public class ReaderOptionsMenu extends Menu {
    Storages.Reader theOne;
    String adress = "Главное меню\\Читатели\\Меню читателя";
    String name = "---=== Меню читателя ";
    String[] optoins = {
            "<<help>> _________ показать возможные действия",
            "<<info>> _________ информация о читателе",
            "<<take>> _________ Читатель взял книгу>>", // тут взять/вернуть книгу
            "<<put>> __________ читатель вернул книгу",
            "<<reader #>> _____ вывести меню читателя #", // тут выгрузка, сохранение базы, и т.д.
            "<<up>> ___________ выход в меню читателей",
            "<<exit>> _________ выход из программы"};

    public ReaderOptionsMenu(Storages.Reader theOne){
        this.theOne = theOne;
        this.menuAdress = adress + " " + theOne.getNameReader()+"_";
        this.menuName = name + theOne.getNameReader()+ " ===---";
        this.menuOptions = optoins;
    }

    public boolean menuSorting (String[] command){
        boolean flag = true;
        switch (command[0]) {
            case "help":
                showMenuOptions();
                break;
            case "info":
                theOne.printReader();
                break;
            case "reader":
                chooseReader(command);
                break;
            case "take":
                Menu.takeBook(theOne);
                break;
            case "put":
                System.out.println("put the book");
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
