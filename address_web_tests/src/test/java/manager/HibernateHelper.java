package manager;

import io.qameta.allure.Step;
import manager.hbm.AddressInGroup;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(AddressInGroup.class)
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        var bDay = data.id();
        var aDay = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        if ("".equals(bDay)) {
            bDay = "0";
        }
        if ("".equals(aDay)) {
            aDay = "0";
        }

        return new ContactRecord(Integer.parseInt(id),
                data.firstName(),
                data.middleName(),
                data.lastName(),
                data.nickName(),
                data.file(),
                data.title(),
                data.company(),
                data.address(),
                data.homeNumber(),
                data.mobileNumber(),
                data.workNumber(),
                data.faxNumber(),
                data.email(),
                data.email2(),
                data.email3(),
                data.homePage(),
                Integer.parseInt(bDay),
                data.birthMonth(),
                data.birthYear(),
                Integer.parseInt(aDay),
                data.anniversaryMonth(),
                data.anniversaryYear());
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData("" + record.id,
                record.firstName,
                record.middleName,
                record.lastName,
                record.nickName,
                record.file,
                record.title,
                record.company,
                record.address,
                record.homeNumber,
                record.mobileNumber,
                record.workNumber,
                record.faxNumber,
                (record.homeNumber + " " + record.mobileNumber + " " + record.workNumber + " " + record.faxNumber),
                record.email,
                record.email2,
                record.email3,
                (record.email + " " + record.email2 + " " + record.email3),
                record.homePage,
                "" + record.birthDay,
                record.birthMonth,
                record.birthYear,
                "" + record.anniversaryDay,
                record.anniversaryMonth,
                record.anniversaryYear);
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
//        List<ContactData> result = new ArrayList<>();
//        for (var record : records) {
//            result.add(new ContactData(
//                    "" + record.id,
//                    record.firstName,
//                    record.middleName,
//                    record.lastName,
//                    record.nickName,
//                    record.file,
//                    record.title,
//                    record.company,
//                    record.address,
//                    record.homeNumber,
//                    record.mobileNumber,
//                    record.workNumber,
//                    record.faxNumber,
//                    (record.homeNumber + " " + record.mobileNumber + " " + record.workNumber + " " + record.faxNumber),
//                    record.email,
//                    record.email2,
//                    record.email3,
//                    (record.email + " " + record.email2 + " " + record.email3),
//                    record.homePage,
//                    "" + record.birthDay,
//                    record.birthMonth,
//                    record.birthYear,
//                    "" + record.anniversaryDay,
//                    record.anniversaryMonth,
//                    record.anniversaryYear));
//        }
//        return result;
    }

    @Step
    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public GroupData getGroupById(int id) {
        return sessionFactory.fromSession(session -> {
            return convert(session.get(GroupRecord.class, id));
        });
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    @Step
    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public long getCountContactsInGroup() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from AddressInGroup", Long.class).getSingleResult();
        });
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        var addressInGroup = new AddressInGroup(Integer.parseInt(contact.id()), Integer.parseInt(group.id()));
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(addressInGroup);
            session.getTransaction().commit();
        });
    }

    public List<AddressInGroup> getAddressInGroups() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("from AddressInGroup", AddressInGroup.class).list();
        });
    }

    public ContactData getContactById(int id) {
        return sessionFactory.fromSession(session -> {
            return convert(session.get(ContactRecord.class, id));
        });
    }

    public boolean isExistContactInGroup(ContactData contact, GroupData group) {
        return sessionFactory.fromSession(session -> {
            Long count = session.createQuery("SELECT COUNT(a) FROM AddressInGroup a WHERE a.id = :id AND a.group_id = :groupId", Long.class)
                    .setParameter("id", contact.id())
                    .setParameter("groupId", group.id())
                    .uniqueResult();
            return count != null && count > 0;
        });
    }

    public boolean isExistContactInGroup(int contactId, int groupId) {
        return sessionFactory.fromSession(session -> {
            Long count = session.createQuery("SELECT COUNT(a) FROM AddressInGroup a WHERE a.id = :id AND a.group_id = :groupId", Long.class)
                    .setParameter("id", contactId)
                    .setParameter("groupId", groupId)
                    .uniqueResult();
            return count != null && count > 0;
        });
    }
}
