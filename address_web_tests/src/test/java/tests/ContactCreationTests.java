package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider(){
       var result = new ArrayList<ContactData>();
       for (var lastName : List.of("", "Last Name")) {
           for (var firstName : List.of("", "First Name")) {
               for (var address : List.of("", "New Address")) {
                   for (var email : List.of("", "newemail1@gmail.com")) {
                       for (var phone : List.of("", "7778889990102")) {
                           result.add(new ContactData().
                                   withLastName(lastName).
                                   withFirstName(firstName).
                                   withAddress(address).
                                   withAllPhones(phone, "", "", "")
                                   .withAllEmails(email, "", ""));
                       }
                   }

               }
           }
       }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    new File("C:\\Users\\Maks\\Desktop\\java_for_testers\\address_web_tests\\src\\test\\resources\\fileOne.jpg"),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomNumbers(),
                    randomNumbers(),
                    randomNumbers(),
                    randomNumbers(),
                    randomString(i * 10) + "@mail.com",
                    randomString(i * 10) + "@mail.com",
                    randomString(i * 10) + "@mail.com",
                    randomString(i * 10),
                    "20",
                    "May",
                    "1990",
                    "15",
                    "December",
                    "2012")
            );
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData().withFirstName("FirstName'"),
                new ContactData().withLastName("LastName'")
        ));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }
}
