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
    public int birthDay;

    @Column(name = "bmonth")
    public String birthMonth;

    @Column(name = "byear")
    public String birthYear;

    @Column(name = "aday")
    public int anniversaryDay;

    @Column(name = "amonth")
    public String anniversaryMonth;

    @Column(name = "ayear")
    public String anniversaryYear;

    public ContactRecord() {
    }

    public ContactRecord(int id,
                         String firstName,
                         String middleName,
                         String lastName,
                         String nickName,
                         String file,
                         String title,
                         String company,
                         String address,
                         String homeNumber,
                         String mobileNumber,
                         String workNumber,
                         String faxNumber,
                         String email,
                         String email2,
                         String email3,
                         String homePage,
                         int birthDay,
                         String birthMonth,
                         String birthYear,
                         int anniversaryDay,
                         String anniversaryMonth,
                         String anniversaryYear) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.file = file;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homeNumber = homeNumber;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homePage = homePage;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.anniversaryDay = anniversaryDay;
        this.anniversaryMonth = anniversaryMonth;
        this.anniversaryYear = anniversaryYear;
    }
}
