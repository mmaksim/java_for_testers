package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToContactPage();
    }

    private void selectGroup(GroupData group) {
        selectGroup(By.name("new_group"), group);

    }

    private void addToGroup(GroupData group) {
        selectGroup(By.name("to_group"), group);
        click(By.name("add"));
    }

    private void selectGroup(By locator, GroupData group) {
        new Select(manager.driver.findElement(locator)).selectByValue(group.id());
    }

    private void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.firstName());
        type(By.name("middlename"), contactData.middleName());
        type(By.name("lastname"), contactData.lastName());
        type(By.name("nickname"), contactData.nickName());
        attach(By.name("photo"), contactData.file());
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

    private void attach(By locator, String file) {
        if (!Objects.equals(file, "")) {
            manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
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
        refreshPage();
        openContactPage();
        selectContact(contact);
        removeSelectedContact();
        returnToContactPage();
    }

    private void refreshPage() {
        manager.driver.navigate().refresh();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void removeAllContacts() {
        refreshPage();
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
            var allEmails = entry.findElement(By.cssSelector("td:nth-child(5)")).getText();
            var allPhones = entry.findElement(By.cssSelector("td:nth-child(6)")).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withLastName(lastName)
                    .withFirstName(firstName)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
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

    public void addToGroup(ContactData contact, GroupData group) {
        openContactPage();
        selectContact(contact);
        addToGroup(group);
        returnToContactPage();
    }

    public void removeGroup(ContactData contact, GroupData group) {
        openContactPage();
        selectGroup(By.name("group"), group);
        selectContact(contact);
        click(By.name("remove"));
        returnToContactPage();
    }
}

