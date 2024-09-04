package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName("AddedFirstName")
                    .withLastName("AddedLastName")
                    .withAddress("Added address")
                    .withEmails("testadd1@mail.com", "testadd2@mail.com", "testadd3@mail.com")
                    .withPhones("257571", "+799943432312", "12331213", "4443214"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData("", "First Name",
                "Modify Middle Name",
                "Modify Last Name",
                "Modify Nick Name",
                "src/test/resources/images/file5.jpg",
                "Modify Title",
                "Modify Company",
                "Modify Address",
                "7575751",
                "780055535352",
                "790055535353",
                "760055535354",
                "",
                "email1@mail.com",
                "email2@mail.com",
                "email3@mail.com",
                "",
                "MyHomePage",
                "19",
                "May",
                "1991",
                "14",
                "December",
                "2022");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
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
