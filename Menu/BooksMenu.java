package Menu;

public class BooksMenu extends Menu implements InterfaceMenu{
    String adress = "Главное меню\\Книги\\_";
    String name = "---=== Меню БД книг ===---";
    String[] optoins = {
            "<<help>> ________ показать возможные действия",
            "<<list>> ________ показать список книг",
            "<<book #>> ______ вывести кингу с заданным номером>>", // тут взять/вернуть книгу
            "<<baza>> ________ работа с БД книг", // тут выгрузка, сохранение базы, и т.д.
            "<<up>> __________ выход в главное меню",
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
                list();
                break;
            case "book":
                ////// интерфейс в зависимости от того, выбираем ли книгу или читателя
                enterReaderNumber(command);
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

    @Override
    public void list() {
        Storages.Book.showAll();
    }
}
