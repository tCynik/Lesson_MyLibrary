import java.util.List;
import java.util.Scanner;

public class Menu { // the maternal (материнский) class of menus' structure
    private String introductionMessege;
    private String position; // variable to show current position in the menu

    void introduction() { // the method to display current position in the menu
        System.out.println(introductionMessege);
    }

    String inputCommand() { // inputing command in current branch of the menu
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        return command;
    }

    void checkoutMenu() { // navigate to enother branch of the menu
    }

    void ingate() { // ingating an accomplishment(выполнение) of any functoins
    }

    void help() { // <<help>> function to current menu

    }
}
//    public static void main(String[] args) {
//    }
//
//    public static void mainMenu(List<Reader> bazaReaders, List<Book> bazaKnig){
//        System.out.println("Программа <<Билиотекарь>> запущена. Введите команду.");
//        boolean flag = true;
//        while (flag) {
//            System.out.println("команда <<help>> - помощь");
//            System.out.print("_");
//            String command;
//            Scanner scan = new Scanner(System.in);
//            command = scan.nextLine();
//            switch (command) {
//                case "help":
//                    Commands.help();
//                    break;
//                case "books":
//                    Book.allBooks(bazaKnig);
//                    break;
//                case "savebooks":
//                    Book.uploadBooksBin(bazaKnig);
//                    System.out.println("база книг в картотеке сохранена");
//                    break;
//                case "readers":
//                    Reader.allReaders(bazaReaders);
//                    System.out.println("Выберите читателя по номеру билета");
//                    System.out.print("_");
//                    Scanner scanRead = new Scanner (System.in);
//                    int num = scan.nextInt();
//                    bazaReaders.get(num-1).printReader(bazaKnig);
//                    break;
//                case "savereaders":
//                    Reader.uploadReadersBin(bazaReaders);
//                    System.out.println("база читателей в картотеке сохранена");
//                    break;
//                case "exit":
//                    System.out.println("Программа закрывается, хорошего дня");
//                    flag = false;
//                    break;
//                default:
//                    System.out.println("Введена неверная команда, проверьте ввод");
//            }
//        }
//
//
//    }

class MainMenu extends Menu {
    private String introductionMessege = "Главное меню";
    private String position = "_";

    void checoutMenu(){

    }

    void help (){
        System.out.println("список команд главного меню:");
        System.out.println("<<exit>> ___________ выход из программы");
        System.out.println("<<books>> __________ вывести на экран список книг");
        System.out.println("<<savebooks>> ______ сохранить список книг в библиотеке");
        System.out.println("<<readers>> ________ вывести на экран список читателей");
        System.out.println("<<savereaders>> ____ сохранить список читателей в картотеке");
    }
}
