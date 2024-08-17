package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    protected  static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "chrome")); //браузер по умолчанию
    }

    public static String randomString(int length) {
      var rnd = new Random();
       var result = "";
       for (int i = 0; i < length; i++) {
           result = result + (char) ('a' + rnd.nextInt(26));
       }
       return result;
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
