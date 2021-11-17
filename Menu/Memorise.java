package Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Memorise {
    public static List<Reader> bazaReadersDownload(){
        List<Reader> bazaReaders = new ArrayList();
        File bdReaders = new File("BdReaders");
        Scanner scan = null;
        try { scan = new Scanner(bdReaders);
            int count = 0;
            while (scan.hasNextLine()){
                String theLine = scan.nextLine();
                String[] pole = theLine.split(", ");
                int yearBirth = Integer.parseInt(pole[1]);
                count++;
                int numberBileta = count;
                //Reader reader = new Reader(pole[0], yearBirth, numberBileta, pole[2]);
                //bazaReaders.add(reader);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. База данных по читателям пуста.");
            //} catch (ParseException e) {
            //    System.out.println("Ошибка ввода информации - неверные данные.");
        }
        return bazaReaders;
    }

}
