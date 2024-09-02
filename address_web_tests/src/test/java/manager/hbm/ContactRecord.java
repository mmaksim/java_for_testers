package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstName;

    @Column(name = "middlename")
    public String middleName;

    @Column(name = "lastname")
    public String lastName;

    @Column(name = "nickname")
    public String nickName;

    @Column(name = "photo")
    public String file;

    @Column(name = "title")
    public String title;

    @Column(name = "company")
    public String company;

    @Column(name = "address")
    public String address;

    @Column(name = "home")
    public String homeNumber;

    @Column(name = "mobile")
    public String mobileNumber;

    @Column(name = "work")
    public String workNumber;

    @Column(name = "fax")
    public String faxNumber;

    @Column(name = "email")
    public String email;

    @Column(name = "email2")
    public String email2;

    @Column(name = "email3")
    public String email3;

    @Column(name = "homepage")
    public String homePage;

    @Column(name = "bday")
    public String birthDay;

    @Column(name = "bmonth")
    public String birthMonth;

    @Column(name = "byear")
    public String birthYear;

    @Column(name = "aday")
    public String anniversaryDay;

    @Column(name = "amonth")
    public String anniversaryMonth;

    @Column(name = "ayear")
    public String anniversaryYear;
}
