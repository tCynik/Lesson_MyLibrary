package Menu;

public class ReaderOptionsMenu extends Menu {
    Storages.Reader theReader;
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

    public ReaderOptionsMenu(Storages.Reader theReader){
        this.theReader = theReader;
        this.menuAdress = adress + " " + theReader.getNameReader()+"_";
        this.menuName = name + theReader.getNameReader()+ " ===---";
        this.menuOptions = optoins;
    }

    public boolean menuSorting (String[] command){
        boolean flag = true;
        switch (command[0]) {
            case "help":
                showMenuOptions();
                break;
            case "info":
                theReader.printReader();
                break;
            case "reader":
                enterReaderNumber(command);
                break;
            case "take":
                System.out.println("take the book");
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
