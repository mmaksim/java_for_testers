package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunctions {
    public static String randomString(int length) {
      var rnd = new Random();
       var result = "";
       for (int i = 0; i < length; i++) {
           result = result + (char) ('a' + rnd.nextInt(26));
       }
       return result;
    }

    public static String randomFile(String dir){
       var fileNames = new File(dir).list();
       var random = new Random();
       var index = random.nextInt(fileNames.length);
       return Paths.get(dir, fileNames[index]).toString();
    }

    public static String randomNumbers() {
        var rnd = new Random();
        var result = "";
        String s = "123456789";
        for (int i = 0; i < 12; i++) {
            result = result + s.charAt(rnd.nextInt(s.length()));
        }
        return result;
    }
}
