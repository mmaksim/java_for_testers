package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import manager.hbm.AddressInGroup;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
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

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withLastName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withPhones(CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers())
                .withEmails(CommonFunctions.randomString(10) + "@mail.com",
                        CommonFunctions.randomString(10) + "@mail.com",
                        CommonFunctions.randomString(10) + "@mail.com")
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().
                    withName("Group name").
                    withHeader("Group header").
                    withFooter("Group footer"));
        }
        var group = app.hbm().getGroupList().getFirst();
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @Test
    public void canAddContactToGroup() {
        var contacts = app.hbm().getContactList();
        var groups = app.hbm().getGroupList();

        if (groups.isEmpty()) {
            var group = new GroupData().
                    withName(CommonFunctions.randomString(10)).
                    withHeader(CommonFunctions.randomString(10)).
                    withFooter(CommonFunctions.randomString(10));
            app.hbm().createGroup(group);
            groups = app.hbm().getGroupList();
        }
        if (contacts.isEmpty()) {
            var contact = new ContactData()
                    .withLastName(CommonFunctions.randomString(10))
                    .withFirstName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withPhones(CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers())
                    .withEmails(CommonFunctions.randomString(10) + "@mail.com",
                            CommonFunctions.randomString(10) + "@mail.com",
                            CommonFunctions.randomString(10) + "@mail.com")
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
            app.hbm().createContact(contact);
            contacts = app.hbm().getContactList();
        }

        ContactData contactData = new ContactData();
        GroupData groupData = new GroupData();

        outerLoop:
        for (var contact : contacts) {
            for (var group : groups) {
                if (!app.hbm().getContactsInGroup(group).contains(contact)) {
                    contactData = contact;
                    groupData = group;
                    break outerLoop;
                }
            }
        }
        if (groupData.id().equals("")) {
             var group = new GroupData().
                                withName(CommonFunctions.randomString(10)).
                                withHeader(CommonFunctions.randomString(10)).
                                withFooter(CommonFunctions.randomString(10));
                        app.hbm().createGroup(group);
                        groupData = app.hbm().getGroupList().getLast();
                        var rnd = new Random();
                        var contactsIndex = rnd.nextInt(contacts.size());
                        contactData = app.hbm().getContactList().get(contactsIndex);
        }

        var oldRelated = app.hbm().getContactsInGroup(groupData);
        app.contacts().addToGroup(contactData, groupData);
        var expectedList = new ArrayList<ContactData>(oldRelated);
        expectedList.add(contactData);
        var newRelated = app.hbm().getContactsInGroup(groupData);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }

    @Test
    public void canRemoveContactFromGroup() {
        if (app.hbm().getContactCount() == 0) {
            var contact = new ContactData()
                    .withLastName(CommonFunctions.randomString(10))
                    .withFirstName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withPhones(CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers(), CommonFunctions.randomNumbers())
                    .withEmails(CommonFunctions.randomString(10) + "@mail.com",
                            CommonFunctions.randomString(10) + "@mail.com",
                            CommonFunctions.randomString(10) + "@mail.com")
                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));
            app.hbm().createContact(contact);
        }
        if (app.hbm().getGroupCount() == 0) {
            var group = new GroupData().
                    withName(CommonFunctions.randomString(10)).
                    withHeader(CommonFunctions.randomString(10)).
                    withFooter(CommonFunctions.randomString(10));
            app.hbm().createGroup(group);
        }
        if (app.hbm().getCountContactsInGroup() == 0) {
            var contacts = app.hbm().getContactList();
            var groups = app.hbm().getGroupList();
            var rnd = new Random();
            var contactsIndex = rnd.nextInt(contacts.size());
            var groupsIndex = rnd.nextInt(groups.size());
            app.hbm().addContactToGroup(contacts.get(contactsIndex), groups.get(groupsIndex));
        }
        var addressInGroup = app.hbm().getAddressInGroups();
        var rnd = new Random();
        var index = rnd.nextInt(addressInGroup.size());
        var contact = app.hbm().getContactById(addressInGroup.get(index).id);
        var group = app.hbm().getGroupById(addressInGroup.get(index).group_id);

        var expectedList = new ArrayList<AddressInGroup>(addressInGroup);
        expectedList.remove(index);
        app.contacts().removeGroup(contact, group);
        var newAddressInGroup = app.hbm().getAddressInGroups();
        Comparator<AddressInGroup> compareById = (o1, o2) -> {
            return Integer.compare(o1.id, o2.id);
        };
        newAddressInGroup.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddressInGroup, expectedList);
    }
}
