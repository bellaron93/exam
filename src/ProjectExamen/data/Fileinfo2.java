package ProjectExamen.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fileinfo2 {

    public static BufferedWriter creatWriter() {
        File output=new File("resources/output.txt");
        try {
            return new BufferedWriter(new FileWriter(output,true));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }


    }
}
