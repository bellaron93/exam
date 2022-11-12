package ProjectExamen.logics;

import ProjectExamen.data.Fileinfo2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;

public class WriterManager {

    private  final BufferedWriter bufferedWriter;

    public WriterManager() {

        bufferedWriter = Fileinfo2.creatWriter();

    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }



    public void flush(){
        try {
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void write(String str) {
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
