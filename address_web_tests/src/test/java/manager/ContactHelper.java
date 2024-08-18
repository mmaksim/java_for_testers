package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


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
        if (file != null) {
            manager.driver.findElement(By.name("photo")).sendKeys(file.getAbsolutePath());
        }
    }

    private void returnToContactPage() {
        manager.driver.findElement(By.linkText("home")).click();
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

    public void openContactPage() {
        if (!manager.isElementPresent(By.id("maintable"))) {
            click(By.linkText("home"));
        }
    }

    public void removeContact(ContactData contact) {
        openContactPage();
        selectContact(contact);
        removeSelectedContact();
        returnToContactPage();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void removeAllContacts() {
        selectAllContacts();
        removeSelectedContact();
    }

    private void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public int getCount() {
        openContactPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        openContactPage();
        var contacts = new ArrayList<ContactData>();
        var entries = manager.driver.findElements(By.name("entry"));
        for (var entry : entries) {
            var checkbox = entry.findElement(By.cssSelector("input"));
            var id = checkbox.getAttribute("value");
            var lastName = entry.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstName = entry.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var address = entry.findElement(By.cssSelector("td:nth-child(4)")).getText();
            var allEmails = entry.findElement(By.cssSelector("td:nth-child(5)")).getText().split("\n");
            var allPhones = entry.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");

            String[] emails = new String[]{"", "", ""};
            for (int i = 0; i < allEmails.length; i++) {
                emails[i] = allEmails[i];
            }

            String[] phones = new String[]{"", "", "", ""};
            for (int i = 0; i < allPhones.length; i++) {
                phones[i] = allPhones[i];
            }

            contacts.add(new ContactData()
                    .withId(id)
                    .withLastName(lastName)
                    .withFirstName(firstName)
                    .withAddress(address)
                    .withAllEmails(emails[0], emails[1], emails[2])
                    .withAllPhones(phones[0], phones[1], phones[2], phones[3]));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactPage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModify();
        returnToContactPage();
    }

    private void submitContactModify() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        var elementId = manager.driver.findElement(By.id(contact.id()));
        var entry = elementId.findElement(By.xpath("../.."));
        var editButton = entry.findElement(By.cssSelector("td:nth-child(8)"));
        editButton.click();
    }
}

