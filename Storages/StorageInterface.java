package Storages;

import java.util.List;

public interface StorageInterface {
    List<Object> downloadTxt();

    List<Object> downloadBin();

    void uploadBin();

    void showAll();

}
