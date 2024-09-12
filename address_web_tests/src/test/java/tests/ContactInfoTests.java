package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withEmails(CommonFunctions.randomString(5) + "@mail.com",
                            CommonFunctions.randomString(5) + "@mail.com",
                            CommonFunctions.randomString(5) + "@mail.com")
                    .withPhones(CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers()));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.homeNumber(), contact.mobileNumber(), contact.workNumber())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testEmailsAddressPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withEmails(CommonFunctions.randomString(5) + "@mail.com",
                            CommonFunctions.randomString(5) + "@mail.com",
                            CommonFunctions.randomString(5) + "@mail.com")
                    .withPhones(CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers(),
                            CommonFunctions.randomNumbers()));
            app.contacts().refreshPage();
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var contact = oldContacts.get(index);
        var phones = app.contacts().getPhones(contact);
        var address = app.contacts().getAddress(contact);
        var emails = app.contacts().getEmails(contact);
        app.contacts().initContactModification(contact);
        var phonesEditMode = app.contacts().getPhoneEditMode();
        var addressEditMode = app.contacts().getAddressEditMode();
        var emailsEditMode = app.contacts().getEmailsEditMode();

       Assertions.assertEquals(phones, phonesEditMode);
       Assertions.assertEquals(address, addressEditMode);
       Assertions.assertEquals(emails, emailsEditMode);
    }
}

