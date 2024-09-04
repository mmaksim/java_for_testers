package manager;


import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {

    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var stmt = conn.createStatement();
             var result = stmt.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List<ContactData> getContactList() {
        var contact = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var stmt = conn.createStatement();
             var result = stmt.executeQuery("SELECT " +
                     "id," +
                     "firstname," +
                     "middlename," +
                     "lastname," +
                     "nickname," +
                     "company," +
                     "title," +
                     "address," +
                     "addr_long," +
                     "addr_lat," +
                     "addr_status," +
                     "home," +
                     "mobile," +
                     "work," +
                     "fax," +
                     "email," +
                     "email2," +
                     "email3," +
                     "im," +
                     "im2," +
                     "im3," +
                     "homepage," +
                     "bday," +
                     "bmonth," +
                     "byear," +
                     "aday," +
                     "amonth," +
                     "ayear," +
                     "address2," +
                     "phone2," +
                     "notes," +
                     "photo," +
                     "x_vcard," +
                     "x_activesync," +
                     "created," +
                     "modified," +
                     "deprecated," +
                     "password," +
                     "login," +
                     "role" +
                     " FROM addressbook")) {
            while (result.next()) {
                contact.add(new ContactData()
                        .withId(result.getString("id"))
                        .withLastName(result.getString("lastname"))
                        .withFirstName(result.getString("firstName"))
                        .withAddress(result.getString("address"))
                        .withPhones(result.getString("home"), result.getString("mobile"), result.getString("id"), result.getString("fax"))
                        .withEmails(result.getString("email"), result.getString("email2"), result.getString("email3"))
                        .withPhoto(result.getString("photo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var stmt = conn.createStatement();
             var result = stmt.executeQuery("SELECT * FROM address_in_groups ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL")) {
            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
