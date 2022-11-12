package ProjectExamen.logics;

import ProjectExamen.data.FileInfo;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderManager {

    private final BufferedReader bufferedReader;
         public ReaderManager() {

             bufferedReader = FileInfo.CreatReader();
        }


    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }
}