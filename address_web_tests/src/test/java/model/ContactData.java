package model;

import java.util.Objects;

public record ContactData(String id,
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
                          String allPhones,
                          String email,
                          String email2,
                          String email3,
                          String allEmails,
                          String homePage,
                          String birthDay,
                          String birthMonth,
                          String birthYear,
                          String anniversaryDay,
                          String anniversaryMonth,
                          String anniversaryYear
) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(
                id,
                this.firstName,
                this.middleName,
                this.lastName,
                this.nickName,
                this.file,
                this.title,
                this.company,
                this.address,
                this.homeNumber,
                this.mobileNumber,
                this.workNumber,
                this.faxNumber,
                this.allPhones,
                this.email,
                this.email2,
                this.email3,
                this.allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(
                this.id,
                firstName,
                this.middleName,
                this.lastName,
                this.nickName,
                this.file,
                this.title,
                this.company,
                this.address,
                this.homeNumber,
                this.mobileNumber,
                this.workNumber,
                this.faxNumber,
                this.allPhones,
                this.email,
                this.email2,
                this.email3,
                this.allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(
                this.id,
                this.firstName,
                this.middleName,
                lastName,
                this.nickName,
                this.file,
                this.title,
                this.company,
                this.address,
                this.homeNumber,
                this.mobileNumber,
                this.workNumber,
                this.faxNumber,
                this.allPhones,
                this.email,
                this.email2,
                this.email3,
                this.allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }

    public ContactData withAddress(String address) {
        return new ContactData(
                this.id,
                this.firstName,
                this.middleName,
                this.lastName,
                this.nickName,
                this.file,
                this.title,
                this.company,
                address,
                this.homeNumber,
                this.mobileNumber,
                this.workNumber,
                this.faxNumber,
                this.allPhones,
                this.email,
                this.email2,
                this.email3,
                this.allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }

    public ContactData withPhones(String homeNumber, String mobileNumber, String workNumber, String faxNumber) {
        return new ContactData(
                this.id,
                this.firstName,
                this.middleName,
                this.lastName,
                this.nickName,
                this.file,
                this.title,
                this.company,
                this.address,
                homeNumber,
                mobileNumber,
                workNumber,
                faxNumber,
                this.allPhones,
                this.email,
                this.email2,
                this.email3,
                this.allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }

    public ContactData withEmails(String email, String email2, String email3) {
        return new ContactData(
                this.id,
                this.firstName,
                this.middleName,
                this.lastName,
                this.nickName,
                this.file,
                this.title,
                this.company,
                this.address,
                this.homeNumber,
                this.mobileNumber,
                this.workNumber,
                this.faxNumber,
                this.allPhones,
                email,
                email2,
                email3,
                this.allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public ContactData withAllEmails(String allEmails) {
        return new ContactData(
                this.id,
                this.firstName,
                this.middleName,
                this.lastName,
                this.nickName,
                this.file,
                this.title,
                this.company,
                this.address,
                this.homeNumber,
                this.mobileNumber,
                this.workNumber,
                this.faxNumber,
                this.allPhones,
                this.email,
                this.email2,
                this.email3,
                allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }

    public ContactData withAllPhones(String allPhones) {
        return new ContactData(
                this.id,
                this.firstName,
                this.middleName,
                this.lastName,
                this.nickName,
                this.file,
                this.title,
                this.company,
                this.address,
                this.homeNumber,
                this.mobileNumber,
                this.workNumber,
                this.faxNumber,
                allPhones,
                this.email,
                this.email2,
                this.email3,
                this.allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(
                this.id,
                this.firstName,
                this.middleName,
                this.lastName,
                this.nickName,
                photo,
                this.title,
                this.company,
                this.address,
                this.homeNumber,
                this.mobileNumber,
                this.workNumber,
                this.faxNumber,
                this.allPhones,
                this.email,
                this.email2,
                this.email3,
                this.allEmails,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear
        );
    }
}