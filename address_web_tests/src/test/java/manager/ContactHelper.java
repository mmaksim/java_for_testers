package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.io.File;


public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    private void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.firstName());
        type(By.name("middlename"), contactData.middleName());
        type(By.name("lastname"), contactData.lastName());
        type(By.name("nickname"), contactData.nickName());
        uploadFile(contactData.file());
        type(By.name("title"), contactData.title());
        type(By.name("company"), contactData.company());
        type(By.name("address"), contactData.address());
        type(By.name("home"), contactData.homeNumber());
        type(By.name("mobile"), contactData.mobileNumber());
        type(By.name("work"), contactData.workNumber());
        type(By.name("fax"), contactData.faxNumber());
        type(By.name("email"), contactData.email());
        type(By.name("email2"), contactData.email2());
        type(By.name("email3"), contactData.email3());
        type(By.name("homepage"), contactData.homePage());
        setBirthday(contactData.birthDay(), contactData.birthMonth(), contactData.birthYear());
        setAnniversary(contactData.anniversaryDay(), contactData.anniversaryMonth(), contactData.anniversaryYear());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void uploadFile(File file) {
        if(file != null) {
            manager.driver.findElement(By.name("photo")).sendKeys(file.getAbsolutePath());
        }
    }

    private void returnToContactPage() {
        manager.driver.findElement(By.linkText("home page")).click();
    }

    private void submitContactCreation() {
        manager.driver.findElement(By.name("submit")).click();
    }

    private void setAnniversary(String day, String month, String year) {
        setToDropdown(By.name("aday"), day);
        setToDropdown(By.name("amonth"), month);
        type(By.name("ayear"), year);
    }

    private void setBirthday(String day, String month, String year) {
        setToDropdown(By.name("bday"), day);
        setToDropdown(By.name("bmonth"), month);
        type(By.name("byear"), year);

    }

    private void setToDropdown(By locator, String day) {
        click(locator);
        manager.driver.findElement(locator).sendKeys(day);
    }
}

