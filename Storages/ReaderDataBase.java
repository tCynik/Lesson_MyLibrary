package Storages;

/**
 * Класс База данных по читателям. Является наследником суперкласса Databases.
 * Обьект класса представляет собой динамический массив, каждая строчка которого - запись отдельного
 * обьекта "Читатель" класса Reader
 * дополняет (переопределяет) общие (родительские) методы для конкретной текущей базы данных
 * Переопределяет текущие характеристики базы - ее название, адреса файлов
 */
public class ReaderDataBase extends Databases {

    public ReaderDataBase () {
        this.localBaseName = "Readers database";
        this.baseAdressTxt = "BdReaders";
        this.baseAdressBin = "readersbin.bin";
    }

    @Override
    public Reader fillFields (int count, String theLine){
        String[] pole = theLine.split(", ");
        int yearBirth = Integer.parseInt(pole[1]);
        int numberBileta = count;
        Reader reader = new Reader(pole[0], yearBirth, numberBileta, pole[2]);
        return reader;
    }

    @Override
    public void printLine(Object object) {
        Reader reader = (Reader) object;
        reader.info();
    }
}

//////// скорректировать сигнатуру методов книг в соответствии с новой структурой
//////// подчистить классы Book и Reader
//////// исправть порядок назначения номеров читательских билетов (начало с 0)
//////// в классе Start верни автозапуск меню
//////// при загрузке базы из Bin добавлять новые записи далее к предыдущим