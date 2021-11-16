package Menu;

import java.io.Reader;
import java.util.List;

public class ReadersMenu extends Menu {
    String adress = "Главное меню\\Читатели\\_";
    String name = "---=== Меню БД читателей ===---";
    String[] optoins = {"<<help>> ________ показать возможные действия",
                        "<<list>> ________ показать список читателей",
            //"<<books>> _______ работа с БД книг",
            //"<<readers>> _____ работа с БД читателей",
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
                ///////// читаем базу, вызываем метод alldeaders
                List baza = Memorise.bazaReadersDownload();
            ///утро case
            ///////// делаем вариант выбрать пользователя reader +" "+#читателя, переходим в подменю с вызовом конк читатея

            case "readers":
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
}
