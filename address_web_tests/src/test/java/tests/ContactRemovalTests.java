package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canContactRemoval() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "First Name",
                    "Middle Name",
                    "Last Name",
                    "Nick Name",
                    new File("C:\\Users\\Maks\\Desktop\\java_for_testers\\address_web_tests\\src\\test\\resources\\fileOne.jpg"),
                    "Title",
                    "Company",
                    "Address",
                    "757575",
                    "78005553535",
                    "79005553535",
                    "76005553535",
                    "email1@mail.com",
                    "email2@mail.com",
                    "email3@mail.com",
                    "MyHomePage",
                    "20",
                    "May",
                    "1990",
                    "15",
                    "December",
                    "2012"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemovalAllContacts() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData()
                    .withFirstName("TestName")
                    .withLastName("Ivanov")
                    .withAddress("New address")
                    .withAllEmails("test@mail.com", "test2@mail.com", "test3@mail.com")
                    .withAllPhones("757575", "+79994443231", "123321", "321"));
            app.contacts().createContact(new ContactData()
                    .withFirstName("Max")
                    .withLastName("Ivanov")
                    .withAddress("One more address")
                    .withAllEmails("test111@mail.com", "test2222@mail.com", "test3@mail.com")
                    .withAllPhones("757575", "+79992243231", "123321", "321"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}
