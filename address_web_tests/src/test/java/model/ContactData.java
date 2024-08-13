package model;

import java.io.File;

public record ContactData(String firstName,
                          String middleName,
                          String lastName,
                          String nickName,
                          File file,
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
                          String birthDay,
                          String birthMonth,
                          String birthYear,
                          String anniversaryDay,
                          String anniversaryMonth,
                          String anniversaryYear) {

    public ContactData() {
        this("", "", "", "", null, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(
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
                this.email,
                this.email2,
                this.email3,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(
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
                this.email,
                this.email2,
                this.email3,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear);
    }

    public ContactData withAddress(String address) {
        return new ContactData(
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
                this.email,
                this.email2,
                this.email3,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear);
    }

    public ContactData withAllPhones(String homeNumber, String mobileNumber, String workNumber, String faxNumber) {
        return new ContactData(
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
                this.email,
                this.email2,
                this.email3,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear);
    }

    public ContactData withAllEmails(String email, String email2, String email3) {
        return new ContactData(
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
                email,
                email2,
                email3,
                this.homePage,
                this.birthDay,
                this.birthMonth,
                this.birthYear,
                this.anniversaryDay,
                this.anniversaryMonth,
                this.anniversaryYear);
    }
}