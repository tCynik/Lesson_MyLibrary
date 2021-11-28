package Storages;

/**
 * Суперкласс классов Читатель и Книга.
 * содержит общие методы классов Читатель и Книга
 */

///// не является наследником Databases
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
        book.printLine();
    }

}
