package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static String readFile(String filePath){
        try{
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static void  writeFile(String filePath, String content){
        try{
            Files.write(Path.get(filePath), content.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
