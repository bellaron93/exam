import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        File resources=new File("resources");
        resources.mkdir();
        File file=new File(resources,"application.properties");
        System.out.println(file.getAbsolutePath());
        if (file.canWrite()){
            System.out.println("putem sa scriem");
        }else {
            System.out.println("avem nevoie de drepturi");
            file.setWritable(true);
        }

    }
}
