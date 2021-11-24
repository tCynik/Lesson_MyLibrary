package Storages;

/**
 * Класс База данных по читателям. Является наследником суперкласса Databases.
 * Обьект класса представляет собой динамический массив, каждая строчка которого - запись отдельного
 * обьекта "книга" класса Book
 * дополняет (переопределяет) общие (родительские) методы для конкретной текущей базы данных в зависимости от ее структуры
 * Переопределяет текущие характеристики базы - ее название, адреса файлов
 */

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
