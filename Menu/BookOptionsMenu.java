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
            "<<up>> ___________ выход в меню книг",
            "<<exit>> _________ выход из программы"};

    public BookOptionsMenu(Book theBook){
        this.theOne = theBook;
        this.menuAdress = adress + " " + theOne.getNameBook()+"_";
        this.menuName = name + theOne.getNameBook()+ " ===---";
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
                chooseBook(command);
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
