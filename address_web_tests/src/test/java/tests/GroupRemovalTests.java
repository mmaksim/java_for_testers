package tests;

import io.qameta.allure.Allure;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        Allure.step("checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData().
                        withName("Group name").
                        withHeader("Group header").
                        withFooter("Group footer"));
            }
        } );
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);

        Allure.step("validating results", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    public void canRemoveAllGroupsAtOnce() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().
                    withName("Group name").
                    withHeader("Group header").
                    withFooter("Group footer"));
        }
        app.groups().removeAllGroup();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }
}
