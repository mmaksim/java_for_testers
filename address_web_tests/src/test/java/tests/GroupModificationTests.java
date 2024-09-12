package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().
                    withName("Group name").
                    withHeader("Group header").
                    withFooter("Group footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());

        var testData = new GroupData()
                .withName("modify " + CommonFunctions.randomString(5))
                .withHeader("modify " + CommonFunctions.randomString(5))
                .withFooter("modify " + CommonFunctions.randomString(5));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));

        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }
}
