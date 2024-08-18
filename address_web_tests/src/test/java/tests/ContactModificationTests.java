package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName("AddedFirstName")
                    .withLastName("AddedLastName")
                    .withAddress("Added address")
                    .withAllEmails("testadd@mail.com", "testadd2@mail.com", "testadd3@mail.com")
                    .withAllPhones("257575", "+79994343231", "1233121", "444321"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData("", "First Name",
                "Modify Middle Name",
                "Modify Last Name",
                "Modify Nick Name",
                new File("C:\\Users\\Maks\\Desktop\\java_for_testers\\address_web_tests\\src\\test\\resources\\fileOne.jpg"),
                "Modify Title",
                "Modify Company",
                "Modify Address",
                "757575",
                "78005553535",
                "79005553535",
                "76005553535",
                "email1@mail.com",
                "email2@mail.com",
                "email3@mail.com",
                "MyHomePage",
                "19",
                "May",
                "1991",
                "14",
                "December",
                "2022");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<ContactData>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
