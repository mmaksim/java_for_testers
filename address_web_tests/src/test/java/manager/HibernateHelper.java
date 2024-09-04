package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
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
        if ("".equals(bDay)) {bDay="0";}
        if ("".equals(aDay)) {aDay="0";}


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

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(new ContactData(
                    "" + record.id,
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
                    record.anniversaryYear));
        }
        return result;
    }

    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
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
}
