package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrationTests extends TestBase {

    public static List<UserData> userProvider() {
        var result = new ArrayList<UserData>();
        for (var i = 0; i < 3; i++) {
            String name = CommonFunctions.randomString(5);
            result.add(new UserData()
                    .withName(name)
                    .withEmail(String.format("%s@localhost", name))
                    .withPassword("password"));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void canRegisterUser(UserData user) {
        app.jamesCli().addUser(user);
        app.session().loginAsAdmin();
        app.user().create(user);
        var url = app.mail().getUrl(user);
        app.driver().get(url);
        app.user().finishCreation(user);
        app.http().login(user);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
