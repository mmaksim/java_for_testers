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
                    (record.homeNumber + record.mobileNumber + record.workNumber + record.faxNumber),
                    record.email,
                    record.email2,
                    record.email3,
                    (record.email + record.email2 + record.email3),
                    record.homePage,
                    record.birthDay,
                    record.birthMonth,
                    record.birthYear,
                    record.anniversaryDay,
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

}
