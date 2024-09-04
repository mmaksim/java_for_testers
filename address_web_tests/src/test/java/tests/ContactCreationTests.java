package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var lastName : List.of("", "Last Name")) {
            for (var firstName : List.of("", "First Name")) {
                for (var address : List.of("", "New Address")) {
                    for (var email : List.of("", "newemail1@gmail.com")) {
                        for (var phone : List.of("", "7778889990101")) {
                            result.add(new ContactData()
                                    .withLastName(lastName)
                                    .withFirstName(firstName)
                                    .withAddress(address)
                                    .withPhones(phone, "", "", "")
                                    .withEmails(email, "", "")
                                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
                        }
                    }

                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var expectList = new ArrayList<>(oldContacts);
        var newContact = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        expectList.add(contact.withId(newContact.getLast().id()));
        expectList.sort(compareById);

        Assertions.assertEquals(newContact, expectList);
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
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContact = app.hbm().getContactList();
        Assertions.assertEquals(oldContacts, newContact);
    }
}
