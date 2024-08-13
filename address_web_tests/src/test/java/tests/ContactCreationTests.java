package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("First Name",
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

    @Test
    public void canCreateEmptyContact() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithNameAndAddress() {
        app.contacts().createContact(new ContactData()
                .withFirstName("TestName")
                .withLastName("Ivanov")
                .withAllEmails("test@mail.com", "test2@mail.com","test3@mail.com")
                .withAllPhones("757575", "+79994443231", "123321", "321")
        );
    }
}
