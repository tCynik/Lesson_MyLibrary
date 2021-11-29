package Menu;

import Storages.Book;

public class BookOptionsMenu extends Menu {
    Storages.Book theOne; // сразу создае обьект книги, с которым будем роботать
    String adress = "Главное меню\\Книги\\Меню издания";
    String name = "---=== Меню издания ";
    String[] optoins = {
            "<<help>> _________ показать возможные действия",
            "<<info>> _________ информация о книге",
            "<<kard>> _________ данные о держателях книги", // тут взять/вернуть книгу
            "<<book #>> _______ вывести меню книги #", // тут выгрузка, сохранение базы, и т.д.
            "<<come>> _________ поставить на приход книгу",
            "<<out>> __________ списать книгу",
            "<<up>> ___________ назад",
            "<<main>> _________ выход в главное меню",
            "<<exit>> _________ выход из программы"};

    public BookOptionsMenu(Book theBook){
        this.theOne = theBook;
        this.menuAdress = adress + " " + theOne.getNazvanieAndAvtor()+"_";
        this.menuName = name + theOne.getNazvanieAndAvtor()+ " ===---";
        this.menuOptions = optoins;
    }

    public boolean menuSorting (String[] command){
        boolean flag = true;
        switch (command[0]) {
            case "help":
                showMenuOptions();
                break;
            case "info":
                theOne.info();
                break;
            case "reader":
                chooseReader(command);
                break;
            case "book":
                chooseBookToStartBookMenu(command);
                break;
            case "take":
                System.out.println("take the book");
                break;
            case "put":
                System.out.println("put the book");
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
