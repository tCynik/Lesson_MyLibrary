package Menu;

import Storages.*;

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
                Manager.showAll(Manager.downloadBaseBin(new ReaderDataBase()));
                break;
            case "reader":
                chooseReader(command);
                break;
            case "baza":
                System.out.println("working with the datbase");
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

//    public static void chooseNumber(String numString) { ////// не взлетело! переносим назад в майнменю, chooseReaderNumber
//        int num = Integer.parseInt(String.valueOf(numString));
//        num = Storages.Reader.indexReaderByNumBileta(num); // выбираем индекс по номеру билета
//        Databases bazaReaders = Manager.downloadBaseBin(new ReaderDataBase()); // загружаем базу //// вроде получается норм
//        Storages.Reader theReader = (Reader) bazaReaders.get(num); // выбираем конкретную запись
//        // выбираем конкретного читателя - заходим в его меню.
//        ReaderOptionsMenu menu = new ReaderOptionsMenu(theReader);
//        menu.showMenuName();
//        menuCycle(menu);
//    }

}
