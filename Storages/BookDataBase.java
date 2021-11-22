package Storages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDataBase extends Databases{

    public BookDataBase () {
        this.localBaseName = "Books database";
        this.baseAdressTxt = "BdBooks";
        this.baseAdressBin = "booksbin.bin";
    }

    @Override
    public Book fillFields (int count, String theLine){
        String[] pole = theLine.split(", ");
        int invNumber = count;
        Book book = new Book(invNumber, pole[0], pole[1], Integer.parseInt(pole[2]));
        return book;
    }

    @Override
    public void printLine(Object object) {
        Book book = (Book) object;
        book.info();
    }

}
