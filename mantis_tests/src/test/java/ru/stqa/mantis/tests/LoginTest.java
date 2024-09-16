package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends  TestBase {

    @Test
    void testLogin() {
        app.http().login("administrator", "root");
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
